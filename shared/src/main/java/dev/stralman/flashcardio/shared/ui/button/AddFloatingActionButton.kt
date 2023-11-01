package dev.stralman.shared.ui.button

import androidx.compose.foundation.layout.imePadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import dev.stralman.flashcardio.shared.ui.theme.AppTheme
import dev.stralman.flashcardio.shared.ui.util.ThemePreview

@Composable
fun AddFloatingActionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    iconContentDescription: String = "",
) {
    FloatingActionButton(
        modifier = modifier.imePadding(),
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

