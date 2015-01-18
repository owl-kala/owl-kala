package kala.time.core.impl;

import kala.time.core.RemoveTemporalAxiom;
import kala.time.core.TemporalOntologyChangeVisitor;
import kala.time.model.TemporalAxiom;

public class RemoveTemporalAxiomImpl extends TemporalAxiomChangeImpl implements
		RemoveTemporalAxiom {
	
	public RemoveTemporalAxiomImpl(TemporalAxiom axiom) {
		super(axiom);
	}

	@Override
	public void accept(TemporalOntologyChangeVisitor visitor) {
		visitor.visit(this);
	}

}
