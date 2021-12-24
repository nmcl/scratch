import java.util.*;

public class Encoding
{
    public static final String DATA_FILE = "data.txt";
    public static final int PREAMBLE = 25;

    public static void main (String[] args)
    {
        boolean debug = false;
        boolean verify = false;

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

        Vector<Long> data = Util.loadData(DATA_FILE, debug);
        XMAS parser = new XMAS(debug);
        Vector<Long> results = parser.validate(data, PREAMBLE);
        Vector<Long> sequence = parser.findSequence(data, results.elementAt(0));

        System.out.println("Encryption weakness: "+(Util.smallest(sequence) + Util.largest(sequence)));
    }
}