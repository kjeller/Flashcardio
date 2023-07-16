package dev.stralman.flashcardio.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import dev.stralman.flashcardio.ui.deck.AddDeckScreen
import dev.stralman.flashcardio.ui.deck.DeckScreen
import dev.stralman.flashcardio.ui.flashcard.AddFlashCardScreen

@Composable
fun FlashcardioApp(
    appState: FlashcardioAppState = rememberFlashcardioAppState()
) {
    NavHost(
        navController = appState.navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) { backStackEntry ->
            DeckScreen(
                navigateToDeck = { id ->
                    appState.navigateToDeck(id, backStackEntry)
                },
                navigateToAddFlashcard = { id ->
                    appState.navigateToAddFlashcardScreen()
                },
                navigateToAddDeck = { id ->
                    appState.navigateToAddDeckScreen()
                }
            )
        }
        composable(Screen.AddDeckScreen.route) { backStackEntry ->
            AddDeckScreen(
                navigateBack = {
                    appState.navigateBack()
                }
            )
        }
        composable(Screen.AddFlashCardScreen.route) { backStackEntry ->
            AddFlashCardScreen(
                navigateBack = {
                    appState.navigateBack()
                }
            )
        }
    }
}