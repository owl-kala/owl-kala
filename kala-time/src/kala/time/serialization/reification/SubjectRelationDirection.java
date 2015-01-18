package kala.time.serialization.reification;

public enum SubjectRelationDirection {
	
	SUBJECT_TO_REIF("Subject-to-reified-relation"),
	REIF_TO_SUBJECT("Reified-relation-to-subject");
	
	private final String description;
	
	private SubjectRelationDirection(String description) {
		this.description = description;
	}
	
	@Override 
	public String toString() {
		return description;
	}

}
