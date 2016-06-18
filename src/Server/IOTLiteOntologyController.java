package Server;

import java.io.ByteArrayOutputStream;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
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
import org.apache.jena.util.iterator.ExtendedIterator;

import JenaFusekiServer.FusekiQueries;
import Ontologies.IOTLiteOntology;

public class IOTLiteOntologyController {
	public static void insertNewSystem() {
		OntModel IOTLiteOntologyModel = IOTLiteOntology.createSystem();
		ExtendedIterator<Individual> individuals = IOTLiteOntologyModel
				.listIndividuals();

		// Iterates trough the list
		while (individuals.hasNext()) {
			Individual individual = individuals.next();
			// Retrieves the URI - identifier of the individual
			String label = individual.getURI();

			// Retrieves the class name of the individual
			String type = individual.getOntClass().getLocalName();

			StmtIterator itr = individual.listProperties();
			while (itr.hasNext()) {
				Statement statment = itr.nextStatement();
				Resource subject = statment.getSubject();
				Property property = statment.getPredicate();
		
				
				RDFNode Object = statment.getObject();
				//System.out.println(subject +" "+property+" "+Object);
				if(Object.isLiteral()){
					System.out.println("here "+Object.asLiteral());
					FusekiQueries.insertNewTriple(subject.toString(),
							property.toString(),null ,Object.toString());
				}else{
					
					FusekiQueries.insertNewTriple(subject.toString(),
							property.toString(), Object.toString(),null);
				}
			
			}

		}

	}

	public static void main(String[] args) {
//	  FusekiQueries.deleteAllTriples();
//	  insertNewSystem();
//	  FusekiQueries.SelectAllTriples();
		
		String strQuery =" SELECT * "
				+"FROM <http://localhost:3030/myDataset> "
				+"FROM NAMED <http://localhost:3030/NewDataset> "
				+"WHERE {"
				+ "{?a ?b ?c . } "
				+"UNION "
				+"{GRAPH<http://localhost:3030/NewDataset> {?d ?e ?f . }}"
				+ "}";
		Query qe = QueryFactory.create(strQuery);
		QueryExecution quex = QueryExecutionFactory.create(qe);
		try{
		 	ResultSet results = quex.execSelect();
	       // ResultSetFormatter.out(System.out, results);
		 	
		     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		     ResultSetFormatter.outputAsJSON(outputStream, results);

		     // and turn that into a String
		     String json = new String(outputStream.toByteArray());

		     System.out.println(json);
	}finally{
		quex.close();
	}
		
		// Delete from users dataset 
		// delete all books that are created by Hatem Morgan 
		String DeleteQuery = "PREFIX dc: <http://purl.org/dc/elements/1.1/> "
							+"	DELETE {?id dc:title ?book} "
							+" WHERE {?id  dc:creator \"Hatem Morgan\";"
							+"			   dc:title ?book .}"	;	
		
		
		FusekiQueries.deleteByCondition(DeleteQuery, "NewDataset");
						
		
	}
}
