package Ontologies;

import java.io.InputStream;

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
	
	public static OntModel getSSNOntModel(){
		if(SSNOntModel == null)
			loadOntology(SSNOntModel,"ssn.owl");
		
		return SSNOntModel;
	}
	
	
	public static OntModel getFOAFOntModel() {
		if (FOAFOntModel == null)
			loadOntology(FOAFOntModel,"foaf.rdf");
		
		return FOAFOntModel;
	}
	
	public static OntModel getIOTLiteOntModel() {
		if (IotLiteOntModel == null)
			loadOntology(IotLiteOntModel,"iot-lite.rdf");
		
		return IotLiteOntModel;
	}
	
	public static void loadOntology(OntModel ontModel,String path) {
		
		OntDocumentManager mgr = new OntDocumentManager();
		OntModelSpec s = new OntModelSpec(OntModelSpec.OWL_MEM);
		s.setDocumentManager(mgr);
		Model m = ModelFactory.createDefaultModel();
		InputStream in = FileManager.get().open(path);

		if (in == null) {
			throw new IllegalArgumentException("File: " + path + " not found");
		}

		m.read(in, null);
		ontModel = ModelFactory.createOntologyModel(s, m);
	}
	
}
