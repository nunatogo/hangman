package com.poo.hangman.model;

/**
 * Estados posibles de una ronda del juego del ahorcado.
 * Los valores se presentan al usuario en español.
 *
 * @author Grupo 1
 */
public enum RoundStatus {
    EN_CURSO("En curso"),
    GANADA("Ganada"),
    PERDIDA("Perdida");

    private final String displayName;

    RoundStatus(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Obtiene el texto que debe mostrarse en la interfaz gráfica.
     *
     * @return descripción legible del estado en español
     */
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
