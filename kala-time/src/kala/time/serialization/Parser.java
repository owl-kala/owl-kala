package kala.time.serialization;

import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import kala.time.core.TemporalOntology;
import kala.time.core.TemporalOntologyManager;

public interface Parser {
	
	public TemporalOntologyManager getOntologyManager();

	public RepresentationScheme getScheme();
	
	public void read(OWLOntology ontology);
	
	public TemporalOntology build(TemporalOntologyManager ontologyManager) throws OWLOntologyCreationException;


}
