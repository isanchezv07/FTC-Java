package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Robot_Hardware {

    //Instantiate Elapsed Time Object
    private ElapsedTime runtime = new ElapsedTime();

    //Instantiate Drive Motors
    public DcMotor front_left = null;
    public DcMotor front_right = null;
    public DcMotor back_left = null;
    public DcMotor back_right = null;

    //Instantiate Carousel Motor
    public DcMotor carousel_motor = null;

    //Instantiate Arm + Intake Motors
    public DcMotor intake = null;
    public DcMotor arm = null;


    //Initializes all motors upon call to method
    public void initialize(HardwareMap hwMap, Telemetry telemetry ){
        telemetry.addData("Ron Status", "I have started initializing :D");

        //Defines DcMotor for Base Mechanum Wheels w/ hardwareMap
        front_left  = hwMap.get(DcMotor.class, "front_left");
        front_right = hwMap.get(DcMotor.class, "front_right");
        back_left  = hwMap.get(DcMotor.class, "back_left");
        back_right = hwMap.get(DcMotor.class, "back_right");

        //Defines DcMotor for Carousels w/ hardwareMap
        carousel_motor = hwMap.get(DcMotor.class, "carousel_motor");
        intake = hwMap.get(DcMotor.class, "intake");
        arm = hwMap.get(DcMotor.class, "arm");

        //Set Direction of DcMotor for Base Mechanum Wheels
        front_left.setDirection(DcMotor.Direction.FORWARD);
        front_right.setDirection(DcMotor.Direction.FORWARD);
        back_left.setDirection(DcMotor.Direction.FORWARD);
        back_right.setDirection(DcMotor.Direction.FORWARD);

        //Set Direction of DcMotor for Carousel
        carousel_motor.setDirection(DcMotor.Direction.FORWARD);
        intake.setDirection(DcMotor.Direction.FORWARD);
        arm.setDirection(DcMotor.Direction.FORWARD);


        //Set Zero Power Behavior for all Motors
        front_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        carousel_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);


        telemetry.addData("Ron Status", "I'm done initializing :D");
    }
}
