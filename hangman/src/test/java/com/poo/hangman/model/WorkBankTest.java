package com.poo.hangman.model;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas del banco de palabras y sus filtros internos.
 * Los filtros se invocan mediante reflexión porque fueron solicitados
 * explícitamente como métodos privados.
 */
class WorkBankTest {

    @Test
    void loadsExactlyTwoHundredWords() {
        WorkBank bank = new WorkBank();
        assertEquals(200, bank.getTotalWords());
    }

    @SuppressWarnings("unchecked")
    private ArrayList<Word> filter(String name, Class<?>[] parameterTypes, Object... values)
            throws ReflectiveOperationException {
        WorkBank bank = new WorkBank();
        Method method = WorkBank.class.getDeclaredMethod(name, parameterTypes);
        method.setAccessible(true);
        return (ArrayList<Word>) method.invoke(bank, values);
    }

    @Test
    void wordsByLevelContainOnlyRequestedLevel() throws Exception {
        int total = 0;
        for (Level level : Level.values()) {
            ArrayList<Word> selected = filter("getWordsByLevel", new Class<?>[]{Level.class}, level);
            assertTrue(selected.stream().allMatch(word -> word.getLevel() == level));
            total += selected.size();
        }
        assertEquals(200, total);
    }

    @Test
    void wordsByCategoryAndLevelMatchBothFilters() throws Exception {
        int total = 0;
        for (Category category : Category.values()) {
            for (Level level : Level.values()) {
                ArrayList<Word> selected = filter("getWordsByCategoryAndLevel",
                        new Class<?>[]{Category.class, Level.class}, category, level);
                assertTrue(selected.stream().allMatch(word ->
                        word.getCategory() == category && word.getLevel() == level));
                total += selected.size();
            }
        }
        assertEquals(200, total);
    }

    @Test
    void countriesCategoryHasThirtyNineWords() throws Exception {
        int total = 0;
        for (Level level : Level.values()) {
            total += filter("getWordsByCategoryAndLevel",
                    new Class<?>[]{Category.class, Level.class}, Category.PAISES, level).size();
        }
        assertEquals(39, total);
    }

    @Test
    void wordsAreNotRepeated() throws Exception {
        Set<String> all = new HashSet<>();
        for (Level level : Level.values()) {
            for (Word word : filter("getWordsByLevel", new Class<?>[]{Level.class}, level)) {
                assertTrue(all.add(word.getText()), "Palabra duplicada: " + word.getText());
            }
        }
        assertEquals(200, all.size());
    }
}
