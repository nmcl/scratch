import java.util.*;

public class RepairDroid
{
    public static final String INITIAL_INPUT = Integer.toString(DroidMovement.NORTH);

    public RepairDroid (Vector<String> instructions, boolean debug)
    {
        _debug = debug;
        _theComputer = new Intcode(instructions, INITIAL_INPUT, _debug);
        _currentLocation = new Coordinate(0, 0);  // starting location
        _theMap = new Maze();
        _trackTaken = new Stack<Integer>();

        _theMap.updateTile(_currentLocation, TileId.DROID);
    }

    public final int moveToOxygenStation ()
    {
        int numberOfSteps = 0;

        // create a map first!

        explore();

        return numberOfSteps;
    }

    public void printGrid ()
    {
        System.out.println(_theMap);
    }

    /*
     * If we run into a wall then try a different direction.
     * If we can't move other than backwards then do that.
     * Don't move into areas we've already been.
     */
    
    private int explore ()
    {
        int response = DroidStatus.ERROR;

        while (!_theComputer.hasHalted())
        {
            boolean needToBackup = false;

            System.out.println("\n"+_theMap);

            /*
             * We search N, E, S and then W.
             */

            response = tryToMove(String.valueOf(DroidMovement.NORTH), DroidMovement.getNextPosition(_currentLocation, DroidMovement.NORTH));

            if ((response != DroidStatus.ARRIVED) && (response != DroidStatus.MOVED))
            {
                System.out.println("**Failed to move NORTH");

                System.out.println("\n"+_theMap);

                response = tryToMove(String.valueOf(DroidMovement.EAST), DroidMovement.getNextPosition(_currentLocation, DroidMovement.EAST));

                if ((response != DroidStatus.ARRIVED) && (response != DroidStatus.MOVED))
                {
                    System.out.println("**Failed to move WEST");

                    System.out.println("\n"+_theMap);

                    response = tryToMove(String.valueOf(DroidMovement.SOUTH), DroidMovement.getNextPosition(_currentLocation, DroidMovement.SOUTH));

                    if ((response != DroidStatus.ARRIVED) && (response != DroidStatus.MOVED))
                    {
                        System.out.println("**Failed to move SOUTH");

                        System.out.println("\n"+_theMap);

                        response = tryToMove(String.valueOf(DroidMovement.WEST), DroidMovement.getNextPosition(_currentLocation, DroidMovement.WEST));

                        if ((response != DroidStatus.ARRIVED) && (response != DroidStatus.MOVED))
                        {
                            System.out.println("**Failed to move EAST");

                            System.out.println("\n"+_theMap);

                            /*
                             * At this point we've exhausted all of the options for moving from
                             * the current location. Therefore, we need to backtrack.
                             */

                            System.out.println("**NEED TO BACKUP");

                            needToBackup = true;
                        }
                        else
                            recordJourney(DroidMovement.WEST);  // we moved WEST
                    }
                    else
                        recordJourney(DroidMovement.SOUTH);  // we moved SOUTH
                }
                else
                    recordJourney(DroidMovement.EAST);  // we moved EAST
            }
            else
                recordJourney(DroidMovement.NORTH);  // we moved NORTH

            if (needToBackup)
                return backtrack();
        }

        return response;
    }

    private int tryToMove (String direction, Coordinate to)
    {                
        System.out.println("**Trying to move from: "+_currentLocation+" to "+to+" with direction "+DroidMovement.toString(direction));

        // if we've already been there then don't move!

        if (_theMap.isExplored(to))
        {
            System.out.println("**Been there already.");

            return DroidStatus.VISITED;
        }

        _theComputer.setInput(direction);
        _theComputer.executeUntilInput();

        if (_theComputer.hasOutput())
        {
            int response = Integer.parseInt(_theComputer.getOutput());

            System.out.println("**Response is "+DroidStatus.toString(response));

            switch (response)
            {
                case DroidStatus.ARRIVED:  // arrived at the station!!
                {
                    _theMap.updateTile(_currentLocation, TileId.TRAVERSE);
                    _theMap.updateTile(to, TileId.OXYGEN_STATION);

                    _currentLocation = to;

                    recordJourney(Integer.parseInt(direction));

                    System.out.println("**FOUND OXYGEN!");

                    return response;
                }
                case DroidStatus.COLLISION:
                {
                    _theMap.updateTile(to, TileId.WALL);  // didn't move as we hit a wall

                    return response;
                }
                case DroidStatus.MOVED:
                {
                    /*
                     * Droid moved so let's try to move again.
                     */

                    _theMap.updateTile(_currentLocation, TileId.TRAVERSE);
                    _theMap.updateTile(to, TileId.DROID);

                    _currentLocation = to;

                    recordJourney(Integer.parseInt(direction));

                    return explore();
                }
                default:
                    System.out.println("Unknown response: "+response);
            }
        }
        else
            System.out.println("Error - no output after move instruction!");

        return DroidStatus.ERROR;  // error!!
    }

    private int backtrack ()
    {                
        int status = DroidStatus.ERROR;
        
        System.out.println("**backtrack trace");

        if (_trackTaken.size() > 0)
        {
            int backupDirection = DroidMovement.backupDirection(_trackTaken.pop());

            System.out.println("**Trying to backup from: "+_currentLocation+" with direction "+DroidMovement.toString(backupDirection));

            System.out.println("unrecording direction "+DroidMovement.toString(backupDirection));

            _theComputer.setInput(String.valueOf(backupDirection));
            _theComputer.executeUntilInput();

            if (_theComputer.hasOutput())
            {
                int response = Integer.parseInt(_theComputer.getOutput());

                if (response == DroidStatus.MOVED)
                {
                    _theMap.updateTile(_currentLocation, TileId.TRAVERSE);

                    _currentLocation = DroidMovement.getNextPosition(_currentLocation, backupDirection);

                    _theMap.updateTile(_currentLocation, TileId.DROID);

                    status = DroidStatus.BACKTRACKED;  // different from normal move response

                    System.out.println("Droid now at "+_currentLocation);
                }
                else
                    System.out.println("**Unexpected backup response: "+response);
            }
            else
                System.out.println("Error - no output after move instruction!");
        }
        else
            System.out.println("Cannot backtrack!");

        return status;
    }

    private void recordJourney (int direction)
    {
        System.out.println("recording direction "+DroidMovement.toString(direction));
        System.out.println("backup direction would be "+DroidMovement.toString(DroidMovement.backupDirection(direction)));
        System.out.println("Droid at "+_currentLocation);

        _trackTaken.push(direction);
    }

    private final void printTrack ()
    {
        Enumeration<Integer> iter = _trackTaken.elements();

        System.out.println("Path taken so far ...");

        while (iter.hasMoreElements())
            System.out.println("Moved "+DroidMovement.toString(iter.nextElement()));
    }

    private boolean _debug;
    private Intcode _theComputer;
    private Coordinate _currentLocation;
    private Maze _theMap;
    private Stack<Integer> _trackTaken;
}