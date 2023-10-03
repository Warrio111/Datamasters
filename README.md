# Datamasters

Proyecto de OnlineStore con Spring Boot (MVC)

Este proyecto está basado en Spring Boot y sigue la arquitectura Modelo-Vista-Controlador (MVC). A continuación, se describe cómo trabajar en este proyecto y contribuir de manera efectiva.

## Flujo de Trabajo

### 1. Clonar el Repositorio

Primero, clona el repositorio desde la rama principal (Main) a tu máquina local.

``` bash
git clone https://github.com/Warrio111/Datamasters.git
cd Datamasters
```
### 2. Actualizar la Rama Main
Asegúrate de tener la última versión de la rama Main antes de empezar a trabajar en tu desarrollo.

```bash

git checkout main
git pull origin main
```
### 3. Crear una Rama de Desarrollo
Para cualquier nuevo desarrollo en la aplicación, crea una nueva rama de desarrollo desde Main. Asegúrate de darle un nombre descriptivo, por ejemplo, dev/nueva-funcionalidad.

```bash

git checkout -b dev/nueva-funcionalidad
```
### 4. Trabajar en tu Desarrollo
Realiza tus cambios y desarrollos en esta nueva rama. Asegúrate de escribir pruebas unitarias asociadas y verificar que todo funcione correctamente.

### 5. Actualizar desde Main
Antes de finalizar tu desarrollo, asegúrate de estar al día con la rama Main.

```bash
git checkout main
git pull origin main
```
### 6. Hacer un Merge de Main a tu Rama de Desarrollo
Integra cualquier cambio nuevo de Main en tu rama de desarrollo antes de finalizar.

```bash

git checkout dev/nueva-funcionalidad
git merge main
```
Resuelve cualquier conflicto si es necesario.

### 7. Enviar una Solicitud de Extracción (Pull Request)
Cuando tu desarrollo esté completo y funcionando correctamente, sube tu rama de desarrollo a GitHub y crea una Solicitud de Extracción (Pull Request) hacia la rama Main. Asigna a Robert Benavides como revisor.

### 8. Revisión y Aprobación
Robert Benavides revisará tu código y, una vez aprobado, se fusionará con la rama Main.

# Contacto
Si tienes alguna pregunta o necesitas ayuda, no dudes en ponerte en contacto con el equipo de desarrollo.

Este flujo de trabajo te ayudará a mantener un proyecto ordenado y colaborativo, asegurando que todos los cambios se integren correctamente y que la rama Main siempre esté en buen estado.
