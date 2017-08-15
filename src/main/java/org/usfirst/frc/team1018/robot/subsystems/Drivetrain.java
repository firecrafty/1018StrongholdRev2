package org.usfirst.frc.team1018.robot.subsystems;

import edu.wpi.first.wpilibj.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1018.robot.DrivePacket;
import org.usfirst.frc.team1018.robot.RobotMap;
import org.usfirst.frc.team1018.robot.Util;

/**
 * @author Ryan Blue
 */
public class Drivetrain extends Subsystem {

    private static Drivetrain instance;


    double mQuickStopAccumulator;
    public static final double kThrottleDeadband = 0.02;
    private static final double kWheelDeadband = 0.02;
    private static final double kTurnSensitivity = 1.0;

    private TalonSRX leftTalonA = new TalonSRX(RobotMap.LEFT_DRIVE_A_PWM);
    private TalonSRX leftTalonB = new TalonSRX(RobotMap.LEFT_DRIVE_B_PWM);
    private TalonSRX rightTalonA = new TalonSRX(RobotMap.RIGHT_DRIVE_A_PWM);
    private TalonSRX rightTalonB = new TalonSRX(RobotMap.RIGHT_DRIVE_B_PWM);

    private Drivetrain() {
        rightTalonA.setInverted(true);
        rightTalonB.setInverted(true);
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }

    public void driveCheesy(double throttle, double wheel, boolean isQuickTurn) {

        double overPower;

        double angularPower;

        if(isQuickTurn) {
            if(Math.abs(throttle) < 0.2) {
                double alpha = 0.1;
                mQuickStopAccumulator = (1 - alpha) * mQuickStopAccumulator + alpha * Util.limit(wheel, 1.0) * 2;
            }
            overPower = 1.0;
            angularPower = wheel;
        } else {
            overPower = 0.0;
            angularPower = Math.abs(throttle) * wheel * kTurnSensitivity - mQuickStopAccumulator;
            if(mQuickStopAccumulator > 1) {
                mQuickStopAccumulator -= 1;
            } else if(mQuickStopAccumulator < -1) {
                mQuickStopAccumulator += 1;
            } else {
                mQuickStopAccumulator = 0.0;
            }
        }

        double rightPwm = throttle - angularPower;
        double leftPwm = throttle + angularPower;
        if(leftPwm > 1.0) {
            rightPwm -= overPower * (leftPwm - 1.0);
            leftPwm = 1.0;
        } else if(rightPwm > 1.0) {
            leftPwm -= overPower * (rightPwm - 1.0);
            rightPwm = 1.0;
        } else if(leftPwm < -1.0) {
            rightPwm += overPower * (-1.0 - leftPwm);
            leftPwm = -1.0;
        } else if(rightPwm < -1.0) {
            leftPwm += overPower * (-1.0 - rightPwm);
            rightPwm = -1.0;
        }

        leftTalonA.set(leftPwm);
        leftTalonB.set(leftPwm);
        rightTalonA.set(rightPwm);
        rightTalonB.set(rightPwm);
    }

    public void stop() {
        driveCheesy(0,0, false);
    }
    public static Drivetrain getInstance() {
        if(instance == null) instance = new Drivetrain();
        return instance;
    }
}

