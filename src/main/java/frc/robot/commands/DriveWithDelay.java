package frc.robot.commands;

// import java.sql.DriverAction;
// import java.util.Timer;

// import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;
import static frc.robot.Constants.DriveConstants.*;

public class DriveWithDelay extends CommandBase {
  private final DriveTrain driveTrain;
  private final double leftPercent, rightPercent;
  private final int delay;
  private int currentTick;
//   private int uT = new DriveConstants(updateTick);
  /**
   * Creates a new DriveWithDelay to control two motors.
   * Drives for x delay
   */
  public DriveWithDelay(DriveTrain driveTrain, double leftPercent, double rightPercent, int delay) {
    this.driveTrain = driveTrain;
    this.leftPercent = leftPercent;
    this.rightPercent = rightPercent;
    this.delay = delay;
    addRequirements(driveTrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      currentTick = 0;
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      driveTrain.tankDrive(leftPercent, rightPercent);
      currentTick++;
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.tankDrive(0.0, 0.0);
  }


  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (updateTick*currentTick >= delay) ? true : false;
  }
}
