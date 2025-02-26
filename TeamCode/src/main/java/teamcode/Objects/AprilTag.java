package teamcode.Objects;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import org.firstinspires.ftc.vision.VisionPortal;
import android.util.Size;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class AprilTag {

    public static double fx = 1502.91;
    public static double fy = 1052.91;
    public static double cx = 970.487;
    public static double cy = 389.479;

    public static final boolean calibratedCamera = true;

    private static AprilTagProcessor aprilTagProcessor;

    private static final int DESIRED_TAG_ID = -1;
    private static AprilTagDetection desiredTag = null;

    boolean targetFound = false;

    //public static AprilTag initAprilTag(HardwareMap hardwareMap) {


        //return aprilTag;
    //}

    public void tagScan(Telemetry telemetry) {

    }






















}
