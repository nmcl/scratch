/*
 * Aft Scaffolding Control and Information Interface.class ASCII
 */

public class ASCII 
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

        String[] values = IntcodeUtil.readValues(INSTRUCTIONS);
        Camera theCamera = new Camera(values, debug);

        theCamera.takePicture();
    }

    private ASCII ()
    {
    }
}