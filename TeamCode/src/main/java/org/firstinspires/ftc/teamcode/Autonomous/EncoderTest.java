package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.teamcode.Hardware.HardwareDxm;


/**
 * Created by kearneyg20428 on 11/4/2017.
 */


//ONE ROTATION IS 1140

@Autonomous(name = "Encoders")
public class EncoderTest extends LinearOpMode {

    HardwareDxm robot = new HardwareDxm();
    HardwareMap hwMap = null;

    public int encoder;

     static final int ENCODER_RUN = 1140;

    @Override
    public void runOpMode() {


        robot.init(hardwareMap);


        robot.bLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.bRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.fLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.fRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        robot.bLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.bRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.fLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.fRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        robot.jewelKnockDevice.setPosition(.20);

        robot.bLeft.setTargetPosition(-ENCODER_RUN);
        robot.fLeft.setTargetPosition(ENCODER_RUN);
        robot.bRight.setTargetPosition(ENCODER_RUN);
        robot.fRight.setTargetPosition(-ENCODER_RUN);

        robot.bLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.fLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.bRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.fRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        power(.5, .5, .5, .5);

        while (robot.bLeft.isBusy()) ;

        telemetry.addData("here 1", "");
        telemetry.update();

        robot.bLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.bRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.fLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.fRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        robot.bLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.fLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.bRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.fRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.bLeft.setTargetPosition(-1140);
        robot.fLeft.setTargetPosition(-1140);
        robot.bRight.setTargetPosition(-1140);
        robot.fRight.setTargetPosition(-1140);

        power(.5, .5, .5, .5);

        while (robot.bLeft.isBusy()) ;

        robot.jewelKnockDevice.setPosition(1);

        robot.bLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.bRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.fLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.fRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        robot.bLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.fLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.bRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.fRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        robot.bLeft.setTargetPosition(1140 * 2);
        robot.fLeft.setTargetPosition(1140 * 2);
        robot.bRight.setTargetPosition(1140 * 2);
        robot.fRight.setTargetPosition(1140 * 2);

        telemetry.addData("here 3", "");
        telemetry.update();

        while (opModeIsActive()) ;
    }



    public void position(int bL , int bR , int fL , int fR){
        robot.bLeft.setTargetPosition(bL);
        robot.bRight.setTargetPosition(bR);
        robot.fLeft.setTargetPosition(fL);
        robot.fRight.setTargetPosition(fR);
    }

    public void power(double bL , double bR , double fL , double fR){
        robot.bLeft.setPower(bL);
        robot.bRight.setPower(bR);
        robot.fLeft.setPower(fL);
        robot.fRight.setPower(fR);



    }

}