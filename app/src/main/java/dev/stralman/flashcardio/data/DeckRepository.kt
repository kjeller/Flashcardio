package dev.stralman.flashcardio.data

import dev.stralman.flashcardio.data.room.DeckDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeckRepository @Inject constructor(private val deckDao: DeckDao) {

    fun getDecks() = deckDao.getDecks()

    fun getDeck(deckId: Long) = deckDao.getDeck(deckId)

    suspend fun addDeck(deck: Deck) = deckDao.insert(deck)

    suspend fun deleteDeck(deck: Deck) = deckDao.delete(deck)

    suspend fun addCardToDeck(card: Flashcard) = deckDao.insert(card)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: DeckRepository? = null

        fun getInstance(deckDao: DeckDao) =
            instance ?: synchronized(this) {
                instance ?: DeckRepository(deckDao).also { instance = it }
            }
    }
}