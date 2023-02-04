import java.util.*;

public class Generator
{
    public Generator (boolean debug)
    {
        _debug = debug;
    }

    /*
     * The original solution is fine up to about 150 days as a brute force
     * approach but we need something better. If we keep count of how many
     * fish are alive each day then we can work out the next day's count from there.
     */

    public Long[] evolve (int days, Vector<Integer> fish)
    {
        if (_debug)
        {
            System.out.print("Initial state: ");

            for (int j = 0; j < fish.size(); j++)
                System.out.print(fish.elementAt(j)+",");

            System.out.println();
        }

        Long[] theFish = new Long[9];
        int base = 0;

        for (int i = 0; i < 9; i++)
            theFish[i] = 0L;

        for (int i = 0; i < fish.size(); i++)
            theFish[i] = (long) fish.elementAt(i);

        for (int d = 0; d < days; d++)
        {
            if (_debug)
                System.out.println("Day: "+(d+1));

            theFish[(base + 7) % 9] += theFish[base];

            base = (base + 1) % 9;
        }

        return theFish;
    }

    private boolean _debug;
}