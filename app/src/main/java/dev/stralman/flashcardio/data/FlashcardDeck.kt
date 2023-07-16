package dev.stralman.flashcardio.data

data class FlashcardDeck(
    val deckName: String,
    val cards: List<Flashcard>,
)