package DBUtils;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import org.json.JSONObject;

public class DBFunctions {

	
    public static String fetchAllDocuments(String collection)
    {
    	String result = "";
    	try {
    		
    	
    	String urlString = Credentials.getAdressString(collection);
    	URL obj = new URL(urlString);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		int responseCode = con.getResponseCode();
		System.out.println("Response Code : " + responseCode);
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		result = response.toString();
		in.close();
		System.out.println(response.toString());
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return result ; 
    }
	
	
	public static boolean updateDocumentOnId(String collection , String documentId ,JSONObject document)
	{
		boolean outcome = false;
		//JSONObject document = generateJsonObject();
		try {
		System.out.println("here");
        String urlString = Credentials.getAdressString(collection,documentId);
        URL url = new URL(urlString);
        HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-type","application/json");
        con.setRequestProperty("Accept","application/json");
        con.setDoOutput(true);
        con.setDoInput(true);
        
        
        String urlParameters = "{\"user\" : \"random\"}";
        System.out.println(urlParameters);
        OutputStreamWriter w = new OutputStreamWriter(con.getOutputStream(), "UTF-8");
        w.write(document.toString());
        w.flush();
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + document.toString());
		System.out.println("Response Code : " + responseCode);
        con.getInputStream();
//		BufferedReader in = new BufferedReader(
//		        new InputStreamReader(con.getInputStream()));
//		String inputLine;
//		StringBuffer response = new StringBuffer();
//
//		while ((inputLine = in.readLine()) != null) {
//			response.append(inputLine);
//		}
//		in.close();

		//print result
		//System.out.println(response.toString());
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return outcome;
	}
}
