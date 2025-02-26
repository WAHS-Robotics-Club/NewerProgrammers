package teamcode.OpModes.TeleOp.Yos;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import teamcode.Objects.DriveTrain;
import teamcode.Objects.Grabber;


@TeleOp(name ="This is a Teleop that uses one controller", group = "TeleOp")
public class SingleDriverTeleop extends OpMode {
    //Initializing the main objects:
    Grabber grabber;
    DriveTrain driveTrain;

    @Override
    public void init(){
        //Hardware mapping the servos:
        grabber = Grabber.initGrabber(hardwareMap);
        driveTrain = DriveTrain.initDriveTrain(hardwareMap, DcMotor.ZeroPowerBehavior.FLOAT);

        driveTrain.resetEncoders();
        driveTrain.setRunMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override public void loop(){
        //Drive Train manual control system
        driveTrain.manualDrive(gamepad1);
        driveTrain.Strafin(gamepad1);
        driveTrain.checkToggleSpeed(gamepad1);
        DriveTrain.logTelemetry(telemetry, driveTrain);

        //Grabber System (Servos)grabber.ManualToggleYoinker(gamepad1);
        grabber.YoinkHingeage(gamepad1);

        //Linear Bearing Slides controls (Lifting with Motors)
        grabber.SingleHoistage(gamepad1);
        grabber.SingleHingeing(gamepad1);
        }
}