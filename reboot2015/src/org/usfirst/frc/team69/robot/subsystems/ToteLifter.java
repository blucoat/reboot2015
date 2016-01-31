package org.usfirst.frc.team69.robot.subsystems;

import org.usfirst.frc.team69.robot.RobotMap;
import org.usfirst.frc.team69.util.QuickCommand;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ToteLifter extends Subsystem {

	public SpeedController motor = new Talon(RobotMap.ToteLifter.MOTOR);
	public Encoder encoder = new Encoder(
			RobotMap.ToteLifter.ENCODER_A_DIO,
			RobotMap.ToteLifter.ENCODER_B_DIO);
	
	@Override
	protected void initDefaultCommand() {
		setDefaultCommand(stopCmd());
	}
	
	public Command stopCmd() {
		return QuickCommand.oneShot(this, () -> motor.set(0.0));
	}
	
	public Command moveUpCmd() {
		return QuickCommand.continuous(this, () -> motor.set(1.0));
	}
	
	public Command moveDownCmd() {
		return QuickCommand.continuous(this, () -> motor.set(-1.0));
	}

}
