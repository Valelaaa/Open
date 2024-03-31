package com.example.openmind.ui.categories.viewModel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.openmind.data.repository.CategoriesRepository
import com.example.openmind.data.repository.provider.CategoriesRepositoryProvider
import com.example.openmind.ui.GlobalViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class CategoriesViewModel : GlobalViewModel() {
    private val viewState = CategoriesViewState()
    var repository: CategoriesRepository = CategoriesRepositoryProvider.provideRepository()

    fun isLoading() = viewState.isLoading

    @OptIn(ExperimentalEncodingApi::class)
    fun stringToBitMap(encodedString: String): Bitmap {
        val imageBytes = Base64.decode(encodedString, 0)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }

    fun getCategoriesList() =
        viewState.loadedCategories

    init {
        fetchList()
    }

    private fun fetchList() {
        viewModelScope.launch {
            viewState.isLoading.value = true
            repository.fetchAll().catch { cause: Throwable -> handleError(cause) }
                .collect {
                    viewState.loadedCategories.clear()
                    viewState.loadedCategories.addAll(it)
                    viewState.isLoading.value = false
                }

        }


    }

    override fun onCleared() {
        super.onCleared()
        Log.e("CategoriesViewModel", "ViewModel cleared")
    }


}
