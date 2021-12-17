import java.util.*;

public class Verifier
{
    public static final String EXAMPLE = "example.txt";
    public static final int EXAMPLE_PEOPLE = 11;
    public static final int EXAMPLE_ANSWERS = 6;
    public static final int EXAMPLE_GROUPS = 5;

    public Verifier (boolean debug)
    {
        _debug = debug;
    }

    public final boolean verify ()
    {
        Parser p = new Parser(_debug);
        Vector<Answers> answers = p.work(EXAMPLE);
        Enumeration<Answers> iter = answers.elements();
        int count = 0;
        int people = 0;
        
        while (iter.hasMoreElements())
        {
            Answers a = iter.nextElement();

            count += a.numberOfAnswersEveryoneAnsweredTrue();
            people += a.numberOfPeopleInGroup();
        }

        if (answers.size() == EXAMPLE_GROUPS)
        {
            if (people == EXAMPLE_PEOPLE)
            {
                if (count == EXAMPLE_ANSWERS)
                {
                    return true;
                }
                else
                    System.out.println("Wrong number of true answers in EXAMPLE: "+count);
            }
            else
                System.out.println("Wrong number of people in group in EXAMPLE: "+people);
        }
        else
            System.out.println("Wrong number of groups in EXAMPLE: "+answers.size());

        return false;
    }

    private boolean _debug;
}