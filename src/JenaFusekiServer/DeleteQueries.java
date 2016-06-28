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
 
 public static void main(String[] args) {
	//deletePerson("MohamedAhmed");
	//deleteApplication("FoodTesterApp");
    //deletePersonUsesApplicationRelation("MohamedAhmed","FoodTesterApp");
	//deleteSystem("CBuilding");
	//deleteApplicationUsesSystemRelation("FoodTesterApp", "CBuilding"); 
	deleteSystemHasSubSystemRelation("GUC", "CBuilding"); 
}
}

