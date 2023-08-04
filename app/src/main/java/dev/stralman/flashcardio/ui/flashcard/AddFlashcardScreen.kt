package dev.stralman.flashcardio.ui.flashcard

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.stralman.flashcardio.R
import dev.stralman.flashcardio.ui.util.ThemePreview
import dev.stralman.flashcardio.viewmodels.AddFlashcardViewModel
import dev.stralman.flashcardio.viewmodels.FlashCardType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFlashCardScreen(
    modifier: Modifier = Modifier,
    viewModel: AddFlashcardViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
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
                        text = stringResource(R.string.title_add_flashcard_to_deck),
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
            FloatingActionButton(
                onClick = {
                    viewModel.addCardToDeck()
                    onNavigateBack()
                }
            ) {
                Icon(
                    Icons.Rounded.Check,
                    contentDescription = stringResource(id = R.string.deck_add_desc)
                )
            }
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
                FlashCardType.values().forEach {flashcardType ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (viewModel.flashcardType == flashcardType),
                                onClick = { viewModel.updateFlashcardType(flashcardType) },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (viewModel.flashcardType == flashcardType),
                            onClick = { viewModel.updateFlashcardType(flashcardType) },
                        )
                        Text(
                            text = stringResource(id = flashcardType.getDescriptionResourceId()),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
                when (viewModel.flashcardType) {
                    FlashCardType.SIMPLE_TEXT_ITEM ->
                        AddFlashCardTextItem(
                            modifier = modifier,
                            viewModel = viewModel,
                        )

                    FlashCardType.RICH_TEXT_ITEM ->
                        AddFlashCardRichTextItem(
                            modifier = modifier
                        )
                }
            }
        }
    }
}

@Composable
fun AddFlashCardTextItem(
    modifier: Modifier = Modifier,
    viewModel: AddFlashcardViewModel,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        value = viewModel.frontText,
        onValueChange = { frontText -> viewModel.updateFrontText(frontText) },
        label = {
            Text(
                text = stringResource(R.string.add_flashcard_text_item_front_text),
                style = MaterialTheme.typography.labelMedium,
            )
        }
    )
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        value = viewModel.backText,
        onValueChange = { backText -> viewModel.updateBackText(backText) },
        label = {
            Text(
                text = stringResource(R.string.add_flashcard_text_item_back_text),
                style = MaterialTheme.typography.labelMedium,
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFlashCardRichTextItem(
    modifier: Modifier = Modifier,
) {
    Text("TODO")
}

@ThemePreview
@Composable
fun AddFlashCardScreenPreview() {
    AddFlashCardScreen(
        onNavigateBack = {}
    )
}