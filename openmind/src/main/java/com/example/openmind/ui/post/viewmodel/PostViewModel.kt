package com.example.openmind.ui.post.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.viewModelScope
import com.example.openmind.data.repository.CommentsRepository
import com.example.openmind.data.repository.PostRepository
import com.example.openmind.data.repository.provider.CommentsRepositoryProvider
import com.example.openmind.data.repository.provider.PostRepositoryProvider
import com.example.openmind.domain.model.comment.CommentModel
import com.example.openmind.domain.model.comment.CreateCommentModel
import com.example.openmind.ui.GlobalViewModel
import com.example.openmind.ui.post.components.comments.withStylishTags
import com.example.openmind.utils.SortType
import com.example.openmind.utils.Sortable
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class PostViewModel : GlobalViewModel(), Sortable {

    private val viewState: PostViewState = PostViewState()

    private val postRepository: PostRepository = PostRepositoryProvider.provideRepository()

    private val commentRepository: CommentsRepository =
        CommentsRepositoryProvider.provideRepository()

    override fun setActiveSortType(sortType: SortType) = viewState.setActiveSortType(sortType)

    override fun activeSortType(): SortType = viewState.getActiveSortType().value

    override fun getSortingList(): List<SortType> = viewState.getSortingList()
    fun getFocusRequester() = viewState.commentFieldFocusRequester
    fun isCommentsLoading(): Boolean = viewState.commentsLoading.value
    fun getPost() = viewState.post.value

    fun getPostRating() = viewState.post.value.rating

    fun getReplyComment() = viewState.commentToReply
    fun getShortCommentLinesCount() = viewState.defaultCommentLines

    fun getCommentBatchSize() = viewState.commentsBatchSize

    fun postIsLoading(): Boolean = viewState.postIsLoading.value

    fun commentMessage() = viewState.commentMessage

    fun getComments() =
        viewState.comments


    fun setCurrentPostID(postId: String) {
        viewState.currentPostId = postId
    }

    fun setReplyComment(comment: CommentModel) {
        Log.i("PostViewModel", "SetReplyCommentTO $comment")
        viewState.commentToReply.value = comment
        val initialString =
            withStylishTags("@" + viewState.commentToReply.value!!.commentAuthor.let { "$it, " })
        val selection = TextRange(initialString.length)
        viewState.commentMessage.value = TextFieldValue(
            annotatedString = initialString,
            selection = selection
        )
        viewState.commentFieldFocusRequester.requestFocus()
    }


    fun onCommentChange(): (TextFieldValue) -> Unit = { it ->
        if (viewState.commentMessage.value.text.contains("@"))
            viewState.commentMessage.value = TextFieldValue(
                annotatedString = withStylishTags(it.text),
                selection = it.selection
            )
        else {
            viewState.commentMessage.value = it
            viewState.commentToReply.value = null
        }
    }

    private fun saveComment(comment: CreateCommentModel) {
        viewModelScope.launch {
            commentRepository.postComment(comment)
        }
        fetchComments()
    }

    fun onCommentSend(focusManager: FocusManager, replyTo: MutableState<CommentModel?>) {
        val commentToPost = CreateCommentModel(commentMessage = viewState.commentMessage.value.text)
        commentToPost.postId = viewState.currentPostId

        if (replyTo.value != null) {
            commentToPost.parentCommentId = replyTo.value!!.commentId
        }
        saveComment(commentToPost)
        replyTo.value = null
        focusManager.clearFocus()

        viewState.commentMessage.value = TextFieldValue("")
        fetchComments()
    }

    fun fetchPost() {
        GlobalScope.launch {
            viewState.postIsLoading.value = true
            postRepository.fetchPostById(viewState.currentPostId)
                .catch { cause: Throwable -> handleError(cause) }
                .collect {
                    viewState.post.value = it
                    viewState.postIsLoading.value = false
                }
        }

    }

    fun fetchComments() {
        GlobalScope.launch() {
            try {
                viewState.commentsLoading.value = true
                commentRepository.fetchCommentsByPostId(viewState.currentPostId)
                    .catch { cause: Throwable -> handleError(cause) }.collect {
                        viewState.comments.clear()
                        viewState.comments.addAll(it)
                        viewState.commentsLoading.value = false
                    }
            } catch (e: Exception) {
                handleError(e)
            }
        }
    }


}
