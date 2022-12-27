import java.util.*;

public class Bingo
{
    public static final String DATA_FILE = "input.txt";

    public static void main (String[] args)
    {
        boolean verify = false;
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-verify] [-help]");
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

        Vector<Integer> numbers = Util.loadNumbers(DATA_FILE, debug);
        Vector<Board> boards = Util.loadBoards(DATA_FILE, debug);

        if (debug)
        {
            System.out.println("Loaded:\n");

            for (int i = 0; i < numbers.size(); i++)
                System.out.print(numbers.elementAt(i)+",");

            System.out.println("\n");

            for (int i = 0; i < boards.size(); i++)
                System.out.println(boards.elementAt(i)+"\n");
        }

        Caller c = new Caller(debug);
        Result r = c.playTheGame(numbers, boards);

        if (debug)
            System.out.println("\nResult:\n\n"+r);
    }
}