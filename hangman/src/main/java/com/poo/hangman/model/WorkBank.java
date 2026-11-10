package com.poo.hangman.model;

import java.util.ArrayList;

/**
 * Banco de palabras que se van a utilizar dentro del juego
 *
 * @author Grupo 1
 */
public class WorkBank {

    private ArrayList<Word> words;

    public WorkBank() {
        this.words = new ArrayList<Word>();
        loadAnimals();
        loadCountries();
        loadSports();
        loadObjects();
        loadFoods();
    }

    private void loadAnimals() {

    }

    private void loadCountries() {

    }

    private void loadSports() {

    }

    private void loadObjects() {

    }

    private void loadFoods() {

    }

    private void load(String text, Category category, String clue) {
        this.words.add(new Word(text, category, clue));
    }
}
