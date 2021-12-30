import java.util.*;
import java.io.*;

public class Timetable
{
    public static String SEPARATOR = ",";

    public Timetable (String fileName, boolean debug)
    {
        _buses = new Vector<Bus>();
        _debug = debug;
    }

    private void loadData (String fileName)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Command> values = new Vector<Command>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                String[] busData = line.split(SEPARATOR);
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

    private Vector<Bus> _buses;
    private boolean _debug;
}