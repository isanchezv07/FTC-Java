package org.firstinspires.ftc.teamcode4010.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;



@TeleOp(name="Mecano", group="Linear Opmode")

public class Mecano extends LinearOpMode {

    // Declare OpMode members for each of the 4 motors.
    private DcMotor leftFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor rightBackDrive = null;

    public Servo servo;
    ElapsedTime timer;

    @Override
    public void runOpMode() {
        servo = hardwareMap.servo.get("servo1");
        timer = new ElapsedTime();

        // Initialize the hardware variables. Note that the strings used here must correspond
        // to the names assigned during the robot configuration step on the DS or RC devices.
        leftFrontDrive  = hardwareMap.get(DcMotor.class, "front_left");
        leftBackDrive  = hardwareMap.get(DcMotor.class, "back_left");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "front_right");
        rightBackDrive = hardwareMap.get(DcMotor.class, "back_right");

        leftFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        leftBackDrive.setDirection(DcMotor.Direction.REVERSE);
        rightFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        rightBackDrive.setDirection(DcMotor.Direction.FORWARD);

        // Wait for the game to start (driver presses PLAY)
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        waitForStart();
        timer.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            if(gamepad1.y) {
                // move to 0 degrees.
                servo.setPosition(0);
            } else if (gamepad1.x || gamepad1.b) {
                // move to 90 degrees.
                servo.setPosition(0.5);
            } else if (gamepad1.a) {
                // move to 180 degrees.
                servo.setPosition(1);
            }else if(gamepad1.right_trigger){
                servo.setPosition(0.5);
            }
            double max;
            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double axial   = -gamepad1.right_stick_x;  // Note: pushing stick forward gives negative value
            double lateral =  gamepad1.right_stick_y;
            double yaw     = -gamepad1.left_stick_y;

            // Combine the joystick requests for each axis-motion to determine each wheel's power.
            // Set up a variable for each drive wheel to save the power level for telemetry.
            double leftFrontPower  = axial - lateral + yaw;//+
            double rightFrontPower = axial - lateral + yaw;//-
            double leftBackPower   = axial - lateral + yaw;//+
            double rightBackPower  = axial - lateral + yaw;//-

            // Normalize the values so no wheel power exceeds 100%
            // This ensures that the robot maintains the desired motion.
            max = Math.max(Math.abs(leftFrontPower), Math.abs(rightFrontPower));
            max = Math.max(max, Math.abs(leftBackPower));
            max = Math.max(max, Math.abs(rightBackPower));

            if (max > 1.0) {
                leftFrontPower  /= max;
                rightFrontPower /= max;
                leftBackPower   /= max;
                rightBackPower  /= max;
            }

            // Send calculated power to wheels
            leftFrontDrive.setPower(leftFrontPower);
            rightFrontDrive.setPower(rightFrontPower);
            leftBackDrive.setPower(leftBackPower);
            rightBackDrive.setPower(rightBackPower);

            // Show the elapsed game time and wheel power.
            telemetry.addData("Front left/Right", "%4.2f, %4.2f", leftFrontPower, rightFrontPower);
            telemetry.addData("Back  left/Right", "%4.2f, %4.2f", leftBackPower, rightBackPower);
            telemetry.update();
        }
    }}

