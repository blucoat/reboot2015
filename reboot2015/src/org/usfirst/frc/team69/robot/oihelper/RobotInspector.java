package org.usfirst.frc.team69.robot.oihelper;

import java.io.IOException;

import org.usfirst.frc.team69.robot.RobotMap;

public class RobotInspector {

	public static void main(String[] args) {
		try {
			System.out.println("Checking operator interface...");
			new JoystickMapper().mapJoysticks();
			System.out.println("OI successfully mapped.");
		} catch (DuplicateButtonException | InvalidButtonException e) {
			System.out.println("Button mapping error: " + e.getMessage());
			System.out.println("Check your OI file!");
		} catch (IOException e) {
			System.out.println("IOException occured while making joystick map: " + e.getMessage());
		}

		System.out.println();
		
		try {
			System.out.println("Checking RobotMap...");
			new PortMapper().mapPorts(RobotMap.class);
			System.out.println("Robot ports successfully mapped.");
		} catch (DuplicatePortException | InvalidPortException e) {
			System.out.println("Wiring error: " + e.getMessage());
			System.out.println("Check your RobotMap file!");
		} catch (IOException e) {
			System.out.println("IOException occured while making wiring diagram: " + e.getMessage());
		}
	}

	
}
