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

        if (answers.size() == EXAMPLE_GROUPS)
        {
            Answers a = answers.elementAt(0);

            if (a.numberOfPeopleInGroup() == EXAMPLE_PEOPLE)
            {
                if (a.numberOfAnswersEveryoneAnsweredTrue() == EXAMPLE_ANSWERS)
                {
                    return true;
                }
                else
                    System.out.println("Wrong number of true answers in EXAMPLE: "+a.numberOfAnswersEveryoneAnsweredTrue());
            }
            else
                System.out.println("Wrong number of people in group in EXAMPLE: "+a.numberOfPeopleInGroup());
        }
        else
            System.out.println("Wrong number of groups in EXAMPLE: "+answers.size());

        return false;
    }

    private boolean _debug;
}