package Server;

import java.util.Hashtable;
import java.util.Iterator;
import  java.util.Set;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;




import org.apache.jena.rdf.model.Literal;

import JenaFusekiServer.FusekiQueries;
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
//	private static final OntModel FOAFOntologyModel = OntologyMain.getFOAFOntModel();
//	private static final OntModel IOTLiteInstancesOntologyModel = OntologyMain.getIOTLiteInstancesOntModel();
//	private static final OntModel IOTLiteOntologyModel = OntologyMain.getIOTLiteOntModel();
//	private static final OntModel SSNOntologyModel = OntologyMain.getSSNOntModel();
    
   
	
	 public static Individual insertSystem (String SystemName , String[] subSystems){
		 OntModel model = ModelFactory.createOntologyModel();
		 Individual newSystem = model.createIndividual(SSN_URI+SystemName,IOTLiteOntologyClasses.system());
		 FusekiQueries.insertOntmodel(model);
		 return newSystem;
	 }
	 
	 public static Individual insertSubSystem(Individual system , String subSystemName){
		 OntModel model = ModelFactory.createOntologyModel();
			Individual subSystem =  model.createIndividual(SSN_URI+subSystemName,IOTLiteOntologyClasses.system());
			system.addProperty(IOTLiteOntologyProperties.hasSubSystem(), subSystem);
			 FusekiQueries.insertOntmodel(model);
			return subSystem;
	 }
	 
	 public static Individual insertDevice(Individual system , String DeviceName ,Individual miniServer){
		 OntModel model = ModelFactory.createOntologyModel();
		 Individual newDevice = model.createIndividual(SSN_URI+DeviceName,IOTLiteOntologyClasses.device());
		 
		 system.addProperty(IOTLiteOntologyProperties.hasSubSystem(), newDevice);
		 newDevice.addProperty(IOTLiteInstancesOntologyProperties.isConnectedTo(),miniServer);
		 FusekiQueries.insertOntmodel(model);
		 return newDevice;
	 }
	 
	 public static Individual insertSensingDevice(Individual device , String sensingDeviceName){
		 OntModel model = ModelFactory.createOntologyModel();
		 Individual newSensingDevice = model.createIndividual(SSN_URI+sensingDeviceName,IOTLiteOntologyClasses.sensingDevice());
		 device.addProperty(IOTLiteOntologyProperties.hasSubSystem(), newSensingDevice);
		 FusekiQueries.insertOntmodel(model);
		 return newSensingDevice;
	 }
	 
	 public static Individual insertCommunicatingDevice(Individual Device , String communicatingDeviceName,String type,String bandwidth,String networkTopology ,String frequency){
		 OntModel model = ModelFactory.createOntologyModel();
		 Individual newCommunicatingDevice = model.createIndividual(iotlins_URI+communicatingDeviceName,IOTInstancesOntologyClasses.communicatingDevice());
		 
		 Device.addProperty(IOTLiteOntologyProperties.hasSubSystem(), newCommunicatingDevice);
		 newCommunicatingDevice.addProperty(IOTLiteInstancesOntologyProperties.hasBandwidth(),bandwidth);
		 newCommunicatingDevice.addProperty(IOTLiteInstancesOntologyProperties.hasFrequency(), frequency);
		 newCommunicatingDevice.addProperty(IOTLiteInstancesOntologyProperties.hasNetworkTopology(), networkTopology);
		 newCommunicatingDevice.addProperty(IOTLiteInstancesOntologyProperties.hasType(),type);
		 
		 FusekiQueries.insertOntmodel(model);
		 return newCommunicatingDevice;
	 }
	 
	 public static Individual insertMiniServer(String miniServerName , Individual system,String LocationName , String longtitude,String latitude){
		 OntModel model = ModelFactory.createOntologyModel();
		 
		 Individual miniServer = model.createIndividual(iotlins_URI+miniServerName,IOTInstancesOntologyClasses.miniServer());
		 Individual locationPoint = model.createIndividual(GEO_URI+LocationName,IOTLiteOntologyClasses.point());
		 
		 system.addProperty(IOTLiteOntologyProperties.hasSubSystem(),miniServer);
		 miniServer.addProperty(IOTLiteOntologyProperties.hasLocation(),locationPoint);
		 locationPoint.addProperty(IOTLiteOntologyProperties.longtitude(),longtitude);
		 locationPoint.addProperty(IOTLiteOntologyProperties.latitude(), latitude);
		 
		 FusekiQueries.insertOntmodel(model);
		 
		 return miniServer;
	 }
	 
	 public static void insertSensor(Individual sensingDevice , String sensorName , String strUnit , String strQuantityKind,Individual communicatingDevice ,Hashtable<String,Object> metadataList){
		 OntModel model = ModelFactory.createOntologyModel();
		 Individual newSensor = model.createIndividual(SSN_URI+sensorName,IOTLiteOntologyClasses.sensor());
		 Individual metadata  = model.createIndividual(iotlins_URI+sensorName+"Metadata",IOTInstancesOntologyClasses.metaData());
		 
		 sensingDevice.addProperty(IOTLiteOntologyProperties.hasSensingDevice(), newSensor);
		 newSensor.addProperty(IOTLiteOntologyProperties.hasQuantityKind(),QU_URI+strQuantityKind);
		 newSensor.addProperty(IOTLiteOntologyProperties.hasUnit(), QU_URI+strUnit);
		 newSensor.addProperty(IOTLiteInstancesOntologyProperties.hasCommunicatingDevice(), communicatingDevice);
		 newSensor.addProperty(IOTLiteInstancesOntologyProperties.hasMetadata(), metadata);
		 
		 Set<String> metadataSet = metadataList.keySet();
		 Iterator<String> metadataItr = metadataSet.iterator();
		 while(metadataItr.hasNext()){
			 String key = metadataItr.next();
			 Object value = metadataList.get(key);
			 metadata.addProperty(IOTLiteInstancesOntologyProperties.createNewProperty("has"+key), value.toString());
		 }
		 FusekiQueries.insertOntmodel(model);
	 }
	 
	
	 
}
