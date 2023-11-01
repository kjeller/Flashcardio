package dev.stralman.flashcardio.shared.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stralman.flashcardio.shared.model.FlashcardDeck
import dev.stralman.flashcardio.shared.data.DeckRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DeckListViewModel @Inject internal constructor(
    deckRepository: DeckRepository
) : ViewModel() {
    val flashcardDeckList: Flow<List<FlashcardDeck>> =
        deckRepository.getDecks()
}