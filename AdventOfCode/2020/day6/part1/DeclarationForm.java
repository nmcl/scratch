import java.util.*;

public class DeclarationForm
{
    public static final String INPUT_FILE = "input.txt";
    
    public static void main (String[] args)
    {
        boolean debug = false;
        boolean verify = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-verify] [-help]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;

            if ("-verify".equals(args[i]))
                verify = true;
        }

        if (verify)
        {
            Verifier v = new Verifier(debug);

            if (v.verify())
                System.out.println("Verified ok.");
            else
                System.out.println("Verify failed!");

            System.exit(0);
        }

        Parser p = new Parser(debug);
        Vector<Answers> answers = p.work(INPUT_FILE);
        Enumeration<Answers> iter = answers.elements();
        int count = 0;
        int people = 0;

        while (iter.hasMoreElements())
        {
            Answers a = iter.nextElement();

            count += a.numberOfTrueAnswers();
            people += a.numberOfPeopleInGroup();
        }

        System.out.println("The number of questions to which anyone answered 'yes': "+count);
    }
}