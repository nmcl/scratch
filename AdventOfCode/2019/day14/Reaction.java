import java.util.*;
import java.io.*;

/**
 * The reaction (equation), such as ...
 * 
 * 10 ORE => 10 A
 */

public class Reaction
{
    public Reaction ()
    {
        _chemicalCreated = null;
        _reactants = null;
    }

    public final void setCreated (Reactant name)
    {
        _chemicalCreated = name;
    }

    public final void setReactants (Vector<Reactant> reactants)
    {
        _reactants = reactants;
    }

    public final Vector<Reactant> getReactants ()
    {
        return _reactants;
    }

    public final boolean isOre ()
    {
        if (_reactants.size() == 1)
        {
            if (_reactants.elementAt(0).getChemical().isOre())
                return true;
        }

        return false;
    }

    public final Reactant chemicalCreated ()
    {
        return _chemicalCreated;
    }

    @Override
    public String toString ()
    {
        String str = "";
        Enumeration<Reactant> iter = _reactants.elements();

        while (iter.hasMoreElements())
        {
            if (str != "")
                str += " and ";

            str += iter.nextElement();
        }

        str += " creates "+_chemicalCreated;

        return str;
    }

    private Reactant _chemicalCreated;
    private Vector<Reactant> _reactants;
}