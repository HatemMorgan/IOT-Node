package Server;

import java.util.Date;
import java.util.Hashtable;

import org.apache.jena.ontology.Individual;

import com.github.andrewoma.dexx.collection.ArrayList;

public class MainController {
	public static void main(String[] args) {

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
		Individual communicatingDevice = InsertingTriples
				.insertCommunicatingDevice("Bluetooth_Low_Energy_001",
						"Bluetooth Low Energy (BLE)", "10 bits/second",
						"Star_NetworkTopology", "1024 HZ", "100 Watt");
		Individual sensingDevice = InsertingTriples
				.insertSensingDevice("TempretureHumidityModule");
		Individual device = InsertingTriples.insertDevice("TempHumBLE",
				CBuilding, miniServer, service, communicatingDevice,
				sensingDevice, attribute);

		// inserting Device exposed by service
		InsertingTriples.insertDeviceServiceRelation(device, service);

		// adding metaData of tempreture sensor
		Hashtable<String, Object> metaDataList = new Hashtable<String, Object>();
		metaDataList.put("MetaType", "Resolution");
		metaDataList.put("MetaValue", Integer.valueOf(1024));

		// inserting sensors
		Individual tempretureSensor = InsertingTriples.insertSensor(
				sensingDevice, "Tempreture_Sensor", "Degree_Celsius",
				"Tempreture", communicatingDevice, metaDataList);
		Individual humiditySensor = InsertingTriples.insertSensor(
				sensingDevice, "Humidity_Sensor", "Percentage", "Humidity",
				communicatingDevice, null);

		// inserting sensor Output data
		InsertingTriples.insertSensorOutputData("Tempreture", tempretureSensor,
				"70", new Date().toString());
		InsertingTriples.insertSensorOutputData("Humidity", humiditySensor,
				"20", new Date().toString());

		// insert an application
		Individual FoodTesterApp = InsertingTriples
				.insertApplication("FoodTesterApp",
						"Testing food enviroment to insure that the food is healthy to eat");

		// insert application uses system
		InsertingTriples.insertApplicationSystemRelation(FoodTesterApp,
				CBuilding);

		// inserting a person
		Individual HatemMorgan = InsertingTriples.insertPerson("HatemMorgan",
				"Hatem", "Morgan", "Male", "27/7/1995",
				"hatemmorgan17@gmail.com", "Admin");

		// insert person uses application
		InsertingTriples.insertPersonApplicationRelation(HatemMorgan,
				FoodTesterApp);

		// inserting coverage
		try {
		Individual	coverage = InsertingTriples.insertCoverage("Cafeteria'sFridgeArea",
					sensingDevice, "Rectangle");
			// create coverage point
			InsertingTriples.createPoint("SWCorner", "-0.12", "50.01", coverage);
		} catch (Exception e) {
			e.printStackTrace();
		}

	

	}
}
