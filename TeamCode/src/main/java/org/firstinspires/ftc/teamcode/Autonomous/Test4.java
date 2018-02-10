package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.DcMotor;

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


@Autonomous(name = "Test4")
public class Test4 extends BaseCombinedAutonomous {


    @Override
    public void runOpMode() throws InterruptedException {

        myinit();


        waitForStart();



        pinch();

        teamColor = "blue";
        robot.jewelKnockDevice.setPosition(.35);
        int wait1 = 0;
        robot.driveForword(.5, .3);
        while (wait1 < 500) {
            wait1++;
        }
        readVuImage();
        robot.jewelKnockDevice.setPosition(.85);
        robot.driveBackword(.5, .3);

        // robot.jewelKnockDevice.setPosition(.35);

        knockJewelRight();
        robot.jewelKnockDevice.setPosition(1);

        robot.driveBackword(1.5, .3);


        turn(90, "left");

        int mark = 3;

        columnTwoMove(mark);

        robot.lift.setTargetPosition(0);
        robot.lift.setPower(.85);
        robot.lift.setTargetPosition(-1140 / 2);
        robot.lift.setPower(1);
        while (robot.lift.isBusy()) ;


        turn(90 , "left");

        robot.driveForword(.7,.3);

        robot.open();

        robot.driveBackword(.4 , .3);

        push();

    }
}

