package com.poo.hangman;

import com.poo.hangman.view.POOHangman;
import javax.swing.SwingUtilities;

/**
 * Clase principal para iniciar el juego del ahorcado.
 *
 * @author Grupo 1
 */
public class Hangman {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            POOHangman gameView = new POOHangman();
            gameView.setLocationRelativeTo(null);
            gameView.setVisible(true);
        });
    }
}
