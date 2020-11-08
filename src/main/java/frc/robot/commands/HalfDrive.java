/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.XboxController;

/**
 * An example command that uses an example subsystem.
 */
public class HalfDrive extends CommandBase {
  private final DriveTrain driveTrain;
  private final XboxController xBoxController;
  private double leftPercent, rightPercent;
  private int counter = 0;
  private boolean driving = false;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public HalfDrive(DriveTrain driveTrain, XboxController xBoxController) {
    this.driveTrain = driveTrain;
    this.xBoxController = xBoxController;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (xBoxController.getXButtonPressed() && !driving) {
      this.driving = true;
    }

    if (driving) {
      driveTrain.tankDrive(0.5, 0.5);
      this.counter++;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.tankDrive(0, 0);
    this.counter = 0;
    this.driving = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return counter > 250;
  }
}
