package com.poo.hangman.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la enumeración RoundStatus.
 *
 * @author Grupo 1
 */
class RoundStatusTest {

    /**
     * Verifica que cada valor de la enumeración RoundStatus tenga asignada la
     * descripción correcta en español.
     */
    @Test
    void displaysSpanishLabels() {
        assertAll(
                () -> assertEquals("En curso", RoundStatus.EN_CURSO.getDisplayName()),
                () -> assertEquals("Ganada", RoundStatus.GANADA.getDisplayName()),
                () -> assertEquals("Perdida", RoundStatus.PERDIDA.getDisplayName())
        );
    }

    /**
     * Comprueba que el método toString() de RoundStatus devuelva la descripción
     * legible del estado.
     */
    @Test
    void toStringReturnsDisplayName() {
        for (RoundStatus status : RoundStatus.values()) {
            assertEquals(status.getDisplayName(), status.toString());
        }
    }
}
