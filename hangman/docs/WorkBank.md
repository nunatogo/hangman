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

## Métodos nuevos

| Método | Visibilidad | Retorno | Descripción |
|---|---|---|---|
| `loadWords()` | `private` | `void` | Invoca los cinco métodos privados de carga |
| `getWordsByLevel(Level level)` | `private` | `ArrayList<Word>` | Retorna una lista nueva con las palabras cuyo nivel coincide |
| `getWordsByCategoryAndLevel(Category category, Level level)` | `private` | `ArrayList<Word>` | Retorna una lista nueva filtrada por ambos criterios |
| `getTotalWords()` | `public` | `int` | Retorna `this.words.size()` |

Los filtros recorren `this.words` y construyen una colección aparte, sin quitar palabras del banco original. Si reciben valores nulos, lanzan `IllegalArgumentException`. Un filtro sin coincidencias retorna una lista vacía.

**Importante:** Los filtros son privados, por solicitud de diseño. Si la clase del juego necesita seleccionar palabras por nivel o categoría, habrá que decidir qué método público expondrá esa funcionalidad sin romper la encapsulación.

## Pruebas unitarias
`src/test/java/com/poo/hangman/model/WorkBankTest.java` incluye cinco pruebas de total, reparto por nivel, reparto por categoría y nivel, conteo de países y ausencia de duplicados. Para probar los filtros privados se usa reflexión únicamente en los tests.

En NetBeans, después de actualizar `nunatogo-desa`, ejecutar `Test File` sobre `WorkBankTest.java` o `Test Project` para toda la suite.
