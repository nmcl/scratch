import java.util.*;

public class Cryostasis
{
    public static final String INSTRUCTIONS = "instructions.txt";
    public static void main (String[] args)
    {
        boolean debug = false;
        boolean manual = true;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-auto] [-debug] [-help]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-auto".equals(args[i]))
                manual = false;
        }
        
        Vector<String> instructions = IntcodeUtil.readValues(INSTRUCTIONS);
        Droid theDroid = new Droid(instructions, debug);

        if (manual)
            theDroid.stepTraverse();
        else
            theDroid.traverse();
    }

    private Cryostasis ()
    {
    }
}