# Formulario para control de pacientes

_Todo es una pregunta._

## Preguntas por defecto y obligatorias

> -   Cédula.
> -   Nombre.
> -   Dirección.
> -   Teléfono.
> -   Contacto en caso de accidente.
> -   Instrucción (revisar a qué hace referencia).
> -   Fecha de nacimiento.
> -   Sexo.
> -   Tipo (revisar a qué hace referencia).

## Conjunto de preguntas

Cada conjunto debe contener las preguntas individuales (id, enunciado, respuesta). Las cuales pueden ser:

> -   Textual.
> -   Numérica (respuesta sólo admite rango de números).
> -   Booleana (si/no).
> -   Opción múltiple (máximo número de selecciones).

### Datos del conjunto

> -   ID de categoría (o conjunto).
> -   Nombre de la categoría (o conjunto).

### Preguntas

> Contiene un título, id, puede ser opcional.

#### Pregunta textual

> Como respuesta es un texto, se pueden agregar validaciones adicionales como el tamaño máximo de caracteres, o una expresión regular (para el cliente se le presentarán expresiones regulares ya diseñadas como: números telefónicos, mails, etc).

#### Pregunta numérica

> TODO

#### Pregunta booleana

> TODO

#### Pregunta opción múltiple

> TODO

### Condiciones adicionales

> -   Para preguntas de tipo numérico se debe poder hacer análisis estadísticos tales como promedios, sumatorias, desviación típica, desviación estándar, etc.
> -   Usar procedimientos almacenados.
> -   Gestor de consultas genérico.
> -   Cada vez que se inserte una respuesta, se disparará un trigger en una tabla con el dato del usuario (revisar a qué dato se hace referencia) y la fecha.
> -   Exportar archivo a un csv y a un pdf.
