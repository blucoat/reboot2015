package org.usfirst.frc.team69.robot.oihelper;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Port {
	public enum Type {
		PWM,
		DIO,
		ANALOG,
		RELAY,
		PCM
	}
	
	public Type type();
}
