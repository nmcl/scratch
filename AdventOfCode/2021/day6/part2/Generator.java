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

    public long[] evolve (int days, Vector<Integer> fish)
    {
        if (_debug)
        {
            System.out.print("Initial state: ");

            for (int j = 0; j < fish.size(); j++)
                System.out.print(fish.elementAt(j)+",");

            System.out.println();
        }

        long[] theFish = {0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L};
        int base = 0;

        Arrays.stream(theFish).forEach(i -> theFish[(int) i]++);

        for (int i = 0; i < fish.size(); i++)
            theFish[i] = (long) fish.elementAt(i);

        for (int i = 0; i < 9; i++)
            System.out.println(theFish[i]);

        for (int d = 0; d < days; d++)
        {
            //if (_debug)
                System.out.println("Day: "+(d+1));

            theFish[(base + 7) % 9] += theFish[base];
            System.out.println("got "+theFish[(base + 7) % 9]);

            base = (base + 1) % 9;
            System.out.println("base "+base);
        }

        return theFish;
    }

    private boolean _debug;
}