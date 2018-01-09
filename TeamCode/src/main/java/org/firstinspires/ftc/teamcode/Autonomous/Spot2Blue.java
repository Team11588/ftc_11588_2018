package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
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

@Autonomous(name = "Spot 2 Blue")
public class Spot2Blue extends BaseCombinedAutonomous {

    @Override
    public void runOpMode() throws InterruptedException {

        teamColor = "blue";

        myinit();

        waitForStart();

        boolean jewelSpot = isOurJewelOnLeft();

        int mark = readVuImage();

        knockJewel(jewelSpot);

        robot.driveBackword(1.25 , .5);

        telemetry.addData(teamColor , "");
        telemetry.update();
        turn(180, "right");

        robot.strafeRight(1.4 , .5);
        teamColor = "blue";
        columnMove(mark);
        robot.driveForword(.5 , .5);

        while (opModeIsActive()) ;
    }
}