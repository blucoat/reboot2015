package org.usfirst.frc.team69.robot.commands;

import org.usfirst.frc.team69.robot.Robot;
import org.usfirst.frc.team69.robot.oihelper.QuickCommand;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GrabContainers extends CommandGroup {
    
    public  GrabContainers() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	//apparently grabbing with and without a scoring platform uses the exact same code
    	
    	addSequential(new AutoTank(0.5, 2.0, AutoTank.Direction.FORWARD));
		addSequential(Robot.containerGrabber.setArmsOutCmd(true));			
		addSequential(new AutoTank(0.65, 2.0, AutoTank.Direction.BACKWARD));
    	addParallel(Robot.containerGrabber.setArmsOutCmd(false));
    	addSequential(new AutoTank(0.65, 0.5, AutoTank.Direction.BACKWARD));
    	addSequential(QuickCommand.oneShot(Robot.drivetrain, () -> Robot.drivetrain.stop()));  // stop the robot
		
    }
}
