/**
 * 
 */
package kala.time.core;

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

import org.joda.time.DateTime;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;

/**
 * @author sven
 *
 */
public interface TemporalDataFactory extends OWLDataFactory {
	
	public TimeInstant getTimeInstant();
	
	public TimeInstant getTimeInstant(IRI instantIRI);
	
	public TimeInstant getTimeInstant(OWLIndividual individual);
	
	public TimeInterval getTimeInterval();
	
	public TimeInterval getTimeInterval(IRI intervalIRI);
	
	public TimeInterval getTimeInterval(OWLIndividual individual);
	
	public TemporalEntityDeclarationAxiom getTemporalEntityDeclarationAxiom(
			TemporalEntity entity);
	
	public InstantTimeAssertionAxiom getInstantTimeAssertionAxiom(
			TimeInstant instant, DateTime time);
	
	public IntervalStartAssertionAxiom getIntervalStartAssertionAxiom(
			TimeInterval interval, TimeInstant instant);

	public IntervalEndAssertionAxiom getIntervalEndAssertionAxiom(
			TimeInterval interval, TimeInstant instant);
	
	public FluentObjectProperty getFluentObjectProperty(IRI propertyIRI);
	
	public FluentObjectProperty getFluentObjectProperty(
			OWLObjectProperty property);

	public FluentDataProperty getFluentDataProperty(IRI propertyIRI);

	public FluentDataProperty getFluentDataProperty(
			OWLDataProperty property);
	
	public FluentObjectPropertyDomainAxiom getFluentObjectPropertyDomainAxiom(
			FluentObjectProperty property, OWLClassExpression classExpression);

	public FluentObjectPropertyRangeAxiom getFluentObjectPropertyRangeAxiom(
			FluentObjectProperty property, OWLClassExpression classExpression);
	
	public FluentDataPropertyDomainAxiom getFluentDataPropertyDomainAxiom(
			FluentDataProperty property, OWLClassExpression classExpression);
	
	public FluentDataPropertyRangeAxiom getFluentDataPropertyRangeAxiom(
			FluentDataProperty property, OWLDataRange dataRange);
	
	public FluentObjectPropertyAssertionAxiom getFluentObjectPropertyAssertionAxiom(
			OWLIndividual subject, FluentObjectProperty property, OWLIndividual object,
			TimeInterval interval);
	
	public FluentDataPropertyAssertionAxiom getFluentDataPropertyAssertionAxiom(
			OWLIndividual subject, FluentDataProperty property, OWLLiteral object,
			TimeInterval interval);

}
