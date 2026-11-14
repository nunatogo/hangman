package com.poo.hangman.model;

import java.util.Set;

/**
 * Pista que muestra la descripción asociada a una palabra.
 * 
 * @author Grupo 1
 */
public class DescriptionHint extends Hint {

    @Override
    protected String getHintText(Word word, Set<Character> guessedLetters) {
        return "Pista: " + word.getClue();
    }
    
}
