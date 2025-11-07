package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "Auto TEST", group = "Autonomous")
public class AutoOpTest extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    private Servo claw = null;
    private Servo leftShoulder = null;
    private Servo rightShoulder = null;
    private DcMotor leftViperMotor = null;
    private DcMotor rightViperMotor = null;

    // Define constants for positions and power
    private final double CLAW_OPEN_POSITION = 0.45;
    private final double CLAW_CLOSED_POSITION = 0.275;
    private final double TICKS_PER_REVOLUTION = 537.6; // For YELLOWJACKET motors
    private final double WHEEL_DIAMETER_INCHES = 4.0; // Wheel diameter (in inches)
    private final double GEAR_RATIO = 19.2; // Gear ratio for the motor

    // Define constants for shoulder servo limits
    private final double LEFT_SHOULDER_MIN = 1;
    private final double LEFT_SHOULDER_MAX = 0.85;
    private final double RIGHT_SHOULDER_MIN = 0;
    private final double RIGHT_SHOULDER_MAX = 0.15;

    @Override
    public void runOpMode() {
        // Initialize hardware
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");
        claw = hardwareMap.get(Servo.class, "claw");
        leftShoulder = hardwareMap.get(Servo.class, "leftShoulder");
        rightShoulder = hardwareMap.get(Servo.class, "rightShoulder");
        leftViperMotor = hardwareMap.get(DcMotor.class, "leftViper");
        rightViperMotor = hardwareMap.get(DcMotor.class, "rightViper");

        // Reverse left side motors
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        // Reset encoders
        resetEncoders();

        // Close the claw during initialization
        claw.setPosition(CLAW_CLOSED_POSITION);

        // Wait for start signal
        waitForStart();

        leftShoulder.setPosition(0.35);
        rightShoulder.setPosition(0.85);
        encoderStrafeToPosition(100, 0.5);
        encoderDriveToPosition(675, 0.5);
        encoderRotateToPosition(150, 0.25);

        // Move viper slides up and hold them
        moveViperSlidesToPosition(4300, 0.5);

        leftShoulder.setPosition(0.70);
        rightShoulder.setPosition(0.30);
        sleep(500);
        claw.setPosition(CLAW_OPEN_POSITION);
        sleep(100);
        leftShoulder.setPosition(0.20);
        rightShoulder.setPosition(0.80);
        sleep(500);

        // Reset viper slides' power to allow movement down
        leftViperMotor.setPower(0);
        rightViperMotor.setPower(0);

        moveViperSlidesToPosition(200, 0.50);
        sleep(500);
        encoderDriveToPosition(-650, 0.5);
        encoderRotateToPosition(-150, 0.5);
        encoderStrafeToPosition(500, 0.5);

        leftShoulder.setPosition(0.9);
        rightShoulder.setPosition(0.1);
        sleep(100);
        claw.setPosition(CLAW_CLOSED_POSITION);
        sleep(100);
        leftShoulder.setPosition(.2);
        rightShoulder.setPosition(.8);
        sleep(100);

        encoderStrafeToPosition(-500, 0.5);
        encoderRotateToPosition(150, 0.5);
        encoderDriveToPosition(650, 0.5);

        // Move viper slides up and hold them
        moveViperSlidesToPosition(4300, 0.5);

        leftShoulder.setPosition(0.70);
        rightShoulder.setPosition(0.30);
        sleep(500);
        claw.setPosition(CLAW_OPEN_POSITION);
        sleep(100);
        leftShoulder.setPosition(0.20);
        rightShoulder.setPosition(0.80);
        sleep(500);

        // Reset viper slides' power to allow movement down
        leftViperMotor.setPower(0);
        rightViperMotor.setPower(0);

        moveViperSlidesToPosition(200, 0.50);
        sleep(500);
        encoderDriveToPosition(-650, 0.5);
        encoderRotateToPosition(-150, 0.5);
        encoderDriveToPosition(-4400, 0.75);
        encoderStrafeToPosition(-400, 0.25);

        stopMotors();
    }

    // Utility Methods

    private void moveViperSlidesToPosition(int targetEncoderCounts, double power) {
        leftViperMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightViperMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftViperMotor.setTargetPosition(targetEncoderCounts);
        rightViperMotor.setTargetPosition(-targetEncoderCounts);

        leftViperMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightViperMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        leftViperMotor.setPower(power);
        rightViperMotor.setPower(power);

        while (opModeIsActive() &&
                (leftViperMotor.isBusy() && rightViperMotor.isBusy())) {
            telemetry.addData("Viper Slide Target Position", targetEncoderCounts);
            telemetry.addData("Left Viper Position", leftViperMotor.getCurrentPosition());
            telemetry.addData("Right Viper Position", rightViperMotor.getCurrentPosition());
            telemetry.update();
        }

        // Hold position with low power if slides moved up
        if (targetEncoderCounts == 4300) {
            leftViperMotor.setPower(0.05);
            rightViperMotor.setPower(-0.05);
        } else {
            leftViperMotor.setPower(0);
            rightViperMotor.setPower(0);
        }
    }

    private void encoderDriveToPosition(int targetEncoderCounts, double power) {
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        resetEncoders();

        frontLeft.setTargetPosition(targetEncoderCounts);
        frontRight.setTargetPosition(targetEncoderCounts);
        backLeft.setTargetPosition(targetEncoderCounts);
        backRight.setTargetPosition(targetEncoderCounts);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setMotorPowers(power);

        while (opModeIsActive() &&
                (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy())) {
            telemetry.addData("Target Position", targetEncoderCounts);
            telemetry.addData("Front Left Position", frontLeft.getCurrentPosition());
            telemetry.addData("Front Right Position", frontRight.getCurrentPosition());
            telemetry.addData("Back Left Position", backLeft.getCurrentPosition());
            telemetry.addData("Back Right Position", backRight.getCurrentPosition());
            telemetry.update();
        }

        stopMotors();
    }

    private void encoderRotateToPosition(int targetEncoderCounts, double power) {
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        resetEncoders();

        frontLeft.setTargetPosition(-targetEncoderCounts);
        frontRight.setTargetPosition(targetEncoderCounts);
        backLeft.setTargetPosition(-targetEncoderCounts);
        backRight.setTargetPosition(targetEncoderCounts);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setMotorPowers(power);

        while (opModeIsActive() &&
                (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy())) {
            telemetry.addData("Rotate Target Position", targetEncoderCounts);
            telemetry.addData("Front Left Position", frontLeft.getCurrentPosition());
            telemetry.addData("Front Right Position", frontRight.getCurrentPosition());
            telemetry.addData("Back Left Position", backLeft.getCurrentPosition());
            telemetry.addData("Back Right Position", backRight.getCurrentPosition());
            telemetry.update();
        }

        stopMotors();
    }

    private void encoderStrafeToPosition(int targetEncoderCounts, double power) {
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        resetEncoders();

        frontLeft.setTargetPosition(targetEncoderCounts);
        frontRight.setTargetPosition(-targetEncoderCounts);
        backLeft.setTargetPosition(-targetEncoderCounts);
        backRight.setTargetPosition(targetEncoderCounts);

        frontLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        frontRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        backRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        setMotorPowers(power);

        while (opModeIsActive() &&
                (frontLeft.isBusy() && frontRight.isBusy() && backLeft.isBusy() && backRight.isBusy())) {
            telemetry.addData("Strafe Target Position", targetEncoderCounts);
            telemetry.addData("Front Left Position", frontLeft.getCurrentPosition());
            telemetry.addData("Front Right Position", frontRight.getCurrentPosition());
            telemetry.addData("Back Left Position", backLeft.getCurrentPosition());
            telemetry.addData("Back Right Position", backRight.getCurrentPosition());
            telemetry.update();
        }

        stopMotors();
    }

    private void resetEncoders() {
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }

    private void setMotorPowers(double power) {
        frontLeft.setPower(power);
        frontRight.setPower(power);
        backLeft.setPower(power);
        backRight.setPower(power);
    }

    private void stopMotors() {
        frontLeft.setPower(0);
        frontRight.setPower(0);
        backLeft.setPower(0);
        backRight.setPower(0);
    }
}
