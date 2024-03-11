package com.example.openmind.utils

import androidx.compose.runtime.mutableStateOf
import com.example.openmind.data.repository.provider.PostRepositoryProvider

class GlobalViewState {
    val repository = PostRepositoryProvider.provideRepository()
    val isSearchBarVisible = mutableStateOf(false)
    
}
