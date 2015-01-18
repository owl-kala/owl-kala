package kala.time.model.impl;

import org.semanticweb.owlapi.model.OWLObjectProperty;

import kala.time.model.FluentObjectProperty;
import kala.time.model.TemporalEntityVisitor;
import kala.time.util.Hashes;

public class FluentObjectPropertyImpl implements FluentObjectProperty {
	
	private final OWLObjectProperty property;
	
	public FluentObjectPropertyImpl(OWLObjectProperty property) {
		this.property = property;
	}

	@Override
	public OWLObjectProperty getProperty() {
		return property;
	}
	
	@Override
	public void accept(TemporalEntityVisitor visitor) {
		visitor.visit(this);
	}	

	@Override
	public boolean equals(Object o) {
		if(o instanceof FluentObjectPropertyImpl) {
			FluentObjectPropertyImpl co = (FluentObjectPropertyImpl) o;
			return getProperty().equals(co.getProperty());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int m = Hashes.HASH_MULT;
		int hashCode = Hashes.HASH_FLUENT_OBJECT_PROPERTY;
		hashCode = hashCode * m + getProperty().hashCode();
		return hashCode;
	}
	
	@Override
	public String toString() {
		return "FluentObjectProperty(" + getProperty() + ")";
	}

}
