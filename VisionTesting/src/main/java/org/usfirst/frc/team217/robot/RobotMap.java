/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team217.robot;

//import com.revrobotics.CANSparkMax;
import org.team217.rev.*;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import org.team217.ctre.*;

import edu.wpi.first.wpilibj.*;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 * 
 * @author ThunderChickens 217
 */
public class RobotMap {

    // Controllers
    public static final int driverPort = 0;
    public static final int operPort = 1;

    public static final PigeonIMU pigeon = new PigeonIMU(0);

    // Motors
    public static final CANSparkMax leftMaster = new CANSparkMax(4, MotorType.kBrushless),
                leftMidSlave = new CANSparkMax(5, MotorType.kBrushless),
                leftRearSlave = new CANSparkMax(6, MotorType.kBrushless),
                rightMaster = new CANSparkMax(1, MotorType.kBrushless),
                rightMidSlave = new CANSparkMax(2, MotorType.kBrushless),
                rightRearSlave = new CANSparkMax(3, MotorType.kBrushless);
	
	// Power Distribution Panel
	public static PowerDistributionPanel pdp = new PowerDistributionPanel();

	// Buttons
	public static final int buttonSquare = 1;
	public static final int buttonX = 2;
	public static final int buttonCircle = 3;
	public static final int buttonTriangle = 4;
	public static final int leftBumper = 5;
	public static final int rightBumper = 6;
	public static final int leftTrigger = 7;
	public static final int rightTrigger = 8;
	public static final int buttonOption = 10;
	public static final int leftAnalog = 11;
	public static final int rightAnalog = 12;
	public static final int touchPad = 14;

	// Axes
	public static final int X_Axis = 0;
	public static final int Y_Axis = 1;
	public static final int Z_Axis = 2;
	public static final int Z_RotateAxis = 5;
	
	//Pov
}
