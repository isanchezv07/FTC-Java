//NAUTILUS 4010
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name="TeleOpt", group="Robot")
public class TeleOpRobot extends OpMode {
    private HardWareMap robot;
    private double lastChase;
    private ElapsedTime runtime;

    @Override
    public void init(){
        robot = new HardWareMap(this);
        robot.initializeHardware();
        telemetry.update();
        runtime = new ElapsedTime();
    }
    
    @Override
    public void loop(){
        moveChasis(gamepad1);
    }

    @Override
    public void stop(){}

    private void moveChasis(Gamepad gamepad){
        double drive = -gamepad.left_stick_y;
        double lateral = gamepad.right_stick_x;
        double turn = gamepad.left_stick_x;
        double powerMultiplier = getPowerMultiplier(gamepad);
        robot.move(drive, lateral, turn, powerMultiplier);
    }
    private double getPowerMultiplier(Gamepad gamepad){
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