public class Trajectory
{
    public static void main (String[] args)
    {
        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-verify] [-debug] [-help]");
                System.exit(0);
            }
        }
    }
}