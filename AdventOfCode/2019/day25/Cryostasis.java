impport java.util.*;

public class Cryostatis
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

            Vector<String> instructions = IntcodeUtil.readValues(INSTRUCTIONS);
        }
    }

    private Cryostatis ()
    {
    }
}