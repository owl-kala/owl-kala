package kala.time.model.impl;

import org.semanticweb.owlapi.model.OWLDataProperty;

import kala.time.model.FluentDataProperty;
import kala.time.model.TemporalEntityVisitor;
import kala.time.util.Hashes;

public class FluentDataPropertyImpl implements FluentDataProperty {

	private final OWLDataProperty property;
	
	public FluentDataPropertyImpl(OWLDataProperty property) {
		this.property = property;
	}

	@Override
	public OWLDataProperty getProperty() {
		return property;
	}

	@Override
	public void accept(TemporalEntityVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof FluentDataPropertyImpl) {
			FluentDataPropertyImpl co = (FluentDataPropertyImpl) o;
			return getProperty().equals(co.getProperty());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int m = Hashes.HASH_MULT;
		int hashCode = Hashes.HASH_FLUENT_DATA_PROPERTY;
		hashCode = hashCode * m + getProperty().hashCode();
		return hashCode;
	}
	
	@Override
	public String toString() {
		return "FluentDataProperty(" + getProperty() + ")";
	}
	
}
