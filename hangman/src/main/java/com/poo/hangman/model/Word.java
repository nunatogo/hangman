package com.poo.hangman.model;

import java.util.Locale;
import java.util.Objects;

/**
 * Representa una palabra del juego del ahorcado.
 *
 * <p>Almacena el texto que debe adivinarse, su categoría, una pista escrita
 * y el nivel de dificultad. El nivel se determina automáticamente a partir
 * de la longitud del texto: de 1 a 5 caracteres (FACIL), de 6 a 8 (MEDIO)
 * y 9 o más (DIFICIL). Solo admite palabras individuales formadas
 * por letras y las normaliza a mayúsculas.</p>
 *
 * <p>Esta clase encapsula los datos de una palabra y no gestiona los intentos
 * ni la interfaz gráfica del juego.</p>
 *
 * @author Grupo 1
 */
public class Word {

    /** Texto de la palabra que debe adivinar el jugador. */
    private String text;

    /** Pista escrita asociada a la palabra. */
    private String clue;

    /** Categoría a la que pertenece la palabra. */
    private Category category;

    /** Nivel calculado según la cantidad de caracteres del texto. */
    private Level level;

    /**
     * Crea una palabra y calcula su nivel de dificultad inicial.
     *
     * @param text palabra que debe adivinarse (no debe ser null)
     * @param category categoría de la palabra
     * @param clue pista escrita relacionada con la palabra
     */
    public Word(String text, Category category, String clue) {
        setText(text);
        setCategory(category);
        setClue(clue);
    }

    /**
     * Obtiene el texto de la palabra.
     *
     * @return texto de la palabra
     */
    public String getText() {
        return text;
    }

    /**
     * Obtiene la pista escrita.
     *
     * @return pista de la palabra
     */
    public String getClue() {
        return clue;
    }

    /**
     * Obtiene la categoría.
     *
     * @return categoría de la palabra
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Obtiene el nivel de dificultad calculado.
     *
     * @return nivel de dificultad
     */
    public Level getLevel() {
        return level;
    }

    /**
     * Actualiza el texto y recalcula el nivel para mantener ambos valores
     * coherentes.
     *
     * @param text palabra individual no vacía y sin espacios
     */
    public void setText(String text) {
        if (text == null || text.isBlank()) {
            throw new IllegalArgumentException("La palabra no puede ser nula ni vacía");
        }
        String normalizedText = text.trim().toUpperCase(Locale.ROOT);
        if (!normalizedText.matches("\\p{L}+")) {
            throw new IllegalArgumentException("La palabra debe contener solo letras, sin espacios");
        }
        this.text = normalizedText;
        setLevel();
    }

    /**
     * Modifica la pista escrita.
     *
     * @param clue nueva pista no vacía
     */
    public void setClue(String clue) {
        if (clue == null || clue.isBlank()) {
            throw new IllegalArgumentException("La pista no puede ser nula ni vacía");
        }
        this.clue = clue.trim();
    }

    /**
     * Modifica la categoría de la palabra.
     *
     * @param category nueva categoría no nula
     */
    public void setCategory(Category category) {
        this.category = Objects.requireNonNull(category, "La categoría no puede ser nula");
    }

    /**
     * Recalcula el nivel según la longitud del texto actual.
     */
    public void setLevel() {
        this.level = assignLevel();
    }

    /**
     * Determina la dificultad de la palabra a partir de su longitud.
     *
     * @return FACIL para hasta 5 caracteres, MEDIO de 6 a 8,
     *         o DIFICIL para 9 o más caracteres
     */
    private Level assignLevel() {
        if (this.text.length() <= 5) {
            return Level.FACIL;
        } else if (this.text.length() >= 6 && this.text.length() <= 8) {
            return Level.MEDIO;
        } else {
            return Level.DIFICIL;
        }
    }
}
