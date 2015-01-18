/**
 * 
 */
package kala.time.core.impl;

import org.joda.time.DateTime;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import kala.time.core.TemporalDataFactory;
import kala.time.model.FluentDataProperty;
import kala.time.model.FluentDataPropertyAssertionAxiom;
import kala.time.model.FluentDataPropertyDomainAxiom;
import kala.time.model.FluentDataPropertyRangeAxiom;
import kala.time.model.FluentObjectProperty;
import kala.time.model.FluentObjectPropertyAssertionAxiom;
import kala.time.model.FluentObjectPropertyDomainAxiom;
import kala.time.model.FluentObjectPropertyRangeAxiom;
import kala.time.model.InstantTimeAssertionAxiom;
import kala.time.model.IntervalEndAssertionAxiom;
import kala.time.model.IntervalStartAssertionAxiom;
import kala.time.model.TemporalEntity;
import kala.time.model.TimeInstant;
import kala.time.model.TimeInterval;
import kala.time.model.TemporalEntityDeclarationAxiom;
import kala.time.model.impl.FluentDataPropertyAssertionAxiomImpl;
import kala.time.model.impl.FluentDataPropertyDomainAxiomImpl;
import kala.time.model.impl.FluentDataPropertyImpl;
import kala.time.model.impl.FluentDataPropertyRangeAxiomImpl;
import kala.time.model.impl.FluentObjectPropertyAssertionAxiomImpl;
import kala.time.model.impl.FluentObjectPropertyDomainAxiomImpl;
import kala.time.model.impl.FluentObjectPropertyImpl;
import kala.time.model.impl.FluentObjectPropertyRangeAxiomImpl;
import kala.time.model.impl.InstantTimeAssertionAxiomImpl;
import kala.time.model.impl.IntervalEndAssertionAxiomImpl;
import kala.time.model.impl.IntervalStartAssertionAxiomImpl;
import kala.time.model.impl.TimeInstantImpl;
import kala.time.model.impl.TimeIntervalImpl;
import kala.time.model.impl.TemporalEntityDeclarationAxiomImpl;

/**
 * @author sven
 *
 */
public class TemporalDataFactoryImpl extends ForwardingOWLDataFactoryImpl
		implements TemporalDataFactory {

	/**
	 * @param delegate
	 */
	public TemporalDataFactoryImpl(OWLDataFactory delegate) {
		super(delegate);
	}

	@Override
	public TimeInstant getTimeInstant() {
		OWLIndividual individual = getOWLAnonymousIndividual();
		return getTimeInstant(individual);
	}
	
	@Override
	public TimeInstant getTimeInstant(IRI instantIRI) {
		OWLIndividual individual = getOWLNamedIndividual(instantIRI);
		return getTimeInstant(individual);
	}
	
	@Override 
	public TimeInstant getTimeInstant(OWLIndividual individual) {
		return new TimeInstantImpl(individual);
	}

	@Override
	public TimeInterval getTimeInterval() {
		OWLIndividual individual = getOWLAnonymousIndividual();
		return getTimeInterval(individual);
	}
	
	@Override
	public TimeInterval getTimeInterval(IRI intervalIRI) {
		OWLIndividual individual = getOWLNamedIndividual(intervalIRI);
		return getTimeInterval(individual);
	}
	
	@Override 
	public TimeInterval getTimeInterval(OWLIndividual individual) {
		return new TimeIntervalImpl(individual);
	}

	@Override
	public TemporalEntityDeclarationAxiom getTemporalEntityDeclarationAxiom(TemporalEntity entity) {
		return new TemporalEntityDeclarationAxiomImpl(entity);
	}

	@Override
	public InstantTimeAssertionAxiom getInstantTimeAssertionAxiom(
			TimeInstant instant, DateTime time) {
		return new InstantTimeAssertionAxiomImpl(instant, time);
	}

	@Override
	public IntervalStartAssertionAxiom getIntervalStartAssertionAxiom(
			TimeInterval interval, TimeInstant instant) {
		return new IntervalStartAssertionAxiomImpl(interval, instant);
	}

	@Override
	public IntervalEndAssertionAxiom getIntervalEndAssertionAxiom(
			TimeInterval interval, TimeInstant instant) {
		return new IntervalEndAssertionAxiomImpl(interval, instant);
	}
	
	@Override
	public FluentObjectProperty getFluentObjectProperty(IRI propertyIRI) {
		OWLObjectProperty p = getOWLObjectProperty(propertyIRI);
		return getFluentObjectProperty(p);
	}
	
	@Override
	public FluentObjectProperty getFluentObjectProperty(OWLObjectProperty property) {
		return new FluentObjectPropertyImpl(property);
	}
	
	@Override
	public FluentDataProperty getFluentDataProperty(IRI propertyIRI) {
		OWLDataProperty p = getOWLDataProperty(propertyIRI);
		return getFluentDataProperty(p);
	}

	@Override
	public FluentDataProperty getFluentDataProperty(OWLDataProperty property) {
		return new FluentDataPropertyImpl(property);
	}

	@Override
	public FluentObjectPropertyDomainAxiom getFluentObjectPropertyDomainAxiom(
			FluentObjectProperty property, OWLClassExpression classExpression) {
		return new FluentObjectPropertyDomainAxiomImpl(property, classExpression);
	}

	@Override
	public FluentObjectPropertyRangeAxiom getFluentObjectPropertyRangeAxiom(
			FluentObjectProperty property, OWLClassExpression classExpression) {
		return new FluentObjectPropertyRangeAxiomImpl(property, classExpression);
	}

	@Override
	public FluentDataPropertyDomainAxiom getFluentDataPropertyDomainAxiom(
			FluentDataProperty property, OWLClassExpression classExpression) {
		return new FluentDataPropertyDomainAxiomImpl(property, classExpression);
	}

	@Override
	public FluentDataPropertyRangeAxiom getFluentDataPropertyRangeAxiom(
			FluentDataProperty property, OWLDataRange dataRange) {
		return new FluentDataPropertyRangeAxiomImpl(property, dataRange);
	}

	@Override
	public FluentObjectPropertyAssertionAxiom getFluentObjectPropertyAssertionAxiom(
			OWLIndividual subject, FluentObjectProperty property,
			OWLIndividual object, TimeInterval interval) {
		return new FluentObjectPropertyAssertionAxiomImpl(subject, property, object, interval);
	}

	@Override
	public FluentDataPropertyAssertionAxiom getFluentDataPropertyAssertionAxiom(
			OWLIndividual subject, FluentDataProperty property,
			OWLLiteral object, TimeInterval interval) {
		return new FluentDataPropertyAssertionAxiomImpl(subject, property, object, interval);
	}

}
