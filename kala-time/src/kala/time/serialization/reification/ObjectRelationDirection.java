package kala.time.serialization.reification;

public enum ObjectRelationDirection {

	REIF_TO_OBJECT("Reified-relation-to-object"),
	OBJECT_TO_REIF("Object-to-reified-relation");
	
	private final String description;
	
	private ObjectRelationDirection(String description) {
		this.description = description;
	}
	
	@Override 
	public String toString() {
		return description;
	}
	
}
