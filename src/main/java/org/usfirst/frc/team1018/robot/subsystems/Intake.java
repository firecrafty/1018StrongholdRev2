package org.usfirst.frc.team1018.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team1018.robot.RobotMap;

/**
 * @author Ryan Blue
 */
public class Intake extends Subsystem {
    public Roller roller = new Roller();

    public void initDefaultCommand() {
        // Set the default command, if any, for a subsystem here. Example:
        //    setDefaultCommand(new MySpecialCommand());
    }
    public class Roller extends Subsystem{
        private VictorSP rollerMotor = new VictorSP(RobotMap.INTAKE_ROLLER_PWM);
        private VictorSP extenderMotor = new VictorSP(RobotMap.INTAKE_EXTENDER_PWM);
        public void setRollerForward() {
            rollerMotor.set(1);
        }

        public void setExtenderMotor(double value) {
            extenderMotor.set(value);
        }

        @Override
        protected void initDefaultCommand() {

        }
    }
}

