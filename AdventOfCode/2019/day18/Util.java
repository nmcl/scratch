import java.util.*;
import java.io.*;

/*
 * Various utilities. Some are fairly simple.
 */

public class Util
{
    public static final boolean isDoor (char content)
    {
        return Character.isUpperCase(content);
    }

    public static final boolean isKey (char content)
    {
        return Character.isLowerCase(content);
    }

    public static final String keycode (Set<Character> keys)
    {
        String str = "";
        Iterator<Character> iter = keys.iterator();

        while (iter.hasNext())
        {
            Character c = iter.next();

            str += c;
        }

        return str;
    }

    public static final Vector<String> readMap (String inputFile)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<String> map = new Vector<String>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                map.add(line);
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

        return map;
    }

    private Util ()
    {
    }
}