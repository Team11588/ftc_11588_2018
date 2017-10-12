package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Hardware.TheoreticalHardwareDxm;

/**
 * Created by kearneyg20428 on 10/3/2017.
 */

@TeleOp(name = "Claw" , group = "TeleOp")
public class Theoretical_Claw extends OpMode {

    TheoreticalHardwareDxm robot = new TheoreticalHardwareDxm();

    final static double SERVO_SHIFT = 0.05;


    public void init() {
        robot.init(hardwareMap);
    }

    public void loop() {

        double triggerR2 = gamepad2.right_trigger;
        double triggerL2 = gamepad2.left_trigger;

        if (triggerL2 > 0.5)
       {
           double position = robot.claw.getPosition();
           double newPosition = Range.clip( position + SERVO_SHIFT, Servo.MIN_POSITION, Servo.MAX_POSITION);
           robot.claw.setPosition(newPosition);
       }
       else if (triggerR2 > .5)
       {
           double position = robot.claw.getPosition();
           double newPosition = Range.clip( position - SERVO_SHIFT, Servo.MIN_POSITION, Servo.MAX_POSITION);
           robot.claw.setPosition(newPosition);
       }


    }
    }


