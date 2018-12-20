import java.util.ArrayList;
import java.io.PrintWriter;

import org.json.JSONException;

import java.io.IOException;

public class Cart implements Inventory {

	private static int numItems;

	private ArrayList<Music> MusicList = new ArrayList<Music>();
	private ArrayList<Movie> MovieList = new ArrayList<Movie>();

	public Cart() {

		numItems = 0;
	}

	public void add(Music mu) {

		MusicList.add(mu);
		numItems++;
	}

	public void add(Movie mo) {

		MovieList.add(mo);
		numItems++;
	}

	public void delete(String title) throws IOException {

		if(searchMusic(title) == -1 && searchMovie(title) == -1) {

			System.out.println("Please enter a valid title!");
		} else if(searchMusic(title) != -1) {

			MusicList.remove(searchMusic(title));
			numItems--;
			System.out.println("Media removed.");
		} else {

			MovieList.remove(searchMovie(title));
			numItems--;
			System.out.println("Media removed.");
		}
	}

	public int searchMusic(String title) throws IOException, JSONException {

		for (int i = 0; i < MusicList.size(); i++) {

			if(MusicList.get(i).getTitle().equals(title)) {

				return i;
			}
		}

		return -1;
	}

	public int searchMovie(String title) throws IOException, JSONException {

		for (int i = 0; i < MovieList.size(); i++) {

			if(MovieList.get(i).getTitle().equals(title)) {

				return i;
			}
		}

		return -1;
	}

	public boolean isCopy(String title) throws IOException {

		if(searchMovie(title) != -1 || searchMusic(title) != -1) {

			return true;
		} else {

			return false;
		}
	}

	public Music getMusic(int index) {

		return MusicList.get(index);
	}

	public Movie getMovie(int index) {

		return MovieList.get(index);
	}

	public static int getNumItems() {

		return numItems;
	}

	public boolean isEmpty() {

		return numItems == 0;
	}


	public void checkout() throws IOException {

		PrintWriter writer = new PrintWriter("cart.txt");

		for(int i = 0; i < MusicList.size(); i++) {

			writer.println(MusicList.get(i).toString());
			writer.println("music");
		}

		for(int i = 0; i < MovieList.size(); i++) {

			writer.println(MovieList.get(i).toString());
			writer.println("movie");
		}

		writer.close();

		while(!MusicList.isEmpty()) {

			MusicList.remove(0);
		}

		while(!MovieList.isEmpty()) {

			MovieList.remove(0);
		}

		numItems = 0;

	}

	public void displayCart(boolean premium) throws IOException {

		StringBuilder combined = new StringBuilder();
		double totalPrice = 0;

		if(numItems == 0) {

			combined.append("\nYour cart is empty!");
		} else {

			combined.append("\n\nCart:\n");


			if(MusicList.size() != 0) {

				combined.append("\n**** Music ****\n\n");

				for(int i = 0; i < MusicList.size(); i++) {

					combined.append(MusicList.get(i).toString());
					totalPrice += MusicList.get(i).getPrice();
					combined.append("\n\n");
				}
			}

			if(MovieList.size() != 0) {

				combined.append("\n**** Movies ****\n\n");

				for(int i = 0; i < MovieList.size(); i++) {

					combined.append(MovieList.get(i).toString());
					totalPrice += MovieList.get(i).getPrice();
					combined.append("\n\n");
				}
			}

			if (premium == true)
			{
				double discountPrice = totalPrice * .10;
				totalPrice -= discountPrice;
				totalPrice = Math.round(totalPrice * 100.00) / 100.00;
				combined.append("\nTotal: $");
				combined.append(totalPrice);
				combined.append(" (10% off)");
			}
			else
			{
				totalPrice = Math.round(totalPrice * 100.00) / 100.00;
				combined.append("\nTotal: $");
				combined.append(totalPrice);
			}
		}
		
		System.out.println(combined);

	}

}
