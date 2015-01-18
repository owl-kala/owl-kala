package kala.time.model.impl;

import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;

import kala.time.model.FluentDataProperty;
import kala.time.model.FluentDataPropertyAssertionAxiom;
import kala.time.model.TemporalAxiomVisitor;
import kala.time.model.TimeInterval;
import kala.time.util.Hashes;

public class FluentDataPropertyAssertionAxiomImpl implements
		FluentDataPropertyAssertionAxiom {
	
	private final OWLIndividual subject;
	private final FluentDataProperty property;
	private final OWLLiteral object;
	private final TimeInterval interval;
	
	public FluentDataPropertyAssertionAxiomImpl(
			OWLIndividual subject, FluentDataProperty property, OWLLiteral object, 
			TimeInterval interval) {
		this.subject = subject;
		this.property = property;
		this.object = object;
		this.interval = interval;
	}

	@Override
	public OWLIndividual getSubject() {
		return subject;
	}

	@Override
	public FluentDataProperty getProperty() {
		return property;
	}

	@Override
	public OWLLiteral getObject() {
		return object;
	}

	@Override
	public TimeInterval getInterval() {
		return interval;
	}

	@Override
	public void accept(TemporalAxiomVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof FluentDataPropertyAssertionAxiomImpl) {
			FluentDataPropertyAssertionAxiomImpl co = (FluentDataPropertyAssertionAxiomImpl) o;
			return getSubject().equals(co.getSubject())
					&& getProperty().equals(co.getProperty())
					&& getObject().equals(co.getObject())
					&& getInterval().equals(co.getInterval());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int m = Hashes.HASH_MULT;
		int hashCode = Hashes.HASH_FLUENT_DATA_PROPERTY_ASSERTION_AXIOM;
		hashCode = hashCode * m + getSubject().hashCode();
		hashCode = hashCode * m + getProperty().hashCode();
		hashCode = hashCode * m + getObject().hashCode();
		hashCode = hashCode * m + getInterval().hashCode();
		return hashCode;
	}
	
	@Override
	public String toString() {
		return "FluentDataPropertyAssertionAxiom(" + getProperty() + "(" 
				+ getSubject() + ", " + getObject() + ", " + getInterval() + ")";
	}
}
