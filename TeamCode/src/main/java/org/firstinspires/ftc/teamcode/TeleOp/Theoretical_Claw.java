package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Hardware.HardwareDxm;

/**
 * Created by kearneyg20428 on 10/3/2017.
 */

@TeleOp(name = "Claw" , group = "TeleOp")
public class Theoretical_Claw extends OpMode {

    HardwareDxm robot = new HardwareDxm();

    double leftY2 = -gamepad2.left_stick_y;
    double rightY2 = -gamepad2.right_stick_y;
    double triggerR2 = gamepad2.right_trigger;
    double triggerL2 = gamepad2.left_trigger;


    public void init() {
        robot.init(hardwareMap);
    }

    public void loop() {

        robot.verticalClaw.setPower(leftY2);
        robot.lateralClaw.setPower(rightY2);



    }
    }


