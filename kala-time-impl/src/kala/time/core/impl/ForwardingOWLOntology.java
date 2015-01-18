package kala.time.core.impl;

import java.util.List;
import java.util.Set;

import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotation;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationSubject;
import org.semanticweb.owlapi.model.OWLAnonymousIndividual;
import org.semanticweb.owlapi.model.OWLAsymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyExpression;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLDatatypeDefinitionAxiom;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDifferentIndividualsAxiom;
import org.semanticweb.owlapi.model.OWLDisjointClassesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLDisjointUnionAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLEquivalentClassesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentDataPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLEquivalentObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLHasKeyAxiom;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLIndividualAxiom;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLInverseObjectPropertiesAxiom;
import org.semanticweb.owlapi.model.OWLIrreflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLLogicalAxiom;
import org.semanticweb.owlapi.model.OWLMutableOntology;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLNegativeDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLNegativeObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObject;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLObjectVisitor;
import org.semanticweb.owlapi.model.OWLObjectVisitorEx;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.OWLOntologyChangeException;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLReflexiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLSameIndividualAxiom;
import org.semanticweb.owlapi.model.OWLSubAnnotationPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.model.OWLSubDataPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSubObjectPropertyOfAxiom;
import org.semanticweb.owlapi.model.OWLSymmetricObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLTransitiveObjectPropertyAxiom;
import org.semanticweb.owlapi.model.UnknownOWLOntologyException;

public class ForwardingOWLOntology implements OWLMutableOntology {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3967155257733886533L;
	private final OWLMutableOntology delegate;
	
	protected ForwardingOWLOntology(OWLMutableOntology delegate) {
		this.delegate = delegate;
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLObject#getAnonymousIndividuals()
	 */
	public Set<OWLAnonymousIndividual> getAnonymousIndividuals() {
		return delegate.getAnonymousIndividuals();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getOWLOntologyManager()
	 */
	public OWLOntologyManager getOWLOntologyManager() {
		return delegate.getOWLOntologyManager();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getOntologyID()
	 */
	public OWLOntologyID getOntologyID() {
		return delegate.getOntologyID();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#isAnonymous()
	 */
	public boolean isAnonymous() {
		return delegate.isAnonymous();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAnnotations()
	 */
	public Set<OWLAnnotation> getAnnotations() {
		return delegate.getAnnotations();
	}

	/**
	 * @param o
	 * @return
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	public int compareTo(OWLObject o) {
		return delegate.compareTo(o);
	}

	/**
	 * @return
	 * @throws UnknownOWLOntologyException
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDirectImportsDocuments()
	 */
	public Set<IRI> getDirectImportsDocuments()
			throws UnknownOWLOntologyException {
		return delegate.getDirectImportsDocuments();
	}

	/**
	 * @return
	 * @throws UnknownOWLOntologyException
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDirectImports()
	 */
	public Set<OWLOntology> getDirectImports()
			throws UnknownOWLOntologyException {
		return delegate.getDirectImports();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLObject#getNestedClassExpressions()
	 */
	public Set<OWLClassExpression> getNestedClassExpressions() {
		return delegate.getNestedClassExpressions();
	}

	/**
	 * @param visitor
	 * @see org.semanticweb.owlapi.model.OWLObject#accept(org.semanticweb.owlapi.model.OWLObjectVisitor)
	 */
	public void accept(OWLObjectVisitor visitor) {
		delegate.accept(visitor);
	}

	/**
	 * @param visitor
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLObject#accept(org.semanticweb.owlapi.model.OWLObjectVisitorEx)
	 */
	public <O> O accept(OWLObjectVisitorEx<O> visitor) {
		return delegate.accept(visitor);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLObject#isTopEntity()
	 */
	public boolean isTopEntity() {
		return delegate.isTopEntity();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLObject#isBottomEntity()
	 */
	public boolean isBottomEntity() {
		return delegate.isBottomEntity();
	}

	/**
	 * @return
	 * @throws UnknownOWLOntologyException
	 * @see org.semanticweb.owlapi.model.OWLOntology#getImports()
	 */
	public Set<OWLOntology> getImports() throws UnknownOWLOntologyException {
		return delegate.getImports();
	}

	/**
	 * @return
	 * @throws UnknownOWLOntologyException
	 * @see org.semanticweb.owlapi.model.OWLOntology#getImportsClosure()
	 */
	public Set<OWLOntology> getImportsClosure()
			throws UnknownOWLOntologyException {
		return delegate.getImportsClosure();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getImportsDeclarations()
	 */
	public Set<OWLImportsDeclaration> getImportsDeclarations() {
		return delegate.getImportsDeclarations();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#isEmpty()
	 */
	public boolean isEmpty() {
		return delegate.isEmpty();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAxioms()
	 */
	public Set<OWLAxiom> getAxioms() {
		return delegate.getAxioms();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAxiomCount()
	 */
	public int getAxiomCount() {
		return delegate.getAxiomCount();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getLogicalAxioms()
	 */
	public Set<OWLLogicalAxiom> getLogicalAxioms() {
		return delegate.getLogicalAxioms();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getLogicalAxiomCount()
	 */
	public int getLogicalAxiomCount() {
		return delegate.getLogicalAxiomCount();
	}

	/**
	 * @param axiomType
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAxioms(org.semanticweb.owlapi.model.AxiomType)
	 */
	public <T extends OWLAxiom> Set<T> getAxioms(AxiomType<T> axiomType) {
		return delegate.getAxioms(axiomType);
	}

	/**
	 * @param axiomType
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAxioms(org.semanticweb.owlapi.model.AxiomType, boolean)
	 */
	public <T extends OWLAxiom> Set<T> getAxioms(AxiomType<T> axiomType,
			boolean includeImportsClosure) {
		return delegate.getAxioms(axiomType, includeImportsClosure);
	}

	/**
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getTBoxAxioms(boolean)
	 */
	public Set<OWLAxiom> getTBoxAxioms(boolean includeImportsClosure) {
		return delegate.getTBoxAxioms(includeImportsClosure);
	}

	/**
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getABoxAxioms(boolean)
	 */
	public Set<OWLAxiom> getABoxAxioms(boolean includeImportsClosure) {
		return delegate.getABoxAxioms(includeImportsClosure);
	}

	/**
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getRBoxAxioms(boolean)
	 */
	public Set<OWLAxiom> getRBoxAxioms(boolean includeImportsClosure) {
		return delegate.getRBoxAxioms(includeImportsClosure);
	}

	/**
	 * @param axiomType
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAxiomCount(org.semanticweb.owlapi.model.AxiomType)
	 */
	public <T extends OWLAxiom> int getAxiomCount(AxiomType<T> axiomType) {
		return delegate.getAxiomCount(axiomType);
	}

	/**
	 * @param axiomType
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAxiomCount(org.semanticweb.owlapi.model.AxiomType, boolean)
	 */
	public <T extends OWLAxiom> int getAxiomCount(AxiomType<T> axiomType,
			boolean includeImportsClosure) {
		return delegate.getAxiomCount(axiomType, includeImportsClosure);
	}

	/**
	 * @param axiom
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsAxiom(org.semanticweb.owlapi.model.OWLAxiom)
	 */
	public boolean containsAxiom(OWLAxiom axiom) {
		return delegate.containsAxiom(axiom);
	}

	/**
	 * @param axiom
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsAxiom(org.semanticweb.owlapi.model.OWLAxiom, boolean)
	 */
	public boolean containsAxiom(OWLAxiom axiom, boolean includeImportsClosure) {
		return delegate.containsAxiom(axiom, includeImportsClosure);
	}

	/**
	 * @param axiom
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsAxiomIgnoreAnnotations(org.semanticweb.owlapi.model.OWLAxiom)
	 */
	public boolean containsAxiomIgnoreAnnotations(OWLAxiom axiom) {
		return delegate.containsAxiomIgnoreAnnotations(axiom);
	}

	/**
	 * @param axiom
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAxiomsIgnoreAnnotations(org.semanticweb.owlapi.model.OWLAxiom)
	 */
	public Set<OWLAxiom> getAxiomsIgnoreAnnotations(OWLAxiom axiom) {
		return delegate.getAxiomsIgnoreAnnotations(axiom);
	}

	/**
	 * @param axiom
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAxiomsIgnoreAnnotations(org.semanticweb.owlapi.model.OWLAxiom, boolean)
	 */
	public Set<OWLAxiom> getAxiomsIgnoreAnnotations(OWLAxiom axiom,
			boolean includeImportsClosure) {
		return delegate
				.getAxiomsIgnoreAnnotations(axiom, includeImportsClosure);
	}

	/**
	 * @param axiom
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsAxiomIgnoreAnnotations(org.semanticweb.owlapi.model.OWLAxiom, boolean)
	 */
	public boolean containsAxiomIgnoreAnnotations(OWLAxiom axiom,
			boolean includeImportsClosure) {
		return delegate.containsAxiomIgnoreAnnotations(axiom,
				includeImportsClosure);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getGeneralClassAxioms()
	 */
	public Set<OWLClassAxiom> getGeneralClassAxioms() {
		return delegate.getGeneralClassAxioms();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getSignature()
	 */
	public Set<OWLEntity> getSignature() {
		return delegate.getSignature();
	}

	/**
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getSignature(boolean)
	 */
	public Set<OWLEntity> getSignature(boolean includeImportsClosure) {
		return delegate.getSignature(includeImportsClosure);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getClassesInSignature()
	 */
	public Set<OWLClass> getClassesInSignature() {
		return delegate.getClassesInSignature();
	}

	/**
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getClassesInSignature(boolean)
	 */
	public Set<OWLClass> getClassesInSignature(boolean includeImportsClosure) {
		return delegate.getClassesInSignature(includeImportsClosure);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getObjectPropertiesInSignature()
	 */
	public Set<OWLObjectProperty> getObjectPropertiesInSignature() {
		return delegate.getObjectPropertiesInSignature();
	}

	/**
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getObjectPropertiesInSignature(boolean)
	 */
	public Set<OWLObjectProperty> getObjectPropertiesInSignature(
			boolean includeImportsClosure) {
		return delegate.getObjectPropertiesInSignature(includeImportsClosure);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDataPropertiesInSignature()
	 */
	public Set<OWLDataProperty> getDataPropertiesInSignature() {
		return delegate.getDataPropertiesInSignature();
	}

	/**
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDataPropertiesInSignature(boolean)
	 */
	public Set<OWLDataProperty> getDataPropertiesInSignature(
			boolean includeImportsClosure) {
		return delegate.getDataPropertiesInSignature(includeImportsClosure);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getIndividualsInSignature()
	 */
	public Set<OWLNamedIndividual> getIndividualsInSignature() {
		return delegate.getIndividualsInSignature();
	}

	/**
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getIndividualsInSignature(boolean)
	 */
	public Set<OWLNamedIndividual> getIndividualsInSignature(
			boolean includeImportsClosure) {
		return delegate.getIndividualsInSignature(includeImportsClosure);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getReferencedAnonymousIndividuals()
	 */
	public Set<OWLAnonymousIndividual> getReferencedAnonymousIndividuals() {
		return delegate.getReferencedAnonymousIndividuals();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDatatypesInSignature()
	 */
	public Set<OWLDatatype> getDatatypesInSignature() {
		return delegate.getDatatypesInSignature();
	}

	/**
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDatatypesInSignature(boolean)
	 */
	public Set<OWLDatatype> getDatatypesInSignature(
			boolean includeImportsClosure) {
		return delegate.getDatatypesInSignature(includeImportsClosure);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAnnotationPropertiesInSignature()
	 */
	public Set<OWLAnnotationProperty> getAnnotationPropertiesInSignature() {
		return delegate.getAnnotationPropertiesInSignature();
	}

	/**
	 * @param owlEntity
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getReferencingAxioms(org.semanticweb.owlapi.model.OWLEntity)
	 */
	public Set<OWLAxiom> getReferencingAxioms(OWLEntity owlEntity) {
		return delegate.getReferencingAxioms(owlEntity);
	}

	/**
	 * @param owlEntity
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getReferencingAxioms(org.semanticweb.owlapi.model.OWLEntity, boolean)
	 */
	public Set<OWLAxiom> getReferencingAxioms(OWLEntity owlEntity,
			boolean includeImportsClosure) {
		return delegate.getReferencingAxioms(owlEntity, includeImportsClosure);
	}

	/**
	 * @param individual
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getReferencingAxioms(org.semanticweb.owlapi.model.OWLAnonymousIndividual)
	 */
	public Set<OWLAxiom> getReferencingAxioms(OWLAnonymousIndividual individual) {
		return delegate.getReferencingAxioms(individual);
	}

	/**
	 * @param owlEntity
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsEntityInSignature(org.semanticweb.owlapi.model.OWLEntity)
	 */
	public boolean containsEntityInSignature(OWLEntity owlEntity) {
		return delegate.containsEntityInSignature(owlEntity);
	}

	/**
	 * @param owlEntity
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsEntityInSignature(org.semanticweb.owlapi.model.OWLEntity, boolean)
	 */
	public boolean containsEntityInSignature(OWLEntity owlEntity,
			boolean includeImportsClosure) {
		return delegate.containsEntityInSignature(owlEntity,
				includeImportsClosure);
	}

	/**
	 * @param entityIRI
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsEntityInSignature(org.semanticweb.owlapi.model.IRI)
	 */
	public boolean containsEntityInSignature(IRI entityIRI) {
		return delegate.containsEntityInSignature(entityIRI);
	}

	/**
	 * @param entityIRI
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsEntityInSignature(org.semanticweb.owlapi.model.IRI, boolean)
	 */
	public boolean containsEntityInSignature(IRI entityIRI,
			boolean includeImportsClosure) {
		return delegate.containsEntityInSignature(entityIRI,
				includeImportsClosure);
	}

	/**
	 * @param owlEntity
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#isDeclared(org.semanticweb.owlapi.model.OWLEntity)
	 */
	public boolean isDeclared(OWLEntity owlEntity) {
		return delegate.isDeclared(owlEntity);
	}

	/**
	 * @param owlEntity
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#isDeclared(org.semanticweb.owlapi.model.OWLEntity, boolean)
	 */
	public boolean isDeclared(OWLEntity owlEntity, boolean includeImportsClosure) {
		return delegate.isDeclared(owlEntity, includeImportsClosure);
	}

	/**
	 * @param owlClassIRI
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsClassInSignature(org.semanticweb.owlapi.model.IRI)
	 */
	public boolean containsClassInSignature(IRI owlClassIRI) {
		return delegate.containsClassInSignature(owlClassIRI);
	}

	/**
	 * @param owlClassIRI
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsClassInSignature(org.semanticweb.owlapi.model.IRI, boolean)
	 */
	public boolean containsClassInSignature(IRI owlClassIRI,
			boolean includeImportsClosure) {
		return delegate.containsClassInSignature(owlClassIRI,
				includeImportsClosure);
	}

	/**
	 * @param owlObjectPropertyIRI
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsObjectPropertyInSignature(org.semanticweb.owlapi.model.IRI)
	 */
	public boolean containsObjectPropertyInSignature(IRI owlObjectPropertyIRI) {
		return delegate.containsObjectPropertyInSignature(owlObjectPropertyIRI);
	}

	/**
	 * @param owlObjectPropertyIRI
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsObjectPropertyInSignature(org.semanticweb.owlapi.model.IRI, boolean)
	 */
	public boolean containsObjectPropertyInSignature(IRI owlObjectPropertyIRI,
			boolean includeImportsClosure) {
		return delegate.containsObjectPropertyInSignature(owlObjectPropertyIRI,
				includeImportsClosure);
	}

	/**
	 * @param owlDataPropertyIRI
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsDataPropertyInSignature(org.semanticweb.owlapi.model.IRI)
	 */
	public boolean containsDataPropertyInSignature(IRI owlDataPropertyIRI) {
		return delegate.containsDataPropertyInSignature(owlDataPropertyIRI);
	}

	/**
	 * @param owlDataPropertyIRI
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsDataPropertyInSignature(org.semanticweb.owlapi.model.IRI, boolean)
	 */
	public boolean containsDataPropertyInSignature(IRI owlDataPropertyIRI,
			boolean includeImportsClosure) {
		return delegate.containsDataPropertyInSignature(owlDataPropertyIRI,
				includeImportsClosure);
	}

	/**
	 * @param owlAnnotationPropertyIRI
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsAnnotationPropertyInSignature(org.semanticweb.owlapi.model.IRI)
	 */
	public boolean containsAnnotationPropertyInSignature(
			IRI owlAnnotationPropertyIRI) {
		return delegate
				.containsAnnotationPropertyInSignature(owlAnnotationPropertyIRI);
	}

	/**
	 * @param owlAnnotationPropertyIRI
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsAnnotationPropertyInSignature(org.semanticweb.owlapi.model.IRI, boolean)
	 */
	public boolean containsAnnotationPropertyInSignature(
			IRI owlAnnotationPropertyIRI, boolean includeImportsClosure) {
		return delegate.containsAnnotationPropertyInSignature(
				owlAnnotationPropertyIRI, includeImportsClosure);
	}

	/**
	 * @param owlIndividualIRI
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsIndividualInSignature(org.semanticweb.owlapi.model.IRI)
	 */
	public boolean containsIndividualInSignature(IRI owlIndividualIRI) {
		return delegate.containsIndividualInSignature(owlIndividualIRI);
	}

	/**
	 * @param owlIndividualIRI
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsIndividualInSignature(org.semanticweb.owlapi.model.IRI, boolean)
	 */
	public boolean containsIndividualInSignature(IRI owlIndividualIRI,
			boolean includeImportsClosure) {
		return delegate.containsIndividualInSignature(owlIndividualIRI,
				includeImportsClosure);
	}

	/**
	 * @param owlDatatypeIRI
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsDatatypeInSignature(org.semanticweb.owlapi.model.IRI)
	 */
	public boolean containsDatatypeInSignature(IRI owlDatatypeIRI) {
		return delegate.containsDatatypeInSignature(owlDatatypeIRI);
	}

	/**
	 * @param owlDatatypeIRI
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#containsDatatypeInSignature(org.semanticweb.owlapi.model.IRI, boolean)
	 */
	public boolean containsDatatypeInSignature(IRI owlDatatypeIRI,
			boolean includeImportsClosure) {
		return delegate.containsDatatypeInSignature(owlDatatypeIRI,
				includeImportsClosure);
	}

	/**
	 * @param iri
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getEntitiesInSignature(org.semanticweb.owlapi.model.IRI)
	 */
	public Set<OWLEntity> getEntitiesInSignature(IRI iri) {
		return delegate.getEntitiesInSignature(iri);
	}

	/**
	 * @param iri
	 * @param includeImportsClosure
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getEntitiesInSignature(org.semanticweb.owlapi.model.IRI, boolean)
	 */
	public Set<OWLEntity> getEntitiesInSignature(IRI iri,
			boolean includeImportsClosure) {
		return delegate.getEntitiesInSignature(iri, includeImportsClosure);
	}

	/**
	 * @param cls
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAxioms(org.semanticweb.owlapi.model.OWLClass)
	 */
	public Set<OWLClassAxiom> getAxioms(OWLClass cls) {
		return delegate.getAxioms(cls);
	}

	/**
	 * @param prop
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAxioms(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLObjectPropertyAxiom> getAxioms(
			OWLObjectPropertyExpression prop) {
		return delegate.getAxioms(prop);
	}

	/**
	 * @param prop
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAxioms(org.semanticweb.owlapi.model.OWLDataProperty)
	 */
	public Set<OWLDataPropertyAxiom> getAxioms(OWLDataProperty prop) {
		return delegate.getAxioms(prop);
	}

	/**
	 * @param individual
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAxioms(org.semanticweb.owlapi.model.OWLIndividual)
	 */
	public Set<OWLIndividualAxiom> getAxioms(OWLIndividual individual) {
		return delegate.getAxioms(individual);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAxioms(org.semanticweb.owlapi.model.OWLAnnotationProperty)
	 */
	public Set<OWLAnnotationAxiom> getAxioms(OWLAnnotationProperty property) {
		return delegate.getAxioms(property);
	}

	/**
	 * @param datatype
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAxioms(org.semanticweb.owlapi.model.OWLDatatype)
	 */
	public Set<OWLDatatypeDefinitionAxiom> getAxioms(OWLDatatype datatype) {
		return delegate.getAxioms(datatype);
	}

	/**
	 * @param subProperty
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getSubAnnotationPropertyOfAxioms(org.semanticweb.owlapi.model.OWLAnnotationProperty)
	 */
	public Set<OWLSubAnnotationPropertyOfAxiom> getSubAnnotationPropertyOfAxioms(
			OWLAnnotationProperty subProperty) {
		return delegate.getSubAnnotationPropertyOfAxioms(subProperty);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAnnotationPropertyDomainAxioms(org.semanticweb.owlapi.model.OWLAnnotationProperty)
	 */
	public Set<OWLAnnotationPropertyDomainAxiom> getAnnotationPropertyDomainAxioms(
			OWLAnnotationProperty property) {
		return delegate.getAnnotationPropertyDomainAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAnnotationPropertyRangeAxioms(org.semanticweb.owlapi.model.OWLAnnotationProperty)
	 */
	public Set<OWLAnnotationPropertyRangeAxiom> getAnnotationPropertyRangeAxioms(
			OWLAnnotationProperty property) {
		return delegate.getAnnotationPropertyRangeAxioms(property);
	}

	/**
	 * @param subject
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDeclarationAxioms(org.semanticweb.owlapi.model.OWLEntity)
	 */
	public Set<OWLDeclarationAxiom> getDeclarationAxioms(OWLEntity subject) {
		return delegate.getDeclarationAxioms(subject);
	}

	/**
	 * @param entity
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAnnotationAssertionAxioms(org.semanticweb.owlapi.model.OWLAnnotationSubject)
	 */
	public Set<OWLAnnotationAssertionAxiom> getAnnotationAssertionAxioms(
			OWLAnnotationSubject entity) {
		return delegate.getAnnotationAssertionAxioms(entity);
	}

	/**
	 * @param cls
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getSubClassAxiomsForSubClass(org.semanticweb.owlapi.model.OWLClass)
	 */
	public Set<OWLSubClassOfAxiom> getSubClassAxiomsForSubClass(OWLClass cls) {
		return delegate.getSubClassAxiomsForSubClass(cls);
	}

	/**
	 * @param cls
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getSubClassAxiomsForSuperClass(org.semanticweb.owlapi.model.OWLClass)
	 */
	public Set<OWLSubClassOfAxiom> getSubClassAxiomsForSuperClass(OWLClass cls) {
		return delegate.getSubClassAxiomsForSuperClass(cls);
	}

	/**
	 * @param cls
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getEquivalentClassesAxioms(org.semanticweb.owlapi.model.OWLClass)
	 */
	public Set<OWLEquivalentClassesAxiom> getEquivalentClassesAxioms(
			OWLClass cls) {
		return delegate.getEquivalentClassesAxioms(cls);
	}

	/**
	 * @param cls
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDisjointClassesAxioms(org.semanticweb.owlapi.model.OWLClass)
	 */
	public Set<OWLDisjointClassesAxiom> getDisjointClassesAxioms(OWLClass cls) {
		return delegate.getDisjointClassesAxioms(cls);
	}

	/**
	 * @param owlClass
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDisjointUnionAxioms(org.semanticweb.owlapi.model.OWLClass)
	 */
	public Set<OWLDisjointUnionAxiom> getDisjointUnionAxioms(OWLClass owlClass) {
		return delegate.getDisjointUnionAxioms(owlClass);
	}

	/**
	 * @param cls
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getHasKeyAxioms(org.semanticweb.owlapi.model.OWLClass)
	 */
	public Set<OWLHasKeyAxiom> getHasKeyAxioms(OWLClass cls) {
		return delegate.getHasKeyAxioms(cls);
	}

	/**
	 * @param subProperty
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getObjectSubPropertyAxiomsForSubProperty(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLSubObjectPropertyOfAxiom> getObjectSubPropertyAxiomsForSubProperty(
			OWLObjectPropertyExpression subProperty) {
		return delegate.getObjectSubPropertyAxiomsForSubProperty(subProperty);
	}

	/**
	 * @param superProperty
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getObjectSubPropertyAxiomsForSuperProperty(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLSubObjectPropertyOfAxiom> getObjectSubPropertyAxiomsForSuperProperty(
			OWLObjectPropertyExpression superProperty) {
		return delegate
				.getObjectSubPropertyAxiomsForSuperProperty(superProperty);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getObjectPropertyDomainAxioms(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLObjectPropertyDomainAxiom> getObjectPropertyDomainAxioms(
			OWLObjectPropertyExpression property) {
		return delegate.getObjectPropertyDomainAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getObjectPropertyRangeAxioms(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLObjectPropertyRangeAxiom> getObjectPropertyRangeAxioms(
			OWLObjectPropertyExpression property) {
		return delegate.getObjectPropertyRangeAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getInverseObjectPropertyAxioms(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLInverseObjectPropertiesAxiom> getInverseObjectPropertyAxioms(
			OWLObjectPropertyExpression property) {
		return delegate.getInverseObjectPropertyAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getEquivalentObjectPropertiesAxioms(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLEquivalentObjectPropertiesAxiom> getEquivalentObjectPropertiesAxioms(
			OWLObjectPropertyExpression property) {
		return delegate.getEquivalentObjectPropertiesAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDisjointObjectPropertiesAxioms(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLDisjointObjectPropertiesAxiom> getDisjointObjectPropertiesAxioms(
			OWLObjectPropertyExpression property) {
		return delegate.getDisjointObjectPropertiesAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getFunctionalObjectPropertyAxioms(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLFunctionalObjectPropertyAxiom> getFunctionalObjectPropertyAxioms(
			OWLObjectPropertyExpression property) {
		return delegate.getFunctionalObjectPropertyAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getInverseFunctionalObjectPropertyAxioms(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLInverseFunctionalObjectPropertyAxiom> getInverseFunctionalObjectPropertyAxioms(
			OWLObjectPropertyExpression property) {
		return delegate.getInverseFunctionalObjectPropertyAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getSymmetricObjectPropertyAxioms(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLSymmetricObjectPropertyAxiom> getSymmetricObjectPropertyAxioms(
			OWLObjectPropertyExpression property) {
		return delegate.getSymmetricObjectPropertyAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getAsymmetricObjectPropertyAxioms(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLAsymmetricObjectPropertyAxiom> getAsymmetricObjectPropertyAxioms(
			OWLObjectPropertyExpression property) {
		return delegate.getAsymmetricObjectPropertyAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getReflexiveObjectPropertyAxioms(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLReflexiveObjectPropertyAxiom> getReflexiveObjectPropertyAxioms(
			OWLObjectPropertyExpression property) {
		return delegate.getReflexiveObjectPropertyAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getIrreflexiveObjectPropertyAxioms(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLIrreflexiveObjectPropertyAxiom> getIrreflexiveObjectPropertyAxioms(
			OWLObjectPropertyExpression property) {
		return delegate.getIrreflexiveObjectPropertyAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getTransitiveObjectPropertyAxioms(org.semanticweb.owlapi.model.OWLObjectPropertyExpression)
	 */
	public Set<OWLTransitiveObjectPropertyAxiom> getTransitiveObjectPropertyAxioms(
			OWLObjectPropertyExpression property) {
		return delegate.getTransitiveObjectPropertyAxioms(property);
	}

	/**
	 * @param subProperty
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDataSubPropertyAxiomsForSubProperty(org.semanticweb.owlapi.model.OWLDataProperty)
	 */
	public Set<OWLSubDataPropertyOfAxiom> getDataSubPropertyAxiomsForSubProperty(
			OWLDataProperty subProperty) {
		return delegate.getDataSubPropertyAxiomsForSubProperty(subProperty);
	}

	/**
	 * @param superProperty
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDataSubPropertyAxiomsForSuperProperty(org.semanticweb.owlapi.model.OWLDataPropertyExpression)
	 */
	public Set<OWLSubDataPropertyOfAxiom> getDataSubPropertyAxiomsForSuperProperty(
			OWLDataPropertyExpression superProperty) {
		return delegate.getDataSubPropertyAxiomsForSuperProperty(superProperty);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDataPropertyDomainAxioms(org.semanticweb.owlapi.model.OWLDataProperty)
	 */
	public Set<OWLDataPropertyDomainAxiom> getDataPropertyDomainAxioms(
			OWLDataProperty property) {
		return delegate.getDataPropertyDomainAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDataPropertyRangeAxioms(org.semanticweb.owlapi.model.OWLDataProperty)
	 */
	public Set<OWLDataPropertyRangeAxiom> getDataPropertyRangeAxioms(
			OWLDataProperty property) {
		return delegate.getDataPropertyRangeAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getEquivalentDataPropertiesAxioms(org.semanticweb.owlapi.model.OWLDataProperty)
	 */
	public Set<OWLEquivalentDataPropertiesAxiom> getEquivalentDataPropertiesAxioms(
			OWLDataProperty property) {
		return delegate.getEquivalentDataPropertiesAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDisjointDataPropertiesAxioms(org.semanticweb.owlapi.model.OWLDataProperty)
	 */
	public Set<OWLDisjointDataPropertiesAxiom> getDisjointDataPropertiesAxioms(
			OWLDataProperty property) {
		return delegate.getDisjointDataPropertiesAxioms(property);
	}

	/**
	 * @param property
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getFunctionalDataPropertyAxioms(org.semanticweb.owlapi.model.OWLDataPropertyExpression)
	 */
	public Set<OWLFunctionalDataPropertyAxiom> getFunctionalDataPropertyAxioms(
			OWLDataPropertyExpression property) {
		return delegate.getFunctionalDataPropertyAxioms(property);
	}

	/**
	 * @param individual
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getClassAssertionAxioms(org.semanticweb.owlapi.model.OWLIndividual)
	 */
	public Set<OWLClassAssertionAxiom> getClassAssertionAxioms(
			OWLIndividual individual) {
		return delegate.getClassAssertionAxioms(individual);
	}

	/**
	 * @param ce
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getClassAssertionAxioms(org.semanticweb.owlapi.model.OWLClassExpression)
	 */
	public Set<OWLClassAssertionAxiom> getClassAssertionAxioms(
			OWLClassExpression ce) {
		return delegate.getClassAssertionAxioms(ce);
	}

	/**
	 * @param individual
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDataPropertyAssertionAxioms(org.semanticweb.owlapi.model.OWLIndividual)
	 */
	public Set<OWLDataPropertyAssertionAxiom> getDataPropertyAssertionAxioms(
			OWLIndividual individual) {
		return delegate.getDataPropertyAssertionAxioms(individual);
	}

	/**
	 * @param individual
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getObjectPropertyAssertionAxioms(org.semanticweb.owlapi.model.OWLIndividual)
	 */
	public Set<OWLObjectPropertyAssertionAxiom> getObjectPropertyAssertionAxioms(
			OWLIndividual individual) {
		return delegate.getObjectPropertyAssertionAxioms(individual);
	}

	/**
	 * @param individual
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getNegativeObjectPropertyAssertionAxioms(org.semanticweb.owlapi.model.OWLIndividual)
	 */
	public Set<OWLNegativeObjectPropertyAssertionAxiom> getNegativeObjectPropertyAssertionAxioms(
			OWLIndividual individual) {
		return delegate.getNegativeObjectPropertyAssertionAxioms(individual);
	}

	/**
	 * @param individual
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getNegativeDataPropertyAssertionAxioms(org.semanticweb.owlapi.model.OWLIndividual)
	 */
	public Set<OWLNegativeDataPropertyAssertionAxiom> getNegativeDataPropertyAssertionAxioms(
			OWLIndividual individual) {
		return delegate.getNegativeDataPropertyAssertionAxioms(individual);
	}

	/**
	 * @param individual
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getSameIndividualAxioms(org.semanticweb.owlapi.model.OWLIndividual)
	 */
	public Set<OWLSameIndividualAxiom> getSameIndividualAxioms(
			OWLIndividual individual) {
		return delegate.getSameIndividualAxioms(individual);
	}

	/**
	 * @param individual
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDifferentIndividualAxioms(org.semanticweb.owlapi.model.OWLIndividual)
	 */
	public Set<OWLDifferentIndividualsAxiom> getDifferentIndividualAxioms(
			OWLIndividual individual) {
		return delegate.getDifferentIndividualAxioms(individual);
	}

	/**
	 * @param datatype
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntology#getDatatypeDefinitions(org.semanticweb.owlapi.model.OWLDatatype)
	 */
	public Set<OWLDatatypeDefinitionAxiom> getDatatypeDefinitions(
			OWLDatatype datatype) {
		return delegate.getDatatypeDefinitions(datatype);
	}

	@Override
	public List<OWLOntologyChange> applyChange(OWLOntologyChange change)
			throws OWLOntologyChangeException {
		return delegate.applyChange(change);
	}

	@Override
	public List<OWLOntologyChange> applyChanges(List<OWLOntologyChange> changes)
			throws OWLOntologyChangeException {
		return delegate.applyChanges(changes);
	}

}
