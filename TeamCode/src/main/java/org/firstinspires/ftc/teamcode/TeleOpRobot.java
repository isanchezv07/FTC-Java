//NAUTILUS 4010 Isanchezv
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp", group = "Robot")
public class TeleOpRobot extends OpMode {

    private HardWareMap robot;
    private double lastChase;
    private ElapsedTime runtime;
    private Servo miServo;

    @Override
    public void init() {
        miServo = hardwareMap.get(Servo.class, "miServo");
        robot = new HardWareMap(this);
        robot.initializeHardware();
        telemetry.update();
        runtime = new ElapsedTime();
    }

    @Override
    public void loop() {
        moveChasis(gamepad1);

        // Establece la posición del servo según el estado del botón X
        if (gamepad1.x) {
            miServo.setPosition(0.3); // Posición máxima (agarrar)
        } else {
            miServo.setPosition(0.0); // Posición mínima (soltar)
        }
    }

    private void moveChasis(Gamepad gamepad) {
        double drive = -gamepad.left_stick_y;
        double lateral = gamepad.left_stick_x;
        double turn = -gamepad.right_stick_x;

        double powerMultiplier = getPowerMultiplier(gamepad);

        telemetry.addData("Power Multiplier", powerMultiplier);
        telemetry.update();

        robot.move(drive, lateral, turn, powerMultiplier);
    }

    private double getPowerMultiplier(Gamepad gamepad) {
        if (gamepad.y)
            return 0.2;
        if (gamepad.a)
            return 0.6;
        if (gamepad.b)
            return 0.4;
        else
            return 0.8;
    }
}