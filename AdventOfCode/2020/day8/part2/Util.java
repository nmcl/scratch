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
                        Integer v = Integer.parseInt(line.substring(space +1));

                        opcode = new Accumulator(v);
                    }
                    break;
                    case OpCode.JUMP:
                    {
                        Integer v = Integer.parseInt(line.substring(space +1));

                        opcode = new Jump(v);
                    }
                    break;
                    case OpCode.NOOP:
                    {
                        Integer v = Integer.parseInt(line.substring(space +1));

                        opcode = new NoOp(v);
                    }
                    break;
                    default:
                    {
                        System.out.println("Unknown opcode: "+type);
                    }
                    break;
                }

                if (debug)
                    System.out.println("Loaded "+opcode);
                    
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