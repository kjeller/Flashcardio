package dev.stralman.flashcardio.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * List of destinations for [FlashcardioApp]
 */
sealed class Destination(val route: String) {
    object HomeScreen : Destination("home")
    object FlashcardScreen : Destination("deck/{deckId}") {
        fun createRoute(id : String) = "deck/$id"
    }
    object AddDeckScreen : Destination("deck/add")
    object AddFlashCardScreen : Destination("deck/{deckId}/add")
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

    fun onNavigateToDeck(id: String) {
        navController.navigate(Destination.FlashcardScreen.createRoute(id))
    }

    fun onNavigateToAddFlashcardScreen() {
        navController.navigate(Destination.AddFlashCardScreen.route)
    }

    fun onNavigateToAddDeckScreen() {
        navController.navigate(Destination.AddDeckScreen.route)
    }

    fun onNavigateBack() {
        navController.popBackStack()
    }
}