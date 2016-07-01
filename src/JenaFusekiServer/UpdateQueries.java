package JenaFusekiServer;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.apache.jena.update.UpdateExecutionFactory;
import org.apache.jena.update.UpdateFactory;
import org.apache.jena.update.UpdateProcessor;

public class UpdateQueries {
	public static void updatePerson(String userName,Hashtable<String, String> htblColNameValue){
		 Set<String> keySet = htblColNameValue.keySet();
		 Iterator<String> itr = keySet.iterator();
		 System.out.println("called");
		 while(itr.hasNext()){
			 String property = itr.next();
			 String value = htblColNameValue.get(property);
			 System.out.println(property+"----->"+ value);
		 }
	}
	public static void executeUpdateQueries(String strQuery) {
		UpdateProcessor updateQuery = UpdateExecutionFactory.createRemote(
				UpdateFactory.create(strQuery),
				"http://localhost:3030/myDataset/update");
		try {
			updateQuery.execute();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
