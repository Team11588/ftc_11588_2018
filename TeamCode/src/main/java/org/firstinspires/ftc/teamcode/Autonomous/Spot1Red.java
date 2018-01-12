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

        myinit();

        pinch();

        waitForStart();

        robot.jewelKnockDevice.setPosition(.42);

        teamColor = "red";
        boolean jewelLeftAsk = isOurJewelOnLeft();

        if (jewelLeftAsk) {
            robot.driveBackword(.25, .3);
            robot.jewelKnockDevice.setPosition(.85);
            robot.driveForword(.25, .3);
        }

        knockJewelLeft();

        int mark = readVuImage();

        columnOneMove(mark);

        turn(80, "right");

        robot.driveForword(.7, .3);

        release();
    }
}