package Ontologies;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;

import Server.IOTLiteOntologyController;

public class IOTInstancesOntologyClasses {

	private static final String iotlins_URI = "http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#";
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";

	
	private static final OntModel IOTLiteInstancesOntologyModel = ModelFactory.createOntologyModel();
	private static final OntModel IOTLiteOntologyModel = OntologyMain.getIOTLiteOntModel();
	
	public static OntClass getMiniServerClass(){
		OntClass MiniServer = IOTLiteInstancesOntologyModel.createClass(iotlins_URI+"MiniServer");
		MiniServer.addSuperClass(SSNOntologyClasses.getDeviceClass());
		return MiniServer ;
	}
	
	public static OntClass getMetaDataClass(){
		OntClass MetaData = IOTLiteInstancesOntologyModel.createClass(iotlins_URI+ "Metadata");
		return MetaData;
	}	
	
	public static OntClass getBandwidthClass(){
		OntClass Bandwidth = IOTLiteInstancesOntologyModel.createClass(iotlins_URI+ "Bandwidth");
		return Bandwidth;
	}	
	
	public static OntClass getNetworkTopologyClass(){
		OntClass NetworkTopology = IOTLiteInstancesOntologyModel.createClass(iotlins_URI+ "NetworkTopology");
		return NetworkTopology;
	}	
	
	public static OntClass getFrequencyClass(){
		OntClass Frequency = IOTLiteInstancesOntologyModel.createClass(iotlins_URI+ "CommunicatingDevice");
		return Frequency;
	}	
	
	public static OntClass getCommunicationDeviceTypeClass(){
		OntClass type = IOTLiteInstancesOntologyModel.createClass(iotlins_URI+ "Type");
		return type;
	}
	
	public static OntClass getCommunicatingDeviceClass(){
		OntClass CommunicatingDevice = IOTLiteInstancesOntologyModel.createClass(iotlins_URI+ "CommunicatingDevice");
		CommunicatingDevice.addSuperClass(SSNOntologyClasses.getDeviceClass());
		return CommunicatingDevice;
	}	
	
	public static OntClass getApplicationClass(){
		OntClass Application = IOTLiteInstancesOntologyModel.createClass(iotlins_URI+"Application");
		return Application;
	}
	
	
	public static void main(String[] args) {
		System.out.println(getCommunicatingDeviceClass());
	}
	
}
