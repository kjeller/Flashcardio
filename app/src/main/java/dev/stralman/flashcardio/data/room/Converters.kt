package dev.stralman.flashcardio.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dev.stralman.flashcardio.data.Flashcard
import dev.stralman.flashcardio.data.FlashcardDeck
import java.util.Calendar


class Converters {

    @TypeConverter
    fun flashcardDeckFromJson(jsonString: String): FlashcardDeck {
        val type = object : TypeToken<FlashcardDeck>() {}.type
        return Gson().fromJson(jsonString, type)
    }
    @TypeConverter
    fun flashcardFromJson(jsonString: String): Flashcard {
        val type = object : TypeToken<Flashcard>() {}.type
        return Gson().fromJson(jsonString, type)
    }

    @TypeConverter
    fun flashcardListFromJson(jsonString: String): List<Flashcard> {
        if (jsonString.isEmpty()) {
            return emptyList()
        }
        val type = object : TypeToken<List<Flashcard>>() {}.type
        return Gson().fromJson(jsonString, type)
    }

    @TypeConverter
    fun flashcardListToJson(flashcardList: List<Flashcard>): String {
        return Gson().toJson(flashcardList)
    }

    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis

    @TypeConverter
    fun datestampToCalendar(value: Long): Calendar =
        Calendar.getInstance().apply { timeInMillis = value }
}