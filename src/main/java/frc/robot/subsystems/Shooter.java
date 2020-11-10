/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Constants.ShooterConstants.*;


public class Shooter extends SubsystemBase {
  /**
   * Creates a new VelocityBallExit.
   */
  boolean ball=false, lastBall=false;
  int shotCount = 0;
  double timeDelay, startTime = 0, endTime = 0;
 
  private final DigitalInput  input = new DigitalInput(dioExitBallSensor);
  
 
  public Shooter() {
  }

  /**  
  public void getPeriod() {
     SmartDashboard.putNumber("Shots Fired",shotCount);
     SmartDashboard.putNumber("Period", timeDelay);
  }
**/
  public void getTimeDelay(){  //  This has only 20 msec resolution due to update time

    // to fix use interupts or stay in while loop until next detector sensed.  
    // if max velocity is 50 feet/sec and minimum velocity is 5 fps and sensors are 3 inches apart
    //   then time difference is between 5 and 50

    // this is for one detector sensing ball presence.  Needs to be 2 detectors so ball diameter doesn't 
    // enter into calculatons

    ball =  !input.get();
    SmartDashboard.putBoolean("Ball Exit",ball);
    
    if(!lastBall && ball){
      startTime = Timer.getFPGATimestamp();
      ++shotCount;
      while ( ball = !input.get() ){
        endTime = Timer.getFPGATimestamp();
        timeDelay = endTime - startTime;
        if (timeDelay > .05) break;  // Don't hang here forever should be <.05 when 2 detectors
                                  // 0.05 won't trigger watchdog, but will lose 1 or 2 DS updates
      }
    }
    lastBall = ball;
 
    //timeDelay = endTime - startTime;
    
    SmartDashboard.putNumber("Shots Fired",shotCount);
    SmartDashboard.putNumber("Time Delay", timeDelay);
  }

  public void zeroCount(){
    shotCount = 0;
  }

  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
