/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.InterruptableSensorBase;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static frc.robot.Constants.ShooterConstants.*;


public class Shooter extends SubsystemBase {
  /**
   * Creates a new VelocityBallExit.
   */
  boolean ball=false, lastBall;
  int shotCount = 0;
  double timeDelay, velocity, startTime = 0, endTime = 0, lastEndTime;
 
  private final DigitalInput  inputA = new DigitalInput(dioExitBallSensorA);
  private final DigitalInput  inputB = new DigitalInput(dioExitBallSensorB);
   
 
  public Shooter() {

     inputA.requestInterrupts();
     inputA.setUpSourceEdge(false, true);
     inputB.requestInterrupts();
     inputB.setUpSourceEdge(false, true);
  }

  /**  
  public void getPeriod() {
     SmartDashboard.putNumber("Shots Fired",shotCount);
     SmartDashboard.putNumber("Period", timeDelay);
  }
**/
  public void getTimeDelay(){  

    /*  This method measures the time delay between when a ball breaks the beam of two sensors
    
          // This is for testing sensors only run every 20 msec
    SmartDashboard.putBoolean("Detector 1", !inputA.get());   // comment it out so it doesn't waste resources
    SmartDashboard.putBoolean("Detector 2", !inputB.get());
 
    
    startTime = inputA.readFallingTimestamp();  // Time since program started
    endTime   = inputB.readFallingTimestamp();

    if((startTime > lastEndTime) && (endTime > startTime)){  // new Ball
      ++shotCount;
  
      System.out.println("Start Time " + startTime + ":    End Time " +endTime);                
        
      timeDelay = endTime - startTime;
        // calculate velocity  for 3 inch separation of sensors
      //velocity = 1/( 4 * timeDelay);   // result in ft/sec

      // calculate velocity  for 1.5 inch separation of sensors
      velocity = 1/( 8 * timeDelay);   // result in ft/sec
   
    }
    lastEndTime = endTime;
 
     
    SmartDashboard.putNumber("Shots Fired",shotCount);
    SmartDashboard.putNumber("Time Delay", timeDelay);
    SmartDashboard.putNumber("Velocity", velocity);                     

  
/*** 
    //  This has only 20 msec resolution due to update time
    // to fix use interupts   
    // if max velocity is 50 feet/sec and minimum velocity is 5 fps and sensors are 3 inches apart
    //   then time difference is between 5 and 50

  ball =  !inputA.get();      // This needs to be on an interrupt since only run every 20 msec
  SmartDashboard.putBoolean("Ball Exit",ball);
  
  if(!lastBall && ball){
    startTime = Timer.getFPGATimestamp();
    ++shotCount;          //  TODO  this counts ball going either way??
    while ( inputB.get() ){   // no ball in 2nd sensor
      endTime = Timer.getFPGATimestamp();
      timeDelay = endTime - startTime;
      if (timeDelay > .05) break;  // Don't hang here forever should be <.05 
                                // 0.05 won't trigger watchdog, but will lose 1 or 2 DS updates
       // calculate velocity  for 3 inch separation of sensors
      velocity = 1/( 4 * timeDelay);   // result in ft/sec
    }
  }
  lastBall = ball;

   
  SmartDashboard.putNumber("Shots Fired",shotCount);
  SmartDashboard.putNumber("Time Delay", timeDelay);
  SmartDashboard.putNumber("Velocity", velocity);                     
***/
}

  public void zeroCount(){
    shotCount = 0;
  }

  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }
}
