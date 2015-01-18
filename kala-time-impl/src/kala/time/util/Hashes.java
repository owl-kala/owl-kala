package kala.time.util;

public class Hashes {
	
	private Hashes() {
		throw new UnsupportedOperationException("This class cannot be instantiated.");
	}
	
	public static final int HASH_MULT = 17;
	
	public static final int HASH_TIME_INSTANT = 11;
	public static final int HASH_TIME_INTERVAL = 13;
	
	public static final int HASH_TEMPORAL_ENTITY_DECLARATION_AXIOM = 19;
	public static final int HASH_INTERVAL_START_ASSERTION_AXIOM = 23;
	public static final int HASH_INTERVAL_END_ASSERTION_AXIOM = 29;
	public static final int HASH_INSTANT_TIME_ASSERTION_AXIOM = 31;

	public static final int HASH_FLUENT_OBJECT_PROPERTY = 37;
	public static final int HASH_FLUENT_OBJECT_PROPERTY_DOMAIN_AXIOM = 43;
	public static final int HASH_FLUENT_OBJECT_PROPERTY_RANGE_AXIOM = 47;
	public static final int HASH_FLUENT_OBJECT_PROPERTY_ASSERTION_AXIOM = 61;

	public static final int HASH_FLUENT_DATA_PROPERTY = 41;
	public static final int HASH_FLUENT_DATA_PROPERTY_DOMAIN_AXIOM = 53;
	public static final int HASH_FLUENT_DATA_PROPERTY_RANGE_AXIOM = 59;
	public static final int HASH_FLUENT_DATA_PROPERTY_ASSERTION_AXIOM = 67;

	

}
