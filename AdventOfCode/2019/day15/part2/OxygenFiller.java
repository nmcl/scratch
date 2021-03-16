import java.util.*;

public class OxygenFiller
{
    public OxygenFiller (Maze theMaze, boolean debug)
    {
        _theMaze = theMaze;
        _debug = debug;
        _currentLocation = theMaze.getOxygenStation();  // start at the oxygen station
        _trackTaken = new Stack<Integer>();
    }

    // trace through the maze until we get back to the start

    public int fillWithOxygen ()
    {
        int steps = 0;

        return steps;
    }

    /*
     * If we run into a wall then try a different direction.
     * If we can't move other than backwards then do that.
     * Don't move into areas we've already been.
     */
    
    private boolean fill ()
    {
        boolean response = false;
        boolean needToBackup = false;

        /*
         * We search N, E, S and then W.
         */

        if (!tryToFill(DroidMovement.NORTH, DroidMovement.getNextPosition(_currentLocation, DroidMovement.NORTH)))
        {
            if (!tryToFill(DroidMovement.EAST, DroidMovement.getNextPosition(_currentLocation, DroidMovement.EAST)))
            {
                if (!tryToFill(DroidMovement.SOUTH, DroidMovement.getNextPosition(_currentLocation, DroidMovement.SOUTH)))
                {
                    if (!tryToFill(DroidMovement.WEST, DroidMovement.getNextPosition(_currentLocation, DroidMovement.WEST)))
                    {
                        /*
                         * At this point we've exhausted all of the options for moving from
                         * the current location. Therefore, we need to backtrack.
                         */

                    }
                }
            }
        }

        return response;
    }

    private boolean tryToFill (int direction, Coordinate to)
    {
        if (_debug)      
            System.out.println("Trying to move from: "+_currentLocation+" to "+to+" with direction "+DroidMovement.toString(direction));

        // if we've already been there then don't move!

        if (_theMap.isExplored(to))
            return false;

        if (_theMap.isWall(to))
            return false;
        
        /*
         * Oxygen filled space.
         */

         _theMap.updateTile(_currentLocation, TileId.OXYGEN_STATION);

        _currentLocation = to;

        recordJourney(Integer.parseInt(direction));
                    
        return fill();
    }

    private boolean backtrack ()
    {                
        boolean status = false;

        if (_trackTaken.size() > 0)
        {
            int backupDirection = DroidMovement.backupDirection(_trackTaken.pop());

            if (_debug)
                System.out.println("Trying to backup from: "+_currentLocation+" with direction "+DroidMovement.toString(backupDirection));


                if (response == DroidStatus.MOVED)
                {
                    if (!_currentLocation.equals(_theMap.getStartingPoint()))
                    {
                        if (!_currentLocation.equals(_theMap.getOxygenStation()))
                            _theMap.updateTile(_currentLocation, TileId.TRAVERSE);
                    }

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

        return status;
    }

    private Maze _theMaze;
    private boolean _debug;
    private Coordinate _currentLocation;
    private Stack<Integer> _trackTaken;
}