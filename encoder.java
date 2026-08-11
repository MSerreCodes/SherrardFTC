package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;


@Autonomous(name = "Auto TEST", group = "Autonomous")
public class AutoTest extends LinearOpMode {

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;


    private final double TICKS_PER_REVOLUTION = 537.6; // For YELLOWJACKET motors
    private final double WHEEL_DIAMETER_INCHES = 4.0; // Wheel diameter (in inches)
    private final double GEAR_RATIO = 19.2; // Gear ratio for the motor


    @Override
    public void runOpMode() {
        // Initialize hardware
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = hardwareMap.get(DcMotor.class, "backLeft");
        backRight = hardwareMap.get(DcMotor.class, "backRight");

        // Reverse left side motors
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        backLeft.setDirection(DcMotor.Direction.REVERSE);

        // Reset encoders
        resetEncoders();

        // Wait for start signal
        waitForStart();

        encoderStrafeToPosition(100, 0.5);
        encoderDriveToPosition(675, 0.5);
        encoderRotateToPosition(150, 0.25);

        stopMotors();
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