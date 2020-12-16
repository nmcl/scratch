public class Intcode
{
    public static void main (String[] args)
    {
        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("[-help]");
                System.exit(0);
            }
        }
    }
}