package Ontologies;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Resource;

public class FOAFOntologyClasses {

	private static final String FOAF_URI = "http://xmlns.com/foaf/0.1/";
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";

	private static final OntModel FOAFOntologyModel = OntologyMain
			.getFOAFOntModel();

	public static OntClass PersonClass() {

		OntClass Person = FOAFOntologyModel.getOntClass(FOAF_URI + "Person");
		return Person;
	}

}
