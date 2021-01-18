/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;
import frc.robot.utilities.FileLog;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class BallExitVelocity extends CommandBase {
  private final Shooter shooter;
  private final FileLog log;
  /**
   * Creates a new Velocity */
  public BallExitVelocity (Shooter shooter, FileLog log) {
    this.shooter = shooter;
    this.log = log;
    log.writeLog(false, "Shooter", "Initialized", 
      "Velocity", 0, 
      "Time Delay", 0, 
      "Shots Fired", 0);
    addRequirements(shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putNumber("Shots Fired", 0.0);  // shot 1 is in array[0]
    SmartDashboard.putNumber("Time Delay", 0.0);
    SmartDashboard.putNumber("Velocity", 0.0);      
    shooter.zeroCount();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    shooter.getTimeDelay();
    shooter.updateShooterLog(false);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
