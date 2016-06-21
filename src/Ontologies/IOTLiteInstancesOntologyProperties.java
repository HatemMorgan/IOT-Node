package Ontologies;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;

public class IOTLiteInstancesOntologyProperties {
	private static final String iotlins_URI = "http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#";
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";

	private static final OntModel IOTLiteInstancesOntologyModel = ModelFactory
			.createOntologyModel();

	public static OntProperty getHasApplicationName() {
		OntProperty hasApplicationName = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasApplicationName");
		return hasApplicationName;
	}

	public static OntProperty getHasApplicationDescription() {
		OntProperty hasApplicationDescription = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasApplicationDescription");
		return hasApplicationDescription;
	}

	public static OntProperty getUsesSystemProperty() {
		OntProperty usesSystem = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "usesSystem");
		return usesSystem;
	}

	public static OntProperty getUsesApplication() {
		OntProperty usesApplication = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "usesApplication");
		return usesApplication;
	}

	public static OntProperty getHasMetadataValue() {
		OntProperty hasMetadataValue = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasMetadataValue");
		return hasMetadataValue;
	}

	public static OntProperty getHasMetadataType() {
		OntProperty hasMetadataType = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasMetadataType");
		return hasMetadataType;
	}

	public static OntProperty getHasCommunicatingDevice() {
		OntProperty hasCommunicatingDevice = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasCommunicatingDevice");
		return hasCommunicatingDevice;
	}

	public static OntProperty getHasCommunicatingDeviceType() {
		OntProperty hasCommunicatingDeviceType = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "HasCommunicatingDeviceType");
		return hasCommunicatingDeviceType;
	}

	public static OntProperty getHasFrequency() {
		OntProperty hasFrequency = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasFrequency");
		return hasFrequency;
	}

	public static OntProperty getHasNetworkTopology() {
		OntProperty hasNetworkTopology = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasNetworkTopology");
		return hasNetworkTopology;
	}

	public static OntProperty getHasBandwidth() {
		OntProperty hasBandwidth = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasBandwidth");
		return hasBandwidth;
	}

}
