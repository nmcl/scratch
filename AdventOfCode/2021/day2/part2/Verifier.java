public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";
    public static final int EXAMPLE_RESULT = 900;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Submarine s = new Submarine(_debug);
        Course position = s.move(EXAMPLE_FILE);

        if (_debug)
            System.out.println("Final position: "+position);

        if (_debug)
            System.out.println("Course: "+position);
            
        int value = position.getPosition() * position.getDepth();

        if (value == EXAMPLE_RESULT)
            return true;
            
        return false;
    }

    private boolean _debug;
}