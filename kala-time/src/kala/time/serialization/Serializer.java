package kala.time.serialization;

import kala.time.core.TemporalOntologyVisitor;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;

public interface Serializer extends TemporalOntologyVisitor {

	public RepresentationScheme getScheme();

	public OWLOntology createOntology(IRI targetOntologyIRI) throws OWLOntologyCreationException;

}
