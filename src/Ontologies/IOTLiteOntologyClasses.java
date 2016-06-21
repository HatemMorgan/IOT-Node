package Ontologies;

import org.apache.jena.atlas.io.IO;
import org.apache.jena.ontology.OntClass;
import org.apache.jena.ontology.OntModel;

public class IOTLiteOntologyClasses {

	private static final String QU_URI = "http://purl.org/NET/ssnx/qu/qu#";
	private static final String IOT_Lite_URI = "http://purl.oclc.org/NET/UNIS/fiware/iot-lite#";
	private static final String GEO_URI = "http://www.w3.org/2003/01/geo/wgs84_pos#";
	private static final String XSD_URI = "http://www.w3.org/2001/XMLSchema#";
	private static final OntModel IOTLiteOntologyModel = OntologyMain
			.getIOTLiteOntModel();

	public static OntClass object() {
		OntClass Object = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI
				+ "Object");
		return Object;
	}

	public static OntClass attribute() {
		OntClass Attribute = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI
				+ "Attribute");
		return Attribute;
	}

	public static OntClass service() {
		OntClass Service = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI
				+ "Service");
		return Service;
	}

	public static OntClass tagDevice() {
		OntClass TagDevice = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI
				+ "TagDevice");
		return TagDevice;
	}

	public static OntClass actuatingDevice() {
		OntClass ActuatingDevice = IOTLiteOntologyModel
				.getOntClass(IOT_Lite_URI + "ActuatingDevice");
		return ActuatingDevice;
	}

	public static OntClass coverage() {
		OntClass Coverage = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI
				+ "Coverage");
		return Coverage;
	}

	public static OntClass circle() {
		OntClass Circle = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI
				+ "Circle");
		return Circle;
	}

	public static OntClass polygon() {
		OntClass Polygon = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI
				+ "Polygon");
		return Polygon;
	}

	public static OntClass rectangle() {
		OntClass Rectangle = IOTLiteOntologyModel.getOntClass(IOT_Lite_URI
				+ "Rectangle");
		return Rectangle;
	}

	public static OntClass point() {
		OntClass Point = IOTLiteOntologyModel.getOntClass(GEO_URI + "Point");
		return Point;
	}

	public static OntClass unit() {
		OntClass Unit = IOTLiteOntologyModel.getOntClass(QU_URI + "Unit");
		return Unit;
	}

	public static OntClass quantityKind() {
		OntClass QuantityKind = IOTLiteOntologyModel.getOntClass(QU_URI
				+ "QuantityKind");
		return QuantityKind;
	}

}
