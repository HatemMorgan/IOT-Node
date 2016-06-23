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
		
	  
	  // get the union of two datasets
		
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
	     //   ResultSetFormatter.out(System.out, results);
		 	
		     ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

		     ResultSetFormatter.outputAsJSON(outputStream, results);

		     // and turn that into a String
		     String json = new String(outputStream.toByteArray());

		//     System.out.println(json);
	}finally{
		quex.close();
	}
		
		// Delete from users dataset 
		// delete all books that are created by Hatem Morgan 
		String DeleteQuery = "PREFIX dc: <http://purl.org/dc/elements/1.1/> "
							+"	DELETE {?id dc:title ?book} "
							+" WHERE {?id  dc:creator \"Hatem Morgan\";"
							+"			   dc:title ?book .}"	;	
		
		
	//	FusekiQueries.deleteByCondition(DeleteQuery, "NewDataset");
						
	// get all the systems in the database
		String q1 = "PREFIX iot-lite: <http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
					+"PREFIX qu:<http://purl.org/NET/ssnx/qu/qu#>"
					+"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
					+"PREFIX iotlins:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
					+"SELECT * "
					+"WHERE{"
					+"	?a a ssn:System ."
					+"}";
		//FusekiQueries.SelectTriplesByConditions(q1);
		
		// get all the sensors used for the system 
		String q2 = "PREFIX iot-lite: <http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
					+"PREFIX qu:<http://purl.org/NET/ssnx/qu/qu#>"
					+"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
					+"PREFIX iotlins:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
					+"SELECT ?sensor "
					+"WHERE{"
					+"	?a a ssn:SensingDevice ."
					+"    ?sensor ?hasSensingDevice ?a"
					  	
					+"}";	
	  FusekiQueries.SelectTriplesByConditions(q2);
		
		// get the number of sensors used in the system
		String q3 = "PREFIX iot-lite: <http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
				+"PREFIX qu:<http://purl.org/NET/ssnx/qu/qu#>"
				+"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
				+"PREFIX iotlins:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
				+"SELECT (COUNT(*) as ?NumberOfSensors )"
				+"WHERE{"
				+"	?a a ssn:SensingDevice ."
				+"    ?sensor ?hasSensingDevice ?a"
				  	
				+"}";	
	//FusekiQueries.SelectTriplesByConditions(q3);
		
	}
}
