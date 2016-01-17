package org.usfirst.frc.team69.robot;

import org.usfirst.frc.team69.robot.oihelper.DuplicateButtonException;
import org.usfirst.frc.team69.robot.oihelper.IOIHelper;
import org.usfirst.frc.team69.robot.oihelper.IOIHelper.IButtonHelper;
import org.usfirst.frc.team69.robot.oihelper.IOIHelper.IJoystickHelper;
import org.usfirst.frc.team69.robot.oihelper.IOIHelper.JoystickType;

import edu.wpi.first.wpilibj.DriverStation;

import org.usfirst.frc.team69.robot.oihelper.InvalidButtonException;
import org.usfirst.frc.team69.robot.oihelper.QuickCommand;

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
		try {
			addButtons();
		} catch (InvalidButtonException | DuplicateButtonException e) {
			// nothing should happen here.  the exceptions are only thrown when run from the driver station,
			// not on the robot. just in case:
			DriverStation.reportError("ERROR: " + e.getMessage(), false);
		}
		addCommands();
	}
	
	public void addButtons() throws InvalidButtonException, DuplicateButtonException {
		leftDriverJoystick = helper.addJoystick(0, JoystickType.LOGITECH_3_AXIS, "Left Driver");
		rightDriverJoystick = helper.addJoystick(1, JoystickType.LOGITECH_3_AXIS, "Right Driver");
		leftOperatorJoystick = helper.addJoystick(2, JoystickType.LOGITECH_3_AXIS, "Left Operator");
		rightOperatorJoystick = helper.addJoystick(3, JoystickType.LOGITECH_3_AXIS, "Right Operator");
		
		stuffBtn = leftDriverJoystick.addButton(12, "Do stuff!");
	}
	
	public void addCommands() {
		stuffBtn.get().whenPressed(QuickCommand.oneShot(Robot.containerGrabber, () -> Robot.containerGrabber.solenoid.set(true)));
	}
    
}

