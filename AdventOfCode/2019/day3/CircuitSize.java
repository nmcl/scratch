import java.io.*;

public class TestPlotter
{
    public static final String SEPARATOR = ",";

    public static final char UP = 'U';
    public static final char DOWN = 'D';
    public static final char LEFT = 'L';
    public static final char RIGHT = 'R';

    /*
     * Building on TestPlotter, rather than have the user give
     * values for length and width for us to test against, this
     * program takes the two lines and finds the circuit board
     * size needed to cope with the wires.
     */

    public static void main (String[] args)
    {
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help] [-debug] [-width <width>] [-length <length>]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-width".equals(args[i]))
                _width = Integer.parseInt(args[i+1]);

            if ("-length".equals(args[i]))
                _length = Integer.parseInt(args[i+1]);
        }

        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        String[] line1 = null;
        String[] line2 = null;

        try
        {
            reader = new BufferedReader(new FileReader(DATA_FILE));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                if (line1 == null)
                    line1 = line.split(SEPARATOR);
                else
                {
                    if (line2 == null)
                        line2 = line.split(SEPARATOR);
                    else
                    {
                        System.out.println("Unexpected extra lines in input file!");

                        System.exit(0);
                    }
                }
            }

            if (debug)
                dumpData(line1, line2);
            else
            {
                if (checkMatrixSize(line1) && checkMatrixSize(line2))
                {
                    System.out.println("Matrix of "+_length+" and "+_width+" is sufficient.");
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
    }

    private static final boolean checkMatrixSize (String[] line)
    {
        int xPos = _length/2;
        int yPos = _width/2;

        for (String str : line)
        {
            switch (str.charAt(0))
            {
                case TestPlotter.LEFT:
                {
                    xPos -= Integer.parseInt(str.substring(1));

                    if (xPos < 0)
                    {
                        System.out.println("Instruction "+str+" moved x pointer to "+xPos);

                        return false;
                    }
                }
                break;
                case TestPlotter.RIGHT:
                {
                    xPos += Integer.parseInt(str.substring(1));

                    if (xPos > _length)
                    {
                        System.out.println("Instruction "+str+" moved x pointer to "+xPos);

                        return false;
                    }
                }
                break;
                case TestPlotter.UP:
                {
                    yPos += Integer.parseInt(str.substring(1));

                    if (yPos > _width)
                    {
                        System.out.println("Instruction "+str+" moved y pointer to "+yPos);

                        return false;
                    }
                }
                break;
                case TestPlotter.DOWN:
                {
                    yPos -= Integer.parseInt(str.substring(1));

                    if (yPos < 0)
                    {
                        System.out.println("Instruction "+str+" moved y pointer to "+yPos);

                        return false;
                    }
                }
                break;
                default:
                {
                    System.out.println("Unknown instruction: "+str.charAt(0));

                    return false;
                }
            }
        }

        return true;
    }

    private static final void dumpData (String[] line1, String[] line2)
    {
        System.out.println("Wire 1:");
        for (String str : line1)
        {
            System.out.println(str);
        }

        System.out.println("\nWire 2:");
        for (String str : line2)
        {
            System.out.println(str);
        }
    }

    private static int _length = DEFAULT_LENGTH;
    private static int _width = DEFAULT_WIDTH;

    private static final String DATA_FILE = "data.txt";
}