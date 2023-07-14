package dev.stralman.flashcardio

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import dev.stralman.flashcardio.ui.compose.flashcard.CardFace
import dev.stralman.flashcardio.ui.compose.flashcard.FlashCardTextItem
import dev.stralman.flashcardio.ui.theme.FlashcardioTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlashcardioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
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
                    )
                }
            }
        }
    }
}
