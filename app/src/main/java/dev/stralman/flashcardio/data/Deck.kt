package dev.stralman.flashcardio.data

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.Relation
import java.util.Calendar

@Entity(
    tableName = "decks",
)
data class Deck(
    @ColumnInfo(name = "deck_name")
    val name: String,

    /*@ColumnInfo(name = "deck_create_date")
    val date: Calendar = Calendar.getInstance(),*/

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "deck_id")
    var id: Long = 0,
)

data class FlashcardDeck(
    @Embedded
    val deck: Deck,
    @Relation(parentColumn = "deck_id", entityColumn = "deck_id")
    val cards: List<Flashcard> = emptyList(),
)