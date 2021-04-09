public class MovementLogic
{
    public static final int ROUTINE_A = 0;
    public static final int ROUTINE_B = 1;
    public static final int ROUTINE_C = 2;
    public static final int ROUTINE_D = 3;

    public static final String MOVE_LEFT = "L";
    public static final String MOVE_RIGHT = "R";

    public MovementLogic (Map theMap)
    {
        _theMap = theMap;
    }

    public void createMovementFunctions ()
    {

    }

    public void createMovementRoutine ()
    {
        
    }

    /*
     * Create path using only L and R.
     */

    private void createPath ()
    {
        Coordinate currentPosition = new Coordinate(_theMap.findStartingPoint());
        
        if (currentPosition != null)
        {
            
        }
        else
            System.out.println("Robot not found!");
    }

    private Map _theMap;
}