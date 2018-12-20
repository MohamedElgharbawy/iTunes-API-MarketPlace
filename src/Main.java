import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import org.json.JSONException;

public class Main {
	
	public static void main(String[] args) throws IOException, JSONException {
				
        final int CURRENT_MONTH = 11;
        
		Scanner input = new Scanner(System.in);
		boolean more = true;		// For try catch when searching
		String menuChoice = "";
		String songChoice = "";
		String movieChoice = "";
		String cartChoice = "";
		
		Cart cart = new Cart();
		Library library = new Library();
		library.loadLibrary();
		
		System.out.println("Welcome to JavaTunes!");
		
		System.out.println("\n**** Login ****");
		System.out.print("\nEnter username: ");
		String username = input.nextLine();
		System.out.print("Enter password: ");
		String password = input.nextLine();
		
		UserList<User> userList = new UserList<User>();
		UserList<PremiumUser> premiumUserList = new UserList<PremiumUser>();
		loadUsers(userList, premiumUserList);
		userList.bubbleSort();
		premiumUserList.bubbleSort();
		
		boolean isPremium = false;

		User standardUser = new User(username, password);
		PremiumUser premiumUser = new PremiumUser(username, password);

		int position = premiumUserList.linearSearch(premiumUser);
		
        if(position != -1)
        {
            System.out.print("\nWelcome, " + premiumUser.getUserName() + "!");
            isPremium = true;
            
        }
        else
        {
            position = userList.linearSearch(standardUser);
            
            if(position != -1)
            {
                System.out.print("\nWelcome, " + standardUser.getUserName() + "!");
                isPremium = false;
            }
            else
            {
                System.out.println("\nNo such user exists.");
                System.out.println("Account Created!");
                User newUser = new User(username, password, CURRENT_MONTH);
                userList.addToList(newUser);
                userList.writeToFile("User.txt");
                premiumUserList.writeToFile("PremiumUser.txt");
                System.out.print("\nWelcome, " + newUser.getUserName() + "!");
                isPremium = false;
            }
        }
     
								
		while(!menuChoice.toLowerCase().equals("f")) {
			
			more = true;
			printMenu();
			System.out.print("What would you like to do? (a-f): ");
			menuChoice = input.nextLine();
						
			
			if(menuChoice.toLowerCase().equals("a")) {
								
				while(more) {
					System.out.print("\nSearch for a song: ");
					String song = input.nextLine();
					song = song.replaceAll(" ", "+");
					songChoice = "";
					
					try {
						Music mu = new Music(song);
						System.out.println("\n" + mu);

						if(!cart.isCopy(mu.getTitle()) && !library.isCopy(mu.getTitle())) {
							
							while(!songChoice.toLowerCase().equals("y") && !songChoice.toLowerCase().equals("n")) {
								
								System.out.print("\nWould you like to add this song to your cart? (y/n): ");
								songChoice = input.nextLine();
																
								if(!songChoice.toLowerCase().equals("y") && !songChoice.toLowerCase().equals("n")) {
									
									System.out.println("Please enter a valid input!");
								}
							}
							
							if(songChoice.toLowerCase().equals("y")) {
								
								cart.add(mu);
								System.out.println("\nAdded to your cart!");
							}
							
							more = false;
							
						} else {
							
							System.out.println("\nThis song is already in your cart or library!");
						}
					} catch (JSONException e) {
						System.out.println("No songs found.");
					}
				}
			} else if(menuChoice.toLowerCase().equals("b")) {
				
				while(more) {
					System.out.print("\nSearch for a movie: ");
					String movie = input.nextLine();
					movie = movie.replaceAll(" ", "+");
					movieChoice = "";
					
					try {
						Movie mo = new Movie(movie);
						System.out.println("\n" + mo);
						if(!cart.isCopy(mo.getTitle()) && !library.isCopy(mo.getTitle())) {
							
							while(!movieChoice.toLowerCase().equals("y") && !movieChoice.toLowerCase().equals("n")) {
								
								System.out.print("\nWould you like to add this movie to your cart? (y/n): ");
								movieChoice = input.nextLine();
																
								if(!movieChoice.toLowerCase().equals("y") && !movieChoice.toLowerCase().equals("n")) {
									
									System.out.println("Please enter a valid input!");
								}
							}
							
							if(movieChoice.toLowerCase().equals("y")) {
								
								cart.add(mo);
								System.out.println("\nAdded to your cart!");
							}
							
							more = false;
							
						} else {
							
							System.out.println("\nThis movie is already in your cart or library!");
						}
					} catch (JSONException e) {
						System.out.println("No movies found.");
					}
				}
			} else if(menuChoice.toLowerCase().equals("c")) {
				
				cart.displayCart(isPremium);
				cartChoice = "";
				
				if(!cart.isEmpty()) {

					while(!cartChoice.toLowerCase().equals("a") && !cartChoice.toLowerCase().equals("b")) {
						
						System.out.println("\na) Checkout");
						System.out.println("b) Remove an item");
						System.out.print("\nWhat would you like to do? (a/b): ");
						cartChoice = input.nextLine();
														
						if(!cartChoice.toLowerCase().equals("a") && !cartChoice.toLowerCase().equals("b")) {
							
							System.out.println("Please enter a valid input!");
						} 
					}
					
					if(cartChoice.toLowerCase().equals("a")) {
						
						cart.checkout();
						library.addToLibrary();
						System.out.println("\nThank you for purchasing! The media has been added to your library.");
					} else {
						
						System.out.print("\nWhich title would you like to remove? ");
						cartChoice = input.nextLine();
						
						cart.delete(cartChoice);
					}
					
				}
			} else if(menuChoice.toLowerCase().equals("d")) {
				
				library.displayLibrary();
			} else if(menuChoice.toLowerCase().equals("e")) {
				
				System.out.println("\n**** Account Information ****");

				if(isPremium) {
					
					premiumUserList.getItem(premiumUserList.linearSearch(premiumUser)).displayPremiumUser();
				} else {
					
					userList.getItem(userList.linearSearch(standardUser)).displayUser();
				}
			} else if(!menuChoice.toLowerCase().equals("f")){
				
				System.out.println("\nPlease enter a valid input!");
			}
						
		}
		
	  	library.preserve();
		System.out.println("\nThank you for shopping! Come back again!");
		input.close();
	}
	
	public static void printMenu() {
        System.out.println("\n\nMain Menu");
        System.out.println("---------");
        System.out.println("a) Search for a Song");
        System.out.println("b) Search for a Movie");
        System.out.println("c) View your Cart (" + Cart.getNumItems() + ")");
        System.out.println("d) View your Library");
        System.out.println("e) Account Information");
        System.out.println("f) Exit\n");
    }
	
	public static void loadUsers(UserList<User> list, UserList<PremiumUser> list2) throws FileNotFoundException, InputMismatchException
    {
        File infile = new File("User.txt");
        Scanner in = new Scanner(infile);
        String name, password;
        int month, premiumMonth;
         
        while(in.hasNextLine())
        {
 
            name = in.nextLine();
            password = in.nextLine();
      
            month = in.nextInt();
            
            if(in.hasNextLine())
            {
                in.nextLine();
            }
       

            User temp = new User(name, password, month);
            
            list.addToList(temp);
        }
        
        File infile2 = new File("PremiumUser.txt");
        Scanner in2 = new Scanner(infile2);
        
        while(in2.hasNextLine())
        {
            name = in2.nextLine();
            password = in2.nextLine();
            month = in2.nextInt();
            in2.nextLine();
            premiumMonth = in2.nextInt();
            
            if(in2.hasNextLine())
            {
                in2.nextLine();
            }

          
            PremiumUser temp2 = new PremiumUser(name, password, month, premiumMonth);
            
            list2.addToList(temp2);
        }
        in.close();
        in2.close();
    }
}
