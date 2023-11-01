package dev.stralman.flashcardio.shared.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stralman.flashcardio.shared.model.Flashcard
import dev.stralman.flashcardio.shared.data.DeckRepository
import dev.stralman.flashcardio.shared.viewmodels.Constants.Companion.ID_SAVED_STATE_KEY
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel for [Flashcard] list
 */
@HiltViewModel
class FlashcardViewModel @Inject internal constructor(
    savedStateHandle: SavedStateHandle,
    private val deckRepository: DeckRepository,
) : ViewModel() {

    /**
     *  To cache this correctly this needs to match with [Destination.FlashcardScreen]
     */
    private val deckId: Long = savedStateHandle.get<String>(ID_SAVED_STATE_KEY)?.toLong()!!
    val flashcardDeck = deckRepository.getDeck(deckId).asLiveData()
    val selectedCards = mutableListOf<Flashcard>()
    val selectMode = mutableStateOf(false)


    fun updateList(flashcard: Flashcard) {
        if (flashcard in selectedCards) {
            selectedCards.remove(flashcard)
        } else {
            selectedCards.add(flashcard)
        }
    }

    fun updateSelectMode(selectMode: Boolean) {
        this.selectMode.value = selectMode
    }

    fun deleteSelectedFlashcards() {
        viewModelScope.launch {
            deckRepository.deleteCards(selectedCards)
        }
    }
}