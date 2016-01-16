package org.usfirst.frc.team69.robot.oihelper;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

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
		mapJoystick();

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

	private static void mapJoystick() {
		try {
			BufferedImage img = ImageIO.read(JoystickMapper.class.getResource("/org/usfirst/frc/team69/robot/oihelper/sc_mapping_helper.jpg"));
			Graphics g = img.createGraphics();
			g.setColor(Color.BLACK);
			g.drawString("Do stuff", 634, 354);
			ImageIO.write(img, "png", new File("diagrams/joystick.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
