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
    
    public Vector<Integer> evolve (int days, Vector<Integer> fish)
    {
        if (_debug)
        {
            System.out.print("Initial state: ");

            for (int j = 0; j < fish.size(); j++)
                System.out.print(fish.elementAt(j)+",");

            System.out.println();
        }

        for (int d = 0; d < days; d++)
        {
            int currentSize = fish.size();

            for (int i = 0; i < currentSize; i++)
            {
                Integer f = fish.elementAt(i);

                if (f == 0)
                {
                    fish.set(i, 6);
                    
                    fish.add(8);
                }
                else
                    fish.set(i, f -1);
            }

            if (_debug)
            {
                if (_debug)
                    System.out.print("After "+(d+1)+" days: ");

                //for (int j = 0; j < fish.size(); j++)
                //    System.out.print(fish.elementAt(j)+",");

                System.out.println();
            }
        }

        return fish;
    }

    private boolean _debug;
}