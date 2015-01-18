package kala.time.serialization.fluents.impl;

import java.util.HashMap;
import java.util.Map;

import kala.time.model.FluentDataProperty;
import kala.time.model.FluentDataPropertyAssertionAxiom;
import kala.time.model.FluentDataPropertyDomainAxiom;
import kala.time.model.FluentDataPropertyRangeAxiom;
import kala.time.model.FluentObjectProperty;
import kala.time.model.FluentObjectPropertyAssertionAxiom;
import kala.time.model.FluentObjectPropertyDomainAxiom;
import kala.time.model.FluentObjectPropertyRangeAxiom;
import kala.time.model.TemporalEntity;
import kala.time.model.TemporalEntityDeclarationAxiom;
import kala.time.model.TimeInterval;
import kala.time.serialization.fluents.FluentsRepresentationScheme;
import kala.time.serialization.fluents.FluentsSerializer;
import kala.time.serialization.impl.SerializerBase;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class FluentsSerializerImpl extends SerializerBase 
		implements FluentsSerializer {
	
	private final Map<OWLIndividual, Map<TimeInterval, OWLIndividual>> timesliceToIndividual;

	public FluentsSerializerImpl(OWLOntologyManager ontologyManager,
			FluentsRepresentationScheme scheme) {
		super(ontologyManager, scheme);
		this.timesliceToIndividual = 
				new HashMap<OWLIndividual, Map<TimeInterval, OWLIndividual>>();
	}
	
	@Override
	public FluentsRepresentationScheme getScheme() {
		return (FluentsRepresentationScheme) super.getScheme();
	}

	@Override
	public void visit(TemporalEntityDeclarationAxiom axiom) {
		TemporalEntity entity = axiom.getEntity();
		entity.accept(new SerializerBase.TemporalEntityDeclarationHandler() {

			@Override
			public void visit(FluentObjectProperty property) {
				OWLDataFactory factory = getOWLDataFactory();
				OWLObjectProperty op = 
						factory.getOWLObjectProperty(property.getProperty().getIRI());
				
				// Declare the property.
				OWLDeclarationAxiom opDeclAxiom =
						factory.getOWLDeclarationAxiom(op);
				addAxiom(opDeclAxiom);
				
				// The property's domain and range are timeslices.
				OWLClass timesliceClass = getScheme().getTimesliceClass(factory);
				OWLObjectPropertyDomainAxiom opDomAxiom =
						factory.getOWLObjectPropertyDomainAxiom(op, timesliceClass);
				addAxiom(opDomAxiom);
				OWLObjectPropertyRangeAxiom opRngAxiom =
						factory.getOWLObjectPropertyRangeAxiom(op, timesliceClass);
				addAxiom(opRngAxiom);
			}

			@Override
			public void visit(FluentDataProperty property) {
				OWLDataFactory factory = getOWLDataFactory();
				OWLDataProperty dp = 
						factory.getOWLDataProperty(property.getProperty().getIRI());
				
				// Declare the property.
				OWLDeclarationAxiom dpDeclAxiom =
						factory.getOWLDeclarationAxiom(dp);
				addAxiom(dpDeclAxiom);
				
				// The property's domain is a timeslice.
				OWLClass timesliceClass = getScheme().getTimesliceClass(factory);
				OWLDataPropertyDomainAxiom dpDomAxiom =
						factory.getOWLDataPropertyDomainAxiom(dp, timesliceClass);
				addAxiom(dpDomAxiom);
			}
			
		});
	}

	@Override
	public void visit(FluentObjectPropertyDomainAxiom axiom) {
		OWLDataFactory factory = getOWLDataFactory();
		FluentObjectProperty fop = axiom.getProperty();
		OWLObjectProperty op = 
				factory.getOWLObjectProperty(fop.getProperty().getIRI());
		
		OWLObjectProperty timesliceRel = getScheme().getTimesliceRelation(factory);
		switch(getScheme().getTimesliceRelationDirection()) {
		case INDIVIDUAL_TO_TIMESLICE:
			throw new UnsupportedOperationException("Not implemented yet.");
		case TIMESLICE_TO_INDIVIDUAL:
			
			// Create restriction on object property domain.
			OWLObjectSomeValuesFrom domainRestriction =
				factory.getOWLObjectSomeValuesFrom(timesliceRel, axiom.getClassExpression());
			OWLObjectPropertyDomainAxiom domainAxiom =
				factory.getOWLObjectPropertyDomainAxiom(op, domainRestriction);
			addAxiom(domainAxiom);
			break;
		}
	}

	@Override
	public void visit(FluentObjectPropertyRangeAxiom axiom) {
		OWLDataFactory factory = getOWLDataFactory();
		FluentObjectProperty fop = axiom.getProperty();
		OWLObjectProperty op = 
				factory.getOWLObjectProperty(fop.getProperty().getIRI());
		
		OWLObjectProperty timesliceRel = getScheme().getTimesliceRelation(factory);
		switch(getScheme().getTimesliceRelationDirection()) {
		case INDIVIDUAL_TO_TIMESLICE:
			throw new UnsupportedOperationException("Not implemented yet.");
		case TIMESLICE_TO_INDIVIDUAL:
			
			// Create restriction on object property range.
			OWLObjectSomeValuesFrom rangeRestriction =
				factory.getOWLObjectSomeValuesFrom(timesliceRel, axiom.getClassExpression());
			OWLObjectPropertyRangeAxiom rangeAxiom =
				factory.getOWLObjectPropertyRangeAxiom(op, rangeRestriction);
			addAxiom(rangeAxiom);
			break;
		}
	}

	@Override
	public void visit(FluentDataPropertyDomainAxiom axiom) {
		OWLDataFactory factory = getOWLDataFactory();
		FluentDataProperty fdp = axiom.getProperty();
		OWLDataProperty dp = 
				factory.getOWLDataProperty(fdp.getProperty().getIRI());
		
		OWLObjectProperty timesliceRel = getScheme().getTimesliceRelation(factory);
		switch(getScheme().getTimesliceRelationDirection()) {
		case INDIVIDUAL_TO_TIMESLICE:
			throw new UnsupportedOperationException("Not implemented yet.");
		case TIMESLICE_TO_INDIVIDUAL:
			
			// Create restriction on data property domain.
			OWLObjectSomeValuesFrom domainRestriction =
				factory.getOWLObjectSomeValuesFrom(timesliceRel, axiom.getClassExpression());
			OWLDataPropertyDomainAxiom domainAxiom =
				factory.getOWLDataPropertyDomainAxiom(dp, domainRestriction);
			addAxiom(domainAxiom);
			break;
		}
	}

	@Override
	public void visit(FluentDataPropertyRangeAxiom axiom) {
		OWLDataFactory factory = getOWLDataFactory();
		FluentDataProperty fdp = axiom.getProperty();
		OWLDataProperty dp = 
				factory.getOWLDataProperty(fdp.getProperty().getIRI());
		OWLDataPropertyRangeAxiom rangeAxiom =
				factory.getOWLDataPropertyRangeAxiom(dp, axiom.getRange());
		addAxiom(rangeAxiom);
	}

	@Override
	public void visit(FluentObjectPropertyAssertionAxiom axiom) {
		OWLIndividual subject = axiom.getSubject();
		FluentObjectProperty fluentProperty = axiom.getProperty();
		OWLIndividual object = axiom.getObject();
		TimeInterval interval = axiom.getInterval();
		
		OWLIndividual subjTimeslice = declareTimesliceFor(subject, interval);
		OWLIndividual objTimeslice = declareTimesliceFor(object, interval);
		OWLObjectProperty property = fluentProperty.getProperty();
		OWLObjectPropertyAssertionAxiom assertionAxiom =
				getOWLDataFactory().getOWLObjectPropertyAssertionAxiom(property, subjTimeslice, objTimeslice);
		addAxiom(assertionAxiom);
	}

	@Override
	public void visit(FluentDataPropertyAssertionAxiom axiom) {
		OWLIndividual subject = axiom.getSubject();
		FluentDataProperty fluentProperty = axiom.getProperty();
		OWLLiteral object = axiom.getObject();
		TimeInterval interval = axiom.getInterval();

		OWLIndividual subjTimeslice = declareTimesliceFor(subject, interval);
		OWLDataProperty property = fluentProperty.getProperty();
		OWLDataPropertyAssertionAxiom assertionAxiom =
				getOWLDataFactory().getOWLDataPropertyAssertionAxiom(property, subjTimeslice, object);
		addAxiom(assertionAxiom);
	}
	
	private OWLIndividual declareTimesliceFor(OWLIndividual individual, TimeInterval interval) {
		OWLDataFactory factory = getOWLDataFactory();
		OWLIndividual timesliceIndividual = getTimesliceIndividualFor(individual, interval);
		
		// Assert timeslice individual as timeslice.
		OWLClass timesliceClass = getScheme().getTimesliceClass(factory);
		OWLClassAssertionAxiom tsClassAssertionAxiom = 
				factory.getOWLClassAssertionAxiom(timesliceClass, timesliceIndividual);
		addAxiom(tsClassAssertionAxiom);
		
		// Set up timeslice relation.
		OWLObjectProperty timesliceRel = getScheme().getTimesliceRelation(factory);
		switch(getScheme().getTimesliceRelationDirection()) {
		case TIMESLICE_TO_INDIVIDUAL:
			OWLObjectPropertyAssertionAxiom timesliceRelAssAx_T_I =
				factory.getOWLObjectPropertyAssertionAxiom(timesliceRel, timesliceIndividual, individual);
			addAxiom(timesliceRelAssAx_T_I);
			break;
		case INDIVIDUAL_TO_TIMESLICE:
			OWLObjectPropertyAssertionAxiom timesliceRelAssAx_I_T =
				factory.getOWLObjectPropertyAssertionAxiom(timesliceRel, individual, timesliceIndividual);
			addAxiom(timesliceRelAssAx_I_T);
			break;
		}
		
		// Set up interval relation.
		OWLObjectProperty hasIntervalProperty = getScheme().getIntervalProperty(factory);
		OWLObjectPropertyAssertionAxiom intervalAssAxiom =
				factory.getOWLObjectPropertyAssertionAxiom(hasIntervalProperty, timesliceIndividual, interval.getIndividual());
		addAxiom(intervalAssAxiom);
		
		return timesliceIndividual;
	}
	
	private OWLIndividual getTimesliceIndividualFor(
			OWLIndividual individual, TimeInterval interval) {
		
		// First, search by individual and obtain an interval -> timeslice map.
		Map<TimeInterval, OWLIndividual> intervalToTimesliceIndividual 
			= timesliceToIndividual.get(individual);
		if(intervalToTimesliceIndividual == null) {
			intervalToTimesliceIndividual = new HashMap<TimeInterval, OWLIndividual>();
			timesliceToIndividual.put(individual, intervalToTimesliceIndividual);
		}
		
		// Then, search by interval and obtain the timesliceIndividual.
		OWLIndividual timesliceIndividual = intervalToTimesliceIndividual.get(interval);
		if(timesliceIndividual == null) {
			timesliceIndividual = getOWLDataFactory().getOWLAnonymousIndividual();
			intervalToTimesliceIndividual.put(interval, timesliceIndividual);
		}
		
		return timesliceIndividual;
	}

}
