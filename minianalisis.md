# Formulario para control de pacientes

_Todo es una pregunta._

## Preguntas por defecto y obligatorias

> -   Cédula.
> -   Nombre.
> -   Dirección.
> -   Teléfono.
> -   Contacto en caso de accidente.
> -   :exclamation: Instrucción (revisar a qué hace referencia).
> -   Fecha de nacimiento.
> -   Sexo.
> -   :exclamation: Tipo (revisar a qué hace referencia).

## Conjunto de preguntas

:exclamation: ¿Una encuesta está compuesta de conjuntos o un conjunto es una encuesta?

Cada conjunto debe contener las preguntas individuales (id, enunciado, respuesta). Las cuales pueden ser:

> -   [Textual](#pregunta-textual).
> -   [Numérica](#pregunta-numérica) (respuesta sólo admite rango de números).
> -   [Booleana](#pregunta-booleana) (si/no).
> -   [Opción múltiple](#pregunta-de-opción-múltiple) (máximo número de selecciones).
> -   [Fecha](#pregunta-fecha) ().
> -   [Hora](#pregunta-hora) ().

### Datos del conjunto

> -   ID de categoría (o conjunto).
> -   Nombre de la categoría (o conjunto).

### Preguntas

> Contiene un título, id, puede ser opcional.

#### Pregunta textual

> Como respuesta es un texto, se pueden agregar validaciones adicionales como el tamaño máximo de caracteres, o una expresión regular (para el cliente se le presentarán expresiones regulares ya diseñadas como: números telefónicos, mails, etc).

#### Pregunta numérica

> :exclamation: TODO

#### Pregunta booleana

> :exclamation: TODO

#### Pregunta de opción múltiple

> Como respuesta es una una o varias opciones de una lista de opciones definidas por el usuario, también existe la opción de poner una opción adicional para que el usuario llene al criterio personal. Se puede restringir el número de respuestas selecciondas, en caso de que se restringa una sola respuesta se presenta un combo box (ListBox).

### Condiciones adicionales

> -   Para preguntas de tipo numérico se debe poder hacer análisis estadísticos tales como promedios, sumatorias, desviación típica, desviación estándar, etc.
> -   Usar procedimientos almacenados.
> -   Gestor de consultas genérico.
> -   :exclamation: Cada vez que se inserte una respuesta, se disparará un trigger en una tabla con el dato del usuario (revisar a qué dato se hace referencia) y la fecha.
> -   Exportar archivo a un csv y a un pdf.
