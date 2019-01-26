package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.*;
import org.team217.*;
import org.team217.pid.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class teleopDrive extends Command {
	PID turnPID1 = RobotMap.turnPID;
	PID angleCorrectPID = new PID(0.01, 0.0, 0.0, 100);
	PID followPID = new PID(0, 0.06, 0, 100);

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
    	double speed = -Range.deadband(Robot.m_oi.driver.getY(), 0.1);
    	double turn = Range.deadband(Robot.m_oi.driver.getZ(), 0.1);

    	if(Robot.m_oi.circleDriver.get()) {
			double x = Robot.getXVis();
			double area = Robot.getAreaVis();
			
			double kP = Range.inRange(.03 / Math.sqrt(area) - .01, 0.015, 0.025);
			
			turnPID1.setPID(kP, 0.002, 0);
			turnPID1.setMinMax(-0.2, 0.2);
			
			double visTurn;

			if(Range.isWithinRange(x, -0.5, 0.5)) {
				visTurn = 0.0;
				turnPID_OnTarget = true;
			}
			else {
				visTurn = turnPID1.getOutput(0, x);
				
				turnPID_OnTarget = false;
			}

			turn = visTurn;
			
			followPID.setP(0.64);
			followPID.setMinMax(-0.2, 0.2);
			double visSpeed = -followPID.getOutput(Math.sqrt(area), 0.98);
			speed = (area != 0) ? Range.inRange(visSpeed, -0.6, 0.6) : 0;
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
