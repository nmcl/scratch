import java.util.*;

public class Food
{
    public Food ()
    {
        _allergens = new Vector<String>();
        _ingredients = new Vector<String>();
    }

    public final void addIngredient (String data)
    {
        _ingredients.add(data);
    }

    public final void addAllergen (String data)
    {
        _allergens.add(data);
    }

    @Override
    public String toString ()
    {
        String str = "Ingredients: ";

        for (int i = 0; i < _ingredients.size(); i++)
            str += _ingredients.elementAt(i)+" ";

        str += "\nAllergens: ";

        for (int j = 0; j < _allergens.size(); j++)
            str += _allergens.elementAt(j)+" ";
            
        return str;
    }

    Vector<String> _allergens;
    Vector<String> _ingredients;
}