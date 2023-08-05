package dev.stralman.flashcardio.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stralman.flashcardio.data.DeckRepository
import dev.stralman.flashcardio.data.FlashcardDeck
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeckSettingsViewModel @Inject internal constructor(
    savedStateHandle: SavedStateHandle,
    private val deckRepository: DeckRepository,
    ) : ViewModel() {

    /**
     *  To cache this correctly this needs to match with [Destination.FlashcardScreen]
     */
    val deckId: Long = savedStateHandle.get<String>(Constants.ID_SAVED_STATE_KEY)?.toLong()!!
    val flashcardDeck = deckRepository.getDeck(deckId).asLiveData()

    var showDialog by mutableStateOf(false)
        private set

    fun onOpenDialogClicked() {
        showDialog = true
    }

    fun onDialogConfirm() {
        showDialog = false
    }

    fun onDialogDismiss() {
        showDialog = false
    }

    fun deleteDeck(flashcardDeck: FlashcardDeck) {
        viewModelScope.launch {
            deckRepository.deleteAllCardsInDeck(flashcardDeck)
        }
    }
}