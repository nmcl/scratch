import java.util.*;
import java.io.*;

public class Reaction
{
    public Reaction ()
    {
        _chemicalCreated = null;
        _quantityCreated = -1;
        _reactants = null;
        _quantities = null;
    }

    public Reaction (Chemical name, int quantity, Vector<Chemical> reactants, Vector<Integer> quantities)
    {
        _chemicalCreated = name;
        _quantityCreated = quantity;
        _reactants = reactants;
        _quantities = quantities;
    }

    public final void setCreated (Chemical name, int quantity)
    {
        _chemicalCreated = name;
        _quantityCreated = quantity;
    }

    public final void setReactants (Vector<Chemical> reactants, Vector<Integer> quantities)
    {
        _reactants = reactants;
        _quantities = quantities;
    }

    public final String chemicalCreated ()
    {
        return _chemicalCreated.getName();
    }

    @Override
    public String toString ()
    {
        String str = "";

        for (int i = 0; i < _quantities.size(); i++)
        {
            if (str != "")
                str += " and ";

            str += _reactants.elementAt(i)+"*"+_quantities.elementAt(i);
        }

        str += " create "+_quantityCreated+" of "+_chemicalCreated;

        return str;
    }

    private Chemical _chemicalCreated;
    private int _quantityCreated;
    private Vector<Chemical> _reactants;
    private Vector<Integer> _quantities;
}