package com.poo.hangman.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RoundStatusTest {

    @Test
    void displaysSpanishLabels() {
        assertAll(
            () -> assertEquals("En curso", RoundStatus.EN_CURSO.getDisplayName()),
            () -> assertEquals("Ganada", RoundStatus.GANADA.getDisplayName()),
            () -> assertEquals("Perdida", RoundStatus.PERDIDA.getDisplayName())
        );
    }

    @Test
    void toStringReturnsDisplayName() {
        for (RoundStatus status : RoundStatus.values()) {
            assertEquals(status.getDisplayName(), status.toString());
        }
    }
}
