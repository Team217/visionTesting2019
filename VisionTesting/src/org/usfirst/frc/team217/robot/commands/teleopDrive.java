package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.OI;
import org.usfirst.frc.team217.robot.PID;
import org.usfirst.frc.team217.robot.Robot;
import org.usfirst.frc.team217.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class teleopDrive extends Command {
	PID turnPID1 = RobotMap.turnPID;
	PID angleCorrectPID = new PID(0.01, 0.0, 0.0, 100);

	public boolean drivePID_OnTarget = false;
	public boolean turnPID_OnTarget = false;
    public teleopDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    boolean antiTipOn = true;

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double speed = -Robot.deadband(Robot.m_oi.driver.getY(), 0.1);
    	double turn = Robot.deadband(Robot.m_oi.driver.getZ(), 0.1);
    	double x = Robot.getXVis();
    	turnPID1.setPID(.085, .00001, 0);		
		double visSpeed = turnPID1.getOutput(0, x);
		
		turnPID_OnTarget = false;
		
		if(0 >= (x - .5) && 0 <= (x + .5)) {
			visSpeed = 0.0;
			turnPID_OnTarget = true;
		}

    	if(Robot.m_oi.circleDriver.get()) {
    		turn = visSpeed;
    	}
    	
    	if(Robot.m_oi.leftBumperDriver.get()) {
    		antiTipOn = false;
    	}
    	else if(Robot.m_oi.rightBumperDriver.get()) {
    		antiTipOn = true;
    	}
    	
    	Robot.kDrivingSubsystem.teleopDrive(speed, turn, antiTipOn);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
