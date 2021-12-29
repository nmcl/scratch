import java.util.*;

public class Navigator
{
    public static final String COMMAND_FILE = "commands.txt";

    public static void main (String[] args)
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

        if (verify)
        {
            Verifier theVerifier = new Verifier(debug);

            if (theVerifier.verify())
                System.out.println("Verified ok!");
            else
                System.out.println("Verify failed!");

            System.exit(0);
        }

        Vector<Command> commands = Util.loadCommands(COMMAND_FILE, debug);
        Ship theShip = new Ship(debug);

        theShip.move(commands);

        System.out.println(theShip);
        
        System.out.println("Manhattan distance of ship: "+theShip.getManhattanDistance());
    }
}