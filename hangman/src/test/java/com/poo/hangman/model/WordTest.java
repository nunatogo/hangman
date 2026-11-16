package com.poo.hangman.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias de Word. Verifican el comportamiento esperado sin modificar
 * la clase original.
 */
class WordTest {

    /**
     * Comprueba que los métodos setters rechacen alores inválidos sin alterar
     * los datos anteriores.
     */
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

    /**
     * Comprueba que una palabra de hasta cinco letras sea clasificada en el
     * nivel FACIL.
     */
    @Test
    void assignsEasyLevel() {
        Word word = new Word("GATO", Category.ANIMALES, "Animal que maulla");
        assertEquals(Level.FACIL, word.getLevel());
    }

    /**
     * Comprueba que una palabra de seis a ocho letras sea clasificada en el
     * nivel MEDIO.
     */
    @Test
    void assignsMediumLevel() {
        Word word = new Word("CONEJO", Category.ANIMALES, "Orejas largas");
        assertEquals(Level.MEDIO, word.getLevel());
    }

    /**
     * Comprueba que una palabra de nueve letras o más sea clasificada en el
     * nivel DIFICIL.
     */
    @Test
    void assignsHardLevel() {
        Word word = new Word("ELEFANTES", Category.ANIMALES, "Animales grandes");
        assertEquals(Level.DIFICIL, word.getLevel());
    }

    /**
     * Verifica los límites de longitud utilizados para determinar la dificultad
     * de una palabra.
     */
    @Test
    void classifiesBoundaryLengths() {
        assertAll(
                () -> assertEquals(Level.FACIL, new Word("ABCDE", Category.OBJETOS, "Pista").getLevel()),
                () -> assertEquals(Level.MEDIO, new Word("ABCDEF", Category.OBJETOS, "Pista").getLevel()),
                () -> assertEquals(Level.MEDIO, new Word("ABCDEFGH", Category.OBJETOS, "Pista").getLevel()),
                () -> assertEquals(Level.DIFICIL, new Word("ABCDEFGHI", Category.OBJETOS, "Pista").getLevel())
        );
    }

    /**
     * Verifica que el método setText() permita actualizar correctamente el
     * texto de una palabra.
     */
    @Test
    void updatesText() {
        Word word = new Word("GATO", Category.ANIMALES, "Pista");
        word.setText("PERRO");
        assertEquals("PERRO", word.getText());
    }

    /**
     * Comprueba que al modificar el texto de una palabra mediante setText(),
     * también se actualice automáticamente su nivel de dificultad.
     */
    @Test
    void changingTextRecalculatesLevelAutomatically() {
        Word word = new Word("GATO", Category.ANIMALES, "Pista");
        word.setText("ELEFANTES");
        assertAll(
                () -> assertEquals("ELEFANTES", word.getText()),
                () -> assertEquals(Level.DIFICIL, word.getLevel())
        );
    }

    /**
     * Verifica que el método setClue() permita modificar la descripción o pista
     * asociada a una palabra.
     */
    @Test
    void updatesClue() {
        Word word = new Word("GATO", Category.ANIMALES, "Pista");
        word.setClue("Animal que maulla");
        assertEquals("Animal que maulla", word.getClue());
    }

    /**
     * Comprueba que el método setCategory() permita actualizar la categoría de
     * una palabra.
     */
    @Test
    void updatesCategory() {
        Word word = new Word("GATO", Category.ANIMALES, "Pista");
        word.setCategory(Category.OBJETOS);
        assertEquals(Category.OBJETOS, word.getCategory());
    }

    /**
     * Verifica que el método setLevel() recalcule el nivel de dificultad de
     * acuerdo con la longitud del texto actual.
     */
    @Test
    void recalculatesLevel() {
        Word word = new Word("GATO", Category.ANIMALES, "Pista");
        word.setText("ELEFANTES");
        word.setLevel();
        assertEquals(Level.DIFICIL, word.getLevel());
    }

    /**
     * Comprueba que el texto de una palabra sea normalizado automáticamente.
     */
    @Test
    void normalizesTextToUppercase() {
        Word word = new Word("  conejo  ", Category.ANIMALES, "Orejas largas");
        assertEquals("CONEJO", word.getText());
        assertEquals(Level.MEDIO, word.getLevel());
    }

    /**
     * Verifica que la clase Word permita utilizar caracteres alfabéticos con
     * tilde.
     */
    @Test
    void supportsAccentedLetters() {
        Word word = new Word("águila", Category.ANIMALES, "Ave rapaz");
        assertEquals("ÁGUILA", word.getText());
        assertEquals(Level.MEDIO, word.getLevel());
    }

    /**
     * Comprueba que el constructor rechace textos que no cumplen las reglas del
     * banco de palabras.
     */
    @Test
    void rejectsNullEmptyAndCompoundWords() {
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> new Word(null, Category.ANIMALES, "Pista")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Word("  ", Category.ANIMALES, "Pista")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Word("COSTA RICA", Category.PAISES, "Pista")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Word("GATO-NEGRO", Category.ANIMALES, "Pista")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Word("GATO3", Category.ANIMALES, "Pista"))
        );
    }

    /**
     * Verifica que el constructor rechace metadatos inválidos.
     */
    @Test
    void rejectsInvalidMetadata() {
        assertAll(
                () -> assertThrows(NullPointerException.class, () -> new Word("PERRO", null, "Pista")),
                () -> assertThrows(IllegalArgumentException.class, () -> new Word("PERRO", Category.ANIMALES, null)),
                () -> assertThrows(IllegalArgumentException.class, () -> new Word("PERRO", Category.ANIMALES, " "))
        );
    }

    /**
     * Comprueba que los métodos setters rechacen valores inválidos sin alterar
     * los datos anteriores.
     */
    @Test
    void settersRejectInvalidValuesWithoutChangingExistingData() {
        Word word = new Word("GATO", Category.ANIMALES, "Pista");
        assertAll(
                () -> assertThrows(IllegalArgumentException.class, () -> word.setText("DOS PALABRAS")),
                () -> assertThrows(NullPointerException.class, () -> word.setCategory(null)),
                () -> assertThrows(IllegalArgumentException.class, () -> word.setClue(" "))
        );
        assertAll(
                () -> assertEquals("GATO", word.getText()),
                () -> assertEquals(Category.ANIMALES, word.getCategory()),
                () -> assertEquals("Pista", word.getClue()),
                () -> assertEquals(Level.FACIL, word.getLevel())
        );
    }

}
