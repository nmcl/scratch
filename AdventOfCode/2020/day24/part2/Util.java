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

    public static final int commonBlackTiles (HashSet<Coordinate> blackTiles, HashSet<Coordinate> adjacentTiles)
    {
        int count = 0;
        Iterator<Coordinate> iter = blackTiles.iterator();

        while (iter.hasNext())
        {
            if (adjacentTiles.contains(iter.next()))
                count++;
        }

        return count;
    }
}