package dev.stralman.flashcardio.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.stralman.flashcardio.data.FakeRepository
import dev.stralman.flashcardio.ui.deck.AddDeckScreen
import dev.stralman.flashcardio.ui.deck.DeckScreen
import dev.stralman.flashcardio.ui.flashcard.AddFlashCardScreen
import dev.stralman.flashcardio.ui.flashcard.FlashCardScreen

@Composable
fun FlashcardioApp(
    appState: FlashcardioAppState = rememberFlashcardioAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Destination.HomeScreen.route
    ) {
        composable(Destination.HomeScreen.route) {
            DeckScreen(
                onNavigateToDeck = {
                    appState.onNavigateToDeck("${it.id}")
                },
                onNavigateToAddFlashcard = {
                    appState.onNavigateToAddFlashcardScreen()
                }
            ) {
                appState.onNavigateToAddDeckScreen()
            }
        }
        composable(Destination.FlashcardScreen.route,
            arguments = listOf(navArgument("deck_id") {
                type = NavType.StringType
            })) {
            it.arguments?.getString("deck_id")?.let {deck_id ->
                val deck_id = deck_id.toInt()
                FlashCardScreen(
                    flashCardDeck = FakeRepository().getFlashCardDeckMap()[deck_id],
                    onNavigateBack = {
                        appState.onNavigateBack()
                    }
                )
            }

        }
        composable(Destination.AddDeckScreen.route) {
            AddDeckScreen(
                onNavigateBack = {
                    appState.onNavigateBack()
                }
            )
        }
        composable(Destination.AddFlashCardScreen.route) {
            AddFlashCardScreen(
                onNavigateBack = {
                    appState.onNavigateBack()
                }
            )
        }
    }
}