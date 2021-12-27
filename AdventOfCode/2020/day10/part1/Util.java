import java.util.*;
import java.io.*;

public class Util
{
    public static final Device largest (Vector<JoltageAdapter> data, boolean debug)
    {
        int max = data.elementAt(0).outputJoltage();

        for (int i = 0; i < data.size(); i++)
        {
            if (data.elementAt(i).outputJoltage() > max)
                max = data.elementAt(i).outputJoltage();
        }

        return new Device(new JoltageAdapter(max +3, debug), debug);
    }

    public static final Vector<JoltageAdapter> loadData (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<JoltageAdapter> values = new Vector<JoltageAdapter>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                values.add(new JoltageAdapter(Integer.parseInt(line), debug));
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