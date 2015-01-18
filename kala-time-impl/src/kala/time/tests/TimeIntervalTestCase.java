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

public class TimeIntervalTestCase {

	private TemporalDataFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = new TemporalDataFactoryImpl(OWLManager.getOWLDataFactory());
	}

	@Test
	public void testEquals() {
		OWLIndividual individual1 = factory.getOWLNamedIndividual(IRI.create(":testIndividual1"));
		TimeInterval interval1 = factory.getTimeInterval(individual1);
		assertTrue(interval1.equals(interval1));

		TimeInterval interval2 = factory.getTimeInterval(individual1);
		assertTrue(interval1.equals(interval2));
		
		OWLIndividual individual3 = factory.getOWLNamedIndividual(IRI.create(":testIndividual3"));
		TimeInterval interval3 = factory.getTimeInterval(individual3);
		assertFalse(interval1.equals(interval3));

		TimeInstant instant = factory.getTimeInstant(individual1);
		assertFalse(interval1.equals(instant));
	}

	@Test
	public void testHashCode() {
		OWLIndividual individual1 = factory.getOWLNamedIndividual(IRI.create(":testIndividual1"));
		TimeInterval interval1 = factory.getTimeInterval(individual1);
		TimeInterval interval2 = factory.getTimeInterval(individual1);
		assertTrue(interval1.hashCode() == interval2.hashCode()); 
	}

}
