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

    public final Vector<String> getAllergens ()
    {
        return _allergens;
    }

    public final Vector<String> getIngredients ()
    {
        return _ingredients;
    }

    @Override
    public String toString ()
    {
        String str = "\nIngredients: ";

        for (int i = 0; i < _ingredients.size(); i++)
            str += _ingredients.elementAt(i)+" ";

        str += "\nAllergens: ";

        for (int j = 0; j < _allergens.size(); j++)
            str += _allergens.elementAt(j)+" ";
            
        return str;
    }

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (this == obj)
            return true;
        
        if (getClass() == obj.getClass())
        {
            Food temp = (Food) obj;

            return (_ingredients.equals(temp._ingredients));
        }

        return false;
    }

    Vector<String> _allergens;
    Vector<String> _ingredients;
}