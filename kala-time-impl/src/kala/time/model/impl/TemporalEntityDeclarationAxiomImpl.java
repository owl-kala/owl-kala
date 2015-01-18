/**
 * 
 */
package kala.time.model.impl;

import kala.time.model.TemporalAxiomVisitor;
import kala.time.model.TemporalEntity;
import kala.time.model.TemporalEntityDeclarationAxiom;
import kala.time.util.Hashes;

/**
 * @author sven
 *
 */
public class TemporalEntityDeclarationAxiomImpl implements
		TemporalEntityDeclarationAxiom {
	
	private final TemporalEntity entity;
	
	public TemporalEntityDeclarationAxiomImpl(TemporalEntity entity) {
		this.entity = entity;
	}

	@Override
	public TemporalEntity getEntity() {
		return entity;
	}

	@Override
	public void accept(TemporalAxiomVisitor visitor) {
		visitor.visit(this);
	}
	
	@Override
	public boolean equals(Object o) {
		if(o instanceof TemporalEntityDeclarationAxiomImpl) {
			TemporalEntityDeclarationAxiomImpl co = (TemporalEntityDeclarationAxiomImpl) o;
			return getEntity().equals(co.getEntity());
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		int m = Hashes.HASH_MULT;
		int hashCode = Hashes.HASH_TEMPORAL_ENTITY_DECLARATION_AXIOM;
		hashCode = hashCode * m + getEntity().hashCode();
		return hashCode;
	}
	
	@Override
	public String toString() {
		return "TemporalDeclarationAxiom(" + getEntity() + ")";
	}

}
