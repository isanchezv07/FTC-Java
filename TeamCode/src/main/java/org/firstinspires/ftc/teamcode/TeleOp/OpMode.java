
// ########################################################################################
// !!!         IMPORTANTE       Este codigo es un ejemplo para un chasis mecano       !!!!!
// *                             LEER TODOS LOS COMENTARIOS
// ########################################################################################

// Declarar todas las librerias que va a terner tu codigo
package org.firstinspires.ftc.robotcontroller.external.samples;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

//Poner el nombre que va a tener el archivo y el nombre que va aparecer en el Driver Hub
@TeleOp(name="Basic: Omni Linear OpMode", group="Linear Opmode")
public class OpMode extends LinearOpMode {

    //Declarar todos los motores con sus respectivos nombres
    private ElapsedTime runtime = new ElapsedTime();
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;
 
     @Override
     public void runOpMode() {
 
        //Localizar los motores con sus nombre con HardWareMap
         //Los nombres que estan en "" son el nombre que les pusiste en el Driver Hub
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "left_front_drive");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "left_back_drive");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "right_front_drive");
        rightBackDrive = hardwareMap.get(DcMotor.class, "right_back_drive");

        //Darle la direccion a los motores
        //Esta parte es muy importante
        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);
 
        //Iniciar el Telemetry Data 
        telemetry.addData("Status", "Initialized");
        telemetry.update();
 
        waitForStart();
        runtime.reset();

        while (opModeIsActive()) {
            double max;
 
            //Declarar los controles del Joystick para los motores
            double axial   = -gamepad1.left_stick_y; 
            double lateral =  gamepad1.left_stick_x;
            double yaw     =  gamepad1.right_stick_x;
            
            //Declarar como se va a controlar los movimientos del chasis
            //? La parte mas importante del codigo
            double leftFrontPower  = axial + lateral + yaw;
            double rightFrontPower = axial - lateral - yaw;
            double leftBackPower   = axial - lateral + yaw;
            double rightBackPower  = axial + lateral - yaw;

            //Declarar cual va a ser la potencia para los motores
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));
 
            if (max > 1.0) {
                leftFrontPower  /= max;
                rightFrontPower /= max;
                leftBackPower   /= max;
                rightBackPower  /= max;
            }

            //Darle la potencia a los motores
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightBackDrive.setPower(rightBackPower);

            //Cambiar el Telemetry Data para ver la potencia de los motores
            telemetry.addData("Status", "Run Time: " + runtime.toString());
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.update();
        }
    }
}

//Isanchezv-4010