package Ontologies;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;



public class IOTInstancesOntologyClasses {

	private static final String iotlins_URI = "http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#";
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";

	private static final OntModel IOTLiteInstancesOntologyModel = OntologyMain.getIOTLiteInstancesOntModel();
	private static final OntModel IOTLiteOntologyModel = OntologyMain
			.getIOTLiteOntModel();

	public static OntClass miniServer() {
		OntClass MiniServer = IOTLiteInstancesOntologyModel
				.createClass(iotlins_URI + "MiniServer");
		MiniServer.addSuperClass(IOTLiteOntologyClasses.device());
		return MiniServer;
	}

	public static OntClass metaData() {
		OntClass MetaData = IOTLiteInstancesOntologyModel
				.createClass(iotlins_URI + "Metadata");
		return MetaData;
	}


	public static OntClass communicatingDevice() {
		OntClass CommunicatingDevice = IOTLiteInstancesOntologyModel
				.createClass(iotlins_URI + "CommunicatingDevice");
	
		return CommunicatingDevice;
	}

	public static OntClass application() {
		OntClass Application = IOTLiteInstancesOntologyModel
				.createClass(iotlins_URI + "Application");
		return Application;
	}
	
	public static OntClass SensingDeviceUUID() {
		OntClass SensingDeviceUUID = IOTLiteInstancesOntologyModel
				.createClass(iotlins_URI + "SensingDeviceUUID");
		return SensingDeviceUUID;
	}
	
	public static OntClass macAddress() {
		OntClass macAddress = IOTLiteInstancesOntologyModel
				.createClass(iotlins_URI + "macAddress");
		return macAddress;
	}

}
