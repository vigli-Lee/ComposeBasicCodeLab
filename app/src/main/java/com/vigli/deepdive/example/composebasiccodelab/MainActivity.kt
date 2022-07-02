package com.vigli.deepdive.example.composebasiccodelab

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
private fun MyApp(names: List<String> = listOf("World", "Compose")) {
    Column(modifier = Modifier.padding(vertical = 4.dp)) {
        for (name in names) {
            Greeting(name = name)
        }
    }
}

@Composable
fun Greeting(name: String) {
    // 상태 유지로 recomposition 방지
    val expanded = remember {
        // 상태 변경 추적
        mutableStateOf(false)
    }

    val extraPadding = if (expanded.value) 48.dp else 0.dp

    // Surface 와 같은 머터리얼 구성요소는 배경에 따라 텍스트 색상을 적절하게 변경해줌
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding)
            ) {
                Text(text = "Hello, ")
                Text(text = name)
            }
            OutlinedButton(
                onClick = { expanded.value = expanded.value.not() }
            ) {
                Text(if (expanded.value) "Show less" else "Show more")
            }
        }

    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    name = "Text Preview"
)
@Composable
fun DefaultPreview() {
    ComposeBasicCodeLabTheme {
        MyApp()
    }
}