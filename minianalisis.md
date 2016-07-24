# Formulario para control de pacientes

_Todo es una pregunta._


## Preguntas por defecto y obligatorias

> -   Cédula.
> -   Nombre.
> -   Dirección.
> -   Teléfono.
> -   Contacto en caso de accidente.
> -   Instrucción (nivel de educación).
> -   Fecha de nacimiento.
> -   Sexo.

## Encuesta

Una encuesta conteniene las preguntas individuales (id, enunciado, respuesta). Las cuales pueden ser:

> -   [Textual](#pregunta-textual).
> -   [Numérica](#pregunta-numérica).
> -   [Booleana](#pregunta-booleana) (si/no).
> -   [Opción múltiple](#pregunta-de-opción-múltiple).
> -   [Fecha](#pregunta-fecha).
> -   [Hora](#pregunta-hora).

### Datos de la encuesta

> -   ID.
> -   Nombre.
> -   Descripción adicional.

### Preguntas

> Contiene un título, id, puede ser opcional, y puede tener una descripción.

#### Pregunta textual

> Como respuesta se tiene un texto, se pueden agregar validaciones adicionales como el tamaño máximo de caracteres, o una expresión regular (para el cliente se le presentarán expresiones regulares ya diseñadas como: números telefónicos, mails, etc).

#### Pregunta numérica

> Como respuesta se tiene un número dentro de un rango indicado (inclusivo), además se puede especificar si el intervalo es discreto o continuo (números enteros o reales).

#### Pregunta booleana

> Como respuesta se tiene 2 opciones (true/false) o null en caso de ser una pregunta opcional.

#### Pregunta de opción múltiple

> Como respuesta se tiene una una o varias opciones de una lista de opciones definidas por el encuestador, también existe la opción de poner una opción adicional para que el encuestado llene a criterio personal. Se puede restringir el número másimo de respuestas selecciondas, en caso de que se restringa una sola respuesta se presenta un combo box (list box).

#### Pregunta fecha

> Como respuesta se tiene un objeto Calendar, el cuál será guardado en formato dd-MM-yyyy


#### Pregunta hora

> Como respuesta se tiene un objeto Calendar, el cuál será guardado en formato hh:mm:ss.

### Condiciones adicionales

> -   Para preguntas de tipo numérico se debe poder hacer análisis estadísticos tales como promedios, sumatorias, desviación típica, desviación estándar, etc.
> -   Usar procedimientos almacenados.
> -   Gestor de consultas genérico.
> -   :exclamation: Cada vez que se inserte una respuesta, se disparará un trigger en una tabla con el dato del usuario (revisar a qué dato se hace referencia) y la fecha.
> -   Exportar archivo a un csv y a un pdf.
