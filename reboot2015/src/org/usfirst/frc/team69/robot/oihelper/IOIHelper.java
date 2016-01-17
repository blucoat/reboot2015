package org.usfirst.frc.team69.robot.oihelper;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public interface IOIHelper {

	public enum JoystickType {
		LOGITECH_2_AXIS,
		LOGITECH_3_AXIS
	}
	
	public interface IJoystickHelper {
		public Joystick get();
		public IButtonHelper addButton(int number, String name) throws InvalidButtonException, DuplicateButtonException;
	}
	
	public interface IButtonHelper {
		public JoystickButton get();
	}
	
	public IJoystickHelper addJoystick(int port, JoystickType type, String name);
}
