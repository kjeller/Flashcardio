package dev.stralman.flashcardio.ui.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.stralman.flashcardio.ui.theme.AppTheme
import dev.stralman.flashcardio.ui.util.ThemePreview

@Composable
fun AddFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconContentDescription: String = "",
) {
    FloatingActionButton(
        modifier = modifier,
        onClick = onClick,
        content = {
            Icon(
                Icons.Rounded.Add,
                contentDescription = iconContentDescription
            )
        }
    )
}

@ThemePreview
@Composable
fun AddFloatingActionButtonPreview() {
    AppTheme {
        AddFloatingActionButton(
            onClick = {},
        )
    }
}

