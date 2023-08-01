package dev.stralman.flashcardio.ui.deck

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
import dev.stralman.flashcardio.data.FakeRepository
import dev.stralman.flashcardio.data.FlashcardDeck
import dev.stralman.flashcardio.ui.theme.AppTheme
import dev.stralman.flashcardio.ui.util.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckScreen(
    modifier: Modifier = Modifier,
    onNavigateToDeck: (FlashcardDeck) -> Unit,
    onNavigateToAddFlashcard: (String) -> Unit,
    onNavigateToAddDeck: (String) -> Unit,
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
                        text = stringResource(R.string.flashcard_deckscreen_title),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onNavigateToAddDeck("test")
                }
            ) {
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
            val flashCardDeckLists = FakeRepository().getFlashCardDeckMap()
            DeckItemCardList(
                flashcardDeckList = flashCardDeckLists,
                onNavigateToDeck = onNavigateToDeck,
            )
        }
    }
}

@ThemePreview
@Composable
fun DeckScreenPreview() {
    AppTheme {
        DeckScreen(
            onNavigateToDeck = {},
            onNavigateToAddFlashcard = {},
        ) {}
    }
}