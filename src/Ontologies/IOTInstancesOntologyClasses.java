package Ontologies;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;

public class IOTInstancesOntologyClasses {

	private static final String iotlins_URI = "http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#";
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";
	
	private static final OntModel IOTLiteInstancesOntologyModel = ModelFactory.createOntologyModel();
	
	public static OntClass getMiniServerClass(){
		OntClass MiniServer = IOTLiteInstancesOntologyModel.createClass(iotlins_URI+"MiniServer");
		return MiniServer ;
	}
	
	public static OntClass getMetaDataClass(){
		OntClass MetaData = IOTLiteInstancesOntologyModel.createClass(iotlins_URI+ "Metadata");
		return MetaData;
	}	
	
	public static OntClass getCommunicatingDeviceClass(){
		OntClass CommunicatingDevice = IOTLiteInstancesOntologyModel.createClass(iotlins_URI+ "CommunicatingDevice");
		return CommunicatingDevice;
	}	
	
	public static void main(String[] args) {
		System.out.println(getMetaDataClass());
	}
	
}
