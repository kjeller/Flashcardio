package dev.stralman.flashcardio.ui.button

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dev.stralman.flashcardio.ui.theme.AppTheme
import dev.stralman.flashcardio.ui.util.ThemePreview

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

