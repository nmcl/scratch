public class Verifier
{
    public static final String EXAMPLE1_DATA = "example1.txt";
    public static final String EXAMPLE2_DATA = "example2.txt";

    public Verifier (boolean debug)
    {
        _activator = null;
        _debug = debug;
    }

    public final boolean verify ()
    {
        boolean result = false;

        _activator = new MoonSystem(EXAMPLE1_DATA, _debug);

        if (_debug)
            System.out.println("Activated: "+_activator);
            
        System.out.println("Starting with:\n"+_activator.moonVelocities());

        _activator.applyGravity();

        System.out.println("Now have:\n"+_activator.moonVelocities());
        System.out.println("and:\n"+_activator.moonCoordinates());

        return result;
    }

    private MoonSystem _activator;
    private boolean _debug;
}