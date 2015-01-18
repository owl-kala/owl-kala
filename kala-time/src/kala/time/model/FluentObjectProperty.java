package kala.time.model;

import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLObjectProperty;

public interface FluentObjectProperty extends FluentProperty<OWLClassExpression> {

	@Override
	public OWLObjectProperty getProperty();
	
}
