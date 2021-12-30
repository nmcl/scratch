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

    // https://en.wikipedia.org/wiki/Chinese_remainder_theorem

    public final long earliestTimestamp ()
    {
        Bus theBus = _buses.get(_buses.size() - 1);
        long remainder = theBus.getRemainder();
        long theMod = theBus.getID();

        System.out.println("starting with "+remainder+" and "+theMod);

        // go down through the other buses

        for (int i = _buses.size() - 2; i >=0; i--)
        {
            theBus = _buses.elementAt(i);

            long mod = theBus.getID();
            long rem = theBus.getRemainder();
     
            while (remainder % mod != rem)
            {
                remainder += theMod;
            }
      
            theMod *= mod;
        }

        System.out.println("returning "+remainder);

        return remainder;
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
                            Bus bs = new Bus(Integer.parseInt(busData[i]), i);

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
    }

    private int _earliestDeparture;
    private Vector<Bus> _buses;
    private boolean _debug;
}