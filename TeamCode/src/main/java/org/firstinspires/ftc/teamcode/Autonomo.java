//NAUTILUS 4010 Isanchezv
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

// Definimos el modo Autónomo con el nombre "Autonomo" y lo agrupamos en la categoría "Alliance".
@Autonomous(name="Autonomo", group ="Alliance")
public class Autonomo extends LinearOpMode {

    // Creamos una instancia de la clase HardWareMap para manejar los componentes del robot.
    private HardWareMap robot;

    // Sobrescribimos el método runOpMode() de LinearOpMode, que es el punto de entrada para el modo autónomo.
    @Override
    public void runOpMode(){

        // Inicializamos la instancia del HardWareMap pasándole "this" como parámetro, que hace referencia a este objeto Autonomo.
        robot = new HardWareMap(this);

        // Inicializamos los componentes del robot llamando al método initializeHardware() del HardWareMap.
        robot.initializeHardware();

        // Actualizamos los datos telemétricos para que se muestren en la estación de control.
        telemetry.update();

        // Esperamos hasta que se presione el botón "Play" en la estación de control para comenzar el modo autónom
        waitForStart();

        // Una vez que se presiona "Play", el robot ejecutará la acción específica de este programa.
        // En este caso, moverse hacia adelante por 5 centímetros llamando al método moveForward(5) del HardWareMap
        robot.turnRight(180);
    }
}