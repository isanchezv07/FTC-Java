//NAUTILUS 4010 Isanchezv
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.hardware.bosch.BNO055IMU;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

public class HardWareMap {
    // Definición de constantes para el movimiento del robot
    public static final int TICKS_PER_CM = 25;
    public static final double AUTONOMOUS_SPEED = 0.2;

    // Declaración de motores y sensores
    public DcMotor frontLeft;
    public DcMotor frontRight;
    public DcMotor backLeft;
    public DcMotor backRight;
    
    private BNO055IMU imu;
    private Orientation angles;
    private Acceleration gravity;

    private OpMode program;

    // Constructor de la clase, recibe un objeto OpMode como parámetro
    public HardWareMap(OpMode program){
        this.program = program;
    }

    // Método para inicializar el hardware del robot
    public void initializeHardware(){
        HardwareMap hardwareMap = this.program.hardwareMap;
        frontLeft = hardwareMap.get(DcMotor.class, "front_left");
        frontRight = hardwareMap.get(DcMotor.class, "front_right");
        backLeft = hardwareMap.get(DcMotor.class, "back_left");
        backRight = hardwareMap.get(DcMotor.class, "back_right");

        // Configuración de las direcciones de rotación de los motores
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        imu = hardwareMap.get(BNO055IMU.class, "imu2");
        initIMU();
    }

    // Método para inicializar el sensor inercial
    public void initIMU(){
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json";
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        imu.initialize(parameters);
    }

    // Método para obtener el ángulo de orientación actual del robot
    public double getAngle(){
        // Verificar si el sensor inercial está inicializado
        if(imu == null)
            return -1024;

        // Obtener los ángulos de orientación y gravedad del sensor
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
        gravity = imu.getGravity();

        // Devolver el ángulo de orientación en el eje Z (Yaw)
        return angles.firstAngle;
    }

    // Métodos para el movimiento del robot en diferentes direcciones
    public void moveForward(double distance){
        // Reiniciar encoders para el control de movimiento
        resetEncoders();

        // Calcular la posición objetivo en ticks según la distancia deseada
        int targetPosition = (int) Math.round(distance * TICKS_PER_CM);

        // Configurar las posiciones objetivo de los motores
        frontLeft.setTargetPosition(targetPosition);
        frontRight.setTargetPosition(targetPosition); 
        backLeft.setTargetPosition(targetPosition);
        backRight.setTargetPosition(targetPosition);

        // Configurar las velocidades de los motores y activar el control de posición
        setChassisPowers(AUTONOMOUS_SPEED);
        initAutoDrive();
    }

    public void turnRight(double angleToRotate){
        // Configurar los motores para operar sin control de posición
        this.setChassisRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Obtener el ángulo de orientación actual
        double currentAngle = getAngle();

        // Calcular el ángulo objetivo al girar hacia la derecha
        double targetAngle = currentAngle - angleToRotate;

        // Girar hacia la derecha hasta alcanzar el ángulo objetivo
        while(currentAngle > targetAngle ){
            frontLeft.setPower(AUTONOMOUS_SPEED);
            frontRight.setPower(-AUTONOMOUS_SPEED);
            backLeft.setPower(AUTONOMOUS_SPEED);
            backRight.setPower(-AUTONOMOUS_SPEED);
            currentAngle = getAngle();
        }

        // Detener los motores una vez alcanzado el ángulo objetivo
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void turnLeft(double angleToRotate) {
        // Configurar los motores para operar sin control de posición
        this.setChassisRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        // Obtener el ángulo de orientación actual
        double currentAngle = getAngle();

        // Guardar el ángulo inicial para el registro de telemetría
        double startAngle = currentAngle;

        // Calcular el ángulo objetivo al girar hacia la izquierda
        double targetAngle = angleToRotate + getAngle();

        // Girar hacia la izquierda hasta alcanzar el ángulo objetivo
        while (currentAngle < targetAngle) {
            // Mostrar información de telemetría durante el giro
            program.telemetry.addData("Start angle: ", startAngle);
            program.telemetry.addData("Target angle: ", targetAngle);
            program.telemetry.addData("Current angle: ", currentAngle);

            // Configurar las velocidades de los motores para girar hacia la izquierda
            frontLeft.setPower(-AUTONOMOUS_SPEED);
            frontRight.setPower(AUTONOMOUS_SPEED);
            backLeft.setPower(-AUTONOMOUS_SPEED);
            backRight.setPower(AUTONOMOUS_SPEED);

            // Actualizar el ángulo actual
            currentAngle = getAngle();

            // Actualizar la telemetría en la pantalla del Driver Station
            program.telemetry.update();
        }

        // Detener los motores una vez alcanzado el ángulo objetivo
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }

    public void lateralMove(double distance){
        // Reiniciar encoders para el control de movimiento
        resetEncoders();

        // Calcular la posición objetivo en ticks según la distancia deseada
        int targetPosition = (int) Math.round(distance * TICKS_PER_CM);

        // Configurar las posiciones objetivo de los motores para el movimiento lateral
        frontLeft.setTargetPosition(-targetPosition);
        frontRight.setTargetPosition(-targetPosition);
        backLeft.setTargetPosition(-targetPosition);
        backRight.setTargetPosition(-targetPosition);

        // Configurar las velocidades de los motores y activar el control de posición
        setChassisPowers(AUTONOMOUS_SPEED);
        initAutoDrive();
    }

    // Método para mover el robot en una combinación de desplazamiento, lateralidad y giro
    public void move(double drive, double lateral, double turn, double multiplier){
        // Calcular las velocidades de cada motor según las entradas
        double frontLeftPower  = (drive + lateral + turn) * multiplier;
        double frontRightPower = (drive - lateral - turn) * multiplier;
        double backLeftPower   = (drive - lateral + turn) * multiplier;
        double backRightPower  = (drive + lateral - turn) * multiplier;

        // Limitar las velocidades a valores entre -1 y 1
        frontLeft.setPower(Range.clip(frontLeftPower, -1, 1));
        frontRight.setPower(Range.clip(frontRightPower, -1, 1));
        backLeft.setPower(Range.clip(backLeftPower, -1, 1));
        backRight.setPower(Range.clip(backRightPower, -1, 1));
    }

    // Método para obtener las potencias de cada motor en el chasis
    public String[] getChassisPowers(){
        String[] powers = {
            String.format("FL: %.2f", frontLeft.getPower()),
            String.format("FR: %.2f", frontRight.getPower()),
            String.format("BL: %.2f", backLeft.getPower()),
            String.format("BR: %.2f", backRight.getPower())
        };
        return powers;
    }

    // Método para establecer la misma potencia en todos los motores del chasis
    public void setChassisPowers(double power){
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }

    // Método para establecer el modo de funcionamiento de los motores del chasis
    private void setChassisRunMode(DcMotor.RunMode runmode){
        frontLeft.setMode(runmode);
        frontRight.setMode(runmode);
        backLeft.setMode(runmode);
        backRight.setMode(runmode);
    }

    // Método para reiniciar los encoders de los motores del chasis
    private void resetEncoders(){
        setChassisRunMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    // Método para iniciar el control automático de conducción
    private void initAutoDrive(){
        LinearOpMode aux = (LinearOpMode) program;
        this.setChassisRunMode(DcMotor.RunMode.RUN_TO_POSITION);
        while(
            frontLeft.isBusy() &&
            frontRight.isBusy() &&
            backLeft.isBusy() &&
            backRight.isBusy()
        )
        aux.sleep(100L);
    }
}