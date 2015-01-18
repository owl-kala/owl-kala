package kala.time.serialization.fluents;

public enum TimesliceRelationDirection {
	
	TIMESLICE_TO_INDIVIDUAL("Timeslice-to-individual"),
	INDIVIDUAL_TO_TIMESLICE("Individual-to-timeslice");
	
	private final String description;
	
	private TimesliceRelationDirection(String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return description;
	}

}
