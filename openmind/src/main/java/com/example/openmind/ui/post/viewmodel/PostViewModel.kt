package com.example.openmind.ui.post.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.openmind.domain.model.comment.Comment
import com.example.openmind.ui.post.components.comments.withStylishTags
import com.example.openmind.utils.SortType
import com.example.openmind.utils.Sortable

class PostViewModel : ViewModel(), Sortable {

    private val viewState: PostViewState = PostViewState()


    fun setCurrentPostById(postId: String) {
        viewState.currentPostId = postId
    }


    fun getPost() = viewState.post.value
    fun getPostRating() = viewState.post.value.rating
    fun getReplyComment() = viewState.commentToReply
    fun setReplyComment(comment: Comment) {
        viewState.commentToReply.value = comment
    }

    fun getComments() = viewState.comments.value

    fun getShortCommentLinesCount() = viewState.defaultCommentLines
    fun getCommentBatchSize() = viewState.commentsBatchSize

    override fun getSortingList(): List<SortType> = viewState.getSortingList()

    override fun setActiveSortType(sortType: SortType) = viewState.setActiveSortType(sortType)

    override fun activeSortType(): SortType = viewState.getActiveSortType().value


    fun commentMessage() = viewState.commentMessage
    fun onCommentChange(): (TextFieldValue) -> Unit = { it ->
        if (viewState.commentMessage.value.text.contains("@"))
            viewState.commentMessage.value = TextFieldValue(
                annotatedString = withStylishTags(it.text),
                selection = it.selection
            )
        else viewState.commentMessage.value = it
    }

    fun onCommentSend(focusManager: FocusManager, replyTo: MutableState<Comment?>) {
        val editedComments = viewState.comments.value.toMutableList()
        if (replyTo.value == null) {
            if (viewState.commentMessage.value.text.isNotBlank())
                editedComments.add(
                    Comment(
                        viewState.currentUser,
                        message = viewState.commentMessage.value.text
                    )
                )
        } else if (replyTo.value!!.parentId == null) {
            editedComments.first { comment: Comment -> comment.commentId == replyTo.value!!.commentId }
                .addSubComment(
                    subCommentAuthor = viewState.currentUser,
                    subCommentMessage = viewState.commentMessage.value.text
                )

        } else {
            editedComments.first { comment: Comment -> comment.commentId == replyTo.value!!.parentId }
                .addSubComment(
                    subCommentAuthor = viewState.currentUser,//my nickname
                    subCommentMessage = viewState.commentMessage.value.text
                )

        }
        replyTo.value = null

        viewState.updatePostComments(editedComments.toList())
        focusManager.clearFocus()
        viewState.commentMessage.value = TextFieldValue("")
    }

    fun scrollToComments() {
        TODO("Not yet implemented")
    }
}
