package kala.time.examples;

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxOntologyFormat;
import org.joda.time.DateTime;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.RDFXMLOntologyFormat;
import org.semanticweb.owlapi.io.StreamDocumentTarget;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDatatype;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import kala.time.core.TemporalDataFactory;
import kala.time.core.TemporalOntology;
import kala.time.core.TemporalOntologyManager;
import kala.time.core.impl.AddTemporalAxiomImpl;
import kala.time.core.impl.TemporalOntologyManagerImpl;
import kala.time.model.FluentDataProperty;
import kala.time.model.FluentDataPropertyAssertionAxiom;
import kala.time.model.FluentDataPropertyDomainAxiom;
import kala.time.model.FluentDataPropertyRangeAxiom;
import kala.time.model.FluentObjectProperty;
import kala.time.model.FluentObjectPropertyAssertionAxiom;
import kala.time.model.FluentObjectPropertyDomainAxiom;
import kala.time.model.FluentObjectPropertyRangeAxiom;
import kala.time.model.InstantTimeAssertionAxiom;
import kala.time.model.IntervalStartAssertionAxiom;
import kala.time.model.TemporalEntityDeclarationAxiom;
import kala.time.model.TimeInstant;
import kala.time.model.TimeInterval;
import kala.time.serialization.Serializer;
import kala.time.serialization.fluents.TOWLRepresentationScheme;
import kala.time.serialization.fluents.impl.FluentsRepresentationSchemeImpl;
import kala.time.serialization.fluents.impl.FluentsSerializerImpl;
import kala.time.serialization.reification.IRINamePatternConstant;
import kala.time.serialization.reification.ObjectRelationDirection;
import kala.time.serialization.reification.ReificationRepresentationScheme;
import kala.time.serialization.reification.SubjectRelationDirection;
import kala.time.serialization.reification.impl.ReificationConfigurationDataPropertyItemImpl;
import kala.time.serialization.reification.impl.ReificationConfigurationImpl;
import kala.time.serialization.reification.impl.ReificationConfigurationObjectPropertyItemImpl;
import kala.time.serialization.reification.impl.ReificationRepresentationSchemeImpl;
import kala.time.serialization.reification.impl.ReificationSerializerImpl;

public class Example1 {

	/**
	 * @param args
	 * @throws OWLOntologyCreationException 
	 * @throws OWLOntologyStorageException 
	 */
	public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException {
		TemporalOntologyManager ontologyManager = 
				new TemporalOntologyManagerImpl(OWLManager.createOWLOntologyManager());
		TemporalDataFactory factory = ontologyManager.getTemporalDataFactory();
		TemporalOntology ontology = ontologyManager.createTemporalOntology();
		
		IRI towlIRI = IRI.create("http://www.towl.org/towl");
		ontologyManager.addIRIMapper(new SimpleIRIMapper(
				towlIRI, IRI.create("file:///Users/sven/Thesis/kala/towl.owl")));
		
		// Create configuration.
		ReificationConfigurationImpl conf = new ReificationConfigurationImpl();

		IRI ceoOfIRI = IRI.create("#ceoOf");
		IRI hasTitleIRI = IRI.create("#hasTitle");

		conf.addFluentObjectProperty(ceoOfIRI, new ReificationConfigurationObjectPropertyItemImpl(
				new IRINamePatternConstant(IRI.create("#ceo")), SubjectRelationDirection.REIF_TO_SUBJECT,
				new IRINamePatternConstant(IRI.create("#company")), ObjectRelationDirection.REIF_TO_OBJECT));
		conf.addFluentDataProperty(hasTitleIRI, new ReificationConfigurationDataPropertyItemImpl(
				new IRINamePatternConstant(IRI.create("#title")), SubjectRelationDirection.SUBJECT_TO_REIF,
				new IRINamePatternConstant(IRI.create("#value"))));
		
		// Get representation scheme.
		ReificationRepresentationScheme scheme = new ReificationRepresentationSchemeImpl(conf);
		
		Serializer rs = new ReificationSerializerImpl(ontologyManager, scheme);

		// Test declaration of fluent object property ceoOf.
		OWLObjectProperty sp = factory.getOWLObjectProperty(ceoOfIRI);
		FluentObjectProperty ceoOf = factory.getFluentObjectProperty(sp);
		TemporalEntityDeclarationAxiom fop_decl_ax = factory.getTemporalEntityDeclarationAxiom(ceoOf);
		ontology.applyChange(new AddTemporalAxiomImpl(fop_decl_ax));
		
		// Test domain axiom on fluent object property ceoOf.
		OWLClass personClass = factory.getOWLClass(IRI.create("#Person"));
		FluentObjectPropertyDomainAxiom domainAxiom = factory.getFluentObjectPropertyDomainAxiom(ceoOf, personClass);
		ontology.applyChange(new AddTemporalAxiomImpl(domainAxiom));

		// Test range axiom on fluent object property ceoOf.
		OWLClass companyClass = factory.getOWLClass(IRI.create("#Company"));
		FluentObjectPropertyRangeAxiom rangeAxiom = factory.getFluentObjectPropertyRangeAxiom(ceoOf, companyClass);
		ontology.applyChange(new AddTemporalAxiomImpl(rangeAxiom));

		// Test declaration of fluent data property hasTitle.
		OWLDataProperty ht = factory.getOWLDataProperty(hasTitleIRI);
		FluentDataProperty hasTitle = factory.getFluentDataProperty(ht);
		TemporalEntityDeclarationAxiom fdp_decl_ax = factory.getTemporalEntityDeclarationAxiom(hasTitle);
		ontology.applyChange(new AddTemporalAxiomImpl(fdp_decl_ax));
		
		// Test domain axiom on fluent data property hasTitle.
		FluentDataPropertyDomainAxiom dpDomainAxiom = factory.getFluentDataPropertyDomainAxiom(hasTitle, personClass);
		ontology.applyChange(new AddTemporalAxiomImpl(dpDomainAxiom));
		
		// Test datarange axiom on fluent data property hasTitle.
		OWLDatatype stringType = factory.getOWLDatatype(OWL2Datatype.XSD_STRING.getIRI());
		FluentDataPropertyRangeAxiom dpRangeAxiom = factory.getFluentDataPropertyRangeAxiom(hasTitle, stringType);
		ontology.applyChange(new AddTemporalAxiomImpl(dpRangeAxiom));
		
		// Test instant time assignment.
		TimeInstant t1 = factory.getTimeInstant();
		DateTime time = DateTime.parse("2013-01-01T12:00:00");
		TemporalEntityDeclarationAxiom t1_decl = factory.getTemporalEntityDeclarationAxiom(t1);
		ontology.applyChange(new AddTemporalAxiomImpl(t1_decl));
		InstantTimeAssertionAxiom t1_ax = factory.getInstantTimeAssertionAxiom(t1, time);
		ontology.applyChange(new AddTemporalAxiomImpl(t1_ax));
		
		// Declare interval.
		TimeInterval i1 = factory.getTimeInterval();
		TemporalEntityDeclarationAxiom i1_decl = factory.getTemporalEntityDeclarationAxiom(i1);
		ontology.applyChange(new AddTemporalAxiomImpl(i1_decl));
		
		// Set i1 start to t1.
		IntervalStartAssertionAxiom i1_start_ax = factory.getIntervalStartAssertionAxiom(i1, t1);
		ontology.applyChange(new AddTemporalAxiomImpl(i1_start_ax));
		
		// Test fluent object property assertion.
		OWLIndividual sam = factory.getOWLNamedIndividual(IRI.create("#sam"));
		OWLIndividual ibm = factory.getOWLNamedIndividual(IRI.create("#ibm"));
		FluentObjectPropertyAssertionAxiom samAssertion = 
				factory.getFluentObjectPropertyAssertionAxiom(sam, ceoOf, ibm, i1);
		ontology.applyChange(new AddTemporalAxiomImpl(samAssertion));
		
		// Test fluent data property assertion.
		OWLLiteral title = factory.getOWLLiteral("Chief Executive Officer");
		FluentDataPropertyAssertionAxiom titleAssertion =
				factory.getFluentDataPropertyAssertionAxiom(sam, hasTitle, title, i1);
		ontology.applyChange(new AddTemporalAxiomImpl(titleAssertion));
		
		// rs.visit(ontology);
		// OWLOntology result = rs.createOntology(IRI.create("#testOntology"));
		
		// ontologyManager.saveOntology(result, new ManchesterOWLSyntaxOntologyFormat(), new StreamDocumentTarget(System.out));

		
		Serializer fs = new FluentsSerializerImpl(ontologyManager, TOWLRepresentationScheme.TOWL_3_0); // new FluentsRepresentationSchemeImpl());
		fs.visit(ontology);
		OWLOntology result2 = fs.createOntology(IRI.create("http://www.example.org/towltest"));
		ontologyManager.applyChange(new AddImport(result2, factory.getOWLImportsDeclaration(towlIRI)));
		ontologyManager.saveOntology(result2, new ManchesterOWLSyntaxOntologyFormat(), new StreamDocumentTarget(System.out));
		ontologyManager.saveOntology(result2, new OWLXMLOntologyFormat(), IRI.create("file:///Users/sven/towltest.owl")); // new StreamDocumentTarget(System.out));
	}

}
