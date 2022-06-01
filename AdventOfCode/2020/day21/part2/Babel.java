import java.util.*;
import java.util.Map.*;

public class Babel
{
    public Babel (boolean debug)
    {
        _debug = debug;
    }

    public int translate (Vector<Food> foods)
    {
        HashMap<String, Vector<String>> mapped = new HashMap<String, Vector<String>>();
        
        for (int i = 0; i < foods.size(); i++)
        {
            Food toCheck = foods.elementAt(i);

            for (String allergen : toCheck.getAllergens())
            {
                mapped.computeIfAbsent(allergen, key -> new Vector<String>(toCheck.getIngredients()));
                mapped.get(allergen).retainAll(toCheck.getIngredients());
            }
        }

        if (_debug)
            System.out.println("Mapped "+mapped.size());

        while (mapped.entrySet().stream().anyMatch(entry -> entry.getValue().size() != 1))
        {
            for (Entry<String, Vector<String>> specific : mapped.entrySet())
            {
                if (specific.getValue().size() == 1)
                {
                    String ingredient = specific.getValue().firstElement();
            
                    System.out.println("Looking at "+ingredient+" from "+specific);

                    for (Entry<String, Vector<String>> other : mapped.entrySet())
                    {
                        System.out.println("Now looking at "+other.getKey()+" and "+specific.getKey());

                        if (!other.getKey().equals(specific.getKey()))
                        {
                            System.out.println("Removing "+ingredient);

                            other.getValue().remove(ingredient);
                        }
                    }
                }
            }
        }
        
        return 0;
    }
    
    private boolean _debug;
}