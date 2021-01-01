public class Amplifier
{
    public Amplifier (int input1, int input2, String[] commands, boolean debug)
    {
        _computer = new Intcode(debug);
        _input1 = input1;
        _input2 = input2;
        _commands = commands;
        _debug = debug;

        //if (_debug)
            System.out.println("Amplifier created with states <"+_input1+", "+_input2+">");
    }

    public final int executeCommands ()
    {
        String[] currentCommands = new String[_commands.length];

        System.arraycopy(_commands, 0, currentCommands, 0, _commands.length);

        return Integer.parseInt(_computer.parseAndExecute(currentCommands, _input1, _input2));
    }

    private Intcode _computer;
    private int _input1;
    private int _input2;
    private String[] _commands;
    private boolean _debug;
}