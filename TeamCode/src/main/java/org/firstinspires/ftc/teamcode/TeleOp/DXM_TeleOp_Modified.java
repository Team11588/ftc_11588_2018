/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Hardware.HardwareDxm;


@TeleOp(name="DXM TeleOp_Modified" , group = "TeleOp")
public class DXM_TeleOp_Modified extends OpMode {

    HardwareDxm robot = new HardwareDxm();

    final static int PINCER_ENCODER = (int) (1140 * 1.25);
    final static double SERVO_SHIFT = 0.01;
    public boolean speed = false;

    double leftY1;
    double leftX1;
    double rightX1;

    public void init() {
        robot.init(hardwareMap);

        robot.fLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.fRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.bLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.bRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.fLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.fRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.bLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.bRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }


    @Override
    public void loop() {
        if (speed == false) {
            leftY1 = Math.abs(gamepad1.left_stick_y) > 0.3 ? -gamepad1.left_stick_y : 0;
        } else {
            leftY1 = Math.abs(gamepad1.left_stick_y) > 0.3 ? -gamepad1.left_stick_y / 2 : 0;
        }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        double[] wheelPower = wheelPower(leftX1, leftY1, rightX1);
        robot.fLeft.setPower(wheelPower[0]);
        robot.fRight.setPower(wheelPower[1]);
        robot.bLeft.setPower(wheelPower[2]);
        robot.bRight.setPower(wheelPower[3]);

        telemetry.addData("Front Left", robot.fLeft.getCurrentPosition());
        telemetry.addData("Front Right", robot.fRight.getCurrentPosition());
        telemetry.addData("Back Left", robot.bLeft.getCurrentPosition());
        telemetry.addData("Back Right", robot.bRight.getCurrentPosition());
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     telemetry.update();
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public double[] wheelPower(double x, double y, double r) {

        double speed = speed(x, y);
        double angle = angle(x, y);
        double pFL = (speed * (Math.sin((angle) + ((Math.PI) / 4)))) + r;
        double pFR = (speed * (Math.cos((angle) + ((Math.PI) / 4)))) - r;
        double pBL = (speed * (Math.cos((angle) + ((Math.PI) / 4)))) + r;
        double pBR = (speed * (Math.sin((angle) + ((Math.PI) / 4)))) - r;
        double[] wP = {pFL, pFR, pBL, pBR};

        return wP;
    }

    public double speed(double x, double y) {
        return Math.sqrt((Math.pow(x, 2)) + (Math.pow(y, 2)));
    }

    public double angle(double x, double y) {
        if ((y == 0) && (x == 0))
            return 0;
        else
            return Math.atan2(x, y);
    }
//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
}