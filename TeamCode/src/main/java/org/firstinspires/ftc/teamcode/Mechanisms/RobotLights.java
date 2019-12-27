package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import android.graphics.Color;
import android.support.annotation.ColorInt;

public class RobotLights {
    private QwiicLEDStrip leds;
    private ElapsedTime elapsedTime = new ElapsedTime();
    private int red_color;
    private int blue_color;
    private int green_color;
    private
    Random rand = new Random();

    void init(HardwareMap hwmap) {
        leds = hwmap.get(QwiicLEDStrip.class, "robot_lights");
        //       leds.setBrightness(5);
        leds.setColor(Color.rgb(0, 0, 0));
    }
    List<QQ_Test> getTests() {
        return Arrays.asList(
                new QQ_TestLights("tests the led strip",leds)
        );
    }

    public void setColor(@ColorInt int color) {
        leds.setColor(color);
    }

    public void allianceLights(boolean isBlue) {
        if (isBlue) {
            leds.setColor(Color.rgb(0, 0, 255));
        } else {
            leds.setColor(Color.rgb(255, 0, 0));
        }
    }
}