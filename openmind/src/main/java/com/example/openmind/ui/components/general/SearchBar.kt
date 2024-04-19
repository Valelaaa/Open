package com.example.openmind.ui.components.general

import com.example.openmind.enums.Keyboard
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.openmind.R
import com.example.openmind.enums.PostCategories
import com.example.openmind.ui.navigation.navigateToSearchResult
import com.example.openmind.ui.post_list.viewModel.PostListViewModel
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.MaibPrimary
import keyboardAsState


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: PostListViewModel,
    onSearch: (KeyboardActionScope.() -> Unit)? = null,
    onFocusChangeListener: (() -> Unit)? = null
) {
    val searchText by viewModel.getSearchText().collectAsState()
    val focusRequester = remember { FocusRequester() }
    val keyboardState = keyboardAsState()
    val mutableInteractionSource = remember { MutableInteractionSource() }
    var previousKeyboardState by remember { mutableStateOf(keyboardState.value) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(2.dp, MaibPrimary), RoundedCornerShape(6.dp))
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = searchText,
            onValueChange = { viewModel.onSearchTextChanged(it) },
            modifier = Modifier
                .focusRequester(focusRequester = focusRequester)
                .onFocusChanged { onFocusChangeListener }
                .defaultMinSize(minHeight = 44.dp),
            textStyle = TextStyle(
                fontSize = 16.sp
            ),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch
                    navController.navigateToSearchResult(searchText, viewModel.getPostCategory())
                    viewModel.resetSearch()
                    viewModel.updateSearchBarVisibility(false)
                }
            ),
            decorationBox = @Composable { innerTextField ->
                TextFieldDefaults.DecorationBox(
                    value = searchText,
                    innerTextField = innerTextField,
                    placeholder = {
                        Text(text = "Search")
                    },
                    enabled = true,
                    singleLine = true,
                    visualTransformation = VisualTransformation.None,
                    interactionSource = mutableInteractionSource,
                    shape = RoundedCornerShape(6.dp),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.White,
                        unfocusedIndicatorColor = Color.Transparent,
                    ),
                    contentPadding = PaddingValues(top = 10.dp, bottom = 10.dp, start = 20.dp)
                )
            }
        )
        Box(
            modifier = Modifier
                .padding(end = 16.dp),
        ) {
            Icon(
                painterResource(id = R.drawable.search_normal),
                contentDescription = null,
                modifier = Modifier
                    .size(22.dp)
                    .clickable {
                        if (searchText.isNotBlank())
                            navController.navigateToSearchResult(
                                searchText,
                                viewModel
                                    .getActiveCategory()
                                    ?: PostCategories.BUG
                            )
                        viewModel.resetSearch()
                        viewModel.updateSearchBarVisibility(false)
                    },
                tint = BorderLight,
            )
        }
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
    LaunchedEffect(keyboardState.value) {
        if (previousKeyboardState != keyboardState.value) {
            when (keyboardState.value) {
                Keyboard.Closed -> viewModel.updateSearchBarVisibility(false)
                Keyboard.Opened -> Unit
            }
            previousKeyboardState = keyboardState.value
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SearchBarPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(Modifier, navController = rememberNavController(), PostListViewModel())
    }
}