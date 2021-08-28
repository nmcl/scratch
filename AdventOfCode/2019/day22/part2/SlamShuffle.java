import java.math.*;

public class SlamShuffle
{
    public static final String DATA_FILE = "data.txt";
    public static final BigInteger SIZE_OF_DECK = BigInteger.valueOf(119315717514047L);;
    public static final BigInteger SHUFFLE_TIMES = BigInteger.valueOf(101741582076661L);
    public static final int CARD = 2020;
    
    // https://codeforces.com/blog/entry/72593

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

            if ("-verify".equals(args[i]))
                verify = true;
        }
    }
}