/**
 * 
 */
package kala.time.model;

/**
 * @author sven
 *
 */
public interface TemporalEntityVisitor {
	
	public void visit(TimeInstant instant);
	
	public void visit(TimeInterval interval);
	
	public void visit(FluentObjectProperty property);
	
	public void visit(FluentDataProperty property);

}
