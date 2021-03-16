public class OxygenFiller
{
    public OxygenFiller (Maze theMaze, boolean debug)
    {
        _theMaze = theMaze;
        _debug = debug;
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
    
    private int fill ()
    {
        int response = DroidStatus.ERROR;
        boolean needToBackup = false;

        /*
         * We search N, E, S and then W.
         */

        response = tryToFill(String.valueOf(DroidMovement.NORTH), DroidMovement.getNextPosition(_currentLocation, DroidMovement.NORTH));

        if (response != DroidStatus.MOVED)
        {
            response = tryToFill(String.valueOf(DroidMovement.EAST), DroidMovement.getNextPosition(_currentLocation, DroidMovement.EAST));

            if (response != DroidStatus.MOVED)
            {
                response = tryToFill(String.valueOf(DroidMovement.SOUTH), DroidMovement.getNextPosition(_currentLocation, DroidMovement.SOUTH));

                if (response != DroidStatus.MOVED)
                {
                    response = tryToFill(String.valueOf(DroidMovement.WEST), DroidMovement.getNextPosition(_currentLocation, DroidMovement.WEST));

                    if (response != DroidStatus.MOVED)
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

    private int tryToFill (String direction, Coordinate to)
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
                        System.out.println("Found Oxygen Station at: "+to);

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

                    if (!_currentLocation.equals(_theMap.getStartingPoint()))
                    {
                        if (!_currentLocation.equals(_theMap.getOxygenStation()))
                            _theMap.updateTile(_currentLocation, TileId.TRAVERSE);
                    }

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

    private Maze _theMaze;
    private boolean _debug;
}