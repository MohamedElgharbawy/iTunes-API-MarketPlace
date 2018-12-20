
public class PremiumUser extends User {
    private int datePremium;
    
    PremiumUser(String name, String password)
    {
        super(name, password);
    }
    
    PremiumUser(String name, String password, int month, int date)
    {
        super(name, password, month);
        datePremium = date;
    }
    
    public static String ConvertIntToMonth(int n)
    {
        String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        return months[n];
        
    }
    
    public void setDatePremium(int date)
    {
        datePremium = date;
    }
    
    public int getDatePremium()
    {
        return datePremium;
    }
    
    @Override public int hashCode()
    {
        return this.datePremium;
    }
    public int compareTo(PremiumUser e)
    {

        if(this == e) {
            return 0;
        } else if (!this.getUserName().equals(e.getUserName())) {

            return this.getUserName().compareTo(e.getUserName());
        }
        else
        {
            return Integer.compare(this.datePremium, e.datePremium);
        }
    }
    
    public void displayPremiumUser()
    {
        System.out.println("\nUsername: " + this.getUserName() +
        				"\nSubscription: Premium" +
        				 "\nMonth Joined: " + PremiumUser.ConvertIntToMonth(this.getMonth())
                         + "\nPremium Start Month: " + PremiumUser.ConvertIntToMonth(this.datePremium));
    }
    
    @Override public String toString()
    {
        return super.toString()  + "\n" + datePremium;
    }
}
