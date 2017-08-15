package org.usfirst.frc.team1018.robot;

/**
 * @author Ryan Blue
 */
public class DrivePacket {
    public double leftMotor;
    public double rightMotor;

    public DrivePacket(double left, double right) {
        this.leftMotor = left;
        this.rightMotor = right;
    }

    @Override
    public String toString() {
        return "L: " + leftMotor + ", R: " + rightMotor;
    }
}
