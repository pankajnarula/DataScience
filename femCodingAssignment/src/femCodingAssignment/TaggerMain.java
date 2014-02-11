package femCodingAssignment;


import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.google.gson.Gson;

//Main Class for Tagging
public class TaggerMain {
	public static void main(String[] args) throws Exception {
		Gson gson = new Gson();
		ParseData[] beginObject = gson.fromJson(new InputStreamReader(new FileInputStream("C:/Users/Vishu/Desktop/Input/CodeAssignmentDataSet.json"),"UTF8"), ParseData[].class);
		
		// Data Set given for given problem is not too big to fit in memory, hence all of them are brought in otherwise they can be brought in object at a time from inputFile 
		for(int i=0;i<beginObject.length;i++){
			String description = beginObject[i].getDescription();
			
			//Can be used in Analysis ahead
			/*String title = beginObject[i].getTitle();
			ArrayList<String> categories =	beginObject[i].getCategories()*/;
			
			//hitting Dbpedia spotlight webservice(online) to get annotated text
			System.out.println(description);
			System.out.println();
			
			//Prints Actors and Films/TV Shows to be tagged
			SpotLightAnnotator.getSpotlightNodes(description);
		}
	}

}
