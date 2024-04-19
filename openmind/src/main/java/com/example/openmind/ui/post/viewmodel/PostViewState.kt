package com.example.openmind.ui.post.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.text.input.TextFieldValue
import com.example.openmind.domain.model.comment.CommentModel
import com.example.openmind.domain.model.post.PostDto
import com.example.openmind.enums.SortType

internal class PostViewState {
    val postIsLoading = mutableStateOf(true)

    val commentToReply = mutableStateOf<CommentModel?>(null)
    val defaultCommentLines = 3
    val commentsBatchSize = 5

    val commentMessage = mutableStateOf(
        TextFieldValue("")
    )

    val commentFieldFocusRequester = FocusRequester()

    var activeSortType: MutableState<SortType> = mutableStateOf(SortType.HOT)
    val sortingList: List<SortType> = listOf(
        SortType.HOT,
        SortType.NEW,
        SortType.OLD
    )
    var currentPostId: String = ""

    var post: MutableState<PostDto> = mutableStateOf(PostDto("", ""))

    val comments = mutableStateListOf<CommentModel>()

}