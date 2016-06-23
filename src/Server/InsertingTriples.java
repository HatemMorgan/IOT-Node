package Server;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;

import Ontologies.OntologyMain;
import Ontologies.SSNOntologyClasses;
import Ontologies.SSNOntologyProperties;

public class InsertingTriples {
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";
	private static final String iotlins_URI = "http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#";
	private static final String QU_URI = "http://purl.org/NET/ssnx/qu/qu#";
	private static final String IOT_Lite_URI = "http://purl.oclc.org/NET/UNIS/fiware/iot-lite#";
	private static final String GEO_URI = "http://www.w3.org/2003/01/geo/wgs84_pos#";
	private static final String SSN_URI = "http://purl.oclc.org/NET/ssnx/ssn#";
	private static final String FOAF_URI = "http://xmlns.com/foaf/0.1/";
//	private static final OntModel FOAFOntologyModel = OntologyMain.getFOAFOntModel();
//	private static final OntModel IOTLiteInstancesOntologyModel = OntologyMain.getIOTLiteInstancesOntModel();
//	private static final OntModel IOTLiteOntologyModel = OntologyMain.getIOTLiteOntModel();
//	private static final OntModel SSNOntologyModel = OntologyMain.getSSNOntModel();
    
	private static final OntModel model = ModelFactory.createOntologyModel();
	
	 public static void insertNewSystem (String SystemName , String[] subSystems){
		 Individual newSystem = model.createIndividual(SSN_URI+SystemName,SSNOntologyClasses.system());
		
		 for (String subSystemName : subSystems) {
			Individual subSystem =  model.createIndividual(SSN_URI+subSystemName,SSNOntologyClasses.system());
			newSystem.addProperty(SSNOntologyProperties.hasSubSystem(), subSystem);
		}
		 System.out.println("new System inserted");
	 }
	 
	 public static void insertNewSensor(){
		 
	 }
	 
}
