/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.InterruptHandlerFunction;
import edu.wpi.first.wpilibj.InterruptableSensorBase;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.utilities.ShotData;
import frc.robot.utilities.FileLog;

import static frc.robot.Constants.ShooterConstants.*;


public class Shooter extends SubsystemBase {
  /**
   * Creates a new BallExitVelocity.
   */
  boolean ball=false, lastBall;
  int shotCount = 0;
  double timeDelay, velocity, startTime = 0, endTime = 0, lastEndTime;
  // double[] velocityData;    // I couldn't find a good way to display a double array on Shufflboard
  public FileLog log;
  // public ArrayList<ShotData> records = new ArrayList<ShotData>();
 
  private final DigitalInput  inputA = new DigitalInput(dioExitBallSensorA);
  private final DigitalInput  inputB = new DigitalInput(dioExitBallSensorB);
   
 
  public Shooter(FileLog log) {
    this.log = log;
    // InterruptHandlerFunction<Object> aInputHandler = new InterruptHandlerFunction<Object>();

     inputA.requestInterrupts(new InterruptHandlerFunction<Object>() {
       
      @Override
      public void interruptFired(int interruptAssertedMask, Object param) {
        inputAEvent();
      }
     });
     inputA.setUpSourceEdge(false, true);


     inputB.requestInterrupts(new InterruptHandlerFunction<Object>() {
       
      @Override
      public void interruptFired(int interruptAssertedMask, Object param) {
        inputBEvent();
      }
     });
     inputB.setUpSourceEdge(false, true);
  }

  private void inputAEvent() {
    startTime = inputA.readFallingTimestamp();  // Time since program started
  }

  private void inputBEvent() {
    endTime   = inputB.readFallingTimestamp();
    if((startTime > lastEndTime) && (endTime > startTime)){  // new Ball
     
                     
      if (shotCount >= 5) shotCount = 0;   // 5 is limit on number of power cells
  
      timeDelay = endTime - startTime;
      
      // calculate velocity  for detSpacing(inches)  of sensors
      velocity = 1/( 12 * timeDelay/detSpacing);   // result in ft/sec

      // records.add(new ShotData(velocity, timeDelay, shotCount));
      
      SmartDashboard.putNumber("Shots Fired",shotCount);  // shot 1 is in array[0]
      SmartDashboard.putNumber("Time Delay", timeDelay);
      SmartDashboard.putNumber("Velocity", velocity);         
    
      updateShooterLog(false);

      System.out.println("Shot " + shotCount + ";  Start Time " + startTime + ":    Velocity " +velocity );  
      
      lastEndTime = endTime;
    }
  }
  
  public void getTimeDelay(){  

    /*  This method measures the time delay between when a ball breaks the beam of two sensors 
        It uses this time delay to calculate the ball velocity using the distance between the sensors
    */
    
          // This is for testing sensors only runs every 20 msec
    SmartDashboard.putBoolean("Detector 1", !inputA.get());   // comment it out so it doesn't waste resources
    SmartDashboard.putBoolean("Detector 2", !inputB.get());
 
    // for (ShotData record: records) {
      // log.writeLog(false, "Shooter", "retrieveData", "Velocity", record.velocity, "Time Delay", record.timeDelay, "Shot Count", record.shotCount);
    // }

    // records.clear();
    // return records; // I don't really know what you want to do with the information
  }

  public void zeroCount(){
    shotCount = 0;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    
  }

  /**
   * Write information about shooter to fileLog.
   * @param logWhenDisabled true = log when disabled, false = discard the string
   */
  public void updateShooterLog(boolean logWhenDisabled) {
    log.writeLog(logWhenDisabled, "Shooter", "Output Data", 
      "Velocity", velocity, 
      "Time Delay", timeDelay, 
      "Shots Fired", shotCount);
  }
}
