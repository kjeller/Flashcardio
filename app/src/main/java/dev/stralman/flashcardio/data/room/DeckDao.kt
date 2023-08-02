package dev.stralman.flashcardio.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import dev.stralman.flashcardio.data.Deck
import kotlinx.coroutines.flow.Flow

@Dao
interface DeckDao {
    @Query("SELECT * FROM decks ORDER BY deck_id")
    fun getDecks(): Flow<List<Deck>>

    @Query("SELECT * FROM decks WHERE deck_id = :deckId")
    fun getDeck(deckId: Long): Flow<Deck>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(decks: List<Deck>)
}