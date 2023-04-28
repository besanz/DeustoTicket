# GuTicket - Backend

GuTicket es un proyecto para crear una aplicación de venta de entradas para eventos y conciertos destinada a consumidores (B2C). El sistema cuenta con un frontend y un backend. Este es el repositorio del backend, que se encarga de la lógica de negocio y la persistencia de datos.

## Características

El proyecto GuTicket se compone de dos frontends diferentes:

1. Un frontend para la compra de entradas por parte de los consumidores.
2. Un frontend para el personal de los eventos, que permite verificar los códigos QR generados al comprar una entrada.

El sistema también incluirá el envío de correos electrónicos para la compra de entradas, y cada entrada es nominativa para el usuario. La conexión entre el backend y los frontends se realiza a través de RMI.

Utilizamos un proveedor externo de venta de entradas llamado TicketProvider.

## Estructura del proyecto

El proyecto cuenta con una estructura de directorios organizada de la siguiente manera:

- GuTicket
    - Backend
        - (archivos y directorios del backend)
    - FrontendStaff
        - (archivos y directorios del frontend para el personal del evento)
    - FrontendUser
        - (archivos y directorios del frontend para los consumidores)

## Requisitos

Para ejecutar este proyecto, necesitarás tener instalado lo siguiente:

- Java SE Development Kit (JDK) versión 8 o posterior.
- Apache Ant para la construcción y ejecución del proyecto.

## Compilar y ejecutar

Para compilar el proyecto, abre una terminal en el directorio raíz del backend y ejecuta el siguiente comando:

```ant build```

Para ejecutar el proyecto, ejecuta el siguiente comando en la misma terminal:

```ant serverUser```

o

```ant serveStaff```

Dependiendo de qué parte del servidor deseas ejecutar.

**Universidad de Deusto, San Sebastián**