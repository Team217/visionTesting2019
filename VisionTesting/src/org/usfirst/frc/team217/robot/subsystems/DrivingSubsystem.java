package org.usfirst.frc.team217.robot.subsystems;

import org.usfirst.frc.team217.robot.PID;
import org.usfirst.frc.team217.robot.PigeonIMU;
import org.usfirst.frc.team217.robot.Robot;
import org.usfirst.frc.team217.robot.RobotMap;
import org.usfirst.frc.team217.robot.WPI_TalonSRX;

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
	double x = 0;	
	double pigeonAngle = 0.0;


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
    
    public void drive() {
    	
    	x = Robot.getXVis();
    	
    /*	if(x < .5 && x > -.5) {
    		leftMaster1.set(0);
    		rightMaster1.set(0);
    	} 
    	else if(x < -.5){
    		leftMaster1.set(-.3);
    		rightMaster1.set(-.3);
    	}
    	else if(x > .5) {
    		leftMaster1.set(.3);
    		rightMaster1.set(.3);
    	}
    	
    }*/	
    	
		turnPID1.setPID(.105, .00001, 0);		
		double speed = turnPID1.getOutput(0, x);
		
		turnPID_OnTarget = false;
		
		if(0 >= (x - .5) && 0 <= (x + .5)) {
			speed = 0.0;
			turnPID_OnTarget = true;
		}
		
		leftMaster1.set(speed);
		rightMaster1.set(speed);
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

}

    	

