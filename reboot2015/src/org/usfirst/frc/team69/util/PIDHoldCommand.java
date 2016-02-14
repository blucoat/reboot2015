package org.usfirst.frc.team69.util;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class PIDHoldCommand extends Command {

	PIDController pid;
	PIDSource source;
	
	public PIDHoldCommand(Subsystem req, PIDController pid, PIDSource source) {
		requires(req);
		this.pid = pid;
		this.source = source;
	}

	@Override
	protected void initialize() {
		pid.setSetpoint(source.pidGet());
		pid.enable();
	}

	@Override
	protected void execute() {
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		pid.disable();
	}

	@Override
	protected void interrupted() {
		pid.disable();
	}
}
