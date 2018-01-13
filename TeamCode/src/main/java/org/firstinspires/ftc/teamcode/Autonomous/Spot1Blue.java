package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

/**
 * Created by kearneyg20428 on 10/23/2017. *
 *  1. it inits everything
 *  2. Then takes picture and samples it
 *  3. Finds the vuforia and saves the mark number
 *  4. moves to knock of jewel
 */



@Autonomous(name = "Spot 1 Blue")
public class Spot1Blue extends BaseCombinedAutonomous {


    @Override
    public void runOpMode() throws InterruptedException {

        myinit();

       //    pinch();

        waitForStart();

        teamColor = "blue";
        boolean jewelAskLeft = isOurJewelOnLeft();
        if (!jewelAskLeft) {
            robot.jewelKnockDevice.setPosition(.42);
        }
        robot.driveForword(.2, .3);

        int mark = readVuImage();

        robot.jewelKnockDevice.setPosition(.85);

        robot.driveBackword(.2, .3);

        if (jewelAskLeft) {
            robot.jewelKnockDevice.setPosition(.42);
        }

        knockJewelRight();

        columnOneMove(mark);

        turn(90, "right");

        robot.driveForword(.4 , .3);

        release();

        robot.driveBackword(.2 , .3);
    }
}