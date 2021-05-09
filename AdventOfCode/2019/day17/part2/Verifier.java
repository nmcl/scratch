import java.util.*;

/*
 * Aft Scaffolding Control and Information Interface.
 */

public class Verifier 
{
    public static final String EXAMPLE = "R,8,R,8,R,4,R,4,R,8,L,6,L,2,R,4,R,4,R,8,R,8,R,8,L,6,L,2";
    public static final String RESULT = "A,C,B,C,A,B,B";

    public static void main (String[] args)
    {
        boolean debug = false;
        int maxCharacters = FunctionRoutine.MAX_CHARACTERS;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-char <number>] [-debug] [-help]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-char".equals(args[i]))
            {
                try
                {
                    maxCharacters = Integer.parseInt(args[i+1]);
                }
                catch (Exception ex)
                {
                    ex.printStackTrace();

                    System.exit(0);
                }
            }
        }

        FunctionRoutine fr = new FunctionRoutine(EXAMPLE, maxCharacters, debug);
        Vector<MovementFunction> functions = fr.createMovementFunctions();

        if (functions.size() != MovementLogic.NUMBER_OF_FUNCTIONS)
            System.out.println("Got the wrong number of functions: "+functions.size());
        else
        {
            if (debug)
            {
                for (int i = 0; i < MovementLogic.NUMBER_OF_FUNCTIONS; i++)
                {
                    System.out.println("Function "+(i+1)+" is "+functions.elementAt(i));
                }
            }

            MovementRoutine mr = new MovementRoutine(EXAMPLE, functions, debug);

            System.out.println("Routine is "+mr.getMainRoutine());

            if (RESULT.equals(mr.getMainRoutine()))
                System.out.println("Verified ok.");
            else
                System.out.println("Verify failed!");
        }
    }

    private Verifier ()
    {
    }
}