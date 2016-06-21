package Ontologies;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;

public class SSNOntologyClasses {

	private static final String SSN_URI = "http://purl.oclc.org/NET/ssnx/ssn#";
	private static final String GEO_URI = "http://www.w3.org/2003/01/geo/wgs84_pos#";
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";

	private static final OntModel SSNOntologyModel = OntologyMain.getSSNOntModel();

	
	public static OntClass system(){
		OntClass system = SSNOntologyModel.getOntClass(SSN_URI+"System");
		return system;
	}
	
	public static OntClass device(){
		OntClass Device = SSNOntologyModel.getOntClass(SSN_URI+"Device");
		return Device;
	}
	
	public static OntClass sensingDevice(){
		OntClass SensingDevice = SSNOntologyModel.getOntClass(SSN_URI+"SensingDevice");
		return SensingDevice;
	}
	
	public static OntClass sensor(){
		OntClass Sensor = SSNOntologyModel.getOntClass(SSN_URI+"Sensor");
		return Sensor;
	}
	
	public static OntClass sensorOutput(){
		OntClass SensorOutput = SSNOntologyModel.getOntClass(SSN_URI+"SensorOutput");
		return SensorOutput;
	}
	
	public static OntClass observationValue(){
		OntClass ObservationValue = SSNOntologyModel.getOntClass(SSN_URI+"ObservationValue");
		return ObservationValue;
	}
	
	

}
