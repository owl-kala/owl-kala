package kala.time.model;

import org.semanticweb.owlapi.model.OWLClassExpression;

public interface FluentDataPropertyDomainAxiom extends TemporalAxiom {

	public FluentDataProperty getProperty();
	
	public OWLClassExpression getClassExpression();
	
}
