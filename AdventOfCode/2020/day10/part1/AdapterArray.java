import java.util.*;

public class AdapterArray
{
    public static final String DATA_FILE = "data.txt";

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

        Vector<JoltageAdapter> adapters = Util.loadData(DATA_FILE, debug);
        Device device = Util.largest(adapters, debug);
        Connector con = new Connector(debug);
        Vector<JoltageAdapter> sorted = con.connect(adapters);

        sorted.add(device.getAdapter());
        con.getDifferenceThree().add(device.getAdapter());
        
        int result = con.getDifferenceOne().size() * con.getDifferenceThree().size();

        System.out.println("Number of 1-jolt differences multiplied by the number of 3-jolt differences: "+result);
    }
}