package Server;

import java.util.Iterator;

import org.apache.jena.graph.Triple;
import org.apache.jena.ontology.Individual;
import org.apache.jena.query.Query;
import org.apache.jena.query.QueryExecution;
import org.apache.jena.query.QueryExecutionFactory;
import org.apache.jena.query.QueryFactory;
import org.apache.jena.query.QuerySolution;
import org.apache.jena.query.ResultSet;
import org.apache.jena.query.ResultSetFormatter;
import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

public class StaticQueries {
	/*
	 * 2-A normal user should view (Generic) data of the sensor and their
	 * location.
	 */
	public static void SelectSensors(String email) {
		// check if this normaluser exsists
		String strQuery1 = "PREFIX g: <http://learningsparql.com/ns/graphs#>"
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+ "PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
				+ "SELECT (COUNT(*) as ?Found ) WHERE" + "{"
				+ "{ GRAPH g:Persons" + "{" + "?Person a foaf:Person;"
				+ "iot-liteIns:email \"" + email + "\" ;"
				+ "iot-liteIns:hasRole \"NormalUser\" ." + "" + "}" + "}" + "}";
		Query query1 = QueryFactory.create(strQuery1);

		QueryExecution quex = QueryExecutionFactory.sparqlService(
				"http://localhost:3030/myDataset/query", query1);

		try {
			ResultSet results = quex.execSelect();
			QuerySolution sol = results.next();
			String[] arrResult = sol.toString().split("=");
			String strResult = arrResult[1].substring(1, 2);
			int count = Integer.parseInt(strResult);
			if (count == 1) {

				String strQuery2 = "PREFIX g: <http://learningsparql.com/ns/graphs#>"
						+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
						+ "PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
						+ "PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
						+ "PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
						+ "PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
						+ "SELECT ?Sensor ?hasSensingDevice ?hasCommunicatingDevice ?hasUnit ?hasQuantityKind"
						+ " ?LocationName ?longtitude ?latitude "
						+ " WHERE"
						+ " {"
						+ "  GRAPH g:Sensors"
						+ " {"
						+ "  ?Sensor a ssn:Sensor; "
						+ "			iot-lite:hasSensingDevice ?hasSensingDevice;"
						+ "           iot-liteIns:hasCommunicatingDevice ?hasCommunicatingDevice;"
						+ "  		    iot-lite:hasUnit ?hasUnit;"
						+ "      	    iot-lite:hasQuantityKind ?hasQuantityKind. "
						+ "	} "
						+ "    GRAPH g:Devices{ "
						+ "      ?device ssn:hasSubSystem ?hasSensingDevice ."
						+ "      ?attribute iot-lite:isAssociatedWith ?device."
						+ "    } "
						+ "    "
						+ "    GRAPH g:Objects{ "
						+ "      ?object iot-lite:hasAttribute ?attribute;"
						+ "              geo:hasLocation ?LocationName."
						+ "      ?LocationName geo:long ?longtitude;"
						+ "                    geo:lat  ?latitude."
						+ "    }"
						+ "   }";
				Query query2 = QueryFactory.create(strQuery2);
				QueryExecution quex2 = QueryExecutionFactory.sparqlService(
						"http://localhost:3030/myDataset/query", query2);
				try {
					ResultSet results2 = quex2.execSelect();
					ResultSetFormatter.out(System.out, results2);
				} finally {
					quex.close();
				}
				// try{
				// Iterator<Triple> x = quex.execConstructTriples();
				// System.out.println(x.hasNext());
				// while(x.hasNext()){
				// Triple t = x.next();
				// System.out.println(t.getSubject().toString()+"  "+t.getPredicate().toString()+"  "+t.getObject());
				// }
				// }finally{
				// quex.close();
				// }

			}
		} finally {
			quex.close();
		}

	}

	public static void normalUserViewCommunicatingDeviceOfaSensor(String email,
			String DeviceName) {
		// check if this normaluser exsists
		String strQuery1 = "PREFIX g: <http://learningsparql.com/ns/graphs#>"
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+ "PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
				+ "SELECT (COUNT(*) as ?Found ) WHERE" + "{"
				+ "{ GRAPH g:Persons" + "{" + "?Person a foaf:Person;"
				+ "iot-liteIns:email \"" + email + "\" ;"
				+ "iot-liteIns:hasRole \"NormalUser\" ." + "" + "}" + "}" + "}";
		Query query1 = QueryFactory.create(strQuery1);

		QueryExecution quex = QueryExecutionFactory.sparqlService(
				"http://localhost:3030/myDataset/query", query1);

		try {
			ResultSet results = quex.execSelect();
			QuerySolution sol = results.next();
			String[] arrResult = sol.toString().split("=");
			String strResult = arrResult[1].substring(1, 2);
			int count = Integer.parseInt(strResult);
			if (count == 1) {
				String strQuery2 = "PREFIX g: <http://learningsparql.com/ns/graphs#>"
						+ "PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
						+ "PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
						+ "SELECT ?communicatingDeviceName ?type ?Bandwidth ?networkTopology "
						+ " ?frequency ?transmitPower WHERE "
						+ "{"
						+ "GRAPH g:Devices"
						+ "{"
						+ " ssn:"
						+ DeviceName
						+ " ssn:hasSubSystem ?communicatingDeviceName. "
						+ "}"
						+ "GRAPH g:CommunicatingDevices{"
						+ "  ?communicatingDeviceName iot-liteIns:hasBandwidth ?Bandwidth;"
						+ "                           iot-liteIns:hasFrequency ?frequency;"
						+ "                           iot-liteIns:hasNetworkTopology ?networkTopology;"
						+ "                           iot-liteIns:hasTransmitPower ?transmitPower;"
						+ "                        	  iot-liteIns:hasType ?type ."
						+ "	}" + "}";

				Query query2 = QueryFactory.create(strQuery2);
				QueryExecution quex2 = QueryExecutionFactory.sparqlService(
						"http://localhost:3030/myDataset/query", query2);
				try {
					ResultSet results2 = quex2.execSelect();
					ResultSetFormatter.out(System.out, results2);
				} finally {
					quex.close();
				}

			}
		} finally {
			quex.close();
		}
	}

	public static void adminCanChangeCommunicatingDeviceOFaSensor(
			String communicatingDeviceName, String type, String bandwidth,
			String networkTopology, String frequency, String transmitPower,
			String DeviceName, String email) {

		String strQuery1 = "PREFIX g: <http://learningsparql.com/ns/graphs#>"
				+ "PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+ "PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
				+ "SELECT (COUNT(*) as ?Found ) WHERE" + "{"
				+ "{ GRAPH g:Persons" + "{" + "?Person a foaf:Person;"
				+ "iot-liteIns:email \"" + email + "\" ;"
				+ "iot-liteIns:hasRole \"Admin\" ." + "" + "}" + "}" + "}";

		Query query1 = QueryFactory.create(strQuery1);

		QueryExecution quex = QueryExecutionFactory.sparqlService(
				"http://localhost:3030/myDataset/query", query1);

		try {
			ResultSet results = quex.execSelect();
			QuerySolution sol = results.next();
			String[] arrResult = sol.toString().split("=");
			String strResult = arrResult[1].substring(1, 2);
			int count = Integer.parseInt(strResult);
			if (count == 1) {
				String strQuery2 = " PREFIX g: <http://learningsparql.com/ns/graphs#>"
						+ "PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
						+ "PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
						+ "PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
						+ "DELETE{"
						+ "GRAPH g:CommunicatingDevices "
						+ "{"
						+ "?communicatingDeviceName a iot-liteIns:CommunicatingDevice;"
						+ "				iot-liteIns:hasBandwidth ?bandwidth;"
						+ "				iot-liteIns:hasFrequency ?frequency;"
						+ "				iot-liteIns:hasNetworkTopology ?networkTopology;"
						+ "				iot-liteIns:hasTransmitPower ?transmitPower;"
						+ "				iot-liteIns:hasType ?type."
						+ "	} "
						+ "	} "
						+ "INSERT { "
						+ "GRAPH g:CommunicatingDevices "
						+ "{ "
						+ "iot-liteIns:"
						+ communicatingDeviceName
						+ " a iot-liteIns:CommunicatingDevice;"
						+ "		 iot-liteIns:hasBandwidth \""
						+ bandwidth
						+ "\";"
						+ "       iot-liteIns:hasFrequency \""
						+ frequency
						+ "\";"
						+ "		 iot-liteIns:hasNetworkTopology \""
						+ networkTopology
						+ "\";"
						+ "		 iot-liteIns:hasTransmitPower \""
						+ transmitPower
						+ "\";"
						+ "	     iot-liteIns:hasType \""
						+ type
						+ "\"."
						+ "}"
						+ "}"
						+ "WHERE{ "
						+ "GRAPH g:Devices "
						+ "{ "
						+ "ssn:"
						+ DeviceName
						+ " ssn:hasSubSystem ?communicatingDeviceName."
						+ "} "
						+ "GRAPH g:CommunicatingDevices"
						+ "{"
						+ "?communicatingDeviceName a iot-liteIns:CommunicatingDevice;"
						+ "				iot-liteIns:hasBandwidth ?bandwidth;"
						+ "				iot-liteIns:hasFrequency ?frequency;"
						+ "				iot-liteIns:hasNetworkTopology ?networkTopology;"
						+ "				iot-liteIns:hasTransmitPower ?transmitPower;"
						+ "				iot-liteIns:hasType ?type." + "}" + "}";

				UpdateProcessor upp = UpdateExecutionFactory.createRemote(
						UpdateFactory.create(strQuery2),
						"http://localhost:3030/myDataset/update");
				try {
					upp.execute();
				    
				} catch (Exception e) {
					System.out.println(e.toString());
				}

			} else {
				System.out
						.println("This user does not have the privilege to change communicatingDevice of this device");
			}

		} finally {
			quex.close();
		}
	}

	public static void addnewDevice(String DeviceName, Individual system,
			Individual miniServer, Individual service,
			Individual CommunicatingDevice, Individual sensingDevice,
			Individual attribute) {

		InsertingTriples.insertDevice(DeviceName, system, miniServer, service,
				CommunicatingDevice, sensingDevice, attribute);
	}

	
	public static void deleteExsistingDevice(String deviceName){
		
	String strQuery = 	
		"PREFIX ssn: <http://purl.oclc.org/NET/ssnx/ssn#>"
		+"PREFIX iot-liteIns:<http://purl.oclc.org/NET/UNIS/iot-lite/iot-liteInstance#>"
		+"PREFIX iot-lite:<http://purl.oclc.org/NET/UNIS/fiware/iot-lite#>"
		+"PREFIX geo: <http://www.w3.org/2003/01/geo/wgs84_pos#>"
		+"PREFIX g: <http://learningsparql.com/ns/graphs#>"
		+"DELETE  { "
		+" GRAPH g:Devices "
		+"  { "
		+"ssn:"+deviceName+" a ssn:Device;"
		+"    			     ssn:hasSubSystem ?communicatingDevice;"
		+"  				 ssn:hasSubSystem ?sensingDevice;"
		+"    			     iot-liteIns:isConnectedTo ?miniserver;"
		+"  				 iot-lite:exposedBy ?service."
		+"  ?attribute  	 iot-lite:isAssociatedWith  ssn:"+deviceName+"."
		+"  ?system  		 ssn:hasSubSystem ssn:"+deviceName+"."
		+"  ?communicatingDevice a iot-liteIns:CommunicatingDevice."
		+"  ?sensingDevice a ssn:SensingDevice ."
		+"  ssn:"+deviceName+" iot-lite:hasCoverage ?coverage."
		+"  ?coverage a ?coverageClass ."
		+"  ?coverage iot-lite:hasPoint ?point."
		+"  ?point a geo:Point ;"
		+"     	 geo:lat ?latitude;"
		+"    	 geo:long ?longtitude ."
		+"  		    } "
		+"}"
		+" GRAPH g:SensingDevices {"
		+"	   ?sensingDevice a ssn:SensingDevice ."
		+"	  } "
		+"WHERE{"
		+"GRAPH g:Devices "
		+"  {"
		+" ssn:"+deviceName+" a ssn:Device;"
		+"    			     ssn:hasSubSystem ?communicatingDevice;"
		+"  				 ssn:hasSubSystem ?sensingDevice;"
		+"    			     iot-liteIns:isConnectedTo ?miniserver;"
		+"   				 iot-lite:exposedBy ?service."
		+"  ?attribute  	 iot-lite:isAssociatedWith  ssn:"+deviceName+"."
		+"  ?system  		 ssn:hasSubSystem ssn:"+deviceName+"."
		+"  ?communicatingDevice a iot-liteIns:CommunicatingDevice."
		+" ?sensingDevice a ssn:SensingDevice ."
				+ "  ssn:"
				+ deviceName
				+ " iot-lite:hasCoverage ?coverage."
		+"  ?coverage a ?coverageClass ."
		+" ?coverage iot-lite:hasPoint ?point."
		+"  ?point  a  geo:Point;"
		+"          geo:lat ?latitude;"
		+"          geo:long ?longtitude ."
		+"		 }"
		+"}";
	UpdateProcessor upp2 = UpdateExecutionFactory.createRemote(
			UpdateFactory.create(strQuery),
			"http://localhost:3030/myDataset/update");
	try {
		upp2.execute();
	} catch (Exception e) {
		System.out.println(e.toString());
	}
}
	
	public static void main(String[] args) {
	   // SelectSensors("MohamedAhmed@gmail.com");
	   normalUserViewCommunicatingDeviceOfaSensor("MohamedAhmed@gmail.com","TempHumBLE");
//		adminCanChangeCommunicatingDeviceOFaSensor("Zigbee", "Zigbee",
//				"50 bits/second", "Mesh", "1024 HZ", "100 Watt", "TempHumBLE",
//				"hatemmorgan17@gmail.com");
//	   deleteExsistingDevice("TempHumBLE");
	}
}
