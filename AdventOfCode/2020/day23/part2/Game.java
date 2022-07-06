import java.util.*;

public class Game
{
    // Brute force approach won't work. Use circular linked list at scale.
    
    private static final char REMOVED_CUP = ' ';

    public Game (boolean debug)
    {
        _debug = debug;
    }

    /**
     * Each move, the crab does the following actions:
     * 
     * - The crab picks up the three cups that are immediately clockwise of the current cup. They are removed from the circle; cup spacing is adjusted as necessary to maintain the circle.
     * - The crab selects a destination cup: the cup with a label equal to the current cup's label minus one. If this would select one of the cups that was just picked up, the crab will keep subtracting one until it finds a cup that wasn't just picked up. If at any point in this process the value goes below the lowest value on any cup's label, it wraps around to the highest value on any cup's label instead.
     * - The crab places the cups it just picked up so that they are immediately clockwise of the destination cup. They keep the same order as when they were picked up.
     * - The crab selects a new current cup: the cup which is immediately clockwise of the current cup.
     */

    public final String play (String cupsAsString, int numberOfRounds)
    {
        LinkedList<Integer> cups = getCupLabels(cupsAsString);
        int currentCupIndex = 0;
        int numberOfCups = cups.size();

        for (int round = 0; round < numberOfRounds; round++)
        {
            if (_debug)
            {
                System.out.println("\n-- move "+(round+1)+" --");
                System.out.print("cups: ");

                for (int i = 0; i < numberOfCups; i++)
                {
                    if (i == currentCupIndex)
                        System.out.print("("+cups.get(i)+") ");
                    else
                        System.out.print(cups.get(i)+" ");
                }

                if (_debug)
                    System.out.println();
            }

            ArrayList<Integer> pickup = new ArrayList<Integer>();
            int insertLocation = 0;

            for (int i = 1; i <= 3; i++)
            {
                int pickupCupIndex = ((currentCupIndex + i) % numberOfCups);

                pickup.add(cups.get(pickupCupIndex));

                if (pickupCupIndex < currentCupIndex)
                {
                    insertLocation++;
                }
            }

            cups.removeAll(pickup);

            if (_debug)
                System.out.println("pick up: "+pickup);

            int index = cups.get((currentCupIndex - insertLocation + 1) % (numberOfCups - pickup.size()));
            int destinationCup = getDestination(cups.get(currentCupIndex - insertLocation) - 1, pickup, lowestLabel(cupsAsString.toCharArray()), highestLabel(cupsAsString.toCharArray()));

            if (_debug)
                System.out.println("destination: "+destinationCup);

            cups.addAll(cups.indexOf(destinationCup) + 1, pickup);

            currentCupIndex = cups.indexOf(index);
        }

        int start = cups.indexOf(1);
        StringBuilder concatenate = new StringBuilder();

        for (int i = 1; i < numberOfCups; i++)
        {
            concatenate.append(cups.get((start + i) % numberOfCups));
        }

        return concatenate.toString();
    }

    private final LinkedList<Integer> getCupLabels (String cups)
    {
        char[] theCups = cups.toCharArray();
        LinkedList<Integer> cupLabels = new LinkedList<Integer>();

        for (int i = 0; i < theCups.length; i++)
        {
            cupLabels.add(Character.getNumericValue(theCups[i]));
        }

        return cupLabels;
    }

    // find destination cup label in those remaining

    private final int getDestination (int initialDestination, ArrayList<Integer> pickup, int min, int max)
    {
        int destinationCup = initialDestination < min ? max : initialDestination;

        while (pickup.contains(destinationCup))
        {
            destinationCup = destinationCup - 1 < min ? max : destinationCup - 1;
        }

        return destinationCup;
    }

    private final int lowestLabel (char[] theCups)
    {
        int lowest = Character.getNumericValue(theCups[0]);

        for (int i = 1; i < theCups.length; i++)
        {
            if (Character.getNumericValue(theCups[i]) < lowest)
                lowest = Character.getNumericValue(theCups[i]);
        }

        return lowest;
    }

    private final int highestLabel (char[] theCups)
    {
        int highest = Character.getNumericValue(theCups[0]);

        for (int i = 1; i < theCups.length; i++)
        {
            if (Character.getNumericValue(theCups[i]) > highest)
                highest = Character.getNumericValue(theCups[i]);
        }

        return highest;
    }

    private boolean _debug;
}