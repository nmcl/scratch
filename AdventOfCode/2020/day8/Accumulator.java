// increases or decreases a single global value called the accumulator by the value given in the argument.

public class Accumulator extends OpCode
{
    public Accumulator (int amount)
    {
        super(OpCode.ACCUMULATOR);

        _amount = amount;
    }

    private int _amount;
}