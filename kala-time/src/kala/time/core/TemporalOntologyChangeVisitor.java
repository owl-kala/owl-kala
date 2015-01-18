/**
 * 
 */
package kala.time.core;

/**
 * @author sven
 *
 */
public interface TemporalOntologyChangeVisitor {
	
	public void visit(AddTemporalAxiom addTemporalAxiom);
	
	public void visit(RemoveTemporalAxiom removeTemporalAxiom);

}
