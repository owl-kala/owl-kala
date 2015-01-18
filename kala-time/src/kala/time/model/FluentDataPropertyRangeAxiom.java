package kala.time.model;

import org.semanticweb.owlapi.model.OWLDataRange;

public interface FluentDataPropertyRangeAxiom extends TemporalAxiom {
	
	public FluentDataProperty getProperty();
	
	public OWLDataRange getRange();

}
