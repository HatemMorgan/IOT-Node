package JenaFusekiServer;

import java.util.Date;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.xerces.dom.DeepNodeListImpl;

public class DeleteQueries {
 public static void deletePerson(String userName){
	 String strQuery = 
			 	"PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
			   +"PREFIX g: <http://learningsparql.com/ns/graphs#>"
			   +" PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +" DELETE { "
			   +"   GRAPH g:Persons{ "
			   +"	     	foaf:"+userName+" a foaf:Person;"
			   +"       					foaf:firstName 		?firstName;"
			   +"	       					foaf:lastName 	    ?lastName;"
		 	   +"	       					foaf:birthday       ?birthday;"
			   +"	       					foaf:gender         ?gender;"
			   +"       					iot-liteIns:email	?email;"
			   +"	       					iot-liteIns:hasRole ?role."
			   +"	   }"
			   +"	 }"
			   +"	   WHERE{"
			   +"    GRAPH g:Persons{"
			   +"	 	foaf:"+userName+" a foaf:Person;"
			   +"	       					foaf:firstName 		?firstName;"
			   +"	       					foaf:lastName 	    ?lastName;"
			   +"	       					foaf:birthday       ?birthday;"
			   +"	       					foaf:gender         ?gender;"
			   +"	       					iot-liteIns:email	?email;"
			   +"	       					iot-liteIns:hasRole ?role."
			   +"	 	} "
			   +"	 }";
	 
	 FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	 
	 deleteApplicationsUsedByDeletedPersonRelation(userName);
 }
 
 
 
 public static void deleteApplication(String applicationName){
	 String strQuery =
			 "PREFIX g: <http://learningsparql.com/ns/graphs#>"
			 +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			 +"DELETE{ "
			 +"	 GRAPH g:Applications { "
			 +"	     iot-liteIns:"+applicationName+" a  iot-liteIns:Application;"
			 +"	     						  iot-liteIns:hasApplicationName		?name;"
		     +"		       					  iot-liteIns:hasApplicationDescription ?description"				    
		     +"	    }"
		     +"	 }"
			 +"	 WHERE{"
			 +"	 	GRAPH g:Applications {"
			 +"	     iot-liteIns:"+applicationName+" a  iot-liteIns:Application;"
			 +"	     						  iot-liteIns:hasApplicationName		?name;"
			 +"	       						  iot-liteIns:hasApplicationDescription ?description"	    
			 +"	 }"
			 +"	 }";

	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	 deletePersonUsesDeletedApplicationRelation(applicationName);
 }
 
public static void deleteApplicationsUsedByDeletedPersonRelation(String userName){
	String strQuery = 
		    "PREFIX g: <http://learningsparql.com/ns/graphs#>"
		   +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
		   +"PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
		   +"DELETE { "
		   +"   GRAPH g:PersonUsesApplication { "
		   +"	      foaf:"+userName+" iot-liteIns:usesApplication  ?application ."
		   +"	     }"
		   +"	 }"
		   +"WHERE { "
		   +"   GRAPH g:PersonUsesApplication { "
		   +"	      foaf:"+userName+" iot-liteIns:usesApplication  ?application ."
		   +"	     }"
		   +"	 }";
 
FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
 }
 
 public static void deletePersonUsesDeletedApplicationRelation(String applicationName){
	 String strQuery = 
			    "PREFIX g: <http://learningsparql.com/ns/graphs#>"
			   +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +"PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
			   +"DELETE { "
			   +"   GRAPH g:PersonUsesApplication { "
			   +"	      ?person iot-liteIns:usesApplication iot-liteIns:"+applicationName+" ."
			   +"	     }"
			   +"	 }"
	 		   +"WHERE { "
	 		   +"   GRAPH g:PersonUsesApplication { "
			   +"	      ?person iot-liteIns:usesApplication iot-liteIns:"+applicationName+" ."
			   +"	     }"
	 		   +"}";
	 
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
 }

 public static void deletePersonUsesApplicationRelation(String userName , String applicationName){
	 String strQuery = 
			    "PREFIX g: <http://learningsparql.com/ns/graphs#>"
			   +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +"PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
			   +"DELETE DATA{ "
			   +"   GRAPH g:PersonUsesApplication { "
			   +"	      foaf:"+userName+" iot-liteIns:usesApplication iot-liteIns:"+applicationName+" ."
			   +"	     }"
			   +"	 }";
	 
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
 }
 
 public static void deleteSystem(String systemName){
	String strQuery = 
				"PREFIX g: <http://learningsparql.com/ns/graphs#>"
				+"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
				+"DELETE DATA{ "
				+"GRAPH g:Systems { "
				+"   ssn:"+systemName+" a ssn:System ."
				+"  }"
				+"}";
	
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	
	deleteDevicesSubSystemOfDeletedSystem(systemName);
	deleteSystemHasDeletedSubSystemRelation(systemName);
	deletesubSystemsOfDeletedSystemRelation(systemName);
	deleteMiniServersSubSystemOfDeletedSystem(systemName);
	deleteApplicationsUsesDeletedSystemRelation(systemName);
	
	// Hard delete queries
 }
 
 /*
  *  Many To Many relationship
  */
 
 // ---------------------------------------------------------------------------------------------------------
 
 public static void deleteApplicationsUsesDeletedSystemRelation(String systemName){
	 String strQuery = 
			"PREFIX g: <http://learningsparql.com/ns/graphs#>"
 		   +"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
 		   +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +"DELETE { "
			   +"   GRAPH g:ApplicationUsesSystem { "
			   +"	      ?application iot-liteIns:usesSystem ssn:"+systemName+" ."
			   +"	     }"
			   +"	 }"
			   +"WHERE { "
			   +"   GRAPH g:ApplicationUsesSystem { "
			   +"	      ?application iot-liteIns:usesSystem ssn:"+systemName+" ."
			   +"	     }"
			   +"	 }";
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
 }
 
 public static void deleteSystemsUsedByDeletedApplicationRelation (String applicationName){
	 String strQuery = 
			    "PREFIX g: <http://learningsparql.com/ns/graphs#>"
 		   +"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
 		   +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +"DELETE { "
			   +"   GRAPH g:ApplicationUsesSystem { "
			   +"	      iot-liteIns:"+applicationName+" iot-liteIns:usesSystem ?system ."
			   +"	     }"
			   +"	 }"
			   +"WHERE { "
			   +"   GRAPH g:ApplicationUsesSystem { "
			   +"	      iot-liteIns:"+applicationName+" iot-liteIns:usesSystem ?system ."
			   +"	     }"
			   +"	 }";
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery); 
 }
 
 public static void deleteApplicationUsesSystemRelation(String applicationName , String systemName){
	 String strQuery = 
			    "PREFIX g: <http://learningsparql.com/ns/graphs#>"
    		   +"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
    		   +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +"DELETE DATA{ "
			   +"   GRAPH g:ApplicationUsesSystem { "
			   +"	      iot-liteIns:"+applicationName+" iot-liteIns:usesSystem ssn:"+systemName+" ."
			   +"	     }"
			   +"	 }";
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
 }
 
 //------------------------------------------------------------------------------------------------------------
 
 
 
 /*
  * Many to many relationship
  */
 
 
 //---------------------------------------------------------------------------------------------------------
 
 public static void deletesubSystemsOfDeletedSystemRelation(String deletedSystemName){
	 String strQuery = 
			    "PREFIX g: <http://learningsparql.com/ns/graphs#>"
	 		   +"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
			   +"DELETE { "
			   +"   GRAPH g:SystemHasSubSystem { "
			   +"	      ssn:"+deletedSystemName+" ssn:hasSubSystem ?subSystem ."
			   +"	     }"
			   +"	 }"
			   +"WHERE { "
			   +"   GRAPH g:SystemHasSubSystem { "
			   +"	      ssn:"+deletedSystemName+" ssn:hasSubSystem ?subSystem ."
			   +"	     }"
			   +"	 }";
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
 }

 
 
 public static void deleteSystemHasDeletedSubSystemRelation(String deletedSubSystemName){
	 String strQuery = 
			    "PREFIX g: <http://learningsparql.com/ns/graphs#>"
	 		   +"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
			   +"DELETE { "
			   +"   GRAPH g:SystemHasSubSystem { "
			   +"	       ?system ssn:hasSubSystem ssn:"+deletedSubSystemName+" ."
			   +"	     }"
			   +"	 }"
			   +"WHERE { "
			   +"   GRAPH g:SystemHasSubSystem { "
			   +"	       ?system ssn:hasSubSystem ssn:"+deletedSubSystemName+" ."
			   +"	     }"
			   +"	 }";
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
 }
 
 

 
 public static void deleteSystemHasSubSystemRelation(String systemName , String subSystemName){
	 String strQuery = 
			    "PREFIX g: <http://learningsparql.com/ns/graphs#>"
	 		   +"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
			   +"DELETE DATA{ "
			   +"   GRAPH g:SystemHasSubSystem { "
			   +"	      ssn:"+systemName+" ssn:hasSubSystem ssn:"+subSystemName+" ."
			   +"	     }"
			   +"	 }";
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);

 }
 
//------------------------------------------------------------------------------------------------------------ 
 
public static void deleteMiniServers(String miniServerName){
	 String strQuery = 
			 	 "PREFIX g: <http://learningsparql.com/ns/graphs#>"
				 +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
				 +"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
				 +"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
				 +"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
				 +"DELETE { "
				 +"	GRAPH g:MiniServers { "
				 +" 		iot-liteIns:"+miniServerName+" a  iot-liteIns:MiniServer;"
				 +"      									geo:hasLocation ?location ."
				 +"        ?location a   geo:Point;"
				 +"      			  geo:lat ?latitude ;"
				 +"      			  geo:long ?longtitude."    	
				 +"      	?system ssn:hasSubSystem iot-liteIns:"+miniServerName+" ."								
				 +"			}"
				 +"}"
				 +"WHERE{"
				 +"GRAPH g:MiniServers {"
				 +" 		iot-liteIns:"+miniServerName+" a  iot-liteIns:MiniServer;"
				 +"      									geo:hasLocation ?location ."
				 +"        ?location a   geo:Point;"
				 +"      			  geo:lat ?latitude ;"
				 +"      			  geo:long ?longtitude."    	
				 +"      	?system ssn:hasSubSystem iot-liteIns:"+miniServerName+" .	"							
				 +"		}"
				 +"}";
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	 deleteDevicesConnectedToDeletedMiniServer(miniServerName);
				 
 }




public static void deleteDevice(String deviceMacAddress){
String strQuery = 	
			 "PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
			+"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			+"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
			+"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
			+"PREFIX g: <http://learningsparql.com/ns/graphs#>"
			+"DELETE  { "
			+" GRAPH g:Devices "
			+"  { "
			+"      ssn:"+deviceMacAddress+"  a ssn:Device ;"
 			+" 					 iot-liteIns:hasName ?deviceName ;"
			+"    			     ssn:hasSubSystem ?communicatingDevice;"
			+"  				 ssn:hasSubSystem ?sensingDevice;"
			+"    			     iot-liteIns:isConnectedTo ?miniserver;"
			+"  				 iot-lite:exposedBy ?service."
			+"  ?attribute  	 iot-lite:isAssociatedWith       ssn:"+deviceMacAddress+"."
			+"  ?system  		 ssn:hasSubSystem      ssn:"+deviceMacAddress+"."
			+"  ?communicatingDevice a iot-liteIns:CommunicatingDevice."
			+"  ?sensingDevice a ssn:SensingDevice ."
			+"  ssn:"+deviceMacAddress+" iot-lite:hasCoverage ?coverage."
			+"  ?coverage iot-lite:hasPoint ?point."
			+"  ?point a geo:Point ;"
			+"     	 geo:lat ?latitude;"
			+"    	 geo:long ?longtitude ."
			+"  		    } "
			+"}"
			+"WHERE{"
			+"GRAPH g:Devices "
			+"  {"
			+"      ssn:"+deviceMacAddress+"  a ssn:Device;"
 			+" 					 iot-liteIns:hasName ?deviceName ;"
			+"    			     ssn:hasSubSystem ?communicatingDevice;"
			+"  				 ssn:hasSubSystem ?sensingDevice;"
			+"    			     iot-liteIns:isConnectedTo ?miniserver;"
			+"   				 iot-lite:exposedBy ?service."
			+"  ?attribute  	 iot-lite:isAssociatedWith    ssn:"+deviceMacAddress+"."
			+"  ?system  		 ssn:hasSubSystem  ssn:"+deviceMacAddress+"."
			+"  ?communicatingDevice a iot-liteIns:CommunicatingDevice."
			+" ?sensingDevice a ssn:SensingDevice ."
			+ " ssn:"+deviceMacAddress+" iot-lite:hasCoverage ?coverage."
			+" ?coverage iot-lite:hasPoint ?point."
			+"  ?point  a  geo:Point;"
			+"          geo:lat ?latitude;"
			+"          geo:long ?longtitude ."
			+"		 }"
			+"}";

 FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
  
}

public static void deleteSensingDevice(String UUID){
	String strQuery = 
				"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
			   +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +"PREFIX g: <http://learningsparql.com/ns/graphs#>"
			   +"	DELETE { "
			   +"	  GRAPH g:SensingDevices { "
			   +"	       ssn:"+UUID+ " a ssn:SensingDevice ;"
			   +"	      				  iot-liteIns:hasName ?name ;"
			   +"                         iot-liteIns:hasCommunicatingDevice ?communicatingDevice ."
			   +"	    }"
			   +"	 }"
			   +"	WHERE{"
			   +"	     GRAPH g:SensingDevices {"
			   +"	       ssn:"+UUID+ " a ssn:SensingDevice ;"
			   +"	      				  iot-liteIns:hasName ?name;"
			   +"                         iot-liteIns:hasCommunicatingDevice ?communicatingDevice ."
			   +"	    }"
			   +"	  }";
	
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	deleteSensorsHavingDeletedSensingDevice(UUID);
	
	// hard delete

}


 public static void deleteCommunicatingDevice(String macAddress){
	String strQuery = 
			 "PREFIX g: <http://learningsparql.com/ns/graphs#>"
			+"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			+"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
			+"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
			+"DELETE{"
			+ "GRAPH g:CommunicatingDevices "
			+"  {"
			+ "?communicatingDeviceName a iot-liteIns:CommunicatingDevice;"
			+" 						    iot-liteIns:hasMacaddress \""+macAddress+"\" ;"
			+ "							iot-liteIns:hasBandwidth ?bandwidth;"
			+ "							iot-liteIns:hasFrequency ?frequency;"
			+ "							iot-liteIns:hasNetworkTopology ?networkTopology;"
			+ "							iot-liteIns:hasTransmitPower ?transmitPower;"
			+ "							iot-liteIns:hasType ?type;"
			+ "							iot-liteIns:hasDutyCycle ?dutyCycle;"
			+ "							iot-liteIns:hasSensitivity ?sensitivity;"
			+ "							iot-liteIns:hasNumberOfChannels ?numberOfChannels."
			+ "			} "
			+ " } "
			+ "WHERE{ "
			+ "GRAPH g:CommunicatingDevices"
			+ "     {"
			+ "?communicatingDeviceName a iot-liteIns:CommunicatingDevice;"
			+" 						    iot-liteIns:hasMacaddress \""+macAddress+"\" ;"
			+ "							iot-liteIns:hasBandwidth ?bandwidth;"
			+ "							iot-liteIns:hasFrequency ?frequency;"
			+ "							iot-liteIns:hasNetworkTopology ?networkTopology;"
			+ "							iot-liteIns:hasTransmitPower ?transmitPower;"
			+ "							iot-liteIns:hasType ?type;"
			+ "							iot-liteIns:hasDutyCycle ?dutyCycle;"
			+ "							iot-liteIns:hasSensitivity ?sensitivity;"
			+ "							iot-liteIns:hasNumberOfChannels ?numberOfChannels."
			+ "    }" 
			+ "}";
	
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	
	deleteRelationTriple(macAddress);

 }
 
 public static void deleteSensor(String sensorName , String sensingDeviceMacAddress){
	 String strQuery = 

				"	PREFIX g: <http://learningsparql.com/ns/graphs#>"
			   +"	PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
			   +"	PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
			   +"	PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +"	PREFIX iot: <http://www.linkedthings.com/iot/>"
			   +"	DELETE {"
			   +"	  GRAPH g:Sensors{"
			   +"	     ssn:"+sensorName+"      a   ssn:Sensor;"
			   +"	      						 iot-lite:hasSensingDevice iot-liteIns:"+sensingDeviceMacAddress+";"
			   +" 	      					     iot-lite:hasQuantityKind ?quantityKind;"
			   +"	     						 iot-lite:hasUnit  ?unit;"
			   +"	      						 iot-liteIns:hasMetadata ?metadata . "
			   +"      ?metadata  ?property ?value ."
			   +"	   }"
			   +"	 }"
			   +"	WHERE{ "
			   +"		 GRAPH g:Sensors{ "
			   +"	     ssn:"+sensorName+"      a   ssn:Sensor;"
			   +"	      						 iot-lite:hasSensingDevice iot-liteIns:"+sensingDeviceMacAddress+" ;"
			   +"	      					     iot-lite:hasQuantityKind ?quantityKind;"
			   +"	     						 iot-lite:hasUnit  ?unit;"
		       +"		      				     iot-liteIns:hasMetadata ?metadata ."
		       +" }"
			   +"   { "
			   +"  SELECT  ?metadata ?property ?value  "
			   +" WHERE{ "
			   +"    GRAPH g:Sensors{ "
			   +"     ssn:"+sensorName+"   a   ssn:Sensor;"
			   +"                            iot-lite:hasSensingDevice iot-liteIns:"+sensingDeviceMacAddress+";"
			   +"                            iot-liteIns:hasMetadata ?metadata ."
			   +"      ?metadata ?property ?value ."
			   +" 			}"
			   +" 		}"
			   +"	}"
			   +"}";
	 
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	 deleteSensorOutputs(sensorName, sensingDeviceMacAddress);
 }
 
 public static void deleteSensorsMetadata(String sensorName , String sensingDeviceMacAddress){
	 String strQuery = 
			 	 "PREFIX g: <http://learningsparql.com/ns/graphs#>"
				+"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
			    +"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
				+"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
				+" PREFIX iot: <http://www.linkedthings.com/iot/>"
				+" DELETE { "
				+"   GRAPH g:Sensors{ "
				+"     ?metadata ?property ?value . "
				+"    }"
				+"  }"
				+" WHERE{ "
				+"  SELECT  ?metadata ?property ?value "
			    +"  WHERE{ "
			    +"	       GRAPH g:Sensors{ "
			    +"			       ssn:"+sensorName+"   a   ssn:Sensor; "
			    +"			                               iot-lite:hasSensingDevice iot-liteIns:"+sensingDeviceMacAddress+";"
			    +"			                               iot-liteIns:hasMetadata ?metadata ."
			    +"			         ?metadata ?property ?value ."
				+"		    	}"
				+"		 }"		   
			    +" }" ;
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);		
 }
 
 public static void deleteServices(String serviceName){
	 String strQuery =  "PREFIX g: <http://learningsparql.com/ns/graphs#>"
			 		   +"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
			 		   +" DELETE { "
					   +"   GRAPH g:Services{ "
			 		   +"       iot-lite:"+serviceName+"  a   iot-lite:Service ;"
			 		   +"                                 iot-lite:endpoint  ?endpoint ;"
			 		   +"                                 iot-lite:interfaceDescription ?desc ."
			 		   +"  }"
			 		   +"}"
			 		   +"WHERE {"
			 		   +"   GRAPH g:Services{ "
			 		   +"       iot-lite:"+serviceName+"  a   iot-lite:Service ;"
			 		   +"                                 iot-lite:endpoint  ?endpoint ;"
			 		   +"                                 iot-lite:interfaceDescription ?desc ."
			 		   +"  }"
			 		   +"}";
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	 deleteDevicesExposedByDeletedServiceRelation(serviceName);
	 
 }
 
 /*
  *  Many to many relationship
  */
 
 //---------------------------------------------------------------------------------------------------------
 
 public static void deleteDevicesExposedByDeletedServiceRelation(String serviceName){
	 String strQuery = 
			    "PREFIX g: <http://learningsparql.com/ns/graphs#>"
			   +"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
			   +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +"DELETE { "
			   +"   GRAPH g:DeviceExposedByService { "
			   +"	      ?deviceMacAddress iot-lite:exposedBy iot-lite:"+serviceName+" ."
			   +"	     }"
			   +"	 }"
			   +"WHERE { "
			   +"   GRAPH g:DeviceExposedByService { "
			   +"	      ?deviceMacAddress iot-lite:exposedBy iot-lite:"+serviceName+" ."
			   +"	     }"
			   +"	 }";
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
 }
 
 
 public static void deleteServicesExposedByDeletedDeviceRelation(String deviceMacAddress ){
	 String strQuery = 
			    "PREFIX g: <http://learningsparql.com/ns/graphs#>"
			   +"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
			   +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +"DELETE { "
			   +"   GRAPH g:DeviceExposedByService { "
			   +"	      iot-liteIns:"+deviceMacAddress+" iot-lite:exposedBy ?service ."
			   +"	     }"
			   +"	 }"
			   +"WHERE { "
			   +"   GRAPH g:DeviceExposedByService { "
			   +"	      iot-liteIns:"+deviceMacAddress+" iot-lite:exposedBy ?service ."
			   +"	     }"
			   +"	 }";
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
 }
 
 public static void deleteDeviceExposedByServiceRelation (String deviceMacAddress , String serviceName){
	 String strQuery = 
			    "PREFIX g: <http://learningsparql.com/ns/graphs#>"
			   +"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
			   +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +"DELETE DATA{ "
			   +"   GRAPH g:DeviceExposedByService { "
			   +"	      iot-liteIns:"+deviceMacAddress+" iot-lite:exposedBy iot-lite:"+serviceName+" ."
			   +"	     }"
			   +"	 }";
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
 }
 
 
 //-------------------------------------------------------------------------------------------------------------
 
 public static void deleteSensorOutputs(String sensorName , String sensingDeviceMacAddress){
	 String strQuery = 
			 		 "PREFIX g: <http://learningsparql.com/ns/graphs#>"
			    	+"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
			    	+"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			    	+"DELETE { "
			    	+"  GRAPH g:SensorOutputs{ "
			    	+"     		?SensorOutputdata  a  ssn:SensorOutput;"
			        +"  							   iot-liteIns:hasSensingDeviceUUID iot-liteIns:"+sensingDeviceMacAddress+";"	
			        +"  							   ssn:isProducedBy ssn:"+sensorName+";"
			        +"  							   ssn:hasValue  ?value;"
			        +"  							   ssn:observationResultTime ?dateTime ."
			        +"            ?value  a   ssn:ObservationValue ."
			        +"   }"
			        +" }"
			    	+"WHERE{ "
			    	+" GRAPH g:SensorOutputs{ "
			    	+"     		?SensorOutputdata  a  ssn:SensorOutput;"
			        +"							   iot-liteIns:hasSensingDeviceUUID iot-liteIns:"+sensingDeviceMacAddress+";"	
			        +" 							   ssn:isProducedBy ssn:"+sensorName+" ;"
			        +" 							   ssn:hasValue  ?value ;"
			        +"							   ssn:observationResultTime ?dateTime ."
			        +"          ?value  a   ssn:ObservationValue ."
			        +"   }"
			        +"}";
	 
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
 }
 
 public static void deleteSensorOutputsByDateTime(String sensorName , String sensingDeviceMacAddress,String dateTime){
	 String strQuery = 
	 		 "PREFIX g: <http://learningsparql.com/ns/graphs#>"
	    	+"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
	    	+"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
	    	+"DELETE { "
	    	+"  GRAPH g:SensorOutputs{ "
	    	+"     		?SensorOutputdata  a  ssn:SensorOutput;"
	        +"  							   iot-liteIns:hasSensingDeviceUUID iot-liteIns:"+sensingDeviceMacAddress+";"	
	        +"  							   ssn:isProducedBy ssn:"+sensorName+";"
	        +"  							   ssn:hasValue  ?value;"
	        +"  							   ssn:observationResultTime \""+dateTime+"\" ."
	        +"            ?value  a   ssn:ObservationValue ."
	        +"   }"
	        +" }"
	    	+"WHERE{ "
	    	+" GRAPH g:SensorOutputs{ "
	    	+"     		?SensorOutputdata  a  ssn:SensorOutput;"
	        +"							   iot-liteIns:hasSensingDeviceUUID iot-liteIns:"+sensingDeviceMacAddress+";"	
	        +" 							   ssn:isProducedBy ssn:"+sensorName+" ;"
	        +" 							   ssn:hasValue  ?value ;"
	        +"							   ssn:observationResultTime \""+dateTime+"\" ."
	        +"          ?value  a   ssn:ObservationValue ."
	        +"   }"
	        +"}";

	 FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
 }
 
 public static void deleteObject(String objectName){
	 String strQuery = 
			 		 "PREFIX g: <http://learningsparql.com/ns/graphs#>"
			    	+"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
			    	+"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
			    	+"DELETE { "
			    	+"  GRAPH g:Objects{ "
			    	+"     		iot-lite:"+objectName+"  a  iot-lite:Object ;"
			        +"  								 geo:hasLocation ?location ;"
			    	+"                                   iot-lite:hasAttribute ?attribute .  "
			        +"			?location  a  geo:Point;"
			        +"  					   geo:long  ?longtitude ;"
			        +"  					   geo:lat   ?latitude ."
			        +"       ?attribute ?property ?value ."
			        +"   }"
			        +" }"
			    	+"WHERE{ "
			    	+"   GRAPH g:Objects{ "
			    	+"     		iot-lite:"+objectName+"  a  iot-lite:Object ;"
			        +"  								   geo:hasLocation ?location ."
			        +"			?location  a  geo:Point;"
			        +"  					   geo:long  ?longtitude ;"
			        +"  					   geo:lat   ?latitude ."
			        +"   }"
			        +"   { "
					+"  SELECT  ?attribute ?property ?value  "
					+" WHERE{ "
				    +"    GRAPH g:Objects{ "
				    +"   iot-lite:"+objectName+"  iot-lite:hasAttribute ?attribute."
				    +"   ?attribute ?property ?value ."
				    +" 			}"
				    +" 		}"
				    +"	}"
			        +"}";
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	 
 }
 
 public static void deleteAttribute(String ObjectName , String attribute){
	 String strQuery = 
	 		 "PREFIX g: <http://learningsparql.com/ns/graphs#>"
	    	+"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
	    	+"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
	    	+"DELETE { "
	    	+"  GRAPH g:Objects{ "
	    	+"     		iot-lite:"+ObjectName+" iot-lite:hasAttribute  ?attribute ."
	        +"			?attribute  a  iot-lite:Attribute;"
	        +"  					iot-lite:hasQuantityKind ?quantityKind ."
	        +"   }"
	        +" }"
	    	+"WHERE{ "
	    	+"SELECT ?attribute ?quantityKind "
	    	+"	    WHERE{ "
	    	+"		 	  GRAPH g:Objects{ "
	    	+"		     			iot-lite:"+ObjectName+"  iot-lite:hasAttribute  ?attribute ."
	    	+"	    				?attribute  a  iot-lite:Attribute;"
	    	+"	      						    iot-lite:hasQuantityKind ?quantityKind .	"	  			
	    	+"	  			}"
	    	+"			 FILTER (?attribute = iot-lite:"+attribute+")	"
	    	+"	    }	"    
	        +"}";
	 
	 FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	 
 }
 
 /*
  *  one-to-many relationships delete queries 
  */
 
 // ----------------------------------------------------START-------------------------------------------------------
 
public static void deleteDevicesConnectedToDeletedMiniServer(String miniServerName){
	String strQuery = 	
			 "PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
			+"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			+"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
			+"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
			+"PREFIX g: <http://learningsparql.com/ns/graphs#>"
			+"DELETE  { "
			+" GRAPH g:Devices "
			+"  { "
			+"      ?deviceName  a ssn:Device;"
			+" 					 iot-liteIns:hasMacaddress ?macaddress ;"
			+"    			     ssn:hasSubSystem ?communicatingDevice;"
			+"  				 ssn:hasSubSystem ?sensingDevice;"
			+"    			     iot-liteIns:isConnectedTo iot-liteIns:"+miniServerName+";"
			+"  				 iot-lite:exposedBy ?service."
			+"  ?attribute  	 iot-lite:isAssociatedWith  ?deviceName."
			+"  ?system  		 ssn:hasSubSystem ?deviceName."
			+"  ?communicatingDevice a iot-liteIns:CommunicatingDevice."
			+"  ?sensingDevice a ssn:SensingDevice ."
			+"  ?deviceName iot-lite:hasCoverage ?coverage."
			+"  ?coverage a ?coverageClass ."
			+"  ?coverage iot-lite:hasPoint ?point."
			+"  ?point a geo:Point ;"
			+"     	 geo:lat ?latitude;"
			+"    	 geo:long ?longtitude ."
			+"  		    } "
			+"}"
			+"WHERE{"
			+"GRAPH g:Devices "
			+"  {"
			+" ?deviceName a ssn:Device;"
			+" 					 iot-liteIns:hasMacaddress ?macaddress ;"
			+"    			     ssn:hasSubSystem ?communicatingDevice;"
			+"  				 ssn:hasSubSystem ?sensingDevice;"
			+"    			     iot-liteIns:isConnectedTo iot-liteIns:"+miniServerName+";"
			+"   				 iot-lite:exposedBy ?service."
			+"  ?attribute  	 iot-lite:isAssociatedWith  ?deviceName."
			+"  ?system  		 ssn:hasSubSystem ?deviceName."
			+"  ?communicatingDevice a iot-liteIns:CommunicatingDevice."
			+" ?sensingDevice a ssn:SensingDevice ."
			+ "?deviceName iot-lite:hasCoverage ?coverage."
			+"  ?coverage a ?coverageClass ."
			+" ?coverage iot-lite:hasPoint ?point."
			+"  ?point  a  geo:Point;"
			+"          geo:lat ?latitude;"
			+"          geo:long ?longtitude ."
			+"		 }"
			+"}";

FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
}


public static void deleteDevicesSubSystemOfDeletedSystem(String systemName){
	String strQuery = 	
			 "PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
			+"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			+"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
			+"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
			+"PREFIX g: <http://learningsparql.com/ns/graphs#>"
			+"DELETE  { "
			+" GRAPH g:Devices "
			+"  { "
			+"  ssn:"+systemName+"   ssn:hasSubSystem ?deviceName."
			+"      ?deviceName  a ssn:Device;"
			+" 					 iot-liteIns:hasMacaddress ?macaddress ;"
			+"    			     ssn:hasSubSystem ?communicatingDevice;"
			+"  				 ssn:hasSubSystem ?sensingDevice;"
			+"    			     iot-liteIns:isConnectedTo ?miniserver;"
			+"  				 iot-lite:exposedBy ?service."
			+"  ?attribute  	 iot-lite:isAssociatedWith  ?deviceName."
			+"  ?communicatingDevice a iot-liteIns:CommunicatingDevice."
			+"  ?sensingDevice a ssn:SensingDevice ."
			+"  ?deviceName iot-lite:hasCoverage ?coverage."
			+"  ?coverage a ?coverageClass ."
			+"  ?coverage iot-lite:hasPoint ?point."
			+"  ?point a geo:Point ;"
			+"     	 geo:lat ?latitude;"
			+"    	 geo:long ?longtitude ."
			+"  		    } "
			+"}"
			+"WHERE{"
			+"GRAPH g:Devices "
			+"  {"
			+" ssn:"+systemName+"    ssn:hasSubSystem ?deviceName."
			+"       ?deviceName a ssn:Device;"
			+" 					 iot-liteIns:hasMacaddress ?macaddress ;"
			+"    			     ssn:hasSubSystem ?communicatingDevice;"
			+"  				 ssn:hasSubSystem ?sensingDevice;"
			+"    			     iot-liteIns:isConnectedTo ?miniserver;"
			+"   				 iot-lite:exposedBy ?service."
			+"  ?attribute  	 iot-lite:isAssociatedWith  ?deviceName."
			+"  ?communicatingDevice a iot-liteIns:CommunicatingDevice."
			+" ?sensingDevice a ssn:SensingDevice ."
			+ "?deviceName iot-lite:hasCoverage ?coverage."
			+"  ?coverage a ?coverageClass ."
			+" ?coverage iot-lite:hasPoint ?point."
			+"  ?point  a  geo:Point;"
			+"          geo:lat ?latitude;"
			+"          geo:long ?longtitude ."
			+"		 }"
			+"}";

FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
}


public static void deleteDevicesAssociatedWithDeletedAttribute(){
	
}

public static void deleteSensorsHavingDeletedSensingDevice(String sensingDeviceUUID){
	 String strQuery = 

				"	PREFIX g: <http://learningsparql.com/ns/graphs#>"
			   +"	PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
			   +"	PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
			   +"	PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +"	PREFIX iot: <http://www.linkedthings.com/iot/>"
			   +"	DELETE {"
			   +"	  GRAPH g:Sensors{"
			   +"	    ?sensor      a   ssn:Sensor;"
			   +"	      						 iot-lite:hasSensingDevice iot-liteIns:"+sensingDeviceUUID+";"
			   +" 	      					     iot-lite:hasQuantityKind ?quantityKind;"
			   +"	     						 iot-lite:hasUnit  ?unit;"
		       +"		      					 iot-liteIns:hasCommunicatingDevice ?communicatingDevice;"
			   +"	      						 iot-liteIns:hasMetadata ?metadata . "
			   +"      ?metadata  ?property ?value ."
			   +"	   }"
			   +"	 }"
			   +"	WHERE{ "
			   +"		 GRAPH g:Sensors{ "
			   +"	     ?sensor      a   ssn:Sensor;"
			   +"	      						 iot-lite:hasSensingDevice iot-liteIns:"+sensingDeviceUUID+" ;"
			   +"	      					     iot-lite:hasQuantityKind ?quantityKind;"
			   +"	     						 iot-lite:hasUnit  ?unit;"
			   +"	       						 iot-liteIns:hasCommunicatingDevice ?communicatingDevice;"
		       +"		      						 iot-liteIns:hasMetadata ?metadata ."
		       +" }"
			   +"   { "
			   +"  SELECT  ?metadata ?property ?value  "
			   +" WHERE{ "
			   +"    GRAPH g:Sensors{ "
			   +"     ?sensor   a   ssn:Sensor;"
			   +"                            iot-lite:hasSensingDevice iot-liteIns:"+sensingDeviceUUID+";"
			   +"                            iot-liteIns:hasMetadata ?metadata ."
			   +"      ?metadata ?property ?value ."
			   +" 			}"
			   +" 		}"
			   +"	}"
			   +"}";
	 
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
}

public static void deleteMiniServersSubSystemOfDeletedSystem(String systemName){
	 String strQuery = 
		 	 "PREFIX g: <http://learningsparql.com/ns/graphs#>"
			 +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			 +"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
			 +"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
			 +"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
			 +"DELETE { "
			 +"	GRAPH g:MiniServers { "
			 +"      	ssn:"+systemName+" ssn:hasSubSystem ?miniServer ."	
			 +" 		?miniServer a  iot-liteIns:MiniServer;"
			 +"      			    geo:hasLocation ?location ."
			 +"        ?location a   geo:Point;"
			 +"      			  geo:lat ?latitude ;"
			 +"      			  geo:long ?longtitude."    					
			 +"			}"
			 +"}"
			 +"WHERE{"
			 +"GRAPH g:MiniServers {"
			 +"      	ssn:"+systemName+" ssn:hasSubSystem ?miniServer ."	
			 +" 		?miniServer a  iot-liteIns:MiniServer;"
			 +"      									geo:hasLocation ?location ."
			 +"        ?location a   geo:Point;"
			 +"      			  geo:lat ?latitude ;"
			 +"      			  geo:long ?longtitude."    						
			 +"		}"
			 +"}";
FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
}



 //-----------------------------------------------------END---------------------------------------------------------
 
/*
 * ONE TO ONE RELATIONSHIPS 
 * sensor has communicatingDevice communicatingDevice  triple in sensors graph
 */

//-----------------------------------------------------START--------------------------------------------------------
 
public static void deleteRelationTriple(String communicatingDeviceMacAddress){
	 String strQuery = 

				"	PREFIX g: <http://learningsparql.com/ns/graphs#>"
			   +"	PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +"	DELETE {"
			   +"	  GRAPH g:SensingDevices{"
		       +"		   ?sensingDevice  iot-liteIns:hasCommunicatingDevice iot-liteIns:"+communicatingDeviceMacAddress+"."
			   +"	   }"
			   +"	 }"
			   +"	WHERE{ "
			   +"		 GRAPH g:SensingDevices{ "
			   +"	       ?sensingDevice  iot-liteIns:hasCommunicatingDevice iot-liteIns:"+communicatingDeviceMacAddress+"."
			   +"	}"
			   +"}";
	 
	FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
}


//-----------------------------------------------------END----------------------------------------------------------



 
 
 public static void main(String[] args) {
	//deletePerson("MohamedAhmed");
	//deleteApplication("FoodTesterApp");
    //deletePersonUsesApplicationRelation("MohamedAhmed","FoodTesterApp");
	//deleteSystem("CBuilding");
	//deleteApplicationUsesSystemRelation("FoodTesterApp", "CBuilding"); 
	//deleteSystemHasSubSystemRelation("GUC", "CBuilding"); 
	//deleteMiniServers("C_Ground_miniserver");
	//	deleteDevice("c23548ab568953a");
	//deleteSensingDevice("734d3ef2-3385-4470-90bf-4f5554496dc9");
	//deleteCommunicatingDevice("6c23548ab568953a");
	//deleteSensor("Tempreture_Sensor", "734d3ef2-3385-4470-90bf-4f5554496dc9");
	//deleteSensorsMetadata("Tempreture_Sensor", "c82f5b36-a913-4de3-89c0-cdac713ebfec");
	//deleteServices("HealthChecker");
    //deleteDeviceExposedByServiceRelation("6c23548ab568953a", "HealthChecker");
	//deleteSensorOutputs("Humidity_Sensor", "8d47cf8f-4f15-4616-a01e-ccf3099c5937");
	//deleteSensorOutputsByDateTime("Humidity_Sensor", "530e5d9c-5712-409a-aec8-25f3faceaab1", "Wed Jun 29 14:19:48 EET 2016");
	//deleteObject("C1_Cafeteria");
	//deleteAttribute("C1_Cafeteria", "Tempreture_and_Humidity");
	//deleteApplicationsUsedByDeletedPersonRelation("HatemMorgan");
	//deletePersonUsesDeletedApplicationRelation("FoodTesterApp");
	//deleteApplicationsUsesDeletedSystemRelation("CBuilding");
	//deleteSystemsUsedByDeletedApplicationRelation("FoodTesterApp");
	//deleteSystemHasDeletedSubSystemRelation("CBuilding");
	//deletesubSystemsOfDeletedSystemRelation("GUC");
	//deleteDevicesConnectedToDeletedMiniServer("C_Ground_miniserver");
	//deleteDevicesExposedByDeletedServiceRelation("HealthChecker");
	//deleteServicesExposedByDeletedDeviceRelation("6c23548ab568953a");
	//deleteDevicesSubSystemOfDeletedSystem("CBuilding");
	//deleteSensorsHavingDeletedSensingDevice("31130131-8f12-4aab-b1ea-8bfd352a39b4");
	//deleteRelationTriple("6c23548ab568953a");
 }
}

