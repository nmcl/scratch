public class Verifier
{
    public static final String EXAMPLE = "389125467";
    public static final String EXAMPLE_RESULT = "149245887792";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Game theGame = new Game(_debug);
        String result = theGame.play(EXAMPLE, 1000000);

        if (EXAMPLE_RESULT.equals(result))
            return true;

        System.out.println("Wrong result after 1000000 rounds: "+result);

        return false;
    }

    private boolean _debug;
}