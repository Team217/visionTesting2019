package org.usfirst.frc.team217.robot.subsystems;

import org.usfirst.frc.team217.robot.*;
import org.team217.ctre.*;
import org.team217.pid.*;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DrivingSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.	
	
	WPI_TalonSRX rightMaster1 = RobotMap.rightMaster;
	WPI_VictorSPX rightMidSlave1 = RobotMap.rightMidSlave;
	WPI_VictorSPX rightBackSlave1 = RobotMap.rightBackSlave;
	WPI_TalonSRX leftMaster1 = RobotMap.leftMaster;
	WPI_VictorSPX leftMidSlave1 = RobotMap.leftMidSlave;
	WPI_VictorSPX leftBackSlave1 = RobotMap.leftBackSlave;
	PigeonIMU pigeon1 = RobotMap.pigeon;
	double x = 0;	
	double pigeonAngle = 0.0;
	public double speed = 0;

	PID turnPID1 = RobotMap.turnPID;
	PID angleCorrectPID = new PID(0.01, 0.0, 0.0, 100);

	public boolean drivePID_OnTarget = false;
	public boolean turnPID_OnTarget = false;
	public double originalAngle = 0.0;

	//PigeonIMU pigeon1 = RobotMap.pigeon;
	
	//if turning left, left motors backwards right fwds, vice versa

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
		leftMaster1.setup();
		rightMaster1.setup();
		rightMidSlave1.follow(rightMaster1);
		rightBackSlave1.follow(rightMaster1);
		leftMidSlave1.follow(leftMaster1);
		leftBackSlave1.follow(leftMaster1);
		rightMaster1.resetEncoder();
    }
    
    protected double[] antiTip(double leftSpeed, double rightSpeed) {
		if(pigeon1.getPitch() >= 12.0) {
			leftSpeed = -0.5;
			rightSpeed = 0.5;
		}
		else if(pigeon1.getPitch() <= -12.0) {
			leftSpeed = 0.5;
			rightSpeed = -0.5;
		}
		
		double[] speed = {leftSpeed, rightSpeed};
		return speed;
	}
    
    protected void interrupted() {
    	RobotMap.rightMaster.set(0);
    	RobotMap.leftMaster.set(0);
    	RobotMap.rightMaster.resetEncoder();
    }
    public void stop(){
    	leftMaster1.set(0);
    	rightMaster1.set(0);
    }
    
	public void teleopDrive(double speed, double turn, boolean antiTipOn) {
		double leftSpeed = speed + turn;
		double rightSpeed = -speed + turn;
		
		if(antiTipOn && !Climber.currentPTO.equals(Climber.PTOMode.climbMode)) {
			double[] antiTipSpeed = antiTip(leftSpeed, rightSpeed);
			leftSpeed = antiTipSpeed[0];
			rightSpeed = antiTipSpeed[1];
		}
		
		leftMaster1.set(leftSpeed);
		rightMaster1.set(rightSpeed);

		System.out.println(turn);
	}

}

    	

