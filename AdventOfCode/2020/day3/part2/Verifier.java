public class Verifier
{
    public static final String EXAMPLE_FILE = "example.txt";

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
        
        int trees2 = _taboggan.move(Trajectory.JUMP_2);

        if (_debug)
            System.out.println("Trees encountered: "+trees2);

        int trees3 = _taboggan.move(Trajectory.JUMP_3);

        if (_debug)
            System.out.println("Trees encountered: "+trees3);

        int trees4 = _taboggan.move(Trajectory.JUMP_4);

        if (_debug)
            System.out.println("Trees encountered: "+trees4);

        int trees5 = _taboggan.move(Trajectory.JUMP_5);

        if (_debug)
            System.out.println("Trees encountered: "+trees5);

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