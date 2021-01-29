import java.util.*;
import java.io.*;

public class MoonSystem
{
    public static final String X_VALUE = "x=";
    public static final String Y_VALUE = "y=";
    public static final String Z_VALUE = "z=";

    public static final String DEMARCATION = ",";

    public MoonSystem (String dataFile, boolean debug)
    {
        _system = new Vector<Moon>();
        _dataFile = dataFile;
        _debug = debug;

        createSystem();
    }

    public final Vector<Moon> getMoons ()
    {
        return _system;
    }
    
    /*
     * Brute force won't work in any meaningful time! However, the motion of each moon in each dimension
     * is independent of motion in other dimensions so we can simulate each dimension independently. Also
     * the simulation equations are time-symmetical, i.e., the first repeated state is the initial state.
     */

     // do tbis for all axes

    public final long periodicity ()  // a hack for now
    {
        Vector<Moon> tempSystem = new Vector<Moon>();
        Enumeration<Moon> iter = _system.elements();
        Moon[] theState = new Moon[_system.size()];
        int index = 0;
        boolean found = false;

        while (iter.hasMoreElements())
        {
            Moon currentMoon = iter.nextElement();
            Moon replacement = new Moon(currentMoon.getPosition().getX(), 0, 0);

            theState[index] = new Moon(replacement);
            tempSystem.add(replacement);

            index++;
        }

        _system = tempSystem;

        long period = 0;

        while (!found)
        {
            applyGravity();
            period++;

            for (int i = 0; i < theState.length; i++)
            {
                System.out.println("**comparing "+_system.elementAt(i)+" and "+theState[i]);

                if (_system.elementAt(i).equals(theState[i]))
                    found = true;
                else
                    found = false;
            }
        }

        return period;
    }

    /*
     * Initially we won't store the data for each step but simply
     * update position and velocity in situ.
     */

    public final void applyGravity ()
    {
        for (int i = 0; i < _system.size(); i++)
        {
            Moon currentMoon = _system.elementAt(i);

            for (int j = 0; j < _system.size(); j++)
            {
                Moon nextMoon = _system.elementAt(j);

                currentMoon.updateVelocity(nextMoon);
            }
        }

        for (int i = 0; i < _system.size(); i++)
        {
            Moon currentMoon = _system.elementAt(i);

            currentMoon.updatePosition();
        }
    }

    public String moonCoordinates ()
    {
        Enumeration<Moon> iter = _system.elements();
        String toReturn = "";

        while (iter.hasMoreElements())
        {
            toReturn += iter.nextElement().getPosition()+"\n";
        }

        return toReturn;
    }

    public final String moonVelocities ()
    {
        Enumeration<Moon> iter = _system.elements();
        String toReturn = "";

        while (iter.hasMoreElements())
        {
            toReturn += iter.nextElement().getVelocity()+"\n";
        }

        return toReturn;
    }

    public final int totalEnergy ()
    {
        Enumeration<Moon> iter = _system.elements();
        int total = 0;

        while (iter.hasMoreElements())
        {
            total += iter.nextElement().getTotalEnergy();
        }

        return total;
    }

    @Override
    public String toString ()
    {
        Enumeration<Moon> iter = _system.elements();
        String toReturn = "The system: ";

        while (iter.hasMoreElements())
        {
            toReturn += iter.nextElement() + ", ";
        }

        return toReturn;
    }

    private void createSystem ()
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new FileReader(_dataFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                parseLine(line);
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
            }
        }
    }

    private void parseLine (String line)
    {
        if (_debug)
            System.out.println("Parsing data: "+line);

        int endX = line.indexOf(DEMARCATION);
        String xData = line.substring(X_VALUE.length()+1, endX);

        if (_debug)
            System.out.println("xData "+xData);

        int startY = endX +2;
        String yData = line.substring(startY+Y_VALUE.length(), line.indexOf(DEMARCATION, startY));

        if (_debug)
            System.out.println("yData "+yData);

        int endZ = line.length()-1;
        String zData = line.substring(line.indexOf(Z_VALUE)+Z_VALUE.length(), endZ);

        if (_debug)
            System.out.println("zData "+zData);

        _system.add(new Moon(Integer.parseInt(xData), Integer.parseInt(yData), Integer.parseInt(zData)));
    }
    
    private Vector<Moon> _system;
    private String _dataFile;
    private boolean _debug;
}