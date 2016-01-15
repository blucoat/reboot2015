package org.usfirst.frc.team69.robot.subsystems;

import org.usfirst.frc.team69.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ContainerGrabber extends Subsystem {

	public Solenoid solenoid = new Solenoid(
			RobotMap.ContainerGrabber.SOLENOID_FORWARD,
			RobotMap.ContainerGrabber.SOLENOID_REVERSE);
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

}
