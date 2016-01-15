package org.usfirst.frc.team69.robot;

import org.usfirst.frc.team69.robot.oihelper.Port;
import org.usfirst.frc.team69.robot.oihelper.Port.Type;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	public static class Drivetrain {
		@Port(type = Type.PWM) public static final int LEFT_FRONT_MOTOR = 0;
		@Port(type = Type.PWM) public static final int LEFT_REAR_MOTOR = 2;
		@Port(type = Type.PWM) public static final int RIGHT_FRONT_MOTOR = 1;
		@Port(type = Type.PWM) public static final int RIGHT_REAR_MOTOR = 3;
	}
	
	public static class ContainerLifter {
		public static class Vacuum {
			@Port(type = Type.RELAY) public static final int INDICATOR_RELAY = 0;
			@Port(type = Type.DIO) public static final int SENSOR_DIO = 0;
			@Port(type = Type.PCM) public static final int SEAL_SOLENOID = 7;
			@Port(type = Type.PCM) public static final int VENTURI_SOLENOID = 6;
		}
		
		public static class Tilt {
			@Port(type = Type.PCM) public static final int SOLENOID_FORWARD = 3;
			@Port(type = Type.PCM) public static final int SOLENOID_REVERSE = 2;
			
		}
		
		public static class Elevator {
			@Port(type = Type.PWM) public static final int MOTOR = 4;
			@Port(type = Type.DIO) public static final int ENCODER_A_DIO = 2;
			@Port(type = Type.DIO) public static final int ENCODER_B_DIO = 3;
		}
		
	}
	
	public static class ToteLifter {
		@Port(type = Type.PWM) public static final int MOTOR = 5;
		@Port(type = Type.DIO) public static final int ENCODER_A_DIO = 4;
		@Port(type = Type.DIO) public static final int ENCODER_B_DIO = 5;
	}
	
	public static class ContainerGrabber {
		@Port(type = Type.PCM) public static final int SOLENOID_FORWARD = 4;
		@Port(type = Type.PCM) public static final int SOLENOID_REVERSE = 5;
	}
}
