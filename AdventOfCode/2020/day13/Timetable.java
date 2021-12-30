import java.util.*;
import java.io.*;

public class Timetable
{
    public static String SEPARATOR = ",";

    public Timetable (String fileName, boolean debug)
    {
        _buses = new Vector<Bus>();
        _debug = debug;

        loadData(fileName);
    }

    private void loadData (String inputFile)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                String[] busData = line.split(SEPARATOR);

                for (int i = 0; i < busData.length; i++)
                {
                    Bus bs = new Bus(Integer.parseInt(busData[i]));

                    if (_debug)
                        System.out.println("Loaded "+bs);

                    _buses.add(bs);
                }
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