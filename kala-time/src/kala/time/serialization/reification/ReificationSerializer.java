package kala.time.serialization.reification;

import kala.time.serialization.Serializer;

public interface ReificationSerializer extends Serializer {
	
	@Override
	public ReificationRepresentationScheme getScheme();

}
