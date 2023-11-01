package dev.stralman.flashcardio.shared.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stralman.flashcardio.shared.data.DeckRepository
import dev.stralman.flashcardio.shared.model.Deck
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDeckViewModel @Inject internal constructor(
    private val deckRepository: DeckRepository
) : ViewModel() {

    var deckName by mutableStateOf("")
        private set

    fun updateDeckName(input: String) {
        deckName = input
    }

    fun addDeck() {
        viewModelScope.launch {
            val deck = Deck(deckName)
            deckRepository.addDeck(deck)
        }
    }
}