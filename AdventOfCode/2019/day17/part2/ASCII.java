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

        ml.createMovementFunctions();

        System.out.println("\n\n");
        String str = "L,4L,4L,6R,10L,6L,4L,4L,6R,10L,6L,12L,6R,10L,6R,8R,10L,6R,8R,10L,6L,4L,4L,6R,10L,6R,8R,10L,6L,12L,6R,10L,6R,8R,10L,6L,12L,6R,10L,6";
    }

    private ASCII ()
    {
    }
}