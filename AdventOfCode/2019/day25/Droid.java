public class Droid
{
    public Droid (Vector<String> values, boolean debug)
    {
        _theComputer = new Droid(values, debug);
        _debug = debug;
    }

    public void traverse ()
    {

    }

    public void stepTraverse ()
    {
        
    }

    private Intcode _theComputer;
    private boolean _debug;
}