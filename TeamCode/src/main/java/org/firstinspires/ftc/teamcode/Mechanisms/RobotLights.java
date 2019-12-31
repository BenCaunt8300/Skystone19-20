package org.firstinspires.ftc.teamcode.Mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import android.graphics.Color;
import android.support.annotation.ColorInt;

public class RobotLights {
    private QwiicLEDStrip top_leds;
    private QwiicLEDStrip bottom_leds;


    /**
     * initilizes the lights
     * @param hwmap hardware map from config
     */
    void init(HardwareMap hwmap) {
        top_leds = hwmap.get(QwiicLEDStrip.class, "robot_lights_top");
        //       leds.setBrightness(5);
        top_leds.setColor(Color.rgb(0, 0, 0));

        bottom_leds = hwmap.get(QwiicLEDStrip.class, "robot_lights_bottom");
        //       leds.setBrightness(5);
        bottom_leds.setColor(Color.rgb(0, 0, 0));
    }
    /**
     * @return list of tests 
     */
    List<QQ_Test> getTests() {
        return Arrays.asList(
                new QQ_TestLights("tests the top led strip",top_leds),
                new QQ_TestLights("tests the bottom led strip", bottom_leds)
        );
    }

    /**
     * Sets the color of the strip
     * @param color what color to set it to as a color int
     * @see ColorInt
     */
    public void setColor(@ColorInt int color) {
        top_leds.setColor(color);
        bottom_leds.setColor(color);
    }

    /**
     * sets the alliance lights
     * @param isBlue is the alliance blue
     */
    public void allianceLights(boolean isBlue) {
        if (isBlue) {
            setColor(Color.rgb(0, 0, 255));
        } else {
            setColor(Color.rgb(255, 0, 0));
        }
    }
}