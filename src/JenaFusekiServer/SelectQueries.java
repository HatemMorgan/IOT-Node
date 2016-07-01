package JenaFusekiServer;

import java.io.ByteArrayOutputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

public class SelectQueries {
	
	
	
  public static String getPersons(){
	  String query = 
		  		  "PREFIX g: <http://learningsparql.com/ns/graphs#>"
				 +"SELECT ?a ?b ?c {"
				 +" GRAPH g:Persons {"
				 +"  ?a ?b ?c .     "
				 +"}"
				 +" }";
		Query qe = QueryFactory.create(query);

		QueryExecution quex = QueryExecutionFactory.sparqlService(
				"http://localhost:3030/myDataset/query", qe);

		try {
			ResultSet results = quex.execSelect();
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			ResultSetFormatter.outputAsJSON(outputStream, results);
			String json = new String(outputStream.toByteArray());
		   return json;
		
			//ResultSetFormatter.out(System.out, results);
		} finally {
			quex.close();
		}
  }
  public static void main(String[] args) {
	getPersons();
}
}
