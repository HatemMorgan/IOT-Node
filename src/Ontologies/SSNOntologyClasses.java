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
	
	public static OntClass getDeviceClass(){
		OntClass Device = SSNOntologyModel.getOntClass(SSN_URI+"Device");
		return Device;
	}
	
	public static OntClass getSensingDeviceClass(){
		OntClass SensingDevice = SSNOntologyModel.getOntClass(SSN_URI+"SensingDevice");
		return SensingDevice;
	}
	
	public static OntClass getSensorClass(){
		OntClass Sensor = SSNOntologyModel.getOntClass(SSN_URI+"Sensor");
		return Sensor;
	}
	
	public static OntClass getSensorOutputClass(){
		OntClass SensorOutput = SSNOntologyModel.getOntClass(SSN_URI+"SensorOutput");
		return SensorOutput;
	}
	
	public static OntClass getObservationValueClass(){
		OntClass ObservationValue = SSNOntologyModel.getOntClass(SSN_URI+"ObservationValue");
		return ObservationValue;
	}
	
	
	public static void main(String[] args) {
	  System.out.println(getObservationValueClass());
	}
}
