package kala.time.serialization.fluents.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.joda.time.DateTime;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import kala.time.core.TemporalDataFactory;
import kala.time.core.TemporalOntology;
import kala.time.core.TemporalOntologyManager;
import kala.time.model.FluentDataProperty;
import kala.time.model.FluentObjectProperty;
import kala.time.model.TemporalAxiom;
import kala.time.model.TimeInstant;
import kala.time.model.TimeInterval;
import kala.time.serialization.fluents.FluentsParser;
import kala.time.serialization.fluents.FluentsRepresentationScheme;
import kala.time.serialization.fluents.TimesliceRelationDirection;
import kala.time.serialization.impl.ParserBase;

public class FluentsParserImpl extends ParserBase implements FluentsParser {

	private final Set<OWLAxiom> remainingAxioms;
	private final Set<TemporalAxiom> axiomsToAdd;
	
	public FluentsParserImpl(TemporalOntologyManager manager, FluentsRepresentationScheme scheme) {
		super(manager, scheme);
		this.remainingAxioms = new HashSet<OWLAxiom>();
		this.axiomsToAdd = new HashSet<TemporalAxiom>();
	}
	
	@Override
	public FluentsRepresentationScheme getScheme() {
		return (FluentsRepresentationScheme) super.getScheme();
	}
	
	@Override
	public void read(OWLOntology ontology) {
		remainingAxioms.addAll(ontology.getAxioms());

		// Collect all intervals.
		Set<OWLIndividual> intervalIndividuals = getIntervalIndividuals(ontology);
		
		// Handle all interval assertions.
		for(OWLIndividual intervalIndividual: intervalIndividuals) {
			handleInterval(ontology, intervalIndividual);
		}
		
		// Collect all fluent object properties.
		Set<OWLObjectProperty> fluentObjectProperties = getFluentObjectProperties(ontology);
		
		// Handle declarations of fluent object properties.
		for(OWLObjectProperty objectProperty: fluentObjectProperties) {
			handleFluentObjectProperty(ontology, objectProperty);
		}
		
		// Collect all fluent data properties.
		Set<OWLDataProperty> fluentDataProperties = getFluentDataProperties(ontology);

		// Handle declarations of fluent data properties.
		for(OWLDataProperty dataProperty: fluentDataProperties) {
			handleFluentDataProperty(ontology, dataProperty);
		}

		// Collect all timeslices.
		Set<OWLIndividual> timesliceIndividuals = getTimesliceIndividuals(ontology);
		
		// Handle fluent object property assertions per timeslice.
		for(OWLIndividual timesliceIndividual: timesliceIndividuals) {
			handleFluentObjectPropertyAssertions(ontology, timesliceIndividual);
			handleFluentDataPropertyAssertions(ontology, timesliceIndividual);
		}
		
	}

	private void handleFluentObjectPropertyAssertions(OWLOntology ontology,
			OWLIndividual timesliceIndividual) {
		TemporalDataFactory factory = getDataFactory();
		OWLClass timesliceClass = getScheme().getTimesliceClass(factory);
		OWLObjectProperty timesliceRelProperty = getScheme().getTimesliceRelation(factory);
		OWLObjectProperty intervalProperty = getScheme().getIntervalProperty(factory);
		
		OWLIndividual subject = getIndividualOfTimeslice(ontology, timesliceIndividual);
		OWLIndividual intervalIndividual = getIntervalOfTimeslice(ontology, timesliceIndividual);
		
		Map<OWLObjectPropertyExpression, Set<OWLIndividual>> properties = 
				timesliceIndividual.getObjectPropertyValues(ontology);
		for(OWLObjectPropertyExpression ope: properties.keySet()) {
			if(ope instanceof OWLObjectProperty) {
				OWLObjectProperty op = (OWLObjectProperty) ope;
				if(!op.equals(timesliceRelProperty) && !op.equals(intervalProperty)) {
					FluentObjectProperty fop = factory.getFluentObjectProperty(op);
					Set<OWLIndividual> values = properties.get(op);
					for(OWLIndividual value: values) {
						OWLIndividual object = getIndividualOfTimeslice(ontology, value);
						intervalIndividual = intervalIndividual != null ? intervalIndividual : getIntervalOfTimeslice(ontology, value);
						TimeInterval interval = intervalIndividual != null ? factory.getTimeInterval(intervalIndividual) : factory.getTimeInterval(); 
						axiomsToAdd.add(factory.getFluentObjectPropertyAssertionAxiom(subject, fop, object, interval));
						remainingAxioms.removeAll(Arrays.asList(
								factory.getOWLClassAssertionAxiom(timesliceClass, timesliceIndividual),
								factory.getOWLClassAssertionAxiom(timesliceClass, value),
								factory.getOWLObjectPropertyAssertionAxiom(timesliceRelProperty, timesliceIndividual, subject),
								factory.getOWLObjectPropertyAssertionAxiom(timesliceRelProperty, value, object),
								factory.getOWLObjectPropertyAssertionAxiom(intervalProperty, timesliceIndividual, intervalIndividual),
								factory.getOWLObjectPropertyAssertionAxiom(intervalProperty, value, intervalIndividual),
								factory.getOWLObjectPropertyAssertionAxiom(op, timesliceIndividual, value)));
					}
				}
			}
			else {
				throw new IllegalStateException("Cannot handle object property expression " + ope + " for timeslice " + timesliceIndividual);
			}
		}
		
	}
	
	private void handleFluentDataPropertyAssertions(OWLOntology ontology,
			OWLIndividual timesliceIndividual) {
		TemporalDataFactory factory = getDataFactory();
		OWLClass timesliceClass = getScheme().getTimesliceClass(factory);
		OWLObjectProperty timesliceRelProperty = getScheme().getTimesliceRelation(factory);
		OWLObjectProperty intervalProperty = getScheme().getIntervalProperty(factory);
		
		OWLIndividual subject = getIndividualOfTimeslice(ontology, timesliceIndividual);
		OWLIndividual intervalIndividual = getIntervalOfTimeslice(ontology, timesliceIndividual);
		
		Map<OWLDataPropertyExpression, Set<OWLLiteral>> properties = 
				timesliceIndividual.getDataPropertyValues(ontology);
		for(OWLDataPropertyExpression dpe: properties.keySet()) {
			if(dpe instanceof OWLDataProperty) {
				OWLDataProperty dp = (OWLDataProperty) dpe;
				if(!dp.equals(timesliceRelProperty) && !dp.equals(intervalProperty)) {
					FluentDataProperty fdp = factory.getFluentDataProperty(dp);
					Set<OWLLiteral> values = properties.get(dp);
					for(OWLLiteral value: values) {
						TimeInterval interval = intervalIndividual != null ? factory.getTimeInterval(intervalIndividual) : factory.getTimeInterval(); 
						axiomsToAdd.add(factory.getFluentDataPropertyAssertionAxiom(subject, fdp, value, interval));
						remainingAxioms.removeAll(Arrays.asList(
								factory.getOWLClassAssertionAxiom(timesliceClass, timesliceIndividual),
								factory.getOWLObjectPropertyAssertionAxiom(timesliceRelProperty, timesliceIndividual, subject),
								factory.getOWLObjectPropertyAssertionAxiom(intervalProperty, timesliceIndividual, intervalIndividual),
								factory.getOWLDataPropertyAssertionAxiom(dp, timesliceIndividual, value)));
					}
				}
			}
			else {
				throw new IllegalStateException("Cannot handle data property expression " + dpe + " for timeslice " + timesliceIndividual);
			}
		}
		
	}

	@Override
	public TemporalOntology build(TemporalOntologyManager ontologyManager) throws OWLOntologyCreationException {
		OWLOntology delegate = ontologyManager.createOntology(remainingAxioms);
		TemporalOntology result = ontologyManager.createTemporalOntology(delegate);
		result.addTemporalAxioms(axiomsToAdd);
		return result;
	}

	private Set<OWLIndividual> getIntervalIndividuals(OWLOntology ontology) {
		Set<OWLIndividual> result = new HashSet<OWLIndividual>();
		OWLDataFactory factory = getDataFactory();

		// Get all individuals of the interval class.
		OWLClass intervalClass = getScheme().getIntervalClass(factory);
		result.addAll(intervalClass.getIndividuals(ontology));
		
		// Check the interval relation for interval individuals that were not explicitly
		// declared as such. Hacky.
		OWLObjectProperty intervalProperty = getScheme().getIntervalProperty(factory);
		for(OWLAxiom axiom : intervalProperty.getReferencingAxioms(ontology)) {
			if(axiom instanceof OWLObjectPropertyAssertionAxiom) {
				OWLObjectPropertyAssertionAxiom assertionAxiom = (OWLObjectPropertyAssertionAxiom) axiom;
				result.add(assertionAxiom.getObject());
			}
		}	
		
		return result;
	}
	
	private void handleInterval(OWLOntology ontology, OWLIndividual intervalIndividual) {
		TemporalDataFactory factory = getDataFactory();
		
		// First, declare the interval.
		TimeInterval interval = factory.getTimeInterval(intervalIndividual);
		axiomsToAdd.add(factory.getTemporalEntityDeclarationAxiom(interval));
		
		// Remove any declaration of the interval from the original axioms.
		OWLClass intervalClass = getScheme().getIntervalClass(factory);
		remainingAxioms.remove(factory.getOWLClassAssertionAxiom(intervalClass, intervalIndividual));
		
		// Get the interval start literal, if any.
		OWLDataProperty startDataProperty = getScheme().getIntervalStartLiteralProperty(factory);
		Set<OWLLiteral> startValues = intervalIndividual.getDataPropertyValues(startDataProperty, ontology);
		if(!startValues.isEmpty()) {
			if(startValues.size() > 1) {
				throw new IllegalStateException("Interval " + intervalIndividual + " has too many start values (" + startValues.size() + ")");
			}
			OWLLiteral value = startValues.iterator().next();
			if(value.getDatatype().getBuiltInDatatype() != OWL2Datatype.XSD_DATE_TIME) {
				throw new IllegalStateException("Interval " + intervalIndividual + " has non-datetime start literal (" + value.getDatatype() + ")");
			}
			
			// Add the temporal axiom, remove the original axiom.
			TimeInstant startInstant = factory.getTimeInstant();
			DateTime start = DateTime.parse(value.getLiteral());
			axiomsToAdd.addAll(Arrays.asList(
					factory.getInstantTimeAssertionAxiom(startInstant, start),
					factory.getIntervalStartAssertionAxiom(interval, startInstant)));
			remainingAxioms.remove(factory.getOWLDataPropertyAssertionAxiom(startDataProperty, intervalIndividual, value));
		}

		// Get the interval end literal, if any.
		OWLDataProperty endDataProperty = getScheme().getIntervalEndLiteralProperty(factory);
		Set<OWLLiteral> endValues = intervalIndividual.getDataPropertyValues(endDataProperty, ontology);
		if(!endValues.isEmpty()) {
			if(endValues.size() > 1) {
				throw new IllegalStateException("Interval " + intervalIndividual + " has too many end values (" + endValues.size() + ")");
			}
			OWLLiteral value = endValues.iterator().next();
			if(value.getDatatype().getBuiltInDatatype() != OWL2Datatype.XSD_DATE_TIME) {
				throw new IllegalStateException("Interval " + intervalIndividual + " has non-datetime end literal (" + value.getDatatype() + ")");
			}
			
			// Add the temporal axiom, remove the original axiom.
			TimeInstant endInstant = factory.getTimeInstant();
			DateTime end = DateTime.parse(value.getLiteral());
			axiomsToAdd.addAll(Arrays.asList(
					factory.getInstantTimeAssertionAxiom(endInstant, end),
					factory.getIntervalEndAssertionAxiom(interval, endInstant)));
			remainingAxioms.remove(factory.getOWLDataPropertyAssertionAxiom(endDataProperty, intervalIndividual, value));
		}

	}
	
	private Set<OWLIndividual> getTimesliceIndividuals(OWLOntology ontology) {
		Set<OWLIndividual> result = new HashSet<OWLIndividual>();
		OWLDataFactory factory = getDataFactory();
		
		// Get all individuals of the timeslice class.
		OWLClass timesliceClass = getScheme().getTimesliceClass(factory);
		result.addAll(timesliceClass.getIndividuals(ontology));

		// To find timeslices that may not have been declared as such, find the participating
		// objects in the timeslice relation property (subject or object, depending on direction).
		// Hacky.
		OWLObjectProperty timesliceRelProperty = getScheme().getTimesliceRelation(factory);
		TimesliceRelationDirection direction = getScheme().getTimesliceRelationDirection();
		for(OWLAxiom axiom : timesliceRelProperty.getReferencingAxioms(ontology)) {
			if(axiom instanceof OWLObjectPropertyAssertionAxiom) {
				OWLObjectPropertyAssertionAxiom assertionAxiom = (OWLObjectPropertyAssertionAxiom) axiom;
				switch(direction) {
				case TIMESLICE_TO_INDIVIDUAL:
					result.add(assertionAxiom.getSubject());
					break;
				case INDIVIDUAL_TO_TIMESLICE:
					result.add(assertionAxiom.getObject());
					break;
				}
			}
		}
		
		return result;
	}
	


	private Set<OWLObjectProperty> getFluentObjectProperties(OWLOntology ontology) {
		Set<OWLObjectProperty> result = new HashSet<OWLObjectProperty>();
		OWLDataFactory factory = getDataFactory();
		OWLClass timesliceClass = getScheme().getTimesliceClass(factory);
		
		// Find all object properties with domains restricted to timeslices. These should
		// generally represent the fluent properties.
		for(OWLAxiom axiom : timesliceClass.getReferencingAxioms(ontology)) {
			if(axiom instanceof OWLObjectPropertyDomainAxiom) {
				OWLObjectPropertyDomainAxiom domainAxiom = (OWLObjectPropertyDomainAxiom) axiom;
				OWLObjectPropertyExpression ope = domainAxiom.getProperty();
				if(ope instanceof OWLObjectProperty) {
					OWLObjectProperty op = (OWLObjectProperty) ope;
					result.add(op);
				}
			}
		}
		
		// Remove the timeslice property if it's there.
		OWLObjectProperty timesliceProperty = getScheme().getTimesliceRelation(factory);
		result.remove(timesliceProperty);
		
		// Remove the intervalProperty if it's there.
		OWLObjectProperty intervalProperty = getScheme().getIntervalProperty(factory);
		result.remove(intervalProperty);
		
		return result;
	}
	
	private void handleFluentObjectProperty(OWLOntology ontology, OWLObjectProperty property) {
		TemporalDataFactory factory = getDataFactory();
		FluentObjectProperty fop = factory.getFluentObjectProperty(property);
		
		// Add declaration of fluent object property.
		axiomsToAdd.add(factory.getTemporalEntityDeclarationAxiom(fop));
		
		// Remove declaration of original property.
		remainingAxioms.remove(factory.getOWLDeclarationAxiom(property));
		
		// Handle domain axioms for property.
		for(OWLClassExpression domainExpression : property.getDomains(ontology)) {
			if(domainExpression instanceof OWLObjectSomeValuesFrom) {
				OWLObjectSomeValuesFrom domainSVF = (OWLObjectSomeValuesFrom) domainExpression;
				OWLClassExpression domain = domainSVF.getFiller();
				axiomsToAdd.add(factory.getFluentObjectPropertyDomainAxiom(fop, domain));
			}
			
			// Remove original axiom.
			remainingAxioms.remove(factory.getOWLObjectPropertyDomainAxiom(property, domainExpression));
		}
		
		// Handle range axioms for property.
		for(OWLClassExpression rangeExpression : property.getRanges(ontology)) {
			if(rangeExpression instanceof OWLObjectSomeValuesFrom) {
				OWLObjectSomeValuesFrom rangeSVF = (OWLObjectSomeValuesFrom) rangeExpression;
				OWLClassExpression range = rangeSVF.getFiller();
				axiomsToAdd.add(factory.getFluentObjectPropertyRangeAxiom(fop, range));
			}
			
			// Remove original axiom.
			remainingAxioms.remove(factory.getOWLObjectPropertyRangeAxiom(property, rangeExpression));
		}
	
	}
	
	private void handleFluentDataProperty(OWLOntology ontology, OWLDataProperty property) {
		TemporalDataFactory factory = getDataFactory();
		FluentDataProperty fdp = factory.getFluentDataProperty(property);
		
		// Add declaration of fluent object property.
		axiomsToAdd.add(factory.getTemporalEntityDeclarationAxiom(fdp));
		
		// Remove declaration of original property.
		remainingAxioms.remove(factory.getOWLDeclarationAxiom(property));
		
		// Handle domain axioms for property.
		for(OWLClassExpression domainExpression : property.getDomains(ontology)) {
			if(domainExpression instanceof OWLObjectSomeValuesFrom) {
				OWLObjectSomeValuesFrom domainSVF = (OWLObjectSomeValuesFrom) domainExpression;
				OWLClassExpression domain = domainSVF.getFiller();
				axiomsToAdd.add(factory.getFluentDataPropertyDomainAxiom(fdp, domain));
			}
			
			// Remove original axiom.
			remainingAxioms.remove(factory.getOWLDataPropertyDomainAxiom(property, domainExpression));
		}
		
		// Handle range axioms for property.
		for(OWLDataRange rangeExpression : property.getRanges(ontology)) {
			axiomsToAdd.add(factory.getFluentDataPropertyRangeAxiom(fdp, rangeExpression));
			
			// Remove original axiom.
			remainingAxioms.remove(factory.getOWLDataPropertyRangeAxiom(property, rangeExpression));
		}
	
	}
	
	private Set<OWLDataProperty> getFluentDataProperties(OWLOntology ontology) {
		Set<OWLDataProperty> result = new HashSet<OWLDataProperty>();
		OWLDataFactory factory = getDataFactory();
		OWLClass timesliceClass = getScheme().getTimesliceClass(factory);
		
		// Find all object properties with domains restricted to timeslices. These should
		// generally represent the fluent properties.
		for(OWLAxiom axiom : timesliceClass.getReferencingAxioms(ontology)) {
			if(axiom instanceof OWLDataPropertyDomainAxiom) {
				OWLDataPropertyDomainAxiom domainAxiom = (OWLDataPropertyDomainAxiom) axiom;
				OWLDataPropertyExpression dpe = domainAxiom.getProperty();
				if(dpe instanceof OWLDataProperty) {
					OWLDataProperty dp = (OWLDataProperty) dpe;
					result.add(dp);
				}
			}
		}
		
		return result;
	}
	
	private OWLIndividual getIndividualOfTimeslice(
			OWLOntology ontology, OWLIndividual timesliceIndividual) {
		OWLDataFactory factory = getDataFactory();
		OWLObjectProperty timesliceRel = getScheme().getTimesliceRelation(factory);
		Set<OWLIndividual> results = 
				timesliceIndividual.getObjectPropertyValues(timesliceRel, ontology);
		
		if(results.isEmpty()) {
			throw new IllegalStateException("No individuals defined for timeslice " + 
				timesliceIndividual);
		}
		
		if(results.size() > 1) {
			throw new IllegalStateException("Too many individuals defined for timeslice " + 
				timesliceIndividual + " (" + results.size() + ")");
		}
		
		return results.iterator().next();
	}
	
	private OWLIndividual getIntervalOfTimeslice(
			OWLOntology ontology, OWLIndividual timesliceIndividual) {
		OWLDataFactory factory = getDataFactory();
		OWLObjectProperty intervalRel = getScheme().getIntervalProperty(factory);
		Set<OWLIndividual> results = 
				timesliceIndividual.getObjectPropertyValues(intervalRel, ontology);
		
		if(results.isEmpty()) {
			return null;
		}
		
		if(results.size() > 1) {
			throw new IllegalStateException("Too many intervals defined for timeslice " + 
				timesliceIndividual + " (" + results.size() + ")");
		}
		
		return results.iterator().next();
	}

}
