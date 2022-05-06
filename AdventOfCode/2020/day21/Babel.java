import java.util.*;

public class Babel
{
    public Babel (boolean debug)
    {
        _debug = debug;
    }

    public void translate (Vector<Food> foods)
    {
        Vector<String> mapped = new Vector<String>();

        for (int i = 0; i < foods.size(); i++)
        {
            Food toCheck = foods.elementAt(i);
            Vector<String> ingredients = toCheck.getIngredients();

            for (int j = 0; j < ingredients.size(); j++)
            {
                String ingredient = ingredients.elementAt(j);

                for (int k = 0; k < foods.size(); k++)
                {
                    Food compare = foods.elementAt(k);

                    if (!toCheck.equals(compare))
                    {
                        if (compare.getIngredients().contains(ingredient))
                        {
                            if (_debug)
                                System.out.println("Ingredient "+ingredient+" found in\n"+compare);

                            // now check where this is also used

                            mapped.add(ingredient);
                        }
                    }
                }
            }
        }

        for (int i = 0; i < mapped.size(); i++)
        {
            System.out.println("Mapped: "+mapped.elementAt(i));
        }
    }
    
    private boolean _debug;
}