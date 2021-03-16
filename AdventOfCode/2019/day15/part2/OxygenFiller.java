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

        response = tryToMove(String.valueOf(DroidMovement.NORTH), DroidMovement.getNextPosition(_currentLocation, DroidMovement.NORTH));

        if (response != DroidStatus.MOVED)
        {
            response = tryToMove(String.valueOf(DroidMovement.EAST), DroidMovement.getNextPosition(_currentLocation, DroidMovement.EAST));

            if (response != DroidStatus.MOVED)
            {
                response = tryToMove(String.valueOf(DroidMovement.SOUTH), DroidMovement.getNextPosition(_currentLocation, DroidMovement.SOUTH));

                if (response != DroidStatus.MOVED)
                {
                    response = tryToMove(String.valueOf(DroidMovement.WEST), DroidMovement.getNextPosition(_currentLocation, DroidMovement.WEST));

                    if (response != DroidStatus.MOVED)
                    {
                        /*
                            * At this point we've exhausted all of the options for moving from
                            * the current location. Therefore, we need to backtrack.
                            */

                        needToBackup = true;
                    }
                }
            }
        }

        if (needToBackup)
            backtrack();

        return response;
    }

    private Maze _theMaze;
    private boolean _debug;
}