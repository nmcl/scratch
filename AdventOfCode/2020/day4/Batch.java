import java.util.*;
import java.io.*;

public class Batch
{
    public static String DELIMITER = ":";
    public static String SPACE = " ";

    public static Vector<Passport> loadData (String inputFile, boolean debug)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Passport> results = new Vector<Passport>();

        try
        {
            String line = null;

            reader = new BufferedReader(new FileReader(inputFile));

            while ((line = reader.readLine()) != null)
            {
                Passport p = new Passport();

                if (line.length() > 0) // blank?
                {
                    String[] tuples = line.split(SPACE);

                    for (int i = 0; i < tuples.length; i++)
                    {
                        String[] pairs = tuples[i].split(DELIMITER);

                        if (pairs.length != 2)
                            System.out.println("Error - more than two in the pairs field! "+pairs);
                        else
                        {
                            switch (pairs[0])
                            {
                                case PassportFields.BIRTH_YEAR:
                                {
                                    p.setBirthYear(Integer.parseInt(pairs[1]));
                                }
                                break;
                                case PassportFields.ISSUE_YEAR:
                                {
                                    p.setIssueYear(Integer.parseInt(pairs[1]));
                                }
                                break;
                                case PassportFields.EXPORATION_YEAR:
                                {
                                    p.setExpirationYear(Integer.parseInt(pairs[1]));
                                }
                                break;
                                case PassportFields.HEIGHT:
                                {
                                    p.setHeight(Integer.parseInt(pairs[1]));
                                }
                                break;
                                case PassportFields.HAIR_COLOUR:
                                {
                                    p.setHairColour(pairs[1]);
                                }
                                break;
                                case PassportFields.EYE_COLOUR:
                                {
                                    p.setEyeColour(pairs[1]);
                                }
                                break;
                                case PassportFields.PASSPORT_ID:
                                {
                                    p.setPassportID(Integer.parseInt(pairs[1]));
                                }
                                break;
                                case PassportFields.COUNTRY_ID:
                                {
                                    p.setCountryID(Integer.parseInt(pairs[1]));
                                }
                                break;
                                default:
                                {
                                    System.out.println("Error - unknown field: "+pairs[0]);
                                }
                                break;
                            }
                        }
                    }
                }

                if (debug)
                    System.out.println("Loaded passport: "+p);

                results.add(p);
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