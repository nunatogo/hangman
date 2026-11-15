package com.poo.hangman.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 * Panel que dibuja el ahorcado según la cantidad de errores del jugador.
 *
 * <p>
 * Hereda de {@link JPanel} (herencia) y sobrescribe
 * {@link #paintComponent(Graphics)} para personalizar su dibujo (polimorfismo).
 * Cada error agrega una parte en este orden: cabeza, torso, brazo derecho,
 * brazo izquierdo, pierna derecha, pierna izquierda y, finalmente, la
 * cuerda.</p>
 *
 * @author Grupo 1
 */
public class HangmanDrawingPanel extends JPanel {

    private static final Color WOOD = new Color(121, 85, 72);
    private static final Color BODY = new Color(33, 33, 33);
    private static final Color ROPE = new Color(191, 54, 12);

    /**
     * Cantidad de errores que se deben representar (0 a 7).
     */
    private int errors;

    public HangmanDrawingPanel() {
        setPreferredSize(new Dimension(320, 360));
        setBackground(new Color(250, 248, 240));
    }

    /**
     * Actualiza la cantidad de errores y vuelve a dibujar el panel.
     *
     * @param errors errores de la ronda actual (de 0 a 7)
     */
    public void setErrors(int errors) {
        this.errors = Math.max(0, Math.min(errors, 7));
        repaint();
    }

    public int getErrors() {
        return errors;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int w = getWidth();
        int h = getHeight();

        // Coordenadas base, calculadas según el tamaño del panel
        int baseY = h - 30;
        int poleX = w / 2 - 80;
        int topY = 30;
        int beamEndX = w / 2 + 40;   // donde cuelga la cuerda
        int headSize = 50;
        int headTop = topY + 50;
        int neckY = headTop + headSize;
        int hipY = neckY + 80;
        int shoulderY = neckY + 20;

        // Estructura (siempre visible)
        g2.setColor(WOOD);
        g2.setStroke(new BasicStroke(8, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        g2.drawLine(30, baseY, w - 30, baseY);           // base
        g2.drawLine(poleX, baseY, poleX, topY);          // poste
        g2.drawLine(poleX, topY, beamEndX, topY);        // viga
        g2.drawLine(poleX, topY + 40, poleX + 40, topY); // refuerzo

        // Partes del personaje
        g2.setColor(BODY);
        g2.setStroke(new BasicStroke(5, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

        if (errors >= 1) { // 1. cabeza
            g2.drawOval(beamEndX - headSize / 2, headTop, headSize, headSize);
        }
        if (errors >= 2) { // 2. torso
            g2.drawLine(beamEndX, neckY, beamEndX, hipY);
        }
        if (errors >= 3) { // 3. brazo derecho
            g2.drawLine(beamEndX, shoulderY, beamEndX + 40, shoulderY + 45);
        }
        if (errors >= 4) { // 4. brazo izquierdo
            g2.drawLine(beamEndX, shoulderY, beamEndX - 40, shoulderY + 45);
        }
        if (errors >= 5) { // 5. pierna derecha
            g2.drawLine(beamEndX, hipY, beamEndX + 35, hipY + 60);
        }
        if (errors >= 6) { // 6. pierna izquierda
            g2.drawLine(beamEndX, hipY, beamEndX - 35, hipY + 60);
        }
        if (errors >= 7) { // 7. cuerda final
            g2.setColor(ROPE);
            g2.setStroke(new BasicStroke(4));
            g2.drawLine(beamEndX, topY, beamEndX, headTop);
            // ojos en X: el personaje perdió
            g2.setColor(BODY);
            g2.setStroke(new BasicStroke(2));
            int ex = beamEndX - 12;
            int ey = headTop + 15;
            g2.drawLine(ex, ey, ex + 8, ey + 8);
            g2.drawLine(ex + 8, ey, ex, ey + 8);
            ex = beamEndX + 4;
            g2.drawLine(ex, ey, ex + 8, ey + 8);
            g2.drawLine(ex + 8, ey, ex, ey + 8);
        }

        g2.dispose();
    }
}
