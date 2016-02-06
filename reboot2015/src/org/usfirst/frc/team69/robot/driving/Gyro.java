package org.usfirst.frc.team69.robot.driving;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.SerialPort;

/**
 * The {@link Gyro} class extends the {@link AHRS} class, adding a defualt constructor
 * and a reset counter, which is useful for determining if the gyro has been reset since
 * last checked.
 * 
 * @author James Hagborg
 *
 */
public class Gyro extends AHRS {
	private int lastReset = 0;
	
	/**
	 * Constructs a new gyro, using the serial port on the MXP board.
	 */
	public Gyro() {
		super(SerialPort.Port.kMXP);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void reset() {
		super.reset();
	}
	
	/**
	 * Returns an integer representing the number of times the gyro has been reset.
	 * Use this value to check if the gyro has been reset since you last checked it,
	 * so that you don't spin around like an idiot if the driver resets it.
	 * 
	 * @return the number of times the gyro has been reset since the robot turned on
	 */
	public int getLastReset() {
		return lastReset;
	}
}
