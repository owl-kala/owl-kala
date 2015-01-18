package kala.time.serialization.reification.impl;

import kala.time.serialization.reification.IRINamePattern;
import kala.time.serialization.reification.SubjectRelationDirection;

public abstract class ReificationConfigurationPropertyItemImpl {
	
	private final IRINamePattern subjectRelationNamePattern;
	private final SubjectRelationDirection subjectRelationDirection;
	
	protected ReificationConfigurationPropertyItemImpl(
			IRINamePattern subjectRelationNamePattern, 
			SubjectRelationDirection subjectRelationDirection) {
		this.subjectRelationNamePattern = subjectRelationNamePattern;
		this.subjectRelationDirection = subjectRelationDirection;
	}
	
	public IRINamePattern getSubjectRelationNamePattern() {
		return subjectRelationNamePattern;
	}
	
	public SubjectRelationDirection getSubjectRelationDirection() {
		return subjectRelationDirection;
	}

}
