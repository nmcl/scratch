import java.util.*;
import java.io.*;

public class Util
{
    public static final char RULE_NAME_DELIMITER = ':';
    public static final char OR_DELIMITER = '|';
    public static final char MATCH = '"';
    public static final String RULE_DELIMITER = " ";

    public static final Rule[] loadRules (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Rule> values = new Vector<Rule>();
        int maxRule = 0;

        try
        {
            String line = null;

            reader = new BufferedReader(new FileReader(inputFile));
         
            while (((line = reader.readLine()) != null) && (line.length() > 0))
            {
                int nameDelim = line.indexOf(RULE_NAME_DELIMITER);
                String name = line.substring(0, nameDelim);

                if (debug)
                    System.out.println("Rule name: "+name);

                int matchDelim = line.indexOf(MATCH);

                if (matchDelim == -1)
                {
                    int orDelim = line.indexOf(OR_DELIMITER);  // there's only ever a maximum of one of these in a rule
                    String dataOne = line.substring(nameDelim +1, ((orDelim == -1) ? line.length() : orDelim));
                    String dataTwo = ((orDelim == -1) ? null : line.substring(orDelim +1));

                    if (debug)
                    {
                        System.out.println("Rule one: "+dataOne);
                        System.out.println("Rule two: "+dataTwo);
                    }

                    String[] dataOneList = dataOne.trim().split(RULE_DELIMITER);
                    String[] dataTwoList = (dataTwo == null ? null : dataTwo.trim().split(RULE_DELIMITER));

                    if (debug && (dataOneList != null))
                    {
                        for (int i = 0; i < dataOneList.length; i++)
                            System.out.println("Rule one list: "+dataOneList[i]);
                    }

                    if (debug && (dataTwoList != null))
                    {
                        for (int i = 0; i < dataTwoList.length; i++)
                            System.out.println("Rule two list: "+dataTwoList[i]);
                    }

                    Rule r = new Rule(name, dataOneList, dataTwoList);

                    if (r.getNumber() > maxRule)
                        maxRule = r.getNumber();

                    values.add(r);
                }
                else
                {
                    // match is always one character

                    Rule r = new Rule(name, line.charAt(matchDelim +1));

                    if (r.getNumber() > maxRule)
                        maxRule = r.getNumber();

                    values.add(r);
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

        Rule[] ordered = new Rule[maxRule +1];

        for (int i = 0; i < values.size(); i++)
        {
            Rule r = values.elementAt(i);
            
            ordered[r.getNumber()] = r;
        }

        return ordered;
    }

    public static final Message[] loadMessages (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Message> values = new Vector<Message>();

        try
        {
            String line = null;

            reader = new BufferedReader(new FileReader(inputFile));
            
            while (((line = reader.readLine()) != null) && (line.length() > 0))
            {
                // loop until blank line.
            }

            while ((line = reader.readLine()) != null)
            {
                values.add(new Message(line));
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

        return values.toArray(new Message[values.size()]);
    }

    private Util ()
    {
    }
}