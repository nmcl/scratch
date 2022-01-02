import java.util.*;

public class Docking
{
    public static final String DATA_FILE = "data.txt";

    public static void main (String[] args)
    {
        boolean verify = false;
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-verify] [-help]");
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
                System.out.println("Verify failed!");

            System.exit(0);
        }

        Vector<Command> cmds = Util.loadCommands(DATA_FILE, debug);
        Memory mem = new Memory();

        for (int i = 0; i < cmds.size(); i++)
        {
            if (debug)
                System.out.println("Loaded:\n"+cmds.elementAt(i));

            cmds.elementAt(i).execute(mem);
        }
    }
}