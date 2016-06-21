package Ontologies;

import org.apache.jena.ontology.OntModel;
import org.apache.jena.ontology.OntProperty;

public class IOTLiteOntologyProperties {
	private static final String QU_URI = "http://purl.org/NET/ssnx/qu/qu#";
	private static final String IOT_Lite_URI = "http://purl.oclc.org/NET/UNIS/fiware/iot-lite#";
	private static final String GEO_URI = "http://www.w3.org/2003/01/geo/wgs84_pos#";
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";
	private static final OntModel IOTLiteOntologyModel = OntologyMain.getIOTLiteOntModel();
	
	public static OntProperty getHasAttributeProperty(){
		OntProperty hasAttribute = IOTLiteOntologyModel.getOntProperty(IOT_Lite_URI+"hasAttribute");
		return hasAttribute;
	}
	
	public static OntProperty getIsAssociatedWithProperty(){
		OntProperty isAssociatedWith = IOTLiteOntologyModel.getOntProperty(IOT_Lite_URI+"isAssociatedWith");
		return isAssociatedWith;
	}
	
	public static OntProperty getEndPointProperty(){
		OntProperty endPoint = IOTLiteOntologyModel.getOntProperty(IOT_Lite_URI+"endpoint");
		return endPoint;
	}
	
	public static OntProperty getInterfaceDescriptionProperty(){
		OntProperty interfaceDescription = IOTLiteOntologyModel.getOntProperty(IOT_Lite_URI+"interfaceDescription");
		return interfaceDescription;
	}
	
	public static OntProperty getExposedByProperty(){
		OntProperty exposedBy = IOTLiteOntologyModel.getOntProperty(IOT_Lite_URI+"exposedBy");
		return exposedBy;
	}
	
	public static OntProperty getHasQuantityKind(){
		OntProperty hasQuantityKind = IOTLiteOntologyModel.getOntProperty(IOT_Lite_URI+"hasQuantityKind");
		return hasQuantityKind;
	}
	
	public static OntProperty getHasUnit(){
		OntProperty hasUnit = IOTLiteOntologyModel.getOntProperty(IOT_Lite_URI+"hasUnit");
		return hasUnit;
	}
	
	public static OntProperty getHasSensingDeviceProperty(){
		OntProperty hasSensingDevice = IOTLiteOntologyModel.getOntProperty(IOT_Lite_URI+"hasSensingDevice");
		return hasSensingDevice;
	}
	
	public static OntProperty getHasPoint(){
		OntProperty hasPoint  = IOTLiteOntologyModel.createOntProperty(IOT_Lite_URI+"hasPoint");
		return hasPoint;
	}
	

}
