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
            Command cmd = null;
            String mask = null;
            Vector<String> cmds = null;

            while ((line = reader.readLine()) != null)
            {
                if (line.indexOf(Command.MASK) != -1)
                {
                    if (mask != null) // new entry
                    {
                        values.add(new Command(mask, cmds));
                        cmds = null;
                    }

                    nask = line;
                }
                else
                {
                    if (cmds == null)
                        cmds = new Vector<String>();

                    cmds.add(line);
                }
            }

            if (cmds != null)
                values.add(new Command(mask, cmds));
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