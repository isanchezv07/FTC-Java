package main.java.org.firstinspires.ftc.teamcode.Autonomo;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


@Autonomous(name="Autónomo Azul Estacionarse", group="Blue Alliance")
public class AutonomoBlue extends LinearOpMode{
    private Hardbot robot;
    @Override
    public void runOpMode() {
        robot = new Hardbot(this);
        robot.initializeHardware();
        telemetry.update();
        waitForStart();
        robot.moveForward(5);
        robot.turnLeft(82);
        robot.lateralMove(-10);
        robot.lateralMove(-6);
        robot.moveForward(45);
        robot.moveForward(30);
    }
}