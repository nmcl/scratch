public class Reaction
{
    public Reaction (Chemical name, int quantity, Chemical[] reactants, int[] quantities)
    {
        _chemicalCreated = name;
        _quantityCreated = quantity;
        _reactants = reactants;
        _quantities = quantities;
    }

    private Chemical _chemicalCreated;
    private int _quantityCreated;
    private Chemical[] _reactants;
    private int[] _quantities;
}