package org.usfirst.frc.team69.robot.subsystems;

import org.usfirst.frc.team69.robot.RobotMap;
import org.usfirst.frc.team69.util.QuickCommand;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class ContainerGrabber extends Subsystem {

	public DoubleSolenoid solenoid = new DoubleSolenoid(
			RobotMap.ContainerGrabber.SOLENOID_FORWARD,
			RobotMap.ContainerGrabber.SOLENOID_REVERSE);
	
	public ContainerGrabber() {
		super("Container grabber");
		LiveWindow.addActuator(getName(), "Solenoid", solenoid);
	}
	
	@Override
	protected void initDefaultCommand() {
		// No default command
	}
	
	public Command setArmsOutCmd(boolean extend) {
		return QuickCommand.oneShot(this, () -> solenoid.set(extend ? Value.kForward : Value.kReverse));
	}

}
