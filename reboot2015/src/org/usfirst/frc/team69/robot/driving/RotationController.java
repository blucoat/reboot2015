package org.usfirst.frc.team69.robot.driving;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;

public class RotationController implements PIDOutput {
	private double output;
	private double deadzone;
	private double lastReset;
	
	private PIDController pid;
	private Gyro gyro;
	
	public RotationController(Gyro gyro, double deadzone) {
		this.deadzone = deadzone;
		this.pid = new PIDController(1.0, 0.0, 0.0, gyro, this);
		this.lastReset = gyro.getLastReset();
		this.output = 0;
	}
	
	public PIDController getPIDController() {
		return pid;
	}
	
	public void setDeadZone(double d) {
		deadzone = d;
	}
	
	public double getDeadZone() {
		return deadzone;
	}
	
	public double calculateRotation(double jsRot) {
		if (gyro.getLastReset() != lastReset) {
			lastReset = gyro.getLastReset();
			pid.setSetpoint(gyro.pidGet());
		}
		
		if (jsRot < deadzone) {
			pid.enable();
			return output;
		} else {
			pid.disable();
			pid.setSetpoint(gyro.pidGet());
			return jsRot;
		}
	}

	@Override
	public void pidWrite(double output) {
		this.output = output;
	}
}
