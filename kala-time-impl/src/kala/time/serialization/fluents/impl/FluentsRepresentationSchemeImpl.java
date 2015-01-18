package kala.time.serialization.fluents.impl;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import kala.time.serialization.fluents.FluentsRepresentationScheme;
import kala.time.serialization.fluents.TimesliceRelationDirection;

public class FluentsRepresentationSchemeImpl implements
		FluentsRepresentationScheme {

	@Override
	public boolean hasInstantsAsIndividuals() {
		return true;
	}

	@Override
	public OWLClass getInstantClass(OWLDataFactory factory) {
		return factory.getOWLClass(IRI.create("#instant"));
	}

	@Override
	public boolean hasIntervalsAsIndividuals() {
		return true;
	}

	@Override
	public OWLClass getIntervalClass(OWLDataFactory factory) {
		return factory.getOWLClass(IRI.create("#interval"));
	}

	@Override
	public OWLObjectProperty getIntervalProperty(OWLDataFactory factory) {
		return factory.getOWLObjectProperty(IRI.create("#time"));
	}

	@Override
	public OWLObjectProperty getIntervalStartInstantProperty(
			OWLDataFactory factory) {
		return factory.getOWLObjectProperty(IRI.create("#startInstant"));
	}

	@Override
	public OWLDataProperty getIntervalStartLiteralProperty(
			OWLDataFactory factory) {
		return factory.getOWLDataProperty(IRI.create("#start"));
	}

	@Override
	public OWLObjectProperty getIntervalEndInstantProperty(
			OWLDataFactory factory) {
		return factory.getOWLObjectProperty(IRI.create("#endInstant"));
	}

	@Override
	public OWLDataProperty getIntervalEndLiteralProperty(
			OWLDataFactory factory) {
		return factory.getOWLDataProperty(IRI.create("#finish"));
	}

	@Override
	public OWLDataProperty getInstantHasTimeLiteralProperty(
			OWLDataFactory factory) {
		return factory.getOWLDataProperty(IRI.create("#hasTime"));
	}

	@Override
	public OWLClass getTimesliceClass(OWLDataFactory factory) {
		return factory.getOWLClass(IRI.create("#timeSlice"));
	}

	@Override
	public TimesliceRelationDirection getTimesliceRelationDirection() {
		return TimesliceRelationDirection.TIMESLICE_TO_INDIVIDUAL;
	}
	
	@Override
	public OWLObjectProperty getTimesliceRelation(OWLDataFactory factory) {
		return factory.getOWLObjectProperty(IRI.create("#timesliceOf"));
	}

}
