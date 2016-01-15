package org.usfirst.frc.team69.robot.oihelper;

import java.lang.reflect.Field;

import org.usfirst.frc.team69.robot.OI;
import org.usfirst.frc.team69.robot.RobotMap;
import org.usfirst.frc.team69.robot.oihelper.MockOIHelper.ButtonHelper;
import org.usfirst.frc.team69.robot.oihelper.MockOIHelper.JoystickHelper;

public class JoystickMapper {

	public static void main(String[] args) {
		MockOIHelper helper = new MockOIHelper();
		OI oi = new OI(helper);
		oi.addButtons();
		for (JoystickHelper joystick : helper.joysticks) {
			System.out.printf("Found \"%s\" at port %d\n", joystick.name, joystick.port);
			for (ButtonHelper button : joystick.buttons) {
				System.out.printf("Button %d: %s\n", button.number, button.name);
			}
			System.out.println();
		}
		
		findPorts(RobotMap.class, "");
	}
	
	public static class PortData {
		public String name;
		public int port;
		public PortData(String name, int port) {
			this.name = name;
			this.port = port;
		}
	}
	
	public static void findPorts(Class<?> c, String name) {
		for (Field f : c.getFields()) {
			try {
				Port p = f.getAnnotation(Port.class);
				if (p == null) {
					continue;
				}
				int i = f.getInt(null);
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Double check your RobotMap file!");
				System.out.println("If you can't fix it email the backtrace "
						+ "along with the current RobotMap.java to James");
			}
		}
		
		for (Class<?> sub : c.getClasses()) {
			findPorts(sub, name);
		}
	}
}
