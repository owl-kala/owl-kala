package kala.time.serialization.fluents;

import kala.time.serialization.Serializer;

public interface FluentsSerializer extends Serializer {

	@Override
	public FluentsRepresentationScheme getScheme();
	
}
