import java.util.*;

public class ReportRepair
{
    public static final int TOTAL_TO_FIND = 2020;
    public static final String DATA_FILE = "input.txt";

    public static void main (String[] args)
    {
        int number = TOTAL_TO_FIND;
        boolean verify = false;
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-verify] [-total <number>] [-help]");
                System.exit(0);
            }

            if ("-total".equals(args[i]))
            {
                try
                {
                    number = Integer.parseInt(args[i+1]);
                }
                catch (Exception ex)
                {
                    System.err.println("Error parsing: "+args[i+1]);
                    System.exit(0);
                }
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;
        }

        if (verify)
        {
            System.exit(0);
        }

        Vector<Integer> values = Util.readValues(DATA_FILE);
        Total finder = new Total(values, debug);
    }
}