package org.usfirst.frc.team69.robot.commands.containerlifter;

import org.usfirst.frc.team69.robot.Robot;
import org.usfirst.frc.team69.robot.subsystems.ContainerLifter;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.PIDCommand;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

/**
 * This command implements velocity PID to keep the speed of the motor 
 * proportional to the joystick value.  Velocity PID is a bit different
 * from position PID.  For one, we need an I value to keep the motor
 * moving even when there is 0 error.  A feed-forward term might also
 * be useful.  Please test this thoroughly with smartdashboard to
 * see if it actually works.
 * 
 * @author James
 *
 */
public class ContainerLifterMovePID extends PIDCommand {

	private ContainerLifter.Elevator elevator = Robot.containerLifter.elevator;
	private Joystick js;
	
	public ContainerLifterMovePID(Joystick js) {
		super(0.1, 0, 0); // again, we need to tune these parameters. we need an I parameter
		LiveWindow.addActuator("Container Lifter", "Velocity PID", getPIDController());
		this.js = js;
	}

	@Override
	protected double returnPIDInput() {
		return elevator.encoder.getRate();
	}

	@Override
	protected void usePIDOutput(double output) {
		elevator.motor.set(output);
	}

	@Override
	protected void initialize() {}

	@Override
	protected void execute() {
		setSetpoint(js.getY());
	}

	@Override
	protected boolean isFinished() {return false;}
	@Override
	protected void end() {}
	@Override
	protected void interrupted() {}

}
