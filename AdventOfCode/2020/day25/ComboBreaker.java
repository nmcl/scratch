import java.io.*;

public class ComboBreaker
{
    public static final String DATA_FILE = "input.txt";

    public static void main (String[] args)
    {
        boolean debug = false;

        for (int i = 0; i < args.length; i++)
        {
            if ("-help".equals(args[i]))
            {
                System.out.println("Usage: [-debug] [-help]");
                System.exit(0);
            }

            if ("-debug".equals(args[i]))
                debug = true;
        }

        BufferedReader reader = new BufferedReader(new FileReader(DATA_FILE));

        int cardPublicKey = Integer.parseInt(reader.readLine());
        int doorPublicKey = Integer.parseInt(reader.readLine());
    }
}
