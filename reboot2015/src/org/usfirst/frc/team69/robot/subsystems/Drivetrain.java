package org.usfirst.frc.team69.robot.subsystems;

import org.usfirst.frc.team69.robot.Robot;
import org.usfirst.frc.team69.robot.RobotMap;
import org.usfirst.frc.team69.util.QuickCommand;

import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Drivetrain extends Subsystem {
	
	private SpeedController leftFront = new Talon(RobotMap.Drivetrain.LEFT_FRONT_MOTOR);
	private SpeedController leftRear = new Talon(RobotMap.Drivetrain.LEFT_REAR_MOTOR);
	private SpeedController rightFront = new Talon(RobotMap.Drivetrain.RIGHT_FRONT_MOTOR);
	private SpeedController rightRear = new Talon(RobotMap.Drivetrain.RIGHT_REAR_MOTOR);
	
	public RobotDrive drive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);
	
	public void initDefaultCommand() {
		setDefaultCommand(userTankCmd());
	}
	
	public Command userTankCmd() {
		return QuickCommand.continuous(this, () -> drive.tankDrive(
						Robot.oi.leftDriverJoystick.get(),
						Robot.oi.rightDriverJoystick.get()));
	}
}
