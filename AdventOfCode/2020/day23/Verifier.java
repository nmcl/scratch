public class Verifier
{
    public static final String EXAMPLE = "389125467";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Game theGame = new Game(_debug);

        theGame.play(EXAMPLE, 10);
    }

    private boolean _debug;
}