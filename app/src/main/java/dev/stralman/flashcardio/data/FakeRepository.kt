package dev.stralman.flashcardio.data

class FakeRepository(
    private val flashCardDeckLists: List<FlashcardDeck> =
        listOf(
            FlashcardDeck(
                Deck(
                    name = "Korean common phrases (한글)",
                ),
                cards = listOf(
                    Flashcard(
                        frontText = "안녕하세요",
                        backText = "Hello (annyeonghaseyo)"
                    ),
                )
            ),
            FlashcardDeck(
                Deck(
                    name = "NATO Phonetic Alphabet",
                ),
                cards = listOf(
                    Flashcard(
                        frontText = "A",
                        backText = "Alpha"
                    ),
                    Flashcard(
                        frontText = "B",
                        backText = "Bravo"
                    ),
                    Flashcard(
                        frontText = "C",
                        backText = "Charlie"
                    )
                )
            )
        )
) {

    fun getFlashCardDeckMap(): List<FlashcardDeck> {
        return flashCardDeckLists
    }
}

