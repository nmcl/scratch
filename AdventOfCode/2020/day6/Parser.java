import java.io.*;

public class Parser
{
    public Parser (boolean debug)
    {
        _debug = debug;
    }

    public Questions work (String fileName)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Answers theAnswers = new Answers();

        try
        {
            reader = new BufferedReader(new FileReader(fileName));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                if (debug)
                    System.out.println("Loaded: "+b);

                thePlane.addSeat(b.getSeat());
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
    }
    
    private boolean _debug;
}