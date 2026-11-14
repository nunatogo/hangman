package com.poo.hangman.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Pista que revela una letra aleatoria que aún no haya sido acertada.
 *
 * @author Grupo 1
 */
public class RevealLetterHint extends Hint {

    @Override
    protected String getHintText(Word word, Set<Character> guessedLetters) {
        if (guessedLetters == null) {
            throw new IllegalArgumentException("Las letras acertadas no pueden ser nulas");
        }

        Set<Character> availableLetters = new HashSet<>();

        for (char letter : word.getText().toCharArray()) {
            if (!guessedLetters.contains(letter)) {
                availableLetters.add(letter);
            }
        }

        if (availableLetters.isEmpty()) {
            throw new IllegalStateException("No quedan letras por descubrir");
        }

        ArrayList<Character> candidates = new ArrayList<>(availableLetters);
        int index = ThreadLocalRandom.current().nextInt(candidates.size());
        char revealedLetter = candidates.get(index);

        return String.valueOf(revealedLetter);
    }

}
