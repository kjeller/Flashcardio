package dev.stralman.flashcardio.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import dev.stralman.flashcardio.ui.deck.AddDeckScreen
import dev.stralman.flashcardio.ui.deck.DeckScreen
import dev.stralman.flashcardio.ui.deck.DeckSettingsScreen
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
                onNavigateToDeck = { flashcardDeck -> appState.onNavigateToDeck("${flashcardDeck.deck.id}") },
                onNavigateToAddDeck = { appState.onNavigateToAddDeckScreen() }
            )
        }
        composable(
            Destination.FlashcardScreen.route,
            arguments = listOf(navArgument("deckId") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("deckId")?.let {
                FlashCardScreen(
                    onNavigateBack = { appState.onNavigateBack() },
                    onNavigateToDeckSettings = { appState.onNavigateToDeckSettings(it) },
                )
            }
        }
        composable(
            Destination.AddDeckScreen.route,
        ) {
            AddDeckScreen(
                onNavigateBack = { appState.onNavigateBack() },
                onNavigateHome = { appState.onNavigateHome() },
            )
        }
        composable(
            Destination.AddFlashCardScreen.route,
            arguments = listOf(navArgument("deckId") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("deckId")?.let {
                AddFlashCardScreen(
                    onNavigateBack = { appState.onNavigateBack() },
                )
            }
        }
        composable(
            Destination.DeckSettingsScreen.route,
            arguments = listOf(navArgument("deckId") {
                type = NavType.StringType
            })
        ) {
            it.arguments?.getString("deckId")?.let {
                DeckSettingsScreen(
                    onNavigateBack = { appState.onNavigateBack() },
                    onNavigateAddFlashcard = { appState.onNavigateToAddFlashcardScreen(it) },
                    onNavigateHome = { appState.onNavigateHome() },
                )
            }
        }
    }
}