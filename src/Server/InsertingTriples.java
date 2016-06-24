package Server;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Literal;

import com.github.andrewoma.dexx.collection.ArrayList;

import JenaFusekiServer.FusekiQueries;
import Ontologies.FOAFOntologyClasses;
import Ontologies.FOAFOntologyProperties;
import Ontologies.IOTInstancesOntologyClasses;
import Ontologies.IOTLiteInstancesOntologyProperties;
import Ontologies.IOTLiteOntologyClasses;
import Ontologies.IOTLiteOntologyProperties;
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

	// private static final OntModel FOAFOntologyModel =
	// OntologyMain.getFOAFOntModel();
	// private static final OntModel IOTLiteInstancesOntologyModel =
	// OntologyMain.getIOTLiteInstancesOntModel();
	// private static final OntModel IOTLiteOntologyModel =
	// OntologyMain.getIOTLiteOntModel();
	// private static final OntModel SSNOntologyModel =
	// OntologyMain.getSSNOntModel();

	public static Individual insertSystem(String SystemName, String[] subSystems) {
		OntModel model = ModelFactory.createOntologyModel();
		Individual newSystem = model.createIndividual(SSN_URI + SystemName,
				IOTLiteOntologyClasses.system());
		FusekiQueries.insertOntmodel(model);
		return newSystem;
	}

	public static Individual insertSubSystem(Individual system,
			String subSystemName) {
		OntModel model = ModelFactory.createOntologyModel();
		Individual subSystem = model.createIndividual(SSN_URI + subSystemName,
				IOTLiteOntologyClasses.system());
		system.addProperty(IOTLiteOntologyProperties.hasSubSystem(), subSystem);
		FusekiQueries.insertOntmodel(model);
		return subSystem;
	}

	public static Individual insertDevice(Individual system, String DeviceName,
			Individual miniServer, Individual service) {
		OntModel model = ModelFactory.createOntologyModel();
		Individual newDevice = model.createIndividual(SSN_URI + DeviceName,
				IOTLiteOntologyClasses.device());
		newDevice.addProperty(
				IOTLiteInstancesOntologyProperties.isConnectedTo(), miniServer);
		newDevice.addProperty(IOTLiteOntologyProperties.exposedBy(), service);

		insertSystemDeviceRelation(system, newDevice);

		FusekiQueries.insertOntmodel(model);
		return newDevice;
	}

	public static Individual insertSensingDevice(Individual device,
			String sensingDeviceName) {
		OntModel model = ModelFactory.createOntologyModel();
		Individual newSensingDevice = model.createIndividual(SSN_URI
				+ sensingDeviceName, IOTLiteOntologyClasses.sensingDevice());
		device.addProperty(IOTLiteOntologyProperties.hasSubSystem(),
				newSensingDevice);
		FusekiQueries.insertOntmodel(model);
		return newSensingDevice;
	}

	public static Individual insertCommunicatingDevice(Individual Device,
			String communicatingDeviceName, String type, String bandwidth,
			String networkTopology, String frequency, String transmitPower) {
		OntModel model = ModelFactory.createOntologyModel();
		Individual newCommunicatingDevice = model.createIndividual(iotlins_URI
				+ communicatingDeviceName,
				IOTInstancesOntologyClasses.communicatingDevice());

		Device.addProperty(IOTLiteOntologyProperties.hasSubSystem(),
				newCommunicatingDevice);
		newCommunicatingDevice.addProperty(
				IOTLiteInstancesOntologyProperties.hasBandwidth(), bandwidth);
		newCommunicatingDevice.addProperty(
				IOTLiteInstancesOntologyProperties.hasFrequency(), frequency);
		newCommunicatingDevice.addProperty(
				IOTLiteInstancesOntologyProperties.hasNetworkTopology(),
				networkTopology);
		newCommunicatingDevice.addProperty(
				IOTLiteInstancesOntologyProperties.hasType(), type);

		newCommunicatingDevice.addProperty(
				IOTLiteInstancesOntologyProperties.hasTransmitPower(),
				transmitPower);

		FusekiQueries.insertOntmodel(model);
		return newCommunicatingDevice;
	}

	public static Individual insertMiniServer(String miniServerName,
			Individual system, String LocationName, String longtitude,
			String latitude) {
		OntModel model = ModelFactory.createOntologyModel();

		Individual miniServer = model.createIndividual(iotlins_URI
				+ miniServerName, IOTInstancesOntologyClasses.miniServer());
		Individual locationPoint = model.createIndividual(GEO_URI
				+ LocationName, IOTLiteOntologyClasses.point());

		system.addProperty(IOTLiteOntologyProperties.hasSubSystem(), miniServer);
		miniServer.addProperty(IOTLiteOntologyProperties.hasLocation(),
				locationPoint);
		locationPoint.addProperty(IOTLiteOntologyProperties.longtitude(),
				longtitude);
		locationPoint.addProperty(IOTLiteOntologyProperties.latitude(),
				latitude);

		FusekiQueries.insertOntmodel(model);

		return miniServer;
	}

	public static void insertSensor(Individual sensingDevice,
			String sensorName, String strUnit, String strQuantityKind,
			Individual communicatingDevice,
			Hashtable<String, Object> metadataList) {
		OntModel model = ModelFactory.createOntologyModel();
		Individual newSensor = model.createIndividual(SSN_URI + sensorName,
				IOTLiteOntologyClasses.sensor());
		Individual metadata = model.createIndividual(iotlins_URI + sensorName
				+ "Metadata", IOTInstancesOntologyClasses.metaData());

		sensingDevice.addProperty(IOTLiteOntologyProperties.hasSensingDevice(),
				newSensor);
		newSensor.addProperty(IOTLiteOntologyProperties.hasQuantityKind(),
				QU_URI + strQuantityKind);
		newSensor.addProperty(IOTLiteOntologyProperties.hasUnit(), QU_URI
				+ strUnit);
		newSensor.addProperty(
				IOTLiteInstancesOntologyProperties.hasCommunicatingDevice(),
				communicatingDevice);
		newSensor.addProperty(IOTLiteInstancesOntologyProperties.hasMetadata(),
				metadata);

		Set<String> metadataSet = metadataList.keySet();
		Iterator<String> metadataItr = metadataSet.iterator();
		while (metadataItr.hasNext()) {
			String key = metadataItr.next();
			Object value = metadataList.get(key);
			metadata.addProperty(
					IOTLiteInstancesOntologyProperties.createNewProperty("has"
							+ key), value.toString());
		}
		FusekiQueries.insertOntmodel(model);
	}

	public static Individual insertService(String serviceName, String endpoint,
			String interfaceDescription) {
		OntModel model = ModelFactory.createOntologyModel();

		Individual newServiece = model.createIndividual(IOT_Lite_URI
				+ serviceName, IOTLiteOntologyClasses.service());

		newServiece.addProperty(IOTLiteOntologyProperties.endPoint(), endpoint);
		newServiece.addProperty(
				IOTLiteOntologyProperties.interfaceDescription(),
				interfaceDescription);

		FusekiQueries.insertOntmodel(model);

		return newServiece;

	}

	/*
	 * add new place like a room for example
	 */
	public static void insertObject(String objectName, Individual Attribute,
			String locationName, String longtitude, String latitude) {
		OntModel model = ModelFactory.createOntologyModel();

		Individual newObject = model.createIndividual(
				IOT_Lite_URI + objectName, IOTLiteOntologyClasses.object());
		Individual locationPoint = model.createIndividual(GEO_URI
				+ locationName, IOTLiteOntologyClasses.point());

		locationPoint.addProperty(IOTLiteOntologyProperties.longtitude(),
				longtitude);
		locationPoint.addProperty(IOTLiteOntologyProperties.latitude(),
				latitude);
		newObject.addProperty(IOTLiteOntologyProperties.hasLocation(),
				locationPoint);
		newObject.addProperty(IOTLiteOntologyProperties.hasAttribute(),
				Attribute);

		FusekiQueries.insertOntmodel(model);

	}

	/*
	 * add an attribute for an object An attribute of an IoT object that can be
	 * exposed by an IoT service (i.e. a room (IoT Object) has a temperature
	 * (Attribute), that can be exposed by a temperature sensor (IoT device)
	 */

	public static Individual insertAttribute(String attributeName,
			Individual device, String quanityKind) {
		OntModel model = ModelFactory.createOntologyModel();

		Individual newAttrubute = model.createIndividual(IOT_Lite_URI
				+ attributeName, IOTLiteOntologyClasses.attribute());

		newAttrubute.addProperty(IOTLiteOntologyProperties.isAssociatedWith(),
				device);
		newAttrubute.addProperty(IOTLiteOntologyProperties.hasQuantityKind(),
				QU_URI + quanityKind);

		FusekiQueries.insertOntmodel(model);

		return newAttrubute;
	}

	public static void insertSensorOutputData(String SensorOutputdata,
			Individual Sensor, String strValue, String DateTime) {
		OntModel model = ModelFactory.createOntologyModel();

		Individual newSensorOutput = model.createIndividual(SSN_URI
				+ SensorOutputdata, SSNOntologyClasses.sensorOutput());
		Individual value = model.createIndividual(SSN_URI + strValue,
				SSNOntologyClasses.observationValue());

		newSensorOutput.addProperty(SSNOntologyProperties.isProducedBy(),
				Sensor);
		newSensorOutput.addProperty(SSNOntologyProperties.hasValue(), value);
		newSensorOutput.addProperty(
				SSNOntologyProperties.observationResultTime(), DateTime);

		FusekiQueries.insertOntmodel(model);

	}

	public static void insertCoverage(String coverageAreaName,
			Individual device, String coverageType, ArrayList<Individual> Points)
			throws Exception {
		OntModel model = ModelFactory.createOntologyModel();

		Individual newCoverage;
		switch (coverageType) {
		case "Circle":
			newCoverage = model.createIndividual(IOT_Lite_URI + coverageType,
					IOTLiteOntologyClasses.circle());

			break;
		case "Rectangle":
			newCoverage = model.createIndividual(IOT_Lite_URI + coverageType,
					IOTLiteOntologyClasses.rectangle());

			break;
		case "Polygon":
			model.createIndividual(IOT_Lite_URI + coverageType,
					IOTLiteOntologyClasses.polygon());
		default:
			throw new Exception("no coverage type equal to " + coverageType);

		}
		for (Individual point : Points) {
			newCoverage
					.addProperty(IOTLiteOntologyProperties.hasPoint(), point);
		}

		device.addProperty(IOTLiteOntologyProperties.hasCoverage(), newCoverage);

		FusekiQueries.insertOntmodel(model);

	}

	public static Individual createPoint(String pointName, String longtitude,
			String latitude) {
		OntModel model = ModelFactory.createOntologyModel();

		Individual newPoint = model.createIndividual(GEO_URI + pointName,
				IOTLiteOntologyClasses.point());

		newPoint.addProperty(IOTLiteOntologyProperties.longtitude(), longtitude);
		newPoint.addProperty(IOTLiteOntologyProperties.latitude(), latitude);

		return newPoint;
	}

	public static Individual insertApplication(String applicationName,
			String applicationDescription, Individual system) {
		OntModel model = ModelFactory.createOntologyModel();

		Individual newApplication = model.createIndividual(iotlins_URI
				+ applicationName, IOTInstancesOntologyClasses.application());

		newApplication.addProperty(
				IOTLiteInstancesOntologyProperties.hasApplicationName(),
				applicationName);
		newApplication.addProperty(
				IOTLiteInstancesOntologyProperties.hasApplicationDescription(),
				applicationDescription);

		insertApplicationSystemRelation(newApplication, system);

		FusekiQueries.insertOntmodel(model);

		return newApplication;
	}

	public static void insertPerson(String personName, String firstName,
			String lastName, String gender, String Birthday,
			Individual application, String email, String role) {

		OntModel model = ModelFactory.createOntologyModel();

		Individual newPerson = model.createIndividual(FOAF_URI + personName,
				FOAFOntologyClasses.PersonClass());

		newPerson.addProperty(FOAFOntologyProperties.firstName(), firstName);
		newPerson.addProperty(FOAFOntologyProperties.lastName(), lastName);
		newPerson.addProperty(FOAFOntologyProperties.gender(), gender);
		newPerson.addProperty(FOAFOntologyProperties.birthday(), Birthday);
		newPerson
				.addProperty(IOTLiteInstancesOntologyProperties.email(), email);
		newPerson.addProperty(IOTLiteInstancesOntologyProperties.hasRole(),
				role);

		insertPersonApplicationRelation(newPerson, application);

		FusekiQueries.insertOntmodel(model);

	}

	public static void insertPersonApplicationRelation(Individual Person,
			Individual Application) {

		FusekiQueries
				.insertNewTriple(Person.toString(),
						IOTLiteInstancesOntologyProperties.usesApplication()
								.toString(), Application.toString(), null);

	}

	public static void insertApplicationSystemRelation(Individual application,
			Individual system) {
		FusekiQueries
				.insertNewTriple(application.toString(),
						IOTLiteInstancesOntologyProperties.usesApplication()
								.toString(), system.toString(), null);
	}

	public static void insertSystemDeviceRelation(Individual system,
			Individual device) {
		FusekiQueries.insertNewTriple(system.toString(),
				IOTLiteOntologyProperties.hasSubSystem().toString(),
				device.toString(), null);
	}

	public static void insertDeviceServiceRelation(Individual device,
			Individual service) {
		FusekiQueries.insertNewTriple(device.toString(),
				IOTLiteOntologyProperties.exposedBy().toString(),
				service.toString(), null);
	}

}
