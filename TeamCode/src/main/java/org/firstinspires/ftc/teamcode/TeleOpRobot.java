//NAUTILUS 4010
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

// Definimos el modo TeleOp (teleoperación) con el nombre "TeleOpt" y lo agrupamos en la categoría "Robot".
@TeleOp(name="TeleOpt", group="Robot")
public class TeleOpRobot extends OpMode {

    // Creamos una instancia de la clase HardWareMap para manejar los componentes del robot.
    private HardWareMap robot;

    // Variables para el control de tiempo y velocidad.
    private double lastChase;
    private ElapsedTime runtime;

    // Sobrescribimos el método init() de OpMode, que se ejecuta una vez al iniciar el modo teleoperado.
    @Override
    public void init(){

        // Inicializamos la instancia del HardWareMap pasándole "this" como parámetro, que hace referencia a este objeto TeleOpRobot.
        robot = new HardWareMap(this);

        // Inicializamos los componentes del robot llamando al método initializeHardware() del HardWareMap.
        robot.initializeHardware();

        // Actualizamos los datos telemétricos para que se muestren en la estación de control.
        telemetry.update();

        // Creamos una instancia de ElapsedTime para medir el tiempo durante la ejecución del modo teleoperado.
        runtime = new ElapsedTime();
    }

    // Sobrescribimos el método loop() de OpMode, que se ejecuta repetidamente durante el modo teleoperado.
    @Override
    public void loop(){

        // Llamamos al método moveChasis() para mover el chasis del robot según las entradas del gamepad1.
        moveChasis(gamepad1);
    }

    // Sobrescribimos el método stop() de OpMode, que se ejecuta al detener el modo teleoperado.
    @Override
    public void stop(){}

    // Método para controlar el movimiento del chasis del robot basado en las entradas del gamepad.
    private void moveChasis(Gamepad gamepad){

        // Obtenemos las entradas del gamepad para el control de movimiento.
        double drive = -gamepad.left_stick_y;
        double lateral = gamepad.right_stick_x;
        double turn = gamepad.left_stick_x;

        // Obtenemos el multiplicador de velocidad llamando al método getPowerMultiplier().
        double powerMultiplier = getPowerMultiplier(gamepad);

        // Llamamos al método move() del HardWareMap para mover el robot con las velocidades y dirección especificadas.
        robot.move(drive, lateral, turn, powerMultiplier);
    }

    // Método para obtener el multiplicador de velocidad basado en las entradas del gamepad.
    private double getPowerMultiplier(Gamepad gamepad){

        // Verificamos las entradas de los botones de los bumpers en el gamepad para ajustar la velocidad.
        if(gamepad.right_bumper && gamepad.left_bumper)
            return 0.2;
        if(gamepad.right_bumper)
            return 0.6;
        if(gamepad.left_bumper)
            return 0.4;
        else
            return 0.8;
    }
}