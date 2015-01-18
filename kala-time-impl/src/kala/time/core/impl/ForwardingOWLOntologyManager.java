package kala.time.core.impl;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.semanticweb.owlapi.io.OWLOntologyDocumentSource;
import org.semanticweb.owlapi.io.OWLOntologyDocumentTarget;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.ImpendingOWLOntologyChangeListener;
import org.semanticweb.owlapi.model.MissingImportListener;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLImportsDeclaration;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyChange;
import org.semanticweb.owlapi.model.OWLOntologyChangeBroadcastStrategy;
import org.semanticweb.owlapi.model.OWLOntologyChangeListener;
import org.semanticweb.owlapi.model.OWLOntologyChangeProgressListener;
import org.semanticweb.owlapi.model.OWLOntologyChangesVetoedListener;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFactory;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyID;
import org.semanticweb.owlapi.model.OWLOntologyIRIMapper;
import org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration;
import org.semanticweb.owlapi.model.OWLOntologyLoaderListener;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyRenameException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLOntologyStorer;
import org.semanticweb.owlapi.model.UnknownOWLOntologyException;
import org.semanticweb.owlapi.model.UnloadableImportException;

public class ForwardingOWLOntologyManager implements OWLOntologyManager {
	
	private final OWLOntologyManager delegate;
	
	protected ForwardingOWLOntologyManager(OWLOntologyManager delegate) {
		this.delegate = delegate;
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getOWLDataFactory()
	 */
	public OWLDataFactory getOWLDataFactory() {
		return delegate.getOWLDataFactory();
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getOntologies()
	 */
	public Set<OWLOntology> getOntologies() {
		return delegate.getOntologies();
	}

	/**
	 * @param axiom
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getOntologies(org.semanticweb.owlapi.model.OWLAxiom)
	 */
	public Set<OWLOntology> getOntologies(OWLAxiom axiom) {
		return delegate.getOntologies(axiom);
	}

	/**
	 * @param ontology
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getVersions(org.semanticweb.owlapi.model.IRI)
	 */
	public Set<OWLOntology> getVersions(IRI ontology) {
		return delegate.getVersions(ontology);
	}

	/**
	 * @param ontologyIRI
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#contains(org.semanticweb.owlapi.model.IRI)
	 */
	public boolean contains(IRI ontologyIRI) {
		return delegate.contains(ontologyIRI);
	}

	/**
	 * @param id
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#contains(org.semanticweb.owlapi.model.OWLOntologyID)
	 */
	public boolean contains(OWLOntologyID id) {
		return delegate.contains(id);
	}

	/**
	 * @param ontologyVersionIRI
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#containsVersion(org.semanticweb.owlapi.model.IRI)
	 */
	public boolean containsVersion(IRI ontologyVersionIRI) {
		return delegate.containsVersion(ontologyVersionIRI);
	}

	/**
	 * @param ontologyVersionIRI
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getOntologyIDsByVersion(org.semanticweb.owlapi.model.IRI)
	 */
	public Set<OWLOntologyID> getOntologyIDsByVersion(IRI ontologyVersionIRI) {
		return delegate.getOntologyIDsByVersion(ontologyVersionIRI);
	}

	/**
	 * @param ontologyIRI
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getOntology(org.semanticweb.owlapi.model.IRI)
	 */
	public OWLOntology getOntology(IRI ontologyIRI) {
		return delegate.getOntology(ontologyIRI);
	}

	/**
	 * @param ontologyID
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getOntology(org.semanticweb.owlapi.model.OWLOntologyID)
	 */
	public OWLOntology getOntology(OWLOntologyID ontologyID) {
		return delegate.getOntology(ontologyID);
	}

	/**
	 * @param declaration
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getImportedOntology(org.semanticweb.owlapi.model.OWLImportsDeclaration)
	 */
	public OWLOntology getImportedOntology(OWLImportsDeclaration declaration) {
		return delegate.getImportedOntology(declaration);
	}

	/**
	 * @param ontology
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getDirectImports(org.semanticweb.owlapi.model.OWLOntology)
	 */
	public Set<OWLOntology> getDirectImports(OWLOntology ontology) {
		return delegate.getDirectImports(ontology);
	}

	/**
	 * @param ontology
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getImports(org.semanticweb.owlapi.model.OWLOntology)
	 */
	public Set<OWLOntology> getImports(OWLOntology ontology) {
		return delegate.getImports(ontology);
	}

	/**
	 * @param ontology
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getImportsClosure(org.semanticweb.owlapi.model.OWLOntology)
	 */
	public Set<OWLOntology> getImportsClosure(OWLOntology ontology) {
		return delegate.getImportsClosure(ontology);
	}

	/**
	 * @param ontology
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getSortedImportsClosure(org.semanticweb.owlapi.model.OWLOntology)
	 */
	public List<OWLOntology> getSortedImportsClosure(OWLOntology ontology) {
		return delegate.getSortedImportsClosure(ontology);
	}

	/**
	 * @param changes
	 * @return
	 * @throws OWLOntologyRenameException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#applyChanges(java.util.List)
	 */
	public List<OWLOntologyChange> applyChanges(
			List<? extends OWLOntologyChange> changes)
			throws OWLOntologyRenameException {
		return delegate.applyChanges(changes);
	}

	/**
	 * @param ont
	 * @param axioms
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#addAxioms(org.semanticweb.owlapi.model.OWLOntology, java.util.Set)
	 */
	public List<OWLOntologyChange> addAxioms(OWLOntology ont,
			Set<? extends OWLAxiom> axioms) {
		return delegate.addAxioms(ont, axioms);
	}

	/**
	 * @param ont
	 * @param axiom
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#addAxiom(org.semanticweb.owlapi.model.OWLOntology, org.semanticweb.owlapi.model.OWLAxiom)
	 */
	public List<OWLOntologyChange> addAxiom(OWLOntology ont, OWLAxiom axiom) {
		return delegate.addAxiom(ont, axiom);
	}

	/**
	 * @param ont
	 * @param axiom
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#removeAxiom(org.semanticweb.owlapi.model.OWLOntology, org.semanticweb.owlapi.model.OWLAxiom)
	 */
	public List<OWLOntologyChange> removeAxiom(OWLOntology ont, OWLAxiom axiom) {
		return delegate.removeAxiom(ont, axiom);
	}

	/**
	 * @param ont
	 * @param axioms
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#removeAxioms(org.semanticweb.owlapi.model.OWLOntology, java.util.Set)
	 */
	public List<OWLOntologyChange> removeAxioms(OWLOntology ont,
			Set<? extends OWLAxiom> axioms) {
		return delegate.removeAxioms(ont, axioms);
	}

	/**
	 * @param change
	 * @return
	 * @throws OWLOntologyRenameException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#applyChange(org.semanticweb.owlapi.model.OWLOntologyChange)
	 */
	public List<OWLOntologyChange> applyChange(OWLOntologyChange change)
			throws OWLOntologyRenameException {
		return delegate.applyChange(change);
	}

	/**
	 * @return
	 * @throws OWLOntologyCreationException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#createOntology()
	 */
	public OWLOntology createOntology() throws OWLOntologyCreationException {
		return delegate.createOntology();
	}

	/**
	 * @param axioms
	 * @return
	 * @throws OWLOntologyCreationException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#createOntology(java.util.Set)
	 */
	public OWLOntology createOntology(Set<OWLAxiom> axioms)
			throws OWLOntologyCreationException {
		return delegate.createOntology(axioms);
	}

	/**
	 * @param axioms
	 * @param ontologyIRI
	 * @return
	 * @throws OWLOntologyCreationException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#createOntology(java.util.Set, org.semanticweb.owlapi.model.IRI)
	 */
	public OWLOntology createOntology(Set<OWLAxiom> axioms, IRI ontologyIRI)
			throws OWLOntologyCreationException {
		return delegate.createOntology(axioms, ontologyIRI);
	}

	/**
	 * @param ontologyIRI
	 * @return
	 * @throws OWLOntologyCreationException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#createOntology(org.semanticweb.owlapi.model.IRI)
	 */
	public OWLOntology createOntology(IRI ontologyIRI)
			throws OWLOntologyCreationException {
		return delegate.createOntology(ontologyIRI);
	}

	/**
	 * @param ontologyID
	 * @return
	 * @throws OWLOntologyCreationException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#createOntology(org.semanticweb.owlapi.model.OWLOntologyID)
	 */
	public OWLOntology createOntology(OWLOntologyID ontologyID)
			throws OWLOntologyCreationException {
		return delegate.createOntology(ontologyID);
	}

	/**
	 * @param ontologyIRI
	 * @param ontologies
	 * @param copyLogicalAxiomsOnly
	 * @return
	 * @throws OWLOntologyCreationException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#createOntology(org.semanticweb.owlapi.model.IRI, java.util.Set, boolean)
	 */
	public OWLOntology createOntology(IRI ontologyIRI,
			Set<OWLOntology> ontologies, boolean copyLogicalAxiomsOnly)
			throws OWLOntologyCreationException {
		return delegate.createOntology(ontologyIRI, ontologies,
				copyLogicalAxiomsOnly);
	}

	/**
	 * @param ontologyIRI
	 * @param ontologies
	 * @return
	 * @throws OWLOntologyCreationException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#createOntology(org.semanticweb.owlapi.model.IRI, java.util.Set)
	 */
	public OWLOntology createOntology(IRI ontologyIRI,
			Set<OWLOntology> ontologies) throws OWLOntologyCreationException {
		return delegate.createOntology(ontologyIRI, ontologies);
	}

	/**
	 * @param ontologyIRI
	 * @return
	 * @throws OWLOntologyCreationException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#loadOntology(org.semanticweb.owlapi.model.IRI)
	 */
	public OWLOntology loadOntology(IRI ontologyIRI)
			throws OWLOntologyCreationException {
		return delegate.loadOntology(ontologyIRI);
	}

	/**
	 * @param documentIRI
	 * @return
	 * @throws OWLOntologyCreationException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#loadOntologyFromOntologyDocument(org.semanticweb.owlapi.model.IRI)
	 */
	public OWLOntology loadOntologyFromOntologyDocument(IRI documentIRI)
			throws OWLOntologyCreationException {
		return delegate.loadOntologyFromOntologyDocument(documentIRI);
	}

	/**
	 * @param file
	 * @return
	 * @throws OWLOntologyCreationException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#loadOntologyFromOntologyDocument(java.io.File)
	 */
	public OWLOntology loadOntologyFromOntologyDocument(File file)
			throws OWLOntologyCreationException {
		return delegate.loadOntologyFromOntologyDocument(file);
	}

	/**
	 * @param inputStream
	 * @return
	 * @throws OWLOntologyCreationException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#loadOntologyFromOntologyDocument(java.io.InputStream)
	 */
	public OWLOntology loadOntologyFromOntologyDocument(InputStream inputStream)
			throws OWLOntologyCreationException {
		return delegate.loadOntologyFromOntologyDocument(inputStream);
	}

	/**
	 * @param documentSource
	 * @return
	 * @throws OWLOntologyCreationException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#loadOntologyFromOntologyDocument(org.semanticweb.owlapi.io.OWLOntologyDocumentSource)
	 */
	public OWLOntology loadOntologyFromOntologyDocument(
			OWLOntologyDocumentSource documentSource)
			throws OWLOntologyCreationException {
		return delegate.loadOntologyFromOntologyDocument(documentSource);
	}

	/**
	 * @param documentSource
	 * @param config
	 * @return
	 * @throws OWLOntologyCreationException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#loadOntologyFromOntologyDocument(org.semanticweb.owlapi.io.OWLOntologyDocumentSource, org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration)
	 */
	public OWLOntology loadOntologyFromOntologyDocument(
			OWLOntologyDocumentSource documentSource,
			OWLOntologyLoaderConfiguration config)
			throws OWLOntologyCreationException {
		return delegate
				.loadOntologyFromOntologyDocument(documentSource, config);
	}

	/**
	 * @param ontology
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#removeOntology(org.semanticweb.owlapi.model.OWLOntology)
	 */
	public void removeOntology(OWLOntology ontology) {
		delegate.removeOntology(ontology);
	}

	/**
	 * @param ontologyID
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#removeOntology(org.semanticweb.owlapi.model.OWLOntologyID)
	 */
	public void removeOntology(OWLOntologyID ontologyID) {
		delegate.removeOntology(ontologyID);
	}

	/**
	 * @param ontology
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getOntologyDocumentIRI(org.semanticweb.owlapi.model.OWLOntology)
	 */
	public IRI getOntologyDocumentIRI(OWLOntology ontology) {
		return delegate.getOntologyDocumentIRI(ontology);
	}

	/**
	 * @param ontology
	 * @param documentIRI
	 * @throws UnknownOWLOntologyException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#setOntologyDocumentIRI(org.semanticweb.owlapi.model.OWLOntology, org.semanticweb.owlapi.model.IRI)
	 */
	public void setOntologyDocumentIRI(OWLOntology ontology, IRI documentIRI)
			throws UnknownOWLOntologyException {
		delegate.setOntologyDocumentIRI(ontology, documentIRI);
	}

	/**
	 * @param ontology
	 * @return
	 * @throws UnknownOWLOntologyException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getOntologyFormat(org.semanticweb.owlapi.model.OWLOntology)
	 */
	public OWLOntologyFormat getOntologyFormat(OWLOntology ontology)
			throws UnknownOWLOntologyException {
		return delegate.getOntologyFormat(ontology);
	}

	/**
	 * @param ontology
	 * @param ontologyFormat
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#setOntologyFormat(org.semanticweb.owlapi.model.OWLOntology, org.semanticweb.owlapi.model.OWLOntologyFormat)
	 */
	public void setOntologyFormat(OWLOntology ontology,
			OWLOntologyFormat ontologyFormat) {
		delegate.setOntologyFormat(ontology, ontologyFormat);
	}

	/**
	 * @param ontology
	 * @throws OWLOntologyStorageException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#saveOntology(org.semanticweb.owlapi.model.OWLOntology)
	 */
	public void saveOntology(OWLOntology ontology)
			throws OWLOntologyStorageException {
		delegate.saveOntology(ontology);
	}

	/**
	 * @param ontology
	 * @param documentIRI
	 * @throws OWLOntologyStorageException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#saveOntology(org.semanticweb.owlapi.model.OWLOntology, org.semanticweb.owlapi.model.IRI)
	 */
	public void saveOntology(OWLOntology ontology, IRI documentIRI)
			throws OWLOntologyStorageException {
		delegate.saveOntology(ontology, documentIRI);
	}

	/**
	 * @param ontology
	 * @param outputStream
	 * @throws OWLOntologyStorageException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#saveOntology(org.semanticweb.owlapi.model.OWLOntology, java.io.OutputStream)
	 */
	public void saveOntology(OWLOntology ontology, OutputStream outputStream)
			throws OWLOntologyStorageException {
		delegate.saveOntology(ontology, outputStream);
	}

	/**
	 * @param ontology
	 * @param ontologyFormat
	 * @throws OWLOntologyStorageException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#saveOntology(org.semanticweb.owlapi.model.OWLOntology, org.semanticweb.owlapi.model.OWLOntologyFormat)
	 */
	public void saveOntology(OWLOntology ontology,
			OWLOntologyFormat ontologyFormat)
			throws OWLOntologyStorageException {
		delegate.saveOntology(ontology, ontologyFormat);
	}

	/**
	 * @param ontology
	 * @param ontologyFormat
	 * @param documentIRI
	 * @throws OWLOntologyStorageException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#saveOntology(org.semanticweb.owlapi.model.OWLOntology, org.semanticweb.owlapi.model.OWLOntologyFormat, org.semanticweb.owlapi.model.IRI)
	 */
	public void saveOntology(OWLOntology ontology,
			OWLOntologyFormat ontologyFormat, IRI documentIRI)
			throws OWLOntologyStorageException {
		delegate.saveOntology(ontology, ontologyFormat, documentIRI);
	}

	/**
	 * @param ontology
	 * @param ontologyFormat
	 * @param outputStream
	 * @throws OWLOntologyStorageException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#saveOntology(org.semanticweb.owlapi.model.OWLOntology, org.semanticweb.owlapi.model.OWLOntologyFormat, java.io.OutputStream)
	 */
	public void saveOntology(OWLOntology ontology,
			OWLOntologyFormat ontologyFormat, OutputStream outputStream)
			throws OWLOntologyStorageException {
		delegate.saveOntology(ontology, ontologyFormat, outputStream);
	}

	/**
	 * @param ontology
	 * @param documentTarget
	 * @throws OWLOntologyStorageException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#saveOntology(org.semanticweb.owlapi.model.OWLOntology, org.semanticweb.owlapi.io.OWLOntologyDocumentTarget)
	 */
	public void saveOntology(OWLOntology ontology,
			OWLOntologyDocumentTarget documentTarget)
			throws OWLOntologyStorageException {
		delegate.saveOntology(ontology, documentTarget);
	}

	/**
	 * @param ontology
	 * @param ontologyFormat
	 * @param documentTarget
	 * @throws OWLOntologyStorageException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#saveOntology(org.semanticweb.owlapi.model.OWLOntology, org.semanticweb.owlapi.model.OWLOntologyFormat, org.semanticweb.owlapi.io.OWLOntologyDocumentTarget)
	 */
	public void saveOntology(OWLOntology ontology,
			OWLOntologyFormat ontologyFormat,
			OWLOntologyDocumentTarget documentTarget)
			throws OWLOntologyStorageException {
		delegate.saveOntology(ontology, ontologyFormat, documentTarget);
	}

	/**
	 * @param mapper
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#addIRIMapper(org.semanticweb.owlapi.model.OWLOntologyIRIMapper)
	 */
	public void addIRIMapper(OWLOntologyIRIMapper mapper) {
		delegate.addIRIMapper(mapper);
	}

	/**
	 * @param mapper
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#removeIRIMapper(org.semanticweb.owlapi.model.OWLOntologyIRIMapper)
	 */
	public void removeIRIMapper(OWLOntologyIRIMapper mapper) {
		delegate.removeIRIMapper(mapper);
	}

	/**
	 * 
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#clearIRIMappers()
	 */
	public void clearIRIMappers() {
		delegate.clearIRIMappers();
	}

	/**
	 * @param factory
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#addOntologyFactory(org.semanticweb.owlapi.model.OWLOntologyFactory)
	 */
	public void addOntologyFactory(OWLOntologyFactory factory) {
		delegate.addOntologyFactory(factory);
	}

	/**
	 * @param factory
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#removeOntologyFactory(org.semanticweb.owlapi.model.OWLOntologyFactory)
	 */
	public void removeOntologyFactory(OWLOntologyFactory factory) {
		delegate.removeOntologyFactory(factory);
	}

	/**
	 * @return
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#getOntologyFactories()
	 */
	public Collection<OWLOntologyFactory> getOntologyFactories() {
		return delegate.getOntologyFactories();
	}

	/**
	 * @param storer
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#addOntologyStorer(org.semanticweb.owlapi.model.OWLOntologyStorer)
	 */
	public void addOntologyStorer(OWLOntologyStorer storer) {
		delegate.addOntologyStorer(storer);
	}

	/**
	 * @param storer
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#removeOntologyStorer(org.semanticweb.owlapi.model.OWLOntologyStorer)
	 */
	public void removeOntologyStorer(OWLOntologyStorer storer) {
		delegate.removeOntologyStorer(storer);
	}

	/**
	 * @param listener
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#addOntologyChangeListener(org.semanticweb.owlapi.model.OWLOntologyChangeListener)
	 */
	public void addOntologyChangeListener(OWLOntologyChangeListener listener) {
		delegate.addOntologyChangeListener(listener);
	}

	/**
	 * @param listener
	 * @param strategy
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#addOntologyChangeListener(org.semanticweb.owlapi.model.OWLOntologyChangeListener, org.semanticweb.owlapi.model.OWLOntologyChangeBroadcastStrategy)
	 */
	public void addOntologyChangeListener(OWLOntologyChangeListener listener,
			OWLOntologyChangeBroadcastStrategy strategy) {
		delegate.addOntologyChangeListener(listener, strategy);
	}

	/**
	 * @param listener
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#addImpendingOntologyChangeListener(org.semanticweb.owlapi.model.ImpendingOWLOntologyChangeListener)
	 */
	public void addImpendingOntologyChangeListener(
			ImpendingOWLOntologyChangeListener listener) {
		delegate.addImpendingOntologyChangeListener(listener);
	}

	/**
	 * @param listener
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#removeImpendingOntologyChangeListener(org.semanticweb.owlapi.model.ImpendingOWLOntologyChangeListener)
	 */
	public void removeImpendingOntologyChangeListener(
			ImpendingOWLOntologyChangeListener listener) {
		delegate.removeImpendingOntologyChangeListener(listener);
	}

	/**
	 * @param listener
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#addOntologyChangesVetoedListener(org.semanticweb.owlapi.model.OWLOntologyChangesVetoedListener)
	 */
	public void addOntologyChangesVetoedListener(
			OWLOntologyChangesVetoedListener listener) {
		delegate.addOntologyChangesVetoedListener(listener);
	}

	/**
	 * @param listener
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#removeOntologyChangesVetoedListener(org.semanticweb.owlapi.model.OWLOntologyChangesVetoedListener)
	 */
	public void removeOntologyChangesVetoedListener(
			OWLOntologyChangesVetoedListener listener) {
		delegate.removeOntologyChangesVetoedListener(listener);
	}

	/**
	 * @param strategy
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#setDefaultChangeBroadcastStrategy(org.semanticweb.owlapi.model.OWLOntologyChangeBroadcastStrategy)
	 */
	public void setDefaultChangeBroadcastStrategy(
			OWLOntologyChangeBroadcastStrategy strategy) {
		delegate.setDefaultChangeBroadcastStrategy(strategy);
	}

	/**
	 * @param listener
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#removeOntologyChangeListener(org.semanticweb.owlapi.model.OWLOntologyChangeListener)
	 */
	public void removeOntologyChangeListener(OWLOntologyChangeListener listener) {
		delegate.removeOntologyChangeListener(listener);
	}

	/**
	 * @param declaration
	 * @throws UnloadableImportException
	 * @deprecated
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#makeLoadImportRequest(org.semanticweb.owlapi.model.OWLImportsDeclaration)
	 */
	public void makeLoadImportRequest(OWLImportsDeclaration declaration)
			throws UnloadableImportException {
		delegate.makeLoadImportRequest(declaration);
	}

	/**
	 * @param declaration
	 * @param configuration
	 * @throws UnloadableImportException
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#makeLoadImportRequest(org.semanticweb.owlapi.model.OWLImportsDeclaration, org.semanticweb.owlapi.model.OWLOntologyLoaderConfiguration)
	 */
	public void makeLoadImportRequest(OWLImportsDeclaration declaration,
			OWLOntologyLoaderConfiguration configuration)
			throws UnloadableImportException {
		delegate.makeLoadImportRequest(declaration, configuration);
	}

	/**
	 * @param b
	 * @deprecated
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#setSilentMissingImportsHandling(boolean)
	 */
	public void setSilentMissingImportsHandling(boolean b) {
		delegate.setSilentMissingImportsHandling(b);
	}

	/**
	 * @return
	 * @deprecated
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#isSilentMissingImportsHandling()
	 */
	public boolean isSilentMissingImportsHandling() {
		return delegate.isSilentMissingImportsHandling();
	}

	/**
	 * @param listener
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#addMissingImportListener(org.semanticweb.owlapi.model.MissingImportListener)
	 */
	public void addMissingImportListener(MissingImportListener listener) {
		delegate.addMissingImportListener(listener);
	}

	/**
	 * @param listener
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#removeMissingImportListener(org.semanticweb.owlapi.model.MissingImportListener)
	 */
	public void removeMissingImportListener(MissingImportListener listener) {
		delegate.removeMissingImportListener(listener);
	}

	/**
	 * @param listener
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#addOntologyLoaderListener(org.semanticweb.owlapi.model.OWLOntologyLoaderListener)
	 */
	public void addOntologyLoaderListener(OWLOntologyLoaderListener listener) {
		delegate.addOntologyLoaderListener(listener);
	}

	/**
	 * @param listener
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#removeOntologyLoaderListener(org.semanticweb.owlapi.model.OWLOntologyLoaderListener)
	 */
	public void removeOntologyLoaderListener(OWLOntologyLoaderListener listener) {
		delegate.removeOntologyLoaderListener(listener);
	}

	/**
	 * @param listener
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#addOntologyChangeProgessListener(org.semanticweb.owlapi.model.OWLOntologyChangeProgressListener)
	 */
	public void addOntologyChangeProgessListener(
			OWLOntologyChangeProgressListener listener) {
		delegate.addOntologyChangeProgessListener(listener);
	}

	/**
	 * @param listener
	 * @see org.semanticweb.owlapi.model.OWLOntologyManager#removeOntologyChangeProgessListener(org.semanticweb.owlapi.model.OWLOntologyChangeProgressListener)
	 */
	public void removeOntologyChangeProgessListener(
			OWLOntologyChangeProgressListener listener) {
		delegate.removeOntologyChangeProgessListener(listener);
	}

}
