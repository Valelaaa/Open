import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.openmind.R
import com.example.openmind.domain.model.comment.Comment
import com.example.openmind.domain.model.user.User
import com.example.openmind.ui.components.general.CustomTextField
import com.example.openmind.ui.post.viewmodel.PostViewModel
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.DarkGray20
import com.example.openmind.ui.theme.IconColor
import com.example.openmind.ui.theme.MaibPrimary
import com.example.openmind.ui.theme.ManropeSemiBoldW600


@OptIn(ExperimentalMaterial3Api::class, ExperimentalComposeUiApi::class)
@Composable
fun CommentField(
    viewModel: PostViewModel,
    modifier: Modifier = Modifier,
    replyTo: MutableState<Comment?> = remember { mutableStateOf(null) },
) {

    /*TODO(FIX CURSOR AFTER REPLYING)*/
    var commentMessage = remember {
        mutableStateOf(
            ""
        )
    }
    val comments = remember {
        viewModel.getComments()
    }
    val keyboardController = LocalSoftwareKeyboardController.current
    val commentPlaceholderText = stringResource(id = R.string.comment_placeholder)

    Row(
        modifier = modifier
            .background(color = Color.White)
            .border(2.dp, BorderLight, RoundedCornerShape(6.dp))
            .padding(bottom = 5.dp, start = 10.dp, end = 10.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomTextField(
            value = commentMessage.value,
            onValueChange = {
                commentMessage.value = it
            },
            shape = RoundedCornerShape(6.dp),
            colors = TextFieldDefaults.textFieldColors(
                unfocusedIndicatorColor = Color.Transparent,
                containerColor = Color.Transparent,
            ),
            contentPadding = PaddingValues(5.dp),
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
            modifier = Modifier
                .defaultMinSize(minHeight = 40.dp)
                .padding(start = 14.dp, top = 5.dp, bottom = 5.dp)
                .weight(1f),
            textStyle = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                fontFamily = FontFamily.ManropeSemiBoldW600,
                lineHeight = 24.sp,
                color = DarkGray20,
                textAlign = TextAlign.Justify
            ),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = {
                    if (replyTo.value == null) {
                        comments.add(
                            Comment(
                                //TODO(Add @OtherPerson tag, invoke API request)
                                User("@janedoe"),
                                message = commentMessage.value
                            )
                        )
                    } else if (replyTo.value!!.parentId == null) {
                        comments.first { comment: Comment -> comment.commentId == replyTo.value!!.commentId }
                            .addSubComment(
                                subCommentAuthor = User("@janedoe"),//my nickname
                                subCommentMessage = commentMessage.value
                            )

                    } else {
                        comments.first { comment: Comment -> comment.commentId == replyTo.value!!.parentId }
                            .addSubComment(
                                subCommentAuthor = User("@janedoe"),//my nickname
                                subCommentMessage = commentMessage.value
                            )

                    }
                    replyTo.value = null

                    viewModel.updateComments(comments)
                    commentMessage.value = ""
                    keyboardController?.hide()
                }
            ),

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
                                message = commentMessage.value
                            )
                        )
                    } else if (replyTo.value!!.parentId == null && replyTo.value!!.commentId != null) {
                        comments
                            .first { comment: Comment -> comment.commentId == replyTo.value!!.commentId }
                            .addSubComment(
                                subCommentAuthor = User("@janedoe"),//my nickname
                                subCommentMessage = commentMessage.value
                            )

                    } else {
                        comments
                            .first { comment: Comment -> comment.commentId == replyTo.value!!.parentId }
                            .addSubComment(
                                subCommentAuthor = User("@janedoe"),//my nickname
                                subCommentMessage = commentMessage.value
                            )

                    }
                    replyTo.value = null

                    viewModel.updateComments(comments)
                    commentMessage.value = ""
                    keyboardController?.hide()
                }
                .size(30.dp),
            tint = MaibPrimary
        )
    }
    LaunchedEffect(replyTo.value) {
        // Каждый раз, когда изменяется значение replyTo, обновляем commentMessage
        if (replyTo.value != null) {
            commentMessage.value = ("@" + replyTo.value?.author?.nickname?.let { "$it, " })
        }
    }

}