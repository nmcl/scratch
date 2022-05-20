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
        HashMap<String, Integer> count = new HashMap<String, Integer>();
        
        for (int i = 0; i < foods.size(); i++)
        {
            Food toCheck = foods.elementAt(i);
            
            toCheck.getIngredients().forEach(ingredient -> count.put(ingredient, count.getOrDefault(ingredient, 0) + 1));

            for (String allergen : toCheck.getAllergens())
            {
                mapped.computeIfAbsent(allergen, key -> new Vector<String>(toCheck.getIngredients()));
                mapped.get(allergen).retainAll(toCheck.getIngredients());
            }
        }

        if (_debug)
            System.out.println("Mapped "+mapped.size());

        int totalOccurrences = count.entrySet().stream().filter(
                                ingredient -> mapped.entrySet().stream()
                                .noneMatch(entry -> entry.getValue().contains(ingredient.getKey())))
                                .mapToInt(Entry::getValue)
                                .sum();
        
        return totalOccurrences;
    }
    
    private boolean _debug;
}