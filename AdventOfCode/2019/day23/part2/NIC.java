import java.util.*;

public class NIC
{
    public static final String INSTRUCTIONS = "instructions.txt";

    public static void main (String[] args)
    {
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-help]");

                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;
        }

        Network theNewtork = new Network(debug, INSTRUCTIONS);

        System.out.println("First Y value delivered by the NAT to the computer at address 0 twice in a row: "+theNewtork.getRepeatY());
    }
}