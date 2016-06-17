package Ontologies;

import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.util.iterator.ExtendedIterator;

public class IOTLiteOntology {

	private static final String IOT_Lite_URI = "http://purl.oclc.org/NET/UNIS/fiware/iot-lite#";
	private static final String QU_URI = "http://purl.org/NET/ssnx/qu/qu#";
	private static final String SSN_URI = "http://purl.oclc.org/NET/ssnx/ssn#";
	private static final String iotlins_URI = "http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#";
	private static final OntModel IOTLiteOntologyModel = OntologyMain
			.getModel("iot-lite.rdf");

	public static OntModel createSystem() {
		// get ontology defined class
		OntClass systemClass = IOTLiteOntologyModel.getOntClass(SSN_URI
				+ "System");
		OntClass DeviceClass = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI
				+ "Device");
		OntClass SensorClass = IOTLiteOntologyModel.getOntClass(SSN_URI
				+ "Sensor");
		OntClass SensingDeviceClass = IOTLiteOntologyModel.getOntClass(SSN_URI
				+ "SensingDevice");
		OntClass unit = IOTLiteOntologyModel.getOntClass(QU_URI + "Unit");
		OntClass Attribute = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI
				+ "Attribute");

		// create new classes
		OntClass MetaData = IOTLiteOntologyModel.createClass(IOT_Lite_URI
				+ "Metadata");

		// create property for the new class
		Property metadataValue = IOTLiteOntologyModel
				.createProperty(IOT_Lite_URI + "metadataValue");
		Property metadataType = IOTLiteOntologyModel
				.createProperty(IOT_Lite_URI + "metadataType");

		// get ontology defined Properties
		Property hasSubSystem = IOTLiteOntologyModel.getOntProperty(SSN_URI
				+ "hasSubSystem");
		Property hasSensingDevice = IOTLiteOntologyModel
				.getOntProperty(IOT_Lite_URI + "hasSensingDevice");
		Property hasMetadata = IOTLiteOntologyModel.getOntProperty(IOT_Lite_URI
				+ "hasMetadata");
		Property hasUnit = IOTLiteOntologyModel.getOntProperty(IOT_Lite_URI
				+ "hasUnit");
		Property hasQuantityKind = IOTLiteOntologyModel
				.getOntProperty(IOT_Lite_URI + "hasQuantityKind");
		Property isAssociatedWith = IOTLiteOntologyModel
				.getOntProperty(IOT_Lite_URI + "isAssociatedWith");

		// create a system individual
		Individual SmartCampus = IOTLiteOntologyModel.createIndividual(
				iotlins_URI + "SmartCampus", systemClass);
		Individual SmartCCSR = IOTLiteOntologyModel.createIndividual(
				iotlins_URI + "SmartCCSR", systemClass);
		Individual TelosB001 = IOTLiteOntologyModel.createIndividual(
				iotlins_URI + "TelosB001", DeviceClass);
		Individual temperatureSensorTelosB = IOTLiteOntologyModel
				.createIndividual(SSN_URI + "temperatureSensorTelosB",
						SensorClass);
		Individual temperatureSensorRoom13CII01 = IOTLiteOntologyModel
				.createIndividual(SSN_URI + "temperatureSensorRoom13CII01",
						SensingDeviceClass);
		Individual degree_Celsius = IOTLiteOntologyModel.createIndividual(
				QU_URI + "degree_Celsius", unit);
		Individual meter = IOTLiteOntologyModel.createIndividual(QU_URI
				+ "Meter", unit);
		Individual meterPerSecond = IOTLiteOntologyModel.createIndividual(
				QU_URI + "meterPerSecond", unit);
		Individual resolution1024 = IOTLiteOntologyModel.createIndividual(
				iotlins_URI + "resolution1024", MetaData);
		Individual distanceSensor = IOTLiteOntologyModel.createIndividual(
				SSN_URI + "distanceSensor", SensorClass);
		Individual speedSensor = IOTLiteOntologyModel.createIndividual(SSN_URI
				+ "SpeedSensor", SensorClass);
		Individual temperatureTableRoom12CII01 = IOTLiteOntologyModel
				.createIndividual(IOT_Lite_URI + "temperatureTableRoom12CII01",
						Attribute);
		Individual temperature = IOTLiteOntologyModel.createIndividual("http://purl.org/NET/ssnx/qu/quantity#temperature",null);

		// create triples

		SmartCampus.addProperty(hasSubSystem, SmartCCSR);
		SmartCCSR.addProperty(hasSubSystem, TelosB001);
		TelosB001.addProperty(hasSubSystem, temperatureSensorRoom13CII01);
		temperatureSensorTelosB.addProperty(hasSensingDevice,
				temperatureSensorRoom13CII01);
		temperatureSensorTelosB.addProperty(hasUnit, degree_Celsius);
		temperatureSensorTelosB.addProperty(hasMetadata, resolution1024);
		temperatureSensorTelosB.addProperty(hasQuantityKind,
				temperature);
		resolution1024.addProperty(metadataType, "resolution");
		resolution1024.addProperty(metadataValue, "1024");
		distanceSensor.addProperty(hasSensingDevice,
				temperatureSensorRoom13CII01);
		distanceSensor.addProperty(hasUnit, meter);
		speedSensor.addProperty(hasSensingDevice, temperatureSensorRoom13CII01);
		speedSensor.addProperty(hasUnit, meterPerSecond);
		temperatureTableRoom12CII01.addProperty(isAssociatedWith,
				temperatureSensorRoom13CII01);
		temperatureTableRoom12CII01.addProperty(hasQuantityKind,
				temperature);
		
		return IOTLiteOntologyModel;
	}

	public static String getIotLiteUri() {
		return IOT_Lite_URI;
	}

	public static String getQuUri() {
		return QU_URI;
	}

	public static String getSsnUri() {
		return SSN_URI;
	}

	public static String getIotlinsUri() {
		return iotlins_URI;
	}

	public static OntModel getIotliteontologymodel() {
		return IOTLiteOntologyModel;
	}

}
