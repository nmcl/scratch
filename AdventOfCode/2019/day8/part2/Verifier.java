public class Verifier
{
    public static final String EXAMPLE_DATA = "0222112222120000";
    public static final int EXAMPLE_WIDTH = 2;
    public static final int EXAMPLE_HEIGHT = 2;
    public static final char[] EXAMPLE_RESULT = {'0','1','1','0'};

    /*
     * 02
     * 22
     * 
     * 11
     * 22
     * 
     * 22
     * 12
     * 
     * 00
     * 00
     * 
     * Gives ...
     * 
     * 0 1
     * 1 0
     */

    public Verifier (boolean debug)
    {
        _theImage = new Image(EXAMPLE_WIDTH, EXAMPLE_HEIGHT, EXAMPLE_DATA);
        _debug = debug;

        if (_debug)
            System.out.println("Created image:\n"+_theImage);
    }

    public final boolean verify ()
    {
        Layer finalLayer = _theImage.getRenderedLayer();
        Layer expectedLayer = new Layer(EXAMPLE_WIDTH, EXAMPLE_HEIGHT, EXAMPLE_RESULT);

        System.out.println("Got back:\n"+finalLayer);

        return finalLayer.equals(expectedLayer);
    }

    private Image _theImage;
    private boolean _debug;
}