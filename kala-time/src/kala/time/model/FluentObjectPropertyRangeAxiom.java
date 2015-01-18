package kala.time.model;

import org.semanticweb.owlapi.model.OWLClassExpression;

public interface FluentObjectPropertyRangeAxiom extends TemporalAxiom {

	public FluentObjectProperty getProperty();
	
	public OWLClassExpression getClassExpression();
	
}
