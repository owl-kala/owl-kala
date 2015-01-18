package kala.time.core;

import java.util.Collection;
import java.util.Set;

import kala.time.model.FluentDataProperty;
import kala.time.model.FluentObjectProperty;
import kala.time.model.TemporalAxiom;
import kala.time.model.TimeInstant;
import kala.time.model.TimeInterval;

import org.semanticweb.owlapi.model.OWLOntology;

public interface TemporalOntology extends OWLOntology {
	
	public Set<TemporalAxiom> getTemporalAxioms();
	
	public Set<TimeInstant> getTimeInstants();
	
	public Set<TemporalAxiom> getAxioms(TimeInstant instant);
	
	public Set<TimeInterval> getTimeIntervals();

	public Set<TemporalAxiom> getAxioms(TimeInterval interval);

	public Set<FluentObjectProperty> getFluentObjectProperties();

	public Set<TemporalAxiom> getAxioms(FluentObjectProperty property);

	public Set<FluentDataProperty> getFluentDataProperties();

	public Set<TemporalAxiom> getAxioms(FluentDataProperty property);
	
	public void addTemporalAxiom(TemporalAxiom axiom);
	
	public void addTemporalAxioms(Collection<? extends TemporalAxiom> axioms);

	public void applyChange(TemporalOntologyChange change);
	
	public void accept(TemporalOntologyVisitor visitor);

}
