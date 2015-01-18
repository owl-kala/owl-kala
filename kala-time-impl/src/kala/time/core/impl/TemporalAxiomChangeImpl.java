/**
 * 
 */
package kala.time.core.impl;

import kala.time.core.TemporalAxiomChange;
import kala.time.model.TemporalAxiom;

/**
 * @author sven
 *
 */
public abstract class TemporalAxiomChangeImpl implements TemporalAxiomChange {
	
	private final TemporalAxiom axiom;
	
	protected TemporalAxiomChangeImpl(TemporalAxiom axiom) {
		this.axiom = axiom;
	}

	@Override
	public TemporalAxiom getAxiom() {
		return axiom;
	}

}
