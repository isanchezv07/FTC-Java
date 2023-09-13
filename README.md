# NAUTILUS 4010
Bienvenido!




En esta carpeta vas a encontrar varios ejemplos ya hechos por los miembros de Nautilus 4010 para que puedan aprender cómo funciona la programación detrás de los robots de FTC(First Tech Challenge).


Es de suma importancia leer todos los comentarios que tenga el código, esto para comprender de una mejor manera el funcionamiento del código.

<br>

## ⚠Nota para Principiantes⚠

Si eres nuevo en la programación de robots FTC, no te preocupes. Este código está diseñado para ser fácilmente comprensible y modificado según tus necesidades. A medida que adquieras más experiencia, puedes explorar y modificar las funcionalidades del robot para realizar tareas más complejas.

<br>

## Introducción


Este es el código fuente del equipo NAUTILUS 4010, desarrollado para la competencia FIRST Tech Challenge (FTC). NAUTILUS 4010 es un equipo de estudiantes apasionados por la robótica y la programación. Este README tiene como objetivo proporcionar una guía básica para comprender y utilizar el código.


El código que se estará explicando es: <a href="TeamCode/src/main/java/org/firstinspires/ftc/teamcode/HardWareMap.java" style="color: white; background-color: #5bc0de; padding: 5px 10px; text-decoration: none; border-radius: 5px;"> `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/HardWareMap.java`</a>


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
<br>
<br>
<br>
<br>
# TeleOpRobot


## Función del programa TeleOpRobot


### Inicialización


Al ejecutar el programa TeleOpRobot, se lleva a cabo una secuencia de inicialización. En este paso, el robot establece la conexión con los componentes de hardware necesarios para su funcionamiento. Además, se muestra información de telemetría en la pantalla para verificar que todo esté en orden.

El codigo que se estará explicando es: <a href="TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOpRobot.java" style="color: white; background-color: #5bc0de; padding: 5px 10px; text-decoration: none; border-radius: 5px;"> `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/TeleOpRobot.java`</a>


### Control Teleoperado


El programa TeleOpRobot permite controlar el robot de manera teleoperada utilizando un gamepad de control. Las siguientes funcionalidades son fundamentales para el control del robot:


1. **Movimiento del Chasis**


  El chasis del robot puede moverse en múltiples direcciones: adelante, atrás, lateralmente y girar sobre sí mismo. Estos movimientos son controlados utilizando los sticks del gamepad.


2. **Ajuste de Velocidad**


  Para mayor comodidad, el programa ofrece diferentes niveles de velocidad ajustables mediante los botones del gamepad. Esto permite al conductor adaptar la velocidad del robot según la situación.


## Uso del Programa


A continuación, se detallan los pasos para ejecutar el programa TeleOpRobot y controlar el robot de NAUTILUS 4010:


1. Asegúrate de haber cargado este código en el sistema de control FTC y haber conectado correctamente todos los componentes del robot.


2. Enciende el robot y asegúrate de que el gamepad esté emparejado con el sistema de control.


3. Ejecuta el programa TeleOpRobot desde la estación de control.


4. Observa la telemetría mostrada en la pantalla para verificar la conexión del robot.


5. Utiliza los sticks y botones del gamepad para controlar el movimiento del robot.


6. ¡Diviértete explorando las capacidades de NAUTILUS 4010 mientras lo controlas!
<br>
<br>
<br>
<br>

# Autónomo
## ¿Cómo funciona el Programa Autónomo?


El codigo que se estará explicando es: <a href="TeamCode/src/main/java/org/firstinspires/ftc/teamcode/Autonomo.java" style="color: white; background-color: #5bc0de; padding: 5px 10px; text-decoration: none; border-radius: 5px;"> `TeamCode/src/main/java/org/firstinspires/ftc/teamcode/Autonomo.java`</a><br><br>
El programa Autónomo se ejecuta cuando el robot está en modo autónomo. En este modo, el robot realizará una acción específica sin intervención humana. El programa Autónomo contiene los siguientes pasos:
1. **Inicialización del Hardware**


  En el método `runOpMode()`, el programa inicializa los componentes del robot llamando al método `initializeHardware()` de la clase `HardWareMap`. Esto asegura que todos los motores y sensores estén listos para funcionar.


2. **Espera para Iniciar**


  El programa espera a que se presione el botón "Play" en el control de la estación para comenzar la ejecución autónoma. La línea `waitForStart()` indica al programa que espere hasta que el árbitro inicie el modo autónomo.


3. **Movimiento del Robot**


  Una vez iniciado el modo autónomo, el robot realiza una acción específica. En este caso, el robot se mueve hacia adelante durante 5 centímetros utilizando el método `moveForward(5)` de la clase `HardWareMap`.


## Uso del Programa Autónomo


Para usar el programa Autónomo en el robot NAUTILUS 4010, sigue estos pasos:


1. Asegúrate de haber cargado este código en el sistema de control FTC y haber conectado correctamente todos los componentes del robot.


2. Enciende el robot y asegúrate de que el gamepad esté emparejado con el sistema de control.


3. Ejecuta el programa Autónomo desde la estación de control para comenzar el modo autónomo. El robot realizará automáticamente la acción especificada (en este caso, moverse hacia adelante por 5 centímetros).


Recuerda que este es solo un ejemplo básico del programa Autónomo. Puedes modificar el código para que el robot realice diferentes acciones y tareas de acuerdo con los requerimientos de tu competición o proyecto.

<br>
<br>

## **¡Gracias por tu interés en NAUTILUS 4010! Esperamos que disfrutes aprendiendo y explorando el mundo de la robótica y la programación con nosotros. ¡Buena suerte en tus aventuras con FTC! 🚀**