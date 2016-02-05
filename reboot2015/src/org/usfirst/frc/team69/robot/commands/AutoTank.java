package org.usfirst.frc.team69.robot.commands;

import org.usfirst.frc.team69.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoTank extends Command {

	public enum Direction {
		FORWARD,
		BACKWARD,
		ROTATE_LEFT,
		ROTATE_RIGHT
	}
	
	private double speed;
	private Direction direction;
	
    public AutoTank(double speed, double timeout, Direction d) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.drivetrain);
    	this.speed = speed;
    	this.direction = d;
    	setTimeout(timeout);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double leftPolarity = (direction == Direction.FORWARD || direction == Direction.ROTATE_RIGHT) ? 1 : -1;
    	double rightPolarity = (direction == Direction.FORWARD || direction == Direction.ROTATE_LEFT) ? 1 : -1;
    	
    	Robot.drivetrain.drive.tankDrive(leftPolarity*speed, rightPolarity*speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.drivetrain.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.drivetrain.stop();
    }
}
