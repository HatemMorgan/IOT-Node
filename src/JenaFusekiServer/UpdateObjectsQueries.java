package JenaFusekiServer;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

import Ontologies.entitiesOntologyPropertiesPrefixes;

public class UpdateObjectsQueries {
	public static void updatePerson(String userName,Hashtable<String, String> htblColNameValue) throws Exception{

		Hashtable<String, String> htblprefixProperty = entitiesOntologyPropertiesPrefixes.getPersonPropertiesPrefixes();

		String deleteStatement = "";
		String insertStatement = "";
		String whereStatement = "";

		 Set<String> keySet = htblColNameValue.keySet();
		 Iterator<String> itr = keySet.iterator();

		 // use the count to know the last statment in the query in order to add . instead of ; "
		 int count = htblColNameValue.size();
       
		 while(itr.hasNext()){
			 String property = itr.next();
			 String value = htblColNameValue.get(property);

			 if(htblprefixProperty.containsKey(property)){
			 
			 if(count != 1){
				 deleteStatement += htblprefixProperty.get(property)+":"+property +"   ?"+property+";";
				 whereStatement  += htblprefixProperty.get(property)+":"+property +"   ?"+property+";";
				 insertStatement += htblprefixProperty.get(property)+":"+property  +"   \""+value+"\";";
				 count--;
			 }else{
				 deleteStatement += htblprefixProperty.get(property)+":"+property +"   ?"+property+".";
				 whereStatement  += htblprefixProperty.get(property)+":"+property +"   ?"+property+".";
				 insertStatement += htblprefixProperty.get(property)+":"+property  +"   \""+value+"\".";
			 }
			 
		 }else{
			 throw new Exception(property+" is not a property of  person class");
		 }

		 }

	  String strQuery =
			  	   "PREFIX g: <http://learningsparql.com/ns/graphs#>"
				  +"PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				  +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
				  +" DELETE { "
				  +"   GRAPH g:Persons{ "
				  +"	     	foaf:"+userName+" a foaf:Person;"
	  			  +deleteStatement
	  			  +"   }"
				  +"}"
	  			  +" INSERT { "
				  +"   GRAPH g:Persons{ "
				  +"	     	foaf:"+userName+" a foaf:Person;"
				  +insertStatement
				  +"   }"
				  +"}"
				  +" where { "
				  +"   GRAPH g:Persons{ "
				  +"	     	foaf:"+userName+" a foaf:Person;"
				  +whereStatement
				  +"   }"
				  +"}";

	  FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	}
	
	
	public static void updateApplication(String applicationName , Hashtable<String, String> htblColNameValue) throws Exception{
	Hashtable<String, String> htblprefixProperty  = entitiesOntologyPropertiesPrefixes.getApplicationPropertiesPrefixes();
	
	String deleteStatement = "";
	String insertStatement = "";
	String whereStatement = "";

	 Set<String> keySet = htblColNameValue.keySet();
	 Iterator<String> itr = keySet.iterator();

	 // use the count to know the last statment in the query in order to add . instead of ; "
	 int count = htblColNameValue.size();

	 while(itr.hasNext()){
		 String property = itr.next();
		 String value = htblColNameValue.get(property);
        
		 if(htblprefixProperty.containsKey(property)){
			 
		 if(count != 1){
			 deleteStatement += htblprefixProperty.get(property)+":"+property +"   ?"+property+";";
			 whereStatement  += htblprefixProperty.get(property)+":"+property +"   ?"+property+";";
			 insertStatement += htblprefixProperty.get(property)+":"+property  +"   \""+value+"\";";
			 count--;
		 }else{
			 deleteStatement += htblprefixProperty.get(property)+":"+property +"   ?"+property+".";
			 whereStatement  += htblprefixProperty.get(property)+":"+property +"   ?"+property+".";
			 insertStatement += htblprefixProperty.get(property)+":"+property  +"   \""+value+"\".";
		 }
	 }else{
		 throw new Exception(property+" is not a property of Application Class");
	 }

	 }
	 
	 String strQuery =
		  	   "PREFIX g: <http://learningsparql.com/ns/graphs#>"
			  +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			  +" DELETE { "
			  +"   GRAPH g:Applications{ "
			  +"	     iot-liteIns:"+applicationName+" a  iot-liteIns:Application;"
			  +deleteStatement
			  +"   }"
			  +"}"
			  +" INSERT { "
			  +"   GRAPH g:Applications{ "
			  +"	     iot-liteIns:"+applicationName+" a  iot-liteIns:Application;"	
			  +insertStatement
			  +"   }"
			  +"}"
			  +" where { "
			  +"   GRAPH g:Applications{ "
			  +"	     iot-liteIns:"+applicationName+" a  iot-liteIns:Application;"
			  +whereStatement
			  +"   }"
			  +"}";
	 
	 FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	
	}
	
	public static void updateMiniServerLocation	 (String miniServerName , Hashtable<String, String> htblColNameValue){
		
		 String locationName = htblColNameValue.get("hasLocation");
		 String locationLatitude = htblColNameValue.get("lat");
		 String locationLongtitude = htblColNameValue.get("long");
       
		 
		 String strQuery =
			  	   "PREFIX g: <http://learningsparql.com/ns/graphs#>"
				  +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
				  +"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
				  +" DELETE { "
				  +"   GRAPH g:MiniServers{ "
				  +"	     iot-liteIns:"+miniServerName+" geo:hasLocation  ?point ."
				  +"		 ?point   a	geo:Point	;							"
				  +"                  geo:lat  ?latitude ;"
				  +"                  geo:long ?longtitude ."
				  +"   }"
				  +"}"
				  +" INSERT { "
				  +"   GRAPH g:MiniServers{ "
				  +"	     iot-liteIns:"+miniServerName+" geo:hasLocation  geo:"+locationName+" ."
				  +"		 geo:"+locationName+"  a	 geo:Point	;"		
				  +"		    				  geo:lat  \""+locationLatitude+"\" ;"
				  +"							  geo:long  \""+locationLongtitude+"\" ."							
				  +"   }"
				  +"}"
				  +" where { "
				  +"   GRAPH g:MiniServers{ "
				  +"	     iot-liteIns:"+miniServerName+" geo:hasLocation  ?point."
				  +"		 ?point   a	geo:Point	;							"
				  +"                  geo:lat  ?latitude ;"
				  +"                  geo:long ?longtitude ."
				  +"   }"
				  +"}";
		 
		 FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	}
	
	public static void updateCommunicatingDeviceOfSensingDevice(String sensingDeviceUUID , String CommunicationDeviceMacAddress){
		
		String strQuery = 
			  	   "PREFIX g: <http://learningsparql.com/ns/graphs#>"
				  +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
			      +"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"			   
				  +"DELETE { "
				  +"  GRAPH g:SensingDevices{ "
				  +"        ssn:"+sensingDeviceUUID +" iot-liteIns:hasCommunicatingDevice ?communicatingDevice ."
				  +"   }"
				  +" }"
				  +"INSERT { "
				  +"   GRAPH g:SensingDevices{ "
				  +"        ssn:"+sensingDeviceUUID +" iot-liteIns:hasCommunicatingDevice iot-liteIns:"+CommunicationDeviceMacAddress+"."
				  +"      }"
				  +"  }"
				  +"WHERE { "
				  +"  GRAPH g:SensingDevices{ "
				  +"        ssn:"+sensingDeviceUUID +" iot-liteIns:hasCommunicatingDevice ?communicatingDevice ."
				  +"   }"
				  +" }";
				  
		FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);		         
	}
	
	public static void updateCommunicatingDevice(String macAddress , Hashtable<String, String> htblColNameValue ) throws Exception{
		
		Hashtable<String, String> htblprefixProperty  = entitiesOntologyPropertiesPrefixes.getCommunicatingDevicePropertiesPrefixes();
		
		String deleteStatement = "";
		String insertStatement = "";
		String whereStatement = "";

		 Set<String> keySet = htblColNameValue.keySet();
		 Iterator<String> itr = keySet.iterator();

		 // use the count to know the last statment in the query in order to add . instead of ; "
		 int count = htblColNameValue.size();

		 while(itr.hasNext()){
			
			 String property = itr.next();
			 String value = htblColNameValue.get(property);
			 
			 if(htblprefixProperty.containsKey(property)){
				 
			
			 if(count != 1){
				 deleteStatement += "iot-liteIns:"+property +"   ?"+property+";";
				 whereStatement  += "iot-liteIns:"+property +"   ?"+property+";";
				 insertStatement += "iot-liteIns:"+property  +"   \""+value+"\";";
				 count--;
			 }else{
				 deleteStatement += "iot-liteIns:"+property +"   ?"+property+".";
				 whereStatement  += "iot-liteIns:"+property +"   ?"+property+".";
				 insertStatement += "iot-liteIns:"+property  +"   \""+value+"\".";
			 	}
			 }else{
				 throw new Exception(property+" is not a property of CommunicatingDevice Class");
			 }
		 }
		 
		 String strQuery =
			  	   "PREFIX g: <http://learningsparql.com/ns/graphs#>"
				  +"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
				  +" DELETE { "
				  +"   GRAPH g:CommunicatingDevices{ "
				  +"	     iot-liteIns:"+macAddress+" a  iot-liteIns:CommunicatingDevice;"
				  +deleteStatement
				  +"   }"
				  +"}"
				  +" INSERT { "
				  +"   GRAPH g:CommunicatingDevices{ "
				  +"	     iot-liteIns:"+macAddress+" a  iot-liteIns:CommunicatingDevice;"	
				  +insertStatement
				  +"   }"
				  +"}"
				  +" where { "
				  +"   GRAPH g:CommunicatingDevices{ "
				  +"	     iot-liteIns:"+macAddress+" a  iot-liteIns:CommunicatingDevice;"
				  +whereStatement
				  +"   }"
				  +"}";
		 
		 FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	}
	
	public static void updateService(String serviceName ,Hashtable<String, String> htblColNameValue ) throws Exception{
		Hashtable<String, String> htblprefixProperty  = entitiesOntologyPropertiesPrefixes.getServicePropertiesPrefixes();
		
		String deleteStatement = "";
		String insertStatement = "";
		String whereStatement = "";

		 Set<String> keySet = htblColNameValue.keySet();
		 Iterator<String> itr = keySet.iterator();

		 // use the count to know the last statment in the query in order to add . instead of ; "
		 int count = htblColNameValue.size();

		 while(itr.hasNext()){
			
			 String property = itr.next();
			 String value = htblColNameValue.get(property);
			 
			 if(htblprefixProperty.containsKey(property)){
				 
			
				 if(count != 1){
					 deleteStatement += htblprefixProperty.get(property)+":"+property +"   ?"+property+";";
					 whereStatement  += htblprefixProperty.get(property)+":"+property +"   ?"+property+";";
					 insertStatement += htblprefixProperty.get(property)+":"+property  +"   \""+value+"\";";
					 count--;
				 }else{
					 deleteStatement += htblprefixProperty.get(property)+":"+property +"   ?"+property+".";
					 whereStatement  += htblprefixProperty.get(property)+":"+property +"   ?"+property+".";
					 insertStatement += htblprefixProperty.get(property)+":"+property  +"   \""+value+"\".";
				 }
				 
			 }else{
				 throw new Exception(property+" is not a property of a Service class ");
			 }
		 }
		 
		 String strQuery =
			  	   "PREFIX g: <http://learningsparql.com/ns/graphs#>"
				  +"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
				  +" DELETE { "
				  +"   GRAPH g:Services{ "
				  +"	     iot-lite:"+serviceName+" a  iot-lite:Service ;"
				  +deleteStatement
				  +"   }"
				  +"}"
				  +" INSERT { "
				  +"   GRAPH g:Services{ "
				  +"	     iot-lite:"+serviceName+" a  iot-lite:Service ;"
				  +insertStatement
				  +"   }"
				  +"}"
				  +" where { "
				  +"   GRAPH g:Services{ "
				  +"	     iot-lite:"+serviceName+" a  iot-lite:Service ;"
				  +whereStatement
				  +"   }"
				  +"}";
		 
		 FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	}
	public static void updateObjectLocation	 (String objectName , Hashtable<String, String> htblColNameValue){
		
		 String locationName = htblColNameValue.get("hasLocation");
		 String locationLatitude = htblColNameValue.get("lat");
		 String locationLongtitude = htblColNameValue.get("long");
      
		 
		 String strQuery =
			  	   "PREFIX g: <http://learningsparql.com/ns/graphs#>"
				  +"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
				  +"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
				  +" DELETE { "
				  +"   GRAPH g:Objects{ "
				  +"	     iot-lite:"+objectName+" geo:hasLocation  ?point ."
				  +"		 ?point   a	geo:Point	;							"
				  +"                  geo:lat  ?latitude ;"
				  +"                  geo:long ?longtitude ."
				  +"   }"
				  +"}"
				  +" INSERT { "
				  +"   GRAPH g:Objects{ "
				  +"	     iot-lite:"+objectName+" geo:hasLocation  geo:"+locationName+" ."
				  +"		 geo:"+locationName+"  a	 geo:Point	;"		
				  +"		    				  geo:lat  \""+locationLatitude+"\" ;"
				  +"							  geo:long  \""+locationLongtitude+"\" ."							
				  +"   }"
				  +"}"
				  +" where { "
				  +"   GRAPH g:Objects{ "
				  +"	     iot-lite:"+objectName+" geo:hasLocation  ?point."
				  +"		 ?point   a	geo:Point	;							"
				  +"                  geo:lat  ?latitude ;"
				  +"                  geo:long ?longtitude ."
				  +"   }"
				  +"}";
		 
		 FusekiExecuteQueries.executeUpdateAndDeleteQuery(strQuery);
	}
	
    public static void updateSensor(){
    	// TODO when specifing a unique value to it
    }
	
  
    
    
	public static void main(String[] args) {
		
	}
}
