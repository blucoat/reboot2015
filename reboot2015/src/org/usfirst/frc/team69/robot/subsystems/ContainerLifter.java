package org.usfirst.frc.team69.robot.subsystems;

import org.usfirst.frc.team69.robot.RobotMap;
import org.usfirst.frc.team69.util.QuickCommand;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

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

		public Vacuum() {
			super("Container Lifter Vacuum");
			LiveWindow.addSensor(getName(), "Vacuum Sensor", sensor);
			LiveWindow.addActuator(getName(), "Indicator Light", indicator);
			LiveWindow.addActuator(getName(), "Release Solenoid", release);
			LiveWindow.addActuator(getName(), "Venturi Solenoid", venturi);
		}
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

		public Tilt() {
			super("Container Lifter Tilt");
			LiveWindow.addActuator(getName(), "Tilt", solenoid);
		}
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
		private Talon motor = new Talon(RobotMap.ContainerLifter.Elevator.MOTOR);
		private Encoder encoder = new Encoder(
				RobotMap.ContainerLifter.Elevator.ENCODER_A_DIO,
				RobotMap.ContainerLifter.Elevator.ENCODER_B_DIO);
		private PIDController pid = new PIDController(0.05, 0.01, 0.05, encoder, motor);
		
		public Elevator() {
			super("Container Lifter Elevator");
			LiveWindow.addActuator(getName(), "Motor", motor);
			LiveWindow.addSensor(getName(), "Encoder", encoder);
			LiveWindow.addActuator(getName(), "PID Controller", pid);
			
			encoder.setDistancePerPulse(0.01);
			pid.setOutputRange(-0.5, 0.75);
		}
		
		@Override
		protected void initDefaultCommand() {
			setDefaultCommand(pidHoldCmd());
		}
		
		public int getEncoder() {
			return encoder.get();
		}
		
		public Command stopCmd() {
			return QuickCommand.oneShot(this, () -> motor.set(0.0));
		}
		
		public Command pidHoldCmd() {
			return QuickCommand.pidHold(this, pid, encoder);
		}
		
		public Command userControlCmd(Joystick js) {
			return QuickCommand.continuous(this, () -> motor.set(js.getY()));
		}
	}
}

