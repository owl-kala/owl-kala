/**
 * 
 */
package kala.time.core;

/**
 * @author sven
 *
 */
public interface TemporalOntologyChange {
	
	public void accept(TemporalOntologyChangeVisitor visitor);

}
