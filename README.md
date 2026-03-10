# MicroSpringboot

Este proyecto es el desarrollo de un servidor Web en Java como parte de un taller académico. A continuación, se describen los objetivos y características principales del proyecto, así como los pasos para el despliegue en AWS en una instancia EC2.

## Que necesita para funcionar??
### Dependencias
> - Java 21
> - mvn 3.3.0
### Como correrlo
- Primero compilar el proyecto usando
```bash
   mvn clean install
```
- Despues desde la raiz del proyecto, correr el siguiente comando:
```bash
   java -cp target/classes co.edu.escuelaing.MicroSpringBoot
```
## Objetivos del Taller

- **Framework IoC**: Incluir un framework de inversión de control para permitir la construcción de aplicaciones Web a partir de POJOS (Plain Old Java Objects).
- **Aplicación de ejemplo**: Crear una aplicación web de ejemplo utilizando el servidor.
- **Manejo de solicitudes**: Habilitar el manejo de múltiples solicitudes no concurrentes.
- **Prototipo mínimo**: Demostrar las capacidades reflexivas de Java permitiendo cargar un bean (POJO) y derivar una aplicación web a partir de él.

## Descripcion del proyecto
### Anotaciones
- Se crearon las anotaciones GetMapping, RequestParam y RestController
![img.png](img.png)
- Se declaro una clase como @interface.
- Se uso @Retention para que la anotacion se mantenga mientras el programa esta en ejecucion.
- Se uso @Target para saber que elemento va a marcar la anotacion.
- Se definieron en los casos necesarios, valores y en un caso un valor default en caso de no encontrar el otro valor.

### Controllers (POJO)
- Se crearon los siguientes controladores.
![img_1.png](img_1.png)
- Se marcaron los controladores con las anotaciones @RestController
- Se marcaron los metodos con la anotacion @GetMapping y un valor interno que define la ruta.
- Se marco un parametro con @RequestParam para obtener el valor de dicho parametro y declarar un valor default en caso de encontrarlo.

### HttpServer
- El guarda rutas, con sus respectivos metodos e instancias de los se marcaron con las etiquetas @RestController y @Getmapping
- Esta clase crea la conexion con el browser que quiera hacer una solicitud.
- Este recibe solicitudes extrae parametro y ejecuta los metodos correspondientes.
- Registra metodos e instancias de los controladores.
### MicroSpringBoot
- Aca se inicia el servicio de nuestro Micro SpringBoot y realiza las operaciones necesarias para la IoC
> 1. ![img_2.png](img_2.png)
>     - En el metodo main inicia el servidor http y sigue dos caminos, en caso de que se halla especificado un controlador, lo carga directamente, en caso de que no escanea el classPath.
> 2. ![img_3.png](img_3.png)
>     - Este carga un controlador, verificando que la clase especificada contenga la anotacion RestController, creando una instancia de este y recorriendo sus metodos buscando la anotacion de getMapping, al encontrarlo obtiene el valor de la ruta y lo regista en el server.
> 3. ![img_4.png](img_4.png)
>     - El metodo escanea el classpath por completo, al ingresar valida si es un directorio y en caso de serlo comienza su escaneo.
> 4. ![img_5.png](img_5.png)
>     - Escanea el directorio de forma recursiva hasta encontrar archivos, al encontrarlos formatra la URI para crear el path de una clase y cargarla con el metodo loadClass.

## Integrantes del equipo
- Miguel Angel Vanegas Cardenas.

Si deseas contribuir o usar este proyecto como base para tus exploraciones en IoC en Java, sigue las instrucciones descritas para construir y desplegar tu aplicación en una instancia EC2 de AWS. ¡Disfruta explorando y aprendiendo!

