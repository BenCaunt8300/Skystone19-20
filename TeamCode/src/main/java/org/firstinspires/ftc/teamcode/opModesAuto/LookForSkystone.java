package org.firstinspires.ftc.teamcode.opModesAuto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.teamcode.mechanisms.Robot;

@Autonomous()
//@Disabled
public class LookForSkystone extends OpMode {
    private final Robot robot = new Robot();


    // Code to run ONCE when the driver hits INIT
    @Override
    public void init() {
        robot.init(hardwareMap);
    }

    // Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
    @Override
    public void loop() {
       robot.nav.strafe(0.3);
    }


}
