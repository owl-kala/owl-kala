/**
 * 
 */
package kala.time.model;

import org.semanticweb.owlapi.model.OWLIndividual;

/**
 * @author sven
 *
 */
public interface TimePrimitive extends TemporalEntity {
	
	public OWLIndividual getIndividual();

}
