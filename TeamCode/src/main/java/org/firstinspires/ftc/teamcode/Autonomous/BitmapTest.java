package org.firstinspires.ftc.teamcode.Autonomous;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import java.io.File;

/**
 * Created by butterss21317 on 9/26/2017.
 */

@Autonomous(name="BitmapTest", group="Concept")
public class BitmapTest extends LinearOpMode {
    String filePath = "Pictures";
    String imageName = "TestImage.png";
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() throws InterruptedException {
        telemetry.addLine("Hi");

        File sd = Environment.getExternalStorageDirectory();
        if (sd == null){
            telemetry.addLine("Open External Storage Failed");
        }
        File image = new File(sd+filePath, imageName);
        if (image == null) {
            telemetry.addLine("Open Image File Failed");
        }
        else {
            telemetry.addLine("Open Image Successful");

        }
        telemetry.addData("Image Name", "%s",image.getAbsolutePath());


        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
    Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
        if (bitmap == null) {
            telemetry.addLine("Could not read bitmap");

        }
        int color = bitmap.getPixel(0,0);
        telemetry.addData("Color", "%d", color);

        waitForStart();
        runtime.reset();
        while (opModeIsActive())  {
            telemetry.addData("Status", "Run Time: " + runtime.toString());



        }


}

}
