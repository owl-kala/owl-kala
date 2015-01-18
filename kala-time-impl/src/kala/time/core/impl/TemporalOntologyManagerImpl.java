package kala.time.core.impl;

import java.util.Collection;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

import kala.time.core.TemporalDataFactory;
import kala.time.core.TemporalOntology;
import kala.time.core.TemporalOntologyManager;
import kala.time.model.TemporalAxiom;

public class TemporalOntologyManagerImpl extends ForwardingOWLOntologyManager
		implements TemporalOntologyManager {
	
	private final TemporalDataFactory factory;
	
	public TemporalOntologyManagerImpl(OWLOntologyManager delegate) {
		super(delegate);
		this.factory = new TemporalDataFactoryImpl(delegate.getOWLDataFactory());
	}
	
	@Override
	public TemporalOntology createTemporalOntology() throws OWLOntologyCreationException {
		OWLOntology ontology = createOntology();
		return createTemporalOntology(ontology);
	}

	@Override
	public TemporalOntology createTemporalOntology(OWLOntology ontology) {
		return new TemporalOntologyImpl(ontology);
	}
	
	@Override
	public TemporalDataFactory getTemporalDataFactory() {
		return factory;
	}
	
	@Override
	public void addTemporalAxiom(TemporalOntology ontology, TemporalAxiom axiom) {
		ontology.addTemporalAxiom(axiom);
	}
	
	@Override
	public void addTemporalAxioms(TemporalOntology ontology, 
			Collection<? extends TemporalAxiom> axioms) {
		ontology.addTemporalAxioms(axioms);
	}

}
