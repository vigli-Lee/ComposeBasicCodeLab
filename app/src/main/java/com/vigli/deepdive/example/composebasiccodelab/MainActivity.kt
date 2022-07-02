package com.vigli.deepdive.example.composebasiccodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.vigli.deepdive.example.composebasiccodelab.ui.theme.ComposeBasicCodeLabTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBasicCodeLabTheme {
                // A surface container using the 'background' color from the theme
                MyApp()
            }
        }
    }
}

@Composable
private fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Greeting(name = "Android")
    }
}

@Composable
fun Greeting(name: String) {
    // Surface 와 같은 머터리얼 구성요소는 배경에 따라 텍스트 색상을 적절하게 변경해줌
    Surface(color = MaterialTheme.colors.primary) {
        Column(modifier = Modifier.padding(24.dp)) {
            Text(text = "Hello")
            Text(text = name)
        }
    }
}

@Preview(showBackground = true, name = "Text Preview")
@Composable
fun DefaultPreview() {
    ComposeBasicCodeLabTheme {
        MyApp()
    }
}