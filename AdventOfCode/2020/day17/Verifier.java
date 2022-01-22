public class Verifier
{
    public static final String EXAMPLE_WORLD = "example.txt";
    
    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Dimension dim = new Dimension(EXAMPLE_WORLD, _debug);

        System.out.println("Loaded:\n\n"+dim);

        dim.cycle();

        return false;
    }

    private boolean _debug;
}