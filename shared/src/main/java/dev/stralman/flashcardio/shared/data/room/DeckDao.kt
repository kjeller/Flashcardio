package dev.stralman.flashcardio.shared.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import dev.stralman.flashcardio.shared.model.Deck
import dev.stralman.flashcardio.shared.model.Flashcard
import dev.stralman.flashcardio.shared.model.FlashcardDeck
import kotlinx.coroutines.flow.Flow

@Dao
interface DeckDao {
    @Transaction
    @Query("SELECT * FROM decks ORDER BY deck_id")
    fun getDecks(): Flow<List<FlashcardDeck>>

    @Transaction
    @Query("SELECT * FROM decks WHERE deck_id = :deckId")
    fun getDeck(deckId: Long): Flow<FlashcardDeck>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(deck: Deck)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(card: Flashcard)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(decks: List<Flashcard>)

    @Delete
    suspend fun delete(deck: Deck)

    @Delete
    suspend fun deleteCards(cards: List<Flashcard>)

    @Delete
    suspend fun deleteAllCardsInDeck(deck: Deck, cards: List<Flashcard>)
}