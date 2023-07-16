package dev.stralman.flashcardio.ui

import android.content.Context
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

/**
 * List of screens for [FlashcardioApp]
 */
sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Flashcard : Screen("deck/{id}") {
        fun createRoute(id: String) = "deck/1/card/$id"
    }

    object AddDeckScreen : Screen("deck/1/add")
    object AddFlashCardScreen : Screen("deck/1/card/add")
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

    fun navigateToDeck(id: String, from: NavBackStackEntry) {
        // In order to discard duplicated navigation events, we check the Lifecycle
        if (from.lifecycleIsResumed()) {
            val id = Uri.encode(id)
            navController.navigate(Screen.Flashcard.createRoute(id))
        }
    }

    fun navigateToAddFlashcardScreen() {
        navController.navigate(Screen.AddFlashCardScreen.route)
    }

    fun navigateToAddDeckScreen() {
        navController.navigate(Screen.AddDeckScreen.route)
    }

    fun navigateBack() {
        navController.popBackStack()
    }
}

/**
 * If the lifecycle is not resumed it means this NavBackStackEntry already processed a nav event.
 *
 * This is used to de-duplicate navigation events.
 */
private fun NavBackStackEntry.lifecycleIsResumed() =
    this.lifecycle.currentState == Lifecycle.State.RESUMED