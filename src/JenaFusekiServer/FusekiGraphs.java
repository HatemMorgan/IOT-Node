package JenaFusekiServer;

import org.apache.jena.ontology.Individual;
import org.apache.jena.ontology.OntModel;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;
import org.apache.jena.util.iterator.ExtendedIterator;

public class FusekiGraphs {
	
 public static void insertIntoPersonsGraph(OntModel model){
	 insertOntmodel(model, "Persons");
 }
 
 public static void insertIntoApplicationsGraph(OntModel model){
	 insertOntmodel(model, "Applications");
 }
 
 public static void insertIntoPersonUsesApplicationGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "PersonUsesApplication");
 }
	
 public static void insertIntoSystemsGraph(OntModel model){
	 insertOntmodel(model, "Systems");
 }
 
 public static void insertIntoApplicationUsesSystemGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "ApplicationUsesSystem"); 
 }
 
 public static void insertIntoSystemHasSubSystemGraph(OntModel model){
	 insertOntmodel(model, "SystemHasSubSystem");
 }
 
 public static void insertIntoMiniServersGraph(OntModel model){
	 insertOntmodel(model, "MiniServers");
 }
 public static void insertIntoDevicesGraph(OntModel model){
	 insertOntmodel(model, "Devices");
 }
 public static void insertIntoSensingDevicesGraph(OntModel model){
	 insertOntmodel(model, "SensingDevices");
 }
 public static void insertIntoTagDevicesGraph(OntModel model){
	 insertOntmodel(model, "TagDevices");
 }
 
 public static void insertIntoActuatingDevicesGraph(OntModel model){
	 insertOntmodel(model, "ActuatingDevices");
 }
 public static void insertIntoCommunicatingDevicesGraph(OntModel model){
	 insertOntmodel(model, "CommunicatingDevices");
 }
 public static void insertIntoSensorsGraph(OntModel model){
	 insertOntmodel(model, "Sensors");
 }
 public static void insertIntoServicesGraph(OntModel model){
	 insertOntmodel(model, "Services");
 }
 public static void insertIntoDeviceExposedByServiceGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "DeviceExposedByService");
 }
 public static void insertIntoSensorOutputsGraph(OntModel model){
	 insertOntmodel(model, "SensorOutputs");
 }
 public static void insertIntoObjectsGraph(OntModel model){
	 insertOntmodel(model, "Objects");
 }
public static void insertOntmodel(OntModel model,String graphName) {
		
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
					insertNewTriple(subject.toString(),
							property.toString(),null ,Object.toString(),graphName);
				}else{
					
					insertNewTriple(subject.toString(),
							property.toString(), Object.toString(),null,graphName);
				}
			
			}

		}

	}

	public static void insertNewTriple(String subject , String Property , String Object , String literal,String graphName){
		String queryString = "" ;
		if(Object == null){
			
		
		 queryString =   "INSERT DATA"
				 		+"{ GRAPH "+graphName
						+" { <"+subject +"> <"+Property+"> \""+literal+"\" "+" .}"	
				 		+"}";	
		// System.out.println(queryString);
		
		}else{
			if(literal == null){
			 queryString = 		  "INSERT DATA"
								 +"{ GRAPH "+graphName
								 + "{ <"+subject +"> <"+Property+"> <"+Object+"> .}"
								 +"}";
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
}
