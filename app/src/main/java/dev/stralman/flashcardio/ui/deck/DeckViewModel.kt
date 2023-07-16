package dev.stralman.flashcardio.ui.deck

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.stralman.flashcardio.data.FlashcardDeck
import dev.stralman.flashcardio.data.FlashcardDeckStore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class DeckViewModel @Inject constructor(
    private val flashcardDeckId: Long,
    private val flashcardDeckStore: FlashcardDeckStore,
) : ViewModel() {
    private val _state = MutableStateFlow(DeckViewState())

    val state: StateFlow<DeckViewState>
        get() = _state

    init {
        viewModelScope.launch {
            /* TODO Add logic here for viewmodel*/
        }
    }
}

data class DeckViewState(
    val decks: List<FlashcardDeck> = emptyList(),
)