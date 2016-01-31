package org.usfirst.frc.team69.util.oi;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The {@link OIHelper} class creates joysticks and buttons that wrap the
 * WPILib equivalents, {@link Joystick} and {@link JoystickButton}.
 * 
 * @author James Hagborg
 *
 */
public class OIHelper implements IOIHelper {

	private class JoystickHelper implements IJoystick {
		private Joystick joystick;
		
		public JoystickHelper(Joystick joystick) {
			this.joystick = joystick;
		}
		
		@Override
		public Joystick get() {
			return joystick;
		}

		@Override
		public IButton addButton(int number, String name) {
			return new ButtonHelper(new JoystickButton(joystick, number));
		}
	}
	
	private class ButtonHelper implements IButton {
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
	public IJoystick addJoystick(int port, JoystickType type, String name) {
		return new JoystickHelper(new Joystick(port));
	}
}
