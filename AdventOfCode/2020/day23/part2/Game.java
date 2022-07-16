import java.util.*;

public class Game
{
    // Brute force approach won't work. Use circular linked list at scale.

    private static final int MAX_CUP_LABEL = 1000000;

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

    public final long play (String cupsAsString, int numberOfRounds)
    {
        CircularLinkedList theCups = new CircularLinkedList();
        char[] cupLabels = cupsAsString.toCharArray();
        int maxCup = highestLabel(cupLabels);
        int minCup = lowestLabel(cupLabels);

        // add the cup labels

        for (int i = 0; i < cupLabels.length; i++)
        {
            theCups.add(Character.getNumericValue(cupLabels[i]));
        }

        System.out.println("cups "+theCups);

        // now add the remainder (assume no gaps in first list)

        for (int i = maxCup +1; i < MAX_CUP_LABEL; i++)
        {
            theCups.add(i);
        }

        Node index = theCups.getCurrent();

        for (int round = 0; round < numberOfRounds; round++)
        {
            if (_debug)
                System.out.println("\n-- move "+(round+1)+" --");

            CircularLinkedList pickup = theCups.removeFrom(index);

            if (_debug)
                System.out.print("pick up: "+pickup);

            int destination = index.getValue() - 1;

            if (destination < minCup)
                destination = MAX_CUP_LABEL;

            while (pickup.asList().contains(destination))
            {
                destination = (destination - 1 < minCup) ? MAX_CUP_LABEL : destination - 1;
            }

            System.out.println("destination: "+destination);

            theCups.addTo(theCups.getEntries().get(destination), pickup);

            index = index.getNext();
        }

        return (long) theCups.getEntries().get(1).getNext().getValue() *
                (long) theCups.getEntries().get(1).getNext().getNext().getValue();
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