package dev.stralman.flashcardio.shared.ui.flashcard

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import dev.stralman.flashcardio.shared.R
import dev.stralman.flashcardio.shared.model.FlashcardDeck
import dev.stralman.flashcardio.shared.data.FakeRepository
import dev.stralman.flashcardio.shared.ui.theme.AppTheme
import dev.stralman.flashcardio.shared.ui.util.ThemePreview
import dev.stralman.flashcardio.shared.viewmodels.FlashcardViewModel

enum class CardFace {
    FRONT {
        override fun next(): CardFace {
            return BACK
        }
    },
    BACK {
        override fun next(): CardFace {
            return FRONT
        }
    };

    abstract fun next(): CardFace
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCard(
    modifier: Modifier = Modifier,
    cardFace: CardFace,
    onClick: (CardFace) -> Unit,
    back: @Composable (modifier: Modifier) -> Unit = {},
    front: @Composable (modifier: Modifier) -> Unit = {},
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier.fillMaxSize(),
            onClick = { onClick(cardFace) },
        ) {
            Box(
                Modifier.fillMaxSize()
            ) {
                when (cardFace) {
                    CardFace.FRONT -> {
                        front(
                            Modifier.align(Alignment.Center)
                        )
                    }

                    CardFace.BACK -> {
                        back(
                            Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun FlashCardTextItem(
    modifier: Modifier = Modifier,
    front: String,
    back: String,
    cardFace: CardFace = CardFace.FRONT,
    onClick: (CardFace) -> Unit,
) {
    FlashCard(
        cardFace = cardFace,
        onClick = { onClick(cardFace) },
        front = {
            Text(
                text = front,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = it.padding(16.dp),
            )
        },
        back = {
            Text(
                text = back,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center,
                modifier = it.padding(16.dp),
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun FlashCardScreen(
    modifier: Modifier = Modifier,
    flashcardDeck: FlashcardDeck,
    onNavigateBack: () -> Unit,
    onNavigateToDeckSettings: () -> Unit,
) {
    val pagerState = rememberPagerState(pageCount = {
        flashcardDeck.cards.size
    })

    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = {
                    var titleText = "${flashcardDeck.deck.name}:"

                    titleText += if (flashcardDeck.cards.isNotEmpty()) {
                        " ${pagerState.currentPage + 1}/${flashcardDeck.cards.size}"
                    } else {
                        stringResource(R.string.flashcard_screen_empty_deck)
                    }
                    Text(
                        text = titleText,
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
                },
                actions = {
                    IconButton(
                        onClick = {
                            onNavigateToDeckSettings()
                        }
                    ) {
                        Icon(
                            Icons.Rounded.Settings,
                            contentDescription = "Settings"
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    // TODO text to speech(?)
                }
            ) {
                Icon(
                    Icons.Rounded.PlayArrow,
                    contentDescription = ""
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,

        ) { contentPadding ->
        val initialCardstate = CardFace.FRONT
        var cardState by remember { mutableStateOf(initialCardstate) }
        LaunchedEffect(pagerState) {
            // Collect from the a snapshotFlow reading the currentPage
            // reset cardstate to front
            snapshotFlow { pagerState.currentPage }.collect { page ->
                cardState = initialCardstate
            }
        }
        HorizontalPager(
            state = pagerState,
        ) { page ->
            Box(
                modifier = Modifier
                    .padding(contentPadding)
                    .padding(15.dp)
            ) {
                FlashCardTextItem(
                    front = flashcardDeck.cards[page].frontText,
                    back = flashcardDeck.cards[page].backText,
                    cardFace = cardState,
                    onClick = {
                        cardState = it.next()
                    },
                )
            }
        }
    }
}

@Composable
fun FlashCardScreen(
    modifier: Modifier = Modifier,
    viewModel: FlashcardViewModel = hiltViewModel(),
    onNavigateBack: () -> Unit,
    onNavigateToDeckSettings: () -> Unit,
) {
    val flashcardDeck = viewModel.flashcardDeck.observeAsState().value
    if (flashcardDeck != null) {
        FlashCardScreen(
            modifier = modifier,
            flashcardDeck = flashcardDeck,
            onNavigateBack = onNavigateBack,
            onNavigateToDeckSettings = onNavigateToDeckSettings,
        )
    }
}

@ThemePreview
@Composable
fun FlashCardScreenPreview() {
    AppTheme {
        FlashCardScreen(
            flashcardDeck = FakeRepository().getFlashCardDeckMap()[0],
            onNavigateBack = {},
            onNavigateToDeckSettings = {},
        )
    }
}
