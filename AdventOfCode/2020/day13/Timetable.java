import java.util.*;
import java.io.*;

public class Timetable
{
    public static String SEPARATOR = ",";
    public static String OUT_OF_SERVICE = "x";

    public Timetable (String fileName, boolean debug)
    {
        _earliestDeparture = 0;
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
                if (_earliestDeparture == 0)
                {
                    _earliestDeparture = Integer.parseInt(line);

                    if (_debug)
                        System.out.println("Earliest departure time: "+_earliestDeparture+"\n");
                }
                else
                {
                    String[] busData = line.split(SEPARATOR);

                    for (int i = 0; i < busData.length; i++)
                    {
                        if (OUT_OF_SERVICE.equals(busData[i]))
                        {
                            if (_debug)
                                System.out.println("Loaded out of service bus.");
                        }
                        else
                        {
                            Bus bs = new Bus(Integer.parseInt(busData[i]), _earliestDeparture);

                            if (_debug)
                                System.out.println("Loaded "+bs);

                            _buses.add(bs);
                        }
                    }
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

        _earliestDeparture = _buses.elementAt(0).nextDeparture();

        for (int i = 0; i < _buses.size(); i++)
        {
            if (_buses.elementAt(i).nextDeparture() < _earliestDeparture)
                _earliestDeparture = _buses.elementAt(i).nextDeparture();
        }
    }

    private int _earliestDeparture;
    private Vector<Bus> _buses;
    private boolean _debug;
}