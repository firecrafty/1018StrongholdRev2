package org.usfirst.frc.team1018.robot.subsystems;


import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1018.robot.RobotMap;

/**
 * @author Ryan Blue
 */
public class Shooter extends Subsystem {

    private static Shooter instance;

    public final ShooterModule top = new ShooterModule(RobotMap.SHOOTER_TOP_PWM, RobotMap.SHOOTER_TOP_ENC_A_DIO, RobotMap.SHOOTER_TOP_ENC_B_DIO, false, false);
    public final ShooterModule bottom = new ShooterModule(RobotMap.SHOOTER_BOTTOM_PWM, RobotMap.SHOOTER_BOTTOM_ENC_A_DIO, RobotMap.SHOOTER_BOTTOM_ENC_B_DIO, true, true);

    private Shooter() {
    }

    public void setSpeedRpm(double speed) {
        top.setSetpoint(speed);
        bottom.setSetpoint(speed);
    }

    public void setPID(double p, double i, double d) {
        top.getPIDController().setPID(p, i, d);
        bottom.getPIDController().setPID(p, i, d);
    }

    public void setPID(double p, double i, double d, double f) {
        top.getPIDController().setPID(p, i, d, f);
        bottom.getPIDController().setPID(p, i, d, f);
    }

    public void enable() {
        top.enable();
        bottom.enable();
    }

    public void disable() {
        top.disable();
        bottom.disable();
    }

    public static Shooter getInstance() {
        if(instance == null) instance = new Shooter();
        return instance;
    }

    public class ShooterModule implements PIDOutput {

        public TalonSRX shooterMotor;

        private Encoder shooterCimcoder;

        private PIDController controller;

        private ShooterModule(int motor, int enc_a, int enc_b, boolean reverseMotor, boolean reverseEncoder) {
            // Initialize your subsystem here
            System.out.println("Initializing shooter module " + motor);
            shooterMotor = new TalonSRX(motor);
            shooterMotor.setInverted(reverseMotor);
            shooterCimcoder = new Encoder(enc_a, enc_b, reverseEncoder, CounterBase.EncodingType.k2X);
            shooterCimcoder.setDistancePerPulse(RobotMap.SHOOTER_OUTPUT_DIST_PER_PULSE);
            shooterCimcoder.setPIDSourceType(PIDSourceType.kRate);
            controller = new PIDController(0, 0, 0, 0, shooterCimcoder, this);
            controller.setAbsoluteTolerance(5);
            controller.setInputRange(0, 100);
            controller.setOutputRange(0, 1);
            controller.setContinuous(false);
        }

        public double getRateRpm() {
            return shooterCimcoder.getRate();
        }

        public void enable() {
            controller.enable();
        }

        public void disable() {
            controller.disable();
        }

        public void setSetpoint(double setpoint) {
            controller.setSetpoint(setpoint);
        }

        public PIDController getPIDController() {
            return controller;
        }

        @Override
        public void pidWrite(double output) {
            shooterMotor.set(output);
        }


    }


    @Override
    protected void initDefaultCommand() {

    }
}
