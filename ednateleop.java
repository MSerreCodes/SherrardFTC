package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gyroscope;

@TeleOp (name = "Edna 3.1 TeleOp")


public class ednateleop extends LinearOpMode
{
    private Blinker expansion_Hub_2;
    private DcMotor arm;
    private Servo claw;
    private Gyroscope imu;
    private DcMotor leftDrive;
    private DcMotor rightDrive;
    private DcMotor out;
    private Servo wrist;

    @Override
    public void runOpMode() throws InterruptedException
    {

        leftDrive = hardwareMap.dcMotor.get("leftDrive");
        rightDrive = hardwareMap.dcMotor.get("rightDrive");
        arm = hardwareMap.dcMotor.get("arm");
        claw = hardwareMap.servo.get("claw");
        out = hardwareMap.dcMotor.get("out");
        wrist = hardwareMap.servo.get("wrist");

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            double leftDrivePower = -gamepad1.left_stick_y/2;
            double rightDrivePower = gamepad1.right_stick_y/2;

            rightDrive.setPower(rightDrivePower);
            leftDrive.setPower(leftDrivePower);

            double armPower = gamepad2.right_stick_y/2;
            arm.setPower(armPower);

            double armOut = gamepad2.left_stick_y;
            out.setPower(armOut);

            /*
            if (armOut != 0)
            {
                out.setPower(armOut);
                stop();
            }

            if (armIn != 0)
            {
                out.setPower(armIn);
            }

            /*
            if (gamepad2.a)
            {
                wrist.setPosition(0.4);
            }
            */



            /*
            if (wristUp != 0)
            {
                wrist.setPosition(wristUp);
            }

            if (wristDown != 0)
            {
                wrist.setPosition(wristDown);
            }
            */






            /*
            while (arm.getCurrentPosition() >= 1)
            {
                armPower = 0;
            }



            arm.setPower(0.3);

            arm.setDirection(DcMotorSimple.Direction.REVERSE);

            arm.setTargetPosition(1);
            if (gamepad2.a == true)
            {
                arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            }
            */



            //claw.setDirection(Servo.Direction.REVERSE);



            while (gamepad2.right_bumper)
            {
                claw.setPosition(0.3);
            }

            while (gamepad2.left_bumper)
            {
                claw.setPosition(0.0);
            }


            if (gamepad2.a)
            {
                wrist.setPosition(1.0);
            }


            double position = wrist.getPosition();

            wrist.setDirection(Servo.Direction.REVERSE);

            if (gamepad2.dpad_up)
            {
                wrist.setPosition(position += .002);
                position = wrist.getPosition();

            }

            if (gamepad2.dpad_down)
            {
                wrist.setPosition(position -= .002);
                position = wrist.getPosition();
            }
/*
            double moveWrist = gamepad2.left_stick_y;
            wrist.setPosition(moveWrist);

            while (gamepad2.left_stick_button)
            {
                wrist.setPosition(wrist.getPosition());
            }





            while (gamepad2.dpad_up == true)
            {
                wrist.setPosition(0.3);
            }

            while (gamepad2.dpad_down == true)
            {
                wrist.setPosition(0.0);
            }
            */






        }
    }




}

