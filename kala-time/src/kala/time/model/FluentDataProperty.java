package kala.time.model;

import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataRange;

public interface FluentDataProperty extends FluentProperty<OWLDataRange> {
	
	@Override
	public OWLDataProperty getProperty();

}
