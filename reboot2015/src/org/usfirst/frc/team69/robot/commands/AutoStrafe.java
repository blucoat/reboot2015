package org.usfirst.frc.team69.robot.commands;

import org.usfirst.frc.team69.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class AutoStrafe extends Command {
	
	public enum Direction {//robot-centric
		LEFT,
		RIGHT
	}
	
	private double speed;
	private Direction direction;

    public AutoStrafe(double speed, double timeout, Direction d) {
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
    	Robot.drivetrain.drive.mecanumDrive_Polar(direction == Direction.LEFT ? -speed : speed, 90, 0);
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
