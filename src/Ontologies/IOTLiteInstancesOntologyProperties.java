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

	public static OntProperty hasApplicationName() {
		OntProperty hasApplicationName = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasApplicationName");
		return hasApplicationName;
	}

	public static OntProperty hasApplicationDescription() {
		OntProperty hasApplicationDescription = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasApplicationDescription");
		return hasApplicationDescription;
	}

	public static OntProperty usesSystem() {
		OntProperty usesSystem = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "usesSystem");
		return usesSystem;
	}

	public static OntProperty usesApplication() {
		OntProperty usesApplication = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "usesApplication");
		return usesApplication;
	}

	public static OntProperty hasMetadata() {
		OntProperty hasMetadata = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasMetadata");
		return hasMetadata;
	}

	public static OntProperty hasCommunicatingDevice() {
		OntProperty hasCommunicatingDevice = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasCommunicatingDevice");
		return hasCommunicatingDevice;
	}

	public static OntProperty hasFrequency() {
		OntProperty hasFrequency = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasFrequency");
		return hasFrequency;
	}

	public static OntProperty hasNetworkTopology() {
		OntProperty hasNetworkTopology = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasNetworkTopology");
		return hasNetworkTopology;
	}

	public static OntProperty hasBandwidth() {
		OntProperty hasBandwidth = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasBandwidth");
		return hasBandwidth;
	}

	public static OntProperty createNewProperty(String propertyName) {
		OntProperty newProperty = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + propertyName);
		return newProperty;
	}

	public static OntProperty hasType() {
		OntProperty hasType = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasType");
		return hasType;
	}

	public static OntProperty isConnectedTo() {
		OntProperty isConnectedTo = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "isConnectedTo");
		return isConnectedTo;
	}

	public static OntProperty email() {
		OntProperty email = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "email");
		return email;
	}

	public static OntProperty hasRole() {
		OntProperty hasRole = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasRole");
		return hasRole;
	}

	public static OntProperty hasTransmitPower() {
		OntProperty hasTransmitPower = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasTransmitPower");
		return hasTransmitPower;
	}
	public static OntProperty hasSensitivity() {
		OntProperty hasSensitivity = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasSensitivity");
		return hasSensitivity;
	}
	public static OntProperty hasNumberOfChannels() {
		OntProperty hasNumberOfChannels = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasNumberOfChannels");
		return hasNumberOfChannels;
	}
	public static OntProperty hasMacaddress() {
		OntProperty hasMacaddress = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasMacaddress");
		return hasMacaddress;
	}
	public static OntProperty hasDutyCycle() {
		OntProperty hasDutyCycle = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasDutyCycle");
		return hasDutyCycle;
	}

	public static OntProperty hasUUID() {
		OntProperty hasUUID = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasUUID");
		return hasUUID;
	}
	
	public static OntProperty hasName() {
		OntProperty hasName = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasName");
		return hasName;
	}
	
	public static OntProperty hasSensingDeviceUUID() {
		OntProperty hasSensingDeviceUUID = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasSensingDeviceUUID");
		return hasSensingDeviceUUID;
	}
}
