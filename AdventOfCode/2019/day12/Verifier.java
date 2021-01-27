public class Verifier
{
    public static final String EXAMPLE1_DATA = "example1.txt";
    public static final String EXAMPLE2_DATA = "example2.txt";

    public static final Velocity EXAMPLE1_STEP2_MOON_2 = new Velocity(-2, 5, 6);
    public static final Velocity EXAMPLE1_STEP4_MOON1 = new Velocity(-3, -2, 1);
    public static final Coordinate EXAMPLE1_STEP7_MOON4 = new Coordinate(2, 0, 0);
    public static final Coordinate EXAMPLE1_STEP10_MOON3 = new Coordinate(3, -6, 1);
    public static final int EXAMPLE1_TOTAL_ENERGY = 179;

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
        
        for (int i = 0; i < 2; i++)
        {
            _activator.applyGravity();

            if (_debug)
            {
                System.out.println("Moon positions:\n"+_activator.moonCoordinates());
                System.out.println("Moon velocities:\n"+_activator.moonVelocities());
            }
        }

        System.out.println("Moon positions:\n"+_activator.moonCoordinates());
        System.out.println("Moon velocities:\n"+_activator.moonVelocities());

        Moon theMoon = _activator.getMoons().elementAt(1);
        Velocity theVelocity = theMoon.getVelocity();

        if (theVelocity.equals(EXAMPLE1_STEP2_MOON_2))
        {
            result = true;
        }

        return result;
    }

    private MoonSystem _activator;
    private boolean _debug;
}