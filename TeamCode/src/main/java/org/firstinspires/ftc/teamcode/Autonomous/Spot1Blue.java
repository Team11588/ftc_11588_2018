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



@Autonomous(name = "Spot 1 Blue")
public class Spot1Blue extends BaseCombinedAutonomous {


    @Override
    public void runOpMode() throws InterruptedException {


        myinit();

        waitForStart();

        teamColor = "blue";
        boolean jewelSpot = isOurJewelOnLeft();

        int mark = readVuImage();

        knockJewel(jewelSpot);
        if (jewelSpot) {
            robot.driveBackword(1.65, .5);
        } else {
            robot.driveBackword(1, .5);
        }
       /* if (jewelSpot) {
            turn(90, "right");
        } else {
            turn(85, "right");
        }
        */

       /* if(jewelSpot) {
            robot.strafeRight(.8, .6);
        }
        */
        columnMove(mark);



        /*if (jewelSpot) {
            turn(10, "left");

        }
        */

        turn(87 , "right");

        release();
    }
}