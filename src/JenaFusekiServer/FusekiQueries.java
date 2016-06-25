package JenaFusekiServer;

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

public class FusekiQueries {

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

	public static void deleteByCondition(String strQuery, String Dataset) {
		UpdateProcessor upp = UpdateExecutionFactory.createRemote(
				UpdateFactory.create(strQuery), "http://localhost:3030/"
						+ Dataset + "/update");
		try {
			upp.execute();
			System.out.println("All triples are deleted");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public static void SelectAllTriples() {
		String SelectQueryString = "SELECT * WHERE {?x ?r ?y}";

		SelectTriplesByConditions(SelectQueryString);
	}

	public static void SelectTriplesByConditions(String strQuery) {
		Query qe = QueryFactory.create(strQuery);

		QueryExecution quex = QueryExecutionFactory.sparqlService(
				"http://localhost:3030/myDataset/query", qe);

		try {
			ResultSet results = quex.execSelect();
			ResultSetFormatter.out(System.out, results);
		} finally {
			quex.close();
		}
	}
		public static void main(String[] args) {
		DropAllGraphs();
		}
}
