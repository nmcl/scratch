// increases or decreases a single global value called the accumulator by the value given in the argument.

public class Accumulator extends OpCode
{
    public Accumulator (int amount)
    {
        super(OpCode.ACCUMULATOR);

        _amount = amount;
    }

    public int getValue ()
    {
        return _amount;
    }

    @Override
    public String toString ()
    {
        return OpCode.ACCUMULATOR+" "+_amount;
    }

    private int _amount;
}