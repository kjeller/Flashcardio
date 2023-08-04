package dev.stralman.flashcardio.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "cards",
)
data class Flashcard(
    @ColumnInfo(name = "card_front")
    val frontText: String,

    @ColumnInfo(name = "card_back")
    val backText: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "card_id")
    val id: Long = 0,

    @ColumnInfo(name = "deck_id")
    val deckId: Long = -1,
) {
    override fun toString() = "front: $frontText, back: $backText"
}