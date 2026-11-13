package com.poo.hangman.controller;

import com.poo.hangman.model.Category;
import com.poo.hangman.model.Level;
import com.poo.hangman.model.Word;
import com.poo.hangman.model.WorkBank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas de las tres rondas del ahorcado, sin pistas ni interfaz Swing.
 * El banco de prueba devuelve palabras conocidas para evitar resultados
 * dependientes del azar.
 */
class HangmanGameTest {

    private static class FixedWordBank extends WorkBank {
        @Override
        public Word getRandomWord(Level level) {
            return switch (level) {
                case FACIL -> new Word("GATO", Category.ANIMALES, "Felino");
                case MEDIO -> new Word("CONEJO", Category.ANIMALES, "Orejas largas");
                case DIFICIL -> new Word("ELEFANTES", Category.ANIMALES, "Mamíferos");
            };
        }

        @Override
        public Word getRandomWord(Category category, Level level) {
            return switch (level) {
                case FACIL -> new Word("PERRO", category, "Animal");
                case MEDIO -> new Word("CABALLO", category, "Animal");
                case DIFICIL -> new Word("COCODRILO", category, "Animal");
            };
        }
    }

    private HangmanGame newGame() {
        return new HangmanGame(new FixedWordBank(), null);
    }

    private void winCurrentRound(HangmanGame game, String word) {
        for (char letter : word.toCharArray()) {
            if (game.getRoundStatus() == HangmanGame.RoundStatus.IN_PROGRESS) {
                assertTrue(game.guessLetter(letter));
            }
        }
        assertEquals(HangmanGame.RoundStatus.WON, game.getRoundStatus());
    }

    @Test
    void startsWithEasyRoundAndSevenAttempts() {
        HangmanGame game = newGame();
        assertAll(
            () -> assertEquals(1, game.getRoundNumber()),
            () -> assertEquals(Level.FACIL, game.getCurrentLevel()),
            () -> assertEquals(7, game.getRemainingAttempts()),
            () -> assertEquals("_ _ _ _", game.getMaskedWord()),
            () -> assertEquals(HangmanGame.RoundStatus.IN_PROGRESS, game.getRoundStatus())
        );
    }

    @Test
    void correctLetterRevealsPositionsWithoutConsumingAttempts() {
        HangmanGame game = newGame();
        assertTrue(game.guessLetter('a'));
        assertEquals("_ A _ _", game.getMaskedWord());
        assertEquals(7, game.getRemainingAttempts());
        assertTrue(game.getCorrectLetters().contains('A'));
    }

    @Test
    void repeatedLetterDoesNotCountTwice() {
        HangmanGame game = newGame();
        assertFalse(game.guessLetter('z'));
        assertFalse(game.guessLetter('Z'));
        assertEquals(1, game.getWrongAttempts());
        assertEquals(1, game.getIncorrectLetters().size());
    }

    @Test
    void wrongLettersCountUntilSevenAndEndGame() {
        HangmanGame game = newGame();
        for (char letter : "BDEFHIJ".toCharArray()) {
            assertFalse(game.guessLetter(letter));
        }
        assertEquals(0, game.getRemainingAttempts());
        assertTrue(game.isGameLost());
        assertEquals("GATO", game.getRevealedWord());
        assertThrows(IllegalStateException.class, () -> game.guessLetter('G'));
        assertThrows(IllegalStateException.class, game::advanceRound);
    }

    @Test
    void winningThreeRoundsCompletesGame() {
        HangmanGame game = newGame();
        winCurrentRound(game, "GATO");
        assertEquals("GATO", game.getRevealedWord());
        assertTrue(game.advanceRound());
        assertEquals(Level.MEDIO, game.getCurrentLevel());
        assertEquals(7, game.getRemainingAttempts());
        assertEquals(0, game.getCorrectLetters().size());

        winCurrentRound(game, "CONEJO");
        assertTrue(game.advanceRound());
        assertEquals(3, game.getRoundNumber());
        assertEquals(Level.DIFICIL, game.getCurrentLevel());

        winCurrentRound(game, "ELEFANTES");
        assertTrue(game.isGameWon());
        assertFalse(game.advanceRound());
    }

    @Test
    void cannotAdvanceBeforeWinning() {
        HangmanGame game = newGame();
        assertThrows(IllegalStateException.class, game::advanceRound);
        assertThrows(IllegalStateException.class, game::getRevealedWord);
    }

    @Test
    void selectedCategoryIsKeptAcrossRounds() {
        HangmanGame game = new HangmanGame(new FixedWordBank(), Category.COMIDAS);
        assertEquals(Category.COMIDAS, game.getSelectedCategory());
        assertEquals(Category.COMIDAS, game.getCurrentWordCategory());
        winCurrentRound(game, "PERRO");
        assertTrue(game.advanceRound());
        assertEquals(Category.COMIDAS, game.getCurrentWordCategory());
        winCurrentRound(game, "CABALLO");
        assertTrue(game.advanceRound());
        assertEquals(Category.COMIDAS, game.getCurrentWordCategory());
    }

    @Test
    void restartResetsProgressAfterLosing() {
        HangmanGame game = newGame();
        game.guessLetter('Z');
        game.restartGame();
        assertEquals(1, game.getRoundNumber());
        assertEquals(0, game.getWrongAttempts());
        assertEquals(0, game.getIncorrectLetters().size());
        assertEquals("_ _ _ _", game.getMaskedWord());
    }

    @Test
    void rejectsInvalidLettersAndNullBank() {
        HangmanGame game = newGame();
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> game.guessLetter('1')),
            () -> assertThrows(IllegalArgumentException.class, () -> game.guessLetter(' ')),
            () -> assertThrows(IllegalArgumentException.class, () -> new HangmanGame((WorkBank) null, null)),
            () -> assertThrows(IllegalArgumentException.class, () -> new HangmanGame((Category) null))
        );
    }

    @Test
    void returnedLetterCollectionsCannotBeModified() {
        HangmanGame game = newGame();
        game.guessLetter('G');
        assertThrows(UnsupportedOperationException.class, () -> game.getCorrectLetters().add('A'));
    }
}
