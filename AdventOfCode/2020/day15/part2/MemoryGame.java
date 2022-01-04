public class MemoryGame
{
    public static int[] STARTING_NUMBERS = { 2, 15, 0, 9, 1, 20 };

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

        Memory mem = new Memory(debug);

        for (int i = 0; i < STARTING_NUMBERS.length; i++)
        {
            mem.speakNumber(STARTING_NUMBERS[i]);
        }

        Integer lastNumberSpoken = STARTING_NUMBERS[STARTING_NUMBERS.length -1];

        for (int i = STARTING_NUMBERS.length; i < 30000000; i++)
        {
            if (debug)
                System.out.println("Turn number "+i+" spoke "+lastNumberSpoken);

            if (mem.firstTimeSpoken(lastNumberSpoken))
                lastNumberSpoken = 0;
            else
                lastNumberSpoken = mem.getTurnDifference(lastNumberSpoken);

            mem.speakNumber(lastNumberSpoken);
        }

        System.out.println("Final value: "+lastNumberSpoken);
    }
}