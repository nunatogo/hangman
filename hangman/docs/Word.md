# Documentación técnica: clase `Word`

**Proyecto:** Juego del Ahorcado Interactivo — POO Virtual, Universidad EAN  
**Paquete:** `com.poo.hangman.model`  
**Archivo fuente:** `src/main/java/com/poo/hangman/model/Word.java`  
**Equipo:** Grupo 1

## Propósito
La clase `Word` modela cada palabra del banco del juego. Guarda el texto que debe adivinar el jugador, la categoría a la que pertenece, la pista escrita y el nivel de dificultad. Su responsabilidad es representar esos datos y clasificar la palabra según su longitud; no selecciona palabras al azar, dibuja el ahorcado ni controla intentos.

## Principio de POO aplicado
**Encapsulación:** los cuatro atributos son `private` y se consultan o modifican mediante métodos públicos. El cálculo interno de dificultad se encuentra en el método privado `assignLevel()`.

La clase utiliza las enumeraciones `Category` y `Level`. No implementa por sí misma herencia ni polimorfismo; esos principios deben evaluarse en el conjunto del proyecto.

## Atributos

| Atributo | Tipo | Visibilidad | Descripción |
| --- | --- | --- | --- |
| `text` | `String` | `private` | Palabra que debe adivinarse |
| `clue` | `String` | `private` | Pista escrita asociada |
| `category` | `Category` | `private` | Categoría temática |
| `level` | `Level` | `private` | Dificultad calculada |

## Constructor
`Word(String text, Category category, String clue)`: asigna texto, categoría y pista. Calcula el nivel al crear el objeto con `assignLevel()`.

## Métodos

| Método | Retorno | Función |
| --- | --- | --- |
| `getText()` | `String` | Consultar el texto |
| `getClue()` | `String` | Consultar la pista escrita |
| `getCategory()` | `Category` | Consultar la categoría |
| `getLevel()` | `Level` | Consultar el nivel calculado |
| `setText(String text)` | `void` | Cambiar el texto y recalcular automáticamente el nivel |
| `setClue(String clue)` | `void` | Cambiar la pista |
| `setCategory(Category category)` | `void` | Cambiar la categoría |
| `setLevel()` | `void` | Recalcular explícitamente el nivel |
| `assignLevel()` | `Level` | Método privado que clasifica la longitud |

## Reglas de clasificación

| Longitud de la palabra | Nivel |
| --- | --- |
| 1 a 5 caracteres | `FACIL` |
| 6 a 8 caracteres | `MEDIO` |
| 9 o más caracteres | `DIFICIL` |

El nivel se calcula con `String.length()` (longitud del texto, no número de letras únicas).

## Ejemplo de uso

```java
Word word = new Word("CONEJO", Category.ANIMALES, "Animal de orejas largas");
System.out.println(word.getLevel()); // MEDIO

word.setText("ELEFANTES");
System.out.println(word.getLevel()); // DIFICIL
```

## Defecto encontrado y corrección

**Antes:** el nivel medio tenía `text.length() >= 6 || text.length() <= 8`. Esta condición también incluía palabras de nueve o más caracteres, por lo que nunca se alcanzaba `DIFICIL` para textos no nulos.

**Ahora:** se usa `&&` para delimitar de seis a ocho caracteres. También se recalcula el nivel al modificar el texto con `setText()`, evitando que el nivel quede desactualizado.

## Pruebas unitarias

Archivo: `src/test/java/com/poo/hangman/model/WordTest.java` (JUnit 5).

Los diez métodos de prueba cubren: inicialización del constructor, niveles fácil/medio/difícil, valores límite, modificación del texto, recálculo automático del nivel, cambio de pista, cambio de categoría y recálculo explícito.

En NetBeans, abrir el proyecto Maven y usar **Test Project** o **Test File** sobre `WordTest.java`; también se puede ejecutar `mvn test` en la carpeta que contiene `pom.xml`.

## Consideraciones
- La aplicación debe proporcionar un texto no nulo: el cálculo actual usa `text.length()`. Todavía no se validan entradas nulas ni cadenas vacías.
- Si se introducen palabras con espacios, la longitud también contará esos caracteres; se deberá definir cómo tratarlos al asignar niveles.
- Los nombres de clases y métodos están en inglés, mientras que la documentación permanece en español.
