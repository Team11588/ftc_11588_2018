package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/*
 * Created by kearneyg20428 on 10/23/2017.
 *
 * This is my full autonomous
 * it is my baby
 * please talk with me when editing
 *
 *  1. it inits everything
 *  2. Then takes picture and samples it
 *  3. Finds the vuforia and saves the mark number
 *  4. moves to knock of jewel
 */


@Autonomous(name = "Test3")
public class Test3 extends BaseCombinedAutonomous {


    @Override
    public void runOpMode() throws InterruptedException {

        teamColor = "blue";

        myinit();
        waitForStart();
        pinch();
        stopCamera();

        while (opModeIsActive()) ;
    }

}