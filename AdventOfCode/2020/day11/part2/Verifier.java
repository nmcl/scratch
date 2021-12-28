import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_BASE = "example";
    public static final int EXAMPLE_OCCUPIED_SEATS = 26;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Grid thePlane = new Grid(EXAMPLE_BASE+".txt", _debug);
        Grid[] evolution = new Grid[6];

        if (_debug)
            System.out.println(thePlane);

        for (int i = 0; i < 6; i++)
        {
            if (_debug)
                System.out.println("Iteration: "+(i+1));

            evolution[i] = new Grid(EXAMPLE_BASE+""+(i+1)+".txt", _debug);

            thePlane.evolve();

            System.out.println("\nAfter "+(i+1)+" round:\n");
            System.out.println(thePlane);

            if (!evolution[i].equals(thePlane))
                return false;
        }

        if (thePlane.occupiedSeats() == EXAMPLE_OCCUPIED_SEATS)
            return true;
        else
        {
            System.out.println("Incorrect number of occupied seats: "+thePlane.occupiedSeats());

            return false;
        }
    }

    private boolean _debug;
}