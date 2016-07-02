package JenaFusekiServer;

import java.io.ByteArrayOutputStream;

import org.apache.jena.iri.impl.Main;
import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.util.iterator.ExtendedIterator;

import Ontologies.IOTLiteOntology;

public class FusekiExecuteQueries {

	public static void DropAllGraphs() {
		String deleteQueryString = "DROP ALL";
		UpdateProcessor upp = UpdateExecutionFactory.createRemote(
				UpdateFactory.create(deleteQueryString), "http://localhost:3030/myDataset/update");
		try {
			upp.execute();
			System.out.println("All graphs are dropped");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		
	}

	 public static void executeUpdateAndDeleteQuery(String strQuery){
		 UpdateProcessor updateQuery = UpdateExecutionFactory.createRemote(
					UpdateFactory.create(strQuery),
					"http://localhost:3030/myDataset/update");
			try {
				updateQuery.execute();
			} catch (Exception e) {
				System.out.println(e.toString());
			}
	 }
	 
		public static String executeSelectQueries(String strQuery){
			Query qe = QueryFactory.create(strQuery);

			QueryExecution quex = QueryExecutionFactory.sparqlService(
					"http://localhost:3030/myDataset/query", qe);

			try {
				ResultSet results = quex.execSelect();
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				ResultSetFormatter.outputAsJSON(outputStream, results);
				String json = new String(outputStream.toByteArray());
			   return json;

			} finally {
				quex.close();
			}
		}

}
