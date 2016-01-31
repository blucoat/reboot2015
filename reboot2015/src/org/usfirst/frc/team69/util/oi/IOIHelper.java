package org.usfirst.frc.team69.util.oi;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * The {@link IOIHelper} interface provides methods for creating joysticks and
 * buttons.  This allows the same code to both create wpilib joysticks and buttons,
 * as well as create a diagram when run from {@link RobotInspector}.
 * 
 * On the robot, this will be a {@link OIHelper}, and when generating diagrams, this
 * will be a {@link MockOIHelper}.
 * 
 * @author James Hagborg
 *
 */
public interface IOIHelper {

	/**
	 * The type of joystick.  For new joysticks, add to this enum.
	 * 
	 * @author James Hagborg
	 *
	 */
	public enum JoystickType {
		LOGITECH_2_AXIS,
		LOGITECH_3_AXIS;
		
		private int buttons;
		
		static {
			LOGITECH_2_AXIS.buttons = 11;
			LOGITECH_3_AXIS.buttons = 12;
		}
		
		/**
		 * Get the number of buttons on the joystick.
		 * @return The number of buttons on the joystick
		 */
		public int getNumButtons() {
			return buttons;
		}
	}
	
	/**
	 * An interface representing a joystick, either a "mock" one for RobotInspector,
	 * or a "real" WPILib one.
	 * 
	 * @author James Hagborg
	 *
	 */
	public interface IJoystick {
		/**
		 * Get the underlying WPILib {@link Joystick}.
		 * @return The underlying joystick
		 * @throws UnsupportedOperationException if the underlying joystick is
		 * a mock joystick for RobotInspector
		 */
		public Joystick get();
		
		/**
		 * Add a button to the joystick
		 * @param number The number, starting from 1, of the button
		 * @param name A name for the button, to be displayed on the joystick map
		 * @return An {@link IButton} representing the button added
		 */
		public IButton addButton(int number, String name);
	}
	
	/**
	 * An interface representing a button, either a "mock" one for RobotInspector,
	 * or a "real" WPILib one.
	 * 
	 * @author James Hagborg
	 *
	 */
	public interface IButton {
		/**
		 * Get the underlying WPILib {@link JoystickButton}.
		 * @return the underlying JoystickButton object
		 */
		public JoystickButton get();
	}
	
	/**
	 * Add a joystick to the operator interface.
	 * @param port The port number, starting at 0, where the joystick is connected
	 * @param type The type of joystick
	 * @param name A name to display on the diagram generated
	 * @return An {@link IJoystick} representing the joystick added
	 */
	public IJoystick addJoystick(int port, JoystickType type, String name);
}
