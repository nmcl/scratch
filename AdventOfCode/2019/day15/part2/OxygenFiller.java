import java.util.*;

public class OxygenFiller
{
    public OxygenFiller (Maze theMaze, boolean debug)
    {
        _theMap = theMaze;
        _debug = debug;
        _currentLocation = theMaze.getOxygenStation();  // start at the oxygen station
        _trackTaken = new Stack<Integer>();
    }

    // trace through the maze until we get back to the start

    public int fillWithOxygen ()
    {
        int steps = 0;

        fill();

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
            //if (_debug)
                System.out.println("\n"+_theMap);

            if (!tryToFill(DroidMovement.EAST, DroidMovement.getNextPosition(_currentLocation, DroidMovement.EAST)))
            {
                //if (_debug)
                    System.out.println("\n"+_theMap);
                
                if (!tryToFill(DroidMovement.SOUTH, DroidMovement.getNextPosition(_currentLocation, DroidMovement.SOUTH)))
                {
                    //if (_debug)
                        System.out.println("\n"+_theMap);

                    if (!tryToFill(DroidMovement.WEST, DroidMovement.getNextPosition(_currentLocation, DroidMovement.WEST)))
                    {
                        //if (_debug)
                            System.out.println("\n"+_theMap);

                        /*
                         * At this point we've exhausted all of the options for moving from
                         * the current location. Therefore, we need to backtrack.
                         */

                        System.out.println("BACKTRACK");

                        backtrack();
                    }
                }
            }
        }

        return response;
    }

    private boolean tryToFill (int direction, Coordinate to)
    {
        //if (_debug)      
            System.out.println("Trying to fill from: "+_currentLocation+" to "+to+" with direction "+DroidMovement.toString(direction));

        if (_theMap.isOxygenStation(to))
            return false;

        if (_theMap.isWall(to))
        {
            System.out.println("**IS WALL");

            return false;
        }
        
        /*
         * Oxygen filled space.
         */

         _theMap.updateTile(_currentLocation, TileId.OXYGEN_STATION);

        _currentLocation = to;

        System.out.println("\n"+_theMap);

        recordJourney(direction);
                    
        return fill();
    }

    private boolean backtrack ()
    {                
        boolean status = false;

        if (_trackTaken.size() > 0)
        {
            int backupDirection = DroidMovement.backupDirection(_trackTaken.pop());

            //if (_debug)
                System.out.println("Trying to backup from: "+_currentLocation+" with direction "+DroidMovement.toString(backupDirection));

            _currentLocation = DroidMovement.getNextPosition(_currentLocation, backupDirection);

            status = true;
        }

        return status;
    }

    private void recordJourney (int direction)
    {
        _trackTaken.push(direction);
    }

    private Maze _theMap;
    private boolean _debug;
    private Coordinate _currentLocation;
    private Stack<Integer> _trackTaken;
}