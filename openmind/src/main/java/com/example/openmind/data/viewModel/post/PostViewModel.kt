package com.example.openmind.data.viewModel.post

import androidx.lifecycle.ViewModel
import com.example.openmind.data.post.Post
import com.example.openmind.data.repository.PostRepositoryProvider
import com.example.openmind.data.viewModel.SortType
import com.example.openmind.data.viewModel.Sortable
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

class PostViewModel : ViewModel(), Sortable {
    //TODO(Inject)
    private val viewState: PostViewState = PostViewState()

    fun getPostById(postId: String): Post? {
        var result: Post?
        runBlocking {
            result = PostRepositoryProvider.provideRepository().getById(postId).firstOrNull()
        }
        return result
    }

    override fun getSortingList(): List<SortType> = viewState.getSortingList()

    override fun setActiveSortType(sortType: SortType) = viewState.setActiveSortType(sortType)

    override fun activeSortType(): SortType = viewState.getActiveSortType().value

    override fun onSelect(): () -> Unit {
        TODO("Not yet implemented")
    }

}
