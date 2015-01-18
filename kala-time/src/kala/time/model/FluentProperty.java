package kala.time.model;

import org.semanticweb.owlapi.model.OWLProperty;
import org.semanticweb.owlapi.model.OWLPropertyRange;

public interface FluentProperty<R extends OWLPropertyRange> extends TemporalEntity {
	
	public OWLProperty<R,?> getProperty();

}
