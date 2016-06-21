package Ontologies;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;
import org.apache.jena.rdf.model.ModelFactory;

public class IOTLiteInstancesOntologyProperties {
	private static final String iotlins_URI = "http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#";
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";

	private static final OntModel IOTLiteInstancesOntologyModel = ModelFactory
			.createOntologyModel();

	public static OntProperty getHasApplicationName() {
		OntProperty hasApplicationName = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasApplicationName");
		return hasApplicationName;
	}

	public static OntProperty getHasApplicationDescription() {
		OntProperty hasApplicationDescription = IOTLiteInstancesOntologyModel
				.createOntProperty(iotlins_URI + "hasApplicationDescription");
		return hasApplicationDescription;
	}
	
	public static OntProperty getUsesSystemProperty(){
		OntProperty usesSystem = IOTLiteInstancesOntologyModel.createOntProperty(iotlins_URI+"usesSystem");
		return usesSystem;
	}
}
