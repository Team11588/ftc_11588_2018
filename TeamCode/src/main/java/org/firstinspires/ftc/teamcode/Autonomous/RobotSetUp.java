package org.firstinspires.ftc.teamcode.Autonomous;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Environment;
import android.support.annotation.RequiresPermission;

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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Writer;

/**
 * Created by piscullin18641 on 10/23/2017.
 */


/*PSA These distinguish the different processes
************************VUFORIA****************************************
&&&&&&&&&&&&&&&&&&&&&&&&Picture Taking&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&
????????????????????????Pixel Testing??????????????????????????????????
 */

@Autonomous (name = "RobotSetUp")
public class RobotSetUp extends LinearOpModeCamera {

    VuforiaLocalizer vuforia;

    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode() throws InterruptedException {

        if (!isCameraAvailable()) {
            return;
        }
        startCamera();
        telemetry.addData(String.valueOf(width), height);
        telemetry.update();

        //**********************************************************************************************

        waitForStart();

        takePicture();
        //saveCoordinates();
        createBoxBitmap();

        stopCamera();
        //&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&


        OpenGLMatrix lastLocation = null;


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parametersv = new VuforiaLocalizer.Parameters(cameraMonitorViewId);


        parametersv.vuforiaLicenseKey = "AW/DxXD/////AAAAGYJtW/yP3kG0pVGawWtQZngsJNFQ8kp1Md8CaP2NP72Q0on4mGKPLt/lsSnMnUkCFNymrXXOjs0eHMDTvijWRIixEe/sJ4KHEVf1fhf0kqUB29+dZEvh4qeI7tlTU6pIy/MLW0a/t9cpqMksBRFqXIrhtR/vw7ZnErMTZrJNNXqmbecBnRhDfLncklzgH2wAkGmQDn0JSP7scEczgrggcmerXy3v6flLDh1/Tt2QZ8l/bTcEJtthE82i8/8p0NuDDhUyatFK1sZSSebykRz5A4PDUkw+jMTV28iUytrr1QLiQBwaTX7ikl71a1XkBHacnxrqyY07x9QfabtJf/PYNFiU17m/l9DB6Io7DPnnIaFP";


        parametersv.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parametersv);


        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        relicTrackables.activate();


        int mark = 0;
        /*
        Right - 1
        Center - 2
        Left - 3
         */

// This can be used to identify the pictograph and this loop will run until it is found and it'll store the mark

        while (opModeIsActive()) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {

                if (vuMark == RelicRecoveryVuMark.RIGHT) {

                    telemetry.addData("RIGHT", "");
                } else if (vuMark == RelicRecoveryVuMark.CENTER) {
                    telemetry.addData("CENTER", "");

                } else if (vuMark == RelicRecoveryVuMark.LEFT) {
                    telemetry.addData("Left", "");

                }

                telemetry.update();
            }
        }

        //while (opModeIsActive());
        // stopCamera();
    }


    public void takePicture (){

        String filePath = "Pictures";
        String imageName = "RobotSetUp.JPEG";
        Bitmap rgbImage = convertYuvImageToRgb(yuvImage, width, height, 0);

        savePic(imageName, rgbImage);
    }

    public void createBoxBitmap(){
        Bitmap rgbImage = convertYuvImageToRgb(yuvImage, width, height, 0);
        drawSamplingBox(rgbImage);
    }

    public void drawSamplingBox(Bitmap bitmap) {
        double xPercent = (bitmap.getWidth()) / 100.0;
        double yPercent = (bitmap.getHeight()) / 100.0;
        Bitmap mutableBitmap = bitmap.copy(Bitmap.Config.ARGB_8888, true);
        Canvas c = new Canvas(mutableBitmap);
        Paint p = new Paint();
        p.setARGB(100, 0, 200, 0);
        c.drawRect((int) (jewel.sampleLeftXPct * xPercent),
                (int) (jewel.sampleTopYPct * yPercent),
                (int) (jewel.sampleRightXPct * xPercent),
                (int) (jewel.sampleBotYPct * yPercent), p);
        savePic("RobotSetUpPreviewImage.png", mutableBitmap);
        //saveFile();
    }

    public void savePic (String filename, Bitmap bitmap){
        File sd = Environment.getExternalStorageDirectory();
        File image = new File(sd + "/" + "Pictures", filename);
        try

        {
            OutputStream outStream = new FileOutputStream(image);
            bitmap.compress( Bitmap.CompressFormat.PNG, 0, outStream);
            outStream.flush();
            outStream.close();
        } catch(Exception e)

        {
            telemetry.addData("NEED TO FIX", e.getMessage());
        }
    }

    public void saveFile(){
        String sampleBox = "sampleBox";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(sampleBox))){

            writer.write(jewel.sampleLeftXPct);
            writer.newLine();
            writer.write(jewel.sampleTopYPct);
            writer.newLine();
            writer.write(jewel.sampleRightXPct);
            writer.newLine();
            writer.write(jewel.sampleBotYPct);
            writer.newLine();

        }catch(IOException yee)
        {
            telemetry.addData("ERROR WRITING TO FILE", yee.getMessage());
        }
    }


}