import java.util.*;

public class ReportRepair
{
    public static final int TOTAL_TO_FIND = 2020;
    public static final String DATA_FILE = "input.txt";

    public static void main (String[] args)
    {
        int number = TOTAL_TO_FIND;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-total <number>] [-help]");
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
        }

        Vector<Integer> values = Util.readValues(DATA_FILE);
        Total finder = new Total(values);
    }
}