package Server;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Literal;

import com.github.andrewoma.dexx.collection.ArrayList;

import JenaFusekiServer.FusekiGraphs;
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
	private static final OntModel model = OntologyMain.getIOTLiteOntModel();

	// private static final OntModel SSNOntologyModel =
	// OntologyMain.getSSNOntModel();

	public static Individual insertSystem(String SystemName) {

		Individual newSystem = model.createIndividual(SSN_URI + SystemName,
				IOTLiteOntologyClasses.system());

		FusekiGraphs.insertIntoSystemsGraph(model);
		return newSystem;
	}

	/*
	 * A sytem can has many subSystems (many-to-many relationship)
	 */

	public static void insertSubSystem(Individual system, Individual subSystem) {

		FusekiGraphs.insertIntoSystemHasSubSystemGraph(system.toString(),
				IOTLiteOntologyProperties.hasSubSystem().toString(),
				subSystem.toString(), null);

	}

	/*
	 * A system can has many Devices (One-to-many relationship) An attribute of
	 * an object can has many Devices (one-to-many relationship) A device has
	 * subsystem communicatingDevice and SensingDevice (one-to-one relationship)
	 * A service can has many devices (many-to-many relationship) A MiniServer
	 * can has many devices connecting to it (one-to-many relationship)
	 */

	public static Individual insertDevice(String DeviceName, Individual system,
			Individual miniServer, Individual service,
			Individual CommunicatingDevice, Individual sensingDevice,
			Individual attribute) {

		Individual newDevice = model.createIndividual(SSN_URI + DeviceName,
				IOTLiteOntologyClasses.device());
		newDevice.addProperty(
				IOTLiteInstancesOntologyProperties.isConnectedTo(), miniServer);
		newDevice.addProperty(IOTLiteOntologyProperties.exposedBy(), service);

		newDevice.addProperty(IOTLiteOntologyProperties.hasSubSystem(),
				CommunicatingDevice);

		newDevice.addProperty(IOTLiteOntologyProperties.hasSubSystem(),
				sensingDevice);

		attribute.addProperty(IOTLiteOntologyProperties.isAssociatedWith(),
				newDevice);

		system.addProperty(IOTLiteOntologyProperties.hasSubSystem(), newDevice);

		FusekiGraphs.insertIntoDevicesGraph(model);
		return newDevice;
	}

	public static Individual insertSensingDevice(String sensingDeviceName) {

		Individual newSensingDevice = model.createIndividual(SSN_URI
				+ sensingDeviceName, IOTLiteOntologyClasses.sensingDevice());

		FusekiGraphs.insertIntoSensingDevicesGraph(model);
		return newSensingDevice;
	}

	public static Individual insertCommunicatingDevice(
			String communicatingDeviceName, String type, String bandwidth,
			String networkTopology, String frequency, String transmitPower) {

		Individual newCommunicatingDevice = model.createIndividual(iotlins_URI
				+ communicatingDeviceName,
				IOTInstancesOntologyClasses.communicatingDevice());

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

		FusekiGraphs.insertIntoCommunicatingDevicesGraph(model);
		return newCommunicatingDevice;
	}

	/*
	 * A system can has many MiniServers (one-to-many relationship)
	 */

	public static Individual insertMiniServer(String miniServerName,
			String LocationName, String longtitude, String latitude,
			Individual system) {

		Individual miniServer = model.createIndividual(iotlins_URI
				+ miniServerName, IOTInstancesOntologyClasses.miniServer());
		Individual locationPoint = model.createIndividual(GEO_URI
				+ LocationName, IOTLiteOntologyClasses.point());

		miniServer.addProperty(IOTLiteOntologyProperties.hasLocation(),
				locationPoint);
		locationPoint.addProperty(IOTLiteOntologyProperties.longtitude(),
				longtitude);
		locationPoint.addProperty(IOTLiteOntologyProperties.latitude(),
				latitude);

		system.addProperty(IOTLiteOntologyProperties.hasSubSystem(), miniServer);

		FusekiGraphs.insertIntoMiniServersGraph(model);

		return miniServer;
	}

	/*
	 * A sensingDevice can have many sensors (one-to-many relationships) A
	 * sensor must have communicatingDevice (one-to-one relationship) A sensor
	 * may have metadata
	 */

	public static Individual insertSensor(Individual sensingDevice,
			String sensorName, String strUnit, String strQuantityKind,
			Individual communicatingDevice,
			Hashtable<String, Object> metadataList) {

		Individual newSensor = model.createIndividual(SSN_URI + sensorName,
				IOTLiteOntologyClasses.sensor());

		newSensor.addProperty(IOTLiteOntologyProperties.hasSensingDevice(),
				sensingDevice);
		newSensor.addProperty(IOTLiteOntologyProperties.hasQuantityKind(),
				QU_URI + strQuantityKind);
		newSensor.addProperty(IOTLiteOntologyProperties.hasUnit(), QU_URI
				+ strUnit);
		newSensor.addProperty(
				IOTLiteInstancesOntologyProperties.hasCommunicatingDevice(),
				communicatingDevice);

		if (metadataList != null) {

			Individual metadata = model.createIndividual(iotlins_URI
					+ sensorName + "'s_Metadata",
					IOTInstancesOntologyClasses.metaData());
			newSensor.addProperty(
					IOTLiteInstancesOntologyProperties.hasMetadata(), metadata);

			Set<String> metadataSet = metadataList.keySet();
			Iterator<String> metadataItr = metadataSet.iterator();
			while (metadataItr.hasNext()) {
				String key = metadataItr.next();
				Object value = metadataList.get(key);
				metadata.addProperty(IOTLiteInstancesOntologyProperties
						.createNewProperty("has" + key), value.toString());
			}
		}
		FusekiGraphs.insertIntoSensorsGraph(model);

		return newSensor;
	}

	public static Individual insertService(String serviceName, String endpoint,
			String interfaceDescription) {

		Individual newServiece = model.createIndividual(IOT_Lite_URI
				+ serviceName, IOTLiteOntologyClasses.service());

		newServiece.addProperty(IOTLiteOntologyProperties.endPoint(), endpoint);
		newServiece.addProperty(
				IOTLiteOntologyProperties.interfaceDescription(),
				interfaceDescription);
		FusekiGraphs.insertIntoServicesGraph(model);

		return newServiece;

	}

	/*
	 * add new place like a room for example
	 */
	public static Individual insertObject(String objectName,
			String locationName, String longtitude, String latitude) {

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

		FusekiGraphs.insertIntoObjectsGraph(model);
		return newObject;
	}

	/*
	 * add an attribute for an object An attribute of an IoT object that can be
	 * exposed by an IoT service (i.e. a room (IoT Object) has a temperature
	 * (Attribute), that can be exposed by a temperature sensor (IoT device) .
	 * 
	 * An Object(ex:room) can have many attributes (one-to-one relationship)
	 */

	public static Individual insertAttribute(String attributeName,
			String quanityKind, Individual object) {

		Individual newAttrubute = model.createIndividual(IOT_Lite_URI
				+ attributeName, IOTLiteOntologyClasses.attribute());

		newAttrubute.addProperty(IOTLiteOntologyProperties.hasQuantityKind(),
				QU_URI + quanityKind);
		object.addProperty(IOTLiteOntologyProperties.hasAttribute(),
				newAttrubute);

		FusekiGraphs.insertIntoObjectsGraph(model);

		return newAttrubute;
	}

	/*
	 * A sensor can have many outputs (one-to-many relationship)
	 */

	public static void insertSensorOutputData(String SensorOutputdata,
			Individual Sensor, String strValue, String DateTime) {

		Individual newSensorOutput = model.createIndividual(SSN_URI
				+ SensorOutputdata, SSNOntologyClasses.sensorOutput());
		Individual value = model.createIndividual(SSN_URI + strValue,
				SSNOntologyClasses.observationValue());

		newSensorOutput.addProperty(SSNOntologyProperties.isProducedBy(),
				Sensor);
		newSensorOutput.addProperty(SSNOntologyProperties.hasValue(), value);
		newSensorOutput.addProperty(
				SSNOntologyProperties.observationResultTime(), DateTime);

		FusekiGraphs.insertIntoSensorOutputsGraph(model);

	}

	/*
	 * coverage will be inserted into device graph because each device will have
	 * its sensingDevice that is composed of sensors and communicating device to
	 * coverage will be represented as device has coverage Coverage property
	 * (one-to-one relationship)
	 */

	public static void insertCoverage(String coverageAreaName,
			Individual device, String coverageType, ArrayList<Individual> Points)
			throws Exception {

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

		FusekiGraphs.insertIntoDevicesGraph(model);

	}

	public static Individual createPoint(String pointName, String longtitude,
			String latitude) {

		Individual newPoint = model.createIndividual(GEO_URI + pointName,
				IOTLiteOntologyClasses.point());

		newPoint.addProperty(IOTLiteOntologyProperties.longtitude(), longtitude);
		newPoint.addProperty(IOTLiteOntologyProperties.latitude(), latitude);

		return newPoint;
	}

	public static Individual insertApplication(String applicationName,
			String applicationDescription) {

		Individual newApplication = model.createIndividual(iotlins_URI
				+ applicationName, IOTInstancesOntologyClasses.application());

		newApplication.addProperty(
				IOTLiteInstancesOntologyProperties.hasApplicationName(),
				applicationName);
		newApplication.addProperty(
				IOTLiteInstancesOntologyProperties.hasApplicationDescription(),
				applicationDescription);

		FusekiGraphs.insertIntoApplicationsGraph(model);

		return newApplication;
	}

	public static Individual insertPerson(String personName, String firstName,
			String lastName, String gender, String Birthday, String email,
			String role) {

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

		FusekiGraphs.insertIntoPersonsGraph(model);

		return newPerson;
	}

	/*
	 * A person can use many applications and an application can be used by many
	 * persons (Many-to-Many relationship)
	 */

	public static void insertPersonApplicationRelation(Individual Person,
			Individual Application) {

		FusekiGraphs
				.insertIntoPersonUsesApplicationGraph(Person.toString(),
						IOTLiteInstancesOntologyProperties.usesApplication()
								.toString(), Application.toString(), null);

	}

	/*
	 * An application can use many systems and a system can be used by many
	 * Systems (Many-to-Many relationship)
	 */

	public static void insertApplicationSystemRelation(Individual application,
			Individual system) {
		FusekiGraphs.insertIntoApplicationUsesSystemGraph(application
				.toString(), IOTLiteInstancesOntologyProperties
				.usesApplication().toString(), system.toString(), null);
	}

	/*
	 * A device can be exposed by many IOT services and a service can expose
	 * many devices
	 */

	public static void insertDeviceServiceRelation(Individual device,
			Individual service) {
		FusekiGraphs.insertIntoDeviceExposedByServiceGraph(device.toString(),
				IOTLiteOntologyProperties.exposedBy().toString(),
				service.toString(), null);
	}

}
