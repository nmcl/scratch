import java.beans.DesignMode;

public class Game
{
    private static final char REMOVED_CUP = ' ';

    public Game (boolean debug)
    {
        _debug = debug;
    }

    /**
     * Each move, the crab does the following actions:
     * 
     * -The crab picks up the three cups that are immediately clockwise of the current cup. They are removed from the circle; cup spacing is adjusted as necessary to maintain the circle.
     * - The crab selects a destination cup: the cup with a label equal to the current cup's label minus one. If this would select one of the cups that was just picked up, the crab will keep subtracting one until it finds a cup that wasn't just picked up. If at any point in this process the value goes below the lowest value on any cup's label, it wraps around to the highest value on any cup's label instead.
     * - The crab places the cups it just picked up so that they are immediately clockwise of the destination cup. They keep the same order as when they were picked up.
     * - The crab selects a new current cup: the cup which is immediately clockwise of the current cup.
     */

    public final String play (String cups, int numberOfRounds)
    {
        int currentCup = 0;
        char[] theCups = cups.toCharArray();

        for (int round = 0; round < numberOfRounds; round++)
        {
            if (_debug)
            {
                System.out.println("\n-- move "+(round+1)+" --");
                System.out.print("cups: ");

                for (int i = 0; i < theCups.length; i++)
                {
                    if (i == (currentCup +1))
                        System.out.print("("+theCups[i]+") ");
                    else
                        System.out.print(theCups[i]+" ");
                }

                if (_debug)
                    System.out.println();
            }

            char[] pickup = new char[3];

            if (_debug)
                System.out.print("pick up: ");

            for (int i = 0; i < 3; i++)
            {
                int index = currentCup +i +1;

                if (index > theCups.length)  // in case it wraps.
                    index -= theCups.length;

                pickup[i] = theCups[index];
                theCups[index] = REMOVED_CUP;

                if (_debug)
                    System.out.print(pickup[i]+", ");
            }

            if (_debug)
                System.out.println();

            int destinationCup = getDestination(currentCup, theCups, pickup);
            int index = findLocation(destinationCup, theCups);

            if (_debug)
                System.out.println("destination: "+destinationCup);

            String remainingCups = new String(theCups).replaceAll(" ", "");

            theCups = new char[theCups.length];

            for (int i = 0; i < theCups.length; i++)
            {
                theCups[i] = remainingCups.charAt(i);

                if (i == index)
                {
                    for (int j = 0; j < 0; j++)
                        theCups[i+j+1] = pickup[j];

                    i += pickup.length;
                }
            }

            currentCup++;
        }

        return null;
    }

    private final int findLocation (int destinationCup, char[] cups)
    {
        for (int i = 0; i < cups.length; i++)
        {
            if (Character.getNumericValue(cups[i]) == destinationCup)
                return i;
        }

        System.err.println("Error! Can't find destination cup!!");

        return -1;
    }

    private final int getDestination (int currentCup, char[] theCups, char[] pickup)
    {
        int destinationCup = Character.getNumericValue(theCups[currentCup]) - 1;
        int lowest = lowestLabel(theCups);
        boolean done = false;

        while (!done)
        {
            if (_debug)
                System.out.println("Comparing "+destinationCup+" and "+lowest);
                
            if (destinationCup < lowest)
            {
                destinationCup = highestLabel(theCups);
                done = true;
            }
            else
            {
                boolean conflict = false;

                for (int i = 0; (i < pickup.length) && !conflict; i++)
                {
                    if (Character.getNumericValue(pickup[i]) == destinationCup)
                    {
                        destinationCup--;
                        conflict = true;
                    }
                }

                if (!conflict)
                    done = true;
            }
        }

        return destinationCup;
    }

    private final int lowestLabel (char[] theCups)
    {
        int lowest = theCups[0];

        for (int i = 1; i < theCups.length; i++)
        {
            if (theCups[i] < lowest)
                lowest = theCups[i];
        }

        return lowest;
    }

    private final int highestLabel (char[] theCups)
    {
        int highest = theCups[0];

        for (int i = 1; i < theCups.length; i++)
        {
            if (theCups[i] > highest)
                highest = theCups[i];
        }

        return highest;
    }

    private boolean _debug;
}