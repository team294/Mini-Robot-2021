/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalSource;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Constants.ShooterConstants.*;


public class Shooter extends SubsystemBase {
  /**
   * Creates a new VelocityBallExit.
   */
 
  private final DigitalInput  input = new DigitalInput(dioExitBallSensor+1);
  
  private final Counter count = new Counter( dioExitBallSensor);
 
  public Shooter() {
    count.setSemiPeriodMode(true);
    count.setUpSourceEdge(true, false);
  }

  /**
   * @return true = power cell detected, false = no power cell
   * 
  **/
  public void getBall() {
    boolean ball =  !input.get();
    int shotCount = count.get()/2;    // semi period mode counts on the up and down
    double period = count.getPeriod();
    SmartDashboard.putBoolean("Ball Exit",ball);
    SmartDashboard.putNumber("Shots Fired",shotCount);
    SmartDashboard.putNumber("Period", period);
  }

  public void zeroCount(){
    count.reset();
  }

  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
