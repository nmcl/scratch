import java.util.*;

public class Translator
{
    public Translator (boolean debug)
    {
        _debug = debug;
    }

    public void translate (Vector<Food> foods)
    {
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
                            System.out.println("Ingredient "+ingredient+" found in\n"+compare);
                        }
                    }
                }
            }
        }
    }
    
    private boolean _debug;
}