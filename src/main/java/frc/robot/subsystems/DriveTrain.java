/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix.motorcontrol.ControlMode;
// import com.ctre.phoenix.motorcontrol.DemandType;
// import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.ctre.phoenix.motorcontrol.TalonFXFeedbackDevice;
// import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import static frc.robot.Constants.DriveConstants.*;
// import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;




public class DriveTrain extends SubsystemBase {
  /**
   * Creates a new DriveTrain.
   */
  private final WPI_TalonSRX leftMotor1;
  private final WPI_TalonSRX rightMotor1;
  private final DifferentialDrive diffDrive;

  public DriveTrain() {

    // configure motors
    leftMotor1 = new WPI_TalonSRX(canLeftDriveMotor);
    rightMotor1 = new WPI_TalonSRX(canRightDriveMotor);

    leftMotor1.configFactoryDefault();
    rightMotor1.configFactoryDefault();

     // create the differential drive AFTER configuring the motors
     diffDrive = new DifferentialDrive(leftMotor1, rightMotor1);
     diffDrive.setRightSideInverted(true);
     
  }

  /**
   * Tank drive method for differential drive platform. The calculated 
   * values will be squared to decrease sensitivity at low speeds.
   * @param leftPercent The robot's left side percent along the X axis [-1.0..1.0]. Forward is positive.
   * @param rightPercent The robot's right side percent along the X axis [-1.0..1.0]. Forward is positive.
   */
  public void tankDrive(double leftPercent, double rightPercent) {
    //System.out.println("Drive Train "+ leftPercent + rightPercent);
    diffDrive.tankDrive(leftPercent, rightPercent, true);
  }

   /**
   * Stops motors by calling tankDrive(0, 0).
   */
  public void stop() {
    tankDrive(0.0, 0.0);
  }

  /**
   * Call when not using arcade drive or tank drive to turn motors to
   * ensure that motor will not cut out due to differential drive safety.
   */
  public void feedTheDog() {
    diffDrive.feed();
  }


  /**
   * @param percent percent output (+1 = forward, -1 = reverse)
   */
  public void setLeftMotorOutput(double percent) {
    leftMotor1.set(ControlMode.PercentOutput, percent);
    feedTheDog();
  }

  /**
   * @param percent percent output (+1 = forward, -1 = reverse)
   */
  public void setRightMotorOutput(double percent) {
    rightMotor1.set(ControlMode.PercentOutput, -percent);
    feedTheDog();
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("Left Motor", leftMotor1.getMotorOutputVoltage());
    // This method will be called once per scheduler run
  }
}
