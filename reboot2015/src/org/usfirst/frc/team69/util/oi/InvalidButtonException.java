package org.usfirst.frc.team69.util.oi;

import org.usfirst.frc.team69.util.oi.MockOIHelper.JoystickHelper;

@SuppressWarnings("serial")
public class InvalidButtonException extends Exception {
	public String name;
	public int number;
	public JoystickHelper js;
	
	public InvalidButtonException(String name, int number, JoystickHelper js) {
		this.name = name;
		this.number = number;
		this.js = js;
	}
	
	@Override
	public String getMessage() {
		return String.format("Cannot assign button #%d on joystick #%d (%s) to \"%s\" because it is not a valid button.",
				number, js.port, js.name, name);
	}
}
