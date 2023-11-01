package dev.stralman.flashcardio.shared.ui.deck

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.stralman.flashcardio.shared.R
import dev.stralman.flashcardio.shared.ui.theme.AppTheme
import dev.stralman.flashcardio.shared.ui.util.ThemePreview
import dev.stralman.flashcardio.shared.viewmodels.DeckSettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckSettingsScreen(
    modifier: Modifier = Modifier,
    viewModel: DeckSettingsViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateAddFlashcard: () -> Unit,
    onNavigateHome: () -> Unit,
    onNavigateToModifyFlashcards: () -> Unit,
) {
    val flashcardDeck = viewModel.flashcardDeck.observeAsState().value

    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.deck_settings_topbar_title),
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
    ) { contentPadding ->
        // Screen content
        Column(modifier = Modifier.padding(contentPadding)) {
            DeckSettingCard(
                modifier = modifier,
                onClickSettingCard = { onNavigateAddFlashcard() },
                dialog = {},
                icon = {
                    Icon(
                        Icons.Rounded.Add,
                        contentDescription = "",
                        modifier = it
                    )
                },
                text = stringResource(R.string.deck_settings_add_card),
            )
            DeckSettingCard(
                modifier = modifier,
                onClickSettingCard = { onNavigateToModifyFlashcards() },
                dialog = {},
                icon = {
                    Icon(
                        Icons.Rounded.Edit,
                        contentDescription = "",
                        modifier = it
                    )
                },
                text = stringResource(R.string.deck_settings_modify_cards),
            )
            DeckSettingCard(
                modifier = modifier,
                onClickSettingCard = {
                    viewModel.onOpenDialogClicked()
                },
                dialog = {
                    if (viewModel.showDialog) {
                        AlertDialog(
                            onDismissRequest = {
                                // Dismiss the dialog when the user clicks outside the dialog or on the back
                                // button. If you want to disable that functionality, simply use an empty
                                // onDismissRequest.
                                viewModel.onDialogDismiss()
                            },
                            icon = { Icon(Icons.Filled.Delete, contentDescription = null) },
                            title = {
                                Text(text = "Remove deck: ${flashcardDeck?.deck?.name}")
                            },
                            text = {
                                Text(
                                    stringResource(R.string.deck_settings_remove_deck_dialog)
                                )
                            },
                            confirmButton = {
                                TextButton(
                                    onClick = {
                                        if (flashcardDeck != null) {
                                            viewModel.deleteDeck(flashcardDeck)
                                        } else {
                                            // TODO show failure snack message
                                        }
                                        viewModel.onDialogConfirm()
                                        onNavigateHome()
                                    }
                                ) {
                                    Text(stringResource(R.string.dialog_confirm))
                                }
                            },
                            dismissButton = {
                                TextButton(
                                    onClick = {
                                        viewModel.onDialogDismiss()
                                    }
                                ) {
                                    Text(stringResource(R.string.dialog_cancel))
                                }
                            }
                        )
                    }
                },
                icon = {
                    Icon(
                        Icons.Rounded.Clear,
                        contentDescription = "",
                        modifier = it
                    )
                },
                text = stringResource(R.string.deck_settings_remove_deck),
            )
        }
    }
}


@Composable
fun DeckSettingCard(
    modifier: Modifier,
    onClickSettingCard: () -> Unit,
    text: String,
    dialog: @Composable (modifier: Modifier) -> Unit,
    icon: @Composable (modifier: Modifier) -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickSettingCard() }
            .padding(10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        icon(
            modifier.padding(5.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Left,
        )
    }
    dialog(modifier)
}

@ThemePreview
@Composable
fun DeckSettingsScreen() {
    AppTheme {
        DeckSettingsScreen(
            viewModel = hiltViewModel(),
            onNavigateBack = {},
            onNavigateAddFlashcard = {},
            onNavigateHome = {},
            onNavigateToModifyFlashcards = {},
        )
    }
}
