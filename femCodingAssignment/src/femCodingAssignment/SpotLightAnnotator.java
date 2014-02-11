package femCodingAssignment;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.StringTokenizer;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class SpotLightAnnotator {
final static String server = "http://spotlight.dbpedia.org/rest/";
	
	
	
	public static void getSpotlightNodes(String text) {
		if (text.equals("")){
			return ;
		}
		JsonObject annotation = null;

		try {
			text = URLEncoder.encode(text, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		
		String finalURL = server + "annotate" + "?" + "text=" + text + "&spotter=" + "Default";
		//System.out.println("SPOTLIGHT QUERY: " + finalURL);
		
		try{
			URL url = new URL(finalURL);
	
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Accept", "application/json");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					conn.getInputStream()));
			//System.out.println();
			JsonParser parser = new JsonParser();
			annotation = (JsonObject) parser.parse(br);
			//System.out.println(annotation.toString());
			
				JsonArray resources = annotation.getAsJsonArray("Resources");
				
				for (int i=0;i<resources.size();i++){
					JsonObject o = (JsonObject) resources.get(i);
					String uri = o.get("@URI").getAsString();
					String surfaceForm = o.get("@surfaceForm").getAsString();
					String types = o.get("@types").getAsString();
					StringTokenizer tk1 = new StringTokenizer(types,",");
					StringTokenizer tk2 = new StringTokenizer(types,",");
					StringTokenizer tk3 = new StringTokenizer(types,",");
					boolean anythingFound = false;
					String temp1 = "";
					while(tk1.hasMoreTokens()){
						 temp1 = tk1.nextToken();
						
						if((!anythingFound)&(temp1.contains("Actor")|temp1.contains("actor")|temp1.contains("tv_actor"))){
							//System.out.println("*****temp1 is *********"+temp1); 
							System.out.println("***************Actor is ***********"+surfaceForm);
							//System.out.println("URI is :"+uri);
							System.out.println();
							anythingFound = true;
							break;
						}
						
					}
					String temp2="";
					while(tk2.hasMoreTokens()){
						 temp2 = tk2.nextToken();
						 if((!anythingFound)&temp2.contains("DBpedia:TelevisionShow")){
								//System.out.println("*****temp is *********"+temp2); 
								System.out.println("***************Tag TVshow is for ***********"+surfaceForm);
								//System.out.println("URI is :"+uri);
								System.out.println();
								anythingFound = true;
								break;
							}
					}
					String temp3 = "";
					while(tk3.hasMoreTokens()){
						 temp3 = tk3.nextToken();
						 if((!anythingFound)&(temp3.contains("DBpedia:Film")|temp3.contains("Schema:Movie"))){
								//System.out.println("*****temp is *********"+temp2); 
								System.out.println("***************Tag film is for ***********"+surfaceForm);
								//System.out.println("URI is :"+uri);
								System.out.println();
								anythingFound = true;
								break;
							}
					}
					
					
					
				}
			
			
		
		}
		catch(Exception e){
			e.printStackTrace();
		}

	
		
	}
}
