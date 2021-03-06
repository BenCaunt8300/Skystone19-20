package org.firstinspires.ftc.teamcode.opModesAuto;

import android.graphics.Color;

import com.acmerobotics.dashboard.config.Config;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

@Config
public class SkystoneOpenCvPipeline extends OpenCvPipeline {
    static public int SKYSTONE_PIXEL_START_X = 0;
    static public int SKYSTONE_PIXEL_START_Y = 110;
    static public int SKYSTONE_PIXEL_WIDTH = 59;
    static public int SKYSTONE_PIXEL_HEIGHT = 35;
    static Scalar STONE_COLOR = new Scalar(0,255,0);
    static Scalar SKYSTONE_COLOR = new Scalar(0,0,255);
    public int stoneLocation;

    /**
     * processes our image
     *
     * @param input the mat to process
     * @return returns the same mat with colored rectangles corresponding to stone location
     */
    @Override
    public Mat processFrame(Mat input){
        Rect stone1 = new Rect(SKYSTONE_PIXEL_START_X, SKYSTONE_PIXEL_START_Y, SKYSTONE_PIXEL_WIDTH, SKYSTONE_PIXEL_HEIGHT);
        Rect stone2 = new Rect(SKYSTONE_PIXEL_START_X + SKYSTONE_PIXEL_WIDTH, SKYSTONE_PIXEL_START_Y, SKYSTONE_PIXEL_WIDTH, SKYSTONE_PIXEL_HEIGHT);
        Rect stone3 = new Rect(SKYSTONE_PIXEL_START_X + (2 * SKYSTONE_PIXEL_WIDTH), SKYSTONE_PIXEL_START_Y, SKYSTONE_PIXEL_WIDTH, SKYSTONE_PIXEL_HEIGHT);
        double stone1Yel = getAmountYellow(input, stone1);
        double stone2Yel = getAmountYellow(input, stone2);
        double stone3Yel = getAmountYellow(input, stone3);

        if((stone1Yel < stone2Yel) && (stone1Yel < stone3Yel)){
            stoneLocation = 3;
        } else if((stone2Yel < stone1Yel) && (stone2Yel < stone3Yel)) {
            stoneLocation = 2;
        } else if((stone3Yel < stone1Yel) && (stone3Yel < stone2Yel)){
            stoneLocation = 1;
        } else{
            stoneLocation = 0;
        }



        /*
         * Draw three boxes in stone position
         */
        Imgproc.rectangle(input, stone1, (stoneLocation == 3) ? SKYSTONE_COLOR : STONE_COLOR, 4);

        Imgproc.rectangle(input, stone2, (stoneLocation == 2) ? SKYSTONE_COLOR : STONE_COLOR, 4);

        Imgproc.rectangle(input, stone3, (stoneLocation == 1) ? SKYSTONE_COLOR : STONE_COLOR, 4);

        return input;
    }
    /**
     * Gets the amount of yellow that the sensors cam see in a rectangle.
     *
     * @param input what the sensor sees
     * @param rect The area that the data is being taken from.
     *
     * @return the amount of yellow that was seen.

     */
    private double getAmountYellow(Mat input, Rect rect){
        Mat stoneMat = input.submat(rect);
        Scalar color = Core.mean(stoneMat);
        return color.val[0] + color.val[1]; //throw away blue and look at yellow (red + green)
    }
}
