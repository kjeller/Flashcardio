package dev.stralman.flashcardio.ui.deck

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.stralman.flashcardio.data.FakeRepository
import dev.stralman.flashcardio.data.FlashcardDeck
import dev.stralman.flashcardio.ui.theme.AppTheme
import dev.stralman.flashcardio.ui.util.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckItemCard(
    flashcardDeck: FlashcardDeck,
    onNavigateToDeck: (FlashcardDeck) -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier,
        onClick = { onNavigateToDeck(flashcardDeck) },
    ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = flashcardDeck.name,
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
    onNavigateToDeck: (FlashcardDeck) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(modifier = modifier) {
        items(flashcardDeckList) { flashCardSet ->
            DeckItemCard(
                flashcardDeck = flashCardSet,
                onNavigateToDeck = onNavigateToDeck,
                modifier = Modifier.padding(8.dp),
            )
        }
    }
}

@ThemePreview
@Composable
fun DeckItemCardPreview() {
    AppTheme {
        DeckItemCardList(
            flashcardDeckList = FakeRepository().getFlashCardDeckMap(),
            onNavigateToDeck = {},
        )
    }
}