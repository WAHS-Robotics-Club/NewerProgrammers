package teamcode.OpModes.Autonomi.Yos;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import teamcode.Objects.BananaFruit;
import teamcode.Objects.DriveTrain;
import teamcode.Objects.Grabber;


@Autonomous(name ="HOWDY - BSIDE")
public class HOWDYauto_BSIDE extends LinearOpMode {

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
        //----------------------------
        //Grabbing the block
        grabber.toggleGrabberAuto();
        //Drive to desired location
        driveTrain.StrafeRightBy(telemetry, 12);
        driveTrain.moveForwardsBy(telemetry, 12);
        driveTrain.turnToHeading(gyro, telemetry, -45);
        driveTrain.moveForwardsBy(telemetry,16);
        //Sets the height to a safe height AND also rotates hinge
        grabber.HingeHoister(telemetry,-24,48,1);
        grabber.YoinkHTop(2000);
        grabber.toggleGrabberAuto();
        grabber.YoinkHTop(2000);
        grabber.RotateHingeTo(telemetry,-20);
        driveTrain.moveForwardsBy(telemetry,-12);
        grabber.HingeHoister(telemetry, -24, -48,1);

        //LETZ GOOOOOOOOOOOOOO!!!!

        //STILL REQUIRES TESTING
        //I GOT THIS!

    }
}