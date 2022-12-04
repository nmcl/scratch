import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final String EXAMPLE_OXYGEN = "10111";
    public static final String EXAMPLE_CO2 = "01010";
    public static final int EXAMPLE_LIFESUPPORT_RATING = 230;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<String> data = Util.loadData(EXAMPLE_DATA, _debug);
        String oxygen = Oxygen.getOxygen(data, 0);
        String co2 = CO2.getCO2(data, 0);

        if (EXAMPLE_OXYGEN.equals(oxygen))
        {
            if (EXAMPLE_CO2.equals(co2))
            {
                int oxygenRating = Integer.parseInt(oxygen, 2);
                int co2Rating = Integer.parseInt(co2, 2);
                int lifesupportRating = oxygenRating * co2Rating;

                if (lifesupportRating == EXAMPLE_LIFESUPPORT_RATING)
                    return true;
            }
            else
                System.out.println("Failed. Wrong CO2: "+co2);
        }
        else
            System.out.println("Failed. Wrong Oxygen: "+oxygen);

        return false;
    }

    private boolean _debug;
}