public class Tunnel
{
    public static final String TUNNEL_DATA = "tunnel.txt";
    
    public static final void main (String[] args)
    {
        String dataFile = TUNNEL_DATA;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-data <file>] [-help]");
                System.exit(0);
            }
            
            if ("-data".equals(args[i]))
                dataFile = args[i+1];
        }
    }
}