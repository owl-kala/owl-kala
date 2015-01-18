/**
 * 
 */
package kala.time.model.impl;

import org.semanticweb.owlapi.model.OWLIndividual;

import kala.time.model.TemporalEntityVisitor;
import kala.time.model.TimeInstant;
import kala.time.util.Hashes;

/**
 * 
 * @author sven
 *
 */
public class TimeInstantImpl extends TimePrimitiveImpl implements TimeInstant {
	
	public TimeInstantImpl(OWLIndividual individual) {
		super(individual);
	}
	
	@Override
	public void accept(TemporalEntityVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof TimeInstantImpl) {
			TimeInstantImpl co = (TimeInstantImpl) o;
			return getIndividual().equals(co.getIndividual());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int m = Hashes.HASH_MULT;
		int hashCode = Hashes.HASH_TIME_INSTANT;
		hashCode = hashCode * m + getIndividual().hashCode();
		return hashCode;
	}
	
	@Override
	public String toString() {
		return "TimeInstant(" + getIndividual() + ")";
	}
	
}
