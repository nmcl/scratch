public class Verifier
{
    public static final String EXAMPLE1_DATA = "example1.txt";
    public static final String EXAMPLE2_DATA = "example2.txt";

    public static final Velocity EXAMPLE1_STEP2_MOON_2 = new Velocity(-2, 5, 6);
    public static final Coordinate EXAMPLE1_STEP7_MOON4 = new Coordinate(2, 0, 0);
    
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