/**
 * 
 */
package kala.time.core.impl;

import java.util.List;
import java.util.Set;

import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.vocab.OWL2Datatype;
import org.semanticweb.owlapi.vocab.OWLFacet;

/**
 * @author sven
 *
 */
public class ForwardingOWLDataFactoryImpl implements OWLDataFactory {
	
	private final OWLDataFactory delegate;
	
	protected ForwardingOWLDataFactoryImpl(OWLDataFactory delegate) {
		this.delegate = delegate;
	}

	/**
	 * @param iri
	 * @param body
	 * @param head
	 * @return
	 * @deprecated
	 * @see org.semanticweb.owlapi.model.SWRLDataFactory#getSWRLRule(org.semanticweb.owlapi.model.IRI, java.util.Set, java.util.Set)
	 */
	public SWRLRule getSWRLRule(IRI iri, Set<? extends SWRLAtom> body,
			Set<? extends SWRLAtom> head) {
		return delegate.getSWRLRule(iri, body, head);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLThing()
	 */
	public OWLClass getOWLThing() {
		return delegate.getOWLThing();
	}

	/**
	 * @param nodeID
	 * @param body
	 * @param head
	 * @return
	 * @deprecated
	 * @see org.semanticweb.owlapi.model.SWRLDataFactory#getSWRLRule(org.semanticweb.owlapi.model.NodeID, java.util.Set, java.util.Set)
	 */
	public SWRLRule getSWRLRule(NodeID nodeID, Set<? extends SWRLAtom> body,
			Set<? extends SWRLAtom> head) {
		return delegate.getSWRLRule(nodeID, body, head);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLNothing()
	 */
	public OWLClass getOWLNothing() {
		return delegate.getOWLNothing();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLTopObjectProperty()
	 */
	public OWLObjectProperty getOWLTopObjectProperty() {
		return delegate.getOWLTopObjectProperty();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLTopDataProperty()
	 */
	public OWLDataProperty getOWLTopDataProperty() {
		return delegate.getOWLTopDataProperty();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLBottomObjectProperty()
	 */
	public OWLObjectProperty getOWLBottomObjectProperty() {
		return delegate.getOWLBottomObjectProperty();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLBottomDataProperty()
	 */
	public OWLDataProperty getOWLBottomDataProperty() {
		return delegate.getOWLBottomDataProperty();
	}

	/**
	 * @param body
	 * @param head
	 * @return
	 * @see org.semanticweb.owlapi.model.SWRLDataFactory#getSWRLRule(java.util.Set, java.util.Set)
	 */
	public SWRLRule getSWRLRule(Set<? extends SWRLAtom> body,
			Set<? extends SWRLAtom> head) {
		return delegate.getSWRLRule(body, head);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getTopDatatype()
	 */
	public OWLDatatype getTopDatatype() {
		return delegate.getTopDatatype();
	}

	/**
	 * @param body
	 * @param head
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.SWRLDataFactory#getSWRLRule(java.util.Set, java.util.Set, java.util.Set)
	 */
	public SWRLRule getSWRLRule(Set<? extends SWRLAtom> body,
			Set<? extends SWRLAtom> head, Set<OWLAnnotation> annotations) {
		return delegate.getSWRLRule(body, head, annotations);
	}

	/**
	 * @param entityType
	 * @param iri
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEntity(org.semanticweb.owlapi.model.EntityType, org.semanticweb.owlapi.model.IRI)
	 */
	public <E extends OWLEntity> E getOWLEntity(EntityType<E> entityType,
			IRI iri) {
		return delegate.getOWLEntity(entityType, iri);
	}

	/**
	 * @param predicate
	 * @param arg
	 * @return
	 * @see org.semanticweb.owlapi.model.SWRLDataFactory#getSWRLClassAtom(org.semanticweb.owlapi.model.OWLClassExpression, org.semanticweb.owlapi.model.SWRLIArgument)
	 */
	public SWRLClassAtom getSWRLClassAtom(OWLClassExpression predicate,
			SWRLIArgument arg) {
		return delegate.getSWRLClassAtom(predicate, arg);
	}

	/**
	 * @param iri
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLClass(org.semanticweb.owlapi.model.IRI)
	 */
	public OWLClass getOWLClass(IRI iri) {
		return delegate.getOWLClass(iri);
	}

	/**
	 * @param abbreviatedIRI
	 * @param prefixManager
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLClass(java.lang.String, org.semanticweb.owlapi.model.PrefixManager)
	 */
	public OWLClass getOWLClass(String abbreviatedIRI,
			PrefixManager prefixManager) {
		return delegate.getOWLClass(abbreviatedIRI, prefixManager);
	}

	/**
	 * @param predicate
	 * @param arg
	 * @return
	 * @see org.semanticweb.owlapi.model.SWRLDataFactory#getSWRLDataRangeAtom(org.semanticweb.owlapi.model.OWLDataRange, org.semanticweb.owlapi.model.SWRLDArgument)
	 */
	public SWRLDataRangeAtom getSWRLDataRangeAtom(OWLDataRange predicate,
			SWRLDArgument arg) {
		return delegate.getSWRLDataRangeAtom(predicate, arg);
	}

	/**
	 * @param property
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.semanticweb.owlapi.model.SWRLDataFactory#getSWRLObjectPropertyAtom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.SWRLIArgument, org.semanticweb.owlapi.model.SWRLIArgument)
	 */
	public SWRLObjectPropertyAtom getSWRLObjectPropertyAtom(
			OWLObjectPropertyExpression property, SWRLIArgument arg0,
			SWRLIArgument arg1) {
		return delegate.getSWRLObjectPropertyAtom(property, arg0, arg1);
	}

	/**
	 * @param property
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.semanticweb.owlapi.model.SWRLDataFactory#getSWRLDataPropertyAtom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.SWRLIArgument, org.semanticweb.owlapi.model.SWRLDArgument)
	 */
	public SWRLDataPropertyAtom getSWRLDataPropertyAtom(
			OWLDataPropertyExpression property, SWRLIArgument arg0,
			SWRLDArgument arg1) {
		return delegate.getSWRLDataPropertyAtom(property, arg0, arg1);
	}

	/**
	 * @param iri
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectProperty(org.semanticweb.owlapi.model.IRI)
	 */
	public OWLObjectProperty getOWLObjectProperty(IRI iri) {
		return delegate.getOWLObjectProperty(iri);
	}

	/**
	 * @param abbreviatedIRI
	 * @param prefixManager
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectProperty(java.lang.String, org.semanticweb.owlapi.model.PrefixManager)
	 */
	public OWLObjectProperty getOWLObjectProperty(String abbreviatedIRI,
			PrefixManager prefixManager) {
		return delegate.getOWLObjectProperty(abbreviatedIRI, prefixManager);
	}

	/**
	 * @param builtInIRI
	 * @param args
	 * @return
	 * @see org.semanticweb.owlapi.model.SWRLDataFactory#getSWRLBuiltInAtom(org.semanticweb.owlapi.model.IRI, java.util.List)
	 */
	public SWRLBuiltInAtom getSWRLBuiltInAtom(IRI builtInIRI,
			List<SWRLDArgument> args) {
		return delegate.getSWRLBuiltInAtom(builtInIRI, args);
	}

	/**
	 * @param var
	 * @return
	 * @see org.semanticweb.owlapi.model.SWRLDataFactory#getSWRLVariable(org.semanticweb.owlapi.model.IRI)
	 */
	public SWRLVariable getSWRLVariable(IRI var) {
		return delegate.getSWRLVariable(var);
	}

	/**
	 * @param individual
	 * @return
	 * @see org.semanticweb.owlapi.model.SWRLDataFactory#getSWRLIndividualArgument(org.semanticweb.owlapi.model.OWLIndividual)
	 */
	public SWRLIndividualArgument getSWRLIndividualArgument(
			OWLIndividual individual) {
		return delegate.getSWRLIndividualArgument(individual);
	}

	/**
	 * @param literal
	 * @return
	 * @see org.semanticweb.owlapi.model.SWRLDataFactory#getSWRLLiteralArgument(org.semanticweb.owlapi.model.OWLLiteral)
	 */
	public SWRLLiteralArgument getSWRLLiteralArgument(OWLLiteral literal) {
		return delegate.getSWRLLiteralArgument(literal);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectInverseOf(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLObjectInverseOf getOWLObjectInverseOf(
			OWLObjectPropertyExpression property) {
		return delegate.getOWLObjectInverseOf(property);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.semanticweb.owlapi.model.SWRLDataFactory#getSWRLSameIndividualAtom(org.semanticweb.owlapi.model.SWRLIArgument, org.semanticweb.owlapi.model.SWRLIArgument)
	 */
	public SWRLSameIndividualAtom getSWRLSameIndividualAtom(SWRLIArgument arg0,
			SWRLIArgument arg1) {
		return delegate.getSWRLSameIndividualAtom(arg0, arg1);
	}

	/**
	 * @param iri
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataProperty(org.semanticweb.owlapi.model.IRI)
	 */
	public OWLDataProperty getOWLDataProperty(IRI iri) {
		return delegate.getOWLDataProperty(iri);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @return
	 * @see org.semanticweb.owlapi.model.SWRLDataFactory#getSWRLDifferentIndividualsAtom(org.semanticweb.owlapi.model.SWRLIArgument, org.semanticweb.owlapi.model.SWRLIArgument)
	 */
	public SWRLDifferentIndividualsAtom getSWRLDifferentIndividualsAtom(
			SWRLIArgument arg0, SWRLIArgument arg1) {
		return delegate.getSWRLDifferentIndividualsAtom(arg0, arg1);
	}

	/**
	 * @param abbreviatedIRI
	 * @param prefixManager
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataProperty(java.lang.String, org.semanticweb.owlapi.model.PrefixManager)
	 */
	public OWLDataProperty getOWLDataProperty(String abbreviatedIRI,
			PrefixManager prefixManager) {
		return delegate.getOWLDataProperty(abbreviatedIRI, prefixManager);
	}

	/**
	 * @param iri
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLNamedIndividual(org.semanticweb.owlapi.model.IRI)
	 */
	public OWLNamedIndividual getOWLNamedIndividual(IRI iri) {
		return delegate.getOWLNamedIndividual(iri);
	}

	/**
	 * @param abbreviatedIRI
	 * @param prefixManager
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLNamedIndividual(java.lang.String, org.semanticweb.owlapi.model.PrefixManager)
	 */
	public OWLNamedIndividual getOWLNamedIndividual(String abbreviatedIRI,
			PrefixManager prefixManager) {
		return delegate.getOWLNamedIndividual(abbreviatedIRI, prefixManager);
	}

	/**
	 * @param id
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAnonymousIndividual(java.lang.String)
	 */
	public OWLAnonymousIndividual getOWLAnonymousIndividual(String id) {
		return delegate.getOWLAnonymousIndividual(id);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAnonymousIndividual()
	 */
	public OWLAnonymousIndividual getOWLAnonymousIndividual() {
		return delegate.getOWLAnonymousIndividual();
	}

	/**
	 * @param iri
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAnnotationProperty(org.semanticweb.owlapi.model.IRI)
	 */
	public OWLAnnotationProperty getOWLAnnotationProperty(IRI iri) {
		return delegate.getOWLAnnotationProperty(iri);
	}

	/**
	 * @param abbreviatedIRI
	 * @param prefixManager
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAnnotationProperty(java.lang.String, org.semanticweb.owlapi.model.PrefixManager)
	 */
	public OWLAnnotationProperty getOWLAnnotationProperty(
			String abbreviatedIRI, PrefixManager prefixManager) {
		return delegate.getOWLAnnotationProperty(abbreviatedIRI, prefixManager);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getRDFSLabel()
	 */
	public OWLAnnotationProperty getRDFSLabel() {
		return delegate.getRDFSLabel();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getRDFSComment()
	 */
	public OWLAnnotationProperty getRDFSComment() {
		return delegate.getRDFSComment();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getRDFSSeeAlso()
	 */
	public OWLAnnotationProperty getRDFSSeeAlso() {
		return delegate.getRDFSSeeAlso();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getRDFSIsDefinedBy()
	 */
	public OWLAnnotationProperty getRDFSIsDefinedBy() {
		return delegate.getRDFSIsDefinedBy();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLVersionInfo()
	 */
	public OWLAnnotationProperty getOWLVersionInfo() {
		return delegate.getOWLVersionInfo();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLBackwardCompatibleWith()
	 */
	public OWLAnnotationProperty getOWLBackwardCompatibleWith() {
		return delegate.getOWLBackwardCompatibleWith();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLIncompatibleWith()
	 */
	public OWLAnnotationProperty getOWLIncompatibleWith() {
		return delegate.getOWLIncompatibleWith();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDeprecated()
	 */
	public OWLAnnotationProperty getOWLDeprecated() {
		return delegate.getOWLDeprecated();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getRDFPlainLiteral()
	 */
	public OWLDatatype getRDFPlainLiteral() {
		return delegate.getRDFPlainLiteral();
	}

	/**
	 * @param iri
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatype(org.semanticweb.owlapi.model.IRI)
	 */
	public OWLDatatype getOWLDatatype(IRI iri) {
		return delegate.getOWLDatatype(iri);
	}

	/**
	 * @param abbreviatedIRI
	 * @param prefixManager
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatype(java.lang.String, org.semanticweb.owlapi.model.PrefixManager)
	 */
	public OWLDatatype getOWLDatatype(String abbreviatedIRI,
			PrefixManager prefixManager) {
		return delegate.getOWLDatatype(abbreviatedIRI, prefixManager);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getIntegerOWLDatatype()
	 */
	public OWLDatatype getIntegerOWLDatatype() {
		return delegate.getIntegerOWLDatatype();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getFloatOWLDatatype()
	 */
	public OWLDatatype getFloatOWLDatatype() {
		return delegate.getFloatOWLDatatype();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getDoubleOWLDatatype()
	 */
	public OWLDatatype getDoubleOWLDatatype() {
		return delegate.getDoubleOWLDatatype();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getBooleanOWLDatatype()
	 */
	public OWLDatatype getBooleanOWLDatatype() {
		return delegate.getBooleanOWLDatatype();
	}

	/**
	 * @param lexicalValue
	 * @param datatype
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLLiteral(java.lang.String, org.semanticweb.owlapi.model.OWLDatatype)
	 */
	public OWLLiteral getOWLLiteral(String lexicalValue, OWLDatatype datatype) {
		return delegate.getOWLLiteral(lexicalValue, datatype);
	}

	/**
	 * @param lexicalValue
	 * @param datatype
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLLiteral(java.lang.String, org.semanticweb.owlapi.vocab.OWL2Datatype)
	 */
	public OWLLiteral getOWLLiteral(String lexicalValue, OWL2Datatype datatype) {
		return delegate.getOWLLiteral(lexicalValue, datatype);
	}

	/**
	 * @param value
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLLiteral(int)
	 */
	public OWLLiteral getOWLLiteral(int value) {
		return delegate.getOWLLiteral(value);
	}

	/**
	 * @param value
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLLiteral(double)
	 */
	public OWLLiteral getOWLLiteral(double value) {
		return delegate.getOWLLiteral(value);
	}

	/**
	 * @param value
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLLiteral(boolean)
	 */
	public OWLLiteral getOWLLiteral(boolean value) {
		return delegate.getOWLLiteral(value);
	}

	/**
	 * @param value
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLLiteral(float)
	 */
	public OWLLiteral getOWLLiteral(float value) {
		return delegate.getOWLLiteral(value);
	}

	/**
	 * @param value
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLLiteral(java.lang.String)
	 */
	public OWLLiteral getOWLLiteral(String value) {
		return delegate.getOWLLiteral(value);
	}

	/**
	 * @param literal
	 * @param lang
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLLiteral(java.lang.String, java.lang.String)
	 */
	public OWLLiteral getOWLLiteral(String literal, String lang) {
		return delegate.getOWLLiteral(literal, lang);
	}

	/**
	 * @param literal
	 * @param datatype
	 * @return
	 * @deprecated
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLTypedLiteral(java.lang.String, org.semanticweb.owlapi.model.OWLDatatype)
	 */
	public OWLLiteral getOWLTypedLiteral(String literal, OWLDatatype datatype) {
		return delegate.getOWLTypedLiteral(literal, datatype);
	}

	/**
	 * @param literal
	 * @param datatype
	 * @return
	 * @deprecated
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLTypedLiteral(java.lang.String, org.semanticweb.owlapi.vocab.OWL2Datatype)
	 */
	public OWLLiteral getOWLTypedLiteral(String literal, OWL2Datatype datatype) {
		return delegate.getOWLTypedLiteral(literal, datatype);
	}

	/**
	 * @param value
	 * @return
	 * @deprecated
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLTypedLiteral(int)
	 */
	public OWLLiteral getOWLTypedLiteral(int value) {
		return delegate.getOWLTypedLiteral(value);
	}

	/**
	 * @param value
	 * @return
	 * @deprecated
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLTypedLiteral(double)
	 */
	public OWLLiteral getOWLTypedLiteral(double value) {
		return delegate.getOWLTypedLiteral(value);
	}

	/**
	 * @param value
	 * @return
	 * @deprecated
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLTypedLiteral(boolean)
	 */
	public OWLLiteral getOWLTypedLiteral(boolean value) {
		return delegate.getOWLTypedLiteral(value);
	}

	/**
	 * @param value
	 * @return
	 * @deprecated
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLTypedLiteral(float)
	 */
	public OWLLiteral getOWLTypedLiteral(float value) {
		return delegate.getOWLTypedLiteral(value);
	}

	/**
	 * @param value
	 * @return
	 * @deprecated
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLTypedLiteral(java.lang.String)
	 */
	public OWLLiteral getOWLTypedLiteral(String value) {
		return delegate.getOWLTypedLiteral(value);
	}

	/**
	 * @param literal
	 * @param lang
	 * @return
	 * @deprecated
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLStringLiteral(java.lang.String, java.lang.String)
	 */
	public OWLLiteral getOWLStringLiteral(String literal, String lang) {
		return delegate.getOWLStringLiteral(literal, lang);
	}

	/**
	 * @param literal
	 * @return
	 * @deprecated
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLStringLiteral(java.lang.String)
	 */
	public OWLLiteral getOWLStringLiteral(String literal) {
		return delegate.getOWLStringLiteral(literal);
	}

	/**
	 * @param values
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataOneOf(java.util.Set)
	 */
	public OWLDataOneOf getOWLDataOneOf(Set<? extends OWLLiteral> values) {
		return delegate.getOWLDataOneOf(values);
	}

	/**
	 * @param values
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataOneOf(org.semanticweb.owlapi.model.OWLLiteral[])
	 */
	public OWLDataOneOf getOWLDataOneOf(OWLLiteral... values) {
		return delegate.getOWLDataOneOf(values);
	}

	/**
	 * @param dataRange
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataComplementOf(org.semanticweb.owlapi.model.OWLDataRange)
	 */
	public OWLDataComplementOf getOWLDataComplementOf(OWLDataRange dataRange) {
		return delegate.getOWLDataComplementOf(dataRange);
	}

	/**
	 * @param dataRange
	 * @param facetRestrictions
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeRestriction(org.semanticweb.owlapi.model.OWLDatatype, java.util.Set)
	 */
	public OWLDatatypeRestriction getOWLDatatypeRestriction(
			OWLDatatype dataRange, Set<OWLFacetRestriction> facetRestrictions) {
		return delegate.getOWLDatatypeRestriction(dataRange, facetRestrictions);
	}

	/**
	 * @param dataRange
	 * @param facet
	 * @param typedLiteral
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeRestriction(org.semanticweb.owlapi.model.OWLDatatype, org.semanticweb.owlapi.vocab.OWLFacet, org.semanticweb.owlapi.model.OWLLiteral)
	 */
	public OWLDatatypeRestriction getOWLDatatypeRestriction(
			OWLDatatype dataRange, OWLFacet facet, OWLLiteral typedLiteral) {
		return delegate.getOWLDatatypeRestriction(dataRange, facet,
				typedLiteral);
	}

	/**
	 * @param dataRange
	 * @param facetRestrictions
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeRestriction(org.semanticweb.owlapi.model.OWLDatatype, org.semanticweb.owlapi.model.OWLFacetRestriction[])
	 */
	public OWLDatatypeRestriction getOWLDatatypeRestriction(
			OWLDatatype dataRange, OWLFacetRestriction... facetRestrictions) {
		return delegate.getOWLDatatypeRestriction(dataRange, facetRestrictions);
	}

	/**
	 * @param minInclusive
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeMinInclusiveRestriction(int)
	 */
	public OWLDatatypeRestriction getOWLDatatypeMinInclusiveRestriction(
			int minInclusive) {
		return delegate.getOWLDatatypeMinInclusiveRestriction(minInclusive);
	}

	/**
	 * @param maxInclusive
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeMaxInclusiveRestriction(int)
	 */
	public OWLDatatypeRestriction getOWLDatatypeMaxInclusiveRestriction(
			int maxInclusive) {
		return delegate.getOWLDatatypeMaxInclusiveRestriction(maxInclusive);
	}

	/**
	 * @param minInclusive
	 * @param maxInclusive
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeMinMaxInclusiveRestriction(int, int)
	 */
	public OWLDatatypeRestriction getOWLDatatypeMinMaxInclusiveRestriction(
			int minInclusive, int maxInclusive) {
		return delegate.getOWLDatatypeMinMaxInclusiveRestriction(minInclusive,
				maxInclusive);
	}

	/**
	 * @param minExclusive
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeMinExclusiveRestriction(int)
	 */
	public OWLDatatypeRestriction getOWLDatatypeMinExclusiveRestriction(
			int minExclusive) {
		return delegate.getOWLDatatypeMinExclusiveRestriction(minExclusive);
	}

	/**
	 * @param maxExclusive
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeMaxExclusiveRestriction(int)
	 */
	public OWLDatatypeRestriction getOWLDatatypeMaxExclusiveRestriction(
			int maxExclusive) {
		return delegate.getOWLDatatypeMaxExclusiveRestriction(maxExclusive);
	}

	/**
	 * @param minExclusive
	 * @param maxExclusive
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeMinMaxExclusiveRestriction(int, int)
	 */
	public OWLDatatypeRestriction getOWLDatatypeMinMaxExclusiveRestriction(
			int minExclusive, int maxExclusive) {
		return delegate.getOWLDatatypeMinMaxExclusiveRestriction(minExclusive,
				maxExclusive);
	}

	/**
	 * @param minInclusive
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeMinInclusiveRestriction(double)
	 */
	public OWLDatatypeRestriction getOWLDatatypeMinInclusiveRestriction(
			double minInclusive) {
		return delegate.getOWLDatatypeMinInclusiveRestriction(minInclusive);
	}

	/**
	 * @param maxInclusive
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeMaxInclusiveRestriction(double)
	 */
	public OWLDatatypeRestriction getOWLDatatypeMaxInclusiveRestriction(
			double maxInclusive) {
		return delegate.getOWLDatatypeMaxInclusiveRestriction(maxInclusive);
	}

	/**
	 * @param minInclusive
	 * @param maxInclusive
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeMinMaxInclusiveRestriction(double, double)
	 */
	public OWLDatatypeRestriction getOWLDatatypeMinMaxInclusiveRestriction(
			double minInclusive, double maxInclusive) {
		return delegate.getOWLDatatypeMinMaxInclusiveRestriction(minInclusive,
				maxInclusive);
	}

	/**
	 * @param minExclusive
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeMinExclusiveRestriction(double)
	 */
	public OWLDatatypeRestriction getOWLDatatypeMinExclusiveRestriction(
			double minExclusive) {
		return delegate.getOWLDatatypeMinExclusiveRestriction(minExclusive);
	}

	/**
	 * @param maxExclusive
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeMaxExclusiveRestriction(double)
	 */
	public OWLDatatypeRestriction getOWLDatatypeMaxExclusiveRestriction(
			double maxExclusive) {
		return delegate.getOWLDatatypeMaxExclusiveRestriction(maxExclusive);
	}

	/**
	 * @param minExclusive
	 * @param maxExclusive
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeMinMaxExclusiveRestriction(double, double)
	 */
	public OWLDatatypeRestriction getOWLDatatypeMinMaxExclusiveRestriction(
			double minExclusive, double maxExclusive) {
		return delegate.getOWLDatatypeMinMaxExclusiveRestriction(minExclusive,
				maxExclusive);
	}

	/**
	 * @param facet
	 * @param facetValue
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLFacetRestriction(org.semanticweb.owlapi.vocab.OWLFacet, org.semanticweb.owlapi.model.OWLLiteral)
	 */
	public OWLFacetRestriction getOWLFacetRestriction(OWLFacet facet,
			OWLLiteral facetValue) {
		return delegate.getOWLFacetRestriction(facet, facetValue);
	}

	/**
	 * @param facet
	 * @param facetValue
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLFacetRestriction(org.semanticweb.owlapi.vocab.OWLFacet, int)
	 */
	public OWLFacetRestriction getOWLFacetRestriction(OWLFacet facet,
			int facetValue) {
		return delegate.getOWLFacetRestriction(facet, facetValue);
	}

	/**
	 * @param facet
	 * @param facetValue
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLFacetRestriction(org.semanticweb.owlapi.vocab.OWLFacet, double)
	 */
	public OWLFacetRestriction getOWLFacetRestriction(OWLFacet facet,
			double facetValue) {
		return delegate.getOWLFacetRestriction(facet, facetValue);
	}

	/**
	 * @param facet
	 * @param facetValue
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLFacetRestriction(org.semanticweb.owlapi.vocab.OWLFacet, float)
	 */
	public OWLFacetRestriction getOWLFacetRestriction(OWLFacet facet,
			float facetValue) {
		return delegate.getOWLFacetRestriction(facet, facetValue);
	}

	/**
	 * @param dataRanges
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataUnionOf(java.util.Set)
	 */
	public OWLDataUnionOf getOWLDataUnionOf(
			Set<? extends OWLDataRange> dataRanges) {
		return delegate.getOWLDataUnionOf(dataRanges);
	}

	/**
	 * @param dataRanges
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataUnionOf(org.semanticweb.owlapi.model.OWLDataRange[])
	 */
	public OWLDataUnionOf getOWLDataUnionOf(OWLDataRange... dataRanges) {
		return delegate.getOWLDataUnionOf(dataRanges);
	}

	/**
	 * @param dataRanges
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataIntersectionOf(java.util.Set)
	 */
	public OWLDataIntersectionOf getOWLDataIntersectionOf(
			Set<? extends OWLDataRange> dataRanges) {
		return delegate.getOWLDataIntersectionOf(dataRanges);
	}

	/**
	 * @param dataRanges
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataIntersectionOf(org.semanticweb.owlapi.model.OWLDataRange[])
	 */
	public OWLDataIntersectionOf getOWLDataIntersectionOf(
			OWLDataRange... dataRanges) {
		return delegate.getOWLDataIntersectionOf(dataRanges);
	}

	/**
	 * @param operands
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectIntersectionOf(java.util.Set)
	 */
	public OWLObjectIntersectionOf getOWLObjectIntersectionOf(
			Set<? extends OWLClassExpression> operands) {
		return delegate.getOWLObjectIntersectionOf(operands);
	}

	/**
	 * @param operands
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectIntersectionOf(org.semanticweb.owlapi.model.OWLClassExpression[])
	 */
	public OWLObjectIntersectionOf getOWLObjectIntersectionOf(
			OWLClassExpression... operands) {
		return delegate.getOWLObjectIntersectionOf(operands);
	}

	/**
	 * @param property
	 * @param dataRange
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataSomeValuesFrom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLDataRange)
	 */
	public OWLDataSomeValuesFrom getOWLDataSomeValuesFrom(
			OWLDataPropertyExpression property, OWLDataRange dataRange) {
		return delegate.getOWLDataSomeValuesFrom(property, dataRange);
	}

	/**
	 * @param property
	 * @param dataRange
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataAllValuesFrom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLDataRange)
	 */
	public OWLDataAllValuesFrom getOWLDataAllValuesFrom(
			OWLDataPropertyExpression property, OWLDataRange dataRange) {
		return delegate.getOWLDataAllValuesFrom(property, dataRange);
	}

	/**
	 * @param cardinality
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataExactCardinality(int, org.semanticweb.owlapi.model.OWLDataPropertyExpression)
	 */
	public OWLDataExactCardinality getOWLDataExactCardinality(int cardinality,
			OWLDataPropertyExpression property) {
		return delegate.getOWLDataExactCardinality(cardinality, property);
	}

	/**
	 * @param cardinality
	 * @param property
	 * @param dataRange
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataExactCardinality(int, org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLDataRange)
	 */
	public OWLDataExactCardinality getOWLDataExactCardinality(int cardinality,
			OWLDataPropertyExpression property, OWLDataRange dataRange) {
		return delegate.getOWLDataExactCardinality(cardinality, property,
				dataRange);
	}

	/**
	 * @param cardinality
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataMaxCardinality(int, org.semanticweb.owlapi.model.OWLDataPropertyExpression)
	 */
	public OWLDataMaxCardinality getOWLDataMaxCardinality(int cardinality,
			OWLDataPropertyExpression property) {
		return delegate.getOWLDataMaxCardinality(cardinality, property);
	}

	/**
	 * @param cardinality
	 * @param property
	 * @param dataRange
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataMaxCardinality(int, org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLDataRange)
	 */
	public OWLDataMaxCardinality getOWLDataMaxCardinality(int cardinality,
			OWLDataPropertyExpression property, OWLDataRange dataRange) {
		return delegate.getOWLDataMaxCardinality(cardinality, property,
				dataRange);
	}

	/**
	 * @param cardinality
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataMinCardinality(int, org.semanticweb.owlapi.model.OWLDataPropertyExpression)
	 */
	public OWLDataMinCardinality getOWLDataMinCardinality(int cardinality,
			OWLDataPropertyExpression property) {
		return delegate.getOWLDataMinCardinality(cardinality, property);
	}

	/**
	 * @param cardinality
	 * @param property
	 * @param dataRange
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataMinCardinality(int, org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLDataRange)
	 */
	public OWLDataMinCardinality getOWLDataMinCardinality(int cardinality,
			OWLDataPropertyExpression property, OWLDataRange dataRange) {
		return delegate.getOWLDataMinCardinality(cardinality, property,
				dataRange);
	}

	/**
	 * @param property
	 * @param value
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataHasValue(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLLiteral)
	 */
	public OWLDataHasValue getOWLDataHasValue(
			OWLDataPropertyExpression property, OWLLiteral value) {
		return delegate.getOWLDataHasValue(property, value);
	}

	/**
	 * @param operand
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectComplementOf(org.semanticweb.owlapi.model.OWLClassExpression)
	 */
	public OWLObjectComplementOf getOWLObjectComplementOf(
			OWLClassExpression operand) {
		return delegate.getOWLObjectComplementOf(operand);
	}

	/**
	 * @param values
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectOneOf(java.util.Set)
	 */
	public OWLObjectOneOf getOWLObjectOneOf(Set<? extends OWLIndividual> values) {
		return delegate.getOWLObjectOneOf(values);
	}

	/**
	 * @param individuals
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectOneOf(org.semanticweb.owlapi.model.OWLIndividual[])
	 */
	public OWLObjectOneOf getOWLObjectOneOf(OWLIndividual... individuals) {
		return delegate.getOWLObjectOneOf(individuals);
	}

	/**
	 * @param property
	 * @param classExpression
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectAllValuesFrom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLClassExpression)
	 */
	public OWLObjectAllValuesFrom getOWLObjectAllValuesFrom(
			OWLObjectPropertyExpression property,
			OWLClassExpression classExpression) {
		return delegate.getOWLObjectAllValuesFrom(property, classExpression);
	}

	/**
	 * @param property
	 * @param classExpression
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectSomeValuesFrom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLClassExpression)
	 */
	public OWLObjectSomeValuesFrom getOWLObjectSomeValuesFrom(
			OWLObjectPropertyExpression property,
			OWLClassExpression classExpression) {
		return delegate.getOWLObjectSomeValuesFrom(property, classExpression);
	}

	/**
	 * @param cardinality
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectExactCardinality(int, org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLObjectExactCardinality getOWLObjectExactCardinality(
			int cardinality, OWLObjectPropertyExpression property) {
		return delegate.getOWLObjectExactCardinality(cardinality, property);
	}

	/**
	 * @param cardinality
	 * @param property
	 * @param classExpression
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectExactCardinality(int, org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLClassExpression)
	 */
	public OWLObjectExactCardinality getOWLObjectExactCardinality(
			int cardinality, OWLObjectPropertyExpression property,
			OWLClassExpression classExpression) {
		return delegate.getOWLObjectExactCardinality(cardinality, property,
				classExpression);
	}

	/**
	 * @param cardinality
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectMinCardinality(int, org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLObjectMinCardinality getOWLObjectMinCardinality(int cardinality,
			OWLObjectPropertyExpression property) {
		return delegate.getOWLObjectMinCardinality(cardinality, property);
	}

	/**
	 * @param cardinality
	 * @param property
	 * @param classExpression
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectMinCardinality(int, org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLClassExpression)
	 */
	public OWLObjectMinCardinality getOWLObjectMinCardinality(int cardinality,
			OWLObjectPropertyExpression property,
			OWLClassExpression classExpression) {
		return delegate.getOWLObjectMinCardinality(cardinality, property,
				classExpression);
	}

	/**
	 * @param cardinality
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectMaxCardinality(int, org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLObjectMaxCardinality getOWLObjectMaxCardinality(int cardinality,
			OWLObjectPropertyExpression property) {
		return delegate.getOWLObjectMaxCardinality(cardinality, property);
	}

	/**
	 * @param cardinality
	 * @param property
	 * @param classExpression
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectMaxCardinality(int, org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLClassExpression)
	 */
	public OWLObjectMaxCardinality getOWLObjectMaxCardinality(int cardinality,
			OWLObjectPropertyExpression property,
			OWLClassExpression classExpression) {
		return delegate.getOWLObjectMaxCardinality(cardinality, property,
				classExpression);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectHasSelf(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLObjectHasSelf getOWLObjectHasSelf(
			OWLObjectPropertyExpression property) {
		return delegate.getOWLObjectHasSelf(property);
	}

	/**
	 * @param property
	 * @param individual
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectHasValue(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLIndividual)
	 */
	public OWLObjectHasValue getOWLObjectHasValue(
			OWLObjectPropertyExpression property, OWLIndividual individual) {
		return delegate.getOWLObjectHasValue(property, individual);
	}

	/**
	 * @param operands
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectUnionOf(java.util.Set)
	 */
	public OWLObjectUnionOf getOWLObjectUnionOf(
			Set<? extends OWLClassExpression> operands) {
		return delegate.getOWLObjectUnionOf(operands);
	}

	/**
	 * @param operands
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectUnionOf(org.semanticweb.owlapi.model.OWLClassExpression[])
	 */
	public OWLObjectUnionOf getOWLObjectUnionOf(OWLClassExpression... operands) {
		return delegate.getOWLObjectUnionOf(operands);
	}

	/**
	 * @param owlEntity
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDeclarationAxiom(org.semanticweb.owlapi.model.OWLEntity)
	 */
	public OWLDeclarationAxiom getOWLDeclarationAxiom(OWLEntity owlEntity) {
		return delegate.getOWLDeclarationAxiom(owlEntity);
	}

	/**
	 * @param owlEntity
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDeclarationAxiom(org.semanticweb.owlapi.model.OWLEntity, java.util.Set)
	 */
	public OWLDeclarationAxiom getOWLDeclarationAxiom(OWLEntity owlEntity,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLDeclarationAxiom(owlEntity, annotations);
	}

	/**
	 * @param subClass
	 * @param superClass
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSubClassOfAxiom(org.semanticweb.owlapi.model.OWLClassExpression, org.semanticweb.owlapi.model.OWLClassExpression)
	 */
	public OWLSubClassOfAxiom getOWLSubClassOfAxiom(
			OWLClassExpression subClass, OWLClassExpression superClass) {
		return delegate.getOWLSubClassOfAxiom(subClass, superClass);
	}

	/**
	 * @param subClass
	 * @param superClass
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSubClassOfAxiom(org.semanticweb.owlapi.model.OWLClassExpression, org.semanticweb.owlapi.model.OWLClassExpression, java.util.Set)
	 */
	public OWLSubClassOfAxiom getOWLSubClassOfAxiom(
			OWLClassExpression subClass, OWLClassExpression superClass,
			Set<? extends OWLAnnotation> annotations) {
		return delegate
				.getOWLSubClassOfAxiom(subClass, superClass, annotations);
	}

	/**
	 * @param classExpressions
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentClassesAxiom(java.util.Set)
	 */
	public OWLEquivalentClassesAxiom getOWLEquivalentClassesAxiom(
			Set<? extends OWLClassExpression> classExpressions) {
		return delegate.getOWLEquivalentClassesAxiom(classExpressions);
	}

	/**
	 * @param classExpressions
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentClassesAxiom(java.util.Set, java.util.Set)
	 */
	public OWLEquivalentClassesAxiom getOWLEquivalentClassesAxiom(
			Set<? extends OWLClassExpression> classExpressions,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLEquivalentClassesAxiom(classExpressions,
				annotations);
	}

	/**
	 * @param classExpressions
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentClassesAxiom(org.semanticweb.owlapi.model.OWLClassExpression[])
	 */
	public OWLEquivalentClassesAxiom getOWLEquivalentClassesAxiom(
			OWLClassExpression... classExpressions) {
		return delegate.getOWLEquivalentClassesAxiom(classExpressions);
	}

	/**
	 * @param clsA
	 * @param clsB
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentClassesAxiom(org.semanticweb.owlapi.model.OWLClassExpression, org.semanticweb.owlapi.model.OWLClassExpression)
	 */
	public OWLEquivalentClassesAxiom getOWLEquivalentClassesAxiom(
			OWLClassExpression clsA, OWLClassExpression clsB) {
		return delegate.getOWLEquivalentClassesAxiom(clsA, clsB);
	}

	/**
	 * @param clsA
	 * @param clsB
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentClassesAxiom(org.semanticweb.owlapi.model.OWLClassExpression, org.semanticweb.owlapi.model.OWLClassExpression, java.util.Set)
	 */
	public OWLEquivalentClassesAxiom getOWLEquivalentClassesAxiom(
			OWLClassExpression clsA, OWLClassExpression clsB,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLEquivalentClassesAxiom(clsA, clsB, annotations);
	}

	/**
	 * @param classExpressions
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDisjointClassesAxiom(java.util.Set)
	 */
	public OWLDisjointClassesAxiom getOWLDisjointClassesAxiom(
			Set<? extends OWLClassExpression> classExpressions) {
		return delegate.getOWLDisjointClassesAxiom(classExpressions);
	}

	/**
	 * @param classExpressions
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDisjointClassesAxiom(org.semanticweb.owlapi.model.OWLClassExpression[])
	 */
	public OWLDisjointClassesAxiom getOWLDisjointClassesAxiom(
			OWLClassExpression... classExpressions) {
		return delegate.getOWLDisjointClassesAxiom(classExpressions);
	}

	/**
	 * @param classExpressions
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDisjointClassesAxiom(java.util.Set, java.util.Set)
	 */
	public OWLDisjointClassesAxiom getOWLDisjointClassesAxiom(
			Set<? extends OWLClassExpression> classExpressions,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLDisjointClassesAxiom(classExpressions,
				annotations);
	}

	/**
	 * @param owlClass
	 * @param classExpressions
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDisjointUnionAxiom(org.semanticweb.owlapi.model.OWLClass, java.util.Set)
	 */
	public OWLDisjointUnionAxiom getOWLDisjointUnionAxiom(OWLClass owlClass,
			Set<? extends OWLClassExpression> classExpressions) {
		return delegate.getOWLDisjointUnionAxiom(owlClass, classExpressions);
	}

	/**
	 * @param owlClass
	 * @param classExpressions
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDisjointUnionAxiom(org.semanticweb.owlapi.model.OWLClass, java.util.Set, java.util.Set)
	 */
	public OWLDisjointUnionAxiom getOWLDisjointUnionAxiom(OWLClass owlClass,
			Set<? extends OWLClassExpression> classExpressions,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLDisjointUnionAxiom(owlClass, classExpressions,
				annotations);
	}

	/**
	 * @param subProperty
	 * @param superProperty
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSubObjectPropertyOfAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLSubObjectPropertyOfAxiom getOWLSubObjectPropertyOfAxiom(
			OWLObjectPropertyExpression subProperty,
			OWLObjectPropertyExpression superProperty) {
		return delegate.getOWLSubObjectPropertyOfAxiom(subProperty,
				superProperty);
	}

	/**
	 * @param subProperty
	 * @param superProperty
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSubObjectPropertyOfAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLObjectPropertyExpression, java.util.Set)
	 */
	public OWLSubObjectPropertyOfAxiom getOWLSubObjectPropertyOfAxiom(
			OWLObjectPropertyExpression subProperty,
			OWLObjectPropertyExpression superProperty,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLSubObjectPropertyOfAxiom(subProperty,
				superProperty, annotations);
	}

	/**
	 * @param chain
	 * @param superProperty
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSubPropertyChainOfAxiom(java.util.List, org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLSubPropertyChainOfAxiom getOWLSubPropertyChainOfAxiom(
			List<? extends OWLObjectPropertyExpression> chain,
			OWLObjectPropertyExpression superProperty) {
		return delegate.getOWLSubPropertyChainOfAxiom(chain, superProperty);
	}

	/**
	 * @param chain
	 * @param superProperty
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSubPropertyChainOfAxiom(java.util.List, org.semanticweb.owlapi.model.OWLObjectPropertyExpression, java.util.Set)
	 */
	public OWLSubPropertyChainOfAxiom getOWLSubPropertyChainOfAxiom(
			List<? extends OWLObjectPropertyExpression> chain,
			OWLObjectPropertyExpression superProperty,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLSubPropertyChainOfAxiom(chain, superProperty,
				annotations);
	}

	/**
	 * @param properties
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentObjectPropertiesAxiom(java.util.Set)
	 */
	public OWLEquivalentObjectPropertiesAxiom getOWLEquivalentObjectPropertiesAxiom(
			Set<? extends OWLObjectPropertyExpression> properties) {
		return delegate.getOWLEquivalentObjectPropertiesAxiom(properties);
	}

	/**
	 * @param properties
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentObjectPropertiesAxiom(java.util.Set, java.util.Set)
	 */
	public OWLEquivalentObjectPropertiesAxiom getOWLEquivalentObjectPropertiesAxiom(
			Set<? extends OWLObjectPropertyExpression> properties,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLEquivalentObjectPropertiesAxiom(properties,
				annotations);
	}

	/**
	 * @param properties
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentObjectPropertiesAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression[])
	 */
	public OWLEquivalentObjectPropertiesAxiom getOWLEquivalentObjectPropertiesAxiom(
			OWLObjectPropertyExpression... properties) {
		return delegate.getOWLEquivalentObjectPropertiesAxiom(properties);
	}

	/**
	 * @param propertyA
	 * @param propertyB
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentObjectPropertiesAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLEquivalentObjectPropertiesAxiom getOWLEquivalentObjectPropertiesAxiom(
			OWLObjectPropertyExpression propertyA,
			OWLObjectPropertyExpression propertyB) {
		return delegate.getOWLEquivalentObjectPropertiesAxiom(propertyA,
				propertyB);
	}

	/**
	 * @param propertyA
	 * @param propertyB
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentObjectPropertiesAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLObjectPropertyExpression, java.util.Set)
	 */
	public OWLEquivalentObjectPropertiesAxiom getOWLEquivalentObjectPropertiesAxiom(
			OWLObjectPropertyExpression propertyA,
			OWLObjectPropertyExpression propertyB,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLEquivalentObjectPropertiesAxiom(propertyA,
				propertyB, annotations);
	}

	/**
	 * @param properties
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDisjointObjectPropertiesAxiom(java.util.Set)
	 */
	public OWLDisjointObjectPropertiesAxiom getOWLDisjointObjectPropertiesAxiom(
			Set<? extends OWLObjectPropertyExpression> properties) {
		return delegate.getOWLDisjointObjectPropertiesAxiom(properties);
	}

	/**
	 * @param properties
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDisjointObjectPropertiesAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression[])
	 */
	public OWLDisjointObjectPropertiesAxiom getOWLDisjointObjectPropertiesAxiom(
			OWLObjectPropertyExpression... properties) {
		return delegate.getOWLDisjointObjectPropertiesAxiom(properties);
	}

	/**
	 * @param properties
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDisjointObjectPropertiesAxiom(java.util.Set, java.util.Set)
	 */
	public OWLDisjointObjectPropertiesAxiom getOWLDisjointObjectPropertiesAxiom(
			Set<? extends OWLObjectPropertyExpression> properties,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLDisjointObjectPropertiesAxiom(properties,
				annotations);
	}

	/**
	 * @param forwardProperty
	 * @param inverseProperty
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLInverseObjectPropertiesAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLInverseObjectPropertiesAxiom getOWLInverseObjectPropertiesAxiom(
			OWLObjectPropertyExpression forwardProperty,
			OWLObjectPropertyExpression inverseProperty) {
		return delegate.getOWLInverseObjectPropertiesAxiom(forwardProperty,
				inverseProperty);
	}

	/**
	 * @param forwardProperty
	 * @param inverseProperty
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLInverseObjectPropertiesAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLObjectPropertyExpression, java.util.Set)
	 */
	public OWLInverseObjectPropertiesAxiom getOWLInverseObjectPropertiesAxiom(
			OWLObjectPropertyExpression forwardProperty,
			OWLObjectPropertyExpression inverseProperty,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLInverseObjectPropertiesAxiom(forwardProperty,
				inverseProperty, annotations);
	}

	/**
	 * @param property
	 * @param classExpression
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectPropertyDomainAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLClassExpression)
	 */
	public OWLObjectPropertyDomainAxiom getOWLObjectPropertyDomainAxiom(
			OWLObjectPropertyExpression property,
			OWLClassExpression classExpression) {
		return delegate.getOWLObjectPropertyDomainAxiom(property,
				classExpression);
	}

	/**
	 * @param property
	 * @param classExpression
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectPropertyDomainAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLClassExpression, java.util.Set)
	 */
	public OWLObjectPropertyDomainAxiom getOWLObjectPropertyDomainAxiom(
			OWLObjectPropertyExpression property,
			OWLClassExpression classExpression,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLObjectPropertyDomainAxiom(property,
				classExpression, annotations);
	}

	/**
	 * @param property
	 * @param range
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectPropertyRangeAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLClassExpression)
	 */
	public OWLObjectPropertyRangeAxiom getOWLObjectPropertyRangeAxiom(
			OWLObjectPropertyExpression property, OWLClassExpression range) {
		return delegate.getOWLObjectPropertyRangeAxiom(property, range);
	}

	/**
	 * @param property
	 * @param range
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectPropertyRangeAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLClassExpression, java.util.Set)
	 */
	public OWLObjectPropertyRangeAxiom getOWLObjectPropertyRangeAxiom(
			OWLObjectPropertyExpression property, OWLClassExpression range,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLObjectPropertyRangeAxiom(property, range,
				annotations);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLFunctionalObjectPropertyAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLFunctionalObjectPropertyAxiom getOWLFunctionalObjectPropertyAxiom(
			OWLObjectPropertyExpression property) {
		return delegate.getOWLFunctionalObjectPropertyAxiom(property);
	}

	/**
	 * @param property
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLFunctionalObjectPropertyAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, java.util.Set)
	 */
	public OWLFunctionalObjectPropertyAxiom getOWLFunctionalObjectPropertyAxiom(
			OWLObjectPropertyExpression property,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLFunctionalObjectPropertyAxiom(property,
				annotations);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLInverseFunctionalObjectPropertyAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLInverseFunctionalObjectPropertyAxiom getOWLInverseFunctionalObjectPropertyAxiom(
			OWLObjectPropertyExpression property) {
		return delegate.getOWLInverseFunctionalObjectPropertyAxiom(property);
	}

	/**
	 * @param property
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLInverseFunctionalObjectPropertyAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, java.util.Set)
	 */
	public OWLInverseFunctionalObjectPropertyAxiom getOWLInverseFunctionalObjectPropertyAxiom(
			OWLObjectPropertyExpression property,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLInverseFunctionalObjectPropertyAxiom(property,
				annotations);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLReflexiveObjectPropertyAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLReflexiveObjectPropertyAxiom getOWLReflexiveObjectPropertyAxiom(
			OWLObjectPropertyExpression property) {
		return delegate.getOWLReflexiveObjectPropertyAxiom(property);
	}

	/**
	 * @param property
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLReflexiveObjectPropertyAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, java.util.Set)
	 */
	public OWLReflexiveObjectPropertyAxiom getOWLReflexiveObjectPropertyAxiom(
			OWLObjectPropertyExpression property,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLReflexiveObjectPropertyAxiom(property,
				annotations);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLIrreflexiveObjectPropertyAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLIrreflexiveObjectPropertyAxiom getOWLIrreflexiveObjectPropertyAxiom(
			OWLObjectPropertyExpression property) {
		return delegate.getOWLIrreflexiveObjectPropertyAxiom(property);
	}

	/**
	 * @param property
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLIrreflexiveObjectPropertyAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, java.util.Set)
	 */
	public OWLIrreflexiveObjectPropertyAxiom getOWLIrreflexiveObjectPropertyAxiom(
			OWLObjectPropertyExpression property,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLIrreflexiveObjectPropertyAxiom(property,
				annotations);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSymmetricObjectPropertyAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLSymmetricObjectPropertyAxiom getOWLSymmetricObjectPropertyAxiom(
			OWLObjectPropertyExpression property) {
		return delegate.getOWLSymmetricObjectPropertyAxiom(property);
	}

	/**
	 * @param property
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSymmetricObjectPropertyAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, java.util.Set)
	 */
	public OWLSymmetricObjectPropertyAxiom getOWLSymmetricObjectPropertyAxiom(
			OWLObjectPropertyExpression property,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLSymmetricObjectPropertyAxiom(property,
				annotations);
	}

	/**
	 * @param propertyExpression
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAsymmetricObjectPropertyAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLAsymmetricObjectPropertyAxiom getOWLAsymmetricObjectPropertyAxiom(
			OWLObjectPropertyExpression propertyExpression) {
		return delegate.getOWLAsymmetricObjectPropertyAxiom(propertyExpression);
	}

	/**
	 * @param propertyExpression
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAsymmetricObjectPropertyAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, java.util.Set)
	 */
	public OWLAsymmetricObjectPropertyAxiom getOWLAsymmetricObjectPropertyAxiom(
			OWLObjectPropertyExpression propertyExpression,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLAsymmetricObjectPropertyAxiom(propertyExpression,
				annotations);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLTransitiveObjectPropertyAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public OWLTransitiveObjectPropertyAxiom getOWLTransitiveObjectPropertyAxiom(
			OWLObjectPropertyExpression property) {
		return delegate.getOWLTransitiveObjectPropertyAxiom(property);
	}

	/**
	 * @param property
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLTransitiveObjectPropertyAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, java.util.Set)
	 */
	public OWLTransitiveObjectPropertyAxiom getOWLTransitiveObjectPropertyAxiom(
			OWLObjectPropertyExpression property,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLTransitiveObjectPropertyAxiom(property,
				annotations);
	}

	/**
	 * @param subProperty
	 * @param superProperty
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSubDataPropertyOfAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLDataPropertyExpression)
	 */
	public OWLSubDataPropertyOfAxiom getOWLSubDataPropertyOfAxiom(
			OWLDataPropertyExpression subProperty,
			OWLDataPropertyExpression superProperty) {
		return delegate
				.getOWLSubDataPropertyOfAxiom(subProperty, superProperty);
	}

	/**
	 * @param subProperty
	 * @param superProperty
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSubDataPropertyOfAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLDataPropertyExpression, java.util.Set)
	 */
	public OWLSubDataPropertyOfAxiom getOWLSubDataPropertyOfAxiom(
			OWLDataPropertyExpression subProperty,
			OWLDataPropertyExpression superProperty,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLSubDataPropertyOfAxiom(subProperty,
				superProperty, annotations);
	}

	/**
	 * @param properties
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentDataPropertiesAxiom(java.util.Set)
	 */
	public OWLEquivalentDataPropertiesAxiom getOWLEquivalentDataPropertiesAxiom(
			Set<? extends OWLDataPropertyExpression> properties) {
		return delegate.getOWLEquivalentDataPropertiesAxiom(properties);
	}

	/**
	 * @param properties
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentDataPropertiesAxiom(java.util.Set, java.util.Set)
	 */
	public OWLEquivalentDataPropertiesAxiom getOWLEquivalentDataPropertiesAxiom(
			Set<? extends OWLDataPropertyExpression> properties,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLEquivalentDataPropertiesAxiom(properties,
				annotations);
	}

	/**
	 * @param properties
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentDataPropertiesAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression[])
	 */
	public OWLEquivalentDataPropertiesAxiom getOWLEquivalentDataPropertiesAxiom(
			OWLDataPropertyExpression... properties) {
		return delegate.getOWLEquivalentDataPropertiesAxiom(properties);
	}

	/**
	 * @param propertyA
	 * @param propertyB
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentDataPropertiesAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLDataPropertyExpression)
	 */
	public OWLEquivalentDataPropertiesAxiom getOWLEquivalentDataPropertiesAxiom(
			OWLDataPropertyExpression propertyA,
			OWLDataPropertyExpression propertyB) {
		return delegate.getOWLEquivalentDataPropertiesAxiom(propertyA,
				propertyB);
	}

	/**
	 * @param propertyA
	 * @param propertyB
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLEquivalentDataPropertiesAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLDataPropertyExpression, java.util.Set)
	 */
	public OWLEquivalentDataPropertiesAxiom getOWLEquivalentDataPropertiesAxiom(
			OWLDataPropertyExpression propertyA,
			OWLDataPropertyExpression propertyB,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLEquivalentDataPropertiesAxiom(propertyA,
				propertyB, annotations);
	}

	/**
	 * @param dataProperties
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDisjointDataPropertiesAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression[])
	 */
	public OWLDisjointDataPropertiesAxiom getOWLDisjointDataPropertiesAxiom(
			OWLDataPropertyExpression... dataProperties) {
		return delegate.getOWLDisjointDataPropertiesAxiom(dataProperties);
	}

	/**
	 * @param properties
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDisjointDataPropertiesAxiom(java.util.Set)
	 */
	public OWLDisjointDataPropertiesAxiom getOWLDisjointDataPropertiesAxiom(
			Set<? extends OWLDataPropertyExpression> properties) {
		return delegate.getOWLDisjointDataPropertiesAxiom(properties);
	}

	/**
	 * @param properties
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDisjointDataPropertiesAxiom(java.util.Set, java.util.Set)
	 */
	public OWLDisjointDataPropertiesAxiom getOWLDisjointDataPropertiesAxiom(
			Set<? extends OWLDataPropertyExpression> properties,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLDisjointDataPropertiesAxiom(properties,
				annotations);
	}

	/**
	 * @param property
	 * @param domain
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataPropertyDomainAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLClassExpression)
	 */
	public OWLDataPropertyDomainAxiom getOWLDataPropertyDomainAxiom(
			OWLDataPropertyExpression property, OWLClassExpression domain) {
		return delegate.getOWLDataPropertyDomainAxiom(property, domain);
	}

	/**
	 * @param property
	 * @param domain
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataPropertyDomainAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLClassExpression, java.util.Set)
	 */
	public OWLDataPropertyDomainAxiom getOWLDataPropertyDomainAxiom(
			OWLDataPropertyExpression property, OWLClassExpression domain,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLDataPropertyDomainAxiom(property, domain,
				annotations);
	}

	/**
	 * @param property
	 * @param owlDataRange
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataPropertyRangeAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLDataRange)
	 */
	public OWLDataPropertyRangeAxiom getOWLDataPropertyRangeAxiom(
			OWLDataPropertyExpression property, OWLDataRange owlDataRange) {
		return delegate.getOWLDataPropertyRangeAxiom(property, owlDataRange);
	}

	/**
	 * @param property
	 * @param owlDataRange
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataPropertyRangeAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLDataRange, java.util.Set)
	 */
	public OWLDataPropertyRangeAxiom getOWLDataPropertyRangeAxiom(
			OWLDataPropertyExpression property, OWLDataRange owlDataRange,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLDataPropertyRangeAxiom(property, owlDataRange,
				annotations);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLFunctionalDataPropertyAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression)
	 */
	public OWLFunctionalDataPropertyAxiom getOWLFunctionalDataPropertyAxiom(
			OWLDataPropertyExpression property) {
		return delegate.getOWLFunctionalDataPropertyAxiom(property);
	}

	/**
	 * @param property
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLFunctionalDataPropertyAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, java.util.Set)
	 */
	public OWLFunctionalDataPropertyAxiom getOWLFunctionalDataPropertyAxiom(
			OWLDataPropertyExpression property,
			Set<? extends OWLAnnotation> annotations) {
		return delegate
				.getOWLFunctionalDataPropertyAxiom(property, annotations);
	}

	/**
	 * @param ce
	 * @param properties
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLHasKeyAxiom(org.semanticweb.owlapi.model.OWLClassExpression, java.util.Set)
	 */
	public OWLHasKeyAxiom getOWLHasKeyAxiom(OWLClassExpression ce,
			Set<? extends OWLPropertyExpression<?, ?>> properties) {
		return delegate.getOWLHasKeyAxiom(ce, properties);
	}

	/**
	 * @param ce
	 * @param properties
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLHasKeyAxiom(org.semanticweb.owlapi.model.OWLClassExpression, org.semanticweb.owlapi.model.OWLPropertyExpression<?,?>[])
	 */
	public OWLHasKeyAxiom getOWLHasKeyAxiom(OWLClassExpression ce,
			OWLPropertyExpression<?, ?>... properties) {
		return delegate.getOWLHasKeyAxiom(ce, properties);
	}

	/**
	 * @param ce
	 * @param objectProperties
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLHasKeyAxiom(org.semanticweb.owlapi.model.OWLClassExpression, java.util.Set, java.util.Set)
	 */
	public OWLHasKeyAxiom getOWLHasKeyAxiom(OWLClassExpression ce,
			Set<? extends OWLPropertyExpression<?, ?>> objectProperties,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLHasKeyAxiom(ce, objectProperties, annotations);
	}

	/**
	 * @param datatype
	 * @param dataRange
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeDefinitionAxiom(org.semanticweb.owlapi.model.OWLDatatype, org.semanticweb.owlapi.model.OWLDataRange)
	 */
	public OWLDatatypeDefinitionAxiom getOWLDatatypeDefinitionAxiom(
			OWLDatatype datatype, OWLDataRange dataRange) {
		return delegate.getOWLDatatypeDefinitionAxiom(datatype, dataRange);
	}

	/**
	 * @param datatype
	 * @param dataRange
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDatatypeDefinitionAxiom(org.semanticweb.owlapi.model.OWLDatatype, org.semanticweb.owlapi.model.OWLDataRange, java.util.Set)
	 */
	public OWLDatatypeDefinitionAxiom getOWLDatatypeDefinitionAxiom(
			OWLDatatype datatype, OWLDataRange dataRange,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLDatatypeDefinitionAxiom(datatype, dataRange,
				annotations);
	}

	/**
	 * @param individuals
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSameIndividualAxiom(java.util.Set)
	 */
	public OWLSameIndividualAxiom getOWLSameIndividualAxiom(
			Set<? extends OWLIndividual> individuals) {
		return delegate.getOWLSameIndividualAxiom(individuals);
	}

	/**
	 * @param individual
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSameIndividualAxiom(org.semanticweb.owlapi.model.OWLIndividual[])
	 */
	public OWLSameIndividualAxiom getOWLSameIndividualAxiom(
			OWLIndividual... individual) {
		return delegate.getOWLSameIndividualAxiom(individual);
	}

	/**
	 * @param individuals
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSameIndividualAxiom(java.util.Set, java.util.Set)
	 */
	public OWLSameIndividualAxiom getOWLSameIndividualAxiom(
			Set<? extends OWLIndividual> individuals,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLSameIndividualAxiom(individuals, annotations);
	}

	/**
	 * @param individuals
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDifferentIndividualsAxiom(java.util.Set)
	 */
	public OWLDifferentIndividualsAxiom getOWLDifferentIndividualsAxiom(
			Set<? extends OWLIndividual> individuals) {
		return delegate.getOWLDifferentIndividualsAxiom(individuals);
	}

	/**
	 * @param individuals
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDifferentIndividualsAxiom(org.semanticweb.owlapi.model.OWLIndividual[])
	 */
	public OWLDifferentIndividualsAxiom getOWLDifferentIndividualsAxiom(
			OWLIndividual... individuals) {
		return delegate.getOWLDifferentIndividualsAxiom(individuals);
	}

	/**
	 * @param individuals
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDifferentIndividualsAxiom(java.util.Set, java.util.Set)
	 */
	public OWLDifferentIndividualsAxiom getOWLDifferentIndividualsAxiom(
			Set<? extends OWLIndividual> individuals,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLDifferentIndividualsAxiom(individuals,
				annotations);
	}

	/**
	 * @param classExpression
	 * @param individual
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLClassAssertionAxiom(org.semanticweb.owlapi.model.OWLClassExpression, org.semanticweb.owlapi.model.OWLIndividual)
	 */
	public OWLClassAssertionAxiom getOWLClassAssertionAxiom(
			OWLClassExpression classExpression, OWLIndividual individual) {
		return delegate.getOWLClassAssertionAxiom(classExpression, individual);
	}

	/**
	 * @param classExpression
	 * @param individual
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLClassAssertionAxiom(org.semanticweb.owlapi.model.OWLClassExpression, org.semanticweb.owlapi.model.OWLIndividual, java.util.Set)
	 */
	public OWLClassAssertionAxiom getOWLClassAssertionAxiom(
			OWLClassExpression classExpression, OWLIndividual individual,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLClassAssertionAxiom(classExpression, individual,
				annotations);
	}

	/**
	 * @param property
	 * @param individual
	 * @param object
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectPropertyAssertionAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLIndividual, org.semanticweb.owlapi.model.OWLIndividual)
	 */
	public OWLObjectPropertyAssertionAxiom getOWLObjectPropertyAssertionAxiom(
			OWLObjectPropertyExpression property, OWLIndividual individual,
			OWLIndividual object) {
		return delegate.getOWLObjectPropertyAssertionAxiom(property,
				individual, object);
	}

	/**
	 * @param property
	 * @param individual
	 * @param object
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLObjectPropertyAssertionAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLIndividual, org.semanticweb.owlapi.model.OWLIndividual, java.util.Set)
	 */
	public OWLObjectPropertyAssertionAxiom getOWLObjectPropertyAssertionAxiom(
			OWLObjectPropertyExpression property, OWLIndividual individual,
			OWLIndividual object, Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLObjectPropertyAssertionAxiom(property,
				individual, object, annotations);
	}

	/**
	 * @param property
	 * @param subject
	 * @param object
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLNegativeObjectPropertyAssertionAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLIndividual, org.semanticweb.owlapi.model.OWLIndividual)
	 */
	public OWLNegativeObjectPropertyAssertionAxiom getOWLNegativeObjectPropertyAssertionAxiom(
			OWLObjectPropertyExpression property, OWLIndividual subject,
			OWLIndividual object) {
		return delegate.getOWLNegativeObjectPropertyAssertionAxiom(property,
				subject, object);
	}

	/**
	 * @param property
	 * @param subject
	 * @param object
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLNegativeObjectPropertyAssertionAxiom(org.semanticweb.owlapi.model.OWLObjectPropertyExpression, org.semanticweb.owlapi.model.OWLIndividual, org.semanticweb.owlapi.model.OWLIndividual, java.util.Set)
	 */
	public OWLNegativeObjectPropertyAssertionAxiom getOWLNegativeObjectPropertyAssertionAxiom(
			OWLObjectPropertyExpression property, OWLIndividual subject,
			OWLIndividual object, Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLNegativeObjectPropertyAssertionAxiom(property,
				subject, object, annotations);
	}

	/**
	 * @param property
	 * @param subject
	 * @param object
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataPropertyAssertionAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLIndividual, org.semanticweb.owlapi.model.OWLLiteral)
	 */
	public OWLDataPropertyAssertionAxiom getOWLDataPropertyAssertionAxiom(
			OWLDataPropertyExpression property, OWLIndividual subject,
			OWLLiteral object) {
		return delegate.getOWLDataPropertyAssertionAxiom(property, subject,
				object);
	}

	/**
	 * @param property
	 * @param subject
	 * @param object
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataPropertyAssertionAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLIndividual, org.semanticweb.owlapi.model.OWLLiteral, java.util.Set)
	 */
	public OWLDataPropertyAssertionAxiom getOWLDataPropertyAssertionAxiom(
			OWLDataPropertyExpression property, OWLIndividual subject,
			OWLLiteral object, Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLDataPropertyAssertionAxiom(property, subject,
				object, annotations);
	}

	/**
	 * @param property
	 * @param subject
	 * @param value
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataPropertyAssertionAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLIndividual, int)
	 */
	public OWLDataPropertyAssertionAxiom getOWLDataPropertyAssertionAxiom(
			OWLDataPropertyExpression property, OWLIndividual subject, int value) {
		return delegate.getOWLDataPropertyAssertionAxiom(property, subject,
				value);
	}

	/**
	 * @param property
	 * @param subject
	 * @param value
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataPropertyAssertionAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLIndividual, double)
	 */
	public OWLDataPropertyAssertionAxiom getOWLDataPropertyAssertionAxiom(
			OWLDataPropertyExpression property, OWLIndividual subject,
			double value) {
		return delegate.getOWLDataPropertyAssertionAxiom(property, subject,
				value);
	}

	/**
	 * @param property
	 * @param subject
	 * @param value
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataPropertyAssertionAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLIndividual, float)
	 */
	public OWLDataPropertyAssertionAxiom getOWLDataPropertyAssertionAxiom(
			OWLDataPropertyExpression property, OWLIndividual subject,
			float value) {
		return delegate.getOWLDataPropertyAssertionAxiom(property, subject,
				value);
	}

	/**
	 * @param property
	 * @param subject
	 * @param value
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataPropertyAssertionAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLIndividual, boolean)
	 */
	public OWLDataPropertyAssertionAxiom getOWLDataPropertyAssertionAxiom(
			OWLDataPropertyExpression property, OWLIndividual subject,
			boolean value) {
		return delegate.getOWLDataPropertyAssertionAxiom(property, subject,
				value);
	}

	/**
	 * @param property
	 * @param subject
	 * @param value
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLDataPropertyAssertionAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLIndividual, java.lang.String)
	 */
	public OWLDataPropertyAssertionAxiom getOWLDataPropertyAssertionAxiom(
			OWLDataPropertyExpression property, OWLIndividual subject,
			String value) {
		return delegate.getOWLDataPropertyAssertionAxiom(property, subject,
				value);
	}

	/**
	 * @param property
	 * @param subject
	 * @param object
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLNegativeDataPropertyAssertionAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLIndividual, org.semanticweb.owlapi.model.OWLLiteral)
	 */
	public OWLNegativeDataPropertyAssertionAxiom getOWLNegativeDataPropertyAssertionAxiom(
			OWLDataPropertyExpression property, OWLIndividual subject,
			OWLLiteral object) {
		return delegate.getOWLNegativeDataPropertyAssertionAxiom(property,
				subject, object);
	}

	/**
	 * @param property
	 * @param subject
	 * @param object
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLNegativeDataPropertyAssertionAxiom(org.semanticweb.owlapi.model.OWLDataPropertyExpression, org.semanticweb.owlapi.model.OWLIndividual, org.semanticweb.owlapi.model.OWLLiteral, java.util.Set)
	 */
	public OWLNegativeDataPropertyAssertionAxiom getOWLNegativeDataPropertyAssertionAxiom(
			OWLDataPropertyExpression property, OWLIndividual subject,
			OWLLiteral object, Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLNegativeDataPropertyAssertionAxiom(property,
				subject, object, annotations);
	}

	/**
	 * @param property
	 * @param value
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAnnotation(org.semanticweb.owlapi.model.OWLAnnotationProperty, org.semanticweb.owlapi.model.OWLAnnotationValue)
	 */
	public OWLAnnotation getOWLAnnotation(OWLAnnotationProperty property,
			OWLAnnotationValue value) {
		return delegate.getOWLAnnotation(property, value);
	}

	/**
	 * @param property
	 * @param value
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAnnotation(org.semanticweb.owlapi.model.OWLAnnotationProperty, org.semanticweb.owlapi.model.OWLAnnotationValue, java.util.Set)
	 */
	public OWLAnnotation getOWLAnnotation(OWLAnnotationProperty property,
			OWLAnnotationValue value, Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLAnnotation(property, value, annotations);
	}

	/**
	 * @param property
	 * @param subject
	 * @param value
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAnnotationAssertionAxiom(org.semanticweb.owlapi.model.OWLAnnotationProperty, org.semanticweb.owlapi.model.OWLAnnotationSubject, org.semanticweb.owlapi.model.OWLAnnotationValue)
	 */
	public OWLAnnotationAssertionAxiom getOWLAnnotationAssertionAxiom(
			OWLAnnotationProperty property, OWLAnnotationSubject subject,
			OWLAnnotationValue value) {
		return delegate
				.getOWLAnnotationAssertionAxiom(property, subject, value);
	}

	/**
	 * @param subject
	 * @param annotation
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAnnotationAssertionAxiom(org.semanticweb.owlapi.model.OWLAnnotationSubject, org.semanticweb.owlapi.model.OWLAnnotation)
	 */
	public OWLAnnotationAssertionAxiom getOWLAnnotationAssertionAxiom(
			OWLAnnotationSubject subject, OWLAnnotation annotation) {
		return delegate.getOWLAnnotationAssertionAxiom(subject, annotation);
	}

	/**
	 * @param property
	 * @param subject
	 * @param value
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAnnotationAssertionAxiom(org.semanticweb.owlapi.model.OWLAnnotationProperty, org.semanticweb.owlapi.model.OWLAnnotationSubject, org.semanticweb.owlapi.model.OWLAnnotationValue, java.util.Set)
	 */
	public OWLAnnotationAssertionAxiom getOWLAnnotationAssertionAxiom(
			OWLAnnotationProperty property, OWLAnnotationSubject subject,
			OWLAnnotationValue value, Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLAnnotationAssertionAxiom(property, subject,
				value, annotations);
	}

	/**
	 * @param subject
	 * @param annotation
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAnnotationAssertionAxiom(org.semanticweb.owlapi.model.OWLAnnotationSubject, org.semanticweb.owlapi.model.OWLAnnotation, java.util.Set)
	 */
	public OWLAnnotationAssertionAxiom getOWLAnnotationAssertionAxiom(
			OWLAnnotationSubject subject, OWLAnnotation annotation,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLAnnotationAssertionAxiom(subject, annotation,
				annotations);
	}

	/**
	 * @param subject
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getDeprecatedOWLAnnotationAssertionAxiom(org.semanticweb.owlapi.model.IRI)
	 */
	public OWLAnnotationAssertionAxiom getDeprecatedOWLAnnotationAssertionAxiom(
			IRI subject) {
		return delegate.getDeprecatedOWLAnnotationAssertionAxiom(subject);
	}

	/**
	 * @param importedOntologyIRI
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLImportsDeclaration(org.semanticweb.owlapi.model.IRI)
	 */
	public OWLImportsDeclaration getOWLImportsDeclaration(
			IRI importedOntologyIRI) {
		return delegate.getOWLImportsDeclaration(importedOntologyIRI);
	}

	/**
	 * @param prop
	 * @param domain
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAnnotationPropertyDomainAxiom(org.semanticweb.owlapi.model.OWLAnnotationProperty, org.semanticweb.owlapi.model.IRI)
	 */
	public OWLAnnotationPropertyDomainAxiom getOWLAnnotationPropertyDomainAxiom(
			OWLAnnotationProperty prop, IRI domain) {
		return delegate.getOWLAnnotationPropertyDomainAxiom(prop, domain);
	}

	/**
	 * @param prop
	 * @param domain
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAnnotationPropertyDomainAxiom(org.semanticweb.owlapi.model.OWLAnnotationProperty, org.semanticweb.owlapi.model.IRI, java.util.Set)
	 */
	public OWLAnnotationPropertyDomainAxiom getOWLAnnotationPropertyDomainAxiom(
			OWLAnnotationProperty prop, IRI domain,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLAnnotationPropertyDomainAxiom(prop, domain,
				annotations);
	}

	/**
	 * @param prop
	 * @param range
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAnnotationPropertyRangeAxiom(org.semanticweb.owlapi.model.OWLAnnotationProperty, org.semanticweb.owlapi.model.IRI)
	 */
	public OWLAnnotationPropertyRangeAxiom getOWLAnnotationPropertyRangeAxiom(
			OWLAnnotationProperty prop, IRI range) {
		return delegate.getOWLAnnotationPropertyRangeAxiom(prop, range);
	}

	/**
	 * @param prop
	 * @param range
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLAnnotationPropertyRangeAxiom(org.semanticweb.owlapi.model.OWLAnnotationProperty, org.semanticweb.owlapi.model.IRI, java.util.Set)
	 */
	public OWLAnnotationPropertyRangeAxiom getOWLAnnotationPropertyRangeAxiom(
			OWLAnnotationProperty prop, IRI range,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLAnnotationPropertyRangeAxiom(prop, range,
				annotations);
	}

	/**
	 * @param sub
	 * @param sup
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSubAnnotationPropertyOfAxiom(org.semanticweb.owlapi.model.OWLAnnotationProperty, org.semanticweb.owlapi.model.OWLAnnotationProperty)
	 */
	public OWLSubAnnotationPropertyOfAxiom getOWLSubAnnotationPropertyOfAxiom(
			OWLAnnotationProperty sub, OWLAnnotationProperty sup) {
		return delegate.getOWLSubAnnotationPropertyOfAxiom(sub, sup);
	}

	/**
	 * @param sub
	 * @param sup
	 * @param annotations
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#getOWLSubAnnotationPropertyOfAxiom(org.semanticweb.owlapi.model.OWLAnnotationProperty, org.semanticweb.owlapi.model.OWLAnnotationProperty, java.util.Set)
	 */
	public OWLSubAnnotationPropertyOfAxiom getOWLSubAnnotationPropertyOfAxiom(
			OWLAnnotationProperty sub, OWLAnnotationProperty sup,
			Set<? extends OWLAnnotation> annotations) {
		return delegate.getOWLSubAnnotationPropertyOfAxiom(sub, sup,
				annotations);
	}

	/**
	 * 
	 * @see org.semanticweb.owlapi.model.OWLDataFactory#purge()
	 */
	public void purge() {
		delegate.purge();
	}
	
	

}
