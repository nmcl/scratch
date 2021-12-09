import java.util.*;
import java.io.*;

public class Scanner
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

        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Plane thePlane = new Plane(debug);

        try
        {
            reader = new BufferedReader(new FileReader(DATA_FILE));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                Barcode b = new Barcode(line, debug);

                if (debug)
                    System.out.println("Loaded: "+b);

                thePlane.addSeat(b.getSeat());
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
            }
        }

        Vector<Seat> emptySeats = thePlane.emptySeats();
        Enumeration<Seat> iter = emptySeats.elements();
        int row = -1;
        Seat theSeat = null;

        while (iter.hasMoreElements())
        {
            Seat s = iter.nextElement();

            if (debug)
                System.out.println("Empty seat: "+s);

            /*
             * The seat will have a unique row as it's the last one
             * on the plane.
             */

            if (s.row() == row)
            {
                row = -1;
                theSeat = null;
            }
            else
            {
                row = s.row();
                theSeat = s;
            }
        }

        System.out.println("Seat: "+theSeat);
    }
}