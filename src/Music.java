import java.io.IOException;
import java.text.DecimalFormat;

import org.json.JSONObject;
import org.json.JSONException;

public class Music extends iTunes{
	
	private String title;
	private String artist;
	private double price;
	private double length;
	
	public Music(String searchTerm) throws IOException, JSONException{
		
		JSONObject json = readJsonFromUrl("https://itunes.apple.com/search?term=" + searchTerm + "&entity=song&limit=1");
		
		this.title = json.getJSONArray("results").getJSONObject(0).getString("trackName");
		this.artist = json.getJSONArray("results").getJSONObject(0).getString("artistName");
		this.price = json.getJSONArray("results").getJSONObject(0).getDouble("trackPrice");
		this.length =  calculateLength(json.getJSONArray("results").getJSONObject(0).getDouble("trackTimeMillis"));
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
	
	public String formatLength() {
		
		DecimalFormat df = new DecimalFormat("#.##");
		length = Double.valueOf(df.format(length));
		String newLength = String.valueOf(length).replace('.',':');
		
		return newLength;
	}
	
	public double calculateLength(double time) throws IOException, JSONException {
		
		time = (time / 1000) / 60;
		double minutes = Math.floor(time);
		double seconds = time - Math.floor(time);
		seconds = seconds * 0.6;
		return Math.round((minutes + seconds) * 100.0) / 100.0;
	}
	
	public String getArtist() throws IOException, JSONException {
		
		return artist;
	}
	
	@Override public String toString() {
		
		return "Title: " + title + 
				"\nArtist: " + artist + 
				"\nPrice: $" + price + 
				"\nLength: " + formatLength();
	}
	
}
