package com.example.openmind.ui.search_result.viewModel

import androidx.compose.runtime.mutableStateListOf
import com.example.openmind.data.repository.PostRepository
import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.domain.model.post.ShortPostDto

class SearchResultViewState {
    val repository:PostRepository = PostRepositoryProvider.provideRepository()
    val searchResultList = mutableStateListOf<ShortPostDto>()

}
