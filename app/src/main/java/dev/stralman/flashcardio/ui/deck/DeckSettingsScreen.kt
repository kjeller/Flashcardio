package dev.stralman.flashcardio.ui.deck

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.stralman.flashcardio.R
import dev.stralman.flashcardio.ui.util.ThemePreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeckSettingsScreen(
    modifier: Modifier = Modifier,
    onNavigateBack: () -> Unit,
    onNavigateAddFlashcard: () -> Unit,
    onNavigateRemoveFlashcard: () -> Unit,
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
                onClickSettingCard = { /* TODO */ },
                icon = {
                    Icon(
                        Icons.Rounded.Clear,
                        contentDescription = "",
                        modifier = it
                    )
                },
                text = stringResource(R.string.deck_settings_remove_card),
            )
        }
    }
}

@Composable
fun DeckSettingCard(
    modifier: Modifier,
    onClickSettingCard: () -> Unit,
    text: String,
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
}

@ThemePreview
@Composable
fun DeckSettingsScreen() {
    DeckSettingsScreen(
        onNavigateBack = {},
        onNavigateAddFlashcard = {},
        onNavigateRemoveFlashcard = {},
    )
}
