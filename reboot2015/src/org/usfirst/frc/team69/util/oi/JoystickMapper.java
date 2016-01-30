package org.usfirst.frc.team69.util.oi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.usfirst.frc.team69.robot.OI;
import org.usfirst.frc.team69.util.oi.MockOIHelper.ButtonHelper;
import org.usfirst.frc.team69.util.oi.MockOIHelper.JoystickHelper;

public class JoystickMapper {

	MockOIHelper helper;
	
	public void mapJoysticks() throws InvalidButtonException, DuplicateButtonException, IOException {
		helper = new MockOIHelper();
		
		OI.setHelper(helper);
		new OI();
		
		helper.verify();
		
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
			break;//god damnit, james!
		case LOGITECH_3_AXIS:
			img = draw3Axis(joystick);
			break;//god damnit, james!
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
		BufferedImage img = ImageIO.read(getClass().getResource("sc_mapping_helper_2_axis.jpg"));
		Graphics g = img.createGraphics();
		g.setColor(Color.BLACK);
		
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		drawInfo(g, js);
		
		int[] x = {300, 280, 297, 136, 449,  22,  10, 166, 357, 520, 520};
		int[] y = {44, 208, 125, 125, 125, 412, 510, 643, 643, 375, 454};
		
		for (ButtonHelper b : js.buttons) {
			if (b.number == 1) {
				g.setFont(new Font("Arial", Font.PLAIN, 12));
			} else {
				g.setFont(new Font("Arial", Font.PLAIN, 16));
			}
			
			g.drawString(b.name, x[b.number - 1], y[b.number - 1]);
		}
		
		return img;
	}
	
	private BufferedImage draw3Axis(JoystickHelper js) throws IOException {
		BufferedImage img = ImageIO.read(getClass().getResource("sc_mapping_helper.jpg"));
		Graphics g = img.createGraphics();
		g.setColor(Color.BLACK);
		
		g.setFont(new Font("Arial", Font.PLAIN, 16));
		drawInfo(g, js);
		
		int[] x = {634, 206, 450, 810, 419, 826,  28, 206,  28, 206,  28, 206};
		int[] y = {354, 436, 296, 296, 223, 223, 562, 562, 635, 635, 708, 708};
		
		for (ButtonHelper b : js.buttons) {
			if (b.number == 1) {
				g.setFont(new Font("Arial", Font.PLAIN, 12));
			} else {
				g.setFont(new Font("Arial", Font.PLAIN, 16));
			}
			
			g.drawString(b.name, x[b.number - 1], y[b.number - 1]);
		}
		
		return img;
	}
}
