package org.usfirst.frc.team69.util.oi;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The {@link MockOIHelper} class will mock OIHelper, keeping track of all
 * buttons and joysticks added.
 * 
 * @author James Hagborg
 *
 */
public class MockOIHelper implements IOIHelper {

	/**
	 * An ArrayList containing all joysticks in the OI.
	 */
	public ArrayList<JoystickHelper> joysticks = new ArrayList<JoystickHelper>();

	/**
	 * A class to keep track of joysticks created.
	 * 
	 * @author James Hagborg
	 *
	 */
	public class JoystickHelper implements IJoystick {
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
		public IButton addButton(int number, String name) {
			ButtonHelper button = new ButtonHelper(name, number);
			buttons.add(button);
			return button;
		}
		
		/**
		 * Check that there are no duplicate or invalid buttons
		 * @throws InvalidButtonException
		 * @throws DuplicateButtonException
		 */
		public void verify() throws InvalidButtonException, DuplicateButtonException {
			ButtonHelper[] arr = new ButtonHelper[type.getNumButtons()];
			
			for (ButtonHelper b : buttons) {
				if (b.number < 1 || b.number > arr.length) {
					throw new InvalidButtonException(b, this);
				}
				if (arr[b.number - 1] != null) {
					throw new DuplicateButtonException(arr[b.number - 1], b, this);
				}
				
				arr[b.number - 1] = b;
			}
		}
	}
	
	/**
	 * A class to keep track of buttons on the OI.
	 * 
	 * @author James Hagborg
	 *
	 */
	public class ButtonHelper implements IButton {
		public String name;
		public int number;
		
		public ButtonHelper(String name, int number) {
			this.name = name;
			this.number = number;
		}
		
		@Override
		public JoystickButton get() {
			throw new UnsupportedOperationException("Tried to call get on a mock button");
		}
		
	}

	@Override
	public IJoystick addJoystick(int port, JoystickType type, String name) {
		JoystickHelper joystick = new JoystickHelper(port, type, name);
		joysticks.add(joystick);
		return joystick;
	}

	/**
	 * Check that there are no duplicate or invalid buttons.
	 * 
	 * @throws DuplicateButtonException
	 * @throws InvalidButtonException
	 */
	public void verify() throws DuplicateButtonException, InvalidButtonException {
		for (JoystickHelper js : joysticks) {
			js.verify();
		}
	}
}
