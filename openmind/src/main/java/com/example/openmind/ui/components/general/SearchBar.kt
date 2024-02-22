package com.example.openmind.ui.components.general

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.openmind.R
import com.example.openmind.ui.theme.BorderLight
import com.example.openmind.ui.theme.MaibPrimary


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    onSearch: (KeyboardActionScope.() -> Unit)? = null,
    onFocusChangeListener: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    var text by remember {
        mutableStateOf("")
    }

    val focusRequester = remember { FocusRequester() }
    val searchIcon = painterResource(id = R.drawable.search_normal)
    val focusManager = LocalFocusManager.current

    Row(
        modifier = modifier
            .fillMaxWidth()
            .border(BorderStroke(2.dp, MaibPrimary), RoundedCornerShape(6.dp))
            .background(Color.White),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        CustomTextField(
            value = text,
            onValueChange = { text = it },
            placeholder = {
                Text(text = "Search")
            },
            modifier = Modifier
                .focusRequester(focusRequester = focusRequester)
                .onFocusChanged { onFocusChangeListener }
                .defaultMinSize(minHeight = 44.dp),
            shape = RoundedCornerShape(6.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = Color.White,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            contentPadding = PaddingValues(top = 10.dp, bottom = 10.dp, start = 20.dp),
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch
                }
            ),
            textStyle = TextStyle(
                fontSize = 16.sp
            )
        )
        Box(
            modifier = Modifier
                .padding(end = 16.dp),
        ) {
            Icon(
                searchIcon,
                contentDescription = null,
                modifier = Modifier.size(22.dp),
                tint = BorderLight
            )
        }
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SearchBarPreview() {
    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar()
    }
}