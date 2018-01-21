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

        myinit();

        pinch();

        waitForStart();

        robot.jewelKnockDevice.setPosition(.42);

        teamColor = "red";
        boolean jewelLeftAsk = isOurJewelOnLeft();

        if (jewelLeftAsk){
            robot.driveBackword(.25, .3);
            robot.jewelKnockDevice.setPosition(.85);
            robot.driveForword(.25, .3);
        }

        knockJewelLeft();

        robot.jewelKnockDevice.setPosition(1);

        int mark = readVuImage();

        robot.driveForword(1.5, .3);

        turn(95, "left");

        columnTwoMove(mark);

        turn(90 , "right");

        robot.driveForword(.6,.3);

        release();

        robot.driveBackword(.4 , .3);

        push();

    }
}