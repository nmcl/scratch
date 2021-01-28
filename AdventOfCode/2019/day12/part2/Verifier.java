import java.util.*;

public class Verifier
{
    public static final String EXAMPLE1_DATA = "example1.txt";
    public static final String EXAMPLE2_DATA = "example2.txt";

    public static final int EXAMPLE1_REPEAT_TIME = 2772;
    public static final long EXAMPLE2_REPEAT_TIME = 4686774924L;

    public Verifier (boolean debug)
    {
        _activator = null;
        _histories = new Vector<Moon[]>();
        _debug = debug;
    }

    public final boolean verify ()
    {
        return verifyExample1() && verifyExample2();
    }

    // we don't check all the data, just a sample.

    private final boolean verifyExample1 ()
    {
        boolean result = false;
        Moon[] moonsAsArray = null;

        _activator = new MoonSystem(EXAMPLE1_DATA, _debug);
        moonsAsArray = new Moon[_activator.getMoons().size()];

        _histories.add(_activator.duplicateTheSystem().toArray(moonsAsArray));

        if (_debug)
            System.out.println("Activated: "+_activator);
        
        for (int i = 0; i < EXAMPLE1_REPEAT_TIME; i++)
        {
            _activator.applyGravity();
            _histories.add(_activator.duplicateTheSystem().toArray(moonsAsArray));
        }

        System.out.println("Moon positions:\n"+_activator.moonCoordinates());
        System.out.println("Moon velocities:\n"+_activator.moonVelocities());
        
        result = true;

        for (int i = 0; (i < moonsAsArray.length) && result; i++)
        {
            if (!_histories.elementAt(0)[i].equals(_activator.getMoons().elementAt(i)))
                result = false;
        }

        return result;
    }

    private final boolean verifyExample2 ()
    {
        boolean result = false;
        Moon[] moonsAsArray = null;

        _activator = new MoonSystem(EXAMPLE2_DATA, _debug);
        moonsAsArray = new Moon[_activator.getMoons().size()];

        _histories.add(_activator.duplicateTheSystem().toArray(moonsAsArray));

        if (_debug)
            System.out.println("Activated: "+_activator);
        
        long index = 0;

        while (index < EXAMPLE2_REPEAT_TIME)
        {
            _activator.applyGravity();
            _histories.add(_activator.duplicateTheSystem().toArray(moonsAsArray));
            index++;
        }

        System.out.println("Moon positions:\n"+_activator.moonCoordinates());
        System.out.println("Moon velocities:\n"+_activator.moonVelocities());
        
        result = true;

        for (int i = 0; (i < moonsAsArray.length) && result; i++)
        {
            if (!_histories.elementAt(0)[i].equals(_activator.getMoons().elementAt(i)))
                result = false;
        }

        return result;
    }

    private MoonSystem _activator;
    private Vector<Moon[]> _histories;
    private boolean _debug;
}