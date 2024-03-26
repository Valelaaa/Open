package com.example.openmind.ui.categories.viewModel

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModel
import kotlin.io.encoding.Base64
import kotlin.io.encoding.ExperimentalEncodingApi

class CategoriesViewModel : ViewModel() {
    private val viewState = CategoriesViewState()
    fun isLoading() = viewState.isLoading

    @OptIn(ExperimentalEncodingApi::class)
    fun stringToBitMap(encodedString: String): Bitmap {
        val imageBytes = Base64.decode(encodedString, 0)
        return BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
    }
    fun getCategoriesList() =
        viewState.loadedCategories

    fun fetchList() {
        viewState.fetchList()
    }


}
