package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
/**
 * Created by kearneyg20428 on 9/21/2017.
 */

/*
PSA DXM stands for Deus Ex Machina
 */
public class HardwareDxm {
/*
I created this naming system as the letter f or b meaning front or back because
with machanum wheels all 4 wheels need to be motorized
*/

    public DcMotor fLeft = null;
    public DcMotor fRight = null;
    public DcMotor bLeft = null;
    public DcMotor bRight = null;
    public DcMotor lateralClaw = null;
    public DcMotor verticalClaw = null;

    public Servo claw = null;

    HardwareMap hwMap = null;
    private ElapsedTime period = new ElapsedTime();

    public void init(HardwareMap ahwMap)
    {
    hwMap = ahwMap;

        fLeft = hwMap.dcMotor.get("fLeft");
        fRight = hwMap.dcMotor.get("fRight");
        bLeft = hwMap.dcMotor.get("bLeft");
        bRight = hwMap.dcMotor.get("bRight");
        //lateralClaw = hwMap.dcMotor.get("lateralClaw");
        //verticalClaw = hwMap.dcMotor.get("verticalClaw");

        //claw = hwMap.servo.get("claw");

        fLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        fRight.setDirection(DcMotorSimple.Direction.FORWARD);
        bLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        bRight.setDirection(DcMotorSimple.Direction.FORWARD);
        //lateralClaw.setDirection(DcMotorSimple.Direction.FORWARD);
        //verticalClaw.setDirection(DcMotorSimple.Direction.FORWARD);

        fLeft.setPower(0);
        fRight.setPower(0);
        bLeft.setPower(0);
        bRight.setPower(0);
       // lateralClaw.setPower(0);
        //verticalClaw.setPower(0);

       // claw.setPosition(0);

        fLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        fRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        bRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       // lateralClaw.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
       // verticalClaw.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

    }
}
