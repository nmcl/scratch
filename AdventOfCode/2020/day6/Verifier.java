import java.util.*;

public class Verifier
{
    public static final String EXAMPLE1 = "example1.txt";
    public static final int EXAMPLE1_ANSWERS = 6;

    public static final String EXAMPLE2 = "example2.txt";
    public static final int EXAMPLE2_ANSWERS = 11;
    
    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Parser p = new Parser(_debug);
        Vector<Answers> answers = p.work(EXAMPLE1);

        System.out.println("Size: "+answers.size());
        
        return false;
    }

    private boolean _debug;
}