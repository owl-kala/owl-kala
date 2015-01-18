package kala.time.model;

import org.joda.time.DateTime;

public interface InstantTimeAssertionAxiom 
		extends TemporalAxiom {
	
	public TimeInstant getInstant();
	
	public DateTime getTime();

}
