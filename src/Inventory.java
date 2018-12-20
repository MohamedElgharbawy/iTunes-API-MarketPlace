import java.io.IOException;

public interface Inventory {

	void add(Music mu);
	void add(Movie mo);
	
	void delete(String title) throws IOException;
	
	boolean isCopy(String title) throws IOException;
	
	void displayCart(boolean premium) throws IOException;
	void checkout() throws IOException;
	
}