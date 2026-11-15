# Diagrama de clases UML — implementación en main

Diagrama Mermaid elaborado a partir de las fuentes Java de la rama `main` (15 de noviembre de 2026). Representa los paquetes `com.poo.hangman`, `controller`, `model` y `view`, sus clases, operaciones principales y relaciones. El diagrama puede editarse como texto y GitHub lo representa automáticamente.

```mermaid
classDiagram
    direction LR

    namespace app {
        class Hangman {
            +main(String[] args) void
        }
    }

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
            -Hint categoryHint
            -Hint revealLetterHint
            -Hint descriptionHint
            +HangmanGame()
            +startGame() void
            +startGame(Category category) void
            +restartGame() void
            +isGameStarted() boolean
            +guessLetter(char letter) boolean
            +advanceRound() boolean
            +getMaskedWord() String
            +getRoundNumber() int
            +getCurrentLevel() Level
            +getSelectedCategory() Category
            +getCurrentWordCategory() Category
            +getRoundStatus() RoundStatus
            +getWrongAttempts() int
            +getRemainingAttempts() int
            +getCorrectLetters() Set~Character~
            +getStringCorrectLetters() String
            +getIncorrectLetters() Set~Character~
            +getStringIncorrectLetters() String
            +getRevealedWord() String
            +getClueWord() String
            +isGameWon() boolean
            +isGameLost() boolean
            +useCategoryHint() String
            +useRevealLetterHint() String
            +useDescriptionHint() String
            -startRound() void
            -allLettersGuessed() boolean
            -requireStarted() void
            -requireHintAvailable() void
        }
    }

    namespace model {
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

        class Hint {
            <<abstract>>
            -boolean used
            +Hint()
            +useHint(Word word, Set~Character~ guessedLetters) String
            #getHintText(Word word, Set~Character~ guessedLetters) String
            +isUsed() boolean
            +resetHint() void
        }

        class CategoryHint {
            #getHintText(Word word, Set~Character~ guessedLetters) String
        }

        class RevealLetterHint {
            #getHintText(Word word, Set~Character~ guessedLetters) String
        }

        class DescriptionHint {
            #getHintText(Word word, Set~Character~ guessedLetters) String
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

        class RoundStatus {
            <<enumeration>>
            EN_CURSO
            GANADA
            PERDIDA
            -String displayName
            +getDisplayName() String
            +toString() String
        }
    }

    namespace view {
        class POOHangman {
            -HangmanGame controller
            -Category selectedCategory
            -HangmanDrawingPanel hangmanDrawing
            +POOHangman()
            -initComponents() void
            -configureView() void
            -guess() void
            -updateRoundInfo() void
            -viewCategorySelect() void
            -jButton1ActionPerformed(ActionEvent evt) void
            -guessBottonActionPerformed(ActionEvent evt) void
            -jButton2ActionPerformed(ActionEvent evt) void
            -jButton3ActionPerformed(ActionEvent evt) void
            -jButtonClueTextWordActionPerformed(ActionEvent evt) void
            +main(String[] args) void
        }

        class HangmanDrawingPanel {
            -Color WOOD
            -Color BODY
            -Color ROPE
            -int errors
            +HangmanDrawingPanel()
            +setErrors(int errors) void
            +getErrors() int
            #paintComponent(Graphics g) void
        }
    }

    class JFrame {
        <<Swing>>
    }
    class JPanel {
        <<Swing>>
    }

    JFrame <|-- POOHangman
    JPanel <|-- HangmanDrawingPanel
    Hint <|-- CategoryHint
    Hint <|-- RevealLetterHint
    Hint <|-- DescriptionHint
    Hangman ..> POOHangman : inicia
    POOHangman --> HangmanGame : utiliza controlador
    POOHangman --> HangmanDrawingPanel : contiene dibujo
    POOHangman ..> Category : selecciona
    POOHangman ..> RoundStatus : consulta
    HangmanGame *-- WorkBank : banco de palabras
    HangmanGame --> Word : palabra actual
    HangmanGame *-- "3" Hint : pistas de ronda
    HangmanGame --> RoundStatus : estado
    HangmanGame ..> Category : filtro
    HangmanGame ..> Level : dificultad
    WorkBank "1" *-- "0..*" Word : almacena
    WorkBank ..> Category : filtra
    WorkBank ..> Level : filtra
    Word --> Category : categoria
    Word --> Level : nivel
    CategoryHint ..> Word : consulta categoria
    RevealLetterHint ..> Word : consulta letras
    DescriptionHint ..> Word : consulta pista
```

## Notas de diseño

- `Hangman` inicia la interfaz Swing `POOHangman`.
- `POOHangman` hereda de `JFrame`, utiliza `HangmanGame` y contiene `HangmanDrawingPanel`. Se muestran los principales eventos y métodos propios; se omiten los numerosos campos de componentes generados por NetBeans para facilitar la lectura.
- `HangmanDrawingPanel` hereda de `JPanel` y sobrescribe `paintComponent(Graphics)` para dibujar las siete partes del ahorcado.
- `HangmanGame` administra tres rondas, siete errores máximos, las letras acertadas o fallidas, los estados y las tres pistas; `getClueWord()` consulta la descripción de la palabra.
- `Hint` es una superclase abstracta; `CategoryHint`, `RevealLetterHint` y `DescriptionHint` especializan `getHintText()`. Cada pista controla su uso mediante `isUsed()` y `resetHint()`.
- `WorkBank` conserva su nombre real (con **k**), carga palabras y proporciona las dos sobrecargas de `getRandomWord()`. Su selector privado es `selectRandomWord()`.
- `Word` almacena texto, categoría, descripción y nivel. `Category`, `Level` y `RoundStatus` son enumeraciones.
- La multiplicidad de `Hint` representa las tres referencias de tipo `Hint` declaradas en `HangmanGame`, no tres objetos de la misma subclase.
- Las clases de pruebas JUnit no se incluyen en el diagrama de clases de producción; permanecen en `src/test/java`.

El archivo histórico `hangman/src/main/java/docs/class-diagram.png` no fue modificado. Esta versión Mermaid es la referencia vigente.
