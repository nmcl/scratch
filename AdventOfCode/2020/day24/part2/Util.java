import java.util.*;
import java.io.*;

public class Util
{
    public static final Vector<String> readLines (String inputFile)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<String> values = new Vector<String>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                values.add(line);
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

    public static final int countAdjacentBlackTiles (Vector<Coordinate> first, Vector<Coordinate> second)
    {
        int count = 0;

        return count;
    }
}