package org.usfirst.frc.team217.robot.commands;

import org.usfirst.frc.team217.robot.Robot;
import org.usfirst.frc.team217.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;

/**
 * A {@code Command} that turns the bot in auton.
 * 
 * @author ThunderChickens 217
 */
public class autonTurn extends Command {

	double turnAngle = 0;
	double kP = 0.0025;
	double kI = 0.000003;
	double kD = 0.0;
	
	/**
	 * A {@code Command} that turns the bot using an encoder target and {@code PID}.
	 * 
	 * @param target1
	 *          The target gyro angle
	 * @param kP1
	 *          The P value for {@code PID}
	 * @param kI1
	 *          The I value for {@code PID}
	 * @param kD1
	 *          The D value for {@code PID}
	 */
    public autonTurn(double turnAngle1, double kP1, double kI1, double kD1) {
    	super("autonTurn");
    	requires(Robot.kDrivingSubsystem);
		turnAngle = turnAngle1;
		kP = kP1;
		kI = kI1;
		kD = kD1;
    }
    
	/**
	 * A {@code Command} that turns the bot using an encoder target and {@code PID}.
	 * 
	 * </p>This version of the turn command can still complete if the bot does not reach its target. 
	 * 
	 * @param target1
	 *          The target gyro angle
	 * @param kP1
	 *          The P value for {@code PID}
	 * @param kI1
	 *          The I value for {@code PID}
	 * @param kD1
	 *          The D value for {@code PID}
	 * @param timeout
	 *          The command timeout, in seconds
	 */
    public autonTurn(double turnAngle1, double kP1, double kI1, double kD1, double timeout) {
    	super("autonTurn");
    	requires(Robot.kDrivingSubsystem);
		turnAngle = turnAngle1;
		kP = kP1;
		kI = kI1;
		kD = kD1;
		setTimeout(timeout);
    }

    /** Called just before this {@code Command} runs the first time. */
    protected void initialize() {
    	System.out.println("Doin a turny thing");
    	Robot.kDrivingSubsystem.originalAngle = turnAngle;
    }

    /** Called repeatedly when this {@code Command} is scheduled to run. */
    protected void execute() {
    	Robot.kDrivingSubsystem.drive();
    	
    }

    /** Make this return {@code true} when this {@code Command} no longer needs to run {@code execute()}. */
    protected boolean isFinished() {
        return Robot.kDrivingSubsystem.turnPID_OnTarget || isTimedOut();
    }

    /** Called once after {@code isFinished()} returns {@code true}. */
    protected void end() {
    	RobotMap.rightMaster.set(0);
    	RobotMap.leftMaster.set(0);
    	RobotMap.rightMaster.resetEncoder();
    }

    /** Called when another {@code Command} which requires one or more of the same subsystems is scheduled to run. */
    protected void interrupted() {
    	RobotMap.rightMaster.set(0);
    	RobotMap.leftMaster.set(0);
    	RobotMap.rightMaster.resetEncoder();
    }
}
