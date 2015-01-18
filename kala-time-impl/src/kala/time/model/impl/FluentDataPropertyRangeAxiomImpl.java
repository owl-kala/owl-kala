package kala.time.model.impl;

import kala.time.model.FluentDataProperty;
import kala.time.model.FluentDataPropertyRangeAxiom;
import kala.time.model.TemporalAxiomVisitor;
import kala.time.util.Hashes;

import org.semanticweb.owlapi.model.OWLDataRange;

public class FluentDataPropertyRangeAxiomImpl implements
		FluentDataPropertyRangeAxiom {

	private final FluentDataProperty property;
	private final OWLDataRange dataRange;
	
	public FluentDataPropertyRangeAxiomImpl(
			FluentDataProperty property, OWLDataRange dataRange) {
		this.property = property;
		this.dataRange = dataRange;
	}

	@Override
	public FluentDataProperty getProperty() {
		return property;
	}

	@Override
	public OWLDataRange getRange() {
		return dataRange;
	}

	@Override
	public void accept(TemporalAxiomVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof FluentDataPropertyRangeAxiomImpl) {
			FluentDataPropertyRangeAxiomImpl co = (FluentDataPropertyRangeAxiomImpl) o;
			return getProperty().equals(co.getProperty())
					&& getRange().equals(co.getRange());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int m = Hashes.HASH_MULT;
		int hashCode = Hashes.HASH_FLUENT_DATA_PROPERTY_RANGE_AXIOM;
		hashCode = hashCode * m + getProperty().hashCode();
		hashCode = hashCode * m + getRange().hashCode();
		return hashCode;
	}
	
	@Override
	public String toString() {
		return "FluentDataPropertyRangeAxiom(" + getProperty() + ": " + getRange() + ")";
	}

}
