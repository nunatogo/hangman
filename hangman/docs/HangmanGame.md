# Documentación técnica — HangmanGame (sin pistas)

**Paquete:** `com.poo.hangman.controller` (controlador MVC).

## Responsabilidad
Gestiona el estado de una partida de tres rondas de ahorcado y se apoya en `WorkBank` para seleccionar una palabra en cada nivel. Coordina el modelo sin mostrar interfaz gráfica; las tres pistas siguen pendientes.

## Ciclo de vida
El constructor sin parámetros crea el banco de palabras (`WorkBank`) y deja `currentWord`, `correctLetters` e `incorrectLetters` en `null`, además de `roundNumber = 0`, `wrongAttempts = 0` y `roundStatus = EN_CURSO`. No selecciona una palabra. Para comenzar se debe llamar a `startGame()` o `startGame(Category)`, que preparan la primera ronda y las colecciones. Los métodos que requieren una ronda activa rechazan su uso antes del inicio.

**Nota sobre el nombre:** la clase del banco existente en el proyecto se llama `WorkBank`, no `WordBank`; se conserva su nombre para no cambiar otras clases sin solicitarlo.

## Reglas de juego
- **Ronda 1:** `FACIL`. Si gana, puede avanzar a la ronda 2.
- **Ronda 2:** `MEDIO`. Si gana, puede avanzar a la ronda 3.
- **Ronda 3:** `DIFICIL`. Al ganar, `isGameWon()` devuelve `true`.
- Hay **7 errores** disponibles por ronda. Cada palabra nueva reinicia errores y letras usadas.
- Una letra repetida no consume errores adicionales.
- Cuando se pierde una ronda, `isGameLost()` devuelve `true` y no se puede avanzar.
- La categoría se define al invocar `startGame()` o `startGame(Category)` y se mantiene durante las tres rondas. En el modo aleatorio, una categoría podría repetirse.
- Las pistas están **pendientes** y no se contemplan todavía en el cálculo de la victoria.

## API pública

| Método | Retorno | Función |
|---|---|---|
| `HangmanGame()` | constructor | Inicializar atributos en estado base, sin comenzar la partida |
| `startGame()` | `void` | Comenzar desde la ronda fácil con categoría aleatoria |
| `startGame(Category)` | `void` | Comenzar desde la ronda fácil con categoría seleccionada |
| `guessLetter(char)` | `boolean` | Verificar la letra; retorna si existe en la palabra |
| `advanceRound()` | `boolean` | Avanzar tras ganar; retorna `false` al completar la tercera ronda |
| `restartGame()` | `void` | Reiniciar desde la primera ronda |
| `getMaskedWord()` | `String` | Mostrar letras acertadas y guiones bajos |
| `getRoundNumber()` | `int` | Consultar ronda actual |
| `getCurrentLevel()` | `Level` | Consultar dificultad de la ronda |
| `getSelectedCategory()` | `Category` | Consultar categoría elegida, o `null` en modo aleatorio |
| `getCurrentWordCategory()` | `Category` | Consultar la categoría real de la palabra seleccionada |
| `getWrongAttempts()` | `int` | Consultar número de errores |
| `getRemainingAttempts()` | `int` | Consultar errores restantes |
| `getCorrectLetters()` | `Set<Character>` | Letras acertadas (colección no modificable) |
| `getIncorrectLetters()` | `Set<Character>` | Letras equivocadas (colección no modificable) |
| `getRoundStatus()` | `RoundStatus` | Consultar `EN_CURSO`, `GANADA` o `PERDIDA` |
| `isGameWon()` | `boolean` | Confirmar victoria en la tercera ronda |
| `isGameLost()` | `boolean` | Confirmar derrota por siete errores |
| `getRevealedWord()` | `String` | Revelar la palabra una vez terminada la ronda |

## Enumeración del modelo

`RoundStatus` se encuentra en `com.poo.hangman.model` y define `EN_CURSO`, `GANADA` y `PERDIDA`. El método `getDisplayName()` devuelve los textos para la interfaz (`En curso`, `Ganada` y `Perdida`); `toString()` retorna los mismos textos.

## Relación con POO
- **Encapsulación:** el estado interno es privado; los getters de conjuntos entregan vistas no modificables.
- **Composición/colaboración:** se usa `WorkBank` para obtener palabras según la dificultad y modalidad.
- **Control de estado:** `RoundStatus` impide seguir intentando una vez ganada o perdida una ronda.
- **Separación de responsabilidades:** la clase Swing consultará esta lógica y representará gráficamente los errores (cabeza, torso, brazos, piernas y cuerda); `HangmanGame` no dibuja.

## Ejemplo

```java
import com.poo.hangman.controller.HangmanGame;
import com.poo.hangman.model.Category;
import com.poo.hangman.model.RoundStatus;

HangmanGame game = new HangmanGame();
game.startGame(Category.ANIMALES);
boolean isCorrect = game.guessLetter('A');
System.out.println(game.getMaskedWord());
System.out.println(game.getRemainingAttempts());
if (game.getRoundStatus() == RoundStatus.GANADA) {
    boolean hasNextRound = game.advanceRound();
}
```

## Pruebas
`src/test/java/com/poo/hangman/controller/HangmanGameTest.java` contiene once métodos de prueba JUnit 5. Se ejecutan desde NetBeans (Test File) o mediante `mvn -Dtest=HangmanGameTest test` desde la carpeta con `pom.xml`.

## Pendientes intencionados
- Pista 1: categoría de la palabra.
- Pista 2: letra aleatoria revelada.
- Pista 3: descripción escrita.
- Interfaz gráfica Swing y dibujo progresivo.
- Puntaje y ranking si el equipo decide implementarlos.

**Decisión de diseño:** la transición entre rondas no es automática; se realiza mediante `advanceRound()` después de que la interfaz comunique el resultado al jugador.
