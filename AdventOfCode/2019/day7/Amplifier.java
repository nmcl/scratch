public class Amplifier
{
    public Amplifier (int id, int input1, int input2, String[] commands, boolean debug)
    {
        _id = id;
        _computer = new Intcode(debug);
        _input1 = input1;
        _input2 = input2;
        _commands = commands;
        _debug = debug;

        //if (_debug)
            System.out.println("Created "+toString());
    }

    public final int executeCommands ()
    {
        String[] currentCommands = new String[_commands.length];

        System.arraycopy(_commands, 0, currentCommands, 0, _commands.length);

        return Integer.parseInt(_computer.parseAndExecute(currentCommands, _input1, _input2));
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