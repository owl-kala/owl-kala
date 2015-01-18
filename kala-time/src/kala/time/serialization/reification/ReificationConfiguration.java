package kala.time.serialization.reification;

import java.util.Set;

import org.semanticweb.owlapi.model.IRI;

public interface ReificationConfiguration {

	public Set<IRI> getFluentObjectPropertyIRIs();

	public Set<IRI> getFluentDataPropertyIRIs();

	public IRI getFluentObjectPropertySubjectRelationName(IRI property);

	public SubjectRelationDirection getFluentObjectPropertySubjectRelationDirection(
			IRI property);

	public IRI getFluentObjectPropertyObjectRelationName(IRI property);

	public ObjectRelationDirection getFluentObjectPropertyObjectRelationDirection(
			IRI property);

	public IRI getFluentDataPropertySubjectRelationName(IRI property);

	public SubjectRelationDirection getFluentDataPropertySubjectRelationDirection(
			IRI property);

	public IRI getFluentDataPropertyDataRelationName(IRI property);

}
