package dev.stralman.flashcardio.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stralman.flashcardio.data.DeckRepository
import dev.stralman.flashcardio.data.Flashcard
import dev.stralman.flashcardio.data.FlashcardRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel for [Flashcard] list
 */
@HiltViewModel
class FlashcardViewModel @Inject internal constructor(
    savedStateHandle: SavedStateHandle,
    flashcardRepository: FlashcardRepository,
    deckRepository: DeckRepository,
) : ViewModel() {

    /**
     *  To cache this correctly this needs to match with [Destination.FlashcardScreen]
     */
    private val deckId: Long = savedStateHandle.get<String>(ID_SAVED_STATE_KEY)?.toLong()!!
    val deck = deckRepository.getDeck(deckId).asLiveData()

    fun addCardToDeck() {
        viewModelScope.launch {
            // TODO
        }
    }

    fun deleteCardFromDeck() {
        viewModelScope.launch {
            // TODO
        }
    }

    companion object {
        private const val ID_SAVED_STATE_KEY = "deckId"
    }
}