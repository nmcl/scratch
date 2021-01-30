public class Simulator
{
    public static final String DATA_FILE = "data.txt";
    public static final void main (String[] args)
    {
        boolean debug = false;
        boolean verify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-verify]");
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

        MoonSystem theMoons = new MoonSystem(DATA_FILE, debug);
        long[] periods = theMoons.periodicity();
        long timeToRepeat = Util.lcm(periods[0], periods[1], periods[2]);
        
        System.out.println("It will take "+timeToRepeat+" steps to reach a repeating state.");
    }
}