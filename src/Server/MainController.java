package Server;

import java.util.Hashtable;

import org.apache.jena.ontology.Individual;

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
	InsertingTriples.insertSensor(sensingDevice, "Tempreture_Sensor","Degree_Celsius", "Tempreture", communicatingDevice, metaDataList);
	InsertingTriples.insertSensor(sensingDevice, "Humidity_Sensor","Percentage","Humidity", communicatingDevice,null);
  }
}
