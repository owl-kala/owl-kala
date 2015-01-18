package kala.time.model.impl;

import org.joda.time.DateTime;
import kala.time.model.InstantTimeAssertionAxiom;
import kala.time.model.TemporalAxiomVisitor;
import kala.time.model.TimeInstant;
import kala.time.util.Hashes;

public class InstantTimeAssertionAxiomImpl implements
		InstantTimeAssertionAxiom {
	
	private final TimeInstant instant;
	private final DateTime time;
	
	public InstantTimeAssertionAxiomImpl(TimeInstant instant, DateTime time) {
		this.instant = instant;
		this.time = time;
	}
	
	@Override
	public TimeInstant getInstant() {
		return instant;
	}
	
	@Override
	public DateTime getTime() {
		return time;
	}

	@Override
	public void accept(TemporalAxiomVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof InstantTimeAssertionAxiomImpl) {
			InstantTimeAssertionAxiomImpl co = (InstantTimeAssertionAxiomImpl) o;
			return getInstant().equals(co.getInstant())
					&& getTime().equals(co.getTime());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int m = Hashes.HASH_MULT;
		int hashCode = Hashes.HASH_INSTANT_TIME_ASSERTION_AXIOM;
		hashCode = hashCode * m + getInstant().hashCode();
		hashCode = hashCode * m + getTime().hashCode();
		return hashCode;
	}
	
	@Override
	public String toString() {
		return "InstantTimeAssertionAxiom(" + getInstant() + ", " + getTime() + ")";
	}

}
