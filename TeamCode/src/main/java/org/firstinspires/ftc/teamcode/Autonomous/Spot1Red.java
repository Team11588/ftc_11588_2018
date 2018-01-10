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

@Autonomous(name = "Spot 1 Red")
public class Spot1Red extends BaseCombinedAutonomous {

    @Override
    public void runOpMode() throws InterruptedException {

        teamColor = "red";

        myinit();

        waitForStart();

        boolean jewelSpot = isOurJewelOnLeft();

        int mark = readVuImage();

        knockJewel(jewelSpot);

        robot.driveForword(1.25 , .5);

        turn(90, "right");
        teamColor = "red";
        columnMove(mark);

        release();

    }
}