package dev.stralman.flashcardio.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stralman.flashcardio.data.Deck
import dev.stralman.flashcardio.data.DeckRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddDeckViewModel  @Inject internal constructor(
    private val deckRepository: DeckRepository
) : ViewModel() {

    fun addDeck(name: String) {
        viewModelScope.launch {
            val deck = Deck(name)
            deckRepository.addDeck(deck)
        }
    }

    fun deleteDeck(deck: Deck) {
        viewModelScope.launch {
            deckRepository.deleteDeck(deck)
        }
    }
}