package com.poo.hangman.model;

/**
 * Representa una palabra del juego del ahorcado.
 *
 * <p>Almacena el texto que debe adivinarse, su categoría, una pista escrita
 * y el nivel de dificultad. El nivel se determina automáticamente a partir
 * de la longitud del texto: de 1 a 5 caracteres (FACIL), de 6 a 8 (MEDIO)
 * y 9 o más (DIFICIL).</p>
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
        this.text = text;
        this.category = category;
        this.clue = clue;
        this.level = assignLevel();
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
     * @param text nuevo texto (no debe ser null)
     */
    public void setText(String text) {
        this.text = text;
        setLevel();
    }

    /**
     * Modifica la pista escrita.
     *
     * @param clue nueva pista
     */
    public void setClue(String clue) {
        this.clue = clue;
    }

    /**
     * Modifica la categoría de la palabra.
     *
     * @param category nueva categoría
     */
    public void setCategory(Category category) {
        this.category = category;
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
