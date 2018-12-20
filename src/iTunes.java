import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONObject;
import org.json.JSONException;

public abstract class iTunes {
	
	private String searchTerm;
	
	public String getSearchTerm() {
		
		return searchTerm;
	}
	
	/************Abstract Methods************/
	
	public abstract String getTitle() throws IOException, JSONException;
	
	public abstract double getPrice() throws IOException, JSONException;
	
	public abstract double getLength() throws IOException, JSONException;
	
	/****************************************/

	public static String readAll(Reader rd) throws IOException {
		
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			
			sb.append((char) cp);
		}
		return sb.toString();
	}

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		
		InputStream is = new URL(url).openStream();
		try {
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
    		String jsonText = readAll(rd);
    		JSONObject json = new JSONObject(jsonText);
    		return json;
		} catch (IOException e) {
			System.out.println("I/O Exception Occured.");
			return null;
		} catch (JSONException e) {
			
			System.out.println("JSONException Occured.");
			return null;
		} finally {
			is.close();
		}
		
	}
 
}