# Documentación técnica — HangmanGame (sin pistas)

**Paquete:** `com.poo.hangman.controller` (controlador MVC).\n\n## Responsabilidad
Gestiona el estado de una partida de tres rondas de ahorcado y se apoya en `WorkBank` para seleccionar una palabra en cada nivel. Coordina el modelo sin mostrar interfaz gráfica; las tres pistas siguen pendientes.

## Reglas de juego
- **Ronda 1:** `FACIL`. Si gana, puede avanzar a la ronda 2.
- **Ronda 2:** `MEDIO`. Si gana, puede avanzar a la ronda 3.
- **Ronda 3:** `DIFICIL`. Al ganar, `isGameWon()` devuelve `true`.
- Hay **7 errores** disponibles por ronda. Cada palabra nueva reinicia errores y letras usadas.
- Una letra repetida no consume errores adicionales.
- Cuando se pierde una ronda, `isGameLost()` devuelve `true` y no se puede avanzar.
- Se mantiene la modalidad inicial: categoría fija para las tres rondas o cualquier categoría en cada ronda. En el modo aleatorio, una categoría podría repetirse.
- Las pistas están **pendientes** y no se contemplan todavía en el cálculo de la victoria.

## API pública

| Método | Retorno | Función |
|---|---|---|
| `HangmanGame()` | constructor | Iniciar con categorías aleatorias |
| `HangmanGame(Category)` | constructor | Iniciar con categoría fija |
| `HangmanGame(WorkBank, Category)` | constructor | Inyectar un banco para pruebas o reutilización (`null` indica categoría aleatoria) |
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
| `getRoundStatus()` | `RoundStatus` | Consultar `IN_PROGRESS`, `WON` o `LOST` |
| `isGameWon()` | `boolean` | Confirmar victoria en la tercera ronda |
| `isGameLost()` | `boolean` | Confirmar derrota por siete errores |
| `getRevealedWord()` | `String` | Revelar la palabra una vez terminada la ronda |

## Relación con POO
- **Encapsulación:** el estado interno es privado; los getters de conjuntos entregan vistas no modificables.
- **Composición/colaboración:** se usa `WorkBank` para obtener palabras según la dificultad y modalidad.
- **Control de estado:** `RoundStatus` impide seguir intentando una vez ganada o perdida una ronda.
- **Separación de responsabilidades:** la clase Swing consultará esta lógica y representará gráficamente los errores (cabeza, torso, brazos, piernas y cuerda); `HangmanGame` no dibuja.

## Ejemplo

```java
HangmanGame game = new HangmanGame(Category.ANIMALES);
boolean isCorrect = game.guessLetter('A');
System.out.println(game.getMaskedWord());
System.out.println(game.getRemainingAttempts());
if (game.getRoundStatus() == HangmanGame.RoundStatus.WON) {
    boolean hasNextRound = game.advanceRound();
}
```

## Pruebas
`src/test/java/com/poo/hangman/controller/HangmanGameTest.java` contiene diez métodos de prueba JUnit 5. Se ejecutan desde NetBeans (Test File) o mediante `mvn -Dtest=HangmanGameTest test` desde la carpeta con `pom.xml`.

## Pendientes intencionados
- Pista 1: categoría de la palabra.
- Pista 2: letra aleatoria revelada.
- Pista 3: descripción escrita.
- Interfaz gráfica Swing y dibujo progresivo.
- Puntaje y ranking si el equipo decide implementarlos.

**Decisión de diseño:** la transición entre rondas no es automática; se realiza mediante `advanceRound()` después de que la interfaz comunique el resultado al jugador.
