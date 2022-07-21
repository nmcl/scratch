public class Verifier
{
    public static final String EXAMPLE = "389125467";
    public static final long EXAMPLE_RESULT = 149245887792L;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Game theGame = new Game(_debug);
        long result = theGame.play(EXAMPLE, 10000000);

        if (result == EXAMPLE_RESULT)
            return true;

        System.out.println("Wrong result after 10000000 rounds: "+result);

        return false;
    }

    private boolean _debug;
}