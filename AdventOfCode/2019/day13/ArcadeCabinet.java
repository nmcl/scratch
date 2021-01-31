public class ArcadeCabinet
{
    public static void main (String[] args)
    {
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-help] [-debug]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;
        }
    }
}