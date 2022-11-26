import java.util.*;

public class Dive
{
    public static final String DATA_FILE = "input.txt";

    public static void main (String[] args)
    {
        boolean verify = false;
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-verify] [-help]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;
        }

        if (verify)
        {
            Verifier v = new Verifier(debug);
            
            if (v.verify())
                System.out.println("Verified ok.");
            else
                System.out.println("Verify failed.");

            System.exit(0);
        }

        Submarine s = new Submarine(debug);
        Course position = s.move(DATA_FILE);

        if (debug)
            System.out.println("Final position: "+position);

        int value = position.getPosition() * position.getDepth();

        System.out.println("Value obtained: "+value);
    }
}