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
        load("PERRO", Category.ANIMALES,"Es el mejor amigo del hombre");
        load("GATO", Category.ANIMALES,"Maulla y ronronea cuando está feliz");
        load("ELEFANTE", Category.ANIMALES,"El mamifero terrestre más grande");
        load("JIRAFA", Category.ANIMALES,"Tiene el cuello más largo del mundo");
        load("LEON",Category.ANIMALES,"Es conocido como el rey de la selva");
        load("TIGRE", Category.ANIMALES,"Felino con rayas negras");
        load("CABALLO", Category.ANIMALES,"Transporta personas y equipajes");
        load("VACA", Category.ANIMALES,"Tiene manchas y come pasto");
        load("CERDO",Category.ANIMALES,"Es rosado y come toda clase de comida");
        load("OVEJA",Category.ANIMALES,"Con su lana se pueden hacer sacos");
        load("CONEJO",Category.ANIMALES,"Pequeño con orejas largas y saltarin");
        load("RATON",Category.ANIMALES,"Presa de los gatos, le gusta el queso");
        load("DELFIN",Category.ANIMALES,"Mamifero marino muy inteligente");
        

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
