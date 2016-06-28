package JenaFusekiServer;

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
 
 public static void deleteSensors(){
	 
 }
 
 public static void main(String[] args) {
	//deletePerson("MohamedAhmed");
	//deleteApplication("FoodTesterApp");
    //deletePersonUsesApplicationRelation("MohamedAhmed","FoodTesterApp");
	//deleteSystem("CBuilding");
	//deleteApplicationUsesSystemRelation("FoodTesterApp", "CBuilding"); 
	//deleteSystemHasSubSystemRelation("GUC", "CBuilding"); 
	// deleteMiniServers("C_Ground_miniserver");
	// deleteDevice("6c23548ab568953a");
	//deleteSensingDevice("47bed232-3d43-4033-a937-20e34c1ad055");
	 deleteCommunicatingDevice("6c23548ab568953a");
}
}

