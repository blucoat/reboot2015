package org.usfirst.frc.team69.robot.driving;

import java.util.function.DoubleSupplier;

import org.usfirst.frc.team69.robot.Robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;

public class DriveControls {
	
	public enum Align { ROBOT, FIELD };
	
	public static final double ROTATE_DEADZONE = 0.2;
	
	/**
	 * Create a user tank drive command.  This command will use the driver's joysticks
	 * to control the left and right sides of the robot directly.
	 * 
	 * @return A tank drive command
	 */
	public static Command userTankCmd() {
		Joystick left = Robot.oi.leftDriverJoystick.get();
		Joystick right = Robot.oi.rightDriverJoystick.get();
		
		return Robot.drivetrain.tankCmd(left::getY, right::getY);
	}
	
	/**
	 * Create a user mecanum command.  This will allow the user to control the robot using
	 * mecanum drive from a single joystick.  Depending on the parameters here, the
	 * command will use a different drive "mode".
	 * 
	 * @param align Whether to drive the robot field-aligned (using a gyro) or robot-aligned.
	 * @param autoCorrect If {@code true}, the robot will attempt to correct its rotation when
	 * the joystick twist is below a certain amount.
	 * 
	 * @return A mecanum drive command
	 */
	public static Command userMecanumCmd(Align align, boolean autoCorrect) {
		/* This might be a bit confusing, but I think it's cool.
		 * 
		 * The DoubleSuppliers are functions that return doubles.  Passing four of these for
		 * each of the four parameters of mecanum drive will build a command that runs the
		 * drivetrain based on the functions.
		 * 
		 * Depending on whether we are field aligned and whether we have autocorrect, we
		 * will pass different values here.
		 */
		
		Joystick js = Robot.oi.rightDriverJoystick.get();
		
		// If you want to add a deadzone, use something like this:
		// DoubleSupplier x = () -> deadZone(js.getX(), X_DEADZONE);
		// This is also where you can scale values, like x = () -> js.getX() * X_SCALE
		DoubleSupplier x = js::getX;
		DoubleSupplier y = js::getY;
		
		DoubleSupplier userRotate = () -> deadZone(js.getTwist(), ROTATE_DEADZONE);
		DoubleSupplier autoRotate = makeAutoRotate(js);
		
		DoubleSupplier rotate = autoCorrect ? autoRotate : userRotate;
		
		// Mecanum supports field-centric by default.  Passing 0 as the gyro will make it robot-centric
		DoubleSupplier gyro = align == Align.FIELD ? Robot.gyro::getAngle : () -> 0;
		
		return Robot.drivetrain.mecanumCmd(x, y, rotate, gyro);
	}
	
	private static DoubleSupplier makeAutoRotate(Joystick js) {
		RotationController controller = new RotationController(Robot.gyro, ROTATE_DEADZONE);
		controller.getPIDController().setPID(0.05, 0.0, 0.0);
		controller.getPIDController().setAbsoluteTolerance(5);
		
		return () -> controller.calculateRotation(js.getTwist());
	}
	
	private static double deadZone(double value, double deadzone) {
		return Math.abs(value) > deadzone ? value : 0;
	}
}
