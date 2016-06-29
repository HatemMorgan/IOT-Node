package Server;

import java.util.Date;
import java.util.Hashtable;

import org.apache.jena.ontology.Individual;

import JenaFusekiServer.FusekiGraphs;
import JenaFusekiServer.FusekiQueries;

import com.github.andrewoma.dexx.collection.ArrayList;

public class MainController {
	public static void main(String[] args) {
		FusekiQueries.DropAllGraphs();
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

		Individual sensingDevice = InsertingTriples
				.insertSensingDevice("TempretureHumidityModule",communicatingDeviceMacAddress);
		Individual deviceMacAddress = InsertingTriples.insertDevice("TempHumBLE",
				CBuilding, miniServer, service, communicatingDeviceMacAddress,
				sensingDevice, attribute ,"6c23548ab568953a");

		// inserting Device exposed by service
		InsertingTriples.insertDeviceServiceRelation(deviceMacAddress, service);

		// adding metaData of tempreture sensor
		Hashtable<String, Object> metaDataList = new Hashtable<String, Object>();
		metaDataList.put("MetaType", "Resolution");
		metaDataList.put("MetaValue", Integer.valueOf(1024));

		// inserting sensors
		Individual tempretureSensor = InsertingTriples.insertSensor(
				sensingDevice, "Tempreture_Sensor", "Degree_Celsius",
				"Tempreture", metaDataList);
		Individual humiditySensor = InsertingTriples.insertSensor(
				sensingDevice, "Humidity_Sensor", "Percentage", "Humidity",
				 metaDataList);

		// inserting sensor Output data
		InsertingTriples.insertSensorOutputData("Tempreture", tempretureSensor,
				"70", new Date().toString(),sensingDevice);
		InsertingTriples.insertSensorOutputData("Humidity", humiditySensor,
				"20", new Date().toString(),sensingDevice);

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
				InsertingTriples.getDevice(), "Rectangle");
			// create coverage point
			InsertingTriples.createPoint("SWCorner", "-0.12", "50.01", coverage);
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
}
