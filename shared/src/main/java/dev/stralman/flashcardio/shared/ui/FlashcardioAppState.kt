package dev.stralman.flashcardio.shared.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * List of destinations for [FlashcardioApp]
 */
sealed class Destination(val route: String) {
    object HomeScreen : Destination("home")
    object FlashcardScreen : Destination("deck/{deckId}") {
        fun createRoute(id: String) = "deck/$id"
    }

    object DeckSettingsScreen : Destination("deck/{deckId}/settings") {
        fun createRoute(id: String) = "deck/$id/settings"
    }

    object ModifyFlashcardsScreen : Destination("deck/{deckId}/modifyCards") {
        fun createRoute(id: String) = "deck/$id/modifyCards"
    }

    object AddDeckScreen : Destination("deck/add")
    object AddFlashCardScreen : Destination("deck/{deckId}/add") {
        fun createRoute(id: String) = "deck/$id/add"
    }
}

@Composable
fun rememberFlashcardioAppState(
    navController: NavHostController = rememberNavController(),
    context: Context = LocalContext.current
) = remember(navController, context) {
    FlashcardioAppState(navController, context)
}

class FlashcardioAppState(
    val navController: NavHostController,
    private val context: Context
) {

    fun onNavigateHome() {
        navController.navigate(Destination.HomeScreen.route)
    }

    fun onNavigateToDeck(id: String) {
        navController.navigate(Destination.FlashcardScreen.createRoute(id))
    }

    fun onNavigateToDeckSettings(id: String) {
        navController.navigate(Destination.DeckSettingsScreen.createRoute(id))
    }

    fun onNavigateToModifyFlashcardsInDeck(id: String) {
        navController.navigate(Destination.ModifyFlashcardsScreen.createRoute(id))
    }

    fun onNavigateToAddFlashcardScreen(id: String) {
        navController.navigate(Destination.AddFlashCardScreen.createRoute(id))
    }

    fun onNavigateToAddDeckScreen() {
        navController.navigate(Destination.AddDeckScreen.route)
    }

    fun onNavigateBack() {
        navController.popBackStack()
    }
}