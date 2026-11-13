package com.poo.hangman.controller;

import com.poo.hangman.model.Category;
import com.poo.hangman.model.Level;
import com.poo.hangman.model.RoundStatus;
import com.poo.hangman.model.Word;
import com.poo.hangman.model.WorkBank;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Controla una partida del ahorcado de tres rondas sin implementar pistas.
 *
 * <p>Las rondas avanzan de FACIL a MEDIO y DIFICIL. El jugador tiene siete
 * errores permitidos en cada ronda y solo puede avanzar tras acertar la
 * palabra. La selección de categoría (fija o aleatoria) se conserva durante
 * toda la partida.</p>
 *
 * @author Grupo 1
 */
public class HangmanGame {

    public static final int MAX_ERRORS = 7;
    public static final int TOTAL_ROUNDS = 3;

    private final WorkBank wordBank;
    private Category selectedCategory;
    private Set<Character> correctLetters;
    private Set<Character> incorrectLetters;

    private Word currentWord;
    private int roundNumber;
    private int wrongAttempts;
    private RoundStatus roundStatus;

    /**
     * Inicializa únicamente el estado base, sin seleccionar una palabra.
     * La primera ronda se prepara mediante startGame().
     */
    public HangmanGame() {
        this.wordBank = new WorkBank();
        this.selectedCategory = null;
        this.currentWord = null;
        this.correctLetters = null;
        this.incorrectLetters = null;
        this.roundNumber = 0;
        this.wrongAttempts = 0;
        this.roundStatus = RoundStatus.EN_CURSO;
    }

    /**
     * Inicia una partida con categoría aleatoria en cada ronda.
     */
    public void startGame() {
        startGame(null);
    }

    /**
     * Inicia una partida desde la ronda fácil con categoría fija o aleatoria.
     *
     * @param category categoría fija; null significa cualquier categoría
     */
    public void startGame(Category category) {
        this.selectedCategory = category;
        this.roundNumber = 1;
        startRound();
    }

    /**
     * Reinicia la partida conservando la modalidad de categoría seleccionada.
     */
    public void restartGame() {
        startGame(this.selectedCategory);
    }

    /**
     * Inicia o reinicia el estado de la ronda actual.
     */
    private void startRound() {
        if (correctLetters == null) {
            correctLetters = new LinkedHashSet<>();
        } else {
            correctLetters.clear();
        }
        if (incorrectLetters == null) {
            incorrectLetters = new LinkedHashSet<>();
        } else {
            incorrectLetters.clear();
        }
        wrongAttempts = 0;
        roundStatus = RoundStatus.EN_CURSO;
        Level level = getCurrentLevel();
        currentWord = selectedCategory == null
                ? wordBank.getRandomWord(level)
                : wordBank.getRandomWord(selectedCategory, level);
    }

    /**
     * Comprueba la letra ingresada y actualiza el estado de la ronda.
     * Las letras repetidas no suman errores.
     *
     * @param letter letra del jugador (una sola letra del alfabeto)
     * @return true cuando la letra está en la palabra; false si no está
     * @throws IllegalArgumentException si no es una letra válida
     * @throws IllegalStateException si la ronda ya terminó
     */
    public boolean guessLetter(char letter) {
        requireStarted();
        if (roundStatus != RoundStatus.EN_CURSO) {
            throw new IllegalStateException("La ronda ya terminó");
        }
        char normalized = Character.toUpperCase(letter);
        if (!Character.isLetter(normalized)) {
            throw new IllegalArgumentException("Debes ingresar una letra");
        }

        if (correctLetters.contains(normalized)) {
            return true;
        }
        if (incorrectLetters.contains(normalized)) {
            return false;
        }

        boolean correct = currentWord.getText().indexOf(normalized) >= 0;
        if (correct) {
            correctLetters.add(normalized);
            if (allLettersGuessed()) {
                roundStatus = RoundStatus.GANADA;
            }
        } else {
            incorrectLetters.add(normalized);
            wrongAttempts++;
            if (wrongAttempts == MAX_ERRORS) {
                roundStatus = RoundStatus.PERDIDA;
            }
        }
        return correct;
    }

    private boolean allLettersGuessed() {
        for (char letter : currentWord.getText().toCharArray()) {
            if (!correctLetters.contains(letter)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Avanza a la siguiente ronda únicamente si se ganó la actual.
     *
     * @return true si inició la siguiente ronda; false si las tres se ganaron
     * @throws IllegalStateException si la ronda no se ganó
     */
    public boolean advanceRound() {
        requireStarted();
        if (roundStatus != RoundStatus.GANADA) {
            throw new IllegalStateException("Solo puedes avanzar después de ganar la ronda");
        }
        if (roundNumber == TOTAL_ROUNDS) {
            return false;
        }
        roundNumber++;
        startRound();
        return true;
    }

    /** @return palabra oculta con guiones bajos y letras acertadas */
    public String getMaskedWord() {
        requireStarted();
        StringBuilder masked = new StringBuilder();
        for (char letter : currentWord.getText().toCharArray()) {
            if (masked.length() > 0) {
                masked.append(' ');
            }
            masked.append(correctLetters.contains(letter) ? letter : '_');
        }
        return masked.toString();
    }

    /** @return número de ronda actual, entre 1 y 3 */
    public int getRoundNumber() {
        return roundNumber;
    }

    /** @return nivel correspondiente a la ronda actual */
    public Level getCurrentLevel() {
        requireStarted();
        return switch (roundNumber) {
            case 1 -> Level.FACIL;
            case 2 -> Level.MEDIO;
            case 3 -> Level.DIFICIL;
            default -> throw new IllegalStateException("Número de ronda inválido");
        };
    }

    /** @return categoría fijada al inicio, o null si es aleatoria */
    public Category getSelectedCategory() {
        return selectedCategory;
    }

    /** @return estado de la ronda actual */
    public RoundStatus getRoundStatus() {
        return roundStatus;
    }

    /** @return número de errores de la ronda actual */
    public int getWrongAttempts() {
        return wrongAttempts;
    }

    /** @return número de errores restantes en la ronda */
    public int getRemainingAttempts() {
        return MAX_ERRORS - wrongAttempts;
    }

    /** @return letras correctas sin permitir modificar el conjunto interno */
    public Set<Character> getCorrectLetters() {
        return correctLetters == null ? Collections.emptySet() : Collections.unmodifiableSet(correctLetters);
    }

    /** @return letras incorrectas sin permitir modificar el conjunto interno */
    public Set<Character> getIncorrectLetters() {
        return incorrectLetters == null ? Collections.emptySet() : Collections.unmodifiableSet(incorrectLetters);
    }

    /** @return true si se completaron exitosamente las tres rondas */
    public boolean isGameWon() {
        return roundNumber == TOTAL_ROUNDS && roundStatus == RoundStatus.GANADA;
    }

    /** @return true si se agotaron los siete errores de una ronda */
    public boolean isGameLost() {
        return roundStatus == RoundStatus.PERDIDA;
    }

    /**
     * Retorna la palabra completa para mostrarla al cerrar una ronda.
     *
     * @return texto de la palabra actual
     * @throws IllegalStateException si la ronda aún está en curso
     */
    public String getRevealedWord() {
        requireStarted();
        if (roundStatus == RoundStatus.EN_CURSO) {
            throw new IllegalStateException("La palabra todavía está oculta");
        }
        return currentWord.getText();
    }

    /** @return categoría real de la palabra de la ronda actual */
    public Category getCurrentWordCategory() {
        requireStarted();
        return currentWord.getCategory();
    }
    /**
     * Evita utilizar una ronda antes de iniciar la partida.
     */
    private void requireStarted() {
        if (currentWord == null || roundNumber == 0) {
            throw new IllegalStateException("Primero debes iniciar la partida");
        }
    }

}
