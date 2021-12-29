import java.util.*;
import java.io.*;

public class Util
{
    public static final Vector<Command> loadCommands (String inputFile, boolean debug)
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
                char action = line.charAt(0);
                int amount = Integer.parseInt(line.substring(1));

                switch (action)
                {
                    case Action.EAST:
                    case Action.WEST:
                    case Action.NORTH:
                    case Action.SOUTH:
                    case Action.LEFT:
                    case Action.RIGHT:
                    case Action.FORWARD:
                    {
                        values.add(new Command(action, amount));
                    }
                    break;
                    default:
                    {
                        System.out.println("Unknown action: "+action);
                    }
                    break;
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

        return values;
    }

    private Util ()
    {
    }
}