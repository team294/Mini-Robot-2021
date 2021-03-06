/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj2.command.Command;
import static frc.robot.Constants.OIConstants.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.utilities.FileLog;


/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final FileLog log = new FileLog("A4");
  private final DriveTrain m_driveTrain = new DriveTrain();
  private final Shooter shooter = new Shooter(log);
  
  private final ExampleCommand m_autoCommand = new ExampleCommand(m_driveTrain);  // runs in autonomous

  XboxController xboxController = new XboxController(usbXboxController);
    
  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
    configureShuffleboard(); // configure shuffleboard

    m_driveTrain.setDefaultCommand(new DriveWithJoystick(m_driveTrain, xboxController));

  }

  /**
   * Define Shuffleboard mappings.
   */
  public void configureShuffleboard() {
    // drive train subsystem
    SmartDashboard.putNumber("Left Motor",0);
    SmartDashboard.putNumber("Right Motor",0);


    SmartDashboard.putData("DriveTurnLeft", new DriveSetPercentOutput(-0.4, 0.4, m_driveTrain));
    
    SmartDashboard.putData("CheckBallVelocity", new BallExitVelocity( shooter));
  }
  
  

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
  

  public void teleopPeriodic() {
   
  }


}
