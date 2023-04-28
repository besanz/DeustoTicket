# GuTicket - FrontendStaff

GuTicket es un proyecto para crear una aplicación de venta de entradas para eventos y conciertos destinada a consumidores (B2C). El sistema cuenta con un frontend para los consumidores y un frontend para el personal de los eventos. Este es el repositorio del FrontendStaff, que permite verificar los códigos QR generados al comprar una entrada.

## Características

El FrontendStaff permite a los empleados de los eventos:

1. Escanear códigos QR de las entradas compradas por los consumidores.
2. Verificar la validez de las entradas y permitir o denegar el acceso al evento.

La conexión entre el FrontendStaff y el backend se realiza a través de RMI.

## Requisitos

Para ejecutar este proyecto, necesitarás tener instalado lo siguiente:

- Java SE Development Kit (JDK) versión 8 o posterior.
- Apache Ant para la construcción y ejecución del proyecto.

## Compilar y ejecutar

Para compilar el proyecto, abre una terminal en el directorio raíz de FrontendStaff y ejecuta el siguiente comando:

```ant build```

Para ejecutar el cliente, ejecuta el siguiente comando en la misma terminal:

```ant client```

**Universidad de Deusto, San Sebastián**