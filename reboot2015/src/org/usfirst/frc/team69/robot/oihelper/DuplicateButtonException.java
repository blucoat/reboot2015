package org.usfirst.frc.team69.robot.oihelper;

import org.usfirst.frc.team69.robot.oihelper.MockOIHelper.JoystickHelper;

@SuppressWarnings("serial")
public class DuplicateButtonException extends Exception {
	public String name1;
	public String name2;
	public int number;
	public JoystickHelper js;
	
	public DuplicateButtonException(String name1, String name2, int number, JoystickHelper js) {
		this.name1 = name1;
		this.name2 = name2;
		this.number = number;
		this.js = js;
	}
	
	@Override
	public String getMessage() {
		return String.format("\"%s\" and \"%s\" are both assigned to button #%d on joystick #%d (%s)",
				name1, name2, number, js.port, js.name);
	}
	
}
