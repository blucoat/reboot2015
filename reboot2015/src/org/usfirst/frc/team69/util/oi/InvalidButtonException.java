package org.usfirst.frc.team69.util.oi;

import org.usfirst.frc.team69.util.oi.MockOIHelper.ButtonHelper;
import org.usfirst.frc.team69.util.oi.MockOIHelper.JoystickHelper;

/**
 * An exception thrown when you attempt to create a button that does not exist
 * on the specified type of joystick.
 * 
 * @author James Hagborg
 *
 */
@SuppressWarnings("serial")
public class InvalidButtonException extends Exception {
	private ButtonHelper b;
	private JoystickHelper js;
	
	public InvalidButtonException(ButtonHelper b, JoystickHelper js) {
		this.b = b;
		this.js = js;
	}
	
	@Override
	public String getMessage() {
		return String.format("Cannot assign button #%d on joystick #%d (%s) to \"%s\" because it is not a valid button.",
				b.number, js.port, js.name, b.name);
	}
}
