package org.usfirst.frc.team69.robot;

import org.usfirst.frc.team69.robot.oihelper.DuplicateButtonException;
import org.usfirst.frc.team69.robot.oihelper.IOIHelper;
import org.usfirst.frc.team69.robot.oihelper.IOIHelper.IButtonHelper;
import org.usfirst.frc.team69.robot.oihelper.IOIHelper.IJoystickHelper;
import org.usfirst.frc.team69.robot.oihelper.IOIHelper.JoystickType;
import org.usfirst.frc.team69.robot.oihelper.InvalidButtonException;

import edu.wpi.first.wpilibj.DriverStation;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	public IJoystickHelper leftDriverJoystick;
	public IJoystickHelper rightDriverJoystick;
	public IJoystickHelper leftOperatorJoystick;
	public IJoystickHelper rightOperatorJoystick;
	
	// right driver
	public IButtonHelper grabberInBtn;
	public IButtonHelper grabberOutBtn;
	
	// left operator
	public IButtonHelper toteLifterUpBtn;
	public IButtonHelper toteLifterDownBtn;
	
	// right operator
	public IButtonHelper containerTiltInBtn;
	public IButtonHelper containerTiltOutBtn;
	public IButtonHelper vacuumOnBtn;
	public IButtonHelper vacuumOffBtn;
	public IButtonHelper containerLifterManualBtn;
	
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
		
		
		grabberInBtn = rightDriverJoystick.addButton(7, "Retract container grabber");
		grabberOutBtn = rightDriverJoystick.addButton(6, "Extend container grabber");
		
		toteLifterUpBtn = leftOperatorJoystick.addButton(6, "Tote lifter up");
		toteLifterDownBtn = leftOperatorJoystick.addButton(7, "Tote lifter down");
		
		containerTiltInBtn = rightOperatorJoystick.addButton(8, "Container tilt in");
		containerTiltOutBtn = rightOperatorJoystick.addButton(9, "Container tilt out");
		vacuumOnBtn = rightOperatorJoystick.addButton(2, "Vacuum on");
		vacuumOffBtn = rightOperatorJoystick.addButton(3, "Vacuum off");
		containerLifterManualBtn = rightOperatorJoystick.addButton(1, "Container manual control");
	}
	
	public void addCommands() {
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

