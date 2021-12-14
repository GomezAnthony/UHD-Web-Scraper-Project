import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author anthonygomez
 */
public class AmazonScrapper {
    
    private static String json_data = "";

    AmazonScrapperGui gui;
    
    public AmazonScrapper(AmazonScrapperGui tgui){
        gui = tgui;
    }
   
 
    
    public void urlConnect(String input){
        try{
            URL url = new URL("Your  WEB URL API KEY" + input);	
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();	
            conn.setRequestMethod("GET");
			
            conn.connect();
			
            int response = conn.getResponseCode();
            System.out.println("Response code is: " +response);
				
            if(response != 200)
                throw new RuntimeException("HttpResponseCode: " +response);
            else{
                
                Scanner keyboard = new Scanner(url.openStream());
                while(keyboard.hasNext()){
                    json_data+=keyboard.nextLine();
                }
                keyboard.close();
		}
            
           					
		
		conn.disconnect();
        
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
    }
    
    public String[] getTitle() throws IOException, ParseException{
        
        
        JSONParser parse = new JSONParser();	
        JSONObject jsonObj = (JSONObject)parse.parse(json_data);
        JSONArray jsonArr1 = (JSONArray) jsonObj.get("search_results");
        String data[] = new String[jsonArr1.size()];
			
        for(int i=0; i<jsonArr1.size(); i++){
            
            JSONObject jsonObj1 = (JSONObject) jsonArr1.get(i);			
            data[i] = (String) jsonObj1.get("title");

        }
        
        return data;
    }
    
    public String[] getLink() throws IOException, ParseException{
        
       
        JSONParser parse = new JSONParser();	
        JSONObject jsonObj = (JSONObject)parse.parse(json_data);
        JSONArray jsonArr1 = (JSONArray) jsonObj.get("search_results");
         String data[] = new String[jsonArr1.size()];
			
        for(int i=0; i<jsonArr1.size(); i++){
            
            JSONObject jsonObj1 = (JSONObject) jsonArr1.get(i);			
              data[i] = (String) jsonObj1.get("link");


        }
        return data;
    }
    
    public String[] getRating() throws IOException, ParseException{
        
       
        JSONParser parse = new JSONParser();	
        JSONObject jsonObj = (JSONObject)parse.parse(json_data);
        JSONArray jsonArr1 = (JSONArray) jsonObj.get("search_results");
        String data[] = new String[jsonArr1.size()];
       
			
			
        for(int i=0; i<jsonArr1.size(); i++){
            
            JSONObject jsonObj1 = (JSONObject) jsonArr1.get(i);			
              data[i] = String.valueOf(jsonObj1.get("rating"));


        }
        
        return data;
       

    }
    
    public String[] getRatingTotal() throws IOException, ParseException{
        
       
        JSONParser parse = new JSONParser();	
        JSONObject jsonObj = (JSONObject)parse.parse(json_data);
        JSONArray jsonArr1 = (JSONArray) jsonObj.get("search_results");
         String data[] = new String[jsonArr1.size()];
			
        for(int i=0; i<jsonArr1.size(); i++){
            
            JSONObject jsonObj1 = (JSONObject) jsonArr1.get(i);			
              data[i] = String.valueOf(jsonObj1.get("ratings_total"));


        }
        return data;
    }
        
}