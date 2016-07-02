package Ontologies;

import java.util.Hashtable;

public class entitiesOntologyPropertiesPrefixes {
    public static Hashtable<String, String> getPersonPropertiesPrefixes(){
    	Hashtable<String, String> htblPropertyPrefix = new Hashtable<String, String>();

    	htblPropertyPrefix.put("firstName", "foaf");
    	htblPropertyPrefix.put("lastName", "foaf");
    	htblPropertyPrefix.put("birthday", "foaf");
    	htblPropertyPrefix.put("gender", "foaf");
    	htblPropertyPrefix.put("email", "iot-liteIns");
    	htblPropertyPrefix.put("hasRole", "iot-liteIns");  
    	
    	return htblPropertyPrefix;
    }
    
    public static Hashtable<String, String> getApplicationPropertiesPrefixes(){
    	Hashtable<String, String> htblPropertyPrefix = new Hashtable<String, String>();
    	
    	htblPropertyPrefix.put("hasApplicationName", "iot-liteIns");
    	htblPropertyPrefix.put("hasApplicationDescription", "iot-liteIns");
    	
    	return htblPropertyPrefix;
    }
   
    
    public static Hashtable<String, String> getCommunicatingDevicePropertiesPrefixes(){
    	Hashtable<String, String> htblPropertyPrefix = new Hashtable<String, String>();
    	
    	htblPropertyPrefix.put("hasFrequency", "iot-liteIns");
    	htblPropertyPrefix.put("hasNetworkTopology", "iot-liteIns");
    	htblPropertyPrefix.put("hasBandwidth", "iot-liteIns");
    	htblPropertyPrefix.put("hasTransmitPower", "iot-liteIns");
    	htblPropertyPrefix.put("hasSensitivity", "iot-liteIns");
    	htblPropertyPrefix.put("hasNumberOfChannels", "iot-liteIns");
    	htblPropertyPrefix.put("hasDutyCycle", "iot-liteIns");
    	htblPropertyPrefix.put("hasName", "iot-liteIns");
    	htblPropertyPrefix.put("hasType", "iot-liteIns");
    	
    	return htblPropertyPrefix;
    	
    } 
    
    public static Hashtable<String, String> getServicePropertiesPrefixes(){
    	Hashtable<String, String> htblPropertyPrefix = new Hashtable<String, String>();
    	
    	htblPropertyPrefix.put("interfaceDescription", "iot-lite");
    	htblPropertyPrefix.put("endpoint", "iot-lite");

    	
    	return htblPropertyPrefix;
    	
    }  
}
