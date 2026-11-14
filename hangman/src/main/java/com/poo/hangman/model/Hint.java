package com.poo.hangman.model;

import java.util.Set;

/**
 * Superclase abstracta para las pistas del juego.
 *
 * Define el comportamiento común y controla que cada pista se utilice una sola
 * vez por ronda.
 * 
 * @author Grupo 1
 */
public abstract class Hint {

    /**
     * Atributo para determinar si la pista ya ha sido utilizada o no
     */
    private boolean used;

    /**
     * Constructor de la clase Hint.
     */
    public Hint() {
        this.used = false;
    }

    /**
     * Utiliza la pista y marca su estado como usada.
     *
     * @param word palabra actual del juego
     * @param guessedLetters letras ya acertadas
     * @return resultado de la pista
     */
    public final String useHint(
            Word word,
            Set<Character> guessedLetters) {

        if (used) {
            throw new IllegalStateException(
                    "Esta pista ya fue utilizada"
            );
        }

        if (word == null) {
            throw new IllegalArgumentException(
                    "La palabra no puede ser nula"
            );
        }

        String result = getHintText(word, guessedLetters);
        this.used = true;

        return result;
    }

    /**
     * Cada subclase define cómo funciona su pista.
     * @param word
     * @param guessedLetters
     * @return 
     */
    protected abstract String getHintText(
            Word word,
            Set<Character> guessedLetters);

    /**
     * Consulta si la pista ya fue utilizada.
     * @return boolean     
    */
    public boolean isUsed() {
        return used;
    }

    /**
     * Restablece la disponibilidad de la pista. Se utiliza al comenzar una
     * nueva ronda.
     */
    public void resetHint() {
        this.used = false;
    }

}
