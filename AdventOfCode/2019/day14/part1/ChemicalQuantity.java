/**
 * A tuple of the chemical and the amount used/produced, such
 * as ...
 * 
 * 1 C
 */

public class ChemicalQuantity
{
    public ChemicalQuantity (Chemical chem, Integer quantity)
    {
        _chemical = chem;
        _amount = quantity;
    }

    public ChemicalQuantity (ChemicalQuantity fromReaction)
    {
        _chemical = fromReaction._chemical;
        _amount = fromReaction._amount;
    }

    public final Chemical getChemical ()
    {
        return _chemical;
    }

    public final Integer getAmount ()
    {
        return _amount;
    }

    public final void setAmount (int v)
    {
        _amount = v;
    }

    @Override
    public int hashCode()
    {
        return super.hashCode();
    }

    @Override
    public String toString ()
    {
        return _amount+":"+_chemical.getName();
    }

    // just compare chemical names

    @Override
    public boolean equals (Object obj)
    {
        if (obj == null)
            return false;

        if (obj instanceof ChemicalQuantity)
        {
            ChemicalQuantity temp = (ChemicalQuantity) obj;

            return _chemical.equals(temp._chemical);
        }

        return false;
    }

    private Chemical _chemical;
    private Integer _amount;
}