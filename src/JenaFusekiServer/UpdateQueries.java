package JenaFusekiServer;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

import Ontologies.entitiesOntologyPropertiesPrefixes;

public class UpdateQueries {
	public static void updatePerson(String userName,Hashtable<String, String> htblColNameValue){

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
	  System.out.println(strQuery);
	  executeUpdateQueries(strQuery);
	}
	public static void executeUpdateQueries(String strQuery) {
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
		
	}
}
