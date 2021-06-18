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
        _debug = debug;
    }

    /*
     * May insert Maze mapper from previous Days. But for now
     * let's see what a BF search produces.
     */

    public void moveAroundMap ()  // May move this to a test class (derived from Explorer)
    {
        Node start = _theMap.getStartingNode();
        ArrayDeque<Node> queue = new ArrayDeque<Node>();

        System.out.println("Starting at "+start.getCell());

        start.markAsVisited();  // we start here but don't add to queue
        traverse(start, queue);

        while (!queue.isEmpty())
        {
            traverse(queue.remove(), queue);
        }
    }
    
    /*
     * (i)   - Visit the adjacent unvisited Node. Mark it as visited. Print contents. Insert it in the queue.
     * (ii)  − If no adjacent Node is found, remove the first Node from the queue if possible.
     * (iii) - Repeat (i) and (ii) until the queue is empty.
     */

    private void traverse (Node curr, ArrayDeque<Node> queue)
    {
        Node next = curr.tryAdjacentNode(Node.UP_NODE);

        if (next != null)
        {
            System.out.println("Visiting up: "+curr.getCell()+" "+next.getCell());

            next.markAsVisited();

            queue.add(next);
        }

        next = curr.tryAdjacentNode(Node.DOWN_NODE);

        if (next != null)
        {
            System.out.println("Visiting down: "+curr.getCell()+" "+next.getCell());

            next.markAsVisited();

            queue.add(next);
        }

        next = curr.tryAdjacentNode(Node.LEFT_NODE);

        if (next != null)
        {
            System.out.println("Visiting left: "+curr.getCell()+" "+next.getCell());

            next.markAsVisited();

            queue.add(next);
        }

        next = curr.tryAdjacentNode(Node.RIGHT_NODE);

        if (next != null)
        {
            System.out.println("Visiting right: "+curr.getCell()+" "+next.getCell());

            next.markAsVisited();

            queue.add(next);
        }
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
     * Could encode the key set as a bitmask to make comparisons fast.
     * However, it's not critical at this stage.
     */

    private Map _theMap;
    private char[] _keys;
    private int _keysFound;
    private boolean _debug;
}