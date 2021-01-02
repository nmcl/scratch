public class Amplifier
{
    public Amplifier (int id, boolean debug)
    {
        this(id, 0, 0, null, debug);
    }

    public Amplifier (int id, int input1, int input2, String[] commands, boolean debug)
    {
        _id = id;
        _computer = new Intcode(debug);
        _input1 = input1;
        _input2 = input2;
        _debug = debug;

        if (commands == null)
        {
            if (_debug)
                System.out.println("No commands provided at Amplifier construction time");
        }
        else
        {
            _commands = new String[commands.length];

            System.arraycopy(commands, 0, _commands, 0, commands.length);
        }

        if (_debug)
            System.out.println("Created "+toString());
    }

    public final void changeInputCodes (int input1, int input2)
    {
        _input1 = input1;
        _input2 = input2;
    }

    public final void changeCommands (String[] commands)
    {
        if (commands == null)
        {
            if (_debug)
                System.out.println("No commands provided!");
        }
        else
        {
            _commands = new String[commands.length];

            System.arraycopy(commands, 0, _commands, 0, commands.length);
        }
    }

    public final int executeCommands ()
    {
        String[] theCommands = new String[_commands.length];

        System.arraycopy(_commands, 0, theCommands, 0, _commands.length);

        return Integer.parseInt(_computer.parseAndExecute(theCommands, _input1, _input2));
    }

    @Override
    public String toString ()
    {
        return "Amplifier "+_id+" with input1: "+_input1+" and input2: "+_input2;
    }

    private int _id;
    private Intcode _computer;
    private int _input1;
    private int _input2;
    private String[] _commands;
    private boolean _debug;
}