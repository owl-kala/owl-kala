package kala.time.model;

public interface TemporalAxiom {
	
	public void accept(TemporalAxiomVisitor visitor);

}
