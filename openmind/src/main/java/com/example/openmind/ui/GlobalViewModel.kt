package com.example.openmind.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.openmind.data.repository.RatingRepository
import com.example.openmind.data.repository.provider.RatingRepositoryProvider
import kotlinx.coroutines.launch

open class GlobalViewModel : ViewModel() {
    private val _errorMessageState = mutableStateOf("")
    val ratingRepository: RatingRepository = RatingRepositoryProvider.provideRepository()

    protected val errorMessageState get() = _errorMessageState

    protected open fun handleError(throwable: Throwable) {
        Log.e("GlobalViewModel", "handleError", throwable)
        _errorMessageState.value = throwable.message.toString()

    }

    private fun upvote(ratingId: String) {
        viewModelScope.launch {
            ratingRepository.upvote(ratingId)
        }
    }

    private fun downvote(ratingId: String) {
        viewModelScope.launch {
            ratingRepository.downvote(ratingId)
        }
    }

    fun onRatingChange(): (String, Int) -> Unit = { ratingId, vote ->
        when (vote) {
            1 -> upvote(ratingId)
            -1 -> downvote(ratingId)
        }
    }

}