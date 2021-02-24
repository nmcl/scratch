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
        _ChemicalQuantities = null;
    }

    public final void setCreated (ChemicalQuantity name)
    {
        _chemicalCreated = name;
    }

    public final void setChemicalQuantities (Vector<ChemicalQuantity> ChemicalQuantities)
    {
        _ChemicalQuantities = ChemicalQuantities;
    }

    public final Vector<ChemicalQuantity> getChemicalQuantities ()
    {
        return _ChemicalQuantities;
    }

    public final boolean isOre ()
    {
        if (_ChemicalQuantities.size() == 1)
        {
            if (_ChemicalQuantities.elementAt(0).getChemical().isOre())
                return true;
        }

        return false;
    }

    public final ChemicalQuantity chemicalCreated ()
    {
        return _chemicalCreated;
    }

    @Override
    public String toString ()
    {
        String str = "";
        Enumeration<ChemicalQuantity> iter = _ChemicalQuantities.elements();

        while (iter.hasMoreElements())
        {
            if (str != "")
                str += " and ";

            str += iter.nextElement();
        }

        str += " creates "+_chemicalCreated;

        return str;
    }

    private ChemicalQuantity _chemicalCreated;
    private Vector<ChemicalQuantity> _ChemicalQuantities;
}