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

@Autonomous(name = "Spot 2 Red")
public class Spot2Red extends BaseCombinedAutonomous {

    @Override
    public void runOpMode() throws InterruptedException {

        teamColor = "red";

        myinit();

        waitForStart();

        boolean jewelSpot = isOurJewelOnLeft();

        int mark = readVuImage();

        knockJewel(jewelSpot);

        robot.driveForword(1.75, .5);

        if (jewelSpot) {
            robot.strafeLeft(1.4, .5);
        } else {
            robot.strafeLeft(1.55, .5);
        }

        teamColor = "red";

        if (!jewelSpot) {
            columnMove(mark);
        }

        if (!jewelSpot) {

            robot.driveForword(.5, .5);

        } else {

            robot.driveForword(1, .5);

        }
        if (jewelSpot){
            columnMove(mark);
        }
        if (mark == 2){
            robot.strafeRight(.2 , .5);
        }

        if (mark >= 2) {

            turn(5, "right");

        }

        release();

    }
}