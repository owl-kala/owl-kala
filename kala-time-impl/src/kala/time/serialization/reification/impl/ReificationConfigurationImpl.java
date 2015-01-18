package kala.time.serialization.reification.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import kala.time.serialization.reification.IRINamePattern;
import kala.time.serialization.reification.IRINamePatternAppender;
import kala.time.serialization.reification.ObjectRelationDirection;
import kala.time.serialization.reification.ReificationConfiguration;
import kala.time.serialization.reification.SubjectRelationDirection;

import org.semanticweb.owlapi.model.IRI;

public class ReificationConfigurationImpl implements ReificationConfiguration {
	
	private final Map<IRI, ReificationConfigurationObjectPropertyItemImpl> opConf;
	private final Map<IRI, ReificationConfigurationDataPropertyItemImpl> dpConf;
	
	private final IRINamePattern defaultSubjectRelationNamePattern;
	private final SubjectRelationDirection defaultSubjectRelationDirection;
	private final IRINamePattern defaultObjectRelationNamePattern;
	private final ObjectRelationDirection defaultObjectRelationDirection;
	private final IRINamePattern defaultDataRelationNamePattern;
	
	public ReificationConfigurationImpl() {
		this(new IRINamePatternAppender("_subRel"), SubjectRelationDirection.REIF_TO_SUBJECT,
				new IRINamePatternAppender("_obRel"), ObjectRelationDirection.REIF_TO_OBJECT,
				new IRINamePatternAppender("_data"));
	}
	
	public ReificationConfigurationImpl(
			IRINamePattern defaultSubjectRelationNamePattern,
			SubjectRelationDirection defaultSubjectRelationDirection,
			IRINamePattern defaultObjectRelationNamePattern,
			ObjectRelationDirection defaultObjectRelationDirection,
			IRINamePattern defaultDataRelationNamePattern) {
		opConf = new HashMap<IRI, ReificationConfigurationObjectPropertyItemImpl>();
		dpConf = new HashMap<IRI, ReificationConfigurationDataPropertyItemImpl>();
		this.defaultSubjectRelationNamePattern = defaultSubjectRelationNamePattern;
		this.defaultSubjectRelationDirection = defaultSubjectRelationDirection; 
		this.defaultObjectRelationNamePattern = defaultObjectRelationNamePattern;
		this.defaultObjectRelationDirection = defaultObjectRelationDirection;
		this.defaultDataRelationNamePattern = defaultDataRelationNamePattern;
	}
	
	@Override
	public Set<IRI> getFluentObjectPropertyIRIs() {
		return opConf.keySet();
	}

	@Override
	public Set<IRI> getFluentDataPropertyIRIs() {
		return opConf.keySet();
	}

	@Override
	public IRI getFluentObjectPropertySubjectRelationName(IRI property) {
		return getFluentObjectPropertySubjectRelationNamePattern(property).getName(property);
	}
	
	@Override
	public SubjectRelationDirection getFluentObjectPropertySubjectRelationDirection(IRI property) {
		SubjectRelationDirection result = opConf.containsKey(property) 
				? opConf.get(property).getSubjectRelationDirection() : null;
		return result != null ? result : defaultSubjectRelationDirection;
	}

	@Override
	public IRI getFluentObjectPropertyObjectRelationName(IRI property) {
		return getFluentObjectPropertyObjectRelationNamePattern(property).getName(property);
	}

	@Override
	public ObjectRelationDirection getFluentObjectPropertyObjectRelationDirection(IRI property) {
		ObjectRelationDirection result = opConf.containsKey(property) 
				? opConf.get(property).getObjectRelationDirection() : null;
		return result != null ? result : defaultObjectRelationDirection;
	}

	@Override
	public IRI getFluentDataPropertySubjectRelationName(IRI property) {
		return getFluentDataPropertySubjectRelationNamePattern(property).getName(property);
	}

	@Override
	public SubjectRelationDirection getFluentDataPropertySubjectRelationDirection(IRI property) {
		SubjectRelationDirection result = dpConf.containsKey(property) 
				? dpConf.get(property).getSubjectRelationDirection() : null;
		return result != null ? result : defaultSubjectRelationDirection;
	}

	@Override
	public IRI getFluentDataPropertyDataRelationName(IRI property) {
		return getFluentDataPropertyDataRelationNamePattern(property).getName(property);
	}

	public void addFluentObjectProperty(IRI fluentObjectPropertyIRI) {
		addFluentObjectProperty(fluentObjectPropertyIRI, 
				ReificationConfigurationObjectPropertyItemImpl.DEFAULTS);
	}
	
	public void addFluentObjectProperty(
			IRI fluentObjectPropertyIRI, ReificationConfigurationObjectPropertyItemImpl configItem) {
		opConf.put(fluentObjectPropertyIRI, configItem);
	}
	
	public void addFluentDataProperty(IRI fluentDataPropertyIRI) {
		addFluentDataProperty(fluentDataPropertyIRI, 
				ReificationConfigurationDataPropertyItemImpl.DEFAULTS);
	}
	
	public void addFluentDataProperty(
			IRI fluentDataPropertyIRI, ReificationConfigurationDataPropertyItemImpl configItem) {
		dpConf.put(fluentDataPropertyIRI, configItem);
	}
	
	private IRINamePattern getFluentObjectPropertySubjectRelationNamePattern(IRI property) {
		IRINamePattern result = opConf.containsKey(property) 
				? opConf.get(property).getSubjectRelationNamePattern() : null;
		return result != null ? result : defaultSubjectRelationNamePattern;
	}
	

	private IRINamePattern getFluentObjectPropertyObjectRelationNamePattern(IRI property) {
		IRINamePattern result = opConf.containsKey(property) 
				? opConf.get(property).getObjectRelationNamePattern() : null;
		return result != null ? result : defaultObjectRelationNamePattern;
	}

	private IRINamePattern getFluentDataPropertySubjectRelationNamePattern(IRI property) {
		IRINamePattern result = dpConf.containsKey(property) 
				? dpConf.get(property).getSubjectRelationNamePattern() : null;
		return result != null ? result : defaultSubjectRelationNamePattern;
	}

	private IRINamePattern getFluentDataPropertyDataRelationNamePattern(IRI property) {
		IRINamePattern result = dpConf.containsKey(property) 
				? dpConf.get(property).getDataRelationNamePattern() : null;
		return result != null ? result : defaultDataRelationNamePattern;
	}

}
