
package org.usfirst.frc.team1018.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.usfirst.frc.team1018.robot.subsystems.Drivetrain;
import org.usfirst.frc.team1018.robot.subsystems.Shooter;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
    public Drivetrain drivetrain;
    public Shooter shooter;
    public static OI oi;

    VictorSP roller, extender;
    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
        drivetrain = Drivetrain.getInstance();
        shooter = Shooter.getInstance();
        roller = new VictorSP(6);
        extender = new VictorSP(7);
        oi = new OI();

    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
        shooter.disable();
    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     * <p>
     * You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        shooter.enable();
        SmartDashboard.putNumber("Speed", 0);
        SmartDashboard.putNumber("TP", 0);
        SmartDashboard.putNumber("TI", 0);
        SmartDashboard.putNumber("TD", 0);

    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        shooter.setPID(SmartDashboard.getNumber("TP", 0), SmartDashboard.getNumber("TI", 0), SmartDashboard.getNumber("TD", 0));
        drivetrain.driveCheesy(oi.getThrottle(), oi.getTurn(), oi.getQuickTurn());
        shooter.setSpeed(SmartDashboard.getNumber("Speed", 0));
        SmartDashboard.putNumber("TopShooterFeedback", shooter.top.getRate());
        SmartDashboard.putNumber("BottomShooterFeedback", shooter.bottom.getRate());
        SmartDashboard.putNumber("TopShooterOutput", shooter.top.getSetpoint());
        SmartDashboard.putNumber("BottomShooterOutput", shooter.bottom.getSetpoint());
        Scheduler.getInstance().run();
    }

    /**
     *
     */
    @Override
    public void testInit() {
        shooter.disable();
    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
        LiveWindow.run();
    }
}
