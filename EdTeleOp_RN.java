package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gyroscope;

@TeleOp (name = "Ed TeleOp_RN")


public class EdTeleOp_RN extends LinearOpMode
{
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor slider;
    private Servo claw;
    private Blinker expansion_hub_3;
    private Blinker expansion_hub_1;
    private Gyroscope imu;

    @Override
    public void runOpMode() throws InterruptedException
    {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get ("backLeft");
        backRight = hardwareMap.dcMotor.get ("backRight");
        slider = hardwareMap.dcMotor.get ("slider");
        claw = hardwareMap.servo.get ("claw");

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive())
        {
            double leftPower1 = gamepad1.left_stick_y;
            double rightPower1 = gamepad1.right_stick_y;
            double leftPower2 = gamepad1.left_stick_y;
            double rightPower2 = gamepad1.right_stick_y;
            double sliderPower = gamepad2.left_stick_y;
            double clawClose = gamepad2.left_trigger;
            double clawOpen = gamepad2.right_trigger;

            frontLeft.setPower(leftPower1);
            frontRight.setPower(rightPower1);
            frontLeft.setPower(leftPower2);
            frontRight.setPower(rightPower2);
            slider.setPower(sliderPower);
            claw.setPosition(clawOpen);
            claw.setPosition(clawClose);



        }





    }


}
