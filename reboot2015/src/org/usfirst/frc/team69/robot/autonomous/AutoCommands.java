package org.usfirst.frc.team69.robot.autonomous;

import org.usfirst.frc.team69.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutoCommands {
	
	// These are some example commands.  Be creative  Use parameters as necessary
	// Note that the command group can add arbitrary timeouts to commands that don't have them
	// Please do NOT run this unless you do it on blocks first.  Please.... ;_;
	
	public static Command strafeLeftCmd() {
		CommandGroup cmd = new CommandGroup();
		cmd.addSequential(Robot.drivetrain.mecanumCmd(-0.5, 0.0, 0.0, 0.0), 1.0);
		return cmd;
	}
	
	public static Command grabContainersCmd() {
		CommandGroup cmd = new CommandGroup();
		
		cmd.addSequential(Robot.drivetrain.tankCmd(0.5, 0.5), 1.0);
		cmd.addParallel(Robot.containerGrabber.setExtendedCmd(true));
		cmd.addSequential(Robot.drivetrain.tankCmd(0.5, 0.5), 1.0);
		cmd.addSequential(Robot.drivetrain.tankCmd(-0.5, -0.5), 1.5);
		cmd.addSequential(Robot.containerGrabber.setExtendedCmd(false));
		
		return cmd;
	}
}
