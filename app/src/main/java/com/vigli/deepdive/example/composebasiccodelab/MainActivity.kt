package com.vigli.deepdive.example.composebasiccodelab

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
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
    var shouldShowOnboarding by rememberSaveable { mutableStateOf(true) }

    if (shouldShowOnboarding) {
        OnboardingScreen {
            shouldShowOnboarding = false
        }
    } else {
        Greetings()
    }
}

@Composable
private fun OnboardingScreen(onContinueClicked: () -> Unit) {
    Surface {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text("Welcome to the Basics Codelab!")
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
                Text("Continue")
            }
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 320,
    heightDp = 320,
    name = "Onboarding Preview"
)
@Composable
fun OnboardingPreview() {
    ComposeBasicCodeLabTheme {
        OnboardingScreen { }
    }
}

@Composable
private fun Greetings(names: List<String> = List(1000) { "$it" }) {
    LazyColumn(modifier = Modifier.padding(vertical = 4.dp)) {
        items(items = names) { name ->
            Greeting(name = name)
        }
    }
}

@Composable
private fun Greeting(name: String) {
    // 상태 유지로 recomposition 방지
    val expanded = rememberSaveable {
        // 상태 변경 추적
        mutableStateOf(false)
    }

    val extraPadding by animateDpAsState(
        targetValue = if (expanded.value) 48.dp else 0.dp,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        )
    )

    // Surface 와 같은 머터리얼 구성요소는 배경에 따라 텍스트 색상을 적절하게 변경해줌
    Surface(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        Row(modifier = Modifier.padding(24.dp)) {
            Column(modifier = Modifier
                .weight(1f)
                .padding(bottom = extraPadding.coerceAtLeast(0.dp))
            ) {
                Text(text = "Hello, ")
                Text(
                    text = name,
                    style = MaterialTheme.typography.h4.copy(
                        fontWeight = FontWeight.ExtraBold
                    )
                )
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
//    uiMode = UI_MODE_NIGHT_NO,
    uiMode = UI_MODE_NIGHT_YES,
    name = "Text Preview"
)
@Composable
fun DefaultPreview() {
    ComposeBasicCodeLabTheme {
        Greetings()
    }
}