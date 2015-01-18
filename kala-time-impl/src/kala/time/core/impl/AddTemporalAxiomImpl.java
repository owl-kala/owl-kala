/**
 * 
 */
package kala.time.core.impl;

import kala.time.core.AddTemporalAxiom;
import kala.time.core.TemporalOntologyChangeVisitor;
import kala.time.model.TemporalAxiom;

/**
 * @author sven
 *
 */
public class AddTemporalAxiomImpl extends TemporalAxiomChangeImpl 
			implements AddTemporalAxiom {

	public AddTemporalAxiomImpl(TemporalAxiom axiom) {
		super(axiom);
	}
	
	@Override
	public void accept(TemporalOntologyChangeVisitor visitor) {
		visitor.visit(this);
	}

}
