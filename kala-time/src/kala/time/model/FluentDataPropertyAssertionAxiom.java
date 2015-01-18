package kala.time.model;

import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;

public interface FluentDataPropertyAssertionAxiom extends TemporalAxiom {
	
	public OWLIndividual getSubject();
	
	public FluentDataProperty getProperty();
	
	public OWLLiteral getObject();
	
	public TimeInterval getInterval();

}
