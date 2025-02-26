package teamcode.OpModes.Autonomi.Yos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import teamcode.Objects.BananaFruit;
import teamcode.Objects.DriveTrain;
import teamcode.Objects.Grabber;

@Autonomous(name ="Universal Parking - Autonomous - BSIDE")
public class UniversalParking_BSIDE extends LinearOpMode {

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

        waitForStart(); //ONLY MODIFY STUFF AFTER THIS

        driveTrain.StrafeLeftBy(telemetry, 2);
        driveTrain.moveForwardsBy(telemetry, 48);
        driveTrain.turnToHeading(gyro, telemetry, 90);
        driveTrain.moveForwardsBy(telemetry, 12);
        grabber.RotateHingeTo(telemetry, -15);
        grabber.setHeightTo(telemetry,12);
        

        //STILL REQUIRES TESTING
    }
}