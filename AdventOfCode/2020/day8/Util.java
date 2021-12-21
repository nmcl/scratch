import java.util.*;
import java.io.*;

public class Util
{
    public static final String SPACE = " ";

    public static final Vector<OpCode> loadData (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<OpCode> values = new Vector<OpCode>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                int space = line.indexOf(SPACE);
                String type = line.substring(0, space).trim();
                OpCode opcode = null;

                switch (type)
                {
                    case OpCode.ACCUMULATOR:
                    {

                    }
                    break;
                    case OpCode.JUMP:
                    {

                    }
                    break;
                    case OpCode.NOOP:
                    {

                    }
                    break;
                    default:
                    {
                        System.out.println("Unknown opcode: "+type);
                    }
                    break;
                }

                values.add(opcode);
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