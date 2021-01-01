public class Amplifier
{
    public Amplifier (boolean debug)
    {
        _computer = new Intcode(debug);
        _debug = debug;
    }

    private Intcode _computer;
    private boolean _debug;
}