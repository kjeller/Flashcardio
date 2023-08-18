package dev.stralman.flashcardio.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stralman.flashcardio.data.DeckRepository
import dev.stralman.flashcardio.data.Flashcard
import dev.stralman.flashcardio.data.FlashcardRepository
import dev.stralman.flashcardio.viewmodels.Constants.Companion.ID_SAVED_STATE_KEY
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * The ViewModel for [Flashcard] list
 */
@HiltViewModel
class FlashcardViewModel @Inject internal constructor(
    savedStateHandle: SavedStateHandle,
    private val deckRepository: DeckRepository,
    private val flashcardRepository: FlashcardRepository,
) : ViewModel() {

    /**
     *  To cache this correctly this needs to match with [Destination.FlashcardScreen]
     */
    private val deckId: Long = savedStateHandle.get<String>(ID_SAVED_STATE_KEY)?.toLong()!!
    val flashcardDeck = deckRepository.getDeck(deckId).asLiveData()
    val selectedList = mutableListOf<Flashcard>()
    val selectMode = mutableStateOf(false)


    fun updateList(flashcard: Flashcard) {
        if (flashcard in selectedList) {
            selectedList.remove(flashcard)
        } else {
            selectedList.add(flashcard)
        }
    }

    fun updateSelectMode(selectMode: Boolean) {
        this.selectMode.value = selectMode
    }

    fun deleteFlashcardList(flashcards: List<Flashcard>) {
        viewModelScope.launch {
            // TODO add backend DAO calls for this
        }
    }

}