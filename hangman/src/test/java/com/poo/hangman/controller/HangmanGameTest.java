package com.poo.hangman.controller;

import com.poo.hangman.model.Category;
import com.poo.hangman.model.Level;
import com.poo.hangman.model.RoundStatus;
import com.poo.hangman.model.Word;
import java.lang.reflect.Field;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas del ciclo de vida de HangmanGame sin constructores con parámetros.
 */
class HangmanGameTest {

    private HangmanGame newGame() {
        HangmanGame game = new HangmanGame();
        game.startGame();
        return game;
    }

    private Word currentWord(HangmanGame game) throws Exception {
        Field field = HangmanGame.class.getDeclaredField("currentWord");
        field.setAccessible(true);
        return (Word) field.get(game);
    }

    private void setWord(HangmanGame game, String value) throws Exception {
        Field field = HangmanGame.class.getDeclaredField("currentWord");
        field.setAccessible(true);
        field.set(game, new Word(value, Category.ANIMALES, "Palabra de prueba"));
    }

    private void winRound(HangmanGame game) throws Exception {
        String text = currentWord(game).getText();
        for (char letter : text.toCharArray()) {
            if (game.getRoundStatus() == RoundStatus.EN_CURSO) {
                assertTrue(game.guessLetter(letter));
            }
        }
        assertEquals(RoundStatus.GANADA, game.getRoundStatus());
    }

    @Test
    void constructorInitializesOnlyBasicState() throws Exception {
        HangmanGame game = new HangmanGame();
        assertAll(
            () -> assertEquals(0, game.getRoundNumber()),
            () -> assertFalse(game.isGameStarted()),
            () -> assertEquals(0, game.getWrongAttempts()),
            () -> assertEquals(RoundStatus.EN_CURSO, game.getRoundStatus()),
            () -> assertNull(game.getSelectedCategory()),
            () -> assertTrue(game.getCorrectLetters().isEmpty()),
            () -> assertTrue(game.getIncorrectLetters().isEmpty()),
            () -> assertThrows(IllegalStateException.class, () -> game.guessLetter('A')),
            () -> assertThrows(IllegalStateException.class, game::getMaskedWord)
        );
        assertNull(currentWord(game));
        Field correct = HangmanGame.class.getDeclaredField("correctLetters");
        Field incorrect = HangmanGame.class.getDeclaredField("incorrectLetters");
        correct.setAccessible(true);
        incorrect.setAccessible(true);
        assertNull(correct.get(game));
        assertNull(incorrect.get(game));
    }

    @Test
    void startsFirstRoundWithEasyLevelAndSevenAttempts() {
        HangmanGame game = newGame();
        assertTrue(game.isGameStarted());
        assertEquals(1, game.getRoundNumber());
        assertEquals(Level.FACIL, game.getCurrentLevel());
        assertEquals(7, game.getRemainingAttempts());
        assertEquals(RoundStatus.EN_CURSO, game.getRoundStatus());
        assertFalse(game.getMaskedWord().isBlank());
    }

    @Test
    void correctLetterRevealsWordWithoutConsumingAttempts() throws Exception {
        HangmanGame game = newGame();
        setWord(game, "GATO");
        assertTrue(game.guessLetter('a'));
        assertEquals("_ A _ _", game.getMaskedWord());
        assertEquals(7, game.getRemainingAttempts());
    }

    @Test
    void repeatedLetterDoesNotCountTwice() throws Exception {
        HangmanGame game = newGame();
        setWord(game, "GATO");
        assertFalse(game.guessLetter('z'));
        assertFalse(game.guessLetter('Z'));
        assertEquals(1, game.getWrongAttempts());
        assertEquals(1, game.getIncorrectLetters().size());
    }

    @Test
    void sevenErrorsEndGame() throws Exception {
        HangmanGame game = newGame();
        setWord(game, "GATO");
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
    void winningAllRoundsCompletesGame() throws Exception {
        HangmanGame game = newGame();
        winRound(game);
        assertTrue(game.advanceRound());
        assertEquals(Level.MEDIO, game.getCurrentLevel());
        assertEquals(7, game.getRemainingAttempts());
        winRound(game);
        assertTrue(game.advanceRound());
        assertEquals(Level.DIFICIL, game.getCurrentLevel());
        winRound(game);
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
    void selectedCategoryPersistsAcrossRounds() throws Exception {
        HangmanGame game = new HangmanGame();
        game.startGame(Category.COMIDAS);
        for (int i = 0; i < 3; i++) {
            assertEquals(Category.COMIDAS, game.getSelectedCategory());
            assertEquals(Category.COMIDAS, game.getCurrentWordCategory());
            winRound(game);
            if (i < 2) {
                assertTrue(game.advanceRound());
            }
        }
    }

    @Test
    void restartReturnsToInitialStateAndAllowsNewCategory() throws Exception {
        HangmanGame game = new HangmanGame();
        game.startGame(Category.ANIMALES);
        game.guessLetter('Z');
        game.restartGame();
        assertEquals(0, game.getRoundNumber());
        assertEquals(0, game.getWrongAttempts());
        assertNull(game.getSelectedCategory());
        assertNull(currentWord(game));
        assertFalse(game.isGameStarted());
        assertEquals(RoundStatus.EN_CURSO, game.getRoundStatus());
        assertTrue(game.getCorrectLetters().isEmpty());
        assertTrue(game.getIncorrectLetters().isEmpty());
        assertThrows(IllegalStateException.class, () -> game.guessLetter('A'));

        game.startGame(Category.COMIDAS);
        assertTrue(game.isGameStarted());
        assertEquals(1, game.getRoundNumber());
        assertEquals(Category.COMIDAS, game.getCurrentWordCategory());
    }

    @Test
    void rejectsInvalidLetters() {
        HangmanGame game = newGame();
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> game.guessLetter('1')),
            () -> assertThrows(IllegalArgumentException.class, () -> game.guessLetter(' '))
        );
    }

    @Test
    void returnedLetterCollectionsCannotBeModified() {
        HangmanGame game = newGame();
        assertThrows(UnsupportedOperationException.class,
            () -> game.getCorrectLetters().add('A'));
    }

    /**
     * Simula el flujo que realiza una vista Swing:
     * iniciar ronda fácil, intentar la letra A y refrescar los indicadores.
     * Se fija PERRO para que el resultado no dependa del azar.
     */
    @Test
    void interfaceSequenceWhenLetterAIsIncorrect() throws Exception {
        // 1. La vista crea el controlador y el jugador presiona "Iniciar".
        HangmanGame game = new HangmanGame();
        assertFalse(game.isGameStarted());
        game.startGame();

        // 2. Para esta prueba, la palabra fácil elegida será PERRO.
        setWord(game, "PERRO");

        // 3. La vista consulta los valores iniciales.
        assertAll(
            () -> assertTrue(game.isGameStarted()),
            () -> assertEquals(1, game.getRoundNumber()),
            () -> assertEquals(Level.FACIL, game.getCurrentLevel()),
            () -> assertEquals("_ _ _ _ _", game.getMaskedWord()),
            () -> assertEquals(0, game.getWrongAttempts()),
            () -> assertEquals(7, game.getRemainingAttempts()),
            () -> assertEquals(RoundStatus.EN_CURSO, game.getRoundStatus())
        );

        // 4. El jugador presiona el botón A.
        boolean correct = game.guessLetter('A');

        // 5. La vista actualiza la palabra, el contador, el dibujo y las letras.
        assertAll(
            () -> assertFalse(correct, "La palabra PERRO no contiene A"),
            () -> assertEquals("_ _ _ _ _", game.getMaskedWord()),
            () -> assertEquals(1, game.getWrongAttempts()),
            () -> assertEquals(6, game.getRemainingAttempts()),
            () -> assertTrue(game.getIncorrectLetters().contains('A')),
            () -> assertTrue(game.getCorrectLetters().isEmpty()),
            () -> assertEquals(RoundStatus.EN_CURSO, game.getRoundStatus()),
            () -> assertFalse(game.isGameLost()),
            () -> assertFalse(game.isGameWon())
        );

        // 6. Si vuelve a presionar A, no debe descontar otro intento.
        assertFalse(game.guessLetter('A'));
        assertEquals(1, game.getWrongAttempts());
        assertEquals(6, game.getRemainingAttempts());
    }

}
