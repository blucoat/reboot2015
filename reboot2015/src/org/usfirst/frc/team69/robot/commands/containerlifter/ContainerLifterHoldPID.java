package org.usfirst.frc.team69.robot.commands.containerlifter;

import org.usfirst.frc.team69.robot.Robot;
import org.usfirst.frc.team69.robot.subsystems.ContainerLifter;

import edu.wpi.first.wpilibj.command.PIDCommand;

public class ContainerLifterHoldPID extends PIDCommand {
	private ContainerLifter.Elevator elevator = Robot.containerLifter.elevator;
	
	public ContainerLifterHoldPID() {
		super(1, 0, 0); // Major TODO: set these parameters.  Or if we can make it tunable with the livewindow do that.
		this.requires(elevator);
	}

	@Override
	protected void initialize() {
		setSetpoint(elevator.encoder.get());
	}

	@Override
	protected double returnPIDInput() {
		return elevator.encoder.get();
	}

	@Override
	protected void usePIDOutput(double output) {
		elevator.motor.set(output);
	}

	@Override
	protected void execute() {}
	@Override
	protected boolean isFinished() { return false; }
	@Override
	protected void end() {}
	@Override
	protected void interrupted() {}
}
