package JenaFusekiServer;

import java.util.Date;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

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
	 
	 executeDeleteQuery(strQuery);
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

	 executeDeleteQuery(strQuery);
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
	 
	 executeDeleteQuery(strQuery);
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
	executeDeleteQuery(strQuery);
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
	 executeDeleteQuery(strQuery);
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
	 executeDeleteQuery(strQuery);

 }
 
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
	 executeDeleteQuery(strQuery);
				 
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
			+"      ?deviceName  a ssn:Device;"
 			+" 					 iot-liteIns:hasMacaddress \""+deviceMacAddress+"\" ;"
			+"    			     ssn:hasSubSystem ?communicatingDevice;"
			+"  				 ssn:hasSubSystem ?sensingDevice;"
			+"    			     iot-liteIns:isConnectedTo ?miniserver;"
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
			+"    			     ssn:hasSubSystem ?communicatingDevice;"
			+"  				 ssn:hasSubSystem ?sensingDevice;"
			+"    			     iot-liteIns:isConnectedTo ?miniserver;"
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

  executeDeleteQuery(strQuery);
}

public static void deleteSensingDevice(String UUID){
	String strQuery = 
				"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
			   +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +"PREFIX g: <http://learningsparql.com/ns/graphs#>"
			   +"	DELETE { "
			   +"	  GRAPH g:SensingDevices { "
			   +"	       ?sensingDevice a ssn:SensingDevice ;"
			   +"	                      iot-liteIns:hasUUID \""+UUID+"\" ;"
			   +"	      				  iot-liteIns:hasName ?name"
			   +"	    }"
			   +"	 }"
			   +"	WHERE{"
			   +"	     GRAPH g:SensingDevices {"
			   +"	       ?sensingDevice a ssn:SensingDevice ;"
			   +"	                      iot-liteIns:hasUUID \""+UUID+"\" ;"
			   +"	      				  iot-liteIns:hasName ?name"
			   +"	    }"
			   +"	  }";
	
	executeDeleteQuery(strQuery);

}

 public static void executeDeleteQuery(String strQuery){
	 UpdateProcessor updateQuery = UpdateExecutionFactory.createRemote(
				UpdateFactory.create(strQuery),
				"http://localhost:3030/myDataset/update");
		try {
			updateQuery.execute();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
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
	
	executeDeleteQuery(strQuery);

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
		       +"		      					 iot-liteIns:hasCommunicatingDevice ?communicatingDevice;"
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
			   +"	       						 iot-liteIns:hasCommunicatingDevice ?communicatingDevice;"
		       +"		      						 iot-liteIns:hasMetadata ?metadata ."
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
	 
	 executeDeleteQuery(strQuery);
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
	 executeDeleteQuery(strQuery);		
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
	 executeDeleteQuery(strQuery);
 }
 
 public static void deleteDeviceExposedByServiceRelation (String deviceMacAddress , String serviceName){
	 String strQuery = 
			    "PREFIX g: <http://learningsparql.com/ns/graphs#>"
			   +"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
			   +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			   +"DELETE DATA{ "
			   +"   GRAPH g:DeviceExposedByService { "
			   +"	      iot-liteIns:"+deviceMacAddress+" ssn:exposedBy iot-lite:"+serviceName+" ."
			   +"	     }"
			   +"	 }";
	 executeDeleteQuery(strQuery);
 }
 
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
	 
	 executeDeleteQuery(strQuery);
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

executeDeleteQuery(strQuery);
 }
 
 public static void deleteObject(String objectName){
	 String strQuery = 
			 		 "PREFIX g: <http://learningsparql.com/ns/graphs#>"
			    	+"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
			    	+"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
			    	+"DELETE { "
			    	+"  GRAPH g:Objects{ "
			    	+"     		iot-lite:C1_Cafeteria  a  iot-lite:Object ;"
			        +"  								   geo:hasLocation ?location ."
			        +"			?location  a  geo:Point;"
			        +"  					   geo:long  ?longtitude ;"
			        +"  					   geo:lat   ?latitude ."
			        +"   }"
			        +" }"
			    	+"WHERE{ "
			    	+"   GRAPH g:Objects{ "
			    	+"     		iot-lite:C1_Cafeteria  a  iot-lite:Object ;"
			        +"  								   geo:hasLocation ?location ."
			        +"			?location  a  geo:Point;"
			        +"  					   geo:long  ?longtitude ;"
			        +"  					   geo:lat   ?latitude ."
			        +"   }"
			        +"}";
	 executeDeleteQuery(strQuery);
 }
 
 public static void deleteAttribute(String ObjectName , String attribute){
	 String strQuery = 
	 		 "PREFIX g: <http://learningsparql.com/ns/graphs#>"
	    	+"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
	    	+"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
	    	+"DELETE { "
	    	+"  GRAPH g:Objects{ "
	    	+"     		iot-lite:C1_Cafeteria iot-lite:hasAttribute  ?attribute ."
	        +"			?attribute  a  iot-lite:Attribute;"
	        +"  					iot-lite:hasQuantityKind ?quantityKind ."
	        +"   }"
	        +" }"
	    	+"WHERE{ "
	    	+"SELECT ?attribute ?quantityKind "
	    	+"	    WHERE{ "
	    	+"		 	  GRAPH g:Objects{ "
	    	+"		     			iot-lite:C1_Cafeteria  iot-lite:hasAttribute  ?attribute ."
	    	+"	    				?attribute  a  iot-lite:Attribute;"
	    	+"	      						    iot-lite:hasQuantityKind ?quantityKind .	"	  			
	    	+"	  			}"
	    	+"			 FILTER (?attribute = iot-lite:"+attribute+")	"
	    	+"	    }	"    
	        +"}";
executeDeleteQuery(strQuery);
 }
 
 public static void main(String[] args) {
	//deletePerson("MohamedAhmed");
	//deleteApplication("FoodTesterApp");
    //deletePersonUsesApplicationRelation("MohamedAhmed","FoodTesterApp");
	//deleteSystem("CBuilding");
	//deleteApplicationUsesSystemRelation("FoodTesterApp", "CBuilding"); 
	//deleteSystemHasSubSystemRelation("GUC", "CBuilding"); 
	//deleteMiniServers("C_Ground_miniserver");
	//deleteDevice("6c23548ab568953a");
	//deleteSensingDevice("47bed232-3d43-4033-a937-20e34c1ad055");
	//deleteCommunicatingDevice("6c23548ab568953a");
	//deleteSensor("Tempreture_Sensor", "3ed0a862-17f3-4c0d-8f9a-57bdfe8aca52");
	//deleteSensorsMetadata("Tempreture_Sensor", "c82f5b36-a913-4de3-89c0-cdac713ebfec");
	//deleteServices("HealthChecker");
    //deleteDeviceExposedByServiceRelation("6c23548ab568953a", "HealthChecker");
	//deleteSensorOutputs("Humidity_Sensor", "8d47cf8f-4f15-4616-a01e-ccf3099c5937");
	//deleteSensorOutputsByDateTime("Humidity_Sensor", "530e5d9c-5712-409a-aec8-25f3faceaab1", "Wed Jun 29 14:19:48 EET 2016");
	//deleteObject("C1_Cafeteria");
	 deleteAttribute("C1_Cafeteria", "Tempreture_and_Humidity");
 }
}

