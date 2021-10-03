public class Droid
{
    public Droid (Vector<String> values, boolean debug)
    {
        _theComputer = new Droid(values, debug);
        _debug = debug;
    }

    private Intcode _theComputer;
    private boolean _debug;
}