package org.usfirst.frc.team69.robot.subsystems;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team69.robot.RobotMap;
import org.usfirst.frc.team69.robot.driving.DriveControls;
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
	
	private RobotDrive drive = new RobotDrive(leftFront, leftRear, rightFront, rightRear);
	
	public void initDefaultCommand() {
		setDefaultCommand(DriveControls.userTankCmd());
	}
	
	public Command tankCmd(double left, double right) {
		return tankCmd(() -> left, () -> right);
	}
	
	public Command tankCmd(DoubleSupplier left, DoubleSupplier right) {
		return QuickCommand.continuous(this, () -> drive.tankDrive(left.getAsDouble(), right.getAsDouble()));
	}
	
	public Command arcadeCmd(double move, double rotate) {
		return arcadeCmd(() -> move, () -> rotate);
	}
	
	public Command arcadeCmd(DoubleSupplier move, DoubleSupplier rotate) {
		return QuickCommand.continuous(this, () -> drive.arcadeDrive(move.getAsDouble(), rotate.getAsDouble()));
	}
	
	public Command mecanumCmd(double x, double y, double rotation, double gyro) {
		return mecanumCmd(() -> x, () -> y, () -> rotation, () -> gyro);
	}
	
	public Command mecanumCmd(DoubleSupplier x, DoubleSupplier y, DoubleSupplier rotation, DoubleSupplier gyro) {
		return QuickCommand.continuous(this, () -> drive.mecanumDrive_Cartesian(
				x.getAsDouble(), y.getAsDouble(), rotation.getAsDouble(), gyro.getAsDouble()));
	}
}
