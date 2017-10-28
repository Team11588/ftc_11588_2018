package org.firstinspires.ftc.teamcode.Autonomous;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcontroller.internal.LinearOpModeCamera;
import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.navigation.Acceleration;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Hardware.HardwareDxm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by kearneyg20428 on 10/23/2017.
 */


/*PSA These distinguish the different processes
************************VUFORIA****************************************
&&&&&&&&&&&&&&&&&&&&&&&&Picture Taking&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
????????????????????????Pixel Testing??????????????????????????????????
 */

@Autonomous (name = "RobotSetUp")
public class RobotSetUp extends LinearOpModeCamera{

    VuforiaLocalizer vuforia;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException {


        startCamera();
        telemetry.addData(String.valueOf(width), height);
        telemetry.update();


        OpenGLMatrix lastLocation = null;


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parametersv = new VuforiaLocalizer.Parameters(cameraMonitorViewId);


        parametersv.vuforiaLicenseKey = "AW/DxXD/////AAAAGYJtW/yP3kG0pVGawWtQZngsJNFQ8kp1Md8CaP2NP72Q0on4mGKPLt/lsSnMnUkCFNymrXXOjs0eHMDTvijWRIixEe/sJ4KHEVf1fhf0kqUB29+dZEvh4qeI7tlTU6pIy/MLW0a/t9cpqMksBRFqXIrhtR/vw7ZnErMTZrJNNXqmbecBnRhDfLncklzgH2wAkGmQDn0JSP7scEczgrggcmerXy3v6flLDh1/Tt2QZ8l/bTcEJtthE82i8/8p0NuDDhUyatFK1sZSSebykRz5A4PDUkw+jMTV28iUytrr1QLiQBwaTX7ikl71a1XkBHacnxrqyY07x9QfabtJf/PYNFiU17m/l9DB6Io7DPnnIaFP";


        parametersv.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parametersv);


        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

       //**********************************************************************************************

        waitForStart();
// The init process has finished by here

        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
                if (isCameraAvailable()) {

            Bitmap rgbImage = convertYuvImageToRgb(yuvImage, width, height, 0);
            stopCamera();
            File sd = Environment.getExternalStorageDirectory();
            File image = new File(sd + "/" + filePath, imageName);
            try {
                OutputStream outStream = new FileOutputStream(image);
                //rgbImage.compress(Bitmap.CompressFormat.JPEG, 0, outStream);
                yuvImage.compressToJpeg(new Rect(0, 0, width, height), 0, outStream);
                outStream.flush();
                outStream.close();
            } catch (Exception e) {
                telemetry.addData("NEED TO FIX", e.getMessage());
            }
        }

      //  relicTrackables.activate();


      //  int mark = 0;
        /*
        Right - 1
        Center - 2
        Left - 3
         */

// This can be used to identify the pictograph and this loop will run until it is found and it'll store the mark

       /* do {

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                if (vuMark == RelicRecoveryVuMark.RIGHT) {
                    mark = 1;
                    telemetry.addData("RIGHT","");
                }
                else if (vuMark == RelicRecoveryVuMark.CENTER) {
                    telemetry.addData("CENTER", "");
                    mark = 2;
                }
                else if (vuMark == RelicRecoveryVuMark.LEFT) {
                    telemetry.addData("Left", "");
                    mark = 3;
                }

                telemetry.update();
            }
        }while(mark == 0);*/

        //double xPercent = (bitmap.getWidth())/100.0;
        //double yPercent = (bitmap.getHeight())/100.0;

       /* telemetry.addData("Start For loop", "");
        for(int x=20; x<30; x++){ // replace 200 with x pixel size value
            for(int y=60;y<80;y++){
            }
        }*/

        /*Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas c = new Canvas(mutableBitmap);
        Paint p = new Paint();
        p.setColor(Color.BLACK);
        c.drawRect((int) (20*xPercent),(int) (60*yPercent), (int) (30*xPercent),(int) (80*yPercent), p);

     */
    }