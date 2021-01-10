public class Placement
{
    /*
     * Each asteroid is a point in the centre of it's "grid/square". To determine if
     * an asteroid is visible from another without being blocked, we "just" need to
     * be able to plot a straight line between the starting asteroid and the current
     * one in question.
     */

    public static final void main (String[] args)
    {
        boolean debug = false;
        boolean verify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-help] [-debug] [-verify]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;
        }
    }
}