import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.openmind.R
import com.example.openmind.domain.model.comment.Comment
import com.example.openmind.domain.model.user.User
import com.example.openmind.ui.post.components.comments.withStylishTags
import com.example.openmind.ui.post.viewmodel.PostViewModel
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkGray20
import com.example.openmind.ui.theme.IconColor
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeSemiBoldW600


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CommentField(
    viewModel: PostViewModel,
    modifier: Modifier = Modifier,
    replyTo: MutableState<Comment?> = remember { mutableStateOf(null) },
) {

    /*TODO(FIX CURSOR AFTER REPLYING)*/
    var commentMessage = remember {
        mutableStateOf(
            TextFieldValue("")
        )
    }
    val comments = remember {
        viewModel.getComments()
    }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val commentPlaceholderText = stringResource(id = R.string.comment_placeholder)
    Row(
        modifier = modifier
            .background(color = Color.White)
            .border(2.dp, BorderLight, RoundedCornerShape(6.dp))
            .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = commentMessage.value,
            onValueChange = {
                if (commentMessage.value.text.contains("@"))
                    commentMessage.value = TextFieldValue(
                        annotatedString = withStylishTags(it.text),
                        selection = it.selection
                    )
                else commentMessage.value = it
            },
            modifier = Modifier
                .defaultMinSize(minHeight = 40.dp)
                .padding(start = 14.dp, top = 5.dp, bottom = 5.dp)
                .focusRequester(focusRequester)
                .weight(1f),
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                fontFamily = FontFamily.ManropeSemiBoldW600,
                lineHeight = 24.sp,
                color = DarkGray20,
                textAlign = TextAlign.Justify
            ),
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (replyTo.value == null) {
                        comments.add(
                            Comment(
                                //TODO(Add @OtherPerson tag, invoke API request)
                                User("@janedoe"),
                                message = commentMessage.value.text
                            )
                        )
                    } else if (replyTo.value!!.parentId == null) {
                        comments.first { comment: Comment -> comment.commentId == replyTo.value!!.commentId }
                            .addSubComment(
                                subCommentAuthor = User("@janedoe"),//my nickname
                                subCommentMessage = commentMessage.value.text
                            )

                    } else {
                        comments.first { comment: Comment -> comment.commentId == replyTo.value!!.parentId }
                            .addSubComment(
                                subCommentAuthor = User("@janedoe"),//my nickname
                                subCommentMessage = commentMessage.value.text
                            )

                    }
                    replyTo.value = null

                    viewModel.updateComments(comments)
                    focusManager.clearFocus()
                    commentMessage.value = TextFieldValue("")
                }

            ),
            decorationBox = @Composable { innerTextField ->
                TextFieldDefaults.TextFieldDecorationBox(
                    value = commentMessage.value.text,
                    innerTextField =
                    innerTextField,
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = remember { MutableInteractionSource() },
                    colors = TextFieldDefaults.textFieldColors(
                        unfocusedIndicatorColor = Color.Transparent,
                        containerColor = Color.Transparent,
                    ),
                    shape = RoundedCornerShape(6.dp),
                    placeholder = {
                        Text(
                            text = commentPlaceholderText,
                            color = IconColor,
                            fontFamily = FontFamily.ManropeSemiBoldW600,
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            modifier = Modifier.fillMaxWidth()
                        )
                    },
                    contentPadding = PaddingValues(5.dp),
                )
            },

            )
        Icon(
            Icons.Default.Send,
            contentDescription = "send", modifier = Modifier
                .clickable {
                    if (replyTo.value == null) {
                        comments.add(
                            Comment(
                                //TODO(Add @OtherPerson tag, invoke API request)
                                User("@janedoe"),
                                message = commentMessage.value.text
                            )
                        )
                    } else if (replyTo.value!!.parentId == null) {
                        comments
                            .first { comment: Comment -> comment.commentId == replyTo.value!!.commentId }
                            .addSubComment(
                                subCommentAuthor = User("@janedoe"),//my nickname
                                subCommentMessage = commentMessage.value.text
                            )

                    } else {
                        comments
                            .first { comment: Comment -> comment.commentId == replyTo.value!!.parentId }
                            .addSubComment(
                                subCommentAuthor = User("@janedoe"),//my nickname
                                subCommentMessage = commentMessage.value.text
                            )

                    }
                    replyTo.value = null

                    viewModel.updateComments(comments)
                    focusManager.clearFocus()
                    commentMessage.value = TextFieldValue("")
                }
                .size(30.dp),
            tint = MaibPrimary
        )
    }
    LaunchedEffect(replyTo.value) {
        // Каждый раз, когда изменяется значение replyTo, обновляем commentMessage
        if (replyTo.value != null) {
            val initialString =
                withStylishTags("@" + replyTo.value!!.author.nickname.let { "$it, " })
            val selection = TextRange(initialString.length)
            commentMessage.value =
                TextFieldValue(
                    annotatedString = initialString,
                    selection = selection
                )
            focusRequester.requestFocus()
        }
    }
}


@Preview
@Composable
fun CommentFieldPreview() {
    CommentField(viewModel = viewModel())
}