package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

@Autonomous(name="AUTO RED RIGHT", group="Linear Opmode")

public class AutoRedRight extends LinearOpMode {

    BNO055IMU imu;
    Orientation angles;

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    //Declare of DcMotor for Base Mechanum Wheels
    private Robot_Hardware Ron = new Robot_Hardware();


    private static final double ticks_per_revolution = 1120;
    private static final double drive_gear_reduction = 0.77777778;
    private static final double wheel_diameter = 4;
    private static final double ticks_per_inch = (ticks_per_revolution * drive_gear_reduction) / (wheel_diameter * Math.PI);


    @Override
    public void runOpMode()throws InterruptedException {

        Ron.initialize(hardwareMap, telemetry);

        telemetry.addData("Status", "Initializing");
        telemetry.update();

        Ron.initialize(hardwareMap, telemetry);

        //Code for gyro in expansion hub
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);


        Ron.front_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Ron.front_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Ron.back_left.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        Ron.back_right.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        telemetry.addData("Status", "Initialized");

        waitForStart();
        runtime.reset();

        // run until the end of the match (driver presses STOP)
        if (opModeIsActive()) {

            gamepad1.rumble(1000);


            telemetry.update();
        }



    }

    public double inches_to_ticks(double inches){
        return inches * ticks_per_inch;
    }

    public void Travel_Nth_Inches_X(double inches, double power, int ms) throws InterruptedException{
        double delta_target_position = inches_to_ticks(inches);

        Ron.front_left.setTargetPosition(Ron.front_left.getTargetPosition() + (int) delta_target_position);
        Ron.front_right.setTargetPosition(Ron.front_right.getTargetPosition() + (int) delta_target_position);
        Ron.back_left.setTargetPosition(Ron.back_left.getTargetPosition() + (int) delta_target_position);
        Ron.back_right.setTargetPosition(Ron.back_right.getTargetPosition() + (int) delta_target_position);

        Ron.front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Ron.front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Ron.back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Ron.back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Ron.front_left.setPower(power);
        Ron.front_right.setPower(power);
        Ron.back_left.setPower(power);
        Ron.back_right.setPower(power);

        Thread.sleep(ms);

        Ron.front_left.setPower(0);
        Ron.front_right.setPower(0);
        Ron.back_left.setPower(0);
        Ron.back_right.setPower(0);
    }

    public void Travel_Nth_Inches_Y(double inches, double power, int ms) throws InterruptedException{
        double delta_target_position = inches_to_ticks(inches);

        Ron.front_left.setTargetPosition(Ron.front_left.getTargetPosition() + (int) delta_target_position);
        Ron.front_right.setTargetPosition(-(Ron.front_right.getTargetPosition() + (int) delta_target_position));
        Ron.back_left.setTargetPosition(-(Ron.back_left.getTargetPosition() + (int) delta_target_position));
        Ron.back_right.setTargetPosition(Ron.back_right.getTargetPosition() + (int) delta_target_position);

        Ron.front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Ron.front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Ron.back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Ron.back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Ron.front_left.setPower(power);
        Ron.front_right.setPower(power);
        Ron.back_left.setPower(power);
        Ron.back_right.setPower(power);

        Thread.sleep(ms);

        Ron.front_left.setPower(0);
        Ron.front_right.setPower(0);
        Ron.back_left.setPower(0);
        Ron.back_right.setPower(0);
    }

    public void Rotate(double inches, double power, int ms) throws InterruptedException{
        double delta_target_position = inches_to_ticks(inches);

        Ron.front_left.setTargetPosition(-(Ron.front_left.getTargetPosition() + (int) delta_target_position));
        Ron.front_right.setTargetPosition(Ron.front_right.getTargetPosition() + (int) delta_target_position);
        Ron.back_left.setTargetPosition(-(Ron.back_left.getTargetPosition() + (int) delta_target_position));
        Ron.back_right.setTargetPosition(Ron.back_right.getTargetPosition() + (int) delta_target_position);

        Ron.front_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Ron.front_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Ron.back_left.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Ron.back_right.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        Ron.front_left.setPower(power);
        Ron.front_right.setPower(power);
        Ron.back_left.setPower(power);
        Ron.back_right.setPower(power);

        Thread.sleep(ms);

        Ron.front_left.setPower(0);
        Ron.front_right.setPower(0);
        Ron.back_left.setPower(0);
        Ron.back_right.setPower(0);
    }



    public void diagonal(double x, double y, double power, int ms){

    }

}
