import java.util.*;

public class TractorBeam
{
    public static final String INSTRUCTIONS = "instructions.txt";

    public static final int SPACE_SIZE = 50;

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
        int count = 0;

	    for (int x = 0; x < SPACE_SIZE; x++)
        {
	        for (int y = 0; y < SPACE_SIZE; y++)
            {
                Intcode computer = new Intcode(values, debug);

		        computer.setInputs(""+y, ""+x);

                System.out.println("executing");

		        computer.executeProgram();

                System.out.println("done");
                
                Vector<String> outputs = computer.getOutputs();
                Enumeration<String> iter = outputs.elements();

                while (iter.hasMoreElements())
                {
                    String value = iter.nextElement();

                    if (DroneStatus.BEING_PULLED.equals(value))
                        count++;
                }
		    }
	    }

        System.out.println("count "+count);
    }

    private TractorBeam ()
    {
    }
}