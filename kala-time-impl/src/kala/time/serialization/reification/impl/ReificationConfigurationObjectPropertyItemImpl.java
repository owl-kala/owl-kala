package kala.time.serialization.reification.impl;

import kala.time.serialization.reification.IRINamePattern;
import kala.time.serialization.reification.ObjectRelationDirection;
import kala.time.serialization.reification.SubjectRelationDirection;

public class ReificationConfigurationObjectPropertyItemImpl extends
		ReificationConfigurationPropertyItemImpl {
	
	public final static ReificationConfigurationObjectPropertyItemImpl DEFAULTS =
			new ReificationConfigurationObjectPropertyItemImpl(null, null, null, null);

	private final IRINamePattern objectRelationNamePattern;
	private final ObjectRelationDirection objectRelationDirection;
	
	public ReificationConfigurationObjectPropertyItemImpl(
			IRINamePattern subjectRelationNamePattern, 
			SubjectRelationDirection subjectRelationDirection,
			IRINamePattern objectRelationNamePattern, 
			ObjectRelationDirection objectRelationDirection) {
		super(subjectRelationNamePattern, subjectRelationDirection);
		this.objectRelationNamePattern = objectRelationNamePattern;
		this.objectRelationDirection = objectRelationDirection;
	}
	
	public IRINamePattern getObjectRelationNamePattern() {
		return objectRelationNamePattern;
	}
	
	public ObjectRelationDirection getObjectRelationDirection() {
		return objectRelationDirection;
	}
	
}
