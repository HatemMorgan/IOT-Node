package Ontologies;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.sparql.vocabulary.FOAF;

public class FOAFOntologyProperties {
	private static final String FOAF_URI = "http://xmlns.com/foaf/0.1/";
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";

	private static final OntModel FOAFOntologyModel = OntologyMain
			.getFOAFOntModel();

	public static OntProperty firstName() {
		OntProperty firstName = FOAFOntologyModel.getOntProperty(FOAF_URI
				+ "firstName");
		return firstName;
	}

	public static OntProperty lastName() {
		OntProperty lastName = FOAFOntologyModel.getOntProperty(FOAF_URI
				+ "lastName");
		return lastName;
	}

	public static OntProperty birthday() {
		OntProperty birthday = FOAFOntologyModel.getOntProperty(FOAF_URI
				+ "birthday");
		return birthday;
	}

	public static OntProperty gender() {
		OntProperty gender = FOAFOntologyModel.getOntProperty(FOAF_URI
				+ "gender");
		return gender;
	}

	
}
