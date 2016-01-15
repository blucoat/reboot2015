package org.usfirst.frc.team69.robot.subsystems;

import org.usfirst.frc.team69.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * 
 */
public class ContainerLifter {
	
	public Vacuum vacuum = new Vacuum();
	public Tilt tilt = new Tilt();
	public Elevator elevator = new Elevator();
	
	public static class Vacuum extends Subsystem {
		public Relay indicator = new Relay(RobotMap.ContainerLifter.Vacuum.INDICATOR_RELAY);
		public DigitalInput sensor = new DigitalInput(RobotMap.ContainerLifter.Vacuum.SENSOR_DIO);
		public Solenoid seal = new Solenoid(RobotMap.ContainerLifter.Vacuum.SEAL_SOLENOID);
		public Solenoid venturi = new Solenoid(RobotMap.ContainerLifter.Vacuum.VENTURI_SOLENOID);

		@Override
		public void initDefaultCommand() {
			// Set the default command for a subsystem here.
			//setDefaultCommand(new MySpecialCommand());
		}
	}
	
	public static class Tilt extends Subsystem {
		public DoubleSolenoid solenoid = new DoubleSolenoid(
				RobotMap.ContainerLifter.Tilt.SOLENOID_FORWARD,
				RobotMap.ContainerLifter.Tilt.SOLENOID_REVERSE);

		@Override
		protected void initDefaultCommand() {
			// TODO Auto-generated method stub
		}
	}
	
	public static class Elevator extends Subsystem {

		public SpeedController motor = new Talon(RobotMap.ContainerLifter.Elevator.MOTOR);
		public Encoder encoder = new Encoder(
				RobotMap.ContainerLifter.Elevator.ENCODER_A_DIO,
				RobotMap.ContainerLifter.Elevator.ENCODER_B_DIO);
		
		@Override
		protected void initDefaultCommand() {
			// TODO Auto-generated method stub
			
		}
		
	}
}

