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
        _ChemicalQuantitys = null;
    }

    public final void setCreated (ChemicalQuantity name)
    {
        _chemicalCreated = name;
    }

    public final void setChemicalQuantitys (Vector<ChemicalQuantity> ChemicalQuantitys)
    {
        _ChemicalQuantitys = ChemicalQuantitys;
    }

    public final Vector<ChemicalQuantity> getChemicalQuantitys ()
    {
        return _ChemicalQuantitys;
    }

    public final boolean isOre ()
    {
        if (_ChemicalQuantitys.size() == 1)
        {
            if (_ChemicalQuantitys.elementAt(0).getChemical().isOre())
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
        Enumeration<ChemicalQuantity> iter = _ChemicalQuantitys.elements();

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
    private Vector<ChemicalQuantity> _ChemicalQuantitys;
}