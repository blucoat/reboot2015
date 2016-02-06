package org.usfirst.frc.team69.robot.subsystems;

import org.usfirst.frc.team69.robot.RobotMap;
import org.usfirst.frc.team69.util.QuickCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ContainerGrabber extends Subsystem {

	private DoubleSolenoid solenoid = new DoubleSolenoid(
			RobotMap.ContainerGrabber.SOLENOID_FORWARD,
			RobotMap.ContainerGrabber.SOLENOID_REVERSE);
	
	@Override
	protected void initDefaultCommand() {
		// No default command
	}
	
	public boolean isExtended() {
		return solenoid.get() == Value.kForward;
	}
	
	public Command setExtendedCmd(boolean extend) {
		return QuickCommand.oneShot(this, () -> solenoid.set(extend ? Value.kForward : Value.kReverse));
	}

}
