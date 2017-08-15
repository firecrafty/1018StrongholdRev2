package org.usfirst.frc.team1018.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1018.robot.RobotMap;

/**
 * @author Ryan Blue
 */
public class Intake extends Subsystem {


    public void initDefaultCommand() {
        // Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }
    public class Roller extends Subsystem{
        private VictorSP rollerMotor = new VictorSP(RobotMap.INTAKE_ROLLER_PWM);

        public void setForward() {
            rollerMotor.set(1);
        }

        @Override
        protected void initDefaultCommand() {

        }
    }
}

