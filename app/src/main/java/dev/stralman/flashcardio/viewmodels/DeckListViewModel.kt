package dev.stralman.flashcardio.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stralman.flashcardio.data.Deck
import dev.stralman.flashcardio.data.DeckRepository
import dev.stralman.flashcardio.data.FlashcardDeck
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DeckListViewModel @Inject internal constructor(
    deckRepository: DeckRepository
) : ViewModel() {
    val flashcardDeckList: Flow<List<FlashcardDeck>> =
        deckRepository.getDecks()
}