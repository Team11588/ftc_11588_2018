package org.firstinspires.ftc.teamcode.Autonomous;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Environment;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcontroller.internal.LinearOpModeCamera;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Hardware.HardwareDxm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by kearneyg20428 on 10/23/2017.
 */


/*PSA These distinguish the different processes
------------------------IMU SENSOR-------------------------------------
************************VUFORIA****************************************
&&&&&&&&&&&&&&&&&&&&&&&&Picture Taking&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
????????????????????????Pixel Testing??????????????????????????????????
~~~~~~~~~~~~~~~~~~~~~~~~Mechanum Driving~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

@Autonomous(name = "Combined Autonomous")
public class CombinedAutonomous extends LinearOpModeCamera {
    public static final int SAMPLE_LEFT_X_PCT = 20;
    public static final int SAMPLE_RIGHT_X_PCT = 30;
    public static final int SAMPLE_TOP_Y_PCT = 60;
    public static final int SAMPLE_BOT_Y_PCT = 80;
    public static final String TEAM_COLOR = "red";
    HardwareDxm robot = new HardwareDxm();
    HardwareMap hwMap = null;

    //**************************************************************************************************

    VuforiaLocalizer vuforia;

    //**************************************************************************************************

    //??????????????????????????????????????????????????????????????????????????????????????????????????

    String filePath = "Pictures";
    String imageName = "TestImage.JPEG";
    private ElapsedTime runtime = new ElapsedTime();

    //??????????????????????????????????????????????????????????????????????????????????????????????????

    @Override
    public void runOpMode() throws InterruptedException {


        robot.init(hardwareMap);

        //----------------------------------------------------------------------------------------------
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

        //----------------------------------------------------------------------------------------------

        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&


        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
        telemetry.addData("ready", "");
        telemetry.update();


        waitForStart();
// The init process has finished by here


        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&

        telemetry.addData(String.valueOf(width), height);
        telemetry.update();


        if (isCameraAvailable()) {
            startCamera();
            while (yuvImage == null) ;
            Bitmap rgbImage = convertYuvImageToRgb(yuvImage, width, height, 0);
            stopCamera();
            saveBitmap(imageName, rgbImage);
        }

        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&


        //**********************************************************************************************

        //??????????????????????????????????????????????????????????????????????????????????????????????

        File sd = Environment.getExternalStorageDirectory();

        if (sd == null) {
            telemetry.addLine("Open External Storage Failed");
        }

        File image = new File(sd + "/" + filePath, imageName);

        if (image == null) {
            telemetry.addLine("Open Image File Failed");
        } else {
            telemetry.addLine("Open Image Successful");
        }
        telemetry.addData("Image Name", "%s", image.getAbsolutePath());


        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(), bmOptions);

        if (bitmap == null) {
            telemetry.addLine("Could not read bitmap");

        }

        drawSamplingBox(bitmap);

        if (isOurJewelonLeft(bitmap)) {
            robot.jewelKnockDevice.setPosition(0);
            drive(.5, .5, .5, .5);//,drive forward
            drive(0, 0, 0, 0);//stop
        }
        //??????????????????????????????????????????????????????????????????????????????????????????????????

        //**********************************************************************************************


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parametersv = new VuforiaLocalizer.Parameters(cameraMonitorViewId);


        parametersv.vuforiaLicenseKey = "AW/DxXD/////AAAAGYJtW/yP3kG0pVGawWtQZngsJNFQ8kp1Md8CaP2NP72Q0on4mGKPLt/lsSnMnUkCFNymrXXOjs0eHMDTvijWRIixEe/sJ4KHEVf1fhf0kqUB29+dZEvh4qeI7tlTU6pIy/MLW0a/t9cpqMksBRFqXIrhtR/vw7ZnErMTZrJNNXqmbecBnRhDfLncklzgH2wAkGmQDn0JSP7scEczgrggcmerXy3v6flLDh1/Tt2QZ8l/bTcEJtthE82i8/8p0NuDDhUyatFK1sZSSebykRz5A4PDUkw+jMTV28iUytrr1QLiQBwaTX7ikl71a1XkBHacnxrqyY07x9QfabtJf/PYNFiU17m/l9DB6Io7DPnnIaFP";


        parametersv.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parametersv);


        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        //**********************************************************************************************

        relicTrackables.activate();


        int mark = 0;
        /*
        Right - 1
        Center - 2
        Left - 3
         */

// This can be used to identify the pictograph and this loop will run until it is found and it'll store the mark

        do {

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                if (vuMark == RelicRecoveryVuMark.RIGHT) {
                    mark = 1;
                    telemetry.addData("RIGHT", "");
                } else if (vuMark == RelicRecoveryVuMark.CENTER) {
                    telemetry.addData("CENTER", "");
                    mark = 2;
                } else if (vuMark == RelicRecoveryVuMark.LEFT) {
                    telemetry.addData("Left", "");
                    mark = 3;
                }

                telemetry.update();
            }
        }
        while (mark == 0);

        //**********************************************************************************************

        while (opModeIsActive()) ;
    }


    public static double[] RGBtoHSV(double r, double g, double b) {

        double h, s, v;

        double min, max, delta;

        min = Math.min(Math.min(r, g), b);
        max = Math.max(Math.max(r, g), b);

        // V
        v = max;

        delta = max - min;


        /*
        // S
        if( max != 0 )
            s = delta / max;
        else {
            s = 0;
            h = -1;
            return new double[]{h,s,v};
        }*/

        // H
        if (r == max)
            h = (g - b) / delta; // between yellow & magenta - reds
        else if (g == max)
            h = 2 + (b - r) / delta; // between cyan & yellow - greens
        else
            h = 4 + (r - g) / delta; // between magenta & cyan - blues

        h *= 60;    // degrees

        if (h < 0)
            h += 360;

        return new double[]{h,/*s,*/v};
    }

    public static void saveBitmap(String filename, Bitmap bitmap) {

        String filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Pictures";
        FileOutputStream fileOutputStream = null;

        try {
            fileOutputStream = new FileOutputStream(filePath + "/" + filename);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void drive(double fL, double fR, double bL, double bR) {
        robot.fLeft.setPower(fL);
        robot.fRight.setPower(fR);
        robot.bLeft.setPower(bL);
        robot.bRight.setPower(bR);
    }

    public void drawSamplingBox(Bitmap bitmap) {
        double xPercent = (bitmap.getWidth()) / 100.0;
        double yPercent = (bitmap.getHeight()) / 100.0;
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas c = new Canvas(mutableBitmap);
        Paint p = new Paint();
        p.setARGB(100, 128, 128, 128);
        c.drawRect((int) (SAMPLE_LEFT_X_PCT * xPercent), (int) (SAMPLE_TOP_Y_PCT * yPercent), (int) (SAMPLE_RIGHT_X_PCT * xPercent), (int) (SAMPLE_BOT_Y_PCT * yPercent), p);
        saveBitmap("previewImage.png", mutableBitmap);
    }

    public boolean isOurJewelonLeft(Bitmap bitmap) {
        int leftRed = 0;
        int leftBlue = 0;
        int count = 0;

        double xPercent = (bitmap.getWidth()) / 100.0;
        double yPercent = (bitmap.getHeight()) / 100.0;

        telemetry.addData("Start For loop", "");
        for (int x = SAMPLE_LEFT_X_PCT; x < SAMPLE_RIGHT_X_PCT; x++) { // replace 200 with x pixel size value
            for (int y = SAMPLE_TOP_Y_PCT; y < SAMPLE_BOT_Y_PCT; y++) {
                int color = bitmap.getPixel((int) (x * xPercent), (int) (y * yPercent));
                //telemetry.addData("Color", "%d", color);
                count++;
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);

                double[] HBV = RGBtoHSV(red, green, blue);
                double hue = HBV[0];

                if (((300 < hue) || (hue < 60)))
                    leftRed = leftRed + 1;

                if (((180 < hue) && (hue <= 300)))
                    leftBlue = leftBlue + 1;
            }
        }


        String leftJewelColor = "unknown";
        if (leftRed > leftBlue)
            leftJewelColor = "red";
        else
            leftJewelColor = "blue";

        telemetry.addData("Red count", "%d", leftRed);
        telemetry.addData("Blue count", "%d", leftBlue);
        telemetry.addData("Count", "%d", count);
        telemetry.update();

        if (leftJewelColor.equals(TEAM_COLOR))
            return true;
        else
            return false;
    }
}


