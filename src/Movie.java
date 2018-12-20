import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.json.JSONException;

public class Movie extends iTunes {
	
	private String title;
	private String director;
	private double price;
	private double length;
	
	public Movie(String searchTerm) throws IOException, JSONException{
		
		JSONObject json = readJsonFromUrl("https://itunes.apple.com/search?term=" + searchTerm + "&entity=movie&limit=1");
				
		this.title = json.getJSONArray("results").getJSONObject(0).getString("trackName");
		this.director = json.getJSONArray("results").getJSONObject(0).getString("artistName");
		this.price = json.getJSONArray("results").getJSONObject(0).getDouble("trackPrice");
		this.length =  json.getJSONArray("results").getJSONObject(0).getDouble("trackTimeMillis");
	}
	
	public String getTitle() throws IOException, JSONException {
		
		return title;
	}
	
	public double getPrice() throws IOException, JSONException {
		
		return price;
	}
	
	public double getLength() throws IOException, JSONException {
		
		return length;
	}
	
	public String calculateLength(double time) {
		
		return String.format("%01dh %02dm", TimeUnit.MILLISECONDS.toHours((long)time),
	            TimeUnit.MILLISECONDS.toMinutes((long)time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours((long)time)));
	         
	}
	
	
	public String getDirector() throws IOException, JSONException {
		
		return director;
	}
	
	@Override public String toString() {
		
		return "Title: " + title + 
				"\nDirector: " + director + 
				"\nPrice: $" + price + 
				"\nLength: " + calculateLength(length);
	}
}