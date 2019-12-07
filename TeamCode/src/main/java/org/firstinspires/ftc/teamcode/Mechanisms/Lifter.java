package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

import java.util.Arrays;
import java.util.List;


class Lifter {
    private DcMotor lift;
    private DistanceSensor downdistance;
    private static final double DOWN_DISTANCE_CM = 5.5;
    private static final double UP_DISTANCE_CM = 55;

    void init(HardwareMap hwmap){
        lift = hwmap.get(DcMotor.class, "lifter");
        downdistance = hwmap.get(DistanceSensor.class, "downward_distance");
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        lift.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    List<QQ_Test> getTests() {
        return Arrays.asList(
                new QQ_TestMotor("Lift-Down", -0.2, lift),
                new QQ_TestMotor("lift-Up", 0.2, lift),
                new QQ_TestDistanceSensor("Down Sensor:", downdistance)
        );
    }

    boolean move(double speed) {
        boolean returnValue = true;
        if(speed < 0){
            if (downdistance.getDistance(DistanceUnit.CM) <= DOWN_DISTANCE_CM) {
                speed = 0;
                returnValue = false;
            }
        } else {
            if (downdistance.getDistance(DistanceUnit.CM) >= UP_DISTANCE_CM) {
                speed = 0;
                returnValue = false;
            }
        }
        lift.setPower(speed);
        return returnValue;
    }
    int getPosiition(){
        return lift.getCurrentPosition();
    }
}