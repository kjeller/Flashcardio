package dev.stralman.flashcardio.ui.flashcard

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.stralman.flashcardio.data.FakeRepository
import dev.stralman.flashcardio.data.FlashcardDeck
import dev.stralman.flashcardio.ui.theme.AppTheme
import dev.stralman.flashcardio.ui.util.ThemePreview

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
    flashCardDeck: FlashcardDeck,
    onNavigateBack: () -> Unit,
) {
    var state by remember {
        mutableStateOf(CardFace.FRONT)
    }
    val pageState = rememberPagerState(0)
    Scaffold(
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .background(MaterialTheme.colorScheme.background),
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "${flashCardDeck.name}: ${pageState.currentPage + 1}/${flashCardDeck.cards.size}",
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
        HorizontalPager(
            pageCount = flashCardDeck.cards.size,
            state = pageState,
        ) { page ->
            Box(modifier = Modifier
                .padding(contentPadding)
                .padding(15.dp)) {
                FlashCardTextItem(
                    front = flashCardDeck.cards[page].frontText,
                    back = flashCardDeck.cards[page].backText,
                    cardFace = state,
                    onClick = {
                        state = it.next()
                    },
                )
            }
        }

    }
}

@ThemePreview
@Composable
fun FlashCardScreenPreview() {
    AppTheme {
        FlashCardScreen(
            flashCardDeck = FakeRepository().getFlashCardDeckMap()[0],
            onNavigateBack = {},
        )
    }
}
