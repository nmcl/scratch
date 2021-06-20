import java.util.*;

/*
 * We need to get all of the keys. We only need to open doors if they prevent
 * us from getting to a key.
 * 
 * - Order the keys and their locations first in the tunnels?
 * - The order of keys is always a to z? Not necessarily.
 * - If we get to a door and we don't have the key to get through it then backtrack
 *   and find the key? Or don't even go there in the first place?
 * - If another key is closer to our current location than going through the
 *   next door then collect the key for shortest path.
 * - Go for all keys in sae direction until we hit a door that
 *   we can't pass then change direction.
 *
 * BFS from current location to next key.
 */

public class Explorer
{
    public Explorer (Map theMap, boolean debug)
    {
        _theMap = theMap;
        _keys = new char[_theMap.numberOfKeys()];
        _keysFound = 0;
        _currentLocation = _theMap.getStartingNode();
        _numberOfSteps = 0;
        _debug = debug;
    }

   /*
    * Algorithm:
    *
    * - initial starting point @
    * - move to find next key
    * - if we hit a door ...
    *      if we have the key, keep going
    *      if we do not have the key, we can't get through the door so we
    *      need to stop going in that direction and find the key.
    * - each time we find a key, reset the starting point and remember
    *   to reset the visited bit in each Node.
    * - keep going until we hit a door or find all keys
    */

    public int findKeys ()  // May move this to a test class (derived from Explorer)
    {
        Node start = _currentLocation;
        ArrayDeque<Node> queue = new ArrayDeque<Node>();

        System.out.println("Starting at "+start.getCell().getContents());

        start.markAsVisited();  // we start here but don't add to queue
        traverse(start, queue);

        while (!queue.isEmpty())
        {
            traverse(queue.remove(), queue);
        }

        return _numberOfSteps;
    }
    
    @Override
    public String toString ()
    {
        if (_keysFound > 0)
        {
            String str = "Keys found: ";

            for (int i = 0; i < _keysFound; i++)
            {
                str += ", "+_keys[i];
            }

            return str;
        }
        else
            return "No keys found.";
    }

    /*
     * (i)   - Visit the adjacent unvisited Node. Mark it as visited. Print contents. Insert it in the queue.
     * (ii)  − If no adjacent Node is found, remove the first Node from the queue if possible.
     * (iii) - Repeat (i) and (ii) until the queue is empty.
     */

    private void traverse (Node curr, ArrayDeque<Node> queue)
    {
        Node next = tryAdjacentNode(curr, Node.UP_NODE);  // only return non-null if not visited

        if (next != null)
        {
            System.out.println("Visiting up: "+curr.getCell().getContents()+" "+next.getCell().getContents());

            next.markAsVisited();

            // if a door or key we could change content to . afterwards

            queue.add(next);
        }

        next = tryAdjacentNode(curr, Node.DOWN_NODE);

        if (next != null)
        {
            System.out.println("Visiting down: "+curr.getCell().getContents()+" "+next.getCell().getContents());

            next.markAsVisited();

            queue.add(next);
        }

        next = tryAdjacentNode(curr, Node.LEFT_NODE);

        if (next != null)
        {
            System.out.println("Visiting left: "+curr.getCell().getContents()+" "+next.getCell().getContents());

            next.markAsVisited();

            queue.add(next);
        }

        next = tryAdjacentNode(curr, Node.RIGHT_NODE);

        if (next != null)
        {
            System.out.println("Visiting right: "+curr.getCell().getContents()+" "+next.getCell().getContents());

            next.markAsVisited();

            queue.add(next);
        }
    }

    private Node tryAdjacentNode (Node curr, int direction)
    {
        Node nextNode = curr.getAdjacentNode(direction);

        /*
         * If it's a wall then we can't move in that direction.
         * If it's a key, then grab it.
         * If it's a door, we check to see if we have the right key
         * and if not, we don't move in that direction for now.
         */

        if (nextNode != null)
        {
            if (!nextNode.hasBeenVisited())
            {
                if (nextNode.getCell() != null)
                {
                    if (!nextNode.getCell().isWall())
                    {
                        
                        return nextNode;
                    }
                }
            }
        }

        return null;
    }

    private boolean hasKeyForDoor (char door)
    {
        System.out.println("Found door "+door);

        for (int i = 0; i < _keys.length; i++)
        {
            if (((int)_keys[i] - (int)door) == CellId.DOOR_CODE)
            {
                System.out.println("Have key "+_keys[i]);

                return true;
            }
        }

        return false;
    }

    /*
     * Could encode the key set as a bitmask to make comparisons fast.
     * However, it's not critical at this stage.
     */

    private Map _theMap;
    private char[] _keys;
    private int _keysFound;
    private Node _currentLocation;
    private int _numberOfSteps;
    private boolean _debug;
}