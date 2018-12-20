
public class User implements Comparable<User> {
    private String user_name;
    private String password;
    private int month_joined;

    public User(String name, String password)
    {
        user_name = name;
        this.password = password;
    }
    public User(String name, String password, int month)
    {
        user_name = name;
        this.password = password;
        month_joined = month;
    }
    
    public void setMonth(int date) 
    {
        month_joined = date;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
    
    public void setUserName(String n)
    {
        user_name = n;
    }
    
    public int getMonth()
    {
        return month_joined;
    }

    public String getUserName()
    {
        return user_name;
    }

    public boolean verifyPassword(String p)
    {
        return password.equals(p);
    }
    
    @Override public int hashCode()
    {
        return month_joined;
    }

    @Override public boolean equals(Object o)
    {
        if(o == this)
        {
            return true;
        } 
        else if(!(o instanceof User)) {
            return false;
        } 
        else {
            User e2 = (User) o;
            return this.user_name.equals(e2.user_name) && this.password.equals(e2.password);
        }
    }

    public int compareTo(User e)
    {

        if(this == e) {
            return 0;
        } else if(!this.user_name.equals(e.user_name)) {
            return this.user_name.compareTo(e.user_name);
        }
        else
        {
            return Integer.compare(this.month_joined, e.month_joined);
        }
    }
    
    public void displayUser()
    {
        System.out.println("\nUsername: " + this.user_name + 
        					"\nSubscription: Standard" +
                            "\nMonth Joined: " + PremiumUser.ConvertIntToMonth(this.month_joined));
        
    }
    
    @Override public String toString()
    {
        return this.user_name + "\n" + this.password +  "\n" +  month_joined;
                
    }
}
