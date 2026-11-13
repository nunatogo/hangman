# Diagrama de clases UML — estado implementado

Diagrama actualizado con las clases Java existentes en `com.poo.hangman.model` de la rama `nunatogo-desa`. Este archivo utiliza Mermaid para que el diagrama se pueda editar como texto y GitHub lo renderice automáticamente.

```mermaid
classDiagram
    direction LR

    class Word {
        -String text
        -String clue
        -Category category
        -Level level
        +Word(String text, Category category, String clue)
        +getText() String
        +getClue() String
        +getCategory() Category
        +getLevel() Level
        +setText(String text) void
        +setClue(String clue) void
        +setCategory(Category category) void
        +setLevel() void
        -assignLevel() Level
    }

    class WorkBank {
        -ArrayList~Word~ words
        +WorkBank()
        -loadWords() void
        -loadAnimals() void
        -loadCountries() void
        -loadSports() void
        -loadObjects() void
        -loadFoods() void
        -load(String text, Category category, String clue) void
        +getRandomWord(Level level) Word
        +getRandomWord(Category category, Level level) Word
        -selectRandomWord(Category category, Level level) Word
        +getTotalWords() int
    }

    class Category {
        <<enumeration>>
        ANIMALES
        PAISES
        DEPORTES
        OBJETOS
        COMIDAS
    }

    class Level {
        <<enumeration>>
        FACIL
        MEDIO
        DIFICIL
    }

    WorkBank "1" *-- "0..*" Word : almacena
    Word --> Category : pertenece a
    Word --> Level : dificultad
    WorkBank ..> Category : filtra por
    WorkBank ..> Level : filtra por
```

## Notas de diseño

- `WorkBank` almacena 200 palabras y aplica la selección aleatoria mediante dos **sobrecargas** de `getRandomWord()`.
- `selectRandomWord()` es un método privado auxiliar; no existen métodos separados que devuelvan listas filtradas.
- `Word` calcula la dificultad según la longitud de la palabra, valida su contenido y conserva su categoría y pista.
- `Category` y `Level` son enumeraciones.
- El controlador del juego, la interfaz Swing y el avance de las tres rondas todavía no aparecen en el código revisado; se incorporarán al diagrama cuando estén implementados.
