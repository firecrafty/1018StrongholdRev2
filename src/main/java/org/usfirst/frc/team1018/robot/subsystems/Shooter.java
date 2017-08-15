package org.usfirst.frc.team1018.robot.subsystems;


import edu.wpi.first.wpilibj.CounterBase;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1018.lib.Util;
import org.usfirst.frc.team1018.robot.RobotMap;

/**
 * @author Ryan Blue
 */
public class Shooter extends Subsystem {

    private static Shooter instance;

    public final ShooterModule top = new ShooterModule(RobotMap.SHOOTER_TOP_PWM, RobotMap.SHOOTER_TOP_ENC_A_DIO, RobotMap.SHOOTER_TOP_ENC_B_DIO, false);
    public final ShooterModule bottom = new ShooterModule(RobotMap.SHOOTER_BOTTOM_PWM, RobotMap.SHOOTER_BOTTOM_ENC_A_DIO, RobotMap.SHOOTER_BOTTOM_ENC_B_DIO, true);

    private Shooter() {
    }

    public void setSpeed(double speed) {
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

    public class ShooterModule extends PIDSubsystem {

        private TalonSRX shooterMotor;

        private Encoder shooterCimcoder;

        private ShooterModule(int motor, int enc_a, int enc_b, boolean reverseDirection) {
            // Initialize your subsystem here
            super(0, 0, 0, 0.05, 0);
            setAbsoluteTolerance(10);
            setOutputRange(0, 1);
            getPIDController().setContinuous(false);
            shooterMotor = new TalonSRX(motor);
            shooterMotor.setInverted(reverseDirection);
            shooterCimcoder = new Encoder(enc_a, enc_b, reverseDirection, CounterBase.EncodingType.k2X);
            shooterCimcoder.setDistancePerPulse(0.05);
        }

        @Override
        public void initDefaultCommand() {
            // Set the default command for a subsystem here.
            // setDefaultCommand(new MySpecialCommand());
        }

        @Override
        protected double returnPIDInput() {
            // Return your input value for the PID loop
            // e.g. a sensor, like a potentiometer:
            // yourPot.getAverageVoltage() / kYourMaxVoltage;
            return shooterCimcoder.getRate();
        }

        @Override
        protected void usePIDOutput(double output) {
            shooterMotor.set(Util.limit(shooterMotor.get() + output, 0, 1));
        }

        public double getRate() {
            return getPosition();
        }
    }


    @Override
    protected void initDefaultCommand() {

    }
}
