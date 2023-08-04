package dev.stralman.flashcardio.viewmodels

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stralman.flashcardio.data.DeckRepository
import dev.stralman.flashcardio.data.Flashcard
import dev.stralman.flashcardio.data.FlashcardDeck
import dev.stralman.flashcardio.viewmodels.Constants.Companion.ID_SAVED_STATE_KEY
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

}