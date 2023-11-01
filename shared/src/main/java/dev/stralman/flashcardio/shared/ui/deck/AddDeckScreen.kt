package dev.stralman.flashcardio.shared.ui.deck

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.stralman.flashcardio.shared.R
import dev.stralman.shared.ui.button.AddFloatingActionButton
import dev.stralman.flashcardio.shared.ui.theme.AppTheme
import dev.stralman.flashcardio.shared.ui.util.ThemePreview
import dev.stralman.flashcardio.shared.viewmodels.AddDeckViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun AddDeckScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onNavigateHome: () -> Unit,
    viewModel: AddDeckViewModel = hiltViewModel(),
) {
    AddDeckScreen(
        modifier = modifier,
        onNavigateBack = onNavigateBack,
        onNavigateHome = onNavigateHome,
        onAddCard = { scope, snackbarHostState ->
            scope.launch {
                viewModel.addDeck()
                snackbarHostState.showSnackbar("Successfully added deck to database")
                onNavigateHome()
            }
        },
        onUpdateDeckName = { deckName -> viewModel.updateDeckName(deckName) },
        deckName = viewModel.deckName,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddDeckScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onNavigateHome: () -> Unit,
    onAddCard: (CoroutineScope, SnackbarHostState) -> Unit,
    onUpdateDeckName: (String) -> Unit,
    deckName: String,
) {
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        },
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.add_card_deck_topbar_title),
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
                }
            )
        },
        floatingActionButton = {
            AddFloatingActionButton(
                modifier = modifier,
                onClick = { onAddCard(scope, snackbarHostState) },
                iconContentDescription = stringResource(id = R.string.deck_add_desc)
            )
        },
        floatingActionButtonPosition = FabPosition.End,

        ) { contentPadding ->
        // Screen content
        Box(modifier = Modifier.padding(contentPadding)) {
            Column(
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.selectableGroup(),
            ) {
                TextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),
                    value = deckName,
                    onValueChange = onUpdateDeckName,
                    label = {
                        Text(
                            text = stringResource(R.string.add_deck_name_field),
                            style = MaterialTheme.typography.labelMedium,
                        )
                    }
                )
                // TODO add option for toggling TTS for a deck
            }
        }
    }
}

@ThemePreview
@Composable
fun AddDeckScreenPreview() {
    AppTheme {
        AddDeckScreen(
            onNavigateBack = {},
            onNavigateHome = {},
            onAddCard = { _, _ -> },
            onUpdateDeckName = {},
            deckName = "Deck name",
        )
    }
}