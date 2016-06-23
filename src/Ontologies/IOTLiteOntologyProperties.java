package Ontologies;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;

public class IOTLiteOntologyProperties {
	private static final String QU_URI = "http://purl.org/NET/ssnx/qu/qu#";
	private static final String IOT_Lite_URI = "http://purl.oclc.org/NET/UNIS/fiware/iot-lite#";
	private static final String GEO_URI = "http://www.w3.org/2003/01/geo/wgs84_pos#";
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";
	private static final String SSN_URI = "http://purl.oclc.org/NET/ssnx/ssn#";
	private static final OntModel IOTLiteOntologyModel = OntologyMain
			.getIOTLiteOntModel();

	public static OntProperty hasSubSystem(){
		OntProperty hasSubSystem = IOTLiteOntologyModel.getOntProperty(SSN_URI+"hasSubSystem");
		return hasSubSystem;
	}
	
	public static OntProperty hasAttribute() {
		OntProperty hasAttribute = IOTLiteOntologyModel
				.getOntProperty(IOT_Lite_URI + "hasAttribute");
		return hasAttribute;
	}

	public static OntProperty isAssociatedWith() {
		OntProperty isAssociatedWith = IOTLiteOntologyModel
				.getOntProperty(IOT_Lite_URI + "isAssociatedWith");
		return isAssociatedWith;
	}

	public static OntProperty endPoint() {
		OntProperty endPoint = IOTLiteOntologyModel.getOntProperty(IOT_Lite_URI
				+ "endpoint");
		return endPoint;
	}

	public static OntProperty interfaceDescription() {
		OntProperty interfaceDescription = IOTLiteOntologyModel
				.getOntProperty(IOT_Lite_URI + "interfaceDescription");
		return interfaceDescription;
	}

	public static OntProperty exposedBy() {
		OntProperty exposedBy = IOTLiteOntologyModel
				.getOntProperty(IOT_Lite_URI + "exposedBy");
		return exposedBy;
	}

	public static OntProperty hasQuantityKind() {
		OntProperty hasQuantityKind = IOTLiteOntologyModel
				.getOntProperty(IOT_Lite_URI + "hasQuantityKind");
		return hasQuantityKind;
	}

	public static OntProperty hasUnit() {
		OntProperty hasUnit = IOTLiteOntologyModel.getOntProperty(IOT_Lite_URI
				+ "hasUnit");
		return hasUnit;
	}

	public static OntProperty hasSensingDevice() {
		OntProperty hasSensingDevice = IOTLiteOntologyModel
				.getOntProperty(IOT_Lite_URI + "hasSensingDevice");
		return hasSensingDevice;
	}

	public static OntProperty hasPoint() {
		OntProperty hasPoint = IOTLiteOntologyModel
				.getOntProperty(IOT_Lite_URI + "hasPoint");
		return hasPoint;
	}

	public static OntProperty hasLocation() {
		OntProperty hasLocation = IOTLiteOntologyModel
				.createOntProperty(GEO_URI + "hasLocation");
		return hasLocation;
	}
	public static OntProperty longtitude() {
		OntProperty longtitude = IOTLiteOntologyModel
				.createOntProperty(GEO_URI + "long");
		return longtitude;
	}
	public static OntProperty latitude() {
		OntProperty latitude = IOTLiteOntologyModel
				.createOntProperty(GEO_URI + "lat");
		return latitude;
	}
	
	public static OntProperty hasCoverage(){
		OntProperty hasCoverage = IOTLiteOntologyModel.getOntProperty(IOT_Lite_URI+"hasCoverage");
		return hasCoverage;
	}

}

