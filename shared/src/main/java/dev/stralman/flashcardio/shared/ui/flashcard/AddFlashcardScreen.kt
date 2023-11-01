package dev.stralman.flashcardio.shared.ui.flashcard

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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
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
import dev.stralman.flashcardio.shared.R
import dev.stralman.shared.ui.button.AddFloatingActionButton
import dev.stralman.flashcardio.shared.ui.theme.AppTheme
import dev.stralman.flashcardio.shared.ui.util.ThemePreview
import dev.stralman.flashcardio.shared.viewmodels.AddFlashcardViewModel
import dev.stralman.flashcardio.shared.viewmodels.FlashCardType

@Composable
fun AddFlashCardScreen(
    modifier: Modifier = Modifier,
    viewModel: AddFlashcardViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
) {
    AddFlashCardScreen(
        modifier = modifier,
        onNavigateBack = onNavigateBack,
        onAddCard = {
            viewModel.addCardToDeck()
            //onNavigateBack()
        },
        onUpdateFlashcardType = { flashCardType -> viewModel.updateFlashcardType(flashCardType) },
        onUpdateFrontText = { frontText -> viewModel.updateFrontText(frontText) },
        onUpdateBackText = { backText -> viewModel.updateBackText(backText) },
        flashcardType = viewModel.flashcardType,
        frontText = viewModel.frontText,
        backText = viewModel.backText,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFlashCardScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onAddCard: () -> Unit,
    onUpdateFlashcardType: (FlashCardType) -> Unit,
    onUpdateFrontText: (String) -> Unit,
    onUpdateBackText: (String) -> Unit,
    flashcardType: FlashCardType,
    frontText: String,
    backText: String,
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
            AddFloatingActionButton(
                modifier = modifier,
                onClick = onAddCard,
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
                FlashCardType.values().forEach {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (flashcardType == it),
                                onClick = { onUpdateFlashcardType(it) },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = (flashcardType == it),
                            onClick = { onUpdateFlashcardType(it) },
                        )
                        Text(
                            text = stringResource(id = it.getDescriptionResourceId()),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
                when (flashcardType) {
                    FlashCardType.SIMPLE_TEXT_ITEM ->
                        AddFlashCardTextItem(
                            modifier = modifier,
                            frontText = frontText,
                            backText = backText,
                            onUpdateFrontText = onUpdateFrontText,
                            onUpdateBackText = onUpdateBackText,
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
    frontText: String,
    backText: String,
    onUpdateFrontText: (String) -> Unit,
    onUpdateBackText: (String) -> Unit,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp),
        value = frontText,
        onValueChange = onUpdateFrontText,
        label = {
            Text(
                text = stringResource(R.string.add_flashcard_text_item_front_text),
                style = MaterialTheme.typography.labelMedium,
            )
        },
    )
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        value = backText,
        onValueChange = onUpdateBackText,
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
    AppTheme {
        AddFlashCardScreen(
            onNavigateBack = {},
            onAddCard = {},
            onUpdateFlashcardType = {},
            onUpdateFrontText = {},
            onUpdateBackText = {},
            flashcardType = FlashCardType.SIMPLE_TEXT_ITEM,
            frontText = "Front text",
            backText = "Back text",
        )
    }
}