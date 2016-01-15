package org.usfirst.frc.team69.robot.oihelper;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class MockOIHelper implements IOIHelper {

	public ArrayList<JoystickHelper> joysticks = new ArrayList<JoystickHelper>();

	public class JoystickHelper implements IJoystickHelper {
		public int port;
		public JoystickType type;
		public String name;
		public ArrayList<ButtonHelper> buttons = new ArrayList<ButtonHelper>();
		
		public JoystickHelper(int port, JoystickType type, String name) {
			this.port = port;
			this.type = type;
			this.name = name;
		}

		@Override
		public Joystick get() {
			throw new UnsupportedOperationException("Tried to call get on a mock joystick");
		}

		@Override
		public IButtonHelper addButton(int number, String name) {
			ButtonHelper button = new ButtonHelper(number, name);
			buttons.add(button);
			return button;
		}
		
	}
	
	public class ButtonHelper implements IButtonHelper {
		public int number;
		public String name;
		
		public ButtonHelper(int number, String name) {
			this.number = number;
			this.name = name;
		}
		
		@Override
		public JoystickButton get() {
			throw new UnsupportedOperationException("Tried to call get on a mock button");
		}
		
	}

	@Override
	public IJoystickHelper addJoystick(int port, JoystickType type, String name) {
		JoystickHelper joystick = new JoystickHelper(port, type, name);
		joysticks.add(joystick);
		return joystick;
	}
}
