package org.usfirst.frc.team69.robot.subsystems;

import org.usfirst.frc.team69.robot.RobotMap;
import org.usfirst.frc.team69.util.QuickCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
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
		public Solenoid release = new Solenoid(RobotMap.ContainerLifter.Vacuum.SEAL_SOLENOID);
		public Solenoid venturi = new Solenoid(RobotMap.ContainerLifter.Vacuum.VENTURI_SOLENOID);

		@Override
		public void initDefaultCommand() {
			setDefaultCommand(releaseCmd());
		}
		
		public Command autoVacuumCmd() {
			return QuickCommand.continuous(this, () -> {
				release.set(false);
				venturi.set(sensor.get());
				indicator.set(sensor.get() ? Relay.Value.kOff : Relay.Value.kForward); 
			});
		}
		
		public Command releaseCmd() {
			return QuickCommand.oneShot(this, () -> {
				release.set(true);
				venturi.set(false);
				indicator.set(Relay.Value.kOff);
			});
		}
	}
	
	public static class Tilt extends Subsystem {
		public DoubleSolenoid solenoid = new DoubleSolenoid(
				RobotMap.ContainerLifter.Tilt.SOLENOID_FORWARD,
				RobotMap.ContainerLifter.Tilt.SOLENOID_REVERSE);

		@Override
		protected void initDefaultCommand() {
			// no default command
		}
		
		public Command setTiltedCmd(boolean tilt) {
			return QuickCommand.oneShot(this, () -> solenoid.set(tilt ? Value.kForward : Value.kReverse));
		}
	}
	
	// This should be using PID, but I'm making it equivalent in functionality to the 2015 code, which used manual control
	// Implementing PID is left as an exercise to the user :^)
	public static class Elevator extends Subsystem {

		public SpeedController motor = new Talon(RobotMap.ContainerLifter.Elevator.MOTOR);
		public Encoder encoder = new Encoder(
				RobotMap.ContainerLifter.Elevator.ENCODER_A_DIO,
				RobotMap.ContainerLifter.Elevator.ENCODER_B_DIO);
		
		@Override
		protected void initDefaultCommand() {
			setDefaultCommand(stopCmd());
		}
		
		public Command stopCmd() {
			return QuickCommand.oneShot(this, () -> motor.set(0.0));
		}
		
		public Command userControlCmd(Joystick js) {
			return QuickCommand.continuous(this, () -> motor.set(js.getY()));
		}
	}
}

