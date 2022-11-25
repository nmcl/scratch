public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";
    public static final int EXAMPLE_RESULT = 150;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Submarine s = new Submarine(_debug);
        ThreeDPoint position = s.move(EXAMPLE_FILE);

        if (_debug)
            System.out.println("Final position: "+position);

        int value = position.getX() * position.getZ();

        if (value == EXAMPLE_RESULT)
            return true;
            
        return false;
    }

    private boolean _debug;
}