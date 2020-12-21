import java.io.*;

public class CircuitSize
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
        boolean dump = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help] [-debug] [-dump]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-dump".equals(args[i]))
                dump = true;
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

            /*
             * Keep test plotting and extending the matrix (circuit board sizse)
             * until we don't need to expand it any more for that wire. This may
             * not generate the minumum size board needed (since we assume the origin
             * is in the middle, for instance) but it's large enough to cope with
             * each wire without being far too big.
             */

            if (dump)
                dumpData(line1, line2);
            else
            {
                int startLength = _length;
                int startWidth = _width;
                boolean done = false;

                do
                {
                    checkMatrixSize(line1, debug);

                    if (startLength == _length)
                    {
                        if (startWidth == _width)
                            done = true;
                        else
                            startWidth = _width;
                    }
                    else
                        startLength = _length;
                }
                while (!done);

                done = false;

                do
                {
                    checkMatrixSize(line2, debug);

                    if (startLength == _length)
                    {
                        if (startWidth == _width)
                            done = true;
                        else
                            startWidth = _width;
                    }
                    else
                        startLength = _length;
                }
                while (!done);

                System.out.println("Matrix of length:"+_length+" and width:"+_width+" is sufficient.");
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

    private static final boolean checkMatrixSize (String[] line, boolean debug)
    {
        int xPos = _length/2;
        int yPos = _width/2;

        for (String str : line)
        {
            switch (str.charAt(0))
            {
                case TestPlotter.LEFT:
                {
                    int left = Integer.parseInt(str.substring(1));

                    if (xPos - left < 0)
                    {
                        _length += left * 2; // origin should be in the middle

                        xPos = 0;
                    }
                    else
                        xPos -= left;
                }
                break;
                case TestPlotter.RIGHT:
                {
                    int right = Integer.parseInt(str.substring(1));

                    if (xPos + right > _length)
                    {
                        _length += right * 2; // origin should be in the middle

                        xPos += right;
                    }
                    else
                        xPos += right;
                }
                break;
                case TestPlotter.UP:
                {
                    int up = Integer.parseInt(str.substring(1));

                    if (yPos + up > _width)
                    {
                        _width += up * 2; // origin should be in the middle

                        yPos += up;
                    }
                    else
                        yPos += up;
                }
                break;
                case TestPlotter.DOWN:
                {
                    int down = Integer.parseInt(str.substring(1));

                    if (yPos - down < 0)
                    {
                        _width += down * 2; // origin should be in the middle

                        yPos = 0;
                    }
                    else
                        yPos -= down;
                }
                break;
                default:
                {
                    System.out.println("Unknown instruction: "+str.charAt(0));

                    return false;
                }
            }
        }

        if (debug)
            System.out.println("length: "+_length+" and width: "+_width);

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

    private static int _length = 0;
    private static int _width = 0;

    private static final String DATA_FILE = "data.txt";
}