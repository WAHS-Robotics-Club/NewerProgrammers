package teamcode.OpModes.TeleOp.Yos;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import teamcode.Objects.DriveTrain;
import teamcode.Objects.Grabber;


@TeleOp(name ="HOWDY! (That's the name not the greeting) ON THIS EPISODE OF A NEW AND IMPROVED <HOWDY!> SERIES, TWO HOWDERS TWO WORK TOGETHER TO SUCCESSFULLY OUT HOWDY THE COMPETITION.", group = "TeleOp")
public class DualDriverTeleOp extends OpMode {
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
        //DriveTrain system
        driveTrain.manualDrive(gamepad1);
        driveTrain.Strafin(gamepad1);
        driveTrain.checkToggleSpeed(gamepad1);
        DriveTrain.logTelemetry(telemetry, driveTrain);

        //Yoinkage System (Servos)
        grabber.ManualToggleYoinker(gamepad2);
        grabber.YoinkHingeage(gamepad2);

        //Hoisting System
        grabber.Hoistage(gamepad2);
        grabber.Hingeing(gamepad2);
        grabber.Anching(gamepad2);

        //Arm controls

    }



}