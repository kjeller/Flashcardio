package dev.stralman.flashcardio.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "decks",
)
data class Deck(
    @ColumnInfo(name = "deck_name")
    val name: String,

    val cards: List<Flashcard> = emptyList(),

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "deck_id")
    val id: Long = 0
)