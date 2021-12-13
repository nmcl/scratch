import java.util.*;

public class Verifier
{
    public static final String EXAMPLE1 = "example1.txt";
    public static final int EXAMPLE1_PEOPLE = 3;
    public static final int EXAMPLE1_ANSWERS = 6;
    public static final int EXAMPLE1_GROUPS = 1;

    public static final String EXAMPLE2 = "example2.txt";
    public static final int EXAMPLE2_PEOPLE = 11;
    public static final int EXAMPLE2_ANSWERS = 11;
    public static final int EXAMPLE2_GROUPS = 5;
    
    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Parser p = new Parser(_debug);
        Vector<Answers> answers = p.work(EXAMPLE1);

        if (answers.size() == EXAMPLE1_GROUPS)
        {
            Answers a = answers.elementAt(0);

            if (a.numberOfPeopleInGroup() == EXAMPLE1_PEOPLE)
            {
                if (a.)
            }
            else
                System.out.println("Wrong number of people in group in EXAMPLE1: "+a.numberOfPeopleInGroup());
        }
        else
            System.out.println("Wrong number of groups in EXAMPLE1: "+answers.size());

        return false;
    }

    private boolean _debug;
}