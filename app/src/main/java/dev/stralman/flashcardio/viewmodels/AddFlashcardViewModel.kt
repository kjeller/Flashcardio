package dev.stralman.flashcardio.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stralman.flashcardio.R
import dev.stralman.flashcardio.data.Deck
import dev.stralman.flashcardio.data.DeckRepository
import dev.stralman.flashcardio.data.Flashcard
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFlashcardViewModel @Inject internal constructor(
    private val deckRepository: DeckRepository
) : ViewModel() {

    var frontText by mutableStateOf("")
        private set

    var backText by mutableStateOf("")
        private set

    var flashcardType by mutableStateOf(FlashCardType.SIMPLE_TEXT_ITEM)
        private set

    fun updateFrontText(input: String) {
        frontText = input
    }

    fun updateBackText(input: String) {
        backText = input
    }

    fun updateFlashcardType(input: FlashCardType) {
        flashcardType = input
    }

    fun addCardToDeck() {
        viewModelScope.launch {
            val card = Flashcard(
                frontText = frontText,
                backText = backText,
            )
            deckRepository.addCardToDeck(card)
        }
    }

    fun deleteDeck(deck: Deck) {
        viewModelScope.launch {
            deckRepository.deleteDeck(deck)
        }
    }
}

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