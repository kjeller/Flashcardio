package dev.stralman.flashcardio.di

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.stralman.flashcardio.data.room.AppDatabase
import dev.stralman.flashcardio.data.room.DeckDao
import dev.stralman.flashcardio.data.room.FlashcardDao
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return AppDatabase.getInstance(context)
    }

    @Provides
    fun provideDeckDao(appDatabase: AppDatabase): FlashcardDao {
        return appDatabase.flashcardDao()
    }

    @Provides
    fun provideGardenPlantingDao(appDatabase: AppDatabase): DeckDao {
        return appDatabase.deckDao()
    }
}