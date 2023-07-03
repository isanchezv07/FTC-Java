package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@TeleOp(name="TeleOp4785_Version_2.0", group="Iterative Opmode")

public class TeleOp4785 extends OpMode
{

    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    //Declare of DcMotor for Base Mechanum Wheels
    private Robot_Hardware Ron = new Robot_Hardware();


    @Override
    public void init() {
        telemetry.addData("Status", "Initializing");

        Ron.initialize(hardwareMap, telemetry);

        telemetry.addData("Status", "Initialized");
    }

    @Override
    public void init_loop() {



    }

    @Override
    public void start() {
        runtime.reset();




    }


    @Override
    public void loop() {

        //Experimental Drive
        double angle = Math.atan2(gamepad1.left_stick_y,gamepad1.left_stick_x);
        double magnitude  = Math.sqrt(Math.pow(gamepad1.left_stick_x, 2) + Math.pow(gamepad1.left_stick_y, 2));

        //double front_right_back_left_power = Math.sin(angle-(1/(4 * Math.PI))) * magnitude;
        //double front_left_back_right_power = Math.sin(angle+(1/(4 * Math.PI))) * magnitude;

        double front_right_power = (Math.sin(angle-(1/(4 * Math.PI))) * magnitude) - gamepad1.left_stick_y;
        double front_left_power =  (Math.sin(angle+(1/(4 * Math.PI))) * magnitude) + gamepad1.left_stick_y;

        double back_right_power = (Math.sin(angle+(1/(4 * Math.PI))) * magnitude) - gamepad1.left_stick_y;
        double back_left_power = (Math.sin(angle-(1/(4 * Math.PI))) * magnitude) + gamepad1.left_stick_y;

        Ron.front_right.setPower(front_right_power);
        Ron.front_left.setPower(front_left_power);
        Ron.back_left.setPower(back_left_power);
        Ron.back_right.setPower(back_right_power);

    }


    @Override
    public void stop() {

        telemetry.addData("Status", "Stopped");
    }

}
