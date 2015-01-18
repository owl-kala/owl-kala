package kala.time.tests;

import static org.junit.Assert.*;
import kala.time.core.TemporalDataFactory;
import kala.time.core.impl.TemporalDataFactoryImpl;
import kala.time.model.TimeInstant;
import kala.time.model.TimeInterval;
import org.junit.Before;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLIndividual;

public class TimeInstantTestCase {
	
	private TemporalDataFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = new TemporalDataFactoryImpl(OWLManager.getOWLDataFactory());
	}

	@Test
	public void testEquals() {
		OWLIndividual individual1 = factory.getOWLNamedIndividual(IRI.create(":testIndividual1"));
		TimeInstant instant1 = factory.getTimeInstant(individual1);
		assertTrue(instant1.equals(instant1));

		TimeInstant instant2 = factory.getTimeInstant(individual1);
		assertTrue(instant1.equals(instant2));
		
		OWLIndividual individual3 = factory.getOWLNamedIndividual(IRI.create(":testIndividual3"));
		TimeInstant instant3 = factory.getTimeInstant(individual3);
		assertFalse(instant1.equals(instant3));

		TimeInterval interval = factory.getTimeInterval(individual1);
		assertFalse(instant1.equals(interval));
	}

	@Test
	public void testHashCode() {
		OWLIndividual individual1 = factory.getOWLNamedIndividual(IRI.create(":testIndividual1"));
		TimeInstant instant1 = factory.getTimeInstant(individual1);
		TimeInstant instant2 = factory.getTimeInstant(individual1);
		assertTrue(instant1.hashCode() == instant2.hashCode()); 
	}

}
