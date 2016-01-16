package org.usfirst.frc.team69.robot.oihelper;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class QuickCommand {
	public static interface CommandBody {
		public void run();
	}
	
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
