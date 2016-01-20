package org.usfirst.frc.team69.robot.oihelper;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.usfirst.frc.team69.robot.OI;
import org.usfirst.frc.team69.robot.oihelper.MockOIHelper.JoystickHelper;

public class JoystickMapper {

	MockOIHelper helper;
	
	public void mapJoysticks() throws InvalidButtonException, DuplicateButtonException, IOException {
		helper = new MockOIHelper();
		OI oi = new OI(helper);
		oi.addButtons();
		drawMap();
	}
	
	private void drawMap() throws IOException {
		for (JoystickHelper js : helper.joysticks) {
			drawSubMap(js);
		}
	}
	
	private void drawSubMap(JoystickHelper joystick) throws IOException {
		BufferedImage img = null;
		
		switch (joystick.type) {
		case LOGITECH_2_AXIS:
			img = draw2Axis(joystick);
		case LOGITECH_3_AXIS:
			img = draw3Axis(joystick);
		}
		
		// img is null if new enum types are added
		if (img != null) {
			new File("diagrams").mkdirs();
			String filename = String.format("diagrams/joystick%d.png", joystick.port);
			ImageIO.write(img, "png", new File(filename));
		}
	}
	
	private void drawInfo(Graphics g, JoystickHelper js) {
		String s = String.format("Joystick #%d: %s", js.port, js.name);
		final int w = g.getFontMetrics().stringWidth(s);
		g.drawString(s, 50, 50);
		g.drawRect(45, 30, w + 10, 25);
		
	}
	
	private BufferedImage draw2Axis(JoystickHelper js) throws IOException {
		BufferedImage img = ImageIO.read(RobotInspector.class.getResource("/org/usfirst/frc/team69/robot/oihelper/sc_mapping_helper.jpg"));
		Graphics g = img.createGraphics();
		g.setColor(Color.BLACK);
		
		
		throw new UnsupportedOperationException("TODO 2 axis image");
	}
	
	private BufferedImage draw3Axis(JoystickHelper js) throws IOException {
		BufferedImage img = ImageIO.read(RobotInspector.class.getResource("/org/usfirst/frc/team69/robot/oihelper/sc_mapping_helper.jpg"));
		Graphics g = img.createGraphics();
		g.setColor(Color.BLACK);
		
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		drawInfo(g, js);
		
		int[] x = {634, 206, 450, 810, 419, 826, 28, 206, 28, 206, 28, 206};
		int[] y = {354, 436, 296, 296, 223, 223, 562, 562, 635, 635, 708, 708};
		
		for (int i = 0; i < 12; i++) {
			if (i == 0) {
				g.setFont(new Font("Arial", Font.PLAIN, 12));
			} else {
				g.setFont(new Font("Arial", Font.PLAIN, 16));
			}
			if (js.buttons[i] != null) {
				g.drawString(js.buttons[i].name, x[i], y[i]);
			}
		}
		
		return img;
	}
}
