package dev.stralman.flashcardio.shared.data

import dev.stralman.flashcardio.shared.data.room.DeckDao
import dev.stralman.flashcardio.shared.model.Deck
import dev.stralman.flashcardio.shared.model.Flashcard
import dev.stralman.flashcardio.shared.model.FlashcardDeck
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeckRepository @Inject constructor(private val deckDao: DeckDao) {

    fun getDecks() = deckDao.getDecks()

    fun getDeck(deckId: Long) = deckDao.getDeck(deckId)

    suspend fun addDeck(deck: Deck) = deckDao.insert(deck)

    suspend fun deleteCards(cards: List<Flashcard>) = deckDao.deleteCards(cards)

    suspend fun deleteAllCardsInDeck(flashcardDeck: FlashcardDeck) =
        deckDao.deleteAllCardsInDeck(flashcardDeck.deck, flashcardDeck.cards)

    suspend fun addCardToDeck(card: Flashcard) = deckDao.insert(card)

    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: DeckRepository? = null

        fun getInstance(deckDao: DeckDao) =
            instance ?: synchronized(this) {
                instance ?: DeckRepository(deckDao).also { instance = it }
            }
    }
}