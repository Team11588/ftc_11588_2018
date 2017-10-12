package org.firstinspires.ftc.teamcode.Autonomous;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
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
        File image = new File(sd+"/"+filePath, imageName);
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
        int LeftRed = 0;
        int RightRed = 0;
        int LeftBlue = 0;
        int RightBlue = 0;
        for(int x=0; x<200; x++){ // replace 200 with x pixel size value
            for(int y=0;y<200;y++){
                int color = bitmap.getPixel(x,y);
                telemetry.addData("Color", "%d", color);
                int red = Color.red(color);
                int green = Color.green(color);
                int blue = Color.blue(color);

                double[] HBV = RGBtoHSV(red, green, blue);
                double hue = HBV[0];

                if(((300<hue)||(hue<60))&&(x<100))
                    LeftRed = LeftRed + 1;
                else
                    RightRed = RightRed +1;

                if(((180<hue)&&(hue<=300))&&(x<100))
                    LeftBlue = LeftBlue + 1;
                else
                    RightBlue = RightBlue +1;


                telemetry.addData("RGB =", "R = %d G = %d B = %d", red, green, blue);
                telemetry.addData("Hue = ", hue);

                telemetry.update();
            }
            if ((LeftRed == RightRed)||(LeftBlue==RightBlue)){
                telemetry.addLine("the balls are too allusive");
            }
            else if((LeftRed<RightRed) && (LeftBlue>RightBlue)){
                telemetry.addLine("BLUE is on the LEFT side and RED is on the RIGHT side");
            }
            else if((LeftRed>RightRed) && (LeftBlue<RightBlue)){
                telemetry.addLine("RED is on the LEFT side and BLUE is on the RIGHT side");
            }
            else
                telemetry.addLine("ERROR 404: logic not found");
            telemetry.update();
        }

        waitForStart();
        runtime.reset();
        while (opModeIsActive())  {
            telemetry.addData("Status", "Run Time: " + runtime.toString());



        }


}

    public static double[] RGBtoHSV(double r, double g, double b){

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
        if( r == max )
            h = ( g - b ) / delta; // between yellow & magenta - reds
        else if( g == max )
            h = 2 + ( b - r ) / delta; // between cyan & yellow - greens
        else
            h = 4 + ( r - g ) / delta; // between magenta & cyan - blues

        h *= 60;    // degrees

        if( h < 0 )
            h += 360;

        return new double[]{h,/*s,*/v};
    }


}
