/**
 * 
 */
package kala.time.model.impl;

import kala.time.model.TimePrimitive;

import org.semanticweb.owlapi.model.OWLIndividual;

/**
 * @author sven
 *
 */
public abstract class TimePrimitiveImpl implements TimePrimitive {
	
	private final OWLIndividual individual;
	
	protected TimePrimitiveImpl(OWLIndividual individual) {
		this.individual = individual;
	}
	
	@Override
	public OWLIndividual getIndividual() {
		return individual;
	}

}
