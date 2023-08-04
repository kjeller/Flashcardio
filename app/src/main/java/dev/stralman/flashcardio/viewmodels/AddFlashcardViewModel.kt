package dev.stralman.flashcardio.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stralman.flashcardio.data.Deck
import dev.stralman.flashcardio.data.DeckRepository
import dev.stralman.flashcardio.data.Flashcard
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddFlashcardViewModel @Inject internal constructor(
    private val deckRepository: DeckRepository
) : ViewModel() {

    fun addCardToDeck(frontText: String, backText: String) {
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