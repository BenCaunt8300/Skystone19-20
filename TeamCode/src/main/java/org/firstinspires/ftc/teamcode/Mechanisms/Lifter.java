package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import java.util.Arrays;
import java.util.List;


class Lifter {
    private DcMotor lift;

    void init(HardwareMap hwmap){
        lift = hwmap.get(DcMotor.class, "lift");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    List<QQ_Test> getTests() {
        return Arrays.asList(
                new QQ_TestMotor("Lift-Down", -0.2, lift),
                new QQ_TestMotor("lift-Up", 0.2, lift)
        );
    }

    void move(double speed){
        lift.setPower(speed);
    }
    int getPosiition(){
        return lift.getCurrentPosition();
    }
}
