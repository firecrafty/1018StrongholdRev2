package org.usfirst.frc.team1018.robot;

import edu.wpi.first.wpilibj.CameraServer;
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
        CameraServer.getInstance().startAutomaticCapture();
        drivetrain = Drivetrain.getInstance();
        shooter = Shooter.getInstance();
        roller = new VictorSP(6);
        extender = new VictorSP(7);
        oi = new OI();
        /*
        SmartDashboard.putNumber("kP", 0);
        SmartDashboard.putNumber("kI", 0);
        SmartDashboard.putNumber("kD", 0);
        SmartDashboard.putNumber("kF", 0);
        SmartDashboard.putNumber("TopShooterFeedback", 0);
        SmartDashboard.putNumber("BottomShooterFeedback", 0);
        SmartDashboard.putNumber("TopShooterPIDOutput", 0);
        SmartDashboard.putNumber("BottomShooterPIDOutput", 0);
        SmartDashboard.putNumber("TopMotor", 0);
        SmartDashboard.putNumber("BottomMotor", 0);*/
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
        //shooter.enable();
        //SmartDashboard.putNumber("Speed", );
        //SmartDashboard.putNumber("kP", 0);
        //SmartDashboard.putNumber("kI", 0);
        //SmartDashboard.putNumber("kD", 0);
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {
        drivetrain.driveHelper.arcadeDrive(oi.getThrottle()*0.5, oi.getTurn(), false);
        //drivetrain.driveArcade(oi.getThrottle(), oi.getTurn());
        /**if(oi.joystick.getRawButton(1)) {
         roller.set(1);
         } else if(oi.joystick.getRawButton(2)) {
         roller.set(-1);
         } else {
         roller.set(0);
         }
         shooter.bottom.shooterMotor.set(-((oi.joystick.getRawAxis(3) - 1) / 2));
         shooter.top.shooterMotor.set(-((oi.joystick.getRawAxis(3) - 1) / 2));
         if(oi.joystick.getRawButton(5)) {
         extender.set(-0.5);
         } else if(oi.joystick.getRawButton(3)) {
         extender.set(0.5);
         } else {
         extender.set(0);
         }*/


        /*shooter.setPID(SmartDashboard.getNumber("kP", 0), SmartDashboard.getNumber("kI", 0), SmartDashboard.getNumber("kD", 0), SmartDashboard.getNumber("kF", 0));
        shooter.setSpeedRpm(SmartDashboard.getNumber("Speed", 0));
        //drivetrain.driveCheesy(oi.getThrottle(), oi.getTurn(), oi.getQuickTurn());
        SmartDashboard.putNumber("TopShooterFeedback", shooter.top.getRateRpm());
        SmartDashboard.putNumber("BottomShooterFeedback", shooter.bottom.getRateRpm());
        SmartDashboard.putNumber("TopShooterPIDOutput", shooter.top.getPIDController().get());
        SmartDashboard.putNumber("BottomShooterPIDOutput", shooter.bottom.getPIDController().get());
        SmartDashboard.putNumber("TopMotor", shooter.top.shooterMotor.get());
        SmartDashboard.putNumber("BottomMotor", shooter.bottom.shooterMotor.get());
        Scheduler.getInstance().run();
        //shooter.bottom.shooterMotor.set(SmartDashboard.getNumber("Speed", 0));
        //shooter.top.shooterMotor.set(SmartDashboard.getNumber("Speed", 0));*/
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
