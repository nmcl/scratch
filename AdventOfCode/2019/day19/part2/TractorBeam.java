import java.util.*;

public class TractorBeam
{
    public static final String INSTRUCTIONS = "instructions.txt";

    public static final int SPACE_SIZE = 100;

    public static final void main (String[] args)
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

        Vector<String> values = IntcodeUtil.readValues(INSTRUCTIONS);
    }

    private TractorBeam ()
    {
    }
}