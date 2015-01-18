package kala.time.serialization.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import kala.time.core.TemporalOntology;
import kala.time.model.InstantTimeAssertionAxiom;
import kala.time.model.IntervalEndAssertionAxiom;
import kala.time.model.IntervalStartAssertionAxiom;
import kala.time.model.TemporalAxiom;
import kala.time.model.TemporalAxiomVisitor;
import kala.time.model.TemporalEntityVisitor;
import kala.time.model.TimeInstant;
import kala.time.model.TimeInterval;
import kala.time.serialization.RepresentationScheme;
import kala.time.serialization.Serializer;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;
import org.joda.time.format.ISODateTimeFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

public abstract class SerializerBase implements Serializer, TemporalAxiomVisitor {
	
	private final OWLOntologyManager ontologyManager;
	private final RepresentationScheme scheme;
	private final SetMultimap<TimeInterval, TimeInstant> intervalStart;
	private final SetMultimap<TimeInterval, TimeInstant> intervalEnd;
	private final SetMultimap<TimeInstant, OWLLiteral> instantTimeLiterals;
	private final Set<OWLAxiom> axiomsToWrite;
	
	protected SerializerBase(OWLOntologyManager ontologyManager, RepresentationScheme scheme) {
		this.ontologyManager = ontologyManager;
		this.scheme = scheme;
		this.intervalStart = HashMultimap.create();
		this.intervalEnd = HashMultimap.create();
		this.instantTimeLiterals = HashMultimap.create();
		this.axiomsToWrite = new HashSet<OWLAxiom>();
	}
	
	@Override
	public RepresentationScheme getScheme() {
		return scheme;
	}
	
	@Override
	public void visit(TemporalOntology ontology) {
		axiomsToWrite.addAll(ontology.getAxioms());
		for(TemporalAxiom ax : ontology.getTemporalAxioms()) {
			ax.accept(this);
		}
	}

	@Override
	public void visit(IntervalStartAssertionAxiom axiom) {
		
		if(!getScheme().hasIntervalsAsIndividuals()) {
			throw new UnsupportedOperationException("Not implemented yet.");
		}

		TimeInterval interval = axiom.getInterval();
		TimeInstant instant = axiom.getInstant();

		if(getScheme().hasInstantsAsIndividuals()) {
			OWLDataFactory factory = getOWLDataFactory();
			
			OWLObjectProperty intervalStartProperty = scheme.getIntervalStartInstantProperty(factory);
			OWLObjectPropertyAssertionAxiom intervalHasStartAxiom = 
					factory.getOWLObjectPropertyAssertionAxiom(intervalStartProperty, 
					interval.getIndividual(), instant.getIndividual());
			addAxiom(intervalHasStartAxiom);
		}
		else {
			intervalStart.put(interval, instant);
		}
		
	}

	@Override
	public void visit(IntervalEndAssertionAxiom axiom) {
		
		if(!getScheme().hasIntervalsAsIndividuals()) {
			throw new UnsupportedOperationException("Not implemented yet.");
		}

		TimeInterval interval = axiom.getInterval();
		TimeInstant instant = axiom.getInstant();

		if(getScheme().hasInstantsAsIndividuals()) {
			OWLDataFactory factory = getOWLDataFactory();
			
			OWLObjectProperty intervalEndProperty = scheme.getIntervalEndInstantProperty(factory);
			OWLObjectPropertyAssertionAxiom intervalHasEndAxiom = 
					factory.getOWLObjectPropertyAssertionAxiom(intervalEndProperty, 
					interval.getIndividual(), instant.getIndividual());
			addAxiom(intervalHasEndAxiom);
		}
		else {
			intervalEnd.put(interval, instant);
		}
	}

	@Override
	public void visit(InstantTimeAssertionAxiom axiom) {
		
		OWLDataFactory factory = getOWLDataFactory();

		TimeInstant instant = axiom.getInstant();
		DateTime time = axiom.getTime();
		DateTimeFormatter fmt = ISODateTimeFormat.dateTime();
		OWLLiteral literal = getOWLDataFactory().getOWLLiteral(fmt.print(time), OWL2Datatype.XSD_DATE_TIME);

		if(getScheme().hasInstantsAsIndividuals()) {
			OWLDataProperty instantTimeProperty = getScheme().getInstantHasTimeLiteralProperty(factory);
			OWLDataPropertyAssertionAxiom instantHasTimeAxiom =
					getOWLDataFactory().getOWLDataPropertyAssertionAxiom(
							instantTimeProperty, instant.getIndividual(), literal);
			addAxiom(instantHasTimeAxiom);
		}
		else {
			instantTimeLiterals.put(instant, literal);
		}
	}
	
	@Override
	public final OWLOntology createOntology(IRI targetOntologyIRI) throws OWLOntologyCreationException {
		OWLOntology targetOntology = ontologyManager.createOntology(targetOntologyIRI);
		performPostProcessing();
		ontologyManager.addAxioms(targetOntology, axiomsToWrite);
		return targetOntology;
	}
	
	protected void performPostProcessing() {
		OWLDataFactory factory = getOWLDataFactory();
		
		if(!scheme.hasInstantsAsIndividuals()) {
			OWLDataProperty startProperty = getScheme().getIntervalStartLiteralProperty(factory);
			OWLDataProperty endProperty = getScheme().getIntervalEndLiteralProperty(factory);
			
			// Set interval starts to time literals directly.
			for(TimeInterval interval : intervalStart.keySet()) {
				OWLIndividual intervalIndividual = interval.getIndividual();
				for(TimeInstant instant: intervalStart.get(interval)) {
					for(OWLLiteral literal: instantTimeLiterals.get(instant)) {
						OWLDataPropertyAssertionAxiom startAssertion =
								factory.getOWLDataPropertyAssertionAxiom(
										startProperty, intervalIndividual, literal);
						addAxiom(startAssertion);
					}
				}
			}
			
			// Set interval ends to time literals directly.
			for(TimeInterval interval : intervalEnd.keySet()) {
				OWLIndividual intervalIndividual = interval.getIndividual();
				for(TimeInstant instant: intervalEnd.get(interval)) {
					for(OWLLiteral literal: instantTimeLiterals.get(instant)) {
						OWLDataPropertyAssertionAxiom endAssertion =
								factory.getOWLDataPropertyAssertionAxiom(
										endProperty, intervalIndividual, literal);
						addAxiom(endAssertion);
					}
				}
			}
		}
	}
	
	protected void addAxiom(OWLAxiom axiom) {
		axiomsToWrite.add(axiom);
	}
	
	protected void addAxioms(Collection<? extends OWLAxiom> axioms) {
		axiomsToWrite.addAll(axioms);
	}
	
	protected OWLDataFactory getOWLDataFactory() {
		return ontologyManager.getOWLDataFactory();
	}
	
	protected abstract class TemporalEntityDeclarationHandler implements TemporalEntityVisitor {
		
		@Override
		public void visit(TimeInstant instant) {
			
			// Skip if representation scheme does not feature time instants as individuals.
			// warning?
			if(!getScheme().hasInstantsAsIndividuals()) {
				return;
			}
			
			OWLDataFactory factory = getOWLDataFactory();
			OWLIndividual individual = instant.getIndividual();
			OWLClass instantClass = getScheme().getInstantClass(factory);
			OWLClassAssertionAxiom ax = 
					factory.getOWLClassAssertionAxiom(instantClass, individual);
			addAxiom(ax);
		}

		@Override
		public void visit(TimeInterval interval) {

			// Skip if representation scheme does not feature time intervals as individuals.
			// warning?
			if(!getScheme().hasIntervalsAsIndividuals()) {
				return;
			}
			
			OWLDataFactory factory = getOWLDataFactory();
			OWLIndividual individual = interval.getIndividual();
			OWLClass intervalClass = getScheme().getIntervalClass(factory);
			OWLClassAssertionAxiom ax = 
					getOWLDataFactory().getOWLClassAssertionAxiom(intervalClass, individual);
			addAxiom(ax);
		}
	}

}
