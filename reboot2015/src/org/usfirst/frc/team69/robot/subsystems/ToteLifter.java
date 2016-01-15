package org.usfirst.frc.team69.robot.subsystems;

import org.usfirst.frc.team69.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ToteLifter extends Subsystem {

	public SpeedController motor = new Talon(RobotMap.ContainerLifter.Elevator.MOTOR);
	public Encoder encoder = new Encoder(
			RobotMap.ToteLifter.ENCODER_A_DIO,
			RobotMap.ToteLifter.ENCODER_B_DIO);
	
	@Override
	protected void initDefaultCommand() {
		
	}

}
