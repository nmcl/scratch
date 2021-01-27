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
        return verifyExample1() && verifyExample2();
    }

    // we don't check all the data, just a sample.

    private final boolean verifyExample1 ()
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

        System.out.println("Moon positions after 2 steps:\n"+_activator.moonCoordinates());
        System.out.println("Moon velocitie after 2 stepss:\n"+_activator.moonVelocities());

        Moon theMoon = _activator.getMoons().elementAt(1);
        Velocity theVelocity = theMoon.getVelocity();

        if (theVelocity.equals(EXAMPLE1_STEP2_MOON2))
        {
            for (int i = 0; i < 2; i++)
            {
                _activator.applyGravity();
            }

            System.out.println("Moon positions after 4 steps:\n"+_activator.moonCoordinates());
            System.out.println("Moon velocities after 4 steps:\n"+_activator.moonVelocities());

            theMoon = _activator.getMoons().elementAt(0);
            theVelocity = theMoon.getVelocity();

            if (theVelocity.equals(EXAMPLE1_STEP4_MOON1))
            {
                for (int i = 0; i < 3; i++)
                {
                    _activator.applyGravity();
                }

                System.out.println("Moon positions after 7 steps:\n"+_activator.moonCoordinates());
                System.out.println("Moon velocities after 7 steps:\n"+_activator.moonVelocities());

                theMoon = _activator.getMoons().elementAt(3);
                Coordinate theCoordinate = theMoon.getPosition();

                if (theCoordinate.equals(EXAMPLE1_STEP7_MOON4))
                {
                    for (int i = 0; i < 3; i++)
                    {
                        _activator.applyGravity();
                    }

                    System.out.println("Moon positions after 10 steps:\n"+_activator.moonCoordinates());
                    System.out.println("Moon velocities after 10 steps:\n"+_activator.moonVelocities());

                    theMoon = _activator.getMoons().elementAt(2);
                    theCoordinate = theMoon.getPosition();

                    if (theCoordinate.equals(EXAMPLE1_STEP10_MOON3))
                    {
                        if (_activator.totalEnergy() == EXAMPLE1_TOTAL_ENERGY)
                            result = true;
                    }
                }
            }
        }

        return result;
    }

    private final boolean verifyExample2 ()
    {
        boolean result = false;

        _activator = new MoonSystem(EXAMPLE2_DATA, _debug);

        if (_debug)
            System.out.println("Activated: "+_activator);
        
        for (int i = 0; i < 20; i++)
        {
            _activator.applyGravity();

            if (_debug)
            {
                System.out.println("Moon positions:\n"+_activator.moonCoordinates());
                System.out.println("Moon velocities:\n"+_activator.moonVelocities());
            }
        }

        System.out.println("Moon positions after 20 steps:\n"+_activator.moonCoordinates());
        System.out.println("Moon velocities after 20 steps:\n"+_activator.moonVelocities());

        Moon theMoon = _activator.getMoons().elementAt(1);
        Velocity theVelocity = theMoon.getVelocity();

        if (theVelocity.equals(EXAMPLE2_STEP20_MOON2))
        {
            for (int i = 0; i < 20; i++)
            {
                _activator.applyGravity();
            }

            System.out.println("Moon positions after 40 steps:\n"+_activator.moonCoordinates());
            System.out.println("Moon velocities after 40 steps:\n"+_activator.moonVelocities());

            theMoon = _activator.getMoons().elementAt(0);
            theVelocity = theMoon.getVelocity();

            if (theVelocity.equals(EXAMPLE2_STEP40_MOON1))
            {
                for (int i = 0; i < 30; i++)
                {
                    _activator.applyGravity();
                }

                System.out.println("Moon positions after 70 steps:\n"+_activator.moonCoordinates());
                System.out.println("Moon velocities after 70 steps:\n"+_activator.moonVelocities());

                theMoon = _activator.getMoons().elementAt(3);
                Coordinate theCoordinate = theMoon.getPosition();

                if (theCoordinate.equals(EXAMPLE2_STEP70_MOON4))
                {
                    for (int i = 0; i < 30; i++)
                    {
                        _activator.applyGravity();
                    }

                    System.out.println("Moon positions after 100 steps:\n"+_activator.moonCoordinates());
                    System.out.println("Moon velocities after 100 steps:\n"+_activator.moonVelocities());

                    theMoon = _activator.getMoons().elementAt(2);
                    theCoordinate = theMoon.getPosition();

                    if (theCoordinate.equals(EXAMPLE2_STEP100_MOON3))
                    {
                        if (_activator.totalEnergy() == EXAMPLE2_TOTAL_ENERGY)
                            result = true;
                    }
                }
            }
        }

        return result;
    }

    private MoonSystem _activator;
    private boolean _debug;
}