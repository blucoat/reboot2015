package org.usfirst.frc.team69.util.oi;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public interface IOIHelper {

	public enum JoystickType {
		LOGITECH_2_AXIS,
		LOGITECH_3_AXIS;
		
		private int buttons;
		
		static {
			LOGITECH_2_AXIS.buttons = 11;
			LOGITECH_3_AXIS.buttons = 12;
		}
		
		public int getNumButtons() {
			return buttons;
		}
	}
	
	public interface IJoystick {
		public Joystick get();
		public IButton addButton(int number, String name);
	}
	
	public interface IButton {
		public JoystickButton get();
	}
	
	public IJoystick addJoystick(int port, JoystickType type, String name);
}
