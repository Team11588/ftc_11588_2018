package org.firstinspires.ftc.teamcode.Autonomous;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.text.ParcelableSpan;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcontroller.internal.LinearOpModeCamera;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Hardware.HardwareDxm;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Locale;

/**
 * Created by kearneyg20428 on 11/4/2017.
 */

@Autonomous(name = "Encoders")
public class EncoderTest extends LinearOpMode {

    HardwareDxm robot = new HardwareDxm();
    HardwareMap hwMap = null;



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

            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

            telemetry.addData("Angle", angles.firstAngle);
            telemetry.update();


            robot.bLeft.setTargetPosition(8140);
            //robot.bRight.setTargetPosition(8140);
            //robot.fLeft.setTargetPosition(8140);
            //robot.fRight.setTargetPosition(-8140);

            robot.bLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //robot.bRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //robot.fLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            //robot.fRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);


            power(.5, .5, .5, .5);


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