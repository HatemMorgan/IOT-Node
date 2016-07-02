package Controller;

import java.util.Date;
import java.util.Hashtable;
import java.util.ArrayList;
import JenaFusekiServer.FusekiExecuteQueries;
import org.apache.jena.ontology.Individual;


public class MainController {
	public static int test(){
		ArrayList<Integer> x = new ArrayList<Integer>();
		x.add(new Integer(1));
		x.add(new Integer(2));
		x.add(new Integer(3));
		x.add(new Integer(4));
		
		return (int) x.get(2);
	}
	public static void insertData(){
		 JenaFusekiServer.FusekiExecuteQueries.DropAllGraphs();
		// inseting system and its subSystems
		Individual GUC = InsertingTriples.insertSystem("GUC");
		Individual CBuilding = InsertingTriples.insertSystem("CBuilding");

		// insert subsystem of a system
		InsertingTriples.insertSubSystem(GUC, CBuilding);

		// Inserting miniServer
		Individual miniServer = InsertingTriples.insertMiniServer(
				"C_Ground_miniserver", "C2", "-0.12", "50.01", CBuilding);

		// inserting IOT service that is provided by IOT devices
		Individual service = InsertingTriples.insertService("HealthChecker",
				"http://surrey.ac.uk/sensors/measures/C1_Cafeteria",
				"http://surrey.ac.uk/sensors/measures/C1_Cafeteria");

		// inserting Objects that contains the Devices like a room or building
		Individual C1_Cafeteria = InsertingTriples.insertObject("C1_Cafeteria",
				"C1", "-0.12", "50.01");

		// inserting attribute of the object like measuring tempreture for
		// example
		Individual attribute = InsertingTriples.insertAttribute(
				"Tempreture_and_Humidity", "Tempreture_and_Humidity",
				C1_Cafeteria);

		// inserting Device
		Individual communicatingDeviceMacAddress = InsertingTriples.insertCommunicatingDevice("Bluetooth_Low_Energy_001",
				"Bluetooth Low Energy (BLE)", "10 bits/second",
				"Star_NetworkTopology", "1024 HZ", "100 Watt", "-173.9 dBm/Hz", "2", "6c23548ab568953a", "60%");
		Individual communicatingDeviceMacAddress2 = InsertingTriples.insertCommunicatingDevice("Bluetooth_Low_Energy_002",
				"Bluetooth Low Energy (BLE)", "20 bits/second",
				"Star_NetworkTopology", "1024 HZ", "100 Watt", "-173.9 dBm/Hz", "2", "c23548ab568919ca", "30%");

		Individual sensingDevice = InsertingTriples
				.insertSensingDevice("TempretureHumidityModule",communicatingDeviceMacAddress);
		
		Individual sensingDevice2 = InsertingTriples
				.insertSensingDevice("TempretureHumidityModule",communicatingDeviceMacAddress2);
		
		Individual deviceMacAddress = InsertingTriples.insertDevice("TempHumBLE",
				CBuilding, miniServer, service, communicatingDeviceMacAddress,
				sensingDevice, attribute);
		
		Individual deviceMacAddress2 = InsertingTriples.insertDevice("TempHumBLE",
				CBuilding, miniServer, service, communicatingDeviceMacAddress2,
				sensingDevice2, attribute );

		// inserting Device exposed by service
		InsertingTriples.insertDeviceServiceRelation(deviceMacAddress, service);

		// adding metaData of tempreture sensor
		java.util.Hashtable<String, Object> metaDataList = new java.util.Hashtable<String, Object>();
		metaDataList.put("MetaType", "Resolution");
		metaDataList.put("MetaValue", Integer.valueOf(1024));

		// inserting sensors
		Individual tempretureSensor = InsertingTriples.insertSensor(
				sensingDevice, "Tempreture_Sensor", "Degree_Celsius",
				"Tempreture", metaDataList);
//		
//		Individual tempretureSensor2 = InsertingTriples.insertSensor(
//				sensingDevice2, "Tempreture_Sensor", "Degree_Celsius",
//				"Tempreture", metaDataList);
		
		Individual humiditySensor = InsertingTriples.insertSensor(
				sensingDevice, "Humidity_Sensor", "Percentage", "Humidity",
				 metaDataList);

		// inserting sensor Output data
		InsertingTriples.insertSensorOutputData("Tempreture", tempretureSensor,
				"70",new Date().toString(),sensingDevice);
		InsertingTriples.insertSensorOutputData("Humidity", humiditySensor,
				"20", new Date().toString(),sensingDevice);
		
//		InsertingTriples.insertSensorOutputData("Tempreture", humiditySensor,
//				"40", new Date().toString(),sensingDevice);
//		
//		InsertingTriples.insertSensorOutputData("Tempreture", humiditySensor,
//				"30", new Date().toString(),sensingDevice2);
//		InsertingTriples.insertSensorOutputData("Tempreture", humiditySensor,
//				"20", new Date().toString(),sensingDevice2);
		
		// insert an application
		Individual FoodTesterApp = InsertingTriples
				.insertApplication("FoodTesterApp",
						"Testing food enviroment to insure that the food is healthy to eat");

		// insert application uses system
		InsertingTriples.insertApplicationSystemRelation(FoodTesterApp,
				CBuilding);

		// inserting a person
		Individual HatemMorgan;
		try {
			HatemMorgan = InsertingTriples.insertPerson("HatemMorgan",
					"Hatem", "Morgan", "Male", "27/7/1995",
					"hatemmorgan17@gmail.com", "Admin");
			// insert person uses application
			InsertingTriples.insertPersonApplicationRelation(HatemMorgan,
					FoodTesterApp);
			
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	

		// inserting coverage
		try {
		Individual	coverage = InsertingTriples.insertCoverage("Cafeteria'sFridgeArea",
			deviceMacAddress, "Rectangle");
			// create coverage point
			InsertingTriples.createPoint("SWCorner", "-0.12", "50.01", coverage);
			
			Individual	coverage2 = InsertingTriples.insertCoverage("Cafeteria'sCashierArea",
					deviceMacAddress2, "Rectangle");
					// create coverage point
					InsertingTriples.createPoint("NWCorner", "-0.22", "50.51", coverage2);
					
		} catch (Exception e) {
			e.printStackTrace();
		}

	try {
		Individual MohamedAhmed = InsertingTriples.insertPerson("MohamedAhmed","Mohamed", "Ahmed", "Male","01/01/1990","MohamedAhmed@gmail.com","NormalUser");
		InsertingTriples.insertPersonApplicationRelation(MohamedAhmed,
				FoodTesterApp);
	} catch (Exception e) {
		e.printStackTrace();
	}

	}
	public static void main(String[] args) {
		insertData();
	}
}
