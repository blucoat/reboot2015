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
		public ButtonHelper[] buttons;
		
		public JoystickHelper(int port, JoystickType type, String name) {
			this.port = port;
			this.type = type;
			this.name = name;
			switch (type) {
			case LOGITECH_2_AXIS:
				buttons = new ButtonHelper[11];
				break;
			case LOGITECH_3_AXIS:
				buttons = new ButtonHelper[12];
				break;
			default:
				throw new UnsupportedOperationException("MockIOHelper does not support " + type.toString());
			}
		}

		@Override
		public Joystick get() {
			throw new UnsupportedOperationException("Tried to call get on a mock joystick");
		}

		@Override
		public IButtonHelper addButton(int number, String name) throws InvalidButtonException, DuplicateButtonException {
			if (number < 1 || number > buttons.length) {
				throw new InvalidButtonException(name, number, this);
			}
			if (buttons[number - 1] != null) {
				throw new DuplicateButtonException(buttons[number - 1].name, name, number, this);
			}
			
			ButtonHelper button = new ButtonHelper(name);
			buttons[number - 1] = button;
			return button;
		}
		
	}
	
	public class ButtonHelper implements IButtonHelper {
		public String name;
		
		public ButtonHelper(String name) {
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
