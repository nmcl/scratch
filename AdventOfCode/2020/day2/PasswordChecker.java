public class PasswordChecker
{
    public static final String DATA_FILE = "input.txt";
    public static void main (String args[])
    {
        boolean debug = false;
        boolean verify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-verify] [-debug] [-help]");
                System.exit(0);
            }
            
            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;
        }

        Util.loadData(DATA_FILE, debug);
    }
}