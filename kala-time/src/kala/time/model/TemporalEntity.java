package kala.time.model;

public interface TemporalEntity {
	
	public void accept(TemporalEntityVisitor visitor);

}
