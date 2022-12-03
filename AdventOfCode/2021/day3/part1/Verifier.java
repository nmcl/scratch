import java.util.*;

public class Verifier
{
    public static final String EXAMPLE_DATA = "example.txt";
    public static final String EXAMPLE_GAMMA = "10110";
    public static final String EXAMPLE_EPSILON = "01001";
    public static final int EXAMPLE_POWER_CONSUMPTION = 198;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public boolean verify ()
    {
        Vector<String> data = Util.loadData(EXAMPLE_DATA, _debug);
        String gamma = Gamma.getGamma(data);
        String epsilon = Epsilon.getEpsilon(data);

        if (EXAMPLE_GAMMA.equals(gamma))
        {
            if (EXAMPLE_EPSILON.equals(epsilon))
            {
                int gammaRate = Integer.parseInt(gamma, 2);
                int epsilonRate = Integer.parseInt(epsilon, 2);
                int powerConsumption = gammaRate * epsilonRate;

                if (powerConsumption == EXAMPLE_POWER_CONSUMPTION)
                    return true;
            }
            else
                System.out.println("Failed. Wrong epsilon: "+epsilon);
        }
        else
            System.out.println("Failed. Wrong gamma: "+gamma);

        return false;
    }

    private boolean _debug;
}