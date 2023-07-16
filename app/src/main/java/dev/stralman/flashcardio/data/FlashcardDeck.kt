package dev.stralman.flashcardio.data

import androidx.compose.runtime.Immutable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "flashcard_decks",
    indices = [
        Index("name", unique = true)
    ]
)
@Immutable
data class FlashcardDeck(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "id") val id: Long = 0,
    val name: String,
    val cards: List<Flashcard>,
)