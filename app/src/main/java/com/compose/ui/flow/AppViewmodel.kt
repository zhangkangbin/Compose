package com.compose.ui.flow

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class AppViewModel : ViewModel() { // this is our viewModel
    private val _titleInputFlow = MutableStateFlow<String>("")
    val titleInputFlow: StateFlow<String> get() = _titleInputFlow

    fun setTitle(title: String) {
        _titleInputFlow.value = title
    }

    fun resetTitle() {
        _titleInputFlow.value = ""
    }
}
@Preview
@Composable
fun UiTest(){

     val viewModel = AppViewModel()
    MainContent(viewModel = viewModel)
}
@Composable
fun MainContent(viewModel: AppViewModel) {
 
    val title by viewModel.titleInputFlow.collectAsState()
    Content(name = title, onNameChange = {
        viewModel.setTitle(it)
    }, onResetClicked = { viewModel.resetTitle() })
}

@Composable
fun Content(
    name: String,
    onNameChange: (String) -> Unit,
    onResetClicked: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background)
            .padding(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (name.isNotEmpty()) {
            Text(color = Color.Black,
                text = "Hello, $name!",
                modifier = Modifier.padding(bottom = 8.dp),
            )
        }
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = { onNameChange(it) },
            label = { Text(text = "Name") },
            textStyle = MaterialTheme.typography.subtitle1,
            singleLine = true
        )
        OutlinedButton(onClick = { onResetClicked() },
            modifier = Modifier.padding(16.dp)) {
            Text("Reset")
        }
    }
}