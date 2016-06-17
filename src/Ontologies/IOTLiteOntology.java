package Ontologies;

import org.apache.jena.ontology.OntModel;

public class IOTLiteOntology {
	private static final String IOT_Lite_URI = "http://purl.oclc.org/NET/UNIS/fiware/iot-lite#";
	private static final String QU_URI = "http://purl.org/NET/ssnx/qu/qu#";
	private static final String SSN_URI = "http://purl.oclc.org/NET/ssnx/ssn#";
	private static final OntModel IOTLiteOntologyModel = OntologyMain.getModel("iot-lite.rdf");

	public static String getIotLiteUri() {
		return IOT_Lite_URI;
	}

	public static String getQuUri() {
		return QU_URI;
	}

	public static String getSsnUri() {
		return SSN_URI;
	}

	public static OntModel getIOTLiteOntologyModel() {
		return IOTLiteOntologyModel;
	}

}
