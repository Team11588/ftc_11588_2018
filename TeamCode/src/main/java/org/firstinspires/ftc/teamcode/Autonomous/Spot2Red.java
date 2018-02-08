package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

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



        waitForStart();

        pinch();

        robot.jewelKnockDevice.setPosition(.42);

        teamColor = "red";
        boolean jewelLeftAsk = isOurJewelOnLeft();

        if (jewelLeftAsk){
            robot.driveBackword(.35, .3);
            robot.jewelKnockDevice.setPosition(.85);
            robot.driveForword(.35, .3);
        }

        knockJewelLeft();

        robot.jewelKnockDevice.setPosition(1);

        int mark = readVuImage();

        robot.driveForword(1.25, .3);

        turn(95, "left");

        columnTwoMove(mark);

        turn(90 , "right");

        robot.driveForword(.85,.3);

        robot.open();

        robot.driveBackword(.4 , .3);

        push();



    }
}