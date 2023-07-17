//NAUTILUS 4010 

// Importar las librerías y clases necesarias
package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

// Definir la etiqueta de la OpMode y su grupo
@TeleOp(name="BasicOpMode", group="Linear Opmode")
public class BasicOpMode extends LinearOpMode {

    // Crear un objeto para medir el tiempo transcurrido
    private ElapsedTime runtime = new ElapsedTime();

    // Declarar los motores del chasis
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    // Método principal que se ejecuta al iniciar la OpMode
    @Override
    public void runOpMode() {

        // Inicializar los motores usando los nombres configurados en el teléfono
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");

        // Configurar la dirección de los motores según su disposición en el robot
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        // Mostrar en el teléfono que la inicialización está completa
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        // Esperar hasta que se inicie la OpMode
        waitForStart();
        runtime.reset();

        // Bucle principal que se ejecuta mientras la OpMode está activa
        while (opModeIsActive()) {
            // Variables para el control de movimiento
            double max;
            double axial   = -gamepad1.left_stick_y; // Eje vertical izquierdo del gamepad
            double lateral =  gamepad1.left_stick_x; // Eje horizontal izquierdo del gamepad
            double yaw     =  gamepad1.right_stick_x; // Eje horizontal derecho del gamepad

            // Calcular las potencias de los motores para el chasis de tracción mecanum

            /**
            *   1) Axial:    Driving forward and backward
            *   2) Lateral:  Strafing right and left
            *   3) Yaw:      Rotating Clockwise and counter clockwise
            */
            double leftFrontPower  = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower   = axial - lateral + yaw;
            double rightBackPower  = axial + lateral - yaw;

            // Encontrar el máximo valor de potencia entre los motores
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            // Si alguna potencia excede 1.0, reducir todas las potencias para que el máximo sea 1.0
            if (max > 1.0) {
                leftFrontPower  /= max;
                rightFrontPower /= max;
                leftBackPower   /= max;
                rightBackPower  /= max;
            }

            // Configurar las potencias en los motores del chasis
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightBackDrive.setPower(rightBackPower);

            // Mostrar en el teléfono el tiempo de ejecución y las potencias de los motores
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.update();
        }
    }
}