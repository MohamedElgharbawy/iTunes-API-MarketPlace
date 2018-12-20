import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class Library {	
	
	private ArrayList<Media> musicLibrary = new ArrayList<Media>();
	private ArrayList<Media> movieLibrary = new ArrayList<Media>();
		

	public void loadLibrary() throws IOException {
		
		try {
			File cart = new File("library.txt");
			Scanner libraryFile = new Scanner(cart);
			
			while(libraryFile.hasNextLine()) {
				
				String title = libraryFile.nextLine();
				
				String creator = libraryFile.nextLine();
								
				String length = libraryFile.nextLine();
				
				Media m = new Media(title, creator, length);
				
				libraryFile.nextLine();
				
				if(libraryFile.nextLine().equals("music")) {
					
					musicLibrary.add(m);

				} else {
					
					movieLibrary.add(m);

				}
				
			}
			
			libraryFile.close();
		} catch (IOException e) {
			
			System.out.println("Error: File could not be opened.");
		}
		
	}
	
	public void addToLibrary() throws IOException {
		
		try {
			File cart = new File("cart.txt");
			Scanner libraryFile = new Scanner(cart);
			
			while(libraryFile.hasNextLine()) {
				
				String title = libraryFile.nextLine();
				
				String creator = libraryFile.nextLine();
				
				libraryFile.nextLine();
				
				String length = libraryFile.nextLine();
				
				Media m = new Media(title, creator, length);
				
				if(libraryFile.nextLine().equals("music")) {
						
						musicLibrary.add(m);
				} else {
					
						
					movieLibrary.add(m);
				}
				
			}
			
			libraryFile.close();
		} catch (IOException e) {
			
			System.out.println("Error: File could not be opened.");
		}
		
	}
	
	public void preserve() throws IOException {
		
    	PrintWriter writer = new PrintWriter("library.txt");
    	
    	for(int i = 0; i < musicLibrary.size(); i++) {
    		
    		writer.println(musicLibrary.get(i).toString());
    		writer.println("music");
    	}
    	
    	for(int i = 0; i < movieLibrary.size(); i++) {
    		
    		writer.println(movieLibrary.get(i).toString());
    		writer.println("movie");
    	}

    	writer.close();
	}
	
	public void displayLibrary() {
		
		if(musicLibrary.size() == 0 && movieLibrary.size() == 0) {
			
			System.out.println("\nYour library is empty!");
		}
		
		if(musicLibrary.size() != 0) {
			
			System.out.println("\n**** Music ****\n");
			
			for(int i = 0; i < musicLibrary.size(); i++) {
				
				System.out.println(musicLibrary.get(i).toString());
			}
		}
		
		if(movieLibrary.size() != 0) {
			
			System.out.println("\n**** Movies ****\n");
			
			for(int i = 0; i < movieLibrary.size(); i++) {
				
				System.out.println(movieLibrary.get(i).toString());
			}
		}
	}
	
	public int linearSearch(String title, ArrayList<Media> list) {
		
		for(int i = 0; i < list.size(); i++) {
			
			if(list.get(i).getTitle().substring(7).equals(title)) {		//Takes a substring to eliminate "Title: "
				
				return i;
			}
		}
		
		return -1;
	}
	
	public boolean isCopy(String title) throws IOException {
		
		if(linearSearch(title, musicLibrary) != -1 || linearSearch(title, movieLibrary) != -1) {
			
			return true;
		}
		
		return false;
	}

}
