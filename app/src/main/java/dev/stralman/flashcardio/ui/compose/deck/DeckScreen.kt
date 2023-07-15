package dev.stralman.flashcardio.ui.compose.deck

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import dev.stralman.flashcardio.R
import dev.stralman.flashcardio.model.flashcard.Flashcard
import dev.stralman.flashcardio.model.flashcard.FlashcardDeck
import dev.stralman.flashcardio.ui.compose.theme.AppTheme
import dev.stralman.flashcardio.ui.compose.util.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckScreen(
    modifier: Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Flashcard sets",
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* ... */ }) {
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = stringResource(id = R.string.deck_add_desc)
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,

        ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) {
            // TODO replace this with an actual data source
            val flashCardDeckLists = listOf(
                FlashcardDeck(
                    deckName = "Korean common phrases한글",
                    cards = listOf(
                        Flashcard(
                            frontText = "안녕하세요",
                            backText = "Hello (annyeonghaseyo)"
                        ),
                    )
                ),
                FlashcardDeck(
                    deckName = "NATO Phonetic Alphabet",
                    cards = listOf(
                        Flashcard(
                            frontText = "A",
                            backText = "Alpha"
                        ),
                        Flashcard(
                            frontText = "B",
                            backText = "Bravo"
                        ),
                        Flashcard(
                            frontText = "C",
                            backText = "Charlie"
                        )
                    )
                )
            )
            DeckItemCardList(
                flashcardDeckList = flashCardDeckLists
            )
        }
    }
}

@ThemePreview
@Composable
fun DeckScreenPreview() {
    AppTheme {
        DeckScreen()
    }
}