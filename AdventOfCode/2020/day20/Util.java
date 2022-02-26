import java.util.*;
import java.io.*;

public class Util
{
    public static final Vector<Tile> loadData (String inputFile)
    {
        /*
         * Open the data file and read it in.
         */

        BufferedReader reader = null;
        Vector<Tile> data = new Vector<Tile>();

        try
        {
            reader = new BufferedReader(new FileReader(inputFile));
            String line = null;

            while ((line = reader.readLine()) != null)
            {
                long id = -1;

                if (line.startsWith(TileData.TILE_ID))
                {
                    int index = line.indexOf(':');
                    String value = line.substring(TileData.TILE_ID.length(), index);
                    Vector<String> lines = new Vector<String>();

                    id = Long.parseLong(value);

                    while ((line = reader.readLine()) != null)
                    {
                        if (line.length() > 0)
                            lines.add(line);
                        else
                            break;
                    }

                    String[] tileLines = new String[lines.size()];

                    tileLines = lines.toArray(tileLines);

                    data.add(new Tile(id, tileLines));
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

        return data;
    }

    private Util ()
    {
    }
}