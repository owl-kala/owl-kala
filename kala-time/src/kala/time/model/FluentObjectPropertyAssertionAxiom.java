package kala.time.model;

import org.semanticweb.owlapi.model.OWLIndividual;

public interface FluentObjectPropertyAssertionAxiom extends TemporalAxiom {

	public FluentObjectProperty getProperty();
	
	public OWLIndividual getSubject();
	
	public OWLIndividual getObject();
	
	public TimeInterval getInterval();
	
}
