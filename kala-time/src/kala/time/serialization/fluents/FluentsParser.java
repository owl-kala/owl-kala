package kala.time.serialization.fluents;

import kala.time.serialization.Parser;

public interface FluentsParser extends Parser {

	@Override
	public FluentsRepresentationScheme getScheme();
	
}
