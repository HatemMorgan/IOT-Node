package Ontologies;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.ModelFactory;

import Server.IOTLiteOntologyController;

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

	public static OntClass bandwidth() {
		OntClass Bandwidth = IOTLiteInstancesOntologyModel
				.createClass(iotlins_URI + "Bandwidth");
		return Bandwidth;
	}

	public static OntClass networkTopology() {
		OntClass NetworkTopology = IOTLiteInstancesOntologyModel
				.createClass(iotlins_URI + "NetworkTopology");
		return NetworkTopology;
	}

	public static OntClass frequency() {
		OntClass Frequency = IOTLiteInstancesOntologyModel
				.createClass(iotlins_URI + "CommunicatingDevice");
		return Frequency;
	}

	public static OntClass communicationDeviceType() {
		OntClass type = IOTLiteInstancesOntologyModel.createClass(iotlins_URI
				+ "Type");
		return type;
	}

	public static OntClass communicatingDevice() {
		OntClass CommunicatingDevice = IOTLiteInstancesOntologyModel
				.createClass(iotlins_URI + "CommunicatingDevice");
		CommunicatingDevice.addSuperClass(IOTLiteOntologyClasses.device());
		return CommunicatingDevice;
	}

	public static OntClass application() {
		OntClass Application = IOTLiteInstancesOntologyModel
				.createClass(iotlins_URI + "Application");
		return Application;
	}

}
