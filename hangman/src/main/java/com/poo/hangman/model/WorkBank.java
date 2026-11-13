package com.poo.hangman.model;

import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Banco de palabras que se van a utilizar dentro del juego
 *
 * @author Grupo 1
 */
public class WorkBank {

    private ArrayList<Word> words;

    public WorkBank() {
        this.words = new ArrayList<Word>();
        loadWords();
    }

    /** Carga las palabras de todas las categorías en el banco. */
    private void loadWords() {
        loadAnimals();
        loadCountries();
        loadSports();
        loadObjects();
        loadFoods();
    }

    private void loadAnimals() {
        load("PERRO", Category.ANIMALES, "Es el mejor amigo del hombre");
        load("GATO", Category.ANIMALES, "Animal domestico que ronronea");
        load("ELEFANTE", Category.ANIMALES, "Animal terrestre mas grande");
        load("JIRAFA", Category.ANIMALES, "Tiene el cuello más largo de todos");
        load("LEON", Category.ANIMALES, "Rey de la selva");
        load("TIGRE", Category.ANIMALES, "Felino de pelaje naranja con rayas negras");
        load("CABALLO", Category.ANIMALES, "Animal que transporta personas");
        load("VACA", Category.ANIMALES, "Da leche a los humanos");
        load("CERDO", Category.ANIMALES, "Es rosado y come todo");
        load("LORO", Category.ANIMALES, "Repite las palabras de las personas");
        load("CONEJO", Category.ANIMALES, "Es un animal pequeño de orejas largas que puede saltar rápidamente");
        load("OSO", Category.ANIMALES, "Es un animal que le encanta la miel");
        load("PATO", Category.ANIMALES, "Ave acuática que nada y emite un csonido característico");
        load("LOBO", Category.ANIMALES, "Cánido salvaje y social que aúlla a la luna");
        load("RANA", Category.ANIMALES, "Anfibio de piel húmeda que salta lejos y croa en charcas");
        load("BURRO", Category.ANIMALES, "Pariente cercano del caballo, de orejas largas y gran resistencia");
        load("MONO", Category.ANIMALES, "Animal vivaz y ágil que trepa por los árboles comiendo bananas");
        load("CEBRA", Category.ANIMALES, "Animal inconfundible por sus rayas blancas y negras");
        load("OVEJA", Category.ANIMALES, "Animal conocido por su lana");
        load("ZORRO", Category.ANIMALES, "Animal astuto y salvaje con una larga y frondosa cola");
        load("DELFIN", Category.ANIMALES, "Inteligente mamífero marino famoso por su simpatía y saltos");
        load("TORTUGA", Category.ANIMALES, "Animal pacífico que camina lento cargando su propia casa a cuestas");
        load("GALLINA", Category.ANIMALES, "Ave doméstica conocida por poner huevos");
        load("PINGUINO", Category.ANIMALES, "Ave marina que no vuela y vive en el hielo");
        load("COCODRILO", Category.ANIMALES, "Temible reptil con poderosas mandíbulas y piel escamosa");
        load("MARIPOSA", Category.ANIMALES, "Delicado insecto con alas coloridas");
        load("HIPOPOTAMO", Category.ANIMALES, "Animal gigantesco y pesado en los ríos");
        load("MURCIELAGO", Category.ANIMALES, "El único mamífero capaz de volar en la noche");
        load("CANGURO", Category.ANIMALES, "Animal australiano que transporta a su cría en una bolsa");
        load("BUHO", Category.ANIMALES, "Ave rapaz de hábitos nocturnos y grandes ojos fijos muy expresivos");
        load("SERPIENTE", Category.ANIMALES, "Reptil sin extremidades que se desplaza arrastrandose sigilosamente");
        load("ARDILLA", Category.ANIMALES, "Roedor veloz de cola poblada que trepa árboles y oculta nueces");
        load("HORMIGA", Category.ANIMALES, "Insecto diminuto que destaca por su increíble fuerza y trabajo en equipo");
        load("TIBURON", Category.ANIMALES, "Temido depredador marino con un olfato y aleta inconfundibles");
        load("PULPO", Category.ANIMALES, "Animal marino coninteligencia sorprendente y ocho tentáculos");
        load("AGUILA", Category.ANIMALES, "Majestuosa ave rapaz de visión privilegiada y vuelo señorial");
        load("GORILA", Category.ANIMALES, "Animal robusto de gran tamaño que habita en los bosques africanos");
        load("CAMELLO", Category.ANIMALES, "Resistente animal del desierto caracterizado por sus jorobas");
        load("RINOCERONTE", Category.ANIMALES, "Gran mamífero con una piel gruesa y un cuerno nasal");
        load("CHIMPANCE", Category.ANIMALES, "Primate altamente inteligente y cercano evolutivamente al ser humano");
        load("PEZ", Category.ANIMALES, "Vive en el agua, respira por branquias y tiene escamas.");
    }

    private void loadCountries() {
        load("PERU", Category.PAISES, "País andino cuya capital es Lima y donde se encuentra Machu Picchu");
        load("CHILE", Category.PAISES, "Territorio sudamericano extremadamente alargado junto al Océano Pacífico");
        load("IRAN", Category.PAISES, "Antiguo y milenario territorio que heredó la cultura de la antigua Persia");
        load("INDIA", Category.PAISES, "Colosal país del sur asiático hogar del majestuoso monumento Taj Mahal");
        load("JAPON", Category.PAISES, "Archipiélago asiático conocido poéticamente como la tierra del sol naciente");
        load("CHINA", Category.PAISES, "Poblado gigante asiático célebre por su monumental e histórica muralla");
        load("ITALIA", Category.PAISES, "Famosa nación europea cuya silueta geográfica imita una elegante bota");
        load("BRASIL", Category.PAISES, "El país más extenso de Sudamérica, célebre por su fiesta y alegría");
        load("MEXICO", Category.PAISES, "Colorido país norteamericano famoso por sus mariachis y su rica gastronomía");
        load("FRANCIA", Category.PAISES, "Destino europeo occidental cuyo símbolo más universal es la Torre Eiffel");
        load("PORTUGAL", Category.PAISES, "Acogedora nación ibérica frente al Atlántico cuya capital es Lisboa");
        load("ALEMANIA", Category.PAISES, "Potencia económica centroeuropea marcada históricamente por su muro");
        load("ARGENTINA", Category.PAISES, "País austral famoso por su pasión futbolera y las notas del tango");
        load("COLOMBIA", Category.PAISES, "Nación cafetera situada en el extremo norte de América del Sur");
        load("ECUADOR", Category.PAISES, "País andino atravesado de norte a sur por la propia línea ecuatorial");
        load("URUGUAY", Category.PAISES, "Tranquilo país sudamericano cuya capital histórica es Montevideo");
        load("BOLIVIA", Category.PAISES, "Nación andina sin salida al mar que alberga el impresionante salar de Uyuni");
        load("PANAMA", Category.PAISES, "Puente terrestre americano famoso por su estratégica vía interoceánica");
        load("EGIPTO", Category.PAISES, "Milenaria tierra norafricana de faraones y enigmáticas pirámides");
        load("GRECIA", Category.PAISES, "Cuna histórica occidental donde nació el concepto de la democracia");
        load("TURQUIA", Category.PAISES, "Fascinante territorio euroasiático que une dos continentes diferentes");
        load("RUSIA", Category.PAISES, "El gigante territorial más extenso y grande de todo el planeta");
        load("CANADA", Category.PAISES, "Inmenso país norteamericano cuyo emblema nacional es la hoja de arce");
        load("KENIA", Category.PAISES, "Nación del este africano famosa por sus espectaculares safaris naturales");
        load("NEPAL", Category.PAISES, "Montañoso país asiático en el que se alza la cima del Everest");
        load("SUECIA", Category.PAISES, "Nación escandinava del norte europeo cuya capital es Estocolmo");
        load("NORUEGA", Category.PAISES, "País nórdico europeo famoso por sus profundos e impresionantes fiordos");
        load("HOLANDA", Category.PAISES, "Territorio europeo famoso por sus coloridos campos de tulipanes y molinos");
        load("SUIZA", Category.PAISES, "País alpino neutral reconocido por sus exquisitos chocolates y relojes");
        load("IRLANDA", Category.PAISES, "Verdosa isla atlántica famosa por sus prados y la leyenda del trébol");
        load("JAMAICA", Category.PAISES, "Isla caribeña alegre que vio nacer los ritmos del género reggae");
        load("VENEZUELA", Category.PAISES, "Nación caribeña y sudamericana donde brota el imponente Salto Ángel");
        load("GUATEMALA", Category.PAISES, "Centro de gran riqueza cultural heredera directa del imperio maya");
        load("NICARAGUA", Category.PAISES, "Tierra centroamericana de lagos extensos y volcanes imponentes");
        load("AUSTRALIA", Category.PAISES, "Inmensa isla continente famosa por sus marsupiales únicos como el koala");
        load("MARRUECOS", Category.PAISES, "Exótico país del norte de África cuya capital administrativa es Rabat");
        load("FILIPINAS", Category.PAISES, "Archipiélago asiático conformado por miles de islas paradisíacas");
        load("ISLANDIA", Category.PAISES, "Remota isla nórdica caracterizada por sus contrastes de hielo y fuego");
        load("HONDURAS", Category.PAISES, "Nación centroamericana con rica historia precolombina y capital Tegucigalpa");
    }

    private void loadSports() {
        load("GOLF", Category.DEPORTES, "Disciplina de precisión donde se usan palos para introducir la bola en hoyos");
        load("JUDO", Category.DEPORTES, "Arte marcial tradicional de origen japonés enfocado en proyecciones");
        load("POLO", Category.DEPORTES, "Elegante deporte de equipo donde se monta a caballo golpeando con un mazo");
        load("SURF", Category.DEPORTES, "Emocionante disciplina acuática que consiste en deslizarse de pie sobre las olas");
        load("TENIS", Category.DEPORTES, "Deporte de raquetas y red donde se cruza la pelota en una cancha");
        load("BOXEO", Category.DEPORTES, "Combate de contacto directo donde dos contrincantes usan guantes en un ring");
        load("RUGBY", Category.DEPORTES, "Intenso deporte de colaboración y oposición con un balón ovalado");
        load("REMO", Category.DEPORTES, "Deporte náutico de fuerza donde se desplaza una embarcación con palas");
        load("VELA", Category.DEPORTES, "Navegación deportiva donde las embarcaciones se impulsan con el viento");
        load("PADEL", Category.DEPORTES, "Deporte de raqueta dinámico que se juega en parejas dentro de una pista acristalada");
        load("SKATE", Category.DEPORTES, "Deporte urbano acrobático practicado sobre una tabla con ruedas");
        load("LUCHA", Category.DEPORTES, "Combate cuerpo a cuerpo donde se busca derribar al contrincante");
        load("ESQUI", Category.DEPORTES, "Desplazamiento invernal sobre la nieve usando tablas ajustadas a los pies");
        load("FUTBOL", Category.DEPORTES, "El deporte rey mundial donde se usa el pie para marcar goles");
        load("HOCKEY", Category.DEPORTES, "Dinámico juego de equipo donde se maneja un disco o pelota con bastones");
        load("KARATE", Category.DEPORTES, "Arte marcial japonés de defensa personal basado en golpes secos");
        load("BOLOS", Category.DEPORTES, "Juego de precisión enfocado en derribar pinos lanzando una bola pesada");
        load("DARDOS", Category.DEPORTES, "Juego de puntería que consiste en lanzar flechas pequeñas a una diana");
        load("BILLAR", Category.DEPORTES, "Deporte de mesa donde se golpean bolas numeradas con la ayuda de un taco");
        load("NATACION", Category.DEPORTES, "Desplazamiento humano autónomo y fluido a través del agua en piscina");
        load("CICLISMO", Category.DEPORTES, "Prueba de resistencia basada en el pedaleo constante sobre dos ruedas");
        load("ESGRIMA", Category.DEPORTES, "Duelo de caballerosidad y reflejos usando armas blancas reglamentarias");
        load("BEISBOL", Category.DEPORTES, "Deporte tradicional americano de bate, guante y carreras a bases");
        load("VOLEIBOL", Category.DEPORTES, "Deporte de red alta donde el balón se golpea exclusivamente con las manos");
        load("ATLETISMO", Category.DEPORTES, "Conjunto de pruebas que incluye carreras, saltos y diversos lanzamientos");
        load("GIMNASIA", Category.DEPORTES, "Disciplina que exige gran flexibilidad, piruetas y equilibrio en aparatos");
        load("BALONCESTO", Category.DEPORTES, "Deporte de canastas elevadas y ritmo rápido jugado con las manos");
        load("BALONMANO", Category.DEPORTES, "Enérgico juego de equipo donde se anota lanzando el esférico con la mano");
        load("EQUITACION", Category.DEPORTES, "Arte hípico de control y salto de obstáculos montando a caballo");
        load("ARQUERIA", Category.DEPORTES, "Práctica milenaria de precisión basada en tensar un arco y soltar flechas");
        load("BADMINTON", Category.DEPORTES, "Deporte de raqueta donde se golpea un volante ligero por encima de la red");
        load("SNOWBOARD", Category.DEPORTES, "Deslizamiento acrobático sobre la nieve utilizando una tabla única");
        load("TAEKWONDO", Category.DEPORTES, "Arte marcial coreano mundialmente conocido por sus veloces patadas");
        load("PATINAJE", Category.DEPORTES, "Desplazamiento veloz sobre superficies con ruedas o cuchillas de hielo");
        load("ESCALADA", Category.DEPORTES, "Deporte de aventura que consiste en ascender por paredes verticales de roca");
        load("SOFTBOL", Category.DEPORTES, "Variante de campo más cercana y derivada tradicionalmente del béisbol");
        load("WATERPOLO", Category.DEPORTES, "Intenso deporte de pelota desarrollado dentro de una piscina con porterías");
        load("PENTATLON", Category.DEPORTES, "Prueba multidisciplinaria que engloba cinco especialidades atléticas");
        load("TRIATLON", Category.DEPORTES, "Dura prueba de resistencia que combina natación, ciclismo y carrera");
        load("MARATON", Category.DEPORTES, "Carrera de fondo extrema que exige recorrer más de cuarenta kilómetros");
    }

    private void loadObjects() {
        load("LLAVE", Category.OBJETOS, "Utensilio metálico indispensable diseñado para abrir y cerrar cerraduras");
        load("MESA", Category.OBJETOS, "Mueble de superficie plana que se utiliza para comer y trabajar");
        load("SILLA", Category.OBJETOS, "Asiento individual provisto de respaldo diseñado para una persona");
        load("LAPIZ", Category.OBJETOS, "Instrumento de escritura con mina de grafito que permite borrar");
        load("RELOJ", Category.OBJETOS, "Dispositivo portátil o de pared encargado de medir y marcar las horas");
        load("REGLA", Category.OBJETOS, "Instrumento plano y graduado que sirve para medir distancias y trazar");
        load("PEINE", Category.OBJETOS, "Pequeño utensilio de púas destinado a ordenar y arreglar el cabello");
        load("LIBRO", Category.OBJETOS, "Conjunto de hojas encuadernadas que almacenan historias y conocimientos");
        load("PUERTA", Category.OBJETOS, "Panel móvil que regula el acceso abriéndose y cerrándose en un vano");
        load("CAMARA", Category.OBJETOS, "Aparato electrónico tecnológico diseñado para capturar y fijar fotografías");
        load("LAMPARA", Category.OBJETOS, "Objeto decorativo o funcional que emite luz artificial en estancias");
        load("TIJERAS", Category.OBJETOS, "Herramienta cortante de dos hojas articuladas ideal para papel y telas");
        load("PARAGUAS", Category.OBJETOS, "Accesorio portátil desplegable que protege eficazmente de la lluvia");
        load("ESPEJO", Category.OBJETOS, "Superficie pulida de vidrio que refleja fielmente la imagen visual");
        load("CUCHARA", Category.OBJETOS, "Utensilio cóncavo de cocina indispensable para comer sopas y caldos");
        load("TENEDOR", Category.OBJETOS, "Cubierto dotado de púas destinado a pinchar y llevar comida a la boca");
        load("CUCHILLO", Category.OBJETOS, "Herramienta afilada de cocina diseñada para cortar alimentos");
        load("ALMOHADA", Category.OBJETOS, "Cojín mullido y blando que sirve para apoyar la cabeza al descansar");
        load("VENTANA", Category.OBJETOS, "Abertura practicada en un muro que deja pasar la luz y el aire");
        load("CUADERNO", Category.OBJETOS, "Libreta de notas utilizada habitualmente para apuntes escolares o de trabajo");
        load("MOCHILA", Category.OBJETOS, "Bolsa de lona o tela resistente que se cuelga cómodamente a la espalda");
        load("ZAPATO", Category.OBJETOS, "Prenda de calzado diseñada para proteger y cubrir el pie humano");
        load("SOMBRERO", Category.OBJETOS, "Accesorio de vestir ideado para cubrir la cabeza del sol y el frío");
        load("GAFAS", Category.OBJETOS, "Lentes con montura que ayudan a corregir la visión o proteger del sol");
        load("TELEFONO", Category.OBJETOS, "Dispositivo de comunicación diseñado para hablar a distancia");
        load("COMPUTADORA", Category.OBJETOS, "Equipo informático avanzado provisto de pantalla, teclado y ratón");
        load("TELEVISOR", Category.OBJETOS, "Pantalla electrónica de entretenimiento que muestra programas y películas");
        load("CALCULADORA", Category.OBJETOS, "Aparato electrónico portátil que efectúa operaciones matemáticas");
        load("MARTILLO", Category.OBJETOS, "Herramienta pesada de impacto utilizada principalmente para clavar clavos");
        load("TORNILLO", Category.OBJETOS, "Pieza metálica estriada que se enrosca con firmeza en superficies");
        load("BICICLETA", Category.OBJETOS, "Vehículo de dos ruedas impulsado por la fuerza humana mediante pedales");
        load("ESCOBA", Category.OBJETOS, "Utensilio de cerdas largas que sirve para barrer y limpiar suelos");
        load("CEPILLO", Category.OBJETOS, "Herramienta de cerdas múltiples para la higiene dental o el aseo");
        load("BUFANDA", Category.OBJETOS, "Prenda larga de abrigo tejida para proteger y calentar el cuello");
        load("GUANTES", Category.OBJETOS, "Prendas textiles independientes que cubren y abrigan las manos");
        load("BOLIGRAFO", Category.OBJETOS, "Instrumento moderno de escritura que libera tinta fluida de forma continua");
        load("AUDIFONOS", Category.OBJETOS, "Altavoces pequeños diseñados para escuchar música en rigurosa privacidad");
        load("ENCHUFE", Category.OBJETOS, "Dispositivo eléctrico que conecta aparatos a la corriente de la red");
        load("CANDADO", Category.OBJETOS, "Dispositivo de seguridad portátil que cierra cadenas, puertas o taquillas");
        load("BRUJULA", Category.OBJETOS, "Instrumento de orientación imantado que señala siempre hacia el norte");
    }

    private void loadFoods() {
        load("PAN", Category.COMIDAS, "Alimento básico horneado a base de harina que acompaña cualquier plato");
        load("ARROZ", Category.COMIDAS, "Grano blanco y nutritivo fundamental en infinidad de cocinas mundiales");
        load("SOPA", Category.COMIDAS, "Caldo líquido reconfortante que se consume habitualmente con cuchara");
        load("PIZZA", Category.COMIDAS, "Popular plato horneado de masa redonda con tomate y fundido queso");
        load("QUESO", Category.COMIDAS, "Derivado lácteo sólido obtenido por maduración de la cuajada de leche");
        load("HUEVO", Category.COMIDAS, "Alimento de gran valor proteico producido naturalmente por las aves");
        load("PASTA", Category.COMIDAS, "Alimento italiano a base de harina que se hierve antes de comer");
        load("TACO", Category.COMIDAS, "Antojito mexicano consistente en una tortilla doblada con diversos rellenos");
        load("MANZANA", Category.COMIDAS, "Fruta crujiente de árbol que puede encontrarse en tonalidades rojas o verdes");
        load("BANANA", Category.COMIDAS, "Fruta tropical alargada de agradable sabor dulce y piel amarilla");
        load("NARANJA", Category.COMIDAS, "Cítrico jugoso y refrescante que comparte nombre con su tonalidad");
        load("SANDIA", Category.COMIDAS, "Fruta gigante de verano, pulpa roja muy jugosa y gran contenido de agua");
        load("FRESA", Category.COMIDAS, "Pequeña fruta roja de superficie rugosa, dulce y muy aromática");
        load("LIMON", Category.COMIDAS, "Fruto cítrico redondo caracterizado por su jugo sumamente ácido");
        load("TOMATE", Category.COMIDAS, "Fruto rojo comestible indispensable en ensaladas y ricas salsas");
        load("CEBOLLA", Category.COMIDAS, "Bulbo vegetal de capas concéntricas que suele hacer llorar al cortarlo");
        load("ZANAHORIA", Category.COMIDAS, "Hortaliza subterránea de color naranja intenso y textura crujiente");
        load("PATATA", Category.COMIDAS, "Tubérculo versátil que se consume habitualmente frito o cocido");
        load("ENSALADA", Category.COMIDAS, "Plato fresco compuesto por una mezcla de vegetales aliñados");
        load("HAMBURGUESA", Category.COMIDAS, "Jugosa carne picada cocinada y servida entre dos mitades de pan");
        load("EMPANADA", Category.COMIDAS, "Masa crujiente rellena de carnes o verduras cocinada al horno");
        load("CHOCOLATE", Category.COMIDAS, "Dulce irresistible derivado del cacao adorado en todo el mundo");
        load("GALLETA", Category.COMIDAS, "Pequeño bizcocho horneado, plano y de textura crujiente");
        load("HELADO", Category.COMIDAS, "Postre cremoso y frío muy popular durante las épocas calurosas");
        load("MERMELADA", Category.COMIDAS, "Conservación dulce de fruta cocida lentamente junto con azúcar");
        load("TORTILLA", Category.COMIDAS, "Preparación culinaria a base de huevo o de fina masa de maíz");
        load("LENTEJAS", Category.COMIDAS, "Legumbre redonda y pequeña de gran valor nutritivo en guisos");
        load("FRIJOLES", Category.COMIDAS, "Legumbres versátiles que protagonizan numerosos y ricos guisos");
        load("AGUACATE", Category.COMIDAS, "Fruto cremoso de piel rugosa y semilla central, base del guacamole");
        load("PESCADO", Category.COMIDAS, "Alimento saludable y proteico extraído directamente del mar o ríos");
        load("SALCHICHA", Category.COMIDAS, "Embutido alargado de carne sazonada muy fácil de preparar");
        load("JAMON", Category.COMIDAS, "Embutido o carne curada procedente tradicionalmente del cerdo");
        load("PAELLA", Category.COMIDAS, "Emblemático plato tradicional de arroz seco originario de Valencia");
        load("CEVICHE", Category.COMIDAS, "Plato marino de pescado fresco marinado en jugoso limón y especias");
        load("TAMAL", Category.COMIDAS, "Masa de maíz rellena envuelta y cocida tradicionalmente en hojas");
        load("CHURROS", Category.COMIDAS, "Masa frita alargada y crujiente que se suele mojar en chocolate caliente");
        load("YOGUR", Category.COMIDAS, "Alimento lácteo fermentado de textura suave y ligeramente ácida");
        load("MANTEQUILLA", Category.COMIDAS, "Producto graso derivado de la leche que se unta comúnmente en el pan");
        load("ALBONDIGAS", Category.COMIDAS, "Deliciosas bolitas preparadas con carne picada y especias en salsa");
        load("ESPAGUETI", Category.COMIDAS, "Variedad de pasta alimenticia con forma de cordones largos y finos");
    }

    /**
     * Selecciona una palabra aleatoria del nivel solicitado, sin restringir
     * la categoría.
     *
     * @param level nivel de dificultad requerido
     * @return una palabra aleatoria del nivel indicado
     * @throws IllegalArgumentException si el nivel es nulo
     * @throws NoSuchElementException si no hay palabras disponibles
     */
    public Word getRandomWord(Level level) {
        return selectRandomWord(null, level);
    }

    /**
     * Selecciona una palabra aleatoria de una categoría y nivel específicos.
     *
     * @param category categoría elegida por el jugador
     * @param level nivel de dificultad requerido
     * @return una palabra aleatoria que cumpla ambos criterios
     * @throws IllegalArgumentException si la categoría o el nivel son nulos
     * @throws NoSuchElementException si no hay palabras disponibles
     */
    public Word getRandomWord(Category category, Level level) {
        if (category == null) {
            throw new IllegalArgumentException("La categoría no puede ser nula");
        }
        return selectRandomWord(category, level);
    }

    /**
     * Filtra internamente los candidatos y elige una palabra aleatoria.
     * Una categoría nula indica que se acepta cualquiera (uso interno).
     *
     * @param category categoría requerida, o null para cualquiera
     * @param level nivel de dificultad requerido
     * @return una palabra aleatoria de los candidatos disponibles
     */
    private Word selectRandomWord(Category category, Level level) {
        if (level == null) {
            throw new IllegalArgumentException("El nivel no puede ser nulo");
        }

        ArrayList<Word> candidates = new ArrayList<>();
        for (Word word : this.words) {
            if (word.getLevel() == level
                    && (category == null || word.getCategory() == category)) {
                candidates.add(word);
            }
        }

        if (candidates.isEmpty()) {
            throw new NoSuchElementException(
                    "No hay palabras disponibles para los criterios seleccionados");
        }

        int index = ThreadLocalRandom.current().nextInt(candidates.size());
        return candidates.get(index);
    }

    /**
     * Devuelve la cantidad total de palabras disponibles.
     *
     * @return número de palabras del banco
     */
    public int getTotalWords() {
        return this.words.size();
    }

    /**
     * Agrega una nueva palabra al banco de palabras.
     *
     * @param text texto de la palabra
     * @param category categoría a la que pertenece
     * @param clue pista relacionada con la palabra
     */
    private void load(String text, Category category, String clue) {
        this.words.add(new Word(text, category, clue));
    }
}
