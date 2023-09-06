package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.Gyroscope;

@TeleOp (name = "Ed TeleOp")


public class EdTeleOp extends LinearOpMode
{
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor slider;
    //private Servo claw;
    //private Blinker expansion_hub_3;
    //private Blinker expansion_hub_1;
    //private Gyroscope imu;

    @Override
    public void runOpMode() throws InterruptedException
    {
        frontLeft = hardwareMap.dcMotor.get("frontLeft");
        frontRight = hardwareMap.dcMotor.get("frontRight");
        backLeft = hardwareMap.dcMotor.get ("backLeft");
        backRight = hardwareMap.dcMotor.get ("backRight");
        slider = hardwareMap.dcMotor.get ("slider");
        //claw = hardwareMap.servo.get ("claw");

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive())
        {
            double leftPower = gamepad1.left_stick_y;
            double rightPower = -gamepad1.right_stick_y;
            double sliderPower = gamepad2.left_stick_y;

            frontLeft.setPower(leftPower);
            frontRight.setPower(rightPower);
            backLeft.setPower(leftPower);
            backRight.setPower(rightPower);

            slider.setPower(sliderPower);



        }





    }


}
