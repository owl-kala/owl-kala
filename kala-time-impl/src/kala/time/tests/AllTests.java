package kala.time.tests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TimeInstantTestCase.class, TimeIntervalTestCase.class, TemporalOntologyTest.class,
	InstantTimeAssertionAxiomImplTest.class})
public class AllTests {

}
