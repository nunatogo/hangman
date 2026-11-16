package com.poo.hangman.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias de la carga y selección aleatoria de WorkBank. Se comprueba
 * su API pública, sin acceder a los métodos privados.
 *
 * @author Grupo 1
 */
class WorkBankTest {

    /**
     * Comprueba que WorkBank cargue exactamente 200 palabras al construirse.
     */
    @Test
    void loadsExactlyTwoHundredWords() {
        assertEquals(200, new WorkBank().getTotalWords());
    }

    /**
     * Comprueba que las palabras seleccionadas correspondan al nivel
     * solicitado.
     */
    @Test
    void randomWordAlwaysMatchesRequestedLevel() {
        WorkBank bank = new WorkBank();
        for (Level level : Level.values()) {
            for (int i = 0; i < 100; i++) {
                Word word = bank.getRandomWord(level);
                assertNotNull(word);
                assertEquals(level, word.getLevel());
            }
        }
    }

    /**
     * Comprueba que la selección respete simultáneamente categoría y nivel.
     */
    @Test
    void randomWordAlwaysMatchesCategoryAndLevel() {
        WorkBank bank = new WorkBank();
        for (Category category : Category.values()) {
            for (Level level : Level.values()) {
                for (int i = 0; i < 20; i++) {
                    Word word = bank.getRandomWord(category, level);
                    assertNotNull(word);
                    assertEquals(category, word.getCategory());
                    assertEquals(level, word.getLevel());
                }
            }
        }
    }

    /**
     * Verifica que se rechace un nivel nulo en ambas sobrecargas de selección.
     */
    @Test
    void rejectsNullLevel() {
        WorkBank bank = new WorkBank();
        assertAll(
                () -> assertThrows(IllegalArgumentException.class,
                        () -> bank.getRandomWord((Level) null)),
                () -> assertThrows(IllegalArgumentException.class,
                        () -> bank.getRandomWord(Category.ANIMALES, null))
        );
    }

    /**
     * Verifica que se rechace una categoría nula al filtrar palabras.
     */
    @Test
    void rejectsNullCategory() {
        WorkBank bank = new WorkBank();
        assertThrows(IllegalArgumentException.class,
                () -> bank.getRandomWord(null, Level.FACIL));
    }

    /**
     * Comprueba que seleccionar palabras no altere el tamaño del banco.
     */
    @Test
    void selectionDoesNotChangeBankSize() {
        WorkBank bank = new WorkBank();
        for (int i = 0; i < 100; i++) {
            bank.getRandomWord(Level.FACIL);
            bank.getRandomWord(Category.ANIMALES, Level.MEDIO);
        }
        assertEquals(200, bank.getTotalWords());
    }
}
