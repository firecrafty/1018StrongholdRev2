package org.usfirst.frc.team1018.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
    public static final int LEFT_DRIVE_A_PWM = 0;
    public static final int LEFT_DRIVE_B_PWM = 2;
    public static final int RIGHT_DRIVE_A_PWM = 1;
    public static final int RIGHT_DRIVE_B_PWM = 3;

    public static final int SHOOTER_TOP_PWM = 4;
    public static final int SHOOTER_BOTTOM_PWM = 5;

    public static final int SHOOTER_TOP_ENC_A_DIO = 10;
    public static final int SHOOTER_TOP_ENC_B_DIO = 11;
    public static final int SHOOTER_BOTTOM_ENC_A_DIO = 12;
    public static final int SHOOTER_BOTTOM_ENC_B_DIO = 13;

    public static final int INTAKE_ROLLER_PWM = 6;
    public static final int INTAKE_EXTENDER_PWM = 7;
}
