import java.io.*;
import java.util.*;

public class Parser
{
    public Parser (boolean debug)
    {
        _debug = debug;
    }

    public Vector<Answers> work (String fileName)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Answers> allAnswers = new Vector<Answers>();

        try
        {
            reader = new BufferedReader(new FileReader(fileName));
            String line = null;
            Answers theAnswer = new Answers();

            while ((line = reader.readLine()) != null)
            {
                if (_debug)
                    System.out.println("Loaded: "+line);

                if (line.length() == 0)
                {
                    allAnswers.add(theAnswer);
                    theAnswer = new Answers();
                }
                else
                {
                    theAnswer.addPersonToGroup();

                    for (int i = 0; i < line.length(); i++)
                    {
                        theAnswer.answerQuestion(line.charAt(i));
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

        return allAnswers;
    }
    
    private boolean _debug;
}