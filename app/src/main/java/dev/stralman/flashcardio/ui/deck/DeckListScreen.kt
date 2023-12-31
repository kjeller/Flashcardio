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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.stralman.flashcardio.R
import dev.stralman.flashcardio.data.Deck
import dev.stralman.flashcardio.data.FlashcardDeck
import dev.stralman.flashcardio.ui.theme.AppTheme
import dev.stralman.flashcardio.ui.util.ThemePreview
import dev.stralman.flashcardio.viewmodels.DeckListViewModel

@Composable
fun DeckListScreen(
    modifier: Modifier = Modifier,
    viewModel: DeckListViewModel = hiltViewModel(),
    onNavigateToDeck: (FlashcardDeck) -> Unit,
    onNavigateToAddDeck: () -> Unit,
) {
    val flashcardDeckList by viewModel.flashcardDeckList.collectAsState(initial = emptyList())
    DeckListScreen(
        flashCardDeckList = flashcardDeckList,
        onNavigateToDeck = onNavigateToDeck,
        onNavigateToAddDeck = onNavigateToAddDeck
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckListScreen(
    modifier: Modifier = Modifier,
    flashCardDeckList: List<FlashcardDeck>,
    onNavigateToDeck: (FlashcardDeck) -> Unit,
    onNavigateToAddDeck: () -> Unit,
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
                    onNavigateToAddDeck()
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
            DeckItemCardList(
                deckList = flashCardDeckList,
                onNavigateToDeck = onNavigateToDeck,
            )
        }
    }
}

@ThemePreview
@Composable
fun DeckListScreenPreview() {
    val flashcardDeckList = listOf(
        FlashcardDeck(
            deck = Deck(
                "Deck1"
            ),
            cards = emptyList(),
        ),
        FlashcardDeck(
            deck = Deck(
                "Deck2"
            ),
            cards = emptyList(),
        )
    )
    AppTheme {
        DeckListScreen(
            flashCardDeckList = flashcardDeckList,
            onNavigateToDeck = {},
            onNavigateToAddDeck = {}
        )
    }
}