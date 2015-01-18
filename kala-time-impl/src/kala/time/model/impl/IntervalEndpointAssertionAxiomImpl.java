package kala.time.model.impl;

import kala.time.model.IntervalEndpointAssertionAxiom;
import kala.time.model.TimeInstant;
import kala.time.model.TimeInterval;

public abstract class IntervalEndpointAssertionAxiomImpl implements
		IntervalEndpointAssertionAxiom {
	
	private final TimeInterval interval;
	private final TimeInstant instant;
	
	protected IntervalEndpointAssertionAxiomImpl(TimeInterval interval, TimeInstant instant) {
		this.interval = interval;
		this.instant = instant;
	}

	@Override
	public TimeInterval getInterval() {
		return interval;
	}

	@Override
	public TimeInstant getInstant() {
		return instant;
	}

}
