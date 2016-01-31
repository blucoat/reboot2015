package org.usfirst.frc.team69.robot;

import org.usfirst.frc.team69.util.oi.IOIHelper;
import org.usfirst.frc.team69.util.oi.IOIHelper.IButton;
import org.usfirst.frc.team69.util.oi.IOIHelper.IJoystick;
import org.usfirst.frc.team69.util.oi.IOIHelper.JoystickType;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	private static IOIHelper helper;
	
	public static void setHelper(IOIHelper helper) {
		OI.helper = helper;
	}
	
	public static IOIHelper getHelper() {
		return helper;
	}
	
	public IJoystick leftDriverJoystick = helper.addJoystick(0, JoystickType.LOGITECH_2_AXIS, "Left Driver");
	public IJoystick rightDriverJoystick = helper.addJoystick(1, JoystickType.LOGITECH_3_AXIS, "Right Driver");
	public IJoystick leftOperatorJoystick = helper.addJoystick(2, JoystickType.LOGITECH_2_AXIS, "Left Operator");
	public IJoystick rightOperatorJoystick = helper.addJoystick(3, JoystickType.LOGITECH_2_AXIS, "Right Operator");
	
	public IButton grabberInBtn = rightDriverJoystick.addButton(7, "Retract grabber");
	public IButton grabberOutBtn = rightDriverJoystick.addButton(6, "Extend grabber");
	
	public IButton toteLifterUpBtn = leftOperatorJoystick.addButton(6, "Tote lifter up");
	public IButton toteLifterDownBtn = leftOperatorJoystick.addButton(7, "Tote lifter down");
	
	public IButton containerTiltInBtn = rightOperatorJoystick.addButton(8, "Container tilt in");
	public IButton containerTiltOutBtn = rightOperatorJoystick.addButton(9, "Container tilt out");
	public IButton vacuumOnBtn = rightOperatorJoystick.addButton(2, "Vacuum on");
	public IButton vacuumOffBtn = rightOperatorJoystick.addButton(3, "Vacuum off");
	public IButton containerLifterManualBtn = rightOperatorJoystick.addButton(1, "Container manual");
	
	public void init() {
		addCommands();
	}
	
	private void addCommands() {
		grabberInBtn.get().whenPressed(Robot.containerGrabber.setArmsOutCmd(false));
		grabberOutBtn.get().whenPressed(Robot.containerGrabber.setArmsOutCmd(true));
		
		toteLifterUpBtn.get().whileHeld(Robot.toteLifter.moveUpCmd());
		toteLifterDownBtn.get().whileHeld(Robot.toteLifter.moveDownCmd());
		
		containerTiltInBtn.get().whenPressed(Robot.containerLifter.tilt.setTiltedCmd(false));
		containerTiltOutBtn.get().whenPressed(Robot.containerLifter.tilt.setTiltedCmd(true));
		vacuumOnBtn.get().whenPressed(Robot.containerLifter.vacuum.autoVacuumCmd());
		vacuumOffBtn.get().whenPressed(Robot.containerLifter.vacuum.releaseCmd());
		containerLifterManualBtn.get().whileHeld(Robot.containerLifter.elevator.userControlCmd(rightOperatorJoystick.get()));
	}
    
}

