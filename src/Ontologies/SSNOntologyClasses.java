package Ontologies;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;

public class SSNOntologyClasses {

	private static final String SSN_URI = "http://purl.oclc.org/NET/ssnx/ssn#";
	private static final String GEO_URI = "http://www.w3.org/2003/01/geo/wgs84_pos#";
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";

	private static final OntModel SSNOntologyModel = OntologyMain.getSSNOntModel();

	
	public static OntClass getSystemClass(){
		OntClass system = SSNOntologyModel.getOntClass(SSN_URI+"System");
		return system;
	}
	
	public static void main(String[] args) {
		getSystemClass();
	}
}
