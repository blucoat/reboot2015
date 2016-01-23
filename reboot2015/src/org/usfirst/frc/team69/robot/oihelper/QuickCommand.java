package org.usfirst.frc.team69.robot.oihelper;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The {@link QuickCommand} class provides helper methods to make short commands.
 * This removes the need to make a new class for every small command, and thus
 * helps quick development and simplifies code.
 * <p>
 * If you want to make a command that runs once and finishes instantly, use
 * {@code oneShot}.  If you want to make a command that runs continuously until
 * it is interrupted, use {@code continuous}.
 * <p>
 * For example, the following code makes a command that will call
 * {@code exampleSubsystem.doSomething()} once and then end:
 * 
 * <pre>
 * {@code
 * Command myCmd = QuickCommand.oneShot(Robot.exampleSubsystem, () -> Robot.exampleSubsystem.doSomething());
 * }</pre>
 * 
 * For more information on the syntax used, look up Java lambda expressions.
 * <p>
 * Any more advanced features, like custom conditions for {@code isFinished},
 * compound commands, or commands that require multiple or no subsystems,
 * subclass {@link Command} in the usual way.
 * 
 * @author James
 *
 */
public class QuickCommand {
	/**
	 * The functional interface for a command body.  The {@code run} method will
	 * be run once or repeatedly, depending on the type of command.  Usually,
	 * this interface will be implemented with a lambda expression, rather than
	 * directly.
	 * 
	 * @author James
	 *
	 */
	public static interface CommandBody {
		/**
		 * Run either once or repeatedly while the command is active.
		 */
		public void run();
	}
	
	/**
	 * Create a command that will run once and finish instantly.
	 * 
	 * @param req The {@link Subsystem} that this command requires.
	 * @param body The {@link CommandBody} that will run once.
	 * @return The newly created {@link Command}
	 */
	public static Command oneShot(Subsystem req, CommandBody body) {
		return new Command() {
			{
				this.requires(req);
			}
			
			@Override
			protected void initialize() {
				body.run();
			}
			@Override
			protected void execute() {}
			@Override
			protected boolean isFinished() {
				return true;
			}
			@Override
			protected void end() {}
			@Override
			protected void interrupted() {}
		};
	}
	
	/**
	 * Create a command that will run until interrupted.
	 * 
	 * @param req The {@link Subsystem} that this command requires.
	 * @param body The {@link CommandBody} that will run repeatedly.
	 * @return The newly created {@link Command}
	 */
	public static Command continuous(Subsystem req, CommandBody body) {
		return new Command() {
			{
				this.requires(req);
			}
			
			@Override
			protected void initialize() {}
			@Override
			protected void execute() {
				body.run();
			}
			@Override
			protected boolean isFinished() {
				return false;
			}
			@Override
			protected void end() {}
			@Override
			protected void interrupted() {}
		};
	}
}
