package dev.stralman.flashcardio.ui.flashcard

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandIn
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.stralman.flashcardio.data.Flashcard
import dev.stralman.flashcardio.ui.theme.AppTheme
import dev.stralman.flashcardio.ui.util.ThemePreview

@Composable
fun FlashcardItemCard(
    modifier: Modifier = Modifier,
    flashcard: Flashcard,
    onEnterSelectMode: () -> Unit,
    onExitSelectMode: () -> Unit,
    onUpdateCardInSelectList: (Flashcard) -> Unit,
    selectMode: Boolean,
) {
    Card(
        modifier = modifier
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        if (selectMode) {
                            onExitSelectMode()
                        } else {
                            onEnterSelectMode()
                        }
                    },
                    onTap = { onExitSelectMode() },
                )
            },

        ) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column {
                FlashcardItemCardRowItem(
                    title = "Front:",
                    body = flashcard.frontText
                )
                FlashcardItemCardRowItem(
                    title = "Back:",
                    body = flashcard.backText
                )
                FlashcardItemCardRowItem(
                    title = "Id:",
                    body = "${flashcard.id}"
                )
            }
            AnimatedVisibility(
                visible = selectMode,
                enter = expandIn(),
                exit = shrinkOut()
            ) {
                val isSelected = remember { mutableStateOf(false) }
                Checkbox(
                    checked = isSelected.value,
                    onCheckedChange = {
                        isSelected.value = !isSelected.value
                        onUpdateCardInSelectList(flashcard)
                    }
                )
            }
        }
    }
}

@Composable
fun FlashcardItemCardRowItem(
    modifier: Modifier = Modifier,
    title: String,
    body: String
) {
    Row {
        Text(
            text = title,
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            modifier = modifier.padding(5.dp)
        )
        Text(
            text = body,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Right,
            modifier = modifier.padding(5.dp)
        )
    }
}

@Composable
fun FlashcardItemCardList(
    modifier: Modifier = Modifier,
    flashcardList: List<Flashcard>,
    onEnterSelectMode: () -> Unit,
    onExitSelectMode: () -> Unit,
    onUpdateCardInSelectList: (Flashcard) -> Unit,
    selectMode: Boolean,
) {
    LazyColumn(modifier = modifier) {
        items(flashcardList) { flashcard ->
            FlashcardItemCard(
                flashcard = flashcard,
                modifier = Modifier.padding(8.dp),
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
fun FlashcardItemCard() {
    val selectMode by remember { mutableStateOf(false) }

    AppTheme {
        FlashcardItemCard(
            modifier = Modifier,
            flashcard = Flashcard(
                frontText = "frontText1",
                backText = "backText1",
            ),
            onEnterSelectMode = {},
            onExitSelectMode = {},
            onUpdateCardInSelectList = {},
            selectMode = selectMode,
        )
    }
}