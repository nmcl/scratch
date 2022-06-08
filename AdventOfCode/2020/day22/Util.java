import java.util.*;
import java.io.*;

public class Util
{
    public static final String PLAYER_DESIGNATOR = "Player ";

    public static final Deck[] loadRules (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Deck[] decks = new Deck[2];
        int player = 0;

        decks[0] = new Deck(1);
        decks[1] = new Deck(2);

        try
        {
            String line = null;

            reader = new BufferedReader(new FileReader(inputFile));

            while ((line = reader.readLine()) != null)
            {
                if (line.startsWith(PLAYER_DESIGNATOR))
                {
                    player = Integer.parseInt(line.substring(PLAYER_DESIGNATOR.length(), line.indexOf(':')));

                    if (debug)
                        System.out.println("Loading for player "+player);
                }
                else
                {
                    try
                    {
                        int value = Integer.parseInt(line);

                        if (debug)
                            System.out.println("Adding "+value+" to player "+player);

                        decks[player -1].add(value);
                    }
                    catch (Exception ex)
                    {
                        // for the blank line
                    }
                }
            }
        }
        catch (Throwable ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            try
            {
                reader.close();
            }
            catch (Throwable ex)
            {
            }
        }

        return decks;
    }

    // prevent instantiation

    private Util ()
    {
    }
}