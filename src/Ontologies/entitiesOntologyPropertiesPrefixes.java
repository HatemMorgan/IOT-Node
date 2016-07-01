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
}
