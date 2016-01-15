package org.usfirst.frc.team69.robot;

import org.usfirst.frc.team69.robot.oihelper.IOIHelper;
import org.usfirst.frc.team69.robot.oihelper.IOIHelper.IButtonHelper;
import org.usfirst.frc.team69.robot.oihelper.IOIHelper.IJoystickHelper;
import org.usfirst.frc.team69.robot.oihelper.IOIHelper.JoystickType;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public IJoystickHelper leftDriverJoystick;
	public IJoystickHelper rightDriverJoystick;
	public IJoystickHelper leftOperatorJoystick;
	public IJoystickHelper rightOperatorJoystick;
	
	public IButtonHelper stuffBtn;
	
	private IOIHelper helper;
	
	public OI(IOIHelper helper) {
		this.helper = helper;
	}
	
	public void init() {
		addButtons();
		addCommands();
	}
	
	public void addButtons() {
		leftDriverJoystick = helper.addJoystick(0, JoystickType.LOGITECH_3_AXIS, "Left Driver");
		rightDriverJoystick = helper.addJoystick(1, JoystickType.LOGITECH_2_AXIS, "Right Driver");
		leftOperatorJoystick = helper.addJoystick(2, JoystickType.LOGITECH_2_AXIS, "Left Operator");
		rightOperatorJoystick = helper.addJoystick(3, JoystickType.LOGITECH_2_AXIS, "Right Operator");
		
		stuffBtn = leftDriverJoystick.addButton(1, "Do stuff!");
	}
	
	public void addCommands() {
		
	}
    
}

