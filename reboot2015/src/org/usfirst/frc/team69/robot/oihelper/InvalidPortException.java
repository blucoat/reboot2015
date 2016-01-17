package org.usfirst.frc.team69.robot.oihelper;

@SuppressWarnings("serial")
public class InvalidPortException extends Exception {
	private int number;
	private Port.Type type;
	private String name;

	public InvalidPortException(int number, Port.Type type, String name) {
		this.number = number;
		this.type = type;
		this.name = name;
	}
	
	public int getNumber() { return number; }
	public Port.Type getType() { return type; }
	public String getName() { return name; }
	
	public String getMessage() {
		return String.format("%s cannot be assigned to %s #%d because it is not a valid port",
				name, type, number);
	}
}
