package dev.stralman.flashcardio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import dev.stralman.flashcardio.ui.flashcard.AddFlashCardScreen
import dev.stralman.flashcardio.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
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
                    // test1 DeckScreen(modifier = Modifier)
                    AddFlashCardScreen()
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
