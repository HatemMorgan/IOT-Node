package Server;

import java.util.Date;
import java.util.Hashtable;

import org.apache.jena.ontology.Individual;

import com.github.andrewoma.dexx.collection.ArrayList;

public class MainController {
  public static void main(String[] args) {
	  
	// inseting system and its subSystems  
	Individual newSystem = InsertingTriples.insertSystem("GUC");
	Individual subSystem = InsertingTriples.insertSubSystem(newSystem, "CBuilding");
	
	// Inserting miniServer	
	Individual miniServer =InsertingTriples.insertMiniServer("C_Ground_miniserver", subSystem,"C2", "-0.12", "50.01");
	
	// inserting IOT service that is provided by  IOT devices 
	Individual service = InsertingTriples.insertService("HealthChecker", "http://surrey.ac.uk/sensors/measures/C1_Cafeteria","http://surrey.ac.uk/sensors/measures/C1_Cafeteria");

    
	// inserting Device 
	Individual device = InsertingTriples.insertDevice(subSystem, "TempHumBLE", miniServer, service);
	Individual communicatingDevice = InsertingTriples.insertCommunicatingDevice(device,"Bluetooth_Low_Energy_001","Bluetooth Low Energy (BLE)", "10 bits/second","Star_NetworkTopology", "1024 HZ", "100 Watt");
	Individual sensingDevice = InsertingTriples.insertSensingDevice(device, "TempretureHumidityModule");
	
	// adding metaData of tempreture sensor 
	 Hashtable<String, Object> metaDataList = new Hashtable<String, Object>();
	 metaDataList.put("MetaType","Resolution");
	 metaDataList.put("MetaValue",Integer.valueOf(1024));
	 
	// inserting sensors	
	Individual tempretureSensor = InsertingTriples.insertSensor(sensingDevice, "Tempreture_Sensor","Degree_Celsius", "Tempreture", communicatingDevice, metaDataList);
	Individual humiditySensor = InsertingTriples.insertSensor(sensingDevice, "Humidity_Sensor","Percentage","Humidity", communicatingDevice,null);
	
	// inserting attribute of the object like measuring tempreture for example
	Individual attribute = InsertingTriples.insertAttribute("Tempreture_and_Humidity", sensingDevice, "Tempreture_and_Humidity");

	
	// inserting Objects that contains the Devices like a room or building
	InsertingTriples.insertObject("C1_Cafeteria", attribute,"C1", "-0.12", "50.01");
	
	//inserting sensor Output data 
	InsertingTriples.insertSensorOutputData("Tempreture",tempretureSensor, "70",new Date().toString());
	InsertingTriples.insertSensorOutputData("Humidity",humiditySensor, "20",new Date().toString());
	
	//insert an application 
	Individual application = InsertingTriples.insertApplication("FoodTesterApp","Testing food enviroment to insure that the food is healthy to eat",subSystem);
	
	//inserting a person 
	 InsertingTriples.insertPerson("HatemMorgan","Hatem", "Morgan", "Male", "27/7/1995", application, "hatemmorgan17@gmail.com", "Admin");
	 
	//create coverage point 
	Individual point = InsertingTriples.createPoint("SWCorner","-0.12", "50.01");
	ArrayList<Individual> points = new ArrayList<Individual>();
	points.append(point);
	
	// inserting coverage 
	 try {
		InsertingTriples.insertCoverage("Cafeteria'sFridgeArea", sensingDevice,"Rectangle", points);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
  }
}
