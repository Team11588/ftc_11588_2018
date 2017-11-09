package org.firstinspires.ftc.teamcode.Autonomous;


import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.Hardware.HardwareDxm;


/**
 * Created by kearneyg20428 on 11/4/2017.
 */


//ONE ROTATION IS 1140

@Autonomous(name = "Line correcting")
public class LineCorrecting extends LinearOpMode {

    HardwareDxm robot = new HardwareDxm();
    HardwareMap hwMap = null;

    public int encoder;

     static final int ENCODER_RUN = 5700;

    @Override
    public void runOpMode() {

        BNO055IMU imu;

        Orientation angles;
        Acceleration gravity;

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        imu.startAccelerationIntegration(new Position(), new Velocity(), 1000);

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

        while (opModeIsActive()) {

            encoder = robot.bLeft.getCurrentPosition();

            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

            telemetry.addData("Angle", angles.firstAngle);
            telemetry.update();
            if (angles.firstAngle < 10 && angles.firstAngle > -10) {
                robot.bLeft.setTargetPosition(ENCODER_RUN);
                robot.fLeft.setTargetPosition(ENCODER_RUN);
                robot.bRight.setTargetPosition(ENCODER_RUN);
                robot.fRight.setTargetPosition(ENCODER_RUN);

                robot.bLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.fLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.bRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                robot.fRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);

                power(.5, .5, .5, .5);
            }
            else if (angles.firstAngle > 10) {

                power(.65, .5, .65, .5);
            }
            else if (angles.firstAngle < 10) {

                power(.5,.65,.5,.64);

            }
        }
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