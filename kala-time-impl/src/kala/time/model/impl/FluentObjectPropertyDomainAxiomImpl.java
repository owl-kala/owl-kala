package kala.time.model.impl;

import org.semanticweb.owlapi.model.OWLClassExpression;

import kala.time.model.FluentObjectProperty;
import kala.time.model.FluentObjectPropertyDomainAxiom;
import kala.time.model.TemporalAxiomVisitor;
import kala.time.util.Hashes;

public class FluentObjectPropertyDomainAxiomImpl implements
		FluentObjectPropertyDomainAxiom {
	
	private final FluentObjectProperty property;
	private final OWLClassExpression classExpression;
	
	public FluentObjectPropertyDomainAxiomImpl(
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
		if(o instanceof FluentObjectPropertyDomainAxiomImpl) {
			FluentObjectPropertyDomainAxiomImpl co = (FluentObjectPropertyDomainAxiomImpl) o;
			return getProperty().equals(co.getProperty())
					&& getClassExpression().equals(co.getClassExpression());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int m = Hashes.HASH_MULT;
		int hashCode = Hashes.HASH_FLUENT_OBJECT_PROPERTY_DOMAIN_AXIOM;
		hashCode = hashCode * m + getProperty().hashCode();
		hashCode = hashCode * m + getClassExpression().hashCode();
		return hashCode;
	}
	
	@Override
	public String toString() {
		return "FluentObjectPropertyDomainAxiom(" + getProperty() + ": " + getClassExpression() + ")";
	}

}
