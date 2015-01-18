package kala.time.tests;

import static org.junit.Assert.*;
import kala.time.core.TemporalDataFactory;
import kala.time.core.impl.TemporalDataFactoryImpl;
import kala.time.model.InstantTimeAssertionAxiom;
import kala.time.model.TimeInstant;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.junit.Before;
import org.junit.Test;
import org.semanticweb.owlapi.apibinding.OWLManager;

public class InstantTimeAssertionAxiomImplTest {

	private TemporalDataFactory factory;

	@Before
	public void setUp() throws Exception {
		factory = new TemporalDataFactoryImpl(OWLManager.getOWLDataFactory());
	}

	@Test
	public void testEquals() {
		
		// Midnight, January 1st, 2013.
		DateTime time1 = new DateTime(2013, 1, 1, 0, 0, 0, 0, DateTimeZone.UTC);
		DateTime time2 = new DateTime(2013, 1, 1, 0, 0, 0, 0, DateTimeZone.UTC);
		
		// 01:01:01 AM on February 2nd, 2013.
		DateTime time3 = new DateTime(2013, 2, 2, 1, 1, 1, 1, DateTimeZone.UTC);
		
		TimeInstant t1 = factory.getTimeInstant();
		TimeInstant t2 = factory.getTimeInstant();
		
		InstantTimeAssertionAxiom ax1 = factory.getInstantTimeAssertionAxiom(t1, time1);
		
		// Test with same participating objects.
		InstantTimeAssertionAxiom ax2 = factory.getInstantTimeAssertionAxiom(t1, time1);
		assertTrue(ax1.equals(ax2));
		
		// Test with different instant.
		InstantTimeAssertionAxiom ax3 = factory.getInstantTimeAssertionAxiom(t2, time1);
		assertFalse(ax1.equals(ax3));
		
		// Test with same time value, but different time object.
		InstantTimeAssertionAxiom ax4 = factory.getInstantTimeAssertionAxiom(t1, time2);
		assertTrue(ax1.equals(ax4));
		
		// Test with different time value.
		InstantTimeAssertionAxiom ax5 = factory.getInstantTimeAssertionAxiom(t1, time3);
		assertFalse(ax1.equals(ax5));
	}

	@Test
	public void testHashCode() {
		
		// Midnight, January 1st, 2013.
		DateTime time1 = new DateTime(2013, 1, 1, 0, 0, 0, 0, DateTimeZone.UTC);
		DateTime time2 = new DateTime(2013, 1, 1, 0, 0, 0, 0, DateTimeZone.UTC);
		
		TimeInstant t1 = factory.getTimeInstant();
		
		InstantTimeAssertionAxiom ax1 = factory.getInstantTimeAssertionAxiom(t1, time1);
		
		// Test with same participating objects.
		InstantTimeAssertionAxiom ax2 = factory.getInstantTimeAssertionAxiom(t1, time1);
		assertTrue(ax1.hashCode() == ax2.hashCode());
		
		// Test with same time value, but different time object.
		InstantTimeAssertionAxiom ax3 = factory.getInstantTimeAssertionAxiom(t1, time2);
		assertTrue(ax1.hashCode() == ax3.hashCode());
	}

}
