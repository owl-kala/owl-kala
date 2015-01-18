package kala.time.model.impl;

import org.semanticweb.owlapi.model.OWLClassExpression;

import kala.time.model.FluentDataProperty;
import kala.time.model.FluentDataPropertyDomainAxiom;
import kala.time.model.TemporalAxiomVisitor;
import kala.time.util.Hashes;

public class FluentDataPropertyDomainAxiomImpl implements
		FluentDataPropertyDomainAxiom {

	private final FluentDataProperty property;
	private final OWLClassExpression classExpression;
	
	public FluentDataPropertyDomainAxiomImpl(
			FluentDataProperty property, OWLClassExpression classExpression) {
		this.property = property;
		this.classExpression = classExpression;
	}

	@Override
	public FluentDataProperty getProperty() {
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
		if(o instanceof FluentDataPropertyDomainAxiomImpl) {
			FluentDataPropertyDomainAxiomImpl co = (FluentDataPropertyDomainAxiomImpl) o;
			return getProperty().equals(co.getProperty())
					&& getClassExpression().equals(co.getClassExpression());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int m = Hashes.HASH_MULT;
		int hashCode = Hashes.HASH_FLUENT_DATA_PROPERTY_DOMAIN_AXIOM;
		hashCode = hashCode * m + getProperty().hashCode();
		hashCode = hashCode * m + getClassExpression().hashCode();
		return hashCode;
	}
	
	@Override
	public String toString() {
		return "FluentDataPropertyDomainAxiom(" + getProperty() + ": " + getClassExpression() + ")";
	}

}
