package dev.stralman.flashcardio.model.flashcard

data class FlashcardDeck(
    val deckName: String,
    val cards: List<Flashcard>,
)