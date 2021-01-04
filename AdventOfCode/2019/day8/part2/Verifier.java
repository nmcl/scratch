public class Verifier
{
    public static final String EXAMPLE_DATA = "0222112222120000";
    public static final int EXAMPLE_WIDTH = 2;
    public static final int EXAMPLE_HEIGHT = 2;

    public Verifier (boolean debug)
    {
        _theImage = new Image(EXAMPLE_WIDTH, EXAMPLE_HEIGHT, EXAMPLE_DATA);
        _debug = debug;

        if (_debug)
            System.out.println("Created image:\n"+_theImage);
    }

    public final boolean verify ()
    {
        if (_theImage.numberOfLayers() == 4)
            return true;
        else
            return false;
    }

    private Image _theImage;
    private boolean _debug;
}