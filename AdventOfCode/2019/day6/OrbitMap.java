public class OrbitMap
{
    public static final String TEST_DATA = "testdata.txt";
    public static final String ORBITAL_DATA = "data.txt";
    public static void main (String[] args)
    {
        boolean runVerify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-help] [-verify]");
                System.exit(0);
            }

            if ("-verify".equals(args[i]))
                runVerify = true;
        }

        if (runVerify)
        {
            verify();

            System.exit(0);
        }
    }
}