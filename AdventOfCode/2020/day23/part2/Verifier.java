public class Verifier
{
    public static final String EXAMPLE = "389125467";
    public static final String EXAMPLE_RESULT_CUP_1 = "934001";
    public static final String EXAMPLE_RESULT_CUP_2 = "159792";

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Game theGame = new Game(_debug);

        String result = theGame.play(EXAMPLE, 10);

        if (EXAMPLE_RESULT_10.equals(result))
        {
            result = theGame.play(EXAMPLE, 100);

            if (EXAMPLE_RESULT_100.equals(result))
                return true;
            else
                System.out.println("Wrong result after 10 rounds: "+result);
        }
        else
            System.out.println("Wrong result after 10 rounds: "+result);

        return false;
    }

    private boolean _debug;
}