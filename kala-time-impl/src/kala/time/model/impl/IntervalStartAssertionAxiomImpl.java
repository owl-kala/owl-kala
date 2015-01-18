package kala.time.model.impl;

import kala.time.model.IntervalStartAssertionAxiom;
import kala.time.model.TemporalAxiomVisitor;
import kala.time.model.TimeInstant;
import kala.time.model.TimeInterval;
import kala.time.util.Hashes;

public class IntervalStartAssertionAxiomImpl extends
		IntervalEndpointAssertionAxiomImpl implements
		IntervalStartAssertionAxiom {
	
	public IntervalStartAssertionAxiomImpl(TimeInterval interval, TimeInstant instant) {
		super(interval, instant);
	}

	@Override
	public void accept(TemporalAxiomVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof IntervalStartAssertionAxiomImpl) {
			IntervalStartAssertionAxiomImpl co = (IntervalStartAssertionAxiomImpl) o;
			return getInterval().equals(co.getInterval())
					&& getInstant().equals(co.getInstant());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int m = Hashes.HASH_MULT;
		int hashCode = Hashes.HASH_INTERVAL_START_ASSERTION_AXIOM;
		hashCode = hashCode * m + getInterval().hashCode();
		hashCode = hashCode * m + getInstant().hashCode();
		return hashCode;
	}
	
	@Override
	public String toString() {
		return "IntervalStartAssertionAxiom(" + getInterval() + ", " + getInstant() + ")";
	}
	
}
