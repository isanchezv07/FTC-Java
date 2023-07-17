# NAUTILUS 4010 
Bienbenido!


En esta carpeta vas a encontrar varios ejemplos ya hechos por los miembros de Nautilus para que puedan aprender como funciona la programacion detras de los robots de FTC(First Tech Challenge).

Es de suma importancia leer todos los comentarios que tenga el codigo, ya que es de suma importancia para entender el codigo y no perderse.

## Introducción

Este es el código fuente del equipo NAUTILUS 4010, desarrollado para la competencia FIRST Tech Challenge (FTC). NAUTILUS 4010 es un equipo de estudiantes apasionados por la robótica y la programación. Este README tiene como objetivo proporcionar una guía básica para comprender y utilizar el código.

El codigo que se estara explicando es: <a href="TeamCode/src/main/java/org/firstinspires/ftc/teamcode/HardWareMap.java" style="color: white; background-color: #5bc0de; padding: 5px 10px; text-decoration: none; border-radius: 5px;"> `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/HardWareMap.java`</a>
## Descripción General

El código NAUTILUS 4010 está escrito en Java y está diseñado para ser ejecutado en la plataforma FTC utilizando el SDK de FTC proporcionado por Qualcomm. El objetivo principal del código es controlar el robot para realizar diversas tareas durante las competencias de FTC.

## Estructura del Proyecto

El proyecto consta de varias clases, y en este README nos centraremos en la clase principal llamada `HardWareMap.java`. Esta clase se encarga de inicializar el hardware del robot y proporcionar métodos para realizar diferentes movimientos y acciones.

## Configuración del Hardware

Para inicializar el hardware del robot, utilizamos la clase `HardwareMap` proporcionada por el SDK de FTC. En el método `initializeHardware()`, se definen los motores de las ruedas (frontLeft, frontRight, backLeft y backRight) y el sensor inercial BNO055IMU (imu). Además, se establecen las direcciones de rotación de los motores según la configuración mecánica del robot.

## Control del Robot

La clase `HardWareMap` proporciona varios métodos para controlar el robot:

- `moveForward(double distance)`: Mueve el robot hacia adelante una distancia específica en centímetros.
- `turnRight(double angleToRotate)`: Rota el robot hacia la derecha en un ángulo específico en grados.
- `turnLeft(double angleToRotate)`: Rota el robot hacia la izquierda en un ángulo específico en grados.
- `lateralMove(double distance)`: Mueve el robot lateralmente una distancia específica en centímetros.
- `move(double drive, double lateral, double turn, double multiplier)`: Controla los motores del robot para moverse en todas las direcciones.
- `getChasisPowers()`: Devuelve un array con la potencia actual de cada motor del chasis.

## Sensores

El robot utiliza un sensor inercial BNO055IMU para obtener información sobre la orientación y la gravedad. El método `getAngle()` proporciona el ángulo de orientación actual del robot en grados.

## Configuración Adicional

La clase `HardWareMap` también contiene métodos para configurar y utilizar los encoders de los motores, así como para inicializar el sensor inercial con la configuración adecuada.

## Aviso

Este código es una base para el desarrollo del robot y puede ser modificado y mejorado según las necesidades del equipo. Se recomienda comprender completamente cada método antes de realizar cambios significativos.

## Contacto

Si tienes alguna pregunta o deseas obtener más información sobre NAUTILUS 4010, no dudes en contactarnos a través de nuestro sitio web o redes sociales.

¡Gracias por tu interés en NAUTILUS 4010! Esperamos que disfrutes aprendiendo y explorando el mundo de la robótica y la programación con nosotros. ¡Buena suerte en tus aventuras con FTC! 🚀

