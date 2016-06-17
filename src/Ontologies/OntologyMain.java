package Ontologies;

import java.io.InputStream;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntModelSpec;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.util.FileManager;

public class OntologyMain {
  private static OntModel model ;
  
  
  public static OntModel getModel (String path){
	  if(model == null)
		  loadOntology(path);
	  
	  return model;
  }
  
  public static void loadOntology(String path){
	  Model m = ModelFactory.createDefaultModel();
	  InputStream in = FileManager.get().open(path);
	  
	  if (in == null) {
			throw new IllegalArgumentException( "File: " + path + " not found");
			}
	  
      m.read(in, null);
      model = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM, m);
  }
}
