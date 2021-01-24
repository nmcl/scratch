import java.util.*;
import java.io.*;

public class Activate
{
    public static final String X_VALUE = "x=";
    public static final String Y_VALUE = "y=";
    public static final String Z_VALUE = "z=";

    public static final String DEMARCATION = ",";

    public Activate (String dataFile, boolean debug)
    {
        _system = new Vector<Moon>();
        _dataFile = dataFile;
        _debug = debug;

        createSystem();
    }

    public final Vector<Moon> getMoonSystem ()
    {
        return _system;
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