package dev.stralman.flashcardio.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import dev.stralman.flashcardio.data.FlashcardDeck
import dev.stralman.flashcardio.data.room.AppDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {
    override suspend fun doWork(): Result = withContext(Dispatchers.IO) {
        try {
            val filename = inputData.getString(KEY_FILENAME)
            if (filename != null) {
                applicationContext.assets.open(filename).use { inputStream ->
                    JsonReader(inputStream.reader()).use { jsonReader ->
                        val deckType = object : TypeToken<List<FlashcardDeck>>() {}.type
                        val deckList: List<FlashcardDeck> = Gson().fromJson(jsonReader, deckType)

                        val database = AppDatabase.getInstance(applicationContext)
                        var id: Long = 1
                        deckList.forEach { deck ->
                            deck.deck.id = id
                            database.deckDao().insert(deck.deck)

                            deck.cards.forEach { card ->
                                card.deckId = deck.deck.id
                            }
                            database.deckDao().insertAll(deck.cards)
                            id += 1
                        }

                        Result.success()
                    }
                }
            } else {
                Log.e(TAG, "Error seeding database - no valid filename")
                Result.failure()
            }
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
        const val KEY_FILENAME = "DECK_DATA_FILENAME"
    }
}