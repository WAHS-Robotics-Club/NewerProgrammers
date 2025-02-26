package teamcode.Objects;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import teamcode.Objects.Tool.Toggle;

public class Grabber {
    public Servo YoinkR;
    public Servo YoinkL;
    public CRServo YoinkH;
    public DcMotor Hinge;
    public DcMotor Hoister;
    private Toggle toggleGrabber;
    public Servo Anchor;
    private Toggle toggleAnchor;
    private Toggle YoinkToggle;

    public static Grabber initGrabber(HardwareMap hardwareMap) {
        //Creates and hardware maps the grabber element
        Grabber grabber = new Grabber();
        grabber.YoinkR = hardwareMap.servo.get("YoinkR");
        grabber.YoinkL = hardwareMap.servo.get("YoinkL");
        grabber.YoinkH = hardwareMap.crservo.get("YoinkH");
        grabber.Anchor = hardwareMap.servo.get("Anchor");

        grabber.Hinge = hardwareMap.dcMotor.get("Hinge");
        grabber.Hinge.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        grabber.Hinge.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        grabber.Hoister = hardwareMap.dcMotor.get("Hoister");
        grabber.Hoister.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        grabber.Hoister.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        grabber.toggleGrabber = new Toggle();
        grabber.toggleAnchor = new Toggle();
        grabber.YoinkToggle = new Toggle();

        return grabber;
    }


    public void Yoinkage(Gamepad gamepad1) {
        if (gamepad1.a) {
            YoinkR.setPosition(0.8);
            YoinkL.setPosition(0.2);
        } else if (gamepad1.b) {
            YoinkR.setPosition(0.3);
            YoinkL.setPosition(0.7);
        }
    }

    public void YoinkHingeToggle (Gamepad gamepad) {
        if (gamepad.right_stick_button) {
            YoinkToggle.toggle();
        }
    } // useless

    public void YoinkHingeage(Gamepad gamepad) {
        if (gamepad.x) {
            YoinkH.setPower(-0.8);
        } else if (gamepad.y) {
            YoinkH.setPower(-0.2);
        } else if (gamepad.atRest()) {
            YoinkH.setPower(0.6);
        }
    }



    public void checkToggleGrabber() {
        if (toggleGrabber.isToggled()) {
            YoinkR.setPosition(0.8);
            YoinkL.setPosition(0.2);
        } else {
            YoinkR.setPosition(0.3);
            YoinkL.setPosition(0.7);
        }
    }

    public void checkToggleAnchor() {
        if (toggleGrabber.isToggled()) {
            Anchor.setPosition(1);
        } else {
            Anchor.setPosition(0);
        }
    }

    public void OPENYEAAAAAAAAH() {
        YoinkR.setPosition(0.8);
        YoinkL.setPosition(0.2);
    }


    public void setHeightTo(Telemetry telemetry, int targetPosition) throws InterruptedException {
        Thread.sleep(1);

        Hoister.setPower(1);
        Hoister.setTargetPosition(targetPosition);
        Hoister.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int i = 0;
        while (Hoister.isBusy() && i < 500) {
            telemetry.update();
            i++;
            Thread.sleep(1);
        }
    }

    public void RotateHingeTo(Telemetry telemetry, int targetPosition) throws InterruptedException {
        Thread.sleep(1);
            Hinge.setPower(1);
            Hinge.setTargetPosition(targetPosition);
            Hinge.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            int guy = 0;
            while (Hinge.isBusy() && guy < 500) {
                telemetry.update();
                guy++;
                Thread.sleep(1);
            }
    } // I have feeling this wont work how I want it to work...

    public void HingeHoister (Telemetry telemetry, int HingeTargetPosition, int HoistTargetPosition, double hingePower) throws InterruptedException {
        Thread.sleep(1);
        Hinge.setPower(hingePower);
        Hoister.setPower(1);
        Hinge.setTargetPosition(HingeTargetPosition);
        Hoister.setTargetPosition(HoistTargetPosition);
        Hinge.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        Hoister.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        int guy = 0;
        while (Hinge.isBusy() && guy < 500) {
            telemetry.update();
            guy++;
            Thread.sleep(1);
        }
    }

    public void ManualToggleYoinker(Gamepad gamepad) {
        if (gamepad.a) {
            toggleGrabber.toggle();
        }
        checkToggleGrabber();
    }

    public void Anching(Gamepad gamepad) {
        if (gamepad.dpad_up) {
            Anchor.setPosition(0);
        } else if (gamepad.dpad_down) {
            Anchor.setPosition(1);
        }
    }

    public void forceToggleGrabber() {
        toggleGrabber.toggle();
    }

    public void toggleGrabberAuto() throws InterruptedException {
        forceToggleGrabber();
        Thread.sleep(50);
        checkToggleGrabber();
    }

    public void YoinkHTop(int millis) throws InterruptedException {
        YoinkH.setPower(0.6);
        Thread.sleep(millis);
    }

    public void YoinkHMid(int millis) throws InterruptedException {
        YoinkH.setPower(0);
        Thread.sleep(millis);
    }

    /*
    public void ManualLinActuator(Gamepad gamepad) {
        //Moves the arm up and down
            if (gamepad.right_trigger >= 0.1 && gamepad.left_trigger >= 0.1) {
                LinActuatorControl(0, gamepad);
            } else if (gamepad.right_trigger >= 0.1) {
                LinActuatorControl(gamepad.right_trigger, gamepad);
            } else if (gamepad.left_trigger >= 0.1) {
                LinActuatorControl(-gamepad.left_trigger, gamepad);
            } else {
                LinActuatorControl(0, gamepad);
            }
    }

    private void LinActuatorControl(float Power, Gamepad gamepad){
        if(linActuator.getCurrentPosition() < 18000 && linActuator.getCurrentPosition() > -10 || gamepad.dpad_right) {
            if (Math.abs(Power) >= 0.1) {
                linActuator.setPower(Power);
            } else {
                linActuator.setPower(0);
            }
        }else if(linActuator.getCurrentPosition() > -10){
            linActuator.setPower(-.3);
        }else{
            linActuator.setPower(.3);
        }

        if(gamepad.dpad_up){
            linActuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            for(int i = 0; i < 100; i++){/*slow down*/
            /*linActuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }
    }

    public void ManualArmMotor(Gamepad gamepad){
        if (gamepad.right_trigger >= 0.1 && gamepad.left_trigger >= 0.1) {
            armMotor.setPower(0);
        } else if (gamepad.right_trigger >= 0.1) {
            armMotor.setPower(gamepad.right_trigger);
        } else if (gamepad.left_trigger >= 0.1) {
            armMotor.setPower(-gamepad.left_trigger);
        } else {
            //LinActuatorControl(0, gamepad);
        }
    }*/

    public void SingleHingeing(Gamepad gamepad) {
        Hinge.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        if (gamepad.right_trigger >= 0.1) {
            Hinge.setPower((gamepad.right_trigger) * -0.15);
        } else if (gamepad.left_trigger > 0.1) {
            Hinge.setPower((gamepad.left_trigger) * 0.1);
        } else if (gamepad.right_trigger < 0.1) {
            Hinge.setPower(0);
        } // Sam was here
        //else {
        //LinActuatorControl(0, gamepad);
        //}
    }

    public void SingleHoistage(Gamepad gamepad) {
        if (!gamepad.dpad_up && !gamepad.dpad_down) {
            Hoister.setPower(0); // Stop when not pressed
        } else if (gamepad.dpad_down) {
            Hoister.setPower(1); // When Pressed
        } else if (gamepad.dpad_up) {
            Hoister.setPower(-1); // When Pressed
        }
    } // Sam was here

    public void Hingeing(Gamepad gamepad2) {
        if (gamepad2.left_stick_y == 0) {
            Hinge.setPower(0);
        } else if (gamepad2.left_stick_y != 0) {
            Hinge.setPower(gamepad2.left_stick_y / 2);
        }
    } // DOES THIS WORK? IDK (probs not ngl)
    // NOWAY IT DOES!!!!!!
    // I AM HERE!!!!!!

    public void Hoistage(Gamepad gamepad) {
        if (gamepad.right_trigger >= 0.1 && gamepad.left_trigger >= 0.1) {
            Hoister.setPower(0);
        } else if (gamepad.right_trigger >= 0.1) {
            Hoister.setPower(gamepad.right_trigger*0.5);
        } else if (gamepad.left_trigger >= 0.1) {
            Hoister.setPower(-gamepad.left_trigger*0.5);
        } else if (gamepad.atRest()) {
            Hoister.setPower(0);
        }
    }




}

























