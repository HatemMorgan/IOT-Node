package Server;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.apache.jena.datatypes.RDFDatatype;
import org.apache.jena.graph.Node;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.AnonId;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Literal;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.RDFVisitor;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.sparql.engine.index.SetIndexTable;

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

	private static Individual device ;

	private static final OntModel model = OntologyMain.getIOTLiteOntModel();

	/*
	 * Unique value is the systemName
	 */

	public static Individual insertSystem(String SystemName) {

		Individual newSystem = model.createIndividual(SSN_URI + SystemName,
				IOTLiteOntologyClasses.system());

		FusekiGraphs.insertIntoSystemsGraph(SSN_URI + SystemName,
				IOTLiteOntologyProperties.type().toString(),
				IOTLiteOntologyClasses.system().toString(), null);
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
	 * 
	 * Unique value is deviceMacAddress 
	 */

	public static Individual insertDevice(String DeviceName, Individual system,
			Individual miniServer, Individual service,
			Individual CommunicatingDevice, Individual sensingDevice,
			Individual attribute , String deviceMacAddress) {

		Individual newDevice = model.createIndividual(SSN_URI + DeviceName,
				IOTLiteOntologyClasses.device());
		
		setDevice(newDevice);
		
		FusekiGraphs.insertIntoDevicesGraph(CommunicatingDevice.toString(),
				IOTLiteOntologyProperties.type().toString(),
				IOTInstancesOntologyClasses.communicatingDevice().toString(),
				null);
 
		FusekiGraphs.insertIntoDevicesGraph(sensingDevice.toString(),
				IOTLiteOntologyProperties.type().toString(),
				IOTLiteOntologyClasses.sensingDevice().toString(), null);

		FusekiGraphs.insertIntoDevicesGraph(SSN_URI + DeviceName,
				IOTLiteOntologyProperties.type().toString(),
				IOTLiteOntologyClasses.device().toString(), null);
        
		FusekiGraphs.insertIntoDevicesGraph(newDevice.toString(),
				IOTLiteInstancesOntologyProperties.hasMacaddress().toString(),
				null, deviceMacAddress);
		
		FusekiGraphs.insertIntoDevicesGraph(newDevice.toString(),
				IOTLiteInstancesOntologyProperties.isConnectedTo().toString(),
				miniServer.toString(), null);
		FusekiGraphs.insertIntoDevicesGraph(newDevice.toString(),
				IOTLiteOntologyProperties.exposedBy().toString(),
				service.toString(), null);
		FusekiGraphs.insertIntoDevicesGraph(newDevice.toString(),
				IOTLiteOntologyProperties.hasSubSystem().toString(),
				CommunicatingDevice.toString(), null);
		FusekiGraphs.insertIntoDevicesGraph(newDevice.toString(),
				IOTLiteOntologyProperties.hasSubSystem().toString(),
				sensingDevice.toString(), null);
		FusekiGraphs.insertIntoDevicesGraph(attribute.toString(),
				IOTLiteOntologyProperties.isAssociatedWith().toString(),
				newDevice.toString(), null);
		FusekiGraphs.insertIntoDevicesGraph(system.toString(),
				IOTLiteOntologyProperties.hasSubSystem().toString(),
				newDevice.toString(), null);

		return model.createIndividual(iotlins_URI+deviceMacAddress,IOTInstancesOntologyClasses.macAddress());
	}

	
	/*
	 * The unique value is the UUID generated for the new sensingDevice 
	 */
	
	public static Individual insertSensingDevice(String sensingDeviceName , Individual communicatingDevice) {
		String id = UUID.randomUUID().toString();
		Individual newSensingDevice = model.createIndividual(SSN_URI
				+ sensingDeviceName, IOTLiteOntologyClasses.sensingDevice());

		FusekiGraphs.insertIntoSensingDevicesGraph(SSN_URI + sensingDeviceName,
				IOTLiteOntologyProperties.type().toString(),
				IOTLiteOntologyClasses.sensingDevice().toString(), null);
		
		FusekiGraphs.insertIntoSensingDevicesGraph(newSensingDevice.toString(),
				IOTLiteInstancesOntologyProperties.hasUUID().toString(),
				null, id);
		
		FusekiGraphs.insertIntoSensingDevicesGraph(newSensingDevice.toString(),
				IOTLiteInstancesOntologyProperties.hasName().toString(),
				null, sensingDeviceName);

		FusekiGraphs.insertIntoSensingDevicesGraph(newSensingDevice.toString(),
				IOTLiteInstancesOntologyProperties.hasCommunicatingDevice()
						.toString(), communicatingDevice.toString(), null);
		
		return model.createIndividual(iotlins_URI+id,IOTInstancesOntologyClasses.SensingDeviceUUID());
	}
	
	/*
	 * The Unique value is the macAddress
	 */

	public static Individual insertCommunicatingDevice(
			String communicatingDeviceName, String type, String bandwidth,
			String networkTopology, String frequency, String transmitPower,
			String sensitvity, String numberOfChannels, String macAddress,
			String dutyCycle) {

		Individual newCommunicatingDevice = model.createIndividual(iotlins_URI
				+ communicatingDeviceName,
				IOTInstancesOntologyClasses.communicatingDevice());

		FusekiGraphs.insertIntoCommunicatingDevicesGraph(iotlins_URI
				+ communicatingDeviceName, IOTLiteOntologyProperties.type()
				.toString(), IOTInstancesOntologyClasses.communicatingDevice()
				.toString(), null);
		FusekiGraphs.insertIntoCommunicatingDevicesGraph(
				newCommunicatingDevice.toString(),
				IOTLiteInstancesOntologyProperties.hasBandwidth().toString(),
				null, bandwidth);
		FusekiGraphs.insertIntoCommunicatingDevicesGraph(
				newCommunicatingDevice.toString(),
				IOTLiteInstancesOntologyProperties.hasFrequency().toString(),
				null, frequency);
		FusekiGraphs.insertIntoCommunicatingDevicesGraph(newCommunicatingDevice
				.toString(), IOTLiteInstancesOntologyProperties
				.hasNetworkTopology().toString(), null, networkTopology);
		FusekiGraphs.insertIntoCommunicatingDevicesGraph(newCommunicatingDevice
				.toString(), IOTLiteInstancesOntologyProperties
				.hasTransmitPower().toString(), null, transmitPower);

		FusekiGraphs.insertIntoCommunicatingDevicesGraph(newCommunicatingDevice
				.toString(), IOTLiteInstancesOntologyProperties
				.hasMacaddress().toString(), null, macAddress);
		
		FusekiGraphs.insertIntoCommunicatingDevicesGraph(newCommunicatingDevice
				.toString(), IOTLiteInstancesOntologyProperties
				.hasNumberOfChannels().toString(), null, numberOfChannels);
		
		FusekiGraphs.insertIntoCommunicatingDevicesGraph(newCommunicatingDevice
				.toString(), IOTLiteInstancesOntologyProperties
				.hasSensitivity().toString(), null, sensitvity);
		
		FusekiGraphs.insertIntoCommunicatingDevicesGraph(newCommunicatingDevice
				.toString(), IOTLiteInstancesOntologyProperties
				.hasDutyCycle().toString(), null, dutyCycle);
		
		FusekiGraphs.insertIntoCommunicatingDevicesGraph(
				newCommunicatingDevice.toString(),
				IOTLiteInstancesOntologyProperties.hasType().toString(), null,
				type);

		return model.createIndividual(iotlins_URI+macAddress,IOTInstancesOntologyClasses.macAddress());
	}

	/*
	 * A system can has many MiniServers (one-to-many relationship)
	 * 
	 * The unique value is the miniServerName
	 */

	public static Individual insertMiniServer(String miniServerName,
			String LocationName, String longtitude, String latitude,
			Individual system) {

		Individual miniServer = model.createIndividual(iotlins_URI
				+ miniServerName, IOTInstancesOntologyClasses.miniServer());

		FusekiGraphs.insertIntoMiniServersGraph(iotlins_URI + miniServerName,
				IOTLiteOntologyProperties.type().toString(),
				IOTInstancesOntologyClasses.miniServer().toString(), null);

		Individual locationPoint = model.createIndividual(GEO_URI
				+ LocationName, IOTLiteOntologyClasses.point());

		FusekiGraphs.insertIntoMiniServersGraph(GEO_URI + LocationName,
				IOTLiteOntologyProperties.type().toString(),
				IOTLiteOntologyClasses.point().toString(), null);

		FusekiGraphs.insertIntoMiniServersGraph(miniServer.toString(),
				IOTLiteOntologyProperties.hasLocation().toString(),
				locationPoint.toString(), null);
		FusekiGraphs.insertIntoMiniServersGraph(locationPoint.toString(),
				IOTLiteOntologyProperties.longtitude().toString(), null,
				longtitude);
		FusekiGraphs
				.insertIntoMiniServersGraph(locationPoint.toString(),
						IOTLiteOntologyProperties.latitude().toString(), null,
						latitude);
		FusekiGraphs.insertIntoMiniServersGraph(system.toString(),
				IOTLiteOntologyProperties.hasSubSystem().toString(),
				miniServer.toString(), null);

		return miniServer;
	}

	/*
	 * A sensingDevice can have many sensors (one-to-many relationships) A
	 * sensor must have communicatingDevice (one-to-one relationship) A sensor
	 * may have metadata
	 * 
	 * Unique value is the sensor name
	 */

	public static Individual insertSensor(Individual sensingDevice,
			String sensorName, String strUnit, String strQuantityKind,
			Hashtable<String, Object> metadataList) {

		Individual newSensor = model.createIndividual(SSN_URI + sensorName,
				IOTLiteOntologyClasses.sensor());

		FusekiGraphs.insertIntoSensorsGraph(SSN_URI + sensorName,
				IOTLiteOntologyProperties.type().toString(),
				IOTLiteOntologyClasses.sensor().toString(), null);

		FusekiGraphs.insertIntoSensorsGraph(newSensor.toString(),
				IOTLiteOntologyProperties.hasSensingDevice().toString(),
				sensingDevice.toString(), null);
		FusekiGraphs.insertIntoSensorsGraph(newSensor.toString(),
				IOTLiteOntologyProperties.hasQuantityKind().toString(), QU_URI
						+ strQuantityKind, null);
		FusekiGraphs.insertIntoSensorsGraph(newSensor.toString(),
				IOTLiteOntologyProperties.hasUnit().toString(), QU_URI
						+ strUnit, null);


		if (metadataList != null) {

			Individual metadata = model.createIndividual(iotlins_URI
					+ sensorName + "_Metadata",
					IOTInstancesOntologyClasses.metaData());

			FusekiGraphs.insertIntoSensorsGraph(iotlins_URI + sensorName
					+ "_Metadata", IOTLiteOntologyProperties.type()
					.toString(), IOTInstancesOntologyClasses.metaData()
					.toString(), null);

			FusekiGraphs
					.insertIntoSensorsGraph(newSensor.toString(),
							IOTLiteInstancesOntologyProperties.hasMetadata()
									.toString(), metadata.toString(), null);

			Set<String> metadataSet = metadataList.keySet();
			Iterator<String> metadataItr = metadataSet.iterator();
			while (metadataItr.hasNext()) {
				String key = metadataItr.next();
				Object value = metadataList.get(key);

				FusekiGraphs
						.insertIntoSensorsGraph(metadata.toString(),
								IOTLiteInstancesOntologyProperties
										.createNewProperty("has" + key)
										.toString(), null, value.toString());

			}
		}

		return newSensor;
	}

	/*
	 *  The unique value is the serviceName
	 */
	
	public static Individual insertService(String serviceName, String endpoint,
			String interfaceDescription) {

		Individual newServiece = model.createIndividual(IOT_Lite_URI
				+ serviceName, IOTLiteOntologyClasses.service());

		FusekiGraphs.insertIntoServicesGraph(IOT_Lite_URI + serviceName,
				IOTLiteOntologyProperties.type().toString(),
				IOTLiteOntologyClasses.service().toString(), null);

		newServiece.addProperty(IOTLiteOntologyProperties.endPoint(), endpoint);
		FusekiGraphs.insertIntoServicesGraph(newServiece.toString(),
				IOTLiteOntologyProperties.interfaceDescription().toString(),
				null, interfaceDescription);

		FusekiGraphs
				.insertIntoServicesGraph(newServiece.toString(),
						IOTLiteOntologyProperties.endPoint().toString(), null,
						endpoint);

		return newServiece;

	}

	/*
	 * add new place like a room for example
	 * 
	 * The unique value is objectName
	 */
	public static Individual insertObject(String objectName,
			String locationName, String longtitude, String latitude) {

		Individual newObject = model.createIndividual(
				IOT_Lite_URI + objectName, IOTLiteOntologyClasses.object());

		FusekiGraphs.insertIntoObjectsGraph(IOT_Lite_URI + objectName,
				IOTLiteOntologyProperties.type().toString(),
				IOTLiteOntologyClasses.object().toString(), null);

		Individual locationPoint = model.createIndividual(GEO_URI
				+ locationName, IOTLiteOntologyClasses.point());

		FusekiGraphs.insertIntoObjectsGraph(GEO_URI + locationName,
				IOTLiteOntologyProperties.type().toString(),
				IOTLiteOntologyClasses.point().toString(), null);

		FusekiGraphs.insertIntoObjectsGraph(locationPoint.toString(),
				IOTLiteOntologyProperties.longtitude().toString(), null,
				longtitude);
		FusekiGraphs
				.insertIntoObjectsGraph(locationPoint.toString(),
						IOTLiteOntologyProperties.latitude().toString(), null,
						latitude);
		FusekiGraphs.insertIntoObjectsGraph(newObject.toString(),
				IOTLiteOntologyProperties.hasLocation().toString(),
				locationPoint.toString(), null);

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

		FusekiGraphs.insertIntoObjectsGraph(IOT_Lite_URI + attributeName,
				IOTLiteOntologyProperties.type().toString(),
				IOTLiteOntologyClasses.attribute().toString(), null);

		FusekiGraphs.insertIntoObjectsGraph(newAttrubute.toString(),
				IOTLiteOntologyProperties.hasQuantityKind().toString(), QU_URI
						+ quanityKind, null);

		FusekiGraphs.insertIntoObjectsGraph(object.toString(),
				IOTLiteOntologyProperties.hasAttribute().toString(),
				newAttrubute.toString(), null);

		return newAttrubute;
	}

	/*
	 * A sensor can have many outputs (one-to-many relationship)
	 * 
	 * The Unique value is the SensorName and sensingDevice UUID
	 */

	public static void insertSensorOutputData(String SensorOutputdata,
			Individual Sensor, String strValue, String DateTime , Individual sensingDeviceUUID ) {

		Individual newSensorOutput = model.createIndividual(SSN_URI
				+ SensorOutputdata, SSNOntologyClasses.sensorOutput());

		FusekiGraphs.insertIntoSensorOutputsGraph(SSN_URI + SensorOutputdata,
				IOTLiteOntologyProperties.type().toString(), SSNOntologyClasses
						.sensorOutput().toString(), null);

		Individual value = model.createIndividual(SSN_URI + strValue,
				SSNOntologyClasses.observationValue());

		FusekiGraphs.insertIntoSensorOutputsGraph(SSN_URI + strValue,
				IOTLiteOntologyProperties.type().toString(), SSNOntologyClasses
						.observationValue().toString(), null);

		FusekiGraphs.insertIntoSensorOutputsGraph(newSensorOutput.toString(),
				SSNOntologyProperties.isProducedBy().toString(),
				Sensor.toString(), null);

		FusekiGraphs.insertIntoSensorOutputsGraph(newSensorOutput.toString(),
				SSNOntologyProperties.hasValue().toString(), value.toString(),
				null);

		FusekiGraphs.insertIntoSensorOutputsGraph(newSensorOutput.toString(),
				IOTLiteInstancesOntologyProperties.hasSensingDeviceUUID().toString(), sensingDeviceUUID.toString(),
				null);
		
		FusekiGraphs.insertIntoSensorOutputsGraph(newSensorOutput.toString(),
				SSNOntologyProperties.observationResultTime().toString(), null,
				DateTime);

	}

	/*
	 * coverage will be inserted into device graph because each device will have
	 * its sensingDevice that is composed of sensors and communicating device to
	 * coverage will be represented as device has coverage Coverage property
	 * (one-to-one relationship)
	 * 
	 * he coverage with the points that describe the coverage (e.g. A square
	 * coverage is defined with two points in a diagonal of the square).
	 */

	public static Individual insertCoverage(String coverageAreaName,
			Individual device, String coverageType) throws Exception {

		Individual newCoverage;
		switch (coverageType) {
		case "Circle":
			newCoverage = model.createIndividual(IOT_Lite_URI + coverageType,
					IOTLiteOntologyClasses.circle());

			FusekiGraphs.insertIntoDevicesGraph(IOT_Lite_URI + coverageType,
					IOTLiteOntologyProperties.type().toString(),
					IOTLiteOntologyClasses.circle().toString(), null);
			break;
		case "Rectangle":
			newCoverage = model.createIndividual(IOT_Lite_URI + coverageType,
					IOTLiteOntologyClasses.rectangle());

			FusekiGraphs.insertIntoDevicesGraph(IOT_Lite_URI + coverageType,
					IOTLiteOntologyProperties.type().toString(),
					IOTLiteOntologyClasses.rectangle().toString(), null);
			break;
		case "Polygon":
			model.createIndividual(IOT_Lite_URI + coverageType,
					IOTLiteOntologyClasses.polygon());

			FusekiGraphs.insertIntoDevicesGraph(IOT_Lite_URI + coverageType,
					IOTLiteOntologyProperties.type().toString(),
					IOTLiteOntologyClasses.polygon().toString(), null);
		default:
			throw new Exception("no coverage type equal to " + coverageType);

		}

		FusekiGraphs.insertIntoDevicesGraph(device.toString(),
				IOTLiteOntologyProperties.hasCoverage().toString(),
				newCoverage.toString(), null);
		return newCoverage;
	}

	public static void createPoint(String pointName, String longtitude,
			String latitude, Individual coverage) {

		Individual newPoint = model.createIndividual(GEO_URI + pointName,
				IOTLiteOntologyClasses.point());

		FusekiGraphs.insertIntoDevicesGraph(GEO_URI + pointName,
				IOTLiteOntologyProperties.type().toString(),
				IOTLiteOntologyClasses.point().toString(), null);

		FusekiGraphs.insertIntoDevicesGraph(newPoint.toString(),
				IOTLiteOntologyProperties.longtitude().toString(), null,
				longtitude);

		FusekiGraphs
				.insertIntoDevicesGraph(newPoint.toString(),
						IOTLiteOntologyProperties.latitude().toString(), null,
						latitude);

		FusekiGraphs.insertIntoDevicesGraph(coverage.toString(),
				IOTLiteOntologyProperties.hasPoint().toString(),
				newPoint.toString(), null);

	}
	/*
	 * The unique value is the application name
	 */

	public static Individual insertApplication(String applicationName,
			String applicationDescription) {

		Individual newApplication = model.createIndividual(iotlins_URI
				+ applicationName, IOTInstancesOntologyClasses.application());

		FusekiGraphs.insertIntoApplicationsGraph(iotlins_URI + applicationName,
				IOTLiteOntologyProperties.type().toString(),
				IOTInstancesOntologyClasses.application().toString(), null);

		FusekiGraphs.insertIntoApplicationsGraph(newApplication.toString(),
				IOTLiteInstancesOntologyProperties.hasApplicationName()
						.toString(), null, applicationName);

		FusekiGraphs.insertIntoApplicationsGraph(newApplication.toString(),
				IOTLiteInstancesOntologyProperties.hasApplicationDescription()
						.toString(), null, applicationDescription);

		return newApplication;
	}
	
	/*
	 * The unique value is the userName
	 */

	public static Individual insertPerson(String userName, String firstName,
			String lastName, String gender, String Birthday, String email,
			String role) throws Exception {
		if (role.equals("Developer") || role.equals("NormalUser")
				|| role.equals("Admin")) {
			
			Individual newPerson = model.createIndividual(
					FOAF_URI + userName, FOAFOntologyClasses.PersonClass());

			FusekiGraphs.insertIntoPersonsGraph(FOAF_URI + userName,
					IOTLiteOntologyProperties.type().toString(),
					FOAFOntologyClasses.PersonClass().toString(), null);

			FusekiGraphs.insertIntoPersonsGraph(newPerson.toString(),
					FOAFOntologyProperties.firstName().toString(), null,
					firstName);

			FusekiGraphs.insertIntoPersonsGraph(newPerson.toString(),
					FOAFOntologyProperties.lastName().toString(), null,
					lastName);

			FusekiGraphs.insertIntoPersonsGraph(newPerson.toString(),
					FOAFOntologyProperties.gender().toString(), null, gender);

			FusekiGraphs.insertIntoPersonsGraph(newPerson.toString(),
					FOAFOntologyProperties.birthday().toString(), null,
					Birthday);

			FusekiGraphs.insertIntoPersonsGraph(newPerson.toString(),
					IOTLiteInstancesOntologyProperties.email().toString(),
					null, email);

			FusekiGraphs.insertIntoPersonsGraph(newPerson.toString(),
					IOTLiteInstancesOntologyProperties.hasRole().toString(),
					null, role);

			return newPerson;
		} else {
			throw new Exception("no role type equal to " + role);
		}
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
				.usesSystem().toString(), system.toString(), null);
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

	public static void setDevice(Individual newDevice){
		device = newDevice;
	}
	
	public static Individual getDevice (){
		return device;
	}
	
}
