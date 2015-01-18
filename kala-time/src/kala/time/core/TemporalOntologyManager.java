package kala.time.core;

import java.util.Collection;

import kala.time.model.TemporalAxiom;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public interface TemporalOntologyManager extends OWLOntologyManager {
	
	public TemporalOntology createTemporalOntology() throws OWLOntologyCreationException;
	
	public TemporalOntology createTemporalOntology(OWLOntology ontology);
	
	public TemporalDataFactory getTemporalDataFactory();

	public void addTemporalAxiom(TemporalOntology ontology, TemporalAxiom axiom);
	
	public void addTemporalAxioms(TemporalOntology ontology, Collection<? extends TemporalAxiom> axioms);
	
}
