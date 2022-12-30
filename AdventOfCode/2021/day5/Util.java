import java.util.*;
import java.io.*;

public class Util
{
    public static final String DELIMITER = " -> ";

    public static Vector<Coordinate> loadCoordinates (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Coordinate> results = new Vector<Coordinate>();
        
        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            if (debug)
                System.out.println("Loaded:\n");

            while ((line = reader.readLine()) != null)
            {
                int first = line.indexOf(",");
                int X1 = Integer.parseInt(line.substring(0, first));
                int second = line.indexOf(DELIMITER);
                int Y1 = Integer.parseInt(line.substring(first +1, second));

                int third = line.lastIndexOf(",");
                int X2 = Integer.parseInt(line.substring(second + DELIMITER.length(), third));
                int Y2 = Integer.parseInt(line.substring(third +1));

                if (debug)
                    System.out.println(X1+","+Y1+DELIMITER+X2+","+Y2);

                // In part 1 only work with horizontal or vertical lines

                if ((X1 == X2) || (Y1 == Y2))
                {
                    results.add(new Coordinate(X1, Y1));
                    results.add(new Coordinate(X2, Y2));
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

        return results;
    }
}