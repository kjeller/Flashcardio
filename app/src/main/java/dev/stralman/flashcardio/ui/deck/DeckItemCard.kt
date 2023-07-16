package dev.stralman.flashcardio.ui.deck

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.stralman.flashcardio.data.Flashcard
import dev.stralman.flashcardio.data.FlashcardDeck
import dev.stralman.flashcardio.ui.theme.AppTheme
import dev.stralman.flashcardio.ui.util.ThemePreview

@Composable
fun DeckItemCard(
    flashcardDeck: FlashcardDeck,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = flashcardDeck.deckName,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Left
            )

            val setListSize = if (flashcardDeck.cards.size > 100) {
                "99+"
            } else {
                "${flashcardDeck.cards.size}"
            }
            Text(
                text = setListSize,
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Right
            )
        }
    }
}

@Composable
fun DeckItemCardList(
    flashcardDeckList: List<FlashcardDeck>,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(flashcardDeckList) { flashCardSet ->
            DeckItemCard(
                flashcardDeck = flashCardSet,
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@ThemePreview
@Composable
fun DeckItemCardPreview() {
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
    AppTheme {
        DeckItemCardList(
            flashcardDeckList = flashCardDeckLists
        )
    }
}