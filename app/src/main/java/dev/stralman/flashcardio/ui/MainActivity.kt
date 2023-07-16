package dev.stralman.flashcardio.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dagger.hilt.android.AndroidEntryPoint
import dev.stralman.flashcardio.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background,
                    tonalElevation = 5.dp,
                ) {
                    FlashcardioApp()
                    // test1 DeckScreen(modifier = Modifier)
                    //AddFlashCardScreen()
                    /*test2
                    var state by remember {
                        mutableStateOf(CardFace.FRONT)
                    }
                    FlashCardTextItem(
                        front = "Front",
                        back = "Back",
                        cardFace = state,
                        onClick = {
                            state = it.next()
                        },
                    ) */
                }
            }
        }
    }
}
