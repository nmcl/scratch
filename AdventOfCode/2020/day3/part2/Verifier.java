public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";

    public static final int TREES_1 = 2;
    public static final int TREES_2 = 7;
    public static final int TREES_3 = 3;
    public static final int TREES_4 = 4;
    public static final int TREES_5 = 2;
    
    public static final int NUMBER_OF_TREES = 336;

    public Verifier (boolean debug)
    {
        _theMap = new Map(EXAMPLE_FILE, debug);
        _taboggan = new Taboggan(_theMap, debug);
        _debug = debug;
    }

    public boolean verify ()
    {
        if (_debug)
            System.out.println("Verified loaded\n"+_theMap);

        int trees1 = _taboggan.move(Trajectory.JUMP_1);

        if (_debug)
            System.out.println("Trees encountered: "+trees1);
        
        if (trees1 != TREES_1)
        {
            System.out.println("Wrong number of trees1: "+trees1);

            return false;
        }

        int trees2 = _taboggan.move(Trajectory.JUMP_2);

        if (_debug)
            System.out.println("Trees encountered: "+trees2);

        if (trees2 != TREES_2)
        {
            System.out.println("Wrong number of trees2: "+trees2);

            return false;
        }

        int trees3 = _taboggan.move(Trajectory.JUMP_3);

        if (_debug)
            System.out.println("Trees encountered: "+trees3);

        if (trees3 != TREES_3)
        {
            System.out.println("Wrong number of trees3: "+trees3);

            return false;
        }

        int trees4 = _taboggan.move(Trajectory.JUMP_4);

        if (_debug)
            System.out.println("Trees encountered: "+trees4);

        if (trees4 != TREES_4)
        {
            System.out.println("Wrong number of trees4: "+trees4);

            return false;
        }

        int trees5 = _taboggan.move(Trajectory.JUMP_5);

        if (_debug)
            System.out.println("Trees encountered: "+trees5);

        if (trees5 != TREES_5)
        {
            System.out.println("Wrong number of trees5: "+trees5);

            return false;
        }

        int multTrees = trees1 * trees2 * trees3 * trees4 * trees5;

        if (multTrees == NUMBER_OF_TREES)
            return true;
        else
            return false;
    }

    private Map _theMap;
    private Taboggan _taboggan;
    private boolean _debug;
}