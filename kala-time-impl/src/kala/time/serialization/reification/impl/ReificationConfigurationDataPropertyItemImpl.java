package kala.time.serialization.reification.impl;

import kala.time.serialization.reification.IRINamePattern;
import kala.time.serialization.reification.SubjectRelationDirection;

public class ReificationConfigurationDataPropertyItemImpl extends
		ReificationConfigurationPropertyItemImpl {
	
	public final static ReificationConfigurationDataPropertyItemImpl DEFAULTS =
			new ReificationConfigurationDataPropertyItemImpl(null, null, null);
	
	private final IRINamePattern dataRelationNamePattern;
	
	public ReificationConfigurationDataPropertyItemImpl(
			IRINamePattern subjectRelationNamePattern, 
			SubjectRelationDirection subjectRelationDirection,
			IRINamePattern dataRelationNamePattern) {
		super(subjectRelationNamePattern, subjectRelationDirection);
		this.dataRelationNamePattern = dataRelationNamePattern;
	}
	
	public IRINamePattern getDataRelationNamePattern() {
		return dataRelationNamePattern;
	}

}
