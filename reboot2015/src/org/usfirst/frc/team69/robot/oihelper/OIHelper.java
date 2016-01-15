package org.usfirst.frc.team69.robot.oihelper;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OIHelper implements IOIHelper {

	private class JoystickHelper implements IJoystickHelper {
		private Joystick joystick;
		
		public JoystickHelper(Joystick joystick) {
			this.joystick = joystick;
		}
		
		@Override
		public Joystick get() {
			return joystick;
		}

		@Override
		public IButtonHelper addButton(int number, String name) {
			return new ButtonHelper(new JoystickButton(joystick, number));
		}
	}
	
	private class ButtonHelper implements IButtonHelper {
		private JoystickButton button;
		
		public ButtonHelper(JoystickButton button) {
			this.button = button;
		}
		
		@Override
		public JoystickButton get() {
			return button;
		}
		
	}
	
	@Override
	public IJoystickHelper addJoystick(int port, JoystickType type, String name) {
		return new JoystickHelper(new Joystick(port));
	}
}
