package dev.stralman.flashcardio.data

import dev.stralman.flashcardio.data.room.FlashcardDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlashcardRepository @Inject constructor(private val flashcardDao: FlashcardDao) {

    fun getFlashcards() = flashcardDao.getFlashCardsInDeck()

    fun getFlashcard(flashcardId: Long) = flashcardDao.getFlashcard(flashcardId)

    companion object {

        // For Singleton instantiation
        @Volatile private var instance: FlashcardRepository? = null

        fun getInstance(flashcardDao: FlashcardDao) =
            instance ?: synchronized(this) {
                instance ?: FlashcardRepository(flashcardDao).also { instance = it }
            }
    }
}