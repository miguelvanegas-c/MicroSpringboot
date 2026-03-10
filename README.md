# MicroSpringboot

Este proyecto es el desarrollo de un servidor Web en Java como parte de un taller académico. A continuación, se describen los objetivos y características principales del proyecto, así como los pasos para el despliegue en AWS en una instancia EC2.

## Objetivos del Taller

- **Construcción de un servidor Web**: Desarrollar un servidor capaz de:
  - Entregar páginas HTML.
  - Servir imágenes tipo PNG.
- **Framework IoC**: Incluir un framework de inversión de control para permitir la construcción de aplicaciones Web a partir de POJOS (Plain Old Java Objects).
- **Aplicación de ejemplo**: Crear una aplicación web de ejemplo utilizando el servidor.
- **Manejo de solicitudes**: Habilitar el manejo de múltiples solicitudes no concurrentes.
- **Prototipo mínimo**: Demostrar las capacidades reflexivas de Java permitiendo cargar un bean (POJO) y derivar una aplicación web a partir de él.

## Despliegue en AWS

### Creación y Configuración de una Instancia EC2
1. **Creación de la instancia EC2**: 
   Se desplegó una instancia EC2 en AWS.
   ![Creación de la instancia en EC2](img.png)

2. **Conexión a la instancia**: 
   - Acceso a la instancia usando SSH.
   - Es necesario asignar permisos adecuados a la API key para realizar la conexión.
   - Nota: Inicialmente hubo problemas al intentar conectarse desde una red bloqueada (universitaria), pero se solucionó conectándose desde otra red.
   ![Conexión por SSH](img_1.png)
   ![Servidor en ejecución apuntando al DNS](img_2.png)

3. **Carga de archivos mediante SFTP**: 
   El servidor permite cargar clases utilizando SFTP para configuraciones adicionales.
   ![Carga de archivos mediante SFTP](img_4.png)

4. **Apertura de puertos necesarios**: 
   Es fundamental asegurarse de que los puertos necesarios para el servidor estén abiertos.
   ![Apertura de puertos](img_3.png)

## Uso del servidor
El servidor se encuentra desarrollado para servir aplicaciones web básicas y soporta la carga de Beans para generar estas aplicaciones dinámicamente.

### Ejecución del servidor
- Inicialización del servidor directamente desde la consola SSH.
![Servidor corriendo desde SSH](img_1.png)

## Integrantes del equipo
- Este taller fue desarrollado como parte de un curso académico para prácticas en Java.

Si deseas contribuir o usar este proyecto como base para tus exploraciones en IoC en Java, sigue las instrucciones descritas para construir y desplegar tu aplicación en una instancia EC2 de AWS. ¡Disfruta explorando y aprendiendo!