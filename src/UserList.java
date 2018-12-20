import java.util.ArrayList;
import java.io.FileNotFoundException;
import java.io.PrintWriter;


public class UserList<E> {
    
    private ArrayList<E> users = new ArrayList<E>();
    
    public void bubbleSort() {
        for(int i = 0; i < users.size() - 1; i++)
        {
            for(int j = 0; j < users.size() - i - 1; j++)
            {
                if (users.get(j).hashCode() > users.get(j+1).hashCode())
                {
                    E temp = users.get(j+1);
                    users.set(j+1, users.get(j));
                    users.set(j, temp);   
                }
            }
        }
    }
    
    public int linearSearch(E e2)
    {
        for(int i = 0; i < users.size(); i++)
        {
            if(users.get(i).equals(e2))
            {
                return i;
            }
        }
        return -1;
    }
    
    public void addToList(E e2)
    {
    	users.add(e2);
    }
    
    public E getItem(int index) {
    	
    	return users.get(index);
    }
    
    public void writeToFile(String fileName) throws FileNotFoundException
    {
        
        PrintWriter writer = new PrintWriter(fileName);
        
        for(int i = 0; i < users.size(); i++)
        {
            writer.println(users.get(i).toString());
        }
        
        writer.close();
    }
}
