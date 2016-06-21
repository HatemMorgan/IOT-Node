package Ontologies;

import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;

public class IOTLitentologyClasses {
	
	private static final String QU_URI = "http://purl.org/NET/ssnx/qu/qu#";
	private static final String IOT_Lite_URI = "http://purl.oclc.org/NET/UNIS/fiware/iot-lite#";
	private static final String GEO_URI = "http://www.w3.org/2003/01/geo/wgs84_pos#";
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";
	private static final OntModel IOTLiteOntologyModel = OntologyMain.getIOTLiteOntModel();
	
	public static OntClass getObjectClass(){
		OntClass Object = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI+"Object");
		return Object;
	}
   
	public static OntClass getAttributeClass(){
		OntClass Attribute = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI+"Attribute");
		return Attribute;
	}
	
	public static OntClass getServiceClass(){
		OntClass Service = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI+"Service");
		return Service;
	}
	
	public static OntClass getTagDeviceClass(){
		OntClass TagDevice = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI+"TagDevice");
		return TagDevice;
	}
	
	public static OntClass getActuatingDevice(){
		OntClass ActuatingDevice = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI+"ActuatingDevice");
		return ActuatingDevice;
	}
	
	public static OntClass getCoverageClass(){
		OntClass Coverage = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI+"Coverage");
		return Coverage;
	}
	
	public static OntClass getCircleClass(){
		OntClass Circle = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI+"Circle");
		return Circle;
	}
	public static OntClass getPolygonClass(){
		OntClass Polygon = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI+"Polygon");
		return Polygon;
	}
	public static OntClass getRectangleClass(){
		OntClass Rectangle = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI+"Rectangle");
		return Rectangle;
	}
	
	public static OntClass getMetaDataClass(){
		OntClass MetaData = IOTLiteOntologyModel.createClass(IOT_Lite_URI
				+ "Metadata");
		return MetaData;
	}
	
	public static void main(String[] args) {
		System.out.println(getMetaDataClass());
	}
}
