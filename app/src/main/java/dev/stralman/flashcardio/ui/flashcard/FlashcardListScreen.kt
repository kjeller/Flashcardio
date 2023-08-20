package dev.stralman.flashcardio.ui.flashcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.stralman.flashcardio.R
import dev.stralman.flashcardio.data.Flashcard
import dev.stralman.flashcardio.ui.theme.AppTheme
import dev.stralman.flashcardio.ui.util.ThemePreview
import dev.stralman.flashcardio.viewmodels.FlashcardViewModel

@Composable
fun FlashcardListScreen(
    modifier: Modifier = Modifier,
    viewModel: FlashcardViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {
    val flashcardDeck = viewModel.flashcardDeck.observeAsState().value

    if (flashcardDeck != null) {
        FlashcardListScreen(
            modifier = modifier,
            flashcardList = flashcardDeck.cards,
            selectMode = viewModel.selectMode.value,
            onNavigateBack = onNavigateBack,
            onEnterSelectMode = { viewModel.updateSelectMode(true) },
            onExitSelectMode = { viewModel.updateSelectMode(false) },
            onUpdateCardInSelectList = { flashcard -> viewModel.updateList(flashcard) },
            onDeleteSelectedCards = {
                // Delete cards
                viewModel.deleteSelectedFlashcards()

                // .. then exit select mode
                viewModel.updateSelectMode(false)

                // TODO show snackbar on success/fail
                                    },
        )
    } else {
        // TODO add handling
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashcardListScreen(
    modifier: Modifier = Modifier,
    flashcardList: List<Flashcard>,
    selectMode: Boolean,
    onNavigateBack: () -> Unit,
    onEnterSelectMode: () -> Unit,
    onExitSelectMode: () -> Unit,
    onUpdateCardInSelectList: (Flashcard) -> Unit,
    onDeleteSelectedCards: () -> Unit,
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
                        text = stringResource(R.string.modify_flashcards_topbar_title),
                        style = MaterialTheme.typography.titleLarge,
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            onNavigateBack()
                        }
                    ) {
                        Icon(
                            Icons.Rounded.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    if (selectMode) {
                        IconButton(
                            onClick = onDeleteSelectedCards
                        ) {
                            Icon(
                                Icons.Rounded.Delete,
                                contentDescription = "Delete"
                            )
                        }
                    }
                }
            )
        },
    ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) {
            FlashcardItemCardList(
                flashcardList = flashcardList,
                onEnterSelectMode = onEnterSelectMode,
                onExitSelectMode = onExitSelectMode,
                onUpdateCardInSelectList = onUpdateCardInSelectList,
                selectMode = selectMode,
            )
        }
    }
}

@ThemePreview
@Composable
fun FlashcardListScreen() {
    AppTheme {
        FlashcardListScreen(
            selectMode = true,
            onNavigateBack = {},
            onEnterSelectMode = {},
            onExitSelectMode = {},
            onUpdateCardInSelectList = {},
            onDeleteSelectedCards = {},
            flashcardList = listOf(
                Flashcard(
                    frontText = "frontText1",
                    backText = "backText1",
                    id = 0
                ),
                Flashcard(
                    frontText = "frontText2",
                    backText = "backText2",
                    id = 1
                )
            )
        )
    }
}