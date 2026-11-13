# Documentación técnica: clase `WorkBank`

## Propósito
Mantiene el banco de palabras del juego y permite consultar su tamaño y filtrar internamente las entradas por nivel o por categoría y nivel. Emplea `ArrayList<Word>`.

## Construcción y carga
El constructor crea `this.words` e invoca `loadWords()` (privado). Este coordina los métodos `loadAnimals()`, `loadCountries()`, `loadSports()`, `loadObjects()` y `loadFoods()`. Cada entrada se crea mediante `load(text, category, clue)`.

Se agregó la palabra **PERU** (categoría `PAISES`) para llegar a **200 palabras únicas**.

| Categoría | Cantidad |
|---|---:|
| ANIMALES | 41 |
| PAISES | 39 |
| DEPORTES | 40 |
| OBJETOS | 40 |
| COMIDAS | 40 |
| **Total** | **200** |

## Métodos disponibles

| Método | Visibilidad | Retorno | Responsabilidad |
|---|---|---|---|
| `loadWords()` | `private` | `void` | Llamar a los cinco métodos de carga |
| `getRandomWord(Level level)` | `public` | `Word` | Elegir una palabra aleatoria del nivel indicado sin restringir categoría |
| `getRandomWord(Category category, Level level)` | `public` | `Word` | Elegir una palabra aleatoria de una categoría y nivel específicos |
| `selectRandomWord(Category category, Level level)` | `private` | `Word` | Filtrar candidatos en una lista temporal y elegir un elemento aleatorio |
| `getTotalWords()` | `public` | `int` | Retornar el tamaño del banco |

## Sobrecarga y encapsulación
Las dos versiones públicas de `getRandomWord` son un ejemplo de **sobrecarga de métodos**: se llaman igual, pero sus parámetros son distintos. Ambas delegan en un método privado común para evitar duplicar el código de filtrado y selección.

El método privado acepta `category == null` exclusivamente como señal interna de que la categoría puede ser cualquiera. La sobrecarga pública con categoría rechaza valores nulos. Un nivel nulo produce `IllegalArgumentException`; si no hay candidatos se lanza `NoSuchElementException`.

La clase no expone colecciones filtradas como parte de su interfaz pública: retorna una sola instancia de `Word`. La selección aleatoria no elimina elementos del banco, por lo que una palabra podría volver a salir en otra partida.

## Ejemplos
```java
WorkBank bank = new WorkBank();
Word easy = bank.getRandomWord(Level.FACIL);
Word mediumAnimal = bank.getRandomWord(Category.ANIMALES, Level.MEDIO);
int total = bank.getTotalWords(); // 200
```

El controlador del juego decidirá cuándo pasar de `FACIL` a `MEDIO` y luego a `DIFICIL`, y conservará la modalidad de categoría seleccionada por el jugador. `WorkBank` solamente proporciona las palabras.

## Pruebas unitarias
`src/test/java/com/poo/hangman/model/WorkBankTest.java` contiene seis métodos JUnit 5 que comprueban el total de palabras, la selección por nivel, la selección combinada, el rechazo de parámetros nulos y que las selecciones no alteren el tamaño del banco.

Desde NetBeans, ejecutar **Test File** sobre `WorkBankTest.java` o **Test Project** para la suite completa.
