public class RemoteControl
{
    public static void main (String[] args)
    {
        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[0]))
            {
                System.out.println("Usage: [-help]");
                System.exit(0);
            }
        }
    }
}