public class ReportRepair
{
    public static final int TOTAL_TO_FIND = 2020;

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
                    number = Integer
                }
            }
        }
    }
}