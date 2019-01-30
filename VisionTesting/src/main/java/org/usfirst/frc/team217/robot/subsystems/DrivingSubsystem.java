package org.usfirst.frc.team217.robot.subsystems;

import org.usfirst.frc.team217.robot.*;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivingSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        RobotMap.leftMidSlave.follow(RobotMap.leftMaster);
        RobotMap.leftBackSlave.follow(RobotMap.leftMaster);
        RobotMap.rightMidSlave.follow(RobotMap.rightMaster);
        RobotMap.rightBackSlave.follow(RobotMap.rightMaster);
    }
    
    protected double[] antiTip(double leftSpeed, double rightSpeed) {
		if(RobotMap.pigeon.getPitch() >= 12.0) {
			leftSpeed = -0.5;
			rightSpeed = 0.5;
		}
		else if(RobotMap.pigeon.getPitch() <= -12.0) {
			leftSpeed = 0.5;
			rightSpeed = -0.5;
		}
		
		double[] speed = {leftSpeed, rightSpeed};
		return speed;
    }
    
	public void teleopDrive(double speed, double turn, boolean antiTipOn) {
		double leftSpeed = speed + turn;
        double rightSpeed = -speed + turn;
		
		if(antiTipOn) {
			double[] antiTipSpeed = antiTip(leftSpeed, rightSpeed);
			leftSpeed = antiTipSpeed[0];
			rightSpeed = antiTipSpeed[1];
		}
        
        RobotMap.leftMaster.set(leftSpeed);
        RobotMap.rightMaster.set(rightSpeed);
	}

}

    	

