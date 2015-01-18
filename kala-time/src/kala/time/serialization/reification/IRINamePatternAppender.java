package kala.time.serialization.reification;

import org.semanticweb.owlapi.model.IRI;

public class IRINamePatternAppender implements IRINamePattern {
	
	private final String suffix;
	
	public IRINamePatternAppender(String suffix) {
		this.suffix = suffix;
	}

	@Override
	public IRI getName(IRI input) {
		return IRI.create(input.toString() + suffix);
	}

}
