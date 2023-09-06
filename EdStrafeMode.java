package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
//import com.qualcomm.robotcore.hardware.DcMotorSimple;

//import com.qualcomm.robotcore.hardware.Gyroscope;

@TeleOp (name = "Ed the Strafer")


public class EdStrafeMode extends LinearOpMode
{
    private DcMotor frontLeft;
    private DcMotor frontRight;
    private DcMotor backLeft;
    private DcMotor backRight;
    private DcMotor slider;
    private Servo claw;
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
        claw = hardwareMap.servo.get ("claw");

        waitForStart();

        if (isStopRequested()) return;

        double max = -1850;

        while (opModeIsActive())
        {
           double x = -gamepad1.left_stick_y;
           double y = -gamepad1.left_stick_x;
           double rx = gamepad1.right_stick_x;

           telemetry.addData("Counts: ", slider.getCurrentPosition());
           telemetry.update();

           if(gamepad2.right_stick_y > 0 && slider.getCurrentPosition() > max)
            {
                slider.setPower(gamepad2.right_stick_y);
            }
           else if(gamepad2.right_stick_y < 0)
           {
               slider.setPower(gamepad2.right_stick_y);
           }
           else
           {
                slider.setPower(0);
           }

            frontLeft.setPower(y + x + rx);
            backLeft.setPower(y - x + rx);
            frontRight.setPower(y - x - rx);
            backRight.setPower(y + x - rx);

            if (gamepad2.right_bumper)
            {
                claw.setPosition(1);
            }

            if (gamepad2.left_bumper)
            {
                claw.setPosition(0.5);
            }



        }





    }


}
