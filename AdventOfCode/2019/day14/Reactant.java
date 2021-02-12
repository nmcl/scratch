/**
 * A tuple of the chemical and the amount used/produced, such
 * as ...
 * 
 * 1 C
 */

public class Reactant
{
    public Reactant (Chemical chem, Integer quantity)
    {
        _chemical = chem;
        _amount = quantity;
    }

    public final Chemical getChemical ()
    {
        return _chemical;
    }

    public final Integer getAmount ()
    {
        return _amount;
    }

    @Override
    public String toString ()
    {
        return _chemical.getName()+" of "+_amount;
    }

    private Chemical _chemical;
    private Integer _amount;
}