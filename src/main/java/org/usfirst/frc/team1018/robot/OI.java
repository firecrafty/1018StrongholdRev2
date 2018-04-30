package org.usfirst.frc.team1018.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public Joystick joystick = new Joystick(0);
	public Joystick yoke = new Joystick(1);
	//private XboxController xboxController = new XboxController(2);

    public double getThrottle() {
        return joystick.getY();
    }
    public double getTurn() {
        return yoke.getX();
    }
    public boolean getQuickTurn() {
        return yoke.getRawButton(1);
    }

}
