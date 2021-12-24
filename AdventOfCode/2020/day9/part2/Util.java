import java.util.*;
import java.io.*;

public class Util
{
    public static final long smallest (Vector<Long> data)
    {
        long min = data.elementAt(0);

        for (int i = 0; i < data.size(); i++)
        {
            if (data.elementAt(i) < min)
                min = data.elementAt(i);
        }

        return min;
    }

    public static final long largest (Vector<Long> data)
    {
        long max = data.elementAt(0);

        for (int i = 0; i < data.size(); i++)
        {
            if (data.elementAt(i) > max)
                max = data.elementAt(i);
        }

        return max;
    }

    public static final Vector<Long> loadData (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Long> values = new Vector<Long>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                values.add(Long.parseLong(line));
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

        return values;
    }

    private Util ()
    {
    }
}