import java.util.*;

public class RepairDroid
{
    public static final String INITIAL_INPUT = Integer.toString(DroidMovement.NORTH);

    private static final int EXPLORE_UNTIL_OXYGEN = 1;
    private static final int EXPLORE_ENTIRE_MAZE = 2;

    public RepairDroid (Vector<String> instructions, boolean debug)
    {
        _debug = debug;
        _theComputer = new Intcode(instructions, INITIAL_INPUT, _debug);
        _currentLocation = new Coordinate(0, 0);  // starting location
        _startingPoint = _currentLocation;
        _theMap = new Maze();
        _trackTaken = new Stack<Integer>();
        _foundOxygenStation = false;
        _exploreOption = EXPLORE_UNTIL_OXYGEN;

        _theMap.updateTile(_currentLocation, TileId.STARTING_POINT);
    }

    public final int moveToOxygenStation ()
    {
        _exploreOption = EXPLORE_UNTIL_OXYGEN;

        explore();

        return _trackTaken.size();
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

        while (!_theComputer.hasHalted() && !stopSearch())
        {
            boolean needToBackup = false;

            //if (_debug)
                System.out.println("\n"+_theMap);

            /*
             * We search N, E, S and then W.
             */

            response = tryToMove(String.valueOf(DroidMovement.NORTH), DroidMovement.getNextPosition(_currentLocation, DroidMovement.NORTH));

            if (tryNextLocation(response))
            {
                //if (_debug)
                    System.out.println("\n"+_theMap);

                response = tryToMove(String.valueOf(DroidMovement.EAST), DroidMovement.getNextPosition(_currentLocation, DroidMovement.EAST));

                if (tryNextLocation(response))
                {
                    //if (_debug)
                        System.out.println("\n"+_theMap);

                    response = tryToMove(String.valueOf(DroidMovement.SOUTH), DroidMovement.getNextPosition(_currentLocation, DroidMovement.SOUTH));

                    if (tryNextLocation(response))
                    {
                        //if (_debug)
                            System.out.println("\n"+_theMap);

                        response = tryToMove(String.valueOf(DroidMovement.WEST), DroidMovement.getNextPosition(_currentLocation, DroidMovement.WEST));

                        if (tryNextLocation(response))
                        {
                            //if (_debug)
                                System.out.println("\n"+_theMap);

                            /*
                             * At this point we've exhausted all of the options for moving from
                             * the current location. Therefore, we need to backtrack.
                             */

                            needToBackup = true;
                        }
                    }
                }
            }

            if (_theComputer.status() == Status.HALTED)
                response = DroidStatus.HALTED;

            System.out.println("**DroidStatus "+DroidStatus.toString(response));
            System.out.println("**search status "+stopSearch());
            System.out.println("**and "+needToBackup);
            System.out.println("**and "+Status.toString(_theComputer.status()));
            System.out.println("**location "+_currentLocation);

            if (needToBackup)
                return backtrack();
        }

        if (_theComputer.status() == Status.HALTED)
            response = DroidStatus.HALTED;

        System.out.println("**DroidStatus "+DroidStatus.toString(response));

        return response;
    }

    private int tryToMove (String direction, Coordinate to)
    {          
        if (_debug)      
            System.out.println("Trying to move from: "+_currentLocation+" to "+to+" with direction "+DroidMovement.toString(direction));

        // if we've already been there then don't move!

        if (_theMap.isExplored(to))
            return DroidStatus.VISITED;

        _theComputer.setInput(direction);
        _theComputer.executeUntilInput();

        if (_theComputer.hasOutput())
        {
            int response = Integer.parseInt(_theComputer.getOutput());

            if (_debug)
                System.out.println("Response is "+DroidStatus.toString(response));

            switch (response)
            {
                case DroidStatus.ARRIVED:  // arrived at the station!!
                {
                    _theMap.updateTile(_currentLocation, TileId.TRAVERSE);
                    _theMap.updateTile(to, TileId.OXYGEN_STATION);

                    _currentLocation = to;

                    recordJourney(Integer.parseInt(direction));

                    _foundOxygenStation = true;

                    if (_debug)
                    {
                        System.out.println("FOUND OXYGEN!");

                        System.out.println("\n"+_theMap);
                    }

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

                    if (!_currentLocation.equals(_startingPoint))
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

        if (_trackTaken.size() > 0)
        {
            int backupDirection = DroidMovement.backupDirection(_trackTaken.pop());

            if (_debug)
                System.out.println("Trying to backup from: "+_currentLocation+" with direction "+DroidMovement.toString(backupDirection));

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
                }
                else
                    System.out.println("Unexpected backup response: "+response);
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
        if (!stopSearch())
            _trackTaken.push(direction);
    }

    private final void printTrack ()
    {
        Enumeration<Integer> iter = _trackTaken.elements();

        System.out.println("Path taken so far ...");

        while (iter.hasMoreElements())
            System.out.println("Moved "+DroidMovement.toString(iter.nextElement()));
    }

    private final boolean stopSearch ()
    {
        if ((_exploreOption == EXPLORE_UNTIL_OXYGEN) && (_foundOxygenStation))
            return true;
        else
            return false;
    }

    private final boolean tryNextLocation (int response)
    {
        boolean result = false;

        if (_exploreOption == EXPLORE_UNTIL_OXYGEN)
        {
            if ((response != DroidStatus.ARRIVED) && (response != DroidStatus.MOVED))
                result = true;
        }
        else
        {
            if (response != DroidStatus.MOVED)
                result = true;
        }
        
        return result;
    }

    private boolean _debug;
    private Intcode _theComputer;
    private Coordinate _currentLocation;
    private Coordinate _startingPoint;
    private Maze _theMap;
    private Stack<Integer> _trackTaken;
    private boolean _foundOxygenStation;
    private int _exploreOption;
}