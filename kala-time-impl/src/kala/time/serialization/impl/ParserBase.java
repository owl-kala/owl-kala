package kala.time.serialization.impl;

import kala.time.core.TemporalDataFactory;
import kala.time.core.TemporalOntologyManager;
import kala.time.serialization.Parser;
import kala.time.serialization.RepresentationScheme;

public abstract class ParserBase implements Parser {
	
	private final TemporalOntologyManager ontologyManager;
	private final RepresentationScheme scheme;
	
	protected ParserBase(TemporalOntologyManager ontologyManager, RepresentationScheme scheme) {
		this.ontologyManager = ontologyManager;
		this.scheme = scheme;
	}
	
	@Override
	public TemporalOntologyManager getOntologyManager() {
		return ontologyManager;
	}

	@Override
	public RepresentationScheme getScheme() {
		return scheme;
	}
	
	protected TemporalDataFactory getDataFactory() {
		return ontologyManager.getTemporalDataFactory();
	}
	
}
