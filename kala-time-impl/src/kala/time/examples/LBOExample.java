package kala.time.examples;

import java.util.Arrays;
import java.util.Set;

import kala.time.core.TemporalDataFactory;
import kala.time.core.TemporalOntology;
import kala.time.core.TemporalOntologyManager;
import kala.time.core.impl.TemporalOntologyManagerImpl;
import kala.time.model.FluentDataProperty;
import kala.time.model.FluentObjectProperty;
import kala.time.model.TimeInstant;
import kala.time.model.TimeInterval;
import kala.time.serialization.Parser;
import kala.time.serialization.Serializer;
import kala.time.serialization.fluents.TOWLRepresentationScheme;
import kala.time.serialization.fluents.impl.FluentsParserImpl;
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

import org.coode.owlapi.manchesterowlsyntax.ManchesterOWLSyntaxOntologyFormat;
import org.joda.time.DateTime;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLXMLOntologyFormat;
import org.semanticweb.owlapi.io.StreamDocumentTarget;
import org.semanticweb.owlapi.model.AddImport;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLDisjointUnionAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.util.SimpleIRIMapper;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import com.google.common.collect.Sets;

public class LBOExample {

	/**
	 * @param args
	 * @throws OWLOntologyCreationException 
	 * @throws OWLOntologyStorageException 
	 */
	public static void main(String[] args) throws OWLOntologyCreationException, OWLOntologyStorageException {
		
		// Create temporal ontology manager.
		TemporalOntologyManager ontologyManager = 
				new TemporalOntologyManagerImpl(OWLManager.createOWLOntologyManager());

		// Set tOWL mapping.
		IRI towlIRI = IRI.create("http://www.towl.org/towl");
		ontologyManager.addIRIMapper(new SimpleIRIMapper(
				towlIRI, IRI.create("file:///Users/sven/Thesis/kala/towl.owl")));
		
		// Get factory for temporal data constructs.
		TemporalDataFactory factory = ontologyManager.getTemporalDataFactory();
		
		// Create temporal ontology.
		IRI lboIRI = IRI.create("http://www.example.org/LBOExample");
		OWLOntology delegate = ontologyManager.createOntology(lboIRI);
		TemporalOntology lboOnt = ontologyManager.createTemporalOntology(delegate);
		
		// Create and declare the Company class.
		OWLClass companyClass = factory.getOWLClass(IRI.create("#Company"));
		OWLDeclarationAxiom companyClassDeclAxiom = factory.getOWLDeclarationAxiom(companyClass);
		ontologyManager.addAxiom(lboOnt, companyClassDeclAxiom);
		
		// Create the HedgeFund and Target classes, then assert them as subclasses of Company.
		OWLClass hedgefundClass = factory.getOWLClass(IRI.create("#HedgeFund"));
		OWLSubClassOfAxiom hedgefundSubClassAxiom = factory.getOWLSubClassOfAxiom(hedgefundClass, companyClass);
		ontologyManager.addAxiom(lboOnt, hedgefundSubClassAxiom);
		OWLClass targetClass = factory.getOWLClass(IRI.create("#Target"));
		OWLSubClassOfAxiom targetSubClassAxiom = factory.getOWLSubClassOfAxiom(targetClass, companyClass);
		ontologyManager.addAxiom(lboOnt, targetSubClassAxiom);
		
		// Create and declare LBOProcess class.
		OWLClass lboProcessClass = factory.getOWLClass(IRI.create("#LBOProcess"));
		OWLDeclarationAxiom lboClassDeclAxiom = factory.getOWLDeclarationAxiom(lboProcessClass);
		ontologyManager.addAxiom(lboOnt, lboClassDeclAxiom);
		
		// Create Stage class, then create the EarlyStage, DueDiligence, Bidding and Abort
		// classes and declare them as Stage's disjoint union.
		OWLClass stageClass = factory.getOWLClass(IRI.create("#Stage"));
		OWLClass earlyStageClass = factory.getOWLClass(IRI.create("#EarlyStage"));
		OWLClass dueDiligenceClass = factory.getOWLClass(IRI.create("#DueDiligence"));
		OWLClass biddingClass = factory.getOWLClass(IRI.create("#Bidding"));
		OWLClass abortClass = factory.getOWLClass(IRI.create("#Abort"));
		Set<OWLClass> stageDisjointUnionSet = Sets.newHashSet(
				earlyStageClass, dueDiligenceClass, biddingClass, abortClass);
		OWLDisjointUnionAxiom stageDisjointUnionAxiom = 
				factory.getOWLDisjointUnionAxiom(stageClass, stageDisjointUnionSet);
		ontologyManager.addAxiom(lboOnt, stageDisjointUnionAxiom);
		
		// Declare fluent object properties for linking LBO processes to their stages.
		IRI fluentEarlyStagePropertyIRI = IRI.create("#earlystage");
		FluentObjectProperty fluentEarlyStageProperty = 
				factory.getFluentObjectProperty(fluentEarlyStagePropertyIRI);
		IRI fluentDueDiligenceStagePropertyIRI = IRI.create("#duediligence");
		FluentObjectProperty fluentDueDiligenceStageProperty = 
				factory.getFluentObjectProperty(fluentDueDiligenceStagePropertyIRI);
		IRI fluentBiddingStagePropertyIRI = IRI.create("#bidding");
		FluentObjectProperty fluentBiddingStageProperty = 
				factory.getFluentObjectProperty(fluentBiddingStagePropertyIRI);
		IRI fluentAbortStagePropertyIRI = IRI.create("#abort");
		FluentObjectProperty fluentAbortStageProperty = 
				factory.getFluentObjectProperty(fluentAbortStagePropertyIRI);
		ontologyManager.addTemporalAxioms(lboOnt, Arrays.asList(
				factory.getTemporalEntityDeclarationAxiom(fluentEarlyStageProperty),
				factory.getTemporalEntityDeclarationAxiom(fluentDueDiligenceStageProperty),
				factory.getTemporalEntityDeclarationAxiom(fluentBiddingStageProperty),
				factory.getTemporalEntityDeclarationAxiom(fluentAbortStageProperty)));
		
		// Set domains and ranges for the LBO-to-stage properties.
		ontologyManager.addTemporalAxioms(lboOnt, Arrays.asList(
				factory.getFluentObjectPropertyDomainAxiom(fluentEarlyStageProperty, lboProcessClass),
				factory.getFluentObjectPropertyRangeAxiom(fluentEarlyStageProperty, earlyStageClass),
				factory.getFluentObjectPropertyDomainAxiom(fluentDueDiligenceStageProperty, lboProcessClass),
				factory.getFluentObjectPropertyRangeAxiom(fluentDueDiligenceStageProperty, dueDiligenceClass),
				factory.getFluentObjectPropertyDomainAxiom(fluentBiddingStageProperty, lboProcessClass),
				factory.getFluentObjectPropertyRangeAxiom(fluentBiddingStageProperty, biddingClass),
				factory.getFluentObjectPropertyDomainAxiom(fluentAbortStageProperty, lboProcessClass),
				factory.getFluentObjectPropertyRangeAxiom(fluentAbortStageProperty, abortClass)));
		
		// Declare inStage fluent object property, its domain (Company) and range (Stage).
		IRI inStagePropertyIRI = IRI.create("#inStage");
		FluentObjectProperty inStageProperty = factory.getFluentObjectProperty(inStagePropertyIRI);
		ontologyManager.addTemporalAxioms(lboOnt, Arrays.asList(
				factory.getTemporalEntityDeclarationAxiom(inStageProperty),
				factory.getFluentObjectPropertyDomainAxiom(inStageProperty, companyClass),
				factory.getFluentObjectPropertyRangeAxiom(inStageProperty, stageClass)));
		
		// Declare stageImpact fluent data property, its domain (Company) and range (xsd:int).
		IRI stageImpactPropertyIRI = IRI.create("#stageImpact");
		FluentDataProperty stageImpactProperty = factory.getFluentDataProperty(stageImpactPropertyIRI);
		ontologyManager.addTemporalAxioms(lboOnt, Arrays.asList(
				factory.getTemporalEntityDeclarationAxiom(stageImpactProperty),
				factory.getFluentDataPropertyDomainAxiom(stageImpactProperty, companyClass),
				factory.getFluentDataPropertyRangeAxiom(stageImpactProperty, 
						factory.getOWLDatatype(OWL2Datatype.XSD_INT.getIRI()))));
		
		// TODO: temporal restrictions, e.g. starts(EarlyStage, LBOProcess)		
		// TODO: define stageImpacts for Company X Stage?
		// These statements cannot be expressed without evolution constraints.
		
		// Declare the key players: alliance_boots (Target), kkr (HedgeFund) and terrafirma (HedgeFund).
		OWLIndividual alliance_boots = factory.getOWLNamedIndividual(IRI.create("#alliance_boots"));
		OWLIndividual kkr = factory.getOWLNamedIndividual(IRI.create("#kkr"));
		OWLIndividual terrafirma = factory.getOWLNamedIndividual(IRI.create("#terrafirma"));
		ontologyManager.addAxioms(lboOnt, Sets.newHashSet(
				factory.getOWLClassAssertionAxiom(targetClass, alliance_boots),
				factory.getOWLClassAssertionAxiom(hedgefundClass, kkr),
				factory.getOWLClassAssertionAxiom(hedgefundClass, terrafirma)));
		
		// Declare LBO Process #1 and its stages as individuals.
		OWLIndividual lbo_process_1 = factory.getOWLNamedIndividual(IRI.create("#lbo_process_1"));
		OWLIndividual earlystage_1 = factory.getOWLNamedIndividual(IRI.create("#earlystage_1"));
		OWLIndividual duediligence_1 = factory.getOWLNamedIndividual(IRI.create("#duediligence_1"));
		OWLIndividual bidding_1 = factory.getOWLNamedIndividual(IRI.create("#bidding_1"));
		OWLIndividual abort_1 = factory.getOWLNamedIndividual(IRI.create("#abort_1"));
		ontologyManager.addAxioms(lboOnt, Sets.newHashSet(
				factory.getOWLClassAssertionAxiom(lboProcessClass, lbo_process_1),
				factory.getOWLClassAssertionAxiom(earlyStageClass, earlystage_1),
				factory.getOWLClassAssertionAxiom(dueDiligenceClass, duediligence_1),
				factory.getOWLClassAssertionAxiom(biddingClass, bidding_1),
				factory.getOWLClassAssertionAxiom(abortClass, abort_1)));
		
		// Declare time intervals associated with the stages.
		TimeInterval interval_es_1 = factory.getTimeInterval(IRI.create("#interval_es_1"));
		TimeInterval interval_dd_1 = factory.getTimeInterval(IRI.create("#interval_dd_1"));
		TimeInterval interval_bd_1 = factory.getTimeInterval(IRI.create("#interval_bd_1"));
		TimeInterval interval_ab_1 = factory.getTimeInterval(IRI.create("#interval_ab_1"));
		ontologyManager.addTemporalAxioms(lboOnt, Arrays.asList(
				factory.getTemporalEntityDeclarationAxiom(interval_es_1),
				factory.getTemporalEntityDeclarationAxiom(interval_dd_1),
				factory.getTemporalEntityDeclarationAxiom(interval_bd_1),
				factory.getTemporalEntityDeclarationAxiom(interval_ab_1)));
		
		// And link them.
		ontologyManager.addTemporalAxioms(lboOnt, Arrays.asList(
				factory.getFluentObjectPropertyAssertionAxiom(
						lbo_process_1, fluentEarlyStageProperty, earlystage_1, interval_es_1),
				factory.getFluentObjectPropertyAssertionAxiom(
						lbo_process_1, fluentDueDiligenceStageProperty, duediligence_1, interval_es_1),
				factory.getFluentObjectPropertyAssertionAxiom(
						lbo_process_1, fluentBiddingStageProperty, bidding_1, interval_es_1),
				factory.getFluentObjectPropertyAssertionAxiom(
						lbo_process_1, fluentAbortStageProperty, abort_1, interval_es_1)));
		
		// The early stage of LBO process #1 starts at Sun Mar 25, 2007 8:42am EDT (UTC-04:00):
		TimeInstant es_1_start = factory.getTimeInstant(IRI.create("#es_1_start"));
		DateTime es_1_start_time = DateTime.parse("2007-03-25T08:42:00-04:00");
		ontologyManager.addTemporalAxioms(lboOnt, Arrays.asList(
				factory.getTemporalEntityDeclarationAxiom(es_1_start),
				factory.getInstantTimeAssertionAxiom(es_1_start, es_1_start_time),
				factory.getIntervalStartAssertionAxiom(interval_es_1, es_1_start)));
		
		// ... and involves Alliance Boots and Terra Firma, with an impact of 8 for both:
		ontologyManager.addTemporalAxioms(lboOnt, Arrays.asList(
				factory.getFluentObjectPropertyAssertionAxiom(
						alliance_boots, inStageProperty, earlystage_1, interval_es_1),
				factory.getFluentDataPropertyAssertionAxiom(
						alliance_boots, stageImpactProperty, factory.getOWLLiteral(8), interval_es_1),
				factory.getFluentObjectPropertyAssertionAxiom(
						kkr, inStageProperty, earlystage_1, interval_es_1),
				factory.getFluentDataPropertyAssertionAxiom(
						kkr, stageImpactProperty, factory.getOWLLiteral(8), interval_es_1)));
		
		//
		// Fluents
		//
		
		// Create the fluents serializer and use the TOWL 3.0 representation scheme.
		Serializer fluents_serializer = 
				new FluentsSerializerImpl(ontologyManager, TOWLRepresentationScheme.TOWL_3_0);
		
		// Serialize the temporal ontology.
		lboOnt.accept(fluents_serializer);
		
		// Create the output ontology.
		IRI lboFluentsIRI = IRI.create("http://www.example.org/LBOFluents");
		OWLOntology fluentsOnt = fluents_serializer.createOntology(lboFluentsIRI);
		
		// Apply TOWL imports declaration.
		ontologyManager.applyChange(new AddImport(fluentsOnt, factory.getOWLImportsDeclaration(towlIRI)));
		
		// Output to stdout and OWL files.
//		ontologyManager.saveOntology(fluentsOnt, 
//				new OWLXMLOntologyFormat(), new StreamDocumentTarget(System.out));
		ontologyManager.saveOntology(fluentsOnt, 
				new OWLXMLOntologyFormat(), IRI.create("file:///Users/sven/lbo_fluents_owlxml.owl"));
		ontologyManager.saveOntology(fluentsOnt, 
				new ManchesterOWLSyntaxOntologyFormat(), IRI.create("file:///Users/sven/lbo_fluents_manchester.owl"));
		
		//
		// Reification
		//
		
		// Create reification configuration and representation scheme.
		// Configuring the fluent properties is not necessary (the defaults will work just fine), but
		// it may result in a clearer ontology.
		ReificationConfigurationImpl reif_conf = new ReificationConfigurationImpl();
		reif_conf.addFluentObjectProperty(fluentEarlyStagePropertyIRI, 
				new ReificationConfigurationObjectPropertyItemImpl(
						new IRINamePatternConstant(IRI.create("#hasEarlyStage")), 
						SubjectRelationDirection.SUBJECT_TO_REIF,
						new IRINamePatternConstant(IRI.create("#toEarlyStage")), 
						ObjectRelationDirection.REIF_TO_OBJECT));
		reif_conf.addFluentObjectProperty(fluentDueDiligenceStagePropertyIRI, 
				new ReificationConfigurationObjectPropertyItemImpl(
						new IRINamePatternConstant(IRI.create("#hasDueDiligenceStage")), 
						SubjectRelationDirection.SUBJECT_TO_REIF,
						new IRINamePatternConstant(IRI.create("#toDueDiligenceStage")), 
						ObjectRelationDirection.REIF_TO_OBJECT));
		reif_conf.addFluentObjectProperty(fluentBiddingStagePropertyIRI, 
				new ReificationConfigurationObjectPropertyItemImpl(
						new IRINamePatternConstant(IRI.create("#hasBiddingStage")), 
						SubjectRelationDirection.SUBJECT_TO_REIF,
						new IRINamePatternConstant(IRI.create("#toBiddingStage")), 
						ObjectRelationDirection.REIF_TO_OBJECT));
		reif_conf.addFluentObjectProperty(fluentAbortStagePropertyIRI, 
				new ReificationConfigurationObjectPropertyItemImpl(
						new IRINamePatternConstant(IRI.create("#hasAbortStage")), 
						SubjectRelationDirection.SUBJECT_TO_REIF,
						new IRINamePatternConstant(IRI.create("#toAbortStage")), 
						ObjectRelationDirection.REIF_TO_OBJECT));
		reif_conf.addFluentObjectProperty(inStagePropertyIRI, 
				new ReificationConfigurationObjectPropertyItemImpl(
						new IRINamePatternConstant(IRI.create("#stageOfCompany")), 
						SubjectRelationDirection.REIF_TO_SUBJECT,
						new IRINamePatternConstant(IRI.create("#companyInStage")), 
						ObjectRelationDirection.REIF_TO_OBJECT));
		reif_conf.addFluentDataProperty(stageImpactPropertyIRI,
				new ReificationConfigurationDataPropertyItemImpl(
						new IRINamePatternConstant(IRI.create("#impactOnCompany")), 
						SubjectRelationDirection.REIF_TO_SUBJECT,
						new IRINamePatternConstant(IRI.create("#valueOfImpact"))));
		ReificationRepresentationScheme reif_scheme = new ReificationRepresentationSchemeImpl(reif_conf);
		
		// Create the reification serializer.
		Serializer reif_serializer = new ReificationSerializerImpl(ontologyManager, reif_scheme);
		
		// Serialize the temporal ontology.
		lboOnt.accept(reif_serializer);
		
		// Create the output ontology.
		IRI lboReifIRI = IRI.create("http://www.example.org/LBOReification");
		OWLOntology reifOnt = reif_serializer.createOntology(lboReifIRI);

//		ontologyManager.saveOntology(reifOnt, 
//				new OWLXMLOntologyFormat(), new StreamDocumentTarget(System.out));
		ontologyManager.saveOntology(reifOnt, 
				new OWLXMLOntologyFormat(), IRI.create("file:///Users/sven/lbo_reif_owlxml.owl"));
		ontologyManager.saveOntology(reifOnt, 
				new ManchesterOWLSyntaxOntologyFormat(), IRI.create("file:///Users/sven/lbo_reif_manchester.owl"));

		
		Parser fluents_parser = new FluentsParserImpl(ontologyManager, TOWLRepresentationScheme.TOWL_3_0);
		fluents_parser.read(fluentsOnt);
		
		TemporalOntology newOnt = fluents_parser.build(ontologyManager);
		Serializer ser2 = new ReificationSerializerImpl(ontologyManager, reif_scheme);
		newOnt.accept(ser2);
		
		OWLOntology result = ser2.createOntology(IRI.create("http://www.example.org/LBOReification2"));
		
		ontologyManager.saveOntology(result, 
				new OWLXMLOntologyFormat(), new StreamDocumentTarget(System.out));
	}

}
