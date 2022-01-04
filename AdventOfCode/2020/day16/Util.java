import java.util.*;
import java.io.*;

public class Util
{
    public static final Vector<Category> loadData (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Category> values = new Vector<Category>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));

            while ((line = reader.readLine()) != null)
            {
                if (line.indexOf(Command.MASK) != -1)
                {
                    if (mask != null) // new entry
                    {
                        values.add(new Command(mask, cmds, debug));
                        cmds = null;
                    }

                    mask = line;
                }
                else
                {
                    if (cmds == null)
                        cmds = new Vector<String>();

                    cmds.add(line);
                }
            }

            if (cmds != null)
                values.add(new Command(mask, cmds, debug));
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