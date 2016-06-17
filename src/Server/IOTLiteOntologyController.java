package Server;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
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
				FusekiQueries.insertNewTriple(subject.toString(),
						property.toString(), Object.toString());
			}

		}

	}

	public static void main(String[] args) {
	  FusekiQueries.deleteAllTriples();
	}
}
