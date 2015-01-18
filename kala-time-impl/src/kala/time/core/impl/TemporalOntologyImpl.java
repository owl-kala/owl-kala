package kala.time.core.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import kala.time.core.AddTemporalAxiom;
import kala.time.core.RemoveTemporalAxiom;
import kala.time.core.TemporalOntology;
import kala.time.core.TemporalOntologyChange;
import kala.time.core.TemporalOntologyChangeVisitor;
import kala.time.core.TemporalOntologyVisitor;
import kala.time.model.FluentDataProperty;
import kala.time.model.FluentDataPropertyAssertionAxiom;
import kala.time.model.FluentDataPropertyDomainAxiom;
import kala.time.model.FluentDataPropertyRangeAxiom;
import kala.time.model.FluentObjectProperty;
import kala.time.model.FluentObjectPropertyAssertionAxiom;
import kala.time.model.FluentObjectPropertyDomainAxiom;
import kala.time.model.FluentObjectPropertyRangeAxiom;
import kala.time.model.InstantTimeAssertionAxiom;
import kala.time.model.IntervalEndAssertionAxiom;
import kala.time.model.IntervalStartAssertionAxiom;
import kala.time.model.TemporalAxiom;
import kala.time.model.TemporalAxiomVisitor;
import kala.time.model.TemporalEntity;
import kala.time.model.TemporalEntityDeclarationAxiom;
import kala.time.model.TemporalEntityVisitor;
import kala.time.model.TimeInstant;
import kala.time.model.TimeInterval;

import org.semanticweb.owlapi.model.OWLMutableOntology;
import org.semanticweb.owlapi.model.OWLOntology;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.SetMultimap;

public class TemporalOntologyImpl
		extends ForwardingOWLOntology
		implements TemporalOntology {
	
	private static final long serialVersionUID = -6263149814604184819L;
	
	private final Set<TemporalAxiom> axioms;
	private final SetMultimap<TimeInstant, TemporalAxiom> axiomsByInstant;
	private final SetMultimap<TimeInterval, TemporalAxiom> axiomsByInterval;
	private final SetMultimap<FluentObjectProperty, TemporalAxiom> axiomsByFluentObjectProperty;
	private final SetMultimap<FluentDataProperty, TemporalAxiom> axiomsByFluentDataProperty;

	public TemporalOntologyImpl(OWLOntology delegate) {
		super((OWLMutableOntology)delegate); // HACK
		this.axioms = new HashSet<TemporalAxiom>();
		this.axiomsByInstant = HashMultimap.create();
		this.axiomsByInterval = HashMultimap.create();
		this.axiomsByFluentObjectProperty = HashMultimap.create();
		this.axiomsByFluentDataProperty = HashMultimap.create();
	}
	
	@Override
	public Set<TemporalAxiom> getTemporalAxioms() {
		return axioms;
	}
	
	@Override
	public Set<TimeInstant> getTimeInstants() {
		return axiomsByInstant.keySet();
	}
	
	@Override
	public Set<TemporalAxiom> getAxioms(TimeInstant instant) {
		return axiomsByInstant.get(instant);
	}

	@Override
	public Set<TimeInterval> getTimeIntervals() {
		return axiomsByInterval.keySet();
	}
	
	@Override
	public Set<TemporalAxiom> getAxioms(TimeInterval interval) {
		return axiomsByInterval.get(interval);
	}

	@Override
	public Set<FluentObjectProperty> getFluentObjectProperties() {
		return axiomsByFluentObjectProperty.keySet();
	}
	
	@Override
	public Set<TemporalAxiom> getAxioms(FluentObjectProperty property) {
		return axiomsByFluentObjectProperty.get(property);
	}

	@Override
	public Set<FluentDataProperty> getFluentDataProperties() {
		return axiomsByFluentDataProperty.keySet();
	}

	@Override
	public Set<TemporalAxiom> getAxioms(FluentDataProperty property) {
		return axiomsByFluentDataProperty.get(property);
	}

	@Override
	public void applyChange(TemporalOntologyChange change) {
		change.accept(new TemporalOntologyChangeVisitor() {

			@Override
			public void visit(AddTemporalAxiom addTemporalAxiom) {
				addTemporalAxiom(addTemporalAxiom.getAxiom());
			}

			@Override
			public void visit(RemoveTemporalAxiom removeTemporalAxiom) {
				removeAxiom(removeTemporalAxiom.getAxiom());
			}
			
		});
	}
	
	public void addTemporalAxiom(TemporalAxiom axiom) {
		axioms.add(axiom);
		axiom.accept(new TemporalAxiomVisitor() {

			@Override
			public void visit(final TemporalEntityDeclarationAxiom temporalDeclarationAxiom) {
				TemporalEntity entity = temporalDeclarationAxiom.getEntity();
				entity.accept(new TemporalEntityVisitor() {

					@Override
					public void visit(TimeInstant instant) {
						axiomsByInstant.put(instant, temporalDeclarationAxiom);
					}

					@Override
					public void visit(TimeInterval interval) {
						axiomsByInterval.put(interval,  temporalDeclarationAxiom);
					}

					@Override
					public void visit(FluentObjectProperty property) {
						axiomsByFluentObjectProperty.put(property, temporalDeclarationAxiom);
					}

					@Override
					public void visit(FluentDataProperty property) {
						axiomsByFluentDataProperty.put(property, temporalDeclarationAxiom);
					}
					
				});
			}

			@Override
			public void visit(InstantTimeAssertionAxiom axiom) {
				axiomsByInstant.put(axiom.getInstant(), axiom);
			}

			@Override
			public void visit(IntervalStartAssertionAxiom axiom) {
				axiomsByInterval.put(axiom.getInterval(), axiom);
			}

			@Override
			public void visit(IntervalEndAssertionAxiom axiom) {
				axiomsByInterval.put(axiom.getInterval(), axiom);
			}

			@Override
			public void visit(FluentObjectPropertyDomainAxiom axiom) {
				axiomsByFluentObjectProperty.put(axiom.getProperty(),axiom);
			}

			@Override
			public void visit(FluentObjectPropertyRangeAxiom axiom) {
				axiomsByFluentObjectProperty.put(axiom.getProperty(), axiom);
			}

			@Override
			public void visit(FluentDataPropertyDomainAxiom axiom) {
				axiomsByFluentDataProperty.put(axiom.getProperty(), axiom);
			}
			
			@Override
			public void visit(FluentDataPropertyRangeAxiom axiom) {
				axiomsByFluentDataProperty.put(axiom.getProperty(), axiom);
			}

			@Override
			public void visit(FluentObjectPropertyAssertionAxiom axiom) {
				axiomsByFluentObjectProperty.put(axiom.getProperty(), axiom);
			}

			@Override
			public void visit(FluentDataPropertyAssertionAxiom axiom) {
				axiomsByFluentDataProperty.put(axiom.getProperty(), axiom);
			}

			
		});
	}
	
	public void addTemporalAxioms(Collection<? extends TemporalAxiom> axioms) {
		for(TemporalAxiom axiom : axioms) {
			addTemporalAxiom(axiom);
		}
	}
	
	private void removeAxiom(TemporalAxiom axiom) {
		System.out.println("removeAxiom: " + axiom);
	}

	@Override
	public void accept(TemporalOntologyVisitor visitor) {
		visitor.visit(this);
	}

}
