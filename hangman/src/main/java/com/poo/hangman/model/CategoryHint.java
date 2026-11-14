package com.poo.hangman.model;

import java.util.Set;

/**
 * Pista que muestra la categoría de la palabra.
 * 
 * @author Grupo 1
 */
public class CategoryHint extends Hint {

    @Override
    protected String getHintText(Word word, Set<Character> guessedLetters) {
        String category = word.getCategory().name().toLowerCase();
        
        return "La categoría es " + category;
    }
    
}
