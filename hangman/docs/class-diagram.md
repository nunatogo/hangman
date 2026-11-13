# Diagrama de clases UML — estado implementado

Diagrama actualizado con las clases Java existentes en los paquetes `com.poo.hangman.model` y `com.poo.hangman.controller` de la rama `nunatogo-desa`. Este archivo utiliza Mermaid para que el diagrama se pueda editar como texto y GitHub lo renderice automáticamente.

```mermaid
classDiagram
    direction LR

    namespace controller {
    class HangmanGame {
        +int MAX_ERRORS
        +int TOTAL_ROUNDS
        -WorkBank wordBank
        -Category selectedCategory
        -Word currentWord
        -int roundNumber
        -int wrongAttempts
        -RoundStatus roundStatus
        -Set~Character~ correctLetters
        -Set~Character~ incorrectLetters
        +HangmanGame()
        +HangmanGame(Category category)
        +HangmanGame(WorkBank wordBank, Category category)
        +guessLetter(char letter) boolean
        +advanceRound() boolean
        +restartGame() void
        +getMaskedWord() String
        +getRoundNumber() int
        +getCurrentLevel() Level
        +getSelectedCategory() Category
        +getCurrentWordCategory() Category
        +getRoundStatus() RoundStatus
        +getWrongAttempts() int
        +getRemainingAttempts() int
        +getCorrectLetters() Set~Character~
        +getIncorrectLetters() Set~Character~
        +isGameWon() boolean
        +isGameLost() boolean
        +getRevealedWord() String
        -startRound() void
        -allLettersGuessed() boolean
    }

    }

    namespace model {
    class RoundStatus {
        <<enumeration>>
        EN_CURSO
        GANADA
        PERDIDA
        -String displayName
        +getDisplayName() String
        +toString() String
    }

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

    }

    HangmanGame --> WorkBank : selecciona palabra
    HangmanGame --> Word : ronda actual
    HangmanGame --> RoundStatus : estado de ronda
    HangmanGame ..> Level : progresión
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
- `HangmanGame` controla las tres rondas, los intentos y las letras. Sus pistas y la interfaz Swing todavía están pendientes.
