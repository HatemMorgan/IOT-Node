package Ontologies;

import java.io.InputStream;

import org.apache.jena.base.Sys;
import org.apache.jena.ontology.OntDocumentManager;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class OntologyMain {
	private static OntModel FOAFOntModel;
	private static OntModel IotLiteOntModel;
	private static OntModel SSNOntModel;
	private static OntModel IOTLiteInstancesOntologyModel ;
	
	
	public static OntModel getIOTLiteInstancesOntModel(){
		if(IOTLiteInstancesOntologyModel == null)
			IOTLiteInstancesOntologyModel = ModelFactory.createOntologyModel();
		
		return IOTLiteInstancesOntologyModel;
	}
	
	
	public static OntModel getSSNOntModel(){
		if(SSNOntModel == null)
			SSNOntModel = loadOntology("ssn.owl");
		
		return SSNOntModel;
	}
	
	
	public static OntModel getFOAFOntModel() {
		if (FOAFOntModel == null)
			FOAFOntModel = loadOntology("FOAF.rdf");
	
		return FOAFOntModel;
	}
	
	public static OntModel getIOTLiteOntModel() {
		if (IotLiteOntModel == null)
			IotLiteOntModel = loadOntology("iot-lite.rdf");
		
		return IotLiteOntModel;
	}
	
	public static OntModel loadOntology(String path) {
		
		OntDocumentManager mgr = new OntDocumentManager();
		OntModelSpec s = new OntModelSpec(OntModelSpec.OWL_MEM);
		s.setDocumentManager(mgr);
		Model m = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(path);

		if (in == null) {
			throw new IllegalArgumentException("File: " + path + " not found");
		}

		m.read(in, null);
		OntModel ontModel = ModelFactory.createOntologyModel(s, m);
		return ontModel;
	}

}
