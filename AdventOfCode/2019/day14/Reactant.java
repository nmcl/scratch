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