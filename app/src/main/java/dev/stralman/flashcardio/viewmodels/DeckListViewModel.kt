package dev.stralman.flashcardio.viewmodels

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.stralman.flashcardio.data.Deck
import dev.stralman.flashcardio.data.DeckRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class DeckListViewModel @Inject internal constructor(
    deckRepository: DeckRepository
) : ViewModel() {
    val deckList: Flow<List<Deck>> =
        deckRepository.getDecks()
}