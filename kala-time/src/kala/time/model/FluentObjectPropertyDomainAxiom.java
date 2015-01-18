package kala.time.model;

import org.semanticweb.owlapi.model.OWLClassExpression;

public interface FluentObjectPropertyDomainAxiom extends TemporalAxiom {

	public FluentObjectProperty getProperty();
	
	public OWLClassExpression getClassExpression();
	
}
