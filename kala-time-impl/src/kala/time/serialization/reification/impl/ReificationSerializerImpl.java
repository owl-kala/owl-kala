package kala.time.serialization.reification.impl;

import java.util.ArrayList;
import java.util.List;

import kala.time.model.FluentDataProperty;
import kala.time.model.FluentDataPropertyAssertionAxiom;
import kala.time.model.FluentDataPropertyDomainAxiom;
import kala.time.model.FluentDataPropertyRangeAxiom;
import kala.time.model.FluentObjectProperty;
import kala.time.model.FluentObjectPropertyAssertionAxiom;
import kala.time.model.FluentObjectPropertyDomainAxiom;
import kala.time.model.FluentObjectPropertyRangeAxiom;
import kala.time.model.TemporalEntity;
import kala.time.model.TemporalEntityDeclarationAxiom;
import kala.time.model.TimeInterval;
import kala.time.serialization.impl.SerializerBase;
import kala.time.serialization.reification.ObjectRelationDirection;
import kala.time.serialization.reification.ReificationConfiguration;
import kala.time.serialization.reification.ReificationRepresentationScheme;
import kala.time.serialization.reification.ReificationSerializer;
import kala.time.serialization.reification.SubjectRelationDirection;

import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLDataPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLDataRange;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalDataPropertyAxiom;
import org.semanticweb.owlapi.model.OWLFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLInverseFunctionalObjectPropertyAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyDomainAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyRangeAxiom;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;

public class ReificationSerializerImpl extends SerializerBase 
		implements ReificationSerializer {
	
	public ReificationSerializerImpl(OWLOntologyManager ontologyManager,
			ReificationRepresentationScheme scheme) {
		super(ontologyManager, scheme);
	}
	
	@Override
	public ReificationRepresentationScheme getScheme() {
		return (ReificationRepresentationScheme) super.getScheme();
	}

	@Override
	public void visit(TemporalEntityDeclarationAxiom axiom) {
		TemporalEntity entity = axiom.getEntity();
		entity.accept(new SerializerBase.TemporalEntityDeclarationHandler() {

			@Override
			public void visit(FluentObjectProperty property) {
				List<OWLAxiom> axiomsToAdd = new ArrayList<OWLAxiom>();
				
				// Declare fluent object property as class.
				IRI propertyIRI = property.getProperty().getIRI();
				OWLClass propertyAsClass = getOWLDataFactory().getOWLClass(propertyIRI);
				OWLDeclarationAxiom propertyAsClassDeclAx = getOWLDataFactory().getOWLDeclarationAxiom(propertyAsClass);
				axiomsToAdd.add(propertyAsClassDeclAx);
				
				// Declare subject relation.
				OWLObjectProperty subjRel = getSubjectProperty(property);
				OWLDeclarationAxiom subjRelDeclAx = getOWLDataFactory().getOWLDeclarationAxiom(subjRel);
				axiomsToAdd.add(subjRelDeclAx);
				
				// Set restrictions on subject relation.
				switch(getSubjectRelationDirection(property)) {
				case SUBJECT_TO_REIF:
					
					// Set subject relation range to the reified property class.
					OWLObjectPropertyRangeAxiom subjRelRangeAxiom =
							getOWLDataFactory().getOWLObjectPropertyRangeAxiom(subjRel, propertyAsClass);
					axiomsToAdd.add(subjRelRangeAxiom);
					
					// Declare subject relation as inverse-functional.
					OWLInverseFunctionalObjectPropertyAxiom subjRelInvFuncAxiom = 
							getOWLDataFactory().getOWLInverseFunctionalObjectPropertyAxiom(subjRel);
					axiomsToAdd.add(subjRelInvFuncAxiom);
					
					break;
					
				case REIF_TO_SUBJECT:
					
					// Set subject relation domain to the reified property class.
					OWLObjectPropertyDomainAxiom subjRelDomainAxiom =
							getOWLDataFactory().getOWLObjectPropertyDomainAxiom(subjRel, propertyAsClass);
					axiomsToAdd.add(subjRelDomainAxiom);
					
					// Declare subject relation as functional.
					OWLFunctionalObjectPropertyAxiom subjRelFuncAxiom = 
							getOWLDataFactory().getOWLFunctionalObjectPropertyAxiom(subjRel);
					axiomsToAdd.add(subjRelFuncAxiom);
					
					break;
					
				}
				
				// Declare object relation.
				OWLObjectProperty objRel = getObjectProperty(property);
				OWLDeclarationAxiom objRelDeclAx = getOWLDataFactory().getOWLDeclarationAxiom(objRel);
				axiomsToAdd.add(objRelDeclAx);
				
				// Set restrictions on object relation.
				switch(getObjectRelationDirection(property)) {
				case REIF_TO_OBJECT:
					
					// Set object relation domain to the reified property class.
					OWLObjectPropertyDomainAxiom objRelDomainAxiom =
							getOWLDataFactory().getOWLObjectPropertyDomainAxiom(objRel, propertyAsClass);
					axiomsToAdd.add(objRelDomainAxiom);
					
					// Declare object relation as functional.
					OWLFunctionalObjectPropertyAxiom objRelFuncAxiom = 
							getOWLDataFactory().getOWLFunctionalObjectPropertyAxiom(objRel);
					axiomsToAdd.add(objRelFuncAxiom);
					
					break;
				case OBJECT_TO_REIF:
					
					// Set object relation range to the reified property class.
					OWLObjectPropertyRangeAxiom objRelRangeAxiom =
							getOWLDataFactory().getOWLObjectPropertyRangeAxiom(objRel, propertyAsClass);
					axiomsToAdd.add(objRelRangeAxiom);
					
					// Declare object relation as inverse-functional.
					OWLInverseFunctionalObjectPropertyAxiom objRelInvFuncAxiom = 
							getOWLDataFactory().getOWLInverseFunctionalObjectPropertyAxiom(objRel);
					axiomsToAdd.add(objRelInvFuncAxiom);
					
					break;
				}
				
				// Declare holds property.
				OWLObjectProperty holdsRel = getHasIntervalProperty();
				OWLDeclarationAxiom holdsRelDeclAx = getOWLDataFactory().getOWLDeclarationAxiom(holdsRel);
				axiomsToAdd.add(holdsRelDeclAx);
				
				// Set restriction on holds property.
				OWLObjectPropertyRangeAxiom holdsRelRangeAxiom =
						getOWLDataFactory().getOWLObjectPropertyRangeAxiom(holdsRel, getIntervalClass());
				axiomsToAdd.add(holdsRelRangeAxiom);
				
				// Holds property is functional.
				OWLFunctionalObjectPropertyAxiom holdsFuncAxiom = 
						getOWLDataFactory().getOWLFunctionalObjectPropertyAxiom(holdsRel);
				axiomsToAdd.add(holdsFuncAxiom);
				
				// Set restriction on reified property class for holds relation.
				OWLObjectSomeValuesFrom holdsRestriction = 
						getOWLDataFactory().getOWLObjectSomeValuesFrom(holdsRel, getIntervalClass());
				OWLSubClassOfAxiom propertyHoldsRestriction = 
						getOWLDataFactory().getOWLSubClassOfAxiom(propertyAsClass, holdsRestriction);
				axiomsToAdd.add(propertyHoldsRestriction);
				
				addAxioms(axiomsToAdd);
			}

			@Override
			public void visit(FluentDataProperty property) {
				List<OWLAxiom> axiomsToAdd = new ArrayList<OWLAxiom>();
				
				// Declare fluent data property as class.
				IRI propertyIRI = property.getProperty().getIRI();
				OWLClass propertyAsClass = getOWLDataFactory().getOWLClass(propertyIRI);
				OWLDeclarationAxiom propertyAsClassDeclAx = getOWLDataFactory().getOWLDeclarationAxiom(propertyAsClass);
				axiomsToAdd.add(propertyAsClassDeclAx);
				
				// Declare subject relation.
				OWLObjectProperty subjRel = getSubjectProperty(property);
				OWLDeclarationAxiom subjRelDeclAx = getOWLDataFactory().getOWLDeclarationAxiom(subjRel);
				axiomsToAdd.add(subjRelDeclAx);
				
				// Set restrictions on subject relation.
				switch(getSubjectRelationDirection(property)) {
				case SUBJECT_TO_REIF:
					
					// Set subject relation range to the reified property class.
					OWLObjectPropertyRangeAxiom subjRelRangeAxiom =
							getOWLDataFactory().getOWLObjectPropertyRangeAxiom(subjRel, propertyAsClass);
					axiomsToAdd.add(subjRelRangeAxiom);
					
					// Declare subject relation as inverse-functional.
					OWLInverseFunctionalObjectPropertyAxiom subjRelInvFuncAxiom = 
							getOWLDataFactory().getOWLInverseFunctionalObjectPropertyAxiom(subjRel);
					axiomsToAdd.add(subjRelInvFuncAxiom);
					
					break;
					
				case REIF_TO_SUBJECT:
					
					// Set subject relation domain to the reified property class.
					OWLObjectPropertyDomainAxiom subjRelDomainAxiom =
							getOWLDataFactory().getOWLObjectPropertyDomainAxiom(subjRel, propertyAsClass);
					axiomsToAdd.add(subjRelDomainAxiom);
					
					// Declare subject relation as functional.
					OWLFunctionalObjectPropertyAxiom subjRelFuncAxiom = 
							getOWLDataFactory().getOWLFunctionalObjectPropertyAxiom(subjRel);
					axiomsToAdd.add(subjRelFuncAxiom);
					
					break;
					
				}
				
				// Declare data relation.
				OWLDataProperty dataRel = getDataProperty(property);
				OWLDeclarationAxiom dataRelDeclAx = getOWLDataFactory().getOWLDeclarationAxiom(dataRel);
				axiomsToAdd.add(dataRelDeclAx);
									
				// Set data relation domain to the reified property class.
				OWLDataPropertyDomainAxiom dataRelDomainAxiom =
						getOWLDataFactory().getOWLDataPropertyDomainAxiom(dataRel, propertyAsClass);
				axiomsToAdd.add(dataRelDomainAxiom);
					
				// Declare data relation as functional.
				OWLFunctionalDataPropertyAxiom dataRelFuncAxiom = 
						getOWLDataFactory().getOWLFunctionalDataPropertyAxiom(dataRel);
				axiomsToAdd.add(dataRelFuncAxiom);
								
				// Declare holds property.
				OWLObjectProperty holdsRel = getHasIntervalProperty();
				OWLDeclarationAxiom holdsRelDeclAx = getOWLDataFactory().getOWLDeclarationAxiom(holdsRel);
				axiomsToAdd.add(holdsRelDeclAx);
				
				// Set restriction on holds property.
				OWLObjectPropertyRangeAxiom holdsRelRangeAxiom =
						getOWLDataFactory().getOWLObjectPropertyRangeAxiom(holdsRel, getIntervalClass());
				axiomsToAdd.add(holdsRelRangeAxiom);
				
				// Holds property is functional.
				OWLFunctionalObjectPropertyAxiom holdsFuncAxiom = 
						getOWLDataFactory().getOWLFunctionalObjectPropertyAxiom(holdsRel);
				axiomsToAdd.add(holdsFuncAxiom);
				
				// Set restriction on reified property class for holds relation.
				OWLObjectSomeValuesFrom holdsRestriction = 
						getOWLDataFactory().getOWLObjectSomeValuesFrom(holdsRel, getIntervalClass());
				OWLSubClassOfAxiom propertyHoldsRestriction = 
						getOWLDataFactory().getOWLSubClassOfAxiom(propertyAsClass, holdsRestriction);
				axiomsToAdd.add(propertyHoldsRestriction);
				
				addAxioms(axiomsToAdd);
			}
			
		});
	}


	@Override
	public void visit(FluentObjectPropertyDomainAxiom axiom) {
		FluentObjectProperty property = axiom.getProperty();
		IRI propertyIRI = property.getProperty().getIRI();
		OWLClass propertyAsClass = getOWLDataFactory().getOWLClass(propertyIRI);
		OWLClassExpression classExpression = axiom.getClassExpression();
		OWLObjectProperty subjRel = getSubjectProperty(property);
		
		switch(getSubjectRelationDirection(property)) {
		case REIF_TO_SUBJECT:
			// Set range on subject relation.
			OWLObjectPropertyRangeAxiom rangeAxiom =
					getOWLDataFactory().getOWLObjectPropertyRangeAxiom(subjRel, classExpression);
			addAxiom(rangeAxiom);

			// Add existential restriction to property class.
			OWLObjectSomeValuesFrom subjRestriction = 
					getOWLDataFactory().getOWLObjectSomeValuesFrom(subjRel, classExpression);
			OWLSubClassOfAxiom subclassOfAxiom = 
					getOWLDataFactory().getOWLSubClassOfAxiom(propertyAsClass, subjRestriction);
			addAxiom(subclassOfAxiom);
			
			break;
		case SUBJECT_TO_REIF:
			// Set domain on subject relation.
			OWLObjectPropertyDomainAxiom domainAxiom =
					getOWLDataFactory().getOWLObjectPropertyDomainAxiom(subjRel, classExpression);
			addAxiom(domainAxiom);
			
			break;
		}
	}
	
	@Override
	public void visit(FluentObjectPropertyRangeAxiom axiom) {
		FluentObjectProperty property = axiom.getProperty();
		IRI propertyIRI = property.getProperty().getIRI();
		OWLClass propertyAsClass = getOWLDataFactory().getOWLClass(propertyIRI);
		OWLClassExpression classExpression = axiom.getClassExpression();
		OWLObjectProperty objRel = getObjectProperty(property);
		
		switch(getObjectRelationDirection(property)) {
		case REIF_TO_OBJECT:
			// Set range on object relation.
			OWLObjectPropertyRangeAxiom rangeAxiom =
					getOWLDataFactory().getOWLObjectPropertyRangeAxiom(objRel, classExpression);
			addAxiom(rangeAxiom);

			// Add existential restriction to property class.
			OWLObjectSomeValuesFrom objRestriction = 
					getOWLDataFactory().getOWLObjectSomeValuesFrom(objRel, classExpression);
			OWLSubClassOfAxiom subclassOfAxiom = 
					getOWLDataFactory().getOWLSubClassOfAxiom(propertyAsClass, objRestriction);
			addAxiom(subclassOfAxiom);
			
			break;
		case OBJECT_TO_REIF:
			// Set domain on object relation.
			OWLObjectPropertyDomainAxiom domainAxiom =
					getOWLDataFactory().getOWLObjectPropertyDomainAxiom(objRel, classExpression);
			addAxiom(domainAxiom);
			
			break;
		}
	}

	@Override
	public void visit(FluentDataPropertyDomainAxiom axiom) {
		FluentDataProperty property = axiom.getProperty();
		IRI propertyIRI = property.getProperty().getIRI();
		OWLClass propertyAsClass = getOWLDataFactory().getOWLClass(propertyIRI);
		OWLClassExpression classExpression = axiom.getClassExpression();
		OWLObjectProperty subjRel = getSubjectProperty(property);
		
		switch(getSubjectRelationDirection(property)) {
		case REIF_TO_SUBJECT:
			// Set range on subject relation.
			OWLObjectPropertyRangeAxiom rangeAxiom =
					getOWLDataFactory().getOWLObjectPropertyRangeAxiom(subjRel, classExpression);
			addAxiom(rangeAxiom);

			// Add existential restriction to property class.
			OWLObjectSomeValuesFrom subjRestriction = 
					getOWLDataFactory().getOWLObjectSomeValuesFrom(subjRel, classExpression);
			OWLSubClassOfAxiom subclassOfAxiom = 
					getOWLDataFactory().getOWLSubClassOfAxiom(propertyAsClass, subjRestriction);
			addAxiom(subclassOfAxiom);
			
			break;
		case SUBJECT_TO_REIF:
			// Set domain on subject relation.
			OWLObjectPropertyDomainAxiom domainAxiom =
					getOWLDataFactory().getOWLObjectPropertyDomainAxiom(subjRel, classExpression);
			addAxiom(domainAxiom);
			
			break;
		}
	}

	@Override
	public void visit(FluentDataPropertyRangeAxiom axiom) {
		FluentDataProperty property = axiom.getProperty();
		IRI propertyIRI = property.getProperty().getIRI();
		OWLClass propertyAsClass = getOWLDataFactory().getOWLClass(propertyIRI);
		OWLDataRange dataRange = axiom.getRange();
		OWLDataProperty dataRel = getDataProperty(property);
		
		// Set range on data relation.
		OWLDataPropertyRangeAxiom rangeAxiom =
				getOWLDataFactory().getOWLDataPropertyRangeAxiom(dataRel, dataRange);
		addAxiom(rangeAxiom);

		// Add existential restriction to property class.
		OWLDataSomeValuesFrom dataRestriction = 
				getOWLDataFactory().getOWLDataSomeValuesFrom(dataRel, dataRange);
		OWLSubClassOfAxiom subclassOfAxiom = 
				getOWLDataFactory().getOWLSubClassOfAxiom(propertyAsClass, dataRestriction);
		addAxiom(subclassOfAxiom);
	}

	@Override
	public void visit(FluentObjectPropertyAssertionAxiom axiom) {
		OWLIndividual subject = axiom.getSubject();
		FluentObjectProperty property = axiom.getProperty();
		OWLIndividual object = axiom.getObject();
		TimeInterval interval = axiom.getInterval();
		OWLIndividual propertyAssertionIndividual = getOWLDataFactory().getOWLAnonymousIndividual();
		IRI propertyIRI = property.getProperty().getIRI();
		OWLClass propertyAsClass = getOWLDataFactory().getOWLClass(propertyIRI);
		
		// Declare property assertion as individual of property class.
		OWLClassAssertionAxiom declAxiom = 
				getOWLDataFactory().getOWLClassAssertionAxiom(propertyAsClass, propertyAssertionIndividual);
		addAxiom(declAxiom);
		
		// Assert subject relation.
		OWLObjectProperty subjRel = getSubjectProperty(property);
		switch(getSubjectRelationDirection(property)) {
		case REIF_TO_SUBJECT:
			OWLObjectPropertyAssertionAxiom subjAssertionAxiom_R_S =
					getOWLDataFactory().getOWLObjectPropertyAssertionAxiom(subjRel, propertyAssertionIndividual, subject);
			addAxiom(subjAssertionAxiom_R_S);
			break;
		case SUBJECT_TO_REIF:
			OWLObjectPropertyAssertionAxiom subjAssertionAxiom_S_R =
					getOWLDataFactory().getOWLObjectPropertyAssertionAxiom(subjRel, subject, propertyAssertionIndividual);
			addAxiom(subjAssertionAxiom_S_R);
			break;
		}
		
		// Assert object relation.
		OWLObjectProperty objRel = getObjectProperty(property);
		switch(getObjectRelationDirection(property)) {
		case REIF_TO_OBJECT:
			OWLObjectPropertyAssertionAxiom objAssertionAxiom_R_S =
					getOWLDataFactory().getOWLObjectPropertyAssertionAxiom(objRel, propertyAssertionIndividual, object);
			addAxiom(objAssertionAxiom_R_S);
			break;
		case OBJECT_TO_REIF:
			OWLObjectPropertyAssertionAxiom objAssertionAxiom_S_R =
					getOWLDataFactory().getOWLObjectPropertyAssertionAxiom(objRel, object, propertyAssertionIndividual);
			addAxiom(objAssertionAxiom_S_R);
			break;
		}
		
		// Assert time relation.
		OWLObjectProperty holdsRel = getHasIntervalProperty();
		OWLObjectPropertyAssertionAxiom holdsAxiom =
				getOWLDataFactory().getOWLObjectPropertyAssertionAxiom(holdsRel, propertyAssertionIndividual, interval.getIndividual());
		addAxiom(holdsAxiom);
	}

	@Override
	public void visit(FluentDataPropertyAssertionAxiom axiom) {
		OWLIndividual subject = axiom.getSubject();
		FluentDataProperty property = axiom.getProperty();
		OWLLiteral object = axiom.getObject();
		TimeInterval interval = axiom.getInterval();
		OWLIndividual propertyAssertionIndividual = getOWLDataFactory().getOWLAnonymousIndividual();
		IRI propertyIRI = property.getProperty().getIRI();
		OWLClass propertyAsClass = getOWLDataFactory().getOWLClass(propertyIRI);
		
		// Declare property assertion as individual of property class.
		OWLClassAssertionAxiom declAxiom = 
				getOWLDataFactory().getOWLClassAssertionAxiom(propertyAsClass, propertyAssertionIndividual);
		addAxiom(declAxiom);
		
		// Assert subject relation.
		OWLObjectProperty subjRel = getSubjectProperty(property);
		switch(getSubjectRelationDirection(property)) {
		case REIF_TO_SUBJECT:
			OWLObjectPropertyAssertionAxiom subjAssertionAxiom_R_S =
					getOWLDataFactory().getOWLObjectPropertyAssertionAxiom(subjRel, propertyAssertionIndividual, subject);
			addAxiom(subjAssertionAxiom_R_S);
			break;
		case SUBJECT_TO_REIF:
			OWLObjectPropertyAssertionAxiom subjAssertionAxiom_S_R =
					getOWLDataFactory().getOWLObjectPropertyAssertionAxiom(subjRel, subject, propertyAssertionIndividual);
			addAxiom(subjAssertionAxiom_S_R);
			break;
		}
		
		// Assert object relation.
		OWLDataProperty dataRel = getDataProperty(property);
		OWLDataPropertyAssertionAxiom dataAssertionAxiom =
				getOWLDataFactory().getOWLDataPropertyAssertionAxiom(dataRel, propertyAssertionIndividual, object);
		addAxiom(dataAssertionAxiom);
		
		// Assert time relation.
		OWLObjectProperty holdsRel = getHasIntervalProperty();
		OWLObjectPropertyAssertionAxiom holdsAxiom =
				getOWLDataFactory().getOWLObjectPropertyAssertionAxiom(holdsRel, propertyAssertionIndividual, interval.getIndividual());
		addAxiom(holdsAxiom);
	}
	
	private OWLClass getIntervalClass() {
		return getScheme().getIntervalClass(getOWLDataFactory());
	}

	private ReificationConfiguration getConfiguration() {
		return getScheme().getConfiguration();
	}
	
	private SubjectRelationDirection getSubjectRelationDirection(FluentObjectProperty property) {
		IRI propertyIRI = property.getProperty().getIRI();
		return getConfiguration().getFluentObjectPropertySubjectRelationDirection(propertyIRI);
	}

	private SubjectRelationDirection getSubjectRelationDirection(FluentDataProperty property) {
		IRI propertyIRI = property.getProperty().getIRI();
		return getConfiguration().getFluentDataPropertySubjectRelationDirection(propertyIRI);
	}

	private ObjectRelationDirection getObjectRelationDirection(FluentObjectProperty property) {
		IRI propertyIRI = property.getProperty().getIRI();
		return getConfiguration().getFluentObjectPropertyObjectRelationDirection(propertyIRI);
	}
	
	private OWLObjectProperty getSubjectProperty(FluentObjectProperty property) {
		IRI propertyIRI = property.getProperty().getIRI();
		return getOWLDataFactory().getOWLObjectProperty(getConfiguration().getFluentObjectPropertySubjectRelationName(propertyIRI));
	}
	
	private OWLObjectProperty getSubjectProperty(FluentDataProperty property) {
		IRI propertyIRI = property.getProperty().getIRI();
		return getOWLDataFactory().getOWLObjectProperty(getConfiguration().getFluentDataPropertySubjectRelationName(propertyIRI));
	}

	private OWLObjectProperty getObjectProperty(FluentObjectProperty property) {
		IRI propertyIRI = property.getProperty().getIRI();
		return getOWLDataFactory().getOWLObjectProperty(getConfiguration().getFluentObjectPropertyObjectRelationName(propertyIRI));
	}
	
	private OWLDataProperty getDataProperty(FluentDataProperty property) {
		IRI propertyIRI = property.getProperty().getIRI();
		return getOWLDataFactory().getOWLDataProperty(getConfiguration().getFluentDataPropertyDataRelationName(propertyIRI));
	}
	
	private OWLObjectProperty getHasIntervalProperty() {
		return getScheme().getIntervalProperty(getOWLDataFactory());
	}

}
