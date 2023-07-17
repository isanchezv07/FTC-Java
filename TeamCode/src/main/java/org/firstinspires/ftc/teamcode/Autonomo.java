package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name="Autonomo", group ="Alliance")
public class Autonomo extends LinearOpMode {
    private HardWareMap robot;
    @Override
    public void runOpMode(){
        robot = new HardWareMap(this);
        robot.initializeHardware();
        telemetry.update();
        waitForStart();
        robot.moveForward(5);
    }
}
