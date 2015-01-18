package kala.time.serialization.reification;

import org.semanticweb.owlapi.model.IRI;

public class IRINamePatternConstant implements IRINamePattern {
	
	private final IRI name;
	
	public IRINamePatternConstant(IRI name) {
		this.name = name;
	}

	@Override
	public IRI getName(IRI input) {
		return name;
	}

}
