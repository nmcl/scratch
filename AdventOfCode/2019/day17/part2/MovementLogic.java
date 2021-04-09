public class MovementLogic
{
    public static final int ROUTINE_A = 0;
    public static final int ROUTINE_B = 1;
    public static final int ROUTINE_C = 2;
    public static final int ROUTINE_D = 3;

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

    private void createPath ()
    {
        Coordinate start = _theMap.findStartingPoint();

        if (start != null)
        {

        }
        else
            System.out.println("Robot not found!");
    }

    private Map _theMap;
}