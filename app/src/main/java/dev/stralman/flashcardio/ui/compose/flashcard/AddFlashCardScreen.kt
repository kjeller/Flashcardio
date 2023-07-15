package dev.stralman.flashcardio.ui.compose.flashcard

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
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.dp
import dev.stralman.flashcardio.R
import dev.stralman.flashcardio.ui.compose.util.ThemePreview

enum class FlashCardType {
    SIMPLE_TEXT_ITEM {
        override fun getDescriptionResourceId(): Int {
            return R.string.flashcardtype_simple_text_enum_desc
        }
    },
    RICH_TEXT_ITEM {
        override fun getDescriptionResourceId(): Int {
            return R.string.flashcardtype_rich_text_enum_desc
        }
    };

    abstract fun getDescriptionResourceId(): Int
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFlashCardScreen(
    modifier: Modifier = Modifier
) {
    var state by remember { mutableStateOf(FlashCardType.SIMPLE_TEXT_ITEM) }

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
            )
        },
        floatingActionButton = {
            FloatingActionButton(

                onClick = { /* ... */ }) {
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
                FlashCardType.values().forEach {
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .height(56.dp)
                            .selectable(
                                selected = (state == it),
                                onClick = { state = it },
                                role = Role.RadioButton
                            )
                            .padding(horizontal = 16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = state == it,
                            onClick = { state = it },
                            modifier = Modifier.semantics {
                                contentDescription = "Localized Description"
                            }
                        )
                        Text(
                            text = stringResource(id = it.getDescriptionResourceId()),
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
                when (state) {
                    FlashCardType.SIMPLE_TEXT_ITEM ->
                        AddFlashCardTextItem(
                            modifier = modifier
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddFlashCardTextItem(
    modifier: Modifier = Modifier,
) {
    var frontText by remember { mutableStateOf("") }
    var backText by remember { mutableStateOf("") }
    TextField(
        modifier = Modifier.padding(20.dp),
        value = frontText,
        onValueChange = { frontText = it },
        label = {
            Text(
                text = stringResource(R.string.add_flashcard_text_item_front_text),
                style = MaterialTheme.typography.labelMedium,
            )
        }
    )
    TextField(
        modifier = Modifier.padding(20.dp),
        value = backText,
        onValueChange = { backText = it },
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
    AddFlashCardScreen()
}