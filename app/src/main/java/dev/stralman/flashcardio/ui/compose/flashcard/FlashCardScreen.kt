package dev.stralman.flashcardio.ui.compose.flashcard

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

enum class CardFace {
    FRONT {
        override fun next(): CardFace {
            return BACK
        }
    },
    BACK{
        override fun next(): CardFace {
            return FRONT
        }
    };

    abstract fun next() : CardFace
}

@Composable
fun FlashCardTextItem(
    modifier: Modifier = Modifier,
    front: String,
    back: String,
    cardFace: CardFace,
    onClick: (CardFace) -> Unit,
) {
    val fontSize = 30.sp
    FlashCard(
        cardFace = cardFace,
        onClick = { onClick(cardFace) },
        front = {
            Text(
                text = front,
                fontSize = fontSize,
                lineHeight = 116.sp,
                textAlign = TextAlign.Center,
                modifier = it,
            )
        },
        back = {
            Text(
                text = back,
                fontSize = fontSize,
                lineHeight = 116.sp,
                textAlign = TextAlign.Center,
                modifier = it,
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlashCard(
    modifier: Modifier = Modifier,
    cardFace: CardFace,
    onClick: (CardFace) -> Unit,
    back: @Composable (modifier: Modifier) -> Unit = {},
    front: @Composable (modifier: Modifier) -> Unit = {},
){
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

@Preview
@Composable
fun FlipCardTextItemPreview() {
    var state by remember {
        mutableStateOf(CardFace.FRONT)
    }
    FlashCardTextItem(
        front = "Front",
        back = "Back",
        cardFace = state,
        onClick = {
            state = it.next()
        },
    )
}
