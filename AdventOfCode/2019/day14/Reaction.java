public class Reaction
{
    public Reaction (Chemical name, Chemical[] reactants, int[] quantities)
    {
        _name = name;
        _reactants = reactants;
        _quantities = quantities;
    }

    private Chemical _name;
    private Chemical[] _reactants;
    private int[] _quantities;
}