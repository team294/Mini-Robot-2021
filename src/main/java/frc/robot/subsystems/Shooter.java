/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
// import edu.wpi.first.wpilibj.InterruptableSensorBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.utilities.FileLog;
import static frc.robot.Constants.ShooterConstants.*;


public class Shooter extends SubsystemBase {
  /**
   * Creates a new BallExitVelocity.
   */
  boolean ball=false, lastBall;
  int shotCount = 0;
  double timeDelay, velocity, startTime = 0, endTime = 0, lastEndTime;
  double[][] shotData;
  double[] velocityData;    // I couldn't find a good way to display a double array on Shufflboard
  FileLog log;
 
  private final DigitalInput  inputA = new DigitalInput(dioExitBallSensorA);
  private final DigitalInput  inputB = new DigitalInput(dioExitBallSensorB);
   
 
  public Shooter(FileLog log) {
    this.log = log;
     inputA.requestInterrupts();
     inputA.setUpSourceEdge(false, true);
     inputB.requestInterrupts();
     inputB.setUpSourceEdge(false, true);

     shotData = new double [5][2];
  }

  
  public void getTimeDelay(){  

    /*  This method measures the time delay between when a ball breaks the beam of two sensors 
        It uses this time delay to calculate the ball velocity using the distance between the sensors
    */
    
          // This is for testing sensors only runs every 20 msec
    SmartDashboard.putBoolean("Detector 1", !inputA.get());   // comment it out so it doesn't waste resources
    SmartDashboard.putBoolean("Detector 2", !inputB.get());
 
    
    startTime = inputA.readFallingTimestamp();  // Time since program started
    endTime   = inputB.readFallingTimestamp();

    if((startTime > lastEndTime) && (endTime > startTime)){  // new Ball
     
                     
      if (shotCount >= 5) shotCount = 0;   // 5 is limit on number of power cells
  
      timeDelay = endTime - startTime;
      
      // calculate velocity  for detSpacing(inches)  of sensors
      velocity = 1/( 12 * timeDelay/detSpacing);   // result in ft/sec
             
      
      shotData[shotCount][0] =  velocity;
      shotData[shotCount][1] = startTime;
     
      ++shotCount;   
      SmartDashboard.putNumber("Shots Fired",shotCount);  // shot 1 is in array[0]
      SmartDashboard.putNumber("Time Delay", timeDelay);
      SmartDashboard.putNumber("Velocity", velocity);         
    
      System.out.println("Shot " + shotCount + ";  Start Time " + startTime + ":    Velocity " +velocity );  
      updateShooterLog(false);
           
    }
    lastEndTime = endTime;


    
  }

  public void zeroCount(){
    shotCount = 0;
  }

  


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  /**
   * Write information about hopper to fileLog.
   * @param logWhenDisabled true = log when disabled, false = discard the string
   */
	public void updateShooterLog(boolean logWhenDisabled) {
		log.writeLog(logWhenDisabled, "Shooter", "Update Variables",  
      "Velocity", velocity, 
      "Time Delay", timeDelay
    );
  }
}
