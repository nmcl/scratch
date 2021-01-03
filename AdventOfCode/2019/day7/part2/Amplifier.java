public class Amplifier
{
    public Amplifier (int id, String[] commands, boolean debug)
    {
        _id = id;
        _computer = new Intcode(commands, debug);
        _debug = debug;

        if (commands == null)
        {
            if (_debug)
                System.out.println("No commands provided at Amplifier construction time");
        }

        if (_debug)
            System.out.println("Created "+toString());
    }

    public final int executeProgram (int input1, int input2)
    {
        if (!halted())
            return Integer.parseInt(_computer.executeProgram(input1, input2));
        else
        {
            System.out.println("Cannot execute commands: amplifier "+_id+" has already halted!");

            return -1;
        }
    }

    public final boolean halted ()
    {
        return _computer.hasHalted();
    }

    @Override
    public String toString ()
    {
        return "Amplifier "+_id;
    }

    private int _id;
    private Intcode _computer;
    private boolean _debug;
}