import java.util.*;

/*
 * Aft Scaffolding Control and Information Interface.
 */

public class ASCII 
{
    public static final String INSTRUCTIONS = "program.txt";

    public static void main (String[] args)
    {
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-help]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;
        }

        Vector<String> values = IntcodeUtil.readValues(INSTRUCTIONS);
        Camera theCamera = new Camera(values, debug);
        Image theImage = theCamera.takePicture();
        
        if (debug)
        {
            System.out.println(theCamera);

            Vector<Coordinate> intersections = theCamera.scanForIntersections();
            Enumeration<Coordinate> iter = intersections.elements();

            while (iter.hasMoreElements())
            {
                System.out.println("Intersection at: "+iter.nextElement());
            }
        }

        Map theMap = new Map(theImage, debug);
        VacuumRobot theRobot = new VacuumRobot(theMap, IntcodeUtil.readValues(INSTRUCTIONS), debug);

        // print the camera's image/map
        
        System.out.println(theMap);

        MovementLogic ml = new MovementLogic(theMap, debug);

        Vector<MovementFunction> functions = ml.createMovementFunctions();

        if (functions.size() != MovementLogic.NUMBER_OF_FUNCTIONS)
            System.out.println("Got the wrong number of functions: "+functions.size());
        else
        {
            for (int i = 0; i < MovementLogic.NUMBER_OF_FUNCTIONS; i++)
            {
                System.out.println("Function "+(i+1)+" is "+functions.elementAt(i));
            }
        }
    }

    private ASCII ()
    {
    }
}