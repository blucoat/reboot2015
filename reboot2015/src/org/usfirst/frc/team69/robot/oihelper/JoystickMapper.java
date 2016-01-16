package org.usfirst.frc.team69.robot.oihelper;

import java.io.IOException;

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
		

		try {
			new PortMapper().mapPorts(RobotMap.class);
		} catch (DuplicatePortException e) {
			System.out.printf("ERROR: %s and %s are both assigned to %s #%d\n",
					e.getFirst(), e.getSecond(), e.getType(), e.getNumber());
		} catch (InvalidPortException e) {
			System.out.printf("ERROR: %s cannot be assigned to %s #%d because it is not a valid port\n",
					e.getName(), e.getType(), e.getNumber());
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException occured while making wiring diagram");
		}
	}
	
	public static class PortData {
		public String name;
		public int port;
		public PortData(String name, int port) {
			this.name = name;
			this.port = port;
		}
	}

}
