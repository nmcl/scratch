public class Amplifier
{
    public Amplifier (int ps, String[] commands, boolean debug)
    {
        _computer = new Intcode(debug);
        _phaseSetting = ps;
        _commands = commands;
        _debug = debug;
    }

    public final void resetState ()
    {
        _computer = new Intcode(_debug);
    }

    public final void setPhaseSetting (int ps)
    {
        _phaseSetting = ps;
    }

    public final void setCommands (String[] commands)
    {
        _commands = commands;
    }

    public final int executeCommands ()
    {
        String[] currentCommands = new String[_commands.length];

        System.arraycopy(_commands, _commands.length, currentCommands, 0, _commands.length);

        return _computer.parseAndExecute(currentCommands, _phaseSetting);
    }

    private Intcode _computer;
    private int _phaseSetting;
    private String[] _commands;
    private boolean _debug;
}