/**
 * 
 */
package kala.time.model;

/**
 * 
 * @author sven
 *
 */
public interface TemporalAxiomVisitor {
	
	public void visit(TemporalEntityDeclarationAxiom axiom);
	
	public void visit(IntervalStartAssertionAxiom axiom);
	
	public void visit(IntervalEndAssertionAxiom axiom);
	
	public void visit(InstantTimeAssertionAxiom axiom);

	public void visit(FluentObjectPropertyDomainAxiom axiom);
	
	public void visit(FluentObjectPropertyRangeAxiom axiom);

	public void visit(FluentDataPropertyDomainAxiom axiom);

	public void visit(FluentDataPropertyRangeAxiom axiom);

	public void visit(FluentObjectPropertyAssertionAxiom axiom);

	public void visit(FluentDataPropertyAssertionAxiom axiom);

}
