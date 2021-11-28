import java.util.*;
import java.io.*;

public class Batch
{
    public static char DELIMITER = ':';
    public static char SPACE = ' ';

    public static Vector<Passwport> loadData (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Passport> results = new Vector<Passport>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                if (line.length() > 0) // blank?
                {
                    int byr = 0;
                    int iry = 0;
                    int eyr = 0;
                    int hgt = 0;
                    String hcl = "";
                    String ecl = "";
                    int pid = 0;
                    int cid = 0;

                    int rangeDelimiter = line.indexOf(RANGE_DELIMITER);
                    int space = line.indexOf(SPACE);
                    String minimum = line.substring(0, rangeDelimiter);
                    String maximum = line.substring(rangeDelimiter +1, space);
                    int passwordDelimiter = line.indexOf(PASSWORD_DELIMITER);
                    String letter = line.substring(space +1, passwordDelimiter);
                    String password = line.substring(passwordDelimiter +1).trim();

                    if (debug)
                    {
                        System.out.println("\nLoaded < "+minimum+", "+maximum+" >");
                        System.out.println("Letter: "+letter);
                        System.out.println("Password to check: "+password);
                    }

                    PasswordPolicy policy = new PasswordPolicy(Integer.parseInt(minimum), Integer.parseInt(maximum), letter.charAt(0));

                    results.add(new PasswordData(policy, password));
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

        return results;
    }

    private Batch ()
    {
    }
}