package JenaFusekiServer;

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

	public static void insertOntmodel(OntModel model) {
		
		ExtendedIterator<Individual> individuals = model
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
					//System.out.println("here "+Object.asLiteral());
					FusekiQueries.insertNewTriple(subject.toString(),
							property.toString(),null ,Object.toString());
				}else{
					
					FusekiQueries.insertNewTriple(subject.toString(),
							property.toString(), Object.toString(),null);
				}
			
			}

		}

	}

	public static void insertNewTriple(String subject , String Property , String Object , String literal){
		String queryString = "" ;
		if(Object == null){
			
		
		 queryString = 	"INSERT DATA"
							+ "{ <"+subject +"> <"+Property+"> \""+literal+"\" "+" .}"	;	
		// System.out.println(queryString);
		
		}else{
			if(literal == null){
				 queryString = 	"INSERT DATA"
						+ "{ <"+subject +"> <"+Property+"> <"+Object+"> .}"	;	
				// System.out.println(queryString);	 
			}
		}
		
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
		deleteByCondition(deleteQueryString, "myDataset");
	}
	
	public static void deleteByCondition(String strQuery , String Dataset){
		 UpdateProcessor upp = UpdateExecutionFactory.createRemote(
	                UpdateFactory.create(strQuery), 
	                "http://localhost:3030/"+Dataset+"/update");
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
