package dev.stralman.flashcardio.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import dev.stralman.flashcardio.data.Flashcard
import kotlinx.coroutines.flow.Flow

@Dao
interface FlashcardDao {
    @Query("SELECT * FROM cards ORDER BY card_id")
    fun getFlashcards(): Flow<List<Flashcard>>

    @Query("SELECT * FROM cards WHERE card_id = :flashcardId")
    fun getFlashcard(flashcardId: Long): Flow<Flashcard>

    @Transaction
    @Query("SELECT * FROM cards WHERE card_id IN (SELECT DISTINCT(deck_id) FROM decks)")
    fun getFlashCardsInDeck(): Flow<List<Flashcard>>

    @Insert
    suspend fun insertFlashcardToDeck(flashcard: Flashcard): Long

    @Delete
    suspend fun deleteFlashcardFromDeck(flashcard: Flashcard)
}