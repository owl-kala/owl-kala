package kala.time.tests;

import static org.junit.Assert.*;
import kala.time.core.TemporalDataFactory;
import kala.time.core.TemporalOntology;
import kala.time.core.impl.AddTemporalAxiomImpl;
import kala.time.core.impl.TemporalDataFactoryImpl;
import kala.time.core.impl.TemporalOntologyImpl;
import kala.time.model.TemporalEntityDeclarationAxiom;
import kala.time.model.TimeInstant;
import org.junit.Before;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;

public class TemporalOntologyTest {
	
	private TemporalOntology ontology;
	private TemporalDataFactory factory;

	@Before
	public void setUp() throws Exception {
		OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
		OWLDataFactory dataFactory = ontologyManager.getOWLDataFactory();
		OWLOntology ont = ontologyManager.createOntology();
		this.ontology = new TemporalOntologyImpl(ont);
		this.factory = new TemporalDataFactoryImpl(dataFactory);
	}

	@Test
	public void testAddInstantDeclaration() {
		OWLIndividual individual = factory.getOWLAnonymousIndividual();
		TimeInstant instant = factory.getTimeInstant(individual);
		TemporalEntityDeclarationAxiom axiom = factory.getTemporalEntityDeclarationAxiom(instant);
		assertFalse(ontology.getTimeInstants().contains(instant));
		assertFalse(ontology.getAxioms(instant).contains(axiom));
		ontology.applyChange(new AddTemporalAxiomImpl(axiom));
		assertTrue(ontology.getTimeInstants().contains(instant));
		assertTrue(ontology.getAxioms(instant).contains(axiom));
	}

}
