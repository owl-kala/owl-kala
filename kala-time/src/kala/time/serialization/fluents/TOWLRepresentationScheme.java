package kala.time.serialization.fluents;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.util.DefaultPrefixManager;

public enum TOWLRepresentationScheme implements FluentsRepresentationScheme {

	TOWL_3_0("http://www.towl.org/towl#") {
		
		@Override
		public boolean hasInstantsAsIndividuals() {
			return false;
		}

		@Override
		public OWLClass getInstantClass(OWLDataFactory factory) {
			throw new UnsupportedOperationException(
					"This scheme does not feature time instants as individuals.");
		}
		
		@Override
		public boolean hasIntervalsAsIndividuals() {
			return true;
		}
		
		@Override
		public OWLClass getIntervalClass(OWLDataFactory factory) {
			return factory.getOWLClass(Vocabulary.INTERVAL_CLASS.identifier, 
					getPrefixManager());
		}
		
		@Override
		public OWLObjectProperty getIntervalProperty(OWLDataFactory factory) {
			return factory.getOWLObjectProperty(Vocabulary.INTERVAL_PROPERTY.identifier, 
					getPrefixManager());
		}
	
		@Override
		public OWLObjectProperty getIntervalStartInstantProperty(OWLDataFactory factory) {
			throw new UnsupportedOperationException(
					"This scheme does not feature time instants as individuals.");
		}

		@Override
		public OWLDataProperty getIntervalStartLiteralProperty(OWLDataFactory factory) {
			return factory.getOWLDataProperty(Vocabulary.INTERVAL_START_PROPERTY.identifier, 
					getPrefixManager());
		}

		@Override
		public OWLObjectProperty getIntervalEndInstantProperty(OWLDataFactory factory) {
			throw new UnsupportedOperationException(
					"This scheme does not feature time instants as individuals.");
		}

		@Override
		public OWLDataProperty getIntervalEndLiteralProperty(OWLDataFactory factory) {
			return factory.getOWLDataProperty(Vocabulary.INTERVAL_END_PROPERTY.identifier, 
					getPrefixManager());
		}

		@Override
		public OWLDataProperty getInstantHasTimeLiteralProperty(
				OWLDataFactory factory) {
			throw new UnsupportedOperationException(
					"This scheme does not feature time instants as individuals.");
		}
		
		@Override
		public OWLClass getTimesliceClass(OWLDataFactory factory) {
			return factory.getOWLClass(Vocabulary.TIMESLICE_CLASS.identifier, 
					getPrefixManager());
		}

		@Override
		public TimesliceRelationDirection getTimesliceRelationDirection() {
			return TimesliceRelationDirection.TIMESLICE_TO_INDIVIDUAL;
		}

		@Override
		public OWLObjectProperty getTimesliceRelation(OWLDataFactory factory) {
			return factory.getOWLObjectProperty(Vocabulary.TIMESLICE_PROPERTY.identifier, 
					getPrefixManager());
		}
		
	};
	
	private enum Vocabulary {
		
		INTERVAL_CLASS(":Interval"),
		INTERVAL_PROPERTY(":time"),
		INTERVAL_START_PROPERTY(":start"),
		INTERVAL_END_PROPERTY(":end"),
		TIMESLICE_CLASS(":TimeSlice"),
		TIMESLICE_PROPERTY(":timeSliceOf");
		
		private final String identifier;
		
		private Vocabulary(String identifier) {
			this.identifier = identifier;
		}
	
	}
	
	private final PrefixManager pm;
	
	private TOWLRepresentationScheme(String base) {
		this.pm = new DefaultPrefixManager(base);
	}
	
	public PrefixManager getPrefixManager() {
		return pm;
	}


}
