package Ontologies;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;

public class SSNOntologyProperties {

	private static final String SSN_URI = "http://purl.oclc.org/NET/ssnx/ssn#";
	private static final String GEO_URI = "http://www.w3.org/2003/01/geo/wgs84_pos#";
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";

	private static final OntModel SSNOntologyModel = OntologyMain.getSSNOntModel();
	
	public static OntProperty getHasSubSystemProperty(){
		OntProperty hasSubSystem = SSNOntologyModel.getOntProperty(SSN_URI+"hasSubSystem");
		return hasSubSystem;
	}
	
	public static OntProperty getIsProducedByProperty(){
		OntProperty isProducedBy = SSNOntologyModel.getOntProperty(SSN_URI+"isProducedBy");
		return isProducedBy;
	}
	
	public static OntProperty getHasValueProperty(){
		OntProperty hasValue = SSNOntologyModel.getOntProperty(SSN_URI+"hasValue");
		return hasValue;
	}
	
	public static OntProperty getObservationResultTimeProperty(){
		OntProperty observationResultTime = SSNOntologyModel.getOntProperty(SSN_URI+"observationResultTime");
		return observationResultTime;
	}
	
}
