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
	
	
	
 public static void insertIntoPersonsGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "Persons");
 }
 
 public static void insertIntoApplicationsGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "Applications");
 }
 
 public static void insertIntoPersonUsesApplicationGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "PersonUsesApplication");
 }
	
 public static void insertIntoSystemsGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "Systems");
 }
 
 public static void insertIntoApplicationUsesSystemGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "ApplicationUsesSystem"); 
 }
 
 public static void insertIntoSystemHasSubSystemGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "SystemHasSubSystem");
 }
 
 public static void insertIntoMiniServersGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "MiniServers");
 }
 public static void insertIntoDevicesGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "Devices");
 }
 public static void insertIntoSensingDevicesGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "SensingDevices");
 }
 public static void insertIntoTagDevicesGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "TagDevices");
 }
 
 public static void insertIntoActuatingDevicesGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "ActuatingDevices");
 }
 public static void insertIntoCommunicatingDevicesGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "CommunicatingDevices");
 }
 public static void insertIntoSensorsGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "Sensors");
 }
 public static void insertIntoServicesGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "Services");
 }
 public static void insertIntoDeviceExposedByServiceGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "DeviceExposedByService");
 }
 public static void insertIntoSensorOutputsGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "SensorOutputs");
 }
 public static void insertIntoObjectsGraph(String subject , String Property , String Object , String literal){
	 insertNewTriple(subject, Property, Object, literal, "Objects");
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
			
			 queryString =   "PREFIX d: <http://learningsparql.com/ns/data#>"
							+"INSERT DATA"
					 		+"{ GRAPH d:"+graphName
							+" { <"+subject +"> <"+Property+"> \""+literal+"\" "+" .}"	
					 		+"}";	
		// System.out.println(queryString);
		
		}else{
			if(literal == null){
			 queryString = 		  "PREFIX d: <http://learningsparql.com/ns/data#>"
								 +"INSERT DATA"
								 +"{ GRAPH d:"+graphName
								 + " { <"+subject +"> <"+Property+"> <"+Object+"> .}"
								 +"}";
			//	 System.out.println(queryString);	 
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
