package kala.time.serialization.reification.impl;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import kala.time.serialization.reification.ReificationConfiguration;
import kala.time.serialization.reification.ReificationRepresentationScheme;

public class ReificationRepresentationSchemeImpl implements ReificationRepresentationScheme {
	
	private final ReificationConfiguration configuration;
	
	public ReificationRepresentationSchemeImpl(ReificationConfiguration configuration) {
		this.configuration = configuration;
	}

	@Override
	public boolean hasInstantsAsIndividuals() {
		return true;
	}

	@Override
	public OWLClass getInstantClass(OWLDataFactory factory) {
		IRI instantIRI = IRI.create("#instant");
		return factory.getOWLClass(instantIRI);
	}

	@Override
	public boolean hasIntervalsAsIndividuals() {
		return true;
	}

	@Override
	public OWLClass getIntervalClass(OWLDataFactory factory) {
		IRI intervalIRI = IRI.create("#interval");
		return factory.getOWLClass(intervalIRI);
	}

	@Override
	public OWLObjectProperty getIntervalProperty(OWLDataFactory factory) {
		IRI hasIntervalIRI = IRI.create("#holds");
		return factory.getOWLObjectProperty(hasIntervalIRI);
	}

	@Override
	public OWLObjectProperty getIntervalStartInstantProperty(
			OWLDataFactory factory) {
		IRI hasStart = IRI.create("#start");
		return factory.getOWLObjectProperty(hasStart);
	}

	@Override
	public OWLDataProperty getIntervalStartLiteralProperty(
			OWLDataFactory factory) {
		throw new UnsupportedOperationException("Not applicable.");
	}

	@Override
	public OWLObjectProperty getIntervalEndInstantProperty(
			OWLDataFactory factory) {
		IRI hasEnd = IRI.create("#end");
		return factory.getOWLObjectProperty(hasEnd);
	}

	@Override
	public OWLDataProperty getIntervalEndLiteralProperty(
			OWLDataFactory factory) {
		throw new UnsupportedOperationException("Not applicable.");
	}

	@Override
	public ReificationConfiguration getConfiguration() {
		return configuration;
	}

	@Override
	public OWLDataProperty getInstantHasTimeLiteralProperty(
			OWLDataFactory factory) {
		IRI hasTime = IRI.create("#time");
		return factory.getOWLDataProperty(hasTime);
	}

}
