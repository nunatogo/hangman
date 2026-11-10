package com.poo.hangman.model;

/**
 * Clase Palabra 
 * @author Grupo 1
 */
public class Word {
    
    private String text;
    private String clue;
    private Category category;
    private Level level;
    
    public Word(String text, Category category, String clue, Level level){
        this.text = text;
        this.category = category;
        this.clue = clue;
    }

    public String getText() {
        return text;
    }

    public String getClue() {
        return clue;
    }

    public Category getCategory() {
        return category;
    }

    public Level getLevel() {
        return level;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setClue(String clue) {
        this.clue = clue;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setLevel() {
        if(this.text.length() <= 5){
            this.level = Level.FACIL;
        } else if(this.text.length() >= 6 || this.text.length() <= 8){
            this.level = Level.MEDIO;
        } else {
            this.level = Level.DIFICIL;
        }
    }
}
