package teamcode.OpModes.Autonomi.Yos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import teamcode.Objects.BananaFruit;
import teamcode.Objects.DriveTrain;
import teamcode.Objects.Grabber;


@Autonomous(name ="HOWDY - OSIDE")
public class HOWDYauto_OSIDE extends LinearOpMode {

    DriveTrain driveTrain;
    Grabber grabber;

    @Override
    public void runOpMode() throws InterruptedException {


        driveTrain = DriveTrain.initDriveTrain(hardwareMap, DcMotor.ZeroPowerBehavior.BRAKE);
        grabber = Grabber.initGrabber(hardwareMap);

        telemetry.addData("IsBusy", driveTrain.isBusy());
        driveTrain.logTelemetry(telemetry, driveTrain);
        telemetry.update();
        driveTrain.resetEncoders();
        BananaFruit gyro = new BananaFruit();
        gyro.runBananaFruit(hardwareMap, telemetry);
        telemetry.update();

        waitForStart();

        //ONLY MODIFY STUFF AFTER THIS
        //-----------------------------
        driveTrain.StrafeRightBy(telemetry, 48);

        //STILL REQUIRES TESTING
        //I GOT THIS!

    }
}