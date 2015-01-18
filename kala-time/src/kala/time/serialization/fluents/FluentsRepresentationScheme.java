package kala.time.serialization.fluents;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectProperty;

import kala.time.serialization.RepresentationScheme;

public interface FluentsRepresentationScheme extends RepresentationScheme {

	public OWLClass getTimesliceClass(OWLDataFactory factory);
	
	public TimesliceRelationDirection getTimesliceRelationDirection();

	public OWLObjectProperty getTimesliceRelation(OWLDataFactory factory);

}
