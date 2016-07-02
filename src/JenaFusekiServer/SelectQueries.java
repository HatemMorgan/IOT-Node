package JenaFusekiServer;

import java.io.ByteArrayOutputStream;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;

public class SelectQueries {
		
	public static boolean checkUserRole(String userName , String role){
		
		String strQuery1 = "PREFIX g: <http://learningsparql.com/ns/graphs#>"
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+ "PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
				+ "SELECT (COUNT(*) as ?Found )"
				+ " WHERE"
				+ "{"
				+ "{ GRAPH g:Persons" 
				+ "{ foaf:"+userName+ " a foaf:Person;"
				+ "						iot-liteIns:hasRole \""+role+"\" ."
				+ "			}"
				+ "		}" 
				+ "}";
		Query query1 = QueryFactory.create(strQuery1);

		QueryExecution quex = QueryExecutionFactory.sparqlService(
				"http://localhost:3030/myDataset/query", query1);

		try {
			ResultSet results = quex.execSelect();
			QuerySolution sol = results.next();
			String[] arrResult = sol.toString().split("=");
			String strResult = arrResult[1].substring(1, 2);
			int count = Integer.parseInt(strResult);
		
			return count == 1;
		} finally {
			quex.close();
		}
	}
	
  public static String getPersons(){
	  String strQuery = 
		  		  "PREFIX g: <http://learningsparql.com/ns/graphs#>"
				 +"SELECT ?a ?b ?c {"
				 +" GRAPH g:Persons {"
				 +"  ?a ?b ?c .     "
				 +"}"
				 +" }";
	return	FusekiExecuteQueries.executeSelectQueries(strQuery);
  }
  public static void main(String[] args) {
	System.out.println(checkUserRole("MohamedAhmed","NormalUser"));
}
}
