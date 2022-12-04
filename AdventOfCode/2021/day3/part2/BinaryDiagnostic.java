import java.util.*;

public class BinaryDiagnostic
{
    public static final String DATA_FILE = "input.txt";

    public static void main (String[] args)
    {
        boolean verify = false;
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-verify] [-help]");
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
                System.out.println("Verify failed.");

            System.exit(0);
        }

        Vector<String> data = Util.loadData(DATA_FILE, debug);
        String oxygen = Oxygen.getOxygen(data, 0);
        String co2 = CO2.getCO2(data, 0);
        int oxygenRating = Integer.parseInt(oxygen, 2);
        int co2Rating = Integer.parseInt(co2, 2);
        int lifesupportRating = oxygenRating * co2Rating;

        System.out.println("Life support rating: "+lifesupportRating);
    }
}