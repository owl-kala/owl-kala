package kala.time.model.impl;

import org.semanticweb.owlapi.model.OWLClassExpression;

import kala.time.model.FluentObjectProperty;
import kala.time.model.FluentObjectPropertyRangeAxiom;
import kala.time.model.TemporalAxiomVisitor;
import kala.time.util.Hashes;

public class FluentObjectPropertyRangeAxiomImpl implements
		FluentObjectPropertyRangeAxiom {

	private final FluentObjectProperty property;
	private final OWLClassExpression classExpression;
	
	public FluentObjectPropertyRangeAxiomImpl(
			FluentObjectProperty property, OWLClassExpression classExpression) {
		this.property = property;
		this.classExpression = classExpression;
	}

	@Override
	public FluentObjectProperty getProperty() {
		return property;
	}

	@Override
	public OWLClassExpression getClassExpression() {
		return classExpression;
	}

	@Override
	public void accept(TemporalAxiomVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public boolean equals(Object o) {
		if(o instanceof FluentObjectPropertyRangeAxiomImpl) {
			FluentObjectPropertyRangeAxiomImpl co = (FluentObjectPropertyRangeAxiomImpl) o;
			return getProperty().equals(co.getProperty())
					&& getClassExpression().equals(co.getClassExpression());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int m = Hashes.HASH_MULT;
		int hashCode = Hashes.HASH_FLUENT_OBJECT_PROPERTY_RANGE_AXIOM;
		hashCode = hashCode * m + getProperty().hashCode();
		hashCode = hashCode * m + getClassExpression().hashCode();
		return hashCode;
	}
	
	@Override
	public String toString() {
		return "FluentObjectPropertyRangeAxiom(" + getProperty() + ": " + getClassExpression() + ")";
	}

}
