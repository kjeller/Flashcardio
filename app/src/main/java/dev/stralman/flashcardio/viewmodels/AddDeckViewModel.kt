package dev.stralman.flashcardio.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stralman.flashcardio.data.Deck
import dev.stralman.flashcardio.data.DeckRepository
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

    fun deleteDeck(deck: Deck) {
        viewModelScope.launch {
            deckRepository.deleteDeck(deck)
        }
    }
}