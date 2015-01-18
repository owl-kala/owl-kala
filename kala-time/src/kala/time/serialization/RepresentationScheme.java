package kala.time.serialization;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;

public interface RepresentationScheme {
	
	public boolean hasInstantsAsIndividuals();
	
	public OWLClass getInstantClass(OWLDataFactory factory);
	
	public boolean hasIntervalsAsIndividuals();
	
	public OWLClass getIntervalClass(OWLDataFactory factory);
	
	public OWLObjectProperty getIntervalProperty(OWLDataFactory factory);
	
	public OWLObjectProperty getIntervalStartInstantProperty(OWLDataFactory factory);
	
	public OWLDataProperty getIntervalStartLiteralProperty(OWLDataFactory factory);
	
	public OWLObjectProperty getIntervalEndInstantProperty(OWLDataFactory factory);
	
	public OWLDataProperty getIntervalEndLiteralProperty(OWLDataFactory factory);
	
	public OWLDataProperty getInstantHasTimeLiteralProperty(OWLDataFactory factory);

}
