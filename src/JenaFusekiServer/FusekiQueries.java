package JenaFusekiServer;

import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

public class FusekiQueries {
  
	public static void insertNewTriple(String subject , String Property , String Value){
		String queryString = 	"INSERT DATA"
							+ "{ "+subject +" "+Property+" "+Value  +" ."	;	
		
		 UpdateProcessor upp = UpdateExecutionFactory.createRemote(
	                UpdateFactory.create(queryString), 
	                "http://localhost:3030/myDataset/update");
		 try{
			 upp.execute();
			 System.out.println("new triple inserted");
		 }catch(Exception e){
			 System.out.println(e.toString());
		 }
	}
	
	public static void deleteAllTriples (){
		String deleteQueryString = "DELETE  {"
								   + "?a ?b ?c"
								   + "}"
								   +"WHERE {?a ?b ?c}";
		 UpdateProcessor upp = UpdateExecutionFactory.createRemote(
	                UpdateFactory.create(deleteQueryString), 
	                "http://localhost:3030/myDataset/update");
		 try{
			 upp.execute();
			 System.out.println("All triples are deleted");
		 }catch(Exception e){
			 System.out.println(e.toString());
		 }
	}
	
	public static void SelectAllTriples(){
		String SelectQueryString = "SELECT * WHERE {?x ?r ?y}";
		
		SelectTriplesByConditions(SelectQueryString);
	}
	
	public static void SelectTriplesByConditions(String strQuery){
		Query qe = QueryFactory.create(strQuery);
		QueryExecution quex = QueryExecutionFactory.sparqlService("http://localhost:3030/myDataset/query", qe);
		
		try{
			 	ResultSet results = quex.execSelect();
		        ResultSetFormatter.out(System.out, results);
		}finally{
			quex.close();
		}
	}
}
