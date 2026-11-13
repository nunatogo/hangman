package com.poo.hangman.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias de Word.
 * Verifican el comportamiento esperado sin modificar la clase original.
 */
class WordTest {

    @Test
    void constructorInitializesFields() {
        Word word = new Word("PERRO", Category.ANIMALES, "Animal que ladra");
        assertAll(
            () -> assertEquals("PERRO", word.getText()),
            () -> assertEquals(Category.ANIMALES, word.getCategory()),
            () -> assertEquals("Animal que ladra", word.getClue()),
            () -> assertEquals(Level.FACIL, word.getLevel())
        );
    }

    @Test
    void assignsEasyLevel() {
        Word word = new Word("GATO", Category.ANIMALES, "Animal que maulla");
        assertEquals(Level.FACIL, word.getLevel());
    }

    @Test
    void assignsMediumLevel() {
        Word word = new Word("CONEJO", Category.ANIMALES, "Orejas largas");
        assertEquals(Level.MEDIO, word.getLevel());
    }

    @Test
    void assignsHardLevel() {
        Word word = new Word("ELEFANTES", Category.ANIMALES, "Animales grandes");
        assertEquals(Level.DIFICIL, word.getLevel());
    }

    @Test
    void classifiesBoundaryLengths() {
        assertAll(
            () -> assertEquals(Level.FACIL, new Word("ABCDE", Category.OBJETOS, "Pista").getLevel()),
            () -> assertEquals(Level.MEDIO, new Word("ABCDEF", Category.OBJETOS, "Pista").getLevel()),
            () -> assertEquals(Level.MEDIO, new Word("ABCDEFGH", Category.OBJETOS, "Pista").getLevel()),
            () -> assertEquals(Level.DIFICIL, new Word("ABCDEFGHI", Category.OBJETOS, "Pista").getLevel())
        );
    }

    @Test
    void updatesText() {
        Word word = new Word("GATO", Category.ANIMALES, "Pista");
        word.setText("PERRO");
        assertEquals("PERRO", word.getText());
    }

    @Test
    void changingTextRecalculatesLevelAutomatically() {
        Word word = new Word("GATO", Category.ANIMALES, "Pista");
        word.setText("ELEFANTES");
        assertAll(
            () -> assertEquals("ELEFANTES", word.getText()),
            () -> assertEquals(Level.DIFICIL, word.getLevel())
        );
    }

    @Test
    void updatesClue() {
        Word word = new Word("GATO", Category.ANIMALES, "Pista");
        word.setClue("Animal que maulla");
        assertEquals("Animal que maulla", word.getClue());
    }

    @Test
    void updatesCategory() {
        Word word = new Word("GATO", Category.ANIMALES, "Pista");
        word.setCategory(Category.OBJETOS);
        assertEquals(Category.OBJETOS, word.getCategory());
    }

    @Test
    void recalculatesLevel() {
        Word word = new Word("GATO", Category.ANIMALES, "Pista");
        word.setText("ELEFANTES");
        word.setLevel();
        assertEquals(Level.DIFICIL, word.getLevel());
    }
}
