package org.usfirst.frc.team69.util.oi;

import org.usfirst.frc.team69.util.oi.MockOIHelper.ButtonHelper;
import org.usfirst.frc.team69.util.oi.MockOIHelper.JoystickHelper;

@SuppressWarnings("serial")
public class DuplicateButtonException extends Exception {
	private ButtonHelper b1, b2;
	public JoystickHelper js;
	
	public DuplicateButtonException(ButtonHelper b1, ButtonHelper b2, JoystickHelper js) {
		this.b1 = b1;
		this.b2 = b2;
		this.js = js;
	}
	
	@Override
	public String getMessage() {
		return String.format("\"%s\" and \"%s\" are both assigned to button #%d on joystick #%d (%s)",
				b1.name, b2.name, b1.number, js.port, js.name);
	}
	
}
