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

        theCamera.takePicture();
        
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

        VacuumRobot theRobot = new VacuumRobot(theCamera.getImage().scannedLines(), IntcodeUtil.readValues(INSTRUCTIONS), debug);

        theRobot.printCells();
    }

    private ASCII ()
    {
    }
}