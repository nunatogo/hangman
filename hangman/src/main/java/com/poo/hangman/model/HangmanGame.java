package com.poo.hangman.model;

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

    /** Estados de una ronda. */
    public enum RoundStatus {
        IN_PROGRESS, WON, LOST
    }

    private final WorkBank wordBank;
    private final Category selectedCategory;
    private final Set<Character> correctLetters = new LinkedHashSet<>();
    private final Set<Character> incorrectLetters = new LinkedHashSet<>();

    private Word currentWord;
    private int roundNumber;
    private int wrongAttempts;
    private RoundStatus roundStatus;

    /**
     * Crea una partida de categorías aleatorias.
     */
    public HangmanGame() {
        this(new WorkBank(), null);
    }

    /**
     * Crea una partida con categoría fija para las tres rondas.
     *
     * @param category categoría elegida, no nula
     */
    public HangmanGame(Category category) {
        this(new WorkBank(), requireCategory(category));
    }

    /**
     * Constructor para reutilizar un banco; null permite cualquier categoría.
     *
     * @param wordBank banco de palabras
     * @param category categoría fija, o null para todas las categorías
     */
    public HangmanGame(WorkBank wordBank, Category category) {
        if (wordBank == null) {
            throw new IllegalArgumentException("El banco de palabras no puede ser nulo");
        }
        this.wordBank = wordBank;
        this.selectedCategory = category;
        restartGame();
    }

    private static Category requireCategory(Category category) {
        if (category == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula");
        }
        return category;
    }

    /**
     * Inicia una nueva partida desde la primera ronda.
     */
    public final void restartGame() {
        roundNumber = 1;
        startRound();
    }

    /**
     * Inicia o reinicia el estado de la ronda actual.
     */
    private void startRound() {
        correctLetters.clear();
        incorrectLetters.clear();
        wrongAttempts = 0;
        roundStatus = RoundStatus.IN_PROGRESS;
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
        if (roundStatus != RoundStatus.IN_PROGRESS) {
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
                roundStatus = RoundStatus.WON;
            }
        } else {
            incorrectLetters.add(normalized);
            wrongAttempts++;
            if (wrongAttempts == MAX_ERRORS) {
                roundStatus = RoundStatus.LOST;
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
        if (roundStatus != RoundStatus.WON) {
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
        return Collections.unmodifiableSet(correctLetters);
    }

    /** @return letras incorrectas sin permitir modificar el conjunto interno */
    public Set<Character> getIncorrectLetters() {
        return Collections.unmodifiableSet(incorrectLetters);
    }

    /** @return true si se completaron exitosamente las tres rondas */
    public boolean isGameWon() {
        return roundNumber == TOTAL_ROUNDS && roundStatus == RoundStatus.WON;
    }

    /** @return true si se agotaron los siete errores de una ronda */
    public boolean isGameLost() {
        return roundStatus == RoundStatus.LOST;
    }

    /**
     * Retorna la palabra completa para mostrarla al cerrar una ronda.
     *
     * @return texto de la palabra actual
     * @throws IllegalStateException si la ronda aún está en curso
     */
    public String getRevealedWord() {
        if (roundStatus == RoundStatus.IN_PROGRESS) {
            throw new IllegalStateException("La palabra todavía está oculta");
        }
        return currentWord.getText();
    }

    /** @return categoría real de la palabra de la ronda actual */
    public Category getCurrentWordCategory() {
        return currentWord.getCategory();
    }
}
