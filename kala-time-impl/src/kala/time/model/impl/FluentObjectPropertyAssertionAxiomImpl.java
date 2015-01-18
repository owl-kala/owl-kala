package kala.time.model.impl;

import org.semanticweb.owlapi.model.OWLIndividual;

import kala.time.model.FluentObjectProperty;
import kala.time.model.FluentObjectPropertyAssertionAxiom;
import kala.time.model.TemporalAxiomVisitor;
import kala.time.model.TimeInterval;
import kala.time.util.Hashes;

public class FluentObjectPropertyAssertionAxiomImpl implements
		FluentObjectPropertyAssertionAxiom {
	
	private final OWLIndividual subject;
	private final FluentObjectProperty property;
	private final OWLIndividual object;
	private final TimeInterval interval;
	
	public FluentObjectPropertyAssertionAxiomImpl(
			OWLIndividual subject, FluentObjectProperty property, OWLIndividual object,
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
	public FluentObjectProperty getProperty() {
		return property;
	}

	@Override
	public OWLIndividual getObject() {
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
		if(o instanceof FluentObjectPropertyAssertionAxiomImpl) {
			FluentObjectPropertyAssertionAxiomImpl co = (FluentObjectPropertyAssertionAxiomImpl) o;
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
		int hashCode = Hashes.HASH_FLUENT_OBJECT_PROPERTY_ASSERTION_AXIOM;
		hashCode = hashCode * m + getSubject().hashCode();
		hashCode = hashCode * m + getProperty().hashCode();
		hashCode = hashCode * m + getObject().hashCode();
		hashCode = hashCode * m + getInterval().hashCode();
		return hashCode;
	}
	
	@Override
	public String toString() {
		return "FluentObjectPropertyAssertionAxiom(" + getProperty() + "(" 
				+ getSubject() + ", " + getObject() + ", " + getInterval() + ")";
	}

}
