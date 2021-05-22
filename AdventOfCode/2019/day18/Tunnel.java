public class Tunnel
{
    public static final void main (String[] args)
    {
        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-help]");
                System.exit(0);
            }
        }
    }
}